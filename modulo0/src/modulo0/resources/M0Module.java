package modulo0.resources;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.jdt.annotation.Nullable;
import org.nuthatchery.pica.errors.Severity;
import org.nuthatchery.pica.resources.IFact;
import org.nuthatchery.pica.resources.ILanguage;
import org.nuthatchery.pica.resources.IProjectManager;
import org.nuthatchery.pica.resources.IXRefInfo;
import org.nuthatchery.pica.resources.handles.IFileHandle;
import org.nuthatchery.pica.resources.internal.AbstractManagedResource;
import org.nuthatchery.pica.resources.internal.facts.GenericFact;
import org.nuthatchery.pica.resources.managed.IManagedCodeUnit;
import org.nuthatchery.pica.resources.managed.IManagedContainer;
import org.nuthatchery.pica.resources.regions.CodeRegion;
import org.nuthatchery.pica.resources.regions.IOffsetLength;
import org.nuthatchery.pica.resources.storage.IStorage;
import org.nuthatchery.pica.tasks.ITaskMonitor;
import org.nuthatchery.pica.tasks.NullTaskMonitor;
import org.nuthatchery.pica.util.Hash;
import org.nuthatchery.pica.util.ISignature;
import org.nuthatchery.pica.util.Pair;

import modulo0.Modulo0Language;
import modulo0.parser.M0Parser;
import modulo0.parser.Token;
import modulo0.tree.ImportList;
import modulo0.tree.Module;
import modulo0.tree.Var;

public class M0Module extends AbstractManagedResource implements IManagedContainer, IManagedCodeUnit {
	protected final static String MARK_SOURCE = M0Module.class.getName();
	protected final static String MARK_SOURCE_READING_FILE = MARK_SOURCE + "#loadError";
	protected final static String MARK_SOURCE_PARSING = MARK_SOURCE + "#syntaxError";
	protected final static String MARK_SOURCE_GETPACKAGEENV = MARK_SOURCE + "#getPackageEnv";
	protected final static String MARK_SOURCE_RESOLVE_IMPORTS = MARK_SOURCE + "#resolveImports";
	private final AtomicInteger revision = new AtomicInteger(0);

	private final IStorage storage;
	private final IProjectManager manager;
	private final IFact<Module> treeFact;
	private final IFact<List<M0Module>> dependsFact;

	private volatile ISignature sourceSignature;
	private volatile ISignature fullSignature;
	private volatile ISignature depSignature;

	public M0Module(IProjectManager manager, IFileHandle resource, IStorage storage) {
		super(resource.getURI(), resource);
		this.manager = manager;
		this.storage = storage;
		this.treeFact = new GenericFact<>("tree", storage, null);
		this.dependsFact = new GenericFact<>("depends");

//		System.err.println("M0Module: RESOURCE ADDED: " + resource.getURI());

	}

	private List<M0Module> dependsFact(ITaskMonitor tm) {
		synchronized (dependsFact) {
			Module module = treeFact(tm);
			List<M0Module> imports = new ArrayList<>();
			ImportList list = module.getImportList();
			for (int i = 0; i < list.arity(); i++) {
				Var imp = (Var) list.getChild(i);
				String importName = imp.getToken().getData();

				IManagedCodeUnit unit = manager.findCodeUnit(getLanguage(), importName);

				if (unit instanceof M0Module)
					imports.add((M0Module) unit);
			}

			return imports;
		}

	}

	@Override
	@Nullable
	public IManagedCodeUnit getChild(Object childId, ITaskMonitor tm) {
		return null;
	}

	@Override
	public Collection<? extends IManagedCodeUnit> getChildren(ITaskMonitor tm) {
		return Arrays.asList();
	}

	@Override
	public Collection<? extends IManagedCodeUnit> getDepends(ITaskMonitor tm) {
		return dependsFact(tm);
	}

	private IFileHandle getFile() {
		return (IFileHandle) resource;
	}

	@Override
	public ISignature getFullSignature(ITaskMonitor tm) {
		return getSourceSignature(tm);
	}

	@Override
	public String getId() {
		return treeFact(new NullTaskMonitor()).getModuleName().getData();
	}

	@Override
	public String getKind(ITaskMonitor tm) {
		return "module";
	}

