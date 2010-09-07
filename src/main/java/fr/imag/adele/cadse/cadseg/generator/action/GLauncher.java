/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * 
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.cadseg.generator.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import fede.workspace.tool.eclipse.MappingManager;
import fr.imag.adele.cadse.as.generator.GAction;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.cadseg.generator.GCadseGenerator;
import fr.imag.adele.cadse.cadseg.generator.template.LaunchApplicationTemplate;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.fede.workspace.si.view.View;

public class GLauncher extends GAction {

	
	@Override
	public void generate(GGenerator g, Item currentItem, GenContext cxt) throws CoreException {

		IProgressMonitor monitor = View.getDefaultMonitor();
		IProject p = g.getProject(GCadseGenerator.P_ECLIPSE, currentItem);
		

		IFile launchAppli = p.getFile("run-cadse-" + currentItem.getName() + ".launch");
		if (!launchAppli.exists()) {
			LaunchApplicationTemplate lat = new LaunchApplicationTemplate();
			MappingManager.generate(p, null, launchAppli.getName(), lat.generate(currentItem),
					monitor);
		}
		
	}
}
