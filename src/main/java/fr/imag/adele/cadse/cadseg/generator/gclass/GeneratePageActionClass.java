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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;

import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.PageManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.si.view.View;

/**
 * The Class GeneratePageActionClass.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class GeneratePageActionClass extends GenerateClass {

	/** The id. */
	String	id;

	/** The page. */
	Item	page;

	/**
	 * Generate.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param page
	 *            the page
	 */
	public static void generate(ContextVariable cxt, Item page) {
		GeneratePageActionClass ret;
		String cn = GenerateJavaIdentifier.javaClassNamePageActionFromPage(cxt, page);
		String pn = GenerateJavaIdentifier.javaPackagePageFactoryFromPage(cxt, page);

		String super_pn = "fr.imag.adele.cadse.core.impl.ui";
		String super_cn = "AbstractActionPage";

		Item cadseDefinition = PageManager.getCadseDefinition(page);

		IFile f = CadseDefinitionManager.getJavaFile(cadseDefinition, "pageaction", pn, cn);
		IType javatype = CadseDefinitionManager.getJavaType(cadseDefinition, f, cn);

		ret = new GeneratePageActionClass(cxt, pn, cn, super_pn + "." + super_cn, javatype);
		ret.id = page.getName();
		ret.page = page;

		String content = ret.getContent();
		try {
			EclipsePluginContentManger.generateJava(f, content, View.getDefaultMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Instantiates a new generate page action class.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param packageName
	 *            the package name
	 * @param className
	 *            the class name
	 * @param extendedClassName
	 *            the extended class name
	 * @param type
	 *            the type
	 */
	private GeneratePageActionClass(ContextVariable cxt, String packageName, String className,
			String extendedClassName, IType type) {
		super(cxt, true, packageName, className, extendedClassName, (String) null, type, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateClass(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	public void generateClass(GenStringBuilder sb, Set<String> imports, GenContext context) {
		imports.add("fr.imag.adele.cadse.core.IItemNode");
		imports.add("fr.imag.adele.cadse.core.Item");
		imports.add("fr.imag.adele.cadse.core.ItemType");
		imports.add("fr.imag.adele.cadse.core.Link");
		imports.add("fr.imag.adele.cadse.core.LinkType");
		imports.add("fr.imag.adele.cadse.core.impl.ui.AbstractActionPage");
		super.generateClass(sb, imports, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateConstructor(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	protected void generateConstructor(GenStringBuilder sb, Set<String> imports, GenContext context) {
		sb.newline().append("public ").append(getClassName()).append(
				"(Link l, Item item, IItemNode node, ItemType type, LinkType lt) {");
		sb.newline().append("  //TODO");
		sb.newline().append("}");
		sb.newline();
		sb.newline().append("public ").append(getClassName()).append("() {");
		sb.newline().append("  //TODO");
		sb.newline().append("}");
	}

	// public class X extends AbstractActionPage {
	//
	//
	// public X(Link l, Item item, IItemNode node, ItemType type, LinkType lt) {
	// }
	//
	//
	//
	// }
}
