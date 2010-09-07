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

import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;

import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.PageManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldGenerateInfo;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.ConfigurablePageFactory;

/**
 * The Class GeneratePageClass.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class GeneratePageClass2 extends GenerateClass<PageState> {
	public static final GToken PAGE_CLASS = new GToken("page");




	public GeneratePageClass2() {
		super(PAGE_CLASS);
	}

	static public Pattern					p_to_heritage		= Pattern
																		.compile(
																				"(protected\\s+void\\s+registerListener\\(\\)\\s*\\{)"
																						+ "\\s*//\\s*(super\\.registerListener\\(\\);)",
																				Pattern.MULTILINE);

	static public String					r_to_heritage		= "$1\n\t\t$2";

	static public Pattern					p_to_not_heritage	= Pattern
																		.compile(
																				"(protected\\s+void\\s+registerListener\\(\\)\\s*\\{)"
																						+ "\\s*(super\\.registerListener\\(\\);)",
																				Pattern.MULTILINE);

	static public String					r_to_not_heritage	= "$1\n\t\t// $2";

	


//	/**
//	 * Instantiates a new generate page class.
//	 * 
//	 * @param cxt
//	 *            the cxt
//	 * @param packageName
//	 *            the package name
//	 * @param className
//	 *            the class name
//	 * @param extendedClassName
//	 *            the extended class name
//	 * @param type
//	 *            the type
//	 * @param supercm
//	 * @param superPage
//	 */
//	private GeneratePageClass2(ContextVariable cxt, String packageName, String className, String extendedClassName,
//			IType type, Item superPage, PageContentManager supercm) {
//		super(cxt, true, packageName, className, extendedClassName, (String) null, type, false);
//		this.superPage = superPage;
//		this.supercm = supercm;
//	}
	
	@Override
	protected void init(PageState state, Item page, GGenerator g,
			GenContext cxt) {
		super.init(state, page, g, cxt);
		
		GeneratePageClass2 ret;
		
		state._extendedPackageName = "fr.imag.adele.cadse.core.impl.ui";
		state._extendedClassName = "PageImpl";

		Item cadseDefinition = PageManager.getCadseDefinition(page);

//		Item superPage = PageManager.getSuperPage(page);
//		PageContentManager supercm = null;
//		if (superPage != null) {
//			supercm = (PageContentManager) superPage.getContentItem();
//			if (supercm != null) {
//				super_pn = supercm.getPackageName(cxt);
//				super_cn = supercm.getClassName(cxt);
//			} else {
//				superPage = null;
//			}
//		}

		IFile f = CadseDefinitionManager.getJavaFile(cadseDefinition, "page", state.getPackageName(), state.getClassName());
		state._type = CadseDefinitionManager.getJavaType(cadseDefinition, f, state.getClassName());
		state._isClass = true;
		
		state.id = page.getName();
		state.page = page;
		///ret.addInternalShortName = PageManager.addInternalShortName(page);
	///	ret.addInternalAttribute = PageManager.addInternalAttribute(page);

		//state.fields = PageManager.getFieldGenerateInfos(cxt, page, state.getImports(), superPage);
		//state.heritage = superPage != null;
		state.cas = PageManager.isModificationPage(page) ? ConfigurablePageFactory.PAGE_PROPERTY_ITEM
				: ConfigurablePageFactory.PAGE_CREATION_ITEM;

		
	}
	
	@Override
	protected PageState createState() {
		return new PageState();
	}

	protected void generateAttributes(GenStringBuilder sb, Set<String> imports, GenContext context, PageState state) {
		imports.add("fr.imag.adele.cadse.core.IItemNode");
		imports.add("fr.imag.adele.cadse.core.Item");
		imports.add("fr.imag.adele.cadse.core.ItemType");
		imports.add("fr.imag.adele.cadse.core.Link");
		imports.add("fr.imag.adele.cadse.core.LinkType");
		imports.add("fr.imag.adele.cadse.core.ui.PageFactory");
		imports.add("fr.imag.adele.cadse.core.ui.IPage");
		imports.add("fr.imag.adele.cadse.core.impl.ui.PageImpl");
		imports.add("fr.imag.adele.cadse.core.ui.IActionPage");

		
		if (!state.heritage) {
			if (state.cas == ConfigurablePageFactory.PAGE_CREATION_ITEM) {

				sb.newline().appendGeneratedTag();
				sb.newline().append("public Item parent;");
				sb.newline().appendGeneratedTag();
				sb.newline().append("public ItemType it;");
				sb.newline().appendGeneratedTag();
				sb.newline().append("public LinkType lt;");
				// , ,
			} else if (state.cas == ConfigurablePageFactory.PAGE_PROPERTY_ITEM) {
				sb.newline().appendGeneratedTag();
				sb.newline().append("public Item item;");
			}
		}
		// declaration des champ : un par field graphique.
		for (FieldGenerateInfo info : state.fields) {
			if (info.superField != null) {
				continue;
			}
			sb.newline().appendGeneratedTag();
			sb.newline().append("protected ").append(info.uiTypeName).append(" ").append(info.fieldName).append(";");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateConstructor(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	protected void generateConstructor(GenStringBuilder sb, Set<String> imports, GenContext context, PageState state) {
		// declaration du constructeur
		sb.newline().appendGeneratedTag();
		sb.newline().append("protected ").append(state.getClassName());
		sb.append(" (String id, String label, String title, String description, boolean isPageComplete, int hspan) {");
		sb.newline().append("	super(id,label, title, description, isPageComplete, hspan);");
		sb.newline().append("}");

		sb.newline().appendGeneratedTag();
		sb.newline().append("public ").append(state.getClassName()).append(" (");
		if (state.cas == ConfigurablePageFactory.PAGE_CREATION_ITEM) {
			sb.append("Item parent, ItemType it, LinkType lt");
		} else if (state.cas == ConfigurablePageFactory.PAGE_PROPERTY_ITEM) {
			sb.append("Item item");
		}
		sb.append(") {");
		sb.begin();
		generateConstructPage(sb, imports, context, state);
		sb.end();
		sb.newline().append("}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateMethods(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	protected void generateMethods(GenStringBuilder sb, Set<String> imports, GenContext context, PageState state) {

		// method registerListener
		sb.newline();
		sb.newline().append("protected void registerListener() {");
		if (state.heritage) {
			sb.newline().append("  super.registerListener();");
		}
		sb.newline().append("// add init and register");
		sb.newline().append("}");

		// methods des champs

		
		for (FieldGenerateInfo info : state.fields) {
			if (info.superField != null) {
				continue;
			}
			FieldManager.generateMethod(info, sb, imports);
		}
	}

	/**
	 * Generate create page.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	private void generateConstructPage(GenStringBuilder sb, Set<String> imports, GenContext context, PageState state) {

		// call le super
		String key = PageManager.getKey(state.page);
		String title = PageManager.getTitle(state.page);
		String description = PageManager.getDesciption(state.page);
		String label = title;
		String isComplete = "false";

		if (description == null || description.equals("\"\"")) {
			description = "";
			PageManager.setDescriptionAttribute(state.page, "");
		}

		sb.newline().append("super(");
		sb.begin();
		sb.newline().appendStringValue_vir(key);
		sb.newline().appendStringValue_vir(label);
		sb.newline().appendStringValue_vir(title);
		sb.newline().appendStringValue_vir(description);
		sb.newline().append(isComplete).append(",");
		sb.newline().append(-1).append(");");
		sb.end();

		if (state.cas == ConfigurablePageFactory.PAGE_CREATION_ITEM) {

			sb.newline().append("this.parent = parent;");
			sb.newline().append("this.it = it;");
			sb.newline().append("this.lt = lt;");
			// , ,
		} else if (state.cas == ConfigurablePageFactory.PAGE_PROPERTY_ITEM) {
			sb.newline().append("this.item =  item;");
		}

		/* initialise les fields */
		for (FieldGenerateInfo info : state.fields) {
			sb.newline().append("this.").append(info.fieldName).append("=").append(" ").append(info.methodName).append(
					"();");
			boolean isReadOnly = FieldManager.isEditableAttribute(info.field);
			if (isReadOnly) {
				sb.newline().append("this.").append(info.fieldName).append(".setEditable(false);");
			}
		}
		// ajoute l'action
		generateCreateAction(sb, imports, context);

		// ajoute les fields;
		sb.newline().append("addLast(");
		sb.begin();
		// init field
		for (FieldGenerateInfo info : state.fields) {
			sb.newline().append(" this.").append(info.fieldName).append(",");
		}
		sb.decrementLength();
		sb.append(");");
		sb.end();

		// call registerListener
		sb.newline();
		sb.newline().append("registerListener();");
	}

	/**
	 * Generate create action.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	private void generateCreateAction(GenStringBuilder sb, Set<String> imports, GenContext context) {
//		boolean hasAction = PageManager.hasPageAction(page);
//		if (hasAction) {
//			String cn = GenerateJavaIdentifier.javaClassNamePageActionFromPage(cxt, page);
//			sb.newline().append("setActionPage( new ").append(cn).append("());");
//		} else {
//			sb.newline().append("setActionPage(null);");
//		}
	}
	
	@Override
	public boolean match(GToken kind) {
		return kind.abs() == GCst.t_constructor || 
			kind.abs() == GCst.t_method || 
			kind.abs() == GCst.t_field || super.match(kind);
	}
	
	@Override
	public void generatePartFile(GResult g, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		super.generatePartFile(g, currentItem, gf, kind, context, gGenerator, state);
		if (kind.abs() == GCst.t_constructor)
			generateConstructor(g, state.getImports(), context, (PageState) state);
		else if (kind.abs() == GCst.t_method)
			generateMethods(g, state.getImports(), context, (PageState) state);
		else if (kind.abs() == GCst.t_field)
			generateAttributes(g, state.getImports(), context, (PageState) state);
	}
}
