/**************************************************************************
 * Copyright (c) 2012-2014 Anya Helene Bagge
 * Copyright (c) 2012-2014 University of Bergen
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. See http://www.gnu.org/licenses/
 *
 *
 * See the file COPYRIGHT for more information.
 *
 * Contributors:
 * * Anya Helene Bagge
 *
 *************************************************************************/
package modulo0.eclipse.projectgen;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.nuthatchery.pica.Pica;
import org.nuthatchery.pica.errors.CancelledException;
import org.nuthatchery.pica.resources.IProjectManager;
import org.nuthatchery.pica.resources.eclipse.EclipseProjectManager;
import org.nuthatchery.pica.resources.managed.IManagedCodeUnit;
import org.nuthatchery.pica.tasks.ITaskMonitor;
import org.nuthatchery.pica.tasks.eclipse.EclipseTaskMonitor;
import org.nuthatchery.pica.util.Pair;
import org.nuthatchery.pica.util.depgraph.IDepGraph;
import org.nuthatchery.pica.util.depgraph.IWritableDepGraph;
import org.nuthatchery.pica.util.tests.DepGraphGenerator;

import modulo0.eclipse.Modulo0Nature;

public class ProjectGenerator {
	public static String pkgNameOf(IFile file) {
		return file.getName().substring(0, file.getName().lastIndexOf('.'));
	}

	private final NullProgressMonitor monitor = new NullProgressMonitor();
	private final List<Pair<String, Long>> timings = new ArrayList<>();

	private IProject project;

	private IFolder src;

	private final String PROJECT_NAME;

	public ProjectGenerator(String projectName) {
		this.PROJECT_NAME = projectName;
	}

	public void closeProject(ITaskMonitor tm) throws CoreException {
		if (project == null)
			throw new IllegalStateException("Project not intialised");

		IProjectManager manager = Pica.getResourceManager(project.getName());
		assert manager != null;

		System.err.print("Closing project...");
		long t0 = System.currentTimeMillis();
		project.close(EclipseTaskMonitor.makeProgressMonitor(tm, 10));
		System.err.println("done (" + (System.currentTimeMillis() - t0) + "ms)");
		timings.add(new Pair<>("closeProject", (System.currentTimeMillis() - t0)));
	}

	public IProject createProject() throws CoreException {
		project = ProjectUtil.createProject(PROJECT_NAME, true, Modulo0Nature.NATURE_ID);
		src = project.getFolder("src");
		src.create(true, true, monitor);

		return project;
	}

	public void deleteProject() throws CoreException {
		project.delete(true, monitor);
	}

	public void generateFiles(IDepGraph<IFile> graph) {
		if (project == null)
			throw new IllegalStateException("Project not intialised");

		long t0;
		t0 = System.currentTimeMillis();

		Job job = new WorkspaceJob("Creating files for project " + project.getName()) {
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				monitor.beginTask("Creating files", graph.getElements().size());
				for (IFile f : graph.topological()) {
					monitor.subTask("Creating " + f);
					Set<IFile> depends = graph.getDepends(f);
					StringBuilder code = new StringBuilder();
					code.append("module ");
					code.append(pkgNameOf(f));
					code.append(";\n");
					for (IFile d : depends) {
						code.append(" import ");
						code.append(pkgNameOf(d));
						code.append(";\n");
					}
					code.append("\n");
					// System.err.print(code.toString());
					try {
						f.create(new ByteArrayInputStream(code.toString().getBytes("UTF-8")), true, monitor);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					monitor.worked(1);
				}
				monitor.done();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
		try {
			job.join();
		} catch (InterruptedException e) {
			throw new CancelledException("Interrupted", e);
		}
		timings.add(new Pair<>("generateFiles", (System.currentTimeMillis() - t0)));
	}

	public void generateFiles(int nFiles, int nOps) {
		IDepGraph<IFile> graph = generateGraph(nFiles, nOps);
		generateFiles(graph);
	}

	public IDepGraph<IFile> generateGraph(int nFiles, int nOps) {
		if (src == null)
			throw new IllegalStateException("Project not intialised");

		long t0;

		List<IFile> files = new ArrayList<IFile>();
		for (int i = 0; i < nFiles; i++) {
			String name = "";
			int j = i;
			do {
				name += String.valueOf((char) ('A' + j % 26));
				j /= 26;
			} while (j != 0);
			files.add(src.getFile(name + ".m0"));
		}
		t0 = System.currentTimeMillis();
		IWritableDepGraph<IFile> graph = DepGraphGenerator.genDepGraph(files, nOps, true);

		timings.add(new Pair<>("generateGraph", (System.currentTimeMillis() - t0)));

		return graph;
	}
	public void getTopology(ITaskMonitor tm) {
		if (project == null)
			throw new IllegalStateException("Project not intialised");

		IProjectManager manager = Pica.getResourceManager(project.getName());
		assert manager != null;
		System.err.print("Getting topological order...");
		long t0 = System.currentTimeMillis();
		manager.getPackageDependencyGraph(tm).topological();
		System.err.println("done (" + (System.currentTimeMillis() - t0) + "ms)");
		timings.add(new Pair<>("topoSort", (System.currentTimeMillis() - t0)));

	}

	public IDepGraph<IManagedCodeUnit> getDepGraph(ITaskMonitor tm) {
		if (project == null)
			throw new IllegalStateException("Project not intialised");

		IProjectManager manager = Pica.getResourceManager(project.getName());
		assert manager != null;

		System.err.print("Getting graph...");
		long t0 = System.currentTimeMillis();
		IDepGraph<IManagedCodeUnit> depGraph = ((EclipseProjectManager) manager).getPackageDependencyGraph(tm);
		System.err.println("done (" + (System.currentTimeMillis() - t0) + "ms)");
		timings.add(new Pair<>("getDepGraph", (System.currentTimeMillis() - t0)));

		return depGraph;
	}

	public List<Pair<String, Long>> getTimings() {
		return new ArrayList<>(timings);
	}

	public void openProject(ITaskMonitor tm) throws CoreException {
		if (project == null)
			throw new IllegalStateException("Project not intialised");

		System.err.print("Opening project...");
		long t0 = System.currentTimeMillis();
		project.open(EclipseTaskMonitor.makeProgressMonitor(tm, 10));
		System.err.println("done (" + (System.currentTimeMillis() - t0) + "ms)");
		timings.add(new Pair<>("openProject", (System.currentTimeMillis() - t0)));
	}

	public void processChanges(ITaskMonitor tm) {
		if (project == null)
			throw new IllegalStateException("Project not intialised");

		IProjectManager manager = Pica.getResourceManager(project.getName());
		assert manager != null;

		System.err.print("Processing changes...");
		long t0 = System.currentTimeMillis();
		manager.processChanges(tm);
		System.err.println("done (" + (System.currentTimeMillis() - t0) + "ms)");
		timings.add(new Pair<>("processChanges", (System.currentTimeMillis() - t0)));

	}

}
