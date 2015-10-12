package modulo0.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collection;

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
import org.nuthatchery.pica.tasks.ITaskMonitor;

public class EclipseModulo0Package implements IManagedPackage<String> {

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
	@Nullable
	public IManagedResource getParent() {
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
	public boolean isProject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onResourceChanged() {
		// TODO Auto-generated method stub
		
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
	public long getModificationStamp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public URI getURI() {
		// TODO Auto-generated method stub
		return null;
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
	public boolean isManaged() {
		// TODO Auto-generated method stub
		return false;
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
	public IXRefInfo getXRefs(ISourceLocation loc, ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Pair<ISourceLocation, String>> getXRefs(ITaskMonitor rm) {
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
	public IManagedCodeUnit<String> getChild(String childId, ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends IManagedCodeUnit<String>> getChildren(
			ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends IManagedCodeUnit<String>> getDepends(
			ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISignature getFullSignature(ITaskMonitor rm) {
		// TODO Auto-generated method stub
		return null;
	}
}
