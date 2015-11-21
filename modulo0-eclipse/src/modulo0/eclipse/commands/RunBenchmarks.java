package modulo0.eclipse.commands;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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

		ProjectGenerator projGen = null;
		try (PrintWriter out = new PrintWriter("/tmp/benchmark.csv")) {
			Map<String, Long> resultMap = new HashMap<>();
			int count = 0;
			int nFiles = 30000;
			int ops = 20000;
			try {
				for (int i = 0; i < 15; i++) {
					projGen = new ProjectGenerator(projectName + i);
					projGen.createProject();
					sleep();
					projGen.generateFiles(nFiles, ops);
					sleep();
					projGen.processChanges(tm);
					sleep();
					projGen.closeProject(tm);
					sleep();
					projGen.openProject(tm);
					sleep();
					projGen.processChanges(tm);
					sleep();
					projGen.getTopology(tm);
					sleep();
					projGen.deleteProject();
					sleep();
					if(i == 0) {
						String sep = "";
						for (Pair<String, Long> p : projGen.getTimings()) {
							out.print(sep + p.getFirst());
							sep = ";";
						}
						out.println();

					}
					String sep = "";
					for (Pair<String, Long> p : projGen.getTimings()) {
						String name = p.getFirst();
						resultMap.put(name, p.getSecond()+resultMap.getOrDefault(name, 0L));
						out.print(sep + p.getSecond());
						sep = ";";
					}
					out.println();
					out.flush();
					count++;

				}

				for (Pair<String, Long> p : projGen.getTimings())
					System.out.println(p.getFirst() + ": " + p.getSecond());

			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				System.out.println("Summary of Benchmark:");
				System.out.println("Number of iterations: " + count);
				System.out.println("Number of files: " + nFiles);
				System.out.println("Number of operations: " + ops);
				for(String k : resultMap.keySet()) {
					System.out.println("  Avg. time to " + k + ": " + resultMap.get(k)/count);
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
