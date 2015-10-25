package modulo0.eclipse.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.nuthatchery.pica.Pica;
import org.nuthatchery.pica.errors.CancelledException;
import org.nuthatchery.pica.tasks.ITaskMonitor;
import org.nuthatchery.pica.tasks.eclipse.EclipseTaskMonitor;

import io.usethesource.impulse.editor.UniversalEditor;
import io.usethesource.impulse.parser.IParseController;

public abstract class AbstractCommand<Data> extends AbstractHandler {
	protected final String name;

	/**
	 * @param name
	 *            User-friendly name of this command
	 */
	protected AbstractCommand(String name) {
		super();
		this.name = name;
	}

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
		UniversalEditor editor = null;
		IParseController parseCtrl = null;
		Point point = null;
		if (editorPart instanceof UniversalEditor) {
			editor = (UniversalEditor) editorPart;
			parseCtrl = (IParseController) editor.getParseController();
			point = editor.getSelection();
		}
		Data data = null;
		try {
			data = initData(name, event, parseCtrl, editor);
		} catch (CancelledException e) {
			return null;
		}

		CmdJob job = new CmdJob(data, name, event, parseCtrl, point);
		job.schedule();

		return null;
	}

	/**
	 * Execute the command.
	 * 
	 * Runs as an Eclipse job, in a non-UI thread.
	 * 
	 * @param data
	 *            Arbitrary data
	 * @param event
	 *            The event that triggered this command
	 * @param parseCtrl
	 *            Parse controller for the currently active editor
	 * @param point
	 *            Currently selected point in the editor
	 * @param tm
	 *            A monitor
	 */
	protected abstract void perform(Data data, ExecutionEvent event, IParseController parseCtrl, Point point,
			ITaskMonitor tm);

	/**
	 * Set up arbitraty data to be passed to the command. Mainly useful for
	 * extra information that needs to be read from the UI thread. Can also be
	 * used to stop the command if there is no associated parsecontroller or
	 * editor (by throwing a CancelledException).
	 * 
	 * @param name
	 * @param event
	 * @param parseCtrl
	 * @param editor
	 * @return
	 * @throws CancelledException
	 *             if the command cannot proceed for some reason
	 */
	protected Data initData(String name, ExecutionEvent event, IParseController parseCtrl, UniversalEditor editor)
			throws CancelledException {
		return null;
	}

	class CmdJob extends Job {
		final ExecutionEvent event;
		final IParseController parseCtrl;
		final Point point;
		final Data data;

		CmdJob(Data data, String name, ExecutionEvent event, IParseController parseCtrl, Point p) {
			super(name);
			this.event = event;
			this.point = p;
			this.parseCtrl = parseCtrl;
			this.data = data;
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			monitor.beginTask(getName(), 1000);
			try {
				perform(data, event, parseCtrl, point, new EclipseTaskMonitor(SubMonitor.convert(monitor, 1000)));
			} catch(Exception e) {
				Pica.get().logException("Command " + getName() + " failed", e);
				return Status.CANCEL_STATUS;
			}
			finally {
		
				monitor.done();
			}
			return Status.OK_STATUS;
		}
	}
}
