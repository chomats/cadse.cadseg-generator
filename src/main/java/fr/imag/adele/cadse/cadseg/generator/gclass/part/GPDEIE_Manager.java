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
package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.Item;

public class GPDEIE_Manager extends IPDEContributor {
	

	/*
	 * (non-Javadoc)
	 * 
	 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
	 * computeImportsPackage(java.util.Set)
	 */
	public void computeImportsPackage(Item owner, Set<String> imports) {
		imports.add("fede.workspace.model.manager");
		imports.add("org.eclipse.core.resources");
		imports.add("fede.workspace.tool.eclipse");
		imports.add("fede.workspace.tool.eclipse");
		imports.add("org.eclipse.core.runtime.jobs");
		imports.add("fr.imag.adele.cadse.cadseg.managers.dataModel");
		Item itemtype = ManagerManager.getItemType(owner);
		if (ItemTypeManager.isMetaItemTypeH(itemtype)) {
			imports.add("fr.imag.adele.cadse.cadseg.pages.dataModel");
		}
	}
}
