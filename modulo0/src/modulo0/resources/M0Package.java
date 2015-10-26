package modulo0.resources;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.jdt.annotation.Nullable;
import org.nuthatchery.pica.resources.handles.IFolderHandle;
import org.nuthatchery.pica.resources.internal.AbstractManagedResource;
import org.nuthatchery.pica.resources.managed.IManagedCodeUnit;
import org.nuthatchery.pica.resources.managed.IManagedContainer;
import org.nuthatchery.pica.tasks.ITaskMonitor;

public class M0Package extends AbstractManagedResource implements IManagedContainer {

	private M0Package parent;

	protected M0Package(IFolderHandle resource, M0Package parent) {
		super(resource.getURI(), resource);
		this.parent = parent;
	}

	@Override
	@Nullable
	public IManagedCodeUnit getChild(Object childId, ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends IManagedCodeUnit> getChildren(ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getLength() throws UnsupportedOperationException, IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getOffset() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	@Nullable
	public IManagedContainer getParent() {
		return parent;
	}

	@Override
	public boolean isCodeUnit() {
		return false;
	}

	@Override
	public boolean isFragment() {
		return false;
	}

	@Override
	public boolean isRoot() {
		return false;
	}

	@Override
	public void onResourceChanged() {

	}
}
