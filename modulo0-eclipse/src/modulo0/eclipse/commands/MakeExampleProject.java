package modulo0.eclipse.commands;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.nuthatchery.pica.errors.CancelledException;
import org.nuthatchery.pica.tasks.ITaskMonitor;
import org.nuthatchery.pica.tasks.eclipse.EclipseTaskMonitor;

import io.usethesource.impulse.editor.UniversalEditor;
import io.usethesource.impulse.parser.IParseController;
import modulo0.eclipse.Modulo0Nature;
import modulo0.eclipse.projectgen.ProjectUtil;

public class MakeExampleProject extends AbstractCommand<Object> {

	public MakeExampleProject() {
		super("(Re)create Modulo0 Example Project");
	}

	@Override
	protected Object initData(String name, ExecutionEvent event, IParseController parseCtrl, UniversalEditor editor) {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("Modulo0Example");

		if (project.exists()) {
			boolean confirm = MessageDialog.openConfirm(Display.getDefault().getActiveShell(), name,
					"Project Modulo0Example already exists. Delete and recreate?");
			if (!confirm)
				throw new CancelledException("");
		}

		return null;
	}

	@Override
	protected void perform(Object data, ExecutionEvent event, IParseController parseCtrl, Point point,
			ITaskMonitor tm) {

		try {

			IProject project = ProjectUtil.createProject("Modulo0Example", true, Modulo0Nature.NATURE_ID);
			IFolder src = project.getFolder("src");
			src.create(true, true, EclipseTaskMonitor.makeProgressMonitor(tm, 10));

			String code = "module Test;\n\ndata Foo Bar FooFoo\n";
			IFile file = src.getFile("Test.m0");
			file.create(new ByteArrayInputStream(code.toString().getBytes("UTF-8")), true,
					EclipseTaskMonitor.makeProgressMonitor(tm, 10));
		} catch (CoreException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
