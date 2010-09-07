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
package fr.imag.adele.cadse.cadseg.generator.gclass;

import org.eclipse.jdt.core.ICompilationUnit;

import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.cadseg.contents.ManagerJavaFileContentManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.iter.ItemPartIterable;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class GenerateManager.
 * 
 * @author chomats
 */

// package <%=cim.packageName %>;
//
//
// <%
// cim.importsArray.add("fr.imag.adele.cadse.core.ItemType");
// for(String i : cim.importsArray) { %>
// import <%=i%>;
// <%}%>
//
// /**
// @generated
// */
// public class <%=cim.className%> extends <%=cim.superClassName%> {
//
//
//
//
// <% }
// %>
// /**
// @generated
// */
// public <%=cim.className%>() {
// }
//
//
//
// <%=cim.attributes_str%>
// <%=cim.methods_str%>
// }

public class GenerateManager extends GenerateClass<GenManagerState> {
	
	public static final GToken MANAGER = new GToken("manager");
	
	static public class ManagerIter extends ItemPartIterable {

		@Override
		public void beginAll(Item currentItem,
				ContextVariable context) {
			super.beginAll(currentItem, context);
			Item itemtype = ManagerManager.getItemType(currentItem);
			if (itemtype != null)
				_nexts.add(itemtype);
		}
	}

	public GenerateManager() {
		super(MANAGER);
	}
	

	@Override
	protected void init(GenManagerState state, Item manager, GGenerator g, GenContext cxt) {
		GenManagerState cm = (GenManagerState) state;
		ManagerJavaFileContentManager jf = (ManagerJavaFileContentManager) g.getJavaFileContentManager(null, manager);
		Item cadseDefinition = ManagerManager._getCadseDefinition(manager);
		if (jf == null) {
			return;
		}
		if (jf.getPartParent() == null) {
			// reconnect content...
			jf.setParentContent(cadseDefinition.getContentItem());
		}
		ICompilationUnit cu = jf.getCompilationUnit(cxt);
		if (cu == null) {
//			Activator.getDefault().log(
//					new Status(IStatus.ERROR, Activator.PLUGIN_ID,
//							"Cannot find cu of " + getFile(cxt) + " for "
//									+ getOwnerItem().getQualifiedName()));
			return;

		}
		cm.itemtype = ManagerManager.getItemType(manager);
		if (cm.itemtype == null) {
			return;
		}
		cm.manager = manager;
		cm.itemName = cm.itemtype.getName();
		cm._className = jf.getClassName(cxt);
		cm._packageName = jf.getPackageName(cxt);
		cm._type = cu.getType(cm._className);

		ItemType superItem = (ItemType) ItemTypeManager
				.getSuperType(cm.itemtype);
		if (superItem != null) {
			cm.superClassName = ItemTypeManager.getManagerClass(superItem, cxt,
					null);
			cm.overwriteClass = true;
		} else if (ItemTypeManager.isIsMetaItemTypeAttribute(cm.itemtype)) {
			cm.superClassName = "fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager";
			cm.overwriteClass = false;
		} else {
			cm.superClassName = "fr.imag.adele.cadse.core.DefaultItemManager";
			cm.overwriteClass = true;
		}
		if (cm.superClassName != null) {
			String[] packageAndName = JavaIdentifier.getPackageAndClassName(cm.superClassName);
			cm._extendedPackageName = packageAndName[0];
			cm._extendedClassName = packageAndName[1];
		}	
		cm.cm = (ManagerJavaFileContentManager) jf;
	}
	
	@Override
	protected GenManagerState createState() {
		return new GenManagerState();
	}

}
