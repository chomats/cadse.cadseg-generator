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
 */

package fr.imag.adele.cadse.cadseg.generator.gclass;

import org.eclipse.jdt.core.ICompilationUnit;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GIterPart;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenClassState;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerJavaFileContentManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;

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

public class GenerateManager extends GenerateClass {
	
	public static final GToken MANAGER = new GToken("manager");
	
	static public class ManagerIter extends GIterPart {

		@Override
		public void beginAll(Item currentItem, GGenFile gf, GToken kind,
				GenContext context, GGenerator gGenerator) {
			super.beginAll(currentItem, gf, kind, context, gGenerator);
			Item itemtype = ManagerManager.getItemType(currentItem);
			if (itemtype != null)
				stack.add(itemtype);
		}
	}

	/**
	 * The Class GenerateModel.
	 */
	static public class GenManagerState extends GenClassState {

		/** The super class name. */
		public String superClassName;

		/** The item name. */
		public String itemName;

		/** The itemtype. */
		public Item itemtype;

		/** The manager. */
		public Item manager;

		/** The cm. */
		public ManagerJavaFileContentManager cm;

		public boolean overwriteClass;

		public Item getCadseDefinition() {
			return ItemTypeManager.getCadseDefinition(itemtype);
		}
	}
	
	public GenerateManager() {
		super(MANAGER);
	}
	

	@Override
	protected void init(GenState state, Item manager, GGenerator g, GenContext cxt) {
		GenManagerState cm = (GenManagerState) state;
		ManagerJavaFileContentManager jf = (ManagerJavaFileContentManager) g.getJavaFileContentManager(null, manager);
		Item cadseDefinition = ManagerManager._getCadseDefinition(manager);
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
		cm.fClassName = jf.getClassName(cxt);
		cm._packageName = jf.getPackageName(cxt);
		cm.type = cu.getType(cm.fClassName);

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
		cm.cm = (ManagerJavaFileContentManager) jf;
	}
	
	@Override
	protected GenState createState() {
		return new GenManagerState();
	}
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fr.imag.adele.cadse.core.IGenerateContent#generate(fr.imag.adele.
//	 * cadse.core.var.ContextVariable)
//	 */
//	public void generate(JavaFileContentManager jf, ContextVariable cxt) {
//		Item manager = jf.getOwnerItem();
//
//		
//
//
//		
//		// ((IGenerateContent)
//		// _getCadseDefinition(manager).getContentItem()).generate(cxt);
//
//		String path = jf.getPath(cxt);
//		try {
//			EclipsePluginContentManger.generateJava(MelusineProjectManager
//					.getProject(cadseDefinition).getFile(new Path(path)),
//					getContent(), View.getDefaultMonitor());
//
//		} catch (CoreException e) {
//			e.printStackTrace();
//		}
//
//	}

}
