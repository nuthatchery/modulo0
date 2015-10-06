package modulo0.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.imp.pdb.facts.IAnnotatable;
import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IWithKeywordParameters;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.visitors.IValueVisitor;
import org.eclipse.jdt.annotation.Nullable;
import org.nuthatchery.pica.errors.Severity;
import org.nuthatchery.pica.resources.ILanguage;
import org.nuthatchery.pica.resources.managed.IManagedCodeUnit;
import org.nuthatchery.pica.resources.managed.IManagedFile;
import org.nuthatchery.pica.resources.managed.IManagedPackage;
import org.nuthatchery.pica.resources.managed.IManagedResource;
import org.nuthatchery.pica.resources.IProjectManager;
import org.nuthatchery.pica.resources.IWorkspaceManager;
import org.nuthatchery.pica.resources.IXRefInfo;
import org.nuthatchery.pica.resources.storage.IStorage;
import org.nuthatchery.pica.util.ISignature;
import org.nuthatchery.pica.util.Pair;
import org.rascalmpl.debug.IRascalMonitor;

public class EclipseModulo0Package implements IManagedPackage {

	public EclipseModulo0Package(IProjectManager manager, IManagedFile resource,
			IStorage storage, IConstructor id, ILanguage lang) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public long getModificationStamp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Nullable
	public IManagedResource getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getURI() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCodeUnit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isContainer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onResourceChanged() {
		// TODO Auto-generated method stub

	}

	@Override
	@Nullable
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Nullable
	public <T, E extends Throwable> T accept(	@Nullable IValueVisitor<T, E> v) throws E {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEqual(@Nullable IValue other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAnnotatable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Nullable
	public IAnnotatable<? extends IValue> asAnnotatable() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IConstructor getAST(IRascalMonitor rm) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}


	@Override
	public IConstructor getId() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public String getKind(IRascalMonitor rm) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public ILanguage getLanguage() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<? extends IManagedCodeUnit> getTransitiveDepends(
			IRascalMonitor rm) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Pair<ISourceLocation, IConstructor>> getXRefs(
			IRascalMonitor rm) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public IXRefInfo getXRefs(ISourceLocation loc, IRascalMonitor rm) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void onDependencyChanged() {
		// TODO Auto-generated method stub

	}

	@Override
	public IManagedCodeUnit getChild(IConstructor childId, IRascalMonitor rm) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<? extends IManagedCodeUnit> getChildren(IRascalMonitor rm) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<? extends IManagedPackage> getDepends(IRascalMonitor rm) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public ISignature getFullSignature(IRascalMonitor rm) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public ISignature getSourceSignature(IRascalMonitor rm) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public IStorage getStorage() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public IManagedResource getContainingFile()
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLength() throws UnsupportedOperationException, IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public URI getLogicalURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOffset() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isFragment() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IManagedResource asManagedResource()
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isManaged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mayHaveKeywordParameters() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IWithKeywordParameters<? extends IValue> asWithKeywordParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasIncompleteDepends(IRascalMonitor rm) {
		// TODO Auto-generated method stub
		return false;
	}

}
