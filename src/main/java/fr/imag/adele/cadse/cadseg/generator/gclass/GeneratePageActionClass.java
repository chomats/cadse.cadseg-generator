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

import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.PageManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class GeneratePageActionClass.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class GeneratePageActionClass extends GenerateClass<PageActionState> {
	
	public static final GToken PAGE_ACTION_CLASS = new GToken("pageaction");

	public GeneratePageActionClass() {
		super(PAGE_ACTION_CLASS);
	}

	@Override
	protected void init(PageActionState state, Item page, GGenerator g,
			GenContext cxt) {
		super.init(state, page, g, cxt);
		
		PageActionState pageState = (PageActionState) state;  
		pageState._className = GenerateJavaIdentifier.javaClassNamePageActionFromPage(cxt, page);
		pageState._packageName = GenerateJavaIdentifier.javaPackagePageFactoryFromPage(cxt, page);

		pageState._extendedPackageName = "fr.imag.adele.cadse.core.impl.ui";
		pageState._extendedClassName = "AbstractActionPage";

		Item cadseDefinition = PageManager.getCadseDefinition(page);

		IFile f = CadseDefinitionManager.getJavaFile(cadseDefinition, PAGE_ACTION_CLASS.getName(), pageState._packageName, pageState._className);
		pageState._type = CadseDefinitionManager.getJavaType(cadseDefinition, f, pageState._className);
		pageState._isClass = true;
	}

	@Override
	protected PageActionState createState() {
		return new PageActionState();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateClass(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	public void addImport(GenStringBuilder sb, Set<String> imports, GenContext context) {
		imports.add("fr.imag.adele.cadse.core.IItemNode");
		imports.add("fr.imag.adele.cadse.core.Item");
		imports.add("fr.imag.adele.cadse.core.ItemType");
		imports.add("fr.imag.adele.cadse.core.Link");
		imports.add("fr.imag.adele.cadse.core.LinkType");
		imports.add("fr.imag.adele.cadse.core.impl.ui.AbstractActionPage");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateConstructor(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	protected void generateConstructor(GenStringBuilder sb, Set<String> imports, GenContext context, PageState state) {
		sb.newline().append("public ").append(state.getClassName()).append(
				"(Link l, Item item, IItemNode node, ItemType type, LinkType lt) {");
		sb.newline().append("  //TODO");
		sb.newline().append("}");
		sb.newline();
		sb.newline().append("public ").append(state.getClassName()).append("() {");
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
	
	@Override
	public boolean match(GToken kind) {
		return kind.abs() == GCst.t_import || kind.abs() == GCst.t_constructor || super.match(kind);
	}
	
	
	@Override
	public void generatePartFile(GResult g, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		super.generatePartFile(g, currentItem, gf, kind, context, gGenerator, state);
		if (kind.abs() == GCst.t_import) 
			addImport(g, state.getImports(), context);
		else if (kind.abs() == GCst.t_constructor)
			generateConstructor(g, state.getImports(), context, (PageState) state);
	}
}
