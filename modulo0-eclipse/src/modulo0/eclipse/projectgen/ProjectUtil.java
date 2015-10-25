/**************************************************************************
 * Copyright (c) 2012-2013 Anya Helene Bagge
 * Copyright (c) 2012-2013 University of Bergen
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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import modulo0.eclipse.Activator;

public class ProjectUtil {

	public static IProject createProject(String projectName, boolean deleteIfExists, String... natures) throws CoreException  {
		Activator.getOrStartInstance();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject project = workspace.getRoot().getProject(projectName);

		if(project.exists()) {
			if(deleteIfExists) {
				try {
					
					project.delete(IResource.FORCE|IResource.ALWAYS_DELETE_PROJECT_CONTENT, null);
				}
				catch(CoreException e) {
					e.printStackTrace();
				}
			}
			else {
				return project;
			}
		}
		IProjectDescription desc = workspace.newProjectDescription(projectName);
		desc.setNatureIds(natures);
		project.create(desc, null);
		project.open(null);
		return project;
	}

}
