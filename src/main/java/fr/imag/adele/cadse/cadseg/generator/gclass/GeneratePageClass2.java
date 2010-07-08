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

import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.IType;

import fr.imag.adele.cadse.cadseg.managers.dataModel.PageManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.PageManager.PageContentManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldGenerateInfo;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ContentItemImpl;
import fr.imag.adele.cadse.core.impl.ui.ConfigurablePageFactory;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class GeneratePageClass.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class GeneratePageClass2 extends GenerateClass {
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

	/** The id. */
	String									id;

	/** The page. */
	Item									page;

	/** The add internal short name. */
//	private boolean							addInternalShortName;

	/** The add internal attribute. */
	//private boolean							addInternalAttribute;

	/** The fields. */
	private Collection<FieldGenerateInfo>	fields;

	private Item							superPage;

	private PageContentManager				supercm;

	private boolean							heritage;

	private int								cas;

	/**
	 * Generate.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param cm
	 *            the cm
	 * @param page
	 *            the page
	 */
	public static void generate(ContextVariable cxt, PageContentManager cm, Item page) {
//		GeneratePageClass2 ret;
//		String cn = cm.getClassName(cxt);
//		String pn = cm.getPackageName(cxt);
//
//		String super_pn = "fr.imag.adele.cadse.core.impl.ui";
//		String super_cn = "PageImpl";
//
//		Item cadseDefinition = PageManager.getCadseDefinition(page);
//
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
//
//		IFile f = CadseDefinitionManager.getJavaFile(cadseDefinition, "page", pn, cn);
//		IType javatype = CadseDefinitionManager.getJavaType(cadseDefinition, f, cn);
//
//		ret = new GeneratePageClass2(cxt, pn, cn, super_pn + "." + super_cn, javatype, superPage, supercm);
//		ret.id = page.getName();
//		ret.page = page;
//		///ret.addInternalShortName = PageManager.addInternalShortName(page);
//	///	ret.addInternalAttribute = PageManager.addInternalAttribute(page);
//
//		ret.fields = PageManager.getFieldGenerateInfos(cxt, page, ret.imports, superPage);
//		ret.heritage = superPage != null;
//		ret.cas = PageManager.isModificationPage(page) ? ConfigurablePageFactory.PAGE_PROPERTY_ITEM
//				: ConfigurablePageFactory.PAGE_CREATION_ITEM;
//
//		String content = ret.getContent();
//		try {
//			EclipsePluginContentManger.generateJava(f, content, new Pattern[] { ret.heritage ? p_to_heritage
//					: p_to_not_heritage }, new String[] { ret.heritage ? r_to_heritage : r_to_not_heritage }, View
//					.getDefaultMonitor());
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}

	}

	/**
	 * Instantiates a new generate page class.
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
	 * @param supercm
	 * @param superPage
	 */
	private GeneratePageClass2(ContextVariable cxt, String packageName, String className, String extendedClassName,
			IType type, Item superPage, PageContentManager supercm) {
		super(cxt, true, packageName, className, extendedClassName, (String) null, type, false);
		this.superPage = superPage;
		this.supercm = supercm;
	}

	@Override
	protected void generateAttributes(GenStringBuilder sb, Set<String> imports, GenContext context) {
		imports.add("fr.imag.adele.cadse.core.IItemNode");
		imports.add("fr.imag.adele.cadse.core.Item");
		imports.add("fr.imag.adele.cadse.core.ItemType");
		imports.add("fr.imag.adele.cadse.core.Link");
		imports.add("fr.imag.adele.cadse.core.LinkType");
		imports.add("fr.imag.adele.cadse.core.ui.PageFactory");
		imports.add("fr.imag.adele.cadse.core.ui.IPage");
		imports.add("fr.imag.adele.cadse.core.impl.ui.PageImpl");
		imports.add("fr.imag.adele.cadse.core.ui.IActionPage");

		for (FieldGenerateInfo info : fields) {
			ContentItemImpl.generate(info.field, sb, "page", "inner-class", imports, context);
		}

		if (!heritage) {
			if (cas == ConfigurablePageFactory.PAGE_CREATION_ITEM) {

				sb.newline().appendGeneratedTag();
				sb.newline().append("public Item parent;");
				sb.newline().appendGeneratedTag();
				sb.newline().append("public ItemType it;");
				sb.newline().appendGeneratedTag();
				sb.newline().append("public LinkType lt;");
				// , ,
			} else if (cas == ConfigurablePageFactory.PAGE_PROPERTY_ITEM) {
				sb.newline().appendGeneratedTag();
				sb.newline().append("public Item item;");
			}
		}
		// declaration des champ : un par field graphique.
		for (FieldGenerateInfo info : fields) {
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
	@Override
	protected void generateConstructor(GenStringBuilder sb, Set<String> imports, GenContext context) {
		// declaration du constructeur
		sb.newline().appendGeneratedTag();
		sb.newline().append("protected ").append(getClassName());
		sb.append(" (String id, String label, String title, String description, boolean isPageComplete, int hspan) {");
		sb.newline().append("	super(id,label, title, description, isPageComplete, hspan);");
		sb.newline().append("}");

		sb.newline().appendGeneratedTag();
		sb.newline().append("public ").append(getClassName()).append(" (");
		if (cas == ConfigurablePageFactory.PAGE_CREATION_ITEM) {
			sb.append("Item parent, ItemType it, LinkType lt");
		} else if (cas == ConfigurablePageFactory.PAGE_PROPERTY_ITEM) {
			sb.append("Item item");
		}
		sb.append(") {");
		sb.begin();
		generateConstructPage(sb, imports, context);
		sb.end();
		sb.newline().append("}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateMethods(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	protected void generateMethods(GenStringBuilder sb, Set<String> imports, GenContext context) {

		// method registerListener
		sb.newline();
		sb.newline().append("protected void registerListener() {");
		if (heritage) {
			sb.newline().append("  super.registerListener();");
		}
		sb.newline().append("// add init and register");
		sb.newline().append("}");

		// methods des champs

		
		for (FieldGenerateInfo info : fields) {
			if (info.superField != null) {
				continue;
			}
			FieldManager.generateMethod(info, sb, imports);
			ContentItemImpl.generate(info.field, sb, "page", "methods", imports, context);
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
	private void generateConstructPage(GenStringBuilder sb, Set<String> imports, GenContext context) {

		// call le super
		String key = PageManager.getKey(page);
		String title = PageManager.getTitle(page);
		String description = PageManager.getDesciption(page);
		String label = title;
		String isComplete = "false";

		if (description == null || description.equals("\"\"")) {
			description = "";
			PageManager.setDescriptionAttribute(page, "");
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

		if (cas == ConfigurablePageFactory.PAGE_CREATION_ITEM) {

			sb.newline().append("this.parent = parent;");
			sb.newline().append("this.it = it;");
			sb.newline().append("this.lt = lt;");
			// , ,
		} else if (cas == ConfigurablePageFactory.PAGE_PROPERTY_ITEM) {
			sb.newline().append("this.item =  item;");
		}

		/* initialise les fields */
		for (FieldGenerateInfo info : fields) {
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
		for (FieldGenerateInfo info : fields) {
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
}
