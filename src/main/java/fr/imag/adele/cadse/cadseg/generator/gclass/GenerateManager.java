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

import java.util.Set;

import org.eclipse.jdt.core.IType;

import fr.imag.adele.cadse.cadseg.ParseTemplate;
import fr.imag.adele.cadse.cadseg.exp.ParseException;
import fr.imag.adele.cadse.cadseg.exp.TokenMgrError;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.action.GAManagerFile;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
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
public class GenerateManager extends GenerateClass {

	/** The cim. */
	GAManagerFile.GenerateModel	cim;

	/**
	 * Instantiates a new generate manager.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param cim
	 *            the cim
	 * @param type
	 *            the type
	 */
	public GenerateManager(ContextVariable cxt, GAManagerFile.GenerateModel cim, IType type) {
		super(cxt, true, cim.packageName, cim.className, cim.superClassName, (String) null, type, cim.overwriteClass);
		this.cim = cim;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateMethods(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	protected void generateMethods(GenStringBuilder sb, Set<String> imports, GenContext context) {
		imports.add("fr.imag.adele.cadse.core.ItemType");
		cim.cm.generateParts(sb, "manager", "constructors", imports, context);
		internalGenerateMethod(sb, context, imports);
		cim.cm.generateParts(sb, "manager", "methods", imports, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateAttributes(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	protected void generateAttributes(GenStringBuilder sb, Set<String> imports, GenContext context) {
		super.generateAttributes(sb, imports, context);
		GenerateJavaIdentifier.addImportCST(cxt, ItemTypeManager.getCadseDefinition(cim.itemtype), imports);
		cim.cm.generateParts(sb, "manager", "inner-class", imports, context);
		cim.cm.generateParts(sb, "manager", "cstes", imports, context);
		cim.cm.generateParts(sb, "manager", "attributes", imports, context);

	}

	/**
	 * Internal generate method.
	 * 
	 * @param sb
	 *            the sb
	 * @param context
	 *            the context
	 * @param imports
	 *            the imports
	 */
	private void internalGenerateMethod(GenStringBuilder sb, GenContext context, Set<String> imports) {
		Item manager = cim.manager;
		String uniqueNameTemplate = ManagerManager.getUniqueNameTemplate(manager);
		if (uniqueNameTemplate != null && uniqueNameTemplate.length() != 0) {
			imports.add("fr.imag.adele.cadse.core.LinkType");
			imports.add("fr.imag.adele.cadse.core.Item");
			sb.newline();
			sb.newline().append("/**");
			sb.newline().append("	@generated");
			sb.newline().append("*/");
			sb.newline().append("@Override");
			sb.newline().append("public String computeQualifiedName(Item item, String name, Item parent, LinkType lt) {");
			sb.begin();
			Item itemtype = cim.itemtype;
			ParseTemplate pt = new ParseTemplate(itemtype, uniqueNameTemplate, "name");
			try {
				pt.main();
				pt.build(sb, imports, true, getPackageName());
			} catch (ParseException e) {
				sb.newline().append("//").append(e.getMessage());
			} catch (TokenMgrError e) {
				sb.newline().append("//").append(e.getMessage());
			}
			sb.end();
			sb.newline().append("}");
		}

		String displayTemplate = ManagerManager.getDisplayNameTemplateAttribute(manager);
		if (displayTemplate != null && displayTemplate.length() != 0) {
			sb.newline();
			imports.add("fr.imag.adele.cadse.core.Item");
			sb.newline().append("/**");
			sb.newline().append("	@generated");
			sb.newline().append("*/");
			sb.newline().append("@Override");
			sb.newline().append("public String getDisplayName(Item item) {");
			sb.begin();
			Item itemtype = cim.itemtype;
			ParseTemplate pt = new ParseTemplate(itemtype, displayTemplate, null);
			try {
				pt.main();
				pt.build("item", "sb", sb, imports, null, true, getPackageName());
			} catch (ParseException e) {
				sb.newline().append("//").append(e.getMessage());
			} catch (TokenMgrError e) {
				sb.newline().append("//").append(e.getMessage());
			}
			sb.end();
			sb.newline().append("}");
		}
	}

	@Override
	protected Item getCadseDefinition() {
		return cim.getCadseDefinition();
	}

}
