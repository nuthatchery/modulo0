package modulo0.eclipse.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Point;
import org.nuthatchery.pica.tasks.ITaskMonitor;
import org.nuthatchery.pica.util.Pair;

import io.usethesource.impulse.parser.IParseController;
import modulo0.eclipse.projectgen.ProjectGenerator;

public class RunBenchmarks extends AbstractCommand<Object> {

	public RunBenchmarks() {
		super("Run Benchmarks");
	}

	@Override
	protected void perform(Object data, ExecutionEvent event, IParseController parseCtrl, Point point,
			ITaskMonitor tm) {
		System.out.println("data: " + data);
		System.out.println("event: " + event);
		System.out.println("parseCtrl: " + parseCtrl);
		System.out.println("point: " + point);

		System.out.println("Benchmarks!!!");
		
		String projectName = "_Modulo0Benchmarks";
		
		ProjectGenerator projGen = new ProjectGenerator(projectName);
		try {
			projGen.createProject();
			projGen.generateFiles(5000, 20000);
			projGen.processChanges(tm);
			projGen.closeProject(tm);
		projGen.openProject(tm);
			projGen.processChanges(tm);
			
			for(Pair<String, Long> p : projGen.getTimings())
				System.out.println(p.getFirst() + ": " + p.getSecond());
			
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