	@Override
	public ILanguage getLanguage() {
		return Modulo0Language.getInstance();
	}

	@Override
	public long getLength() throws UnsupportedOperationException, IOException {
		return getFile().getLength(new NullTaskMonitor());
	}

	@Override
	public String getName() {
		return treeFact(new NullTaskMonitor()).getModuleName().getData();
	}

	@Override
	public long getOffset() throws UnsupportedOperationException {
		return 0L;
	}

	@Override
	@Nullable
	public IManagedContainer getParent() {
		return null;
	}

	@Override
	public ISignature getSourceSignature(ITaskMonitor tm) {
		ISignature sig = sourceSignature;
		if (sig != null) {
			return sig;
		}
		loadFile(tm);
		sig = sourceSignature;
		assert sig != null;
		return sig;
	}

	@Override
	@Nullable
	public IStorage getStorage() {
		return storage;
	}

	@Override
	public Collection<? extends IManagedCodeUnit> getTransitiveDepends(ITaskMonitor tm) {
		Set<IManagedCodeUnit> depends = new HashSet<IManagedCodeUnit>();
		List<IManagedCodeUnit> todo = new ArrayList<IManagedCodeUnit>(getDepends(tm));
		while (!todo.isEmpty()) {
			IManagedCodeUnit pkg = todo.remove(0);
			depends.add(pkg);
			for (IManagedCodeUnit p : pkg.getDepends(tm)) {
				if (p != this && !depends.contains(p)) {
					todo.add(p);
				} else if (p == this) {
					;// dependency cycle
				}
			}
		}
		return depends;
	}

	@Override
	public URI getURI() {
		return resource.getURI();
	}

	@Override
	@Nullable
	public IXRefInfo getXRefs(IOffsetLength loc, ITaskMonitor tm) {
		return null;
	}

	@Override
	public Collection<Pair<IOffsetLength, Object>> getXRefs(ITaskMonitor tm) {
		return Arrays.asList();
	}

	@Override
	public boolean hasIncompleteDepends(ITaskMonitor tm) {
		return false;
	}

	@Override
	public boolean isCodeUnit() {
		return true;
	}

	@Override
	public boolean isFragment() {
		return false;
	}

	@Override
	public boolean isRoot() {
		return false;
	}

	private @Nullable char[] loadFile(ITaskMonitor tm) {
		// load the file
		char[] cs = null;
		try {
			manager.clearMarks(MARK_SOURCE_READING_FILE, getURI());
			cs = getFile().getContentsCharArray(tm);
			sourceSignature = Hash.hashChars(cs);
		} catch (IOException e) {
			sourceSignature = Hash.hashChars(new char[0]);
			manager.addMark(e.getMessage(), new CodeRegion<URI>(getURI(), 0, 0), Severity.ERROR,
					MARK_SOURCE_READING_FILE, getURI());
		}
		return cs;
	}

	@Override
	public void onDependencyChanged() {
		fullSignature = null;
		depSignature = null;
		revision.incrementAndGet();
		/*
		 * Collection<M0Def> children = childrenFact(null).getFirst(); for(M0Def
		 * mod : children) { mod.onDependencyChanged(); }
		 */

//		System.err.println("M0Module: DEP CHANGED: " + this);
	}

	@Override
	public void onResourceChanged() {
		sourceSignature = null;
		fullSignature = null;
		depSignature = null;
		revision.incrementAndGet();

//		System.err.println("M0Module: RESOURCE CHANGED: " + this);
	}

	private Module treeFact(ITaskMonitor tm) {
		synchronized (treeFact) {
			Module tree = null;
			ISignature sig = sourceSignature;
			if (sig != null) {
				tree = treeFact.getValue(sig);
			}
			if (tree != null) {
				return tree;
			}

			String cs = String.valueOf(loadFile(tm));

			// parse it
			if (cs != null) {
				manager.clearMarks(MARK_SOURCE_PARSING, getURI());
				M0Parser parser = new M0Parser(cs);
				tree = parser.parseModule();
			}

			if (tree == null) {
				tree = new Module(new Token("Identifier", "", getFile(), 0), Arrays.asList(), "");
			}

			assert tree != null;
			assert sourceSignature != null;

			treeFact.setValue(tree, sourceSignature);

			return tree;
		}
	}

}
