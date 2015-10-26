package modulo0.eclipse.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nuthatchery.pica.resources.managed.IManagedCodeUnit;
import org.nuthatchery.pica.tasks.NullTaskMonitor;
import org.nuthatchery.pica.util.depgraph.IDepGraph;

import modulo0.eclipse.Activator;
import modulo0.eclipse.projectgen.ProjectGenerator;

public class DepGraphTest {
	private static final String PROJECT_NAME = "GenerateProjecTest";

	public static void assertSameGraph(IDepGraph<IFile> fileGraph, IDepGraph<IManagedCodeUnit> depGraph) {
		List<IFile> fileElements = new ArrayList<IFile>(fileGraph.getElements());
		List<IManagedCodeUnit> pkgElements = new ArrayList<IManagedCodeUnit>(depGraph.getElements());
		assertEquals(fileElements.size(), pkgElements.size());

		Collections.sort(fileElements, new Comparator<IFile>() {
			@Override
			public int compare(IFile arg0, IFile arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
		Collections.sort(pkgElements, new Comparator<IManagedCodeUnit>() {
			@Override
			public int compare(IManagedCodeUnit o1, IManagedCodeUnit o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		HashMap<IFile, IManagedCodeUnit> map = new HashMap<>();

		for (int i = 0; i < fileElements.size(); i++) {
			assertSamePackage(fileElements.get(i), pkgElements.get(i));
			map.put(fileElements.get(i), pkgElements.get(i));
		}

		for (IFile f : fileElements) {
			Set<IFile> fileDepends = fileGraph.getDepends(f);
			Set<IManagedCodeUnit> pkgDepends = depGraph.getDepends(map.get(f));
			System.err.print(f.getName() + " ");
			assertEquals(fileDepends.size(), pkgDepends.size());
			for (IFile g : fileDepends) {
				assertTrue(pkgDepends.contains(map.get(g)));
			}
		}
		System.err.println();
	}

	public static void assertSamePackage(IFile file, IManagedCodeUnit iManagedCodeUnit) {
		String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
		assertEquals(name, iManagedCodeUnit.getName());
	}

	public static boolean isSamePackage(IFile file, IManagedCodeUnit pkg) {
		return pkgNameOf(file).equals(pkg.getName());
	}

	public static String pkgNameOf(IFile file) {
		return file.getName().substring(0, file.getName().lastIndexOf('.'));
	}

	private ProjectGenerator projGen;

	@Before
	public void setUp() throws Exception {
		Activator.getOrStartInstance();

		projGen = new ProjectGenerator(PROJECT_NAME);
		projGen.createProject();
	}

	@After
	public void tearDown() throws Exception {
		projGen.deleteProject();
	}

	@Test
	public final void test() throws UnsupportedEncodingException, CoreException, InterruptedException {

		IDepGraph<IFile> graph = projGen.generateGraph(5000, 20000);
		projGen.generateFiles(graph);
		projGen.processChanges(new NullTaskMonitor());
		IDepGraph<IManagedCodeUnit> depGraph = projGen.getDepGraph(new NullTaskMonitor());
		System.err.println("STARTING COMPARISON");
		assertSameGraph(graph, depGraph);
		System.err.println("OK!");

	}
}
