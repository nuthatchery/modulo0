package modulo0.eclipse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.nuthatchery.pica.Pica;
import org.nuthatchery.pica.resources.eclipse.EclipseWorkspaceManager;

public class Modulo0Nature implements IProjectNature {

	public static final String NATURE_ID = "modulo0";
	public static final String BUILDER_ID = "modulo0_builder";
	private IProject project;

	@Override
	public void configure() throws CoreException {
		IProjectDescription desc = project.getDescription();
		for (ICommand cmd : desc.getBuildSpec()) {
			if (cmd.getBuilderName().equals(BUILDER_ID)) {
				return;
			}
		}
		/*
		 * TODO: enable when we have a builder ICommand[] cmds =
		 * desc.getBuildSpec(); cmds = Arrays.copyOf(cmds, cmds.length + 1);
		 * 
		 * ICommand cmd = desc.newCommand(); cmd.setBuilderName(BUILDER_ID);
		 * cmds[cmds.length - 1] = cmd; desc.setBuildSpec(cmds);
		 */
		project.setDescription(desc, null);
		((EclipseWorkspaceManager) Pica.getWorkspaceManager()).openProject(project);

		System.err.println("Magnolia nature configured for " + project.getName());
	}

	@Override
	public void deconfigure() throws CoreException {
		IProjectDescription desc = project.getDescription();
		ICommand[] cmds = desc.getBuildSpec();
		List<ICommand> list = new ArrayList<ICommand>(Arrays.asList(cmds));
		Iterator<ICommand> iter = list.iterator();
		while (iter.hasNext()) {
			ICommand cmd = iter.next();
			if (cmd.getBuilderName().equals(BUILDER_ID)) {
				iter.remove();
			}
		}
		desc.setBuildSpec(list.toArray(new ICommand[list.size()]));
		project.setDescription(desc, null);
		((EclipseWorkspaceManager) Pica.getWorkspaceManager()).closeProject(project);
	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public void setProject(IProject project) {
		this.project = project;
	}

}
