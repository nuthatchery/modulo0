package modulo0.resources;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;

import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.jdt.annotation.Nullable;
import org.nuthatchery.pica.resources.ILanguage;
import org.nuthatchery.pica.resources.IXRefInfo;
import org.nuthatchery.pica.resources.handles.IFileHandle;
import org.nuthatchery.pica.resources.internal.AbstractManagedResource;
import org.nuthatchery.pica.resources.managed.IManagedCodeUnit;
import org.nuthatchery.pica.resources.managed.IManagedContainer;
import org.nuthatchery.pica.resources.regions.IOffsetLength;
import org.nuthatchery.pica.resources.storage.IStorage;
import org.nuthatchery.pica.tasks.ITaskMonitor;
import org.nuthatchery.pica.util.ISignature;
import org.nuthatchery.pica.util.Pair;

public class M0Def extends AbstractManagedResource implements IManagedContainer, IManagedCodeUnit {

	protected M0Def(IFileHandle resource) {
		super(resource.getURI(), resource);
	}


	@Override
	public long getLength() throws UnsupportedOperationException, IOException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public long getOffset() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Nullable
	public IManagedContainer getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCodeUnit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFragment() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onResourceChanged() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public URI getURI() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getKind(ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILanguage getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISignature getSourceSignature(ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Nullable
	public IStorage getStorage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends IManagedCodeUnit> getTransitiveDepends(
			ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Nullable
	public IXRefInfo getXRefs(IOffsetLength loc, ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Pair<IOffsetLength, Object>> getXRefs(ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasIncompleteDepends(ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDependencyChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Nullable
	public IManagedCodeUnit getChild(Object childId, ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends IManagedCodeUnit> getChildren(
			ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends IManagedCodeUnit> getDepends(
			ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISignature getFullSignature(ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isRoot() {
		return false;
	}
}
