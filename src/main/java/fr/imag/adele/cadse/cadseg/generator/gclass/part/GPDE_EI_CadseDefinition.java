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

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.internal.core.plugin.PluginElement;
import org.eclipse.pde.internal.core.plugin.PluginExtension;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.view.model.ViewCategoryModel;
import fr.imag.adele.cadse.cadseg.managers.view.model.ViewModel;
import fr.imag.adele.cadse.cadseg.managers.view.model.ViewModels;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class GPDE_EI_CadseDefinition extends IPDEContributor {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage
	 * (java.util.Set)
	 */
	public void computeExportsPackage(Item item, Set<String> exports) {
		exports.add(getDefaultPackage(item));
		Item[] typeDefs = ItemTypeManager.getAllAllItemType(item, null, false);
		for (int i = 0; i < typeDefs.length; i++) {
			if (!addExport(typeDefs[i], exports))
				addExport(ItemTypeManager.getManager(typeDefs[i]), exports);
		}
	}
	
	protected boolean addExport(Item item, Set<String> exports) {
		if (item == null) return false;
		ContentItem o = item.getContentItem();
		if (o instanceof JavaFileContentManager) {
			exports.add(((JavaFileContentManager)o).getPackageName(ContextVariableImpl.DEFAULT));
			return true;
		}
		else return false;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage
	 * (java.util.Set)
	 */
	public void computeImportsPackage(Item item, Set<String> imports) {
		List<String> importsList = CadseDefinitionManager.getImports(item);
		if (importsList != null) {
			imports.addAll(importsList);
		}
		imports.add("fr.imag.adele.cadse.core");
		imports.add("fr.imag.adele.cadse.core.attribute");
		imports.add("fr.imag.adele.cadse.core");
		imports.add("fr.imag.adele.cadse.core.ui");
		imports.add("fr.imag.adele.cadse.core.ui.view");
		imports.add("fr.imag.adele.cadse.core.util");
		imports.add("fr.imag.adele.cadse.objectadapter");
	}
	
	protected String getDefaultPackage(Item item) {
		return CadseDefinitionManager.getDefaultPackage(ContextVariableImpl.DEFAULT, item);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion
	 * (org.eclipse.pde.core.plugin.IPluginBase,
	 * org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
	 */
	public void computeExtenstion(Item currentItem, IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
		try {
			IPluginExtension[] exts = pluginBase.getExtensions();

			IPluginExtension findExt = findExtension(pluginBase, exts, "org.eclipse.ui.views");

			if (findExt != null) {
				pluginBase.remove(findExt);
			}
			PluginExtension pluginExtension = new PluginExtension();
			pluginExtension.setModel(workspacePluginModel);
			pluginBase.add(pluginExtension);
			pluginExtension.setPoint("org.eclipse.ui.views");
			findExt = pluginExtension;

			ViewModels viewmodels = new ViewModels(CadseDefinitionManager.getViewModel(currentItem));
			for (ViewCategoryModel vc : viewmodels.categories) {
				PluginElement categoryElt = new PluginElement();
				categoryElt.setModel(workspacePluginModel);
				findExt.add(categoryElt);
				categoryElt.setName("category");
				categoryElt.setAttribute("name", vc.name);
				categoryElt.setAttribute("id", vc.id);

			}
			for (ViewModel v : viewmodels.views) {
				PluginElement categoryElt = new PluginElement();
				categoryElt.setModel(workspacePluginModel);
				findExt.add(categoryElt);
				categoryElt.setName("view");
				if (v.icon != null) {
					categoryElt.setAttribute("icon", v.icon);
				}
				if (v.category != null) {
					categoryElt.setAttribute("category", v.category);
				}

				categoryElt.setAttribute("class", v.qualifiedClassName);
				categoryElt.setAttribute("id", v.id);
				categoryElt.setAttribute("name", v.name);

			}

		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
