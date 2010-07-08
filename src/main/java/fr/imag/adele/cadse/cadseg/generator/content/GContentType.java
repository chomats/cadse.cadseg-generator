package fr.imag.adele.cadse.cadseg.generator.content;

import java.util.Set;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.GGenerator;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateVariable;
import fr.imag.adele.cadse.cadseg.managers.build.CompositeItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ContentItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.objectadapter.ObjectAdapter;

/**
 * The Class MyContentItem.
 */
public class GContentType extends ObjectAdapter<GContentType> implements IGenerateContent, IPDEContributor {

	private static final String INNER_CLASS = "inner-class";
	public static final String METHODS = "methods";
	private static final String CREATECONTENT_ARGS = "createcontent-args";
	private static final String CREATECONTENT_INIT = "createcontent-init";

	/**
	 * 
	 */
	//private final ContentItemTypeManager contentItemTypeManager;

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IGenerateContent#generate(fr.imag.adele.cadse.core.var.ContextVariable)
	 */
	public void generate(ContextVariable cxt) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.IGenerateContent#getGenerateModel()
	 */
	public GenerateModel getGenerateModel() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.lang.String, java.lang.String, java.util.Set,
	 *      fr.imag.adele.cadse.core.GenContext)
	 */
	public void generate(GGenerator g, Item owner, GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
		ItemType it = owner.getType();
		Class<?> defaultQualifiedClassName = this.getRuntimeClassName();
		String defaultClassName = defaultQualifiedClassName.getSimpleName();

		if (INNER_CLASS.equals(kind)) {
			Item manager = owner.getPartParent();

			Item itemtype = ManagerManager.getItemType(manager);
			generateAllClassVariables(owner, sb, imports);

			g.generateParts(owner, sb, type, kind, imports, null);
			generateComposersParts(owner, sb, type, kind, imports, null);

			boolean extendsClass = mustBeExtended() | ContentItemTypeManager.isExtendsClass(owner);
			if (extendsClass) {

				String extendsClassName = defaultClassName;

				extendsClassName = findSuperClassName(owner, context, extendsClassName, itemtype);
				defaultClassName = GenerateJavaIdentifier.getContentClassName(context, itemtype);
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("public class ").append(defaultClassName).append(" extends ").append(
						extendsClassName).append(" {");
				sb.begin();
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("public ").append(defaultClassName).append("(");
				generateConstructorParameter(sb);
				sb.decrementLength();
				sb.append(") throws CadseException {");
				sb.newline().append("	super(");
				generateConstrustorArguments(sb);
				sb.decrementLength();
				sb.append(");");
				sb.newline().append("}");
				sb.end();
				sb.newline();
				sb.newline().append("}");
				sb.newline();
				imports.add("fr.imag.adele.cadse.core.CadseException");
			}
		}
		if (METHODS.equals(kind)) {
			boolean extendsClass = mustBeExtended() | ContentItemTypeManager.isExtendsClass(owner);
			
			Item manager = owner.getPartParent();
			Item itemtype = ManagerManager.getItemType(manager);

			String className = (extendsClass ? GenerateJavaIdentifier.getContentClassName(context, itemtype) : defaultClassName);
			String itemVar = JavaIdentifier.javaIdentifierFromString(itemtype.getName(), false, true, null);
			sb.newline();
			sb.newline().append("/**");
			sb.newline().append("	@generated");
			sb.newline().append("*/");
			sb.newline().append("@Override");
			sb.newline().append("public ContentItem createContentItem(UUID id, Item owerItem ").append(
					") throws CadseException {");
			/* 1 */sb.begin();
			GenContext newcontext = new GenContext(context);
			newcontext.setAttribute("itemVar", itemVar);
			g.generateParts(owner, sb, type, CREATECONTENT_INIT, imports, newcontext);
			generateCallInit(sb, imports, newcontext);
			sb.newline().append(className).append(" cm = new ").append(className).append("(");
			/* 2 */sb.begin();
			sb.newline().append("id,");
			g.generateParts(owner, sb, type, CREATECONTENT_ARGS, imports, newcontext);
			/* 3 */sb.begin();
			generateCallArguments(owner, sb, imports, newcontext);
			sb.decrementLength();
			/* 3 */sb.end();

			sb.newline().append(");");
			/* 2 */sb.end();
			sb.newline().append("owerItem.setComposers(");
			/* 2 */sb.begin();
			generateComposersParts(owner, sb, type, "composers", imports, newcontext);
			sb.decrementLength();
			/* 2 */sb.end();
			sb.newline().append(");");
			sb.newline().append("owerItem.setExporters(");
			/* 2 */sb.begin();
			generateExportersParts(owner, sb, type, "exporters", imports, newcontext);
			sb.decrementLength();
			/* 2 */sb.end();
			sb.newline().append(");");
			sb.newline().append("return cm;");
			/* 1 */sb.end();
			sb.newline().append("}").newline();
			imports.add("fr.imag.adele.cadse.core.content.ContentItem");
			imports.add("java.util.UUID");
			imports.add("fr.imag.adele.cadse.core.Item");
			imports.add("fr.imag.adele.cadse.core.var.Variable");
			imports.add("fr.imag.adele.cadse.core.var.ContextVariable");
			imports.add("fr.imag.adele.cadse.core.CadseException");
			imports.add(defaultQualifiedClassName.getCanonicalName());

		}
	}

	/**
	 * Must be extended.
	 * 
	 * @return true, if successful
	 */
	public boolean mustBeExtended() {
		return true;
	}
	
	/**
	 * Recherche la class du contentmanager et le manager provenant d'un
	 * super type...
	 * 
	 * @param extendsClassName
	 *            default valeur;
	 * @param itemtype
	 *            le type auquel on veut associer ce content manager.
	 * @param cxt
	 *            the cxt
	 * 
	 * @return le nom de la class ou la valeur par default.
	 */
	private String findSuperClassName(Item owner, ContextVariable cxt, String extendsClassName, Item itemtype) {
		while (true) {
			Item superitemtype = ItemTypeManager.getSuperType(itemtype);
			if (superitemtype == null) {
				break;
			}
			Item superItemManager = ItemTypeManager.getManager(superitemtype);
			if (superItemManager == null)
				break;
			Item supercontentItem = ManagerManager.getContentModel(superItemManager);
			if (supercontentItem != null && supercontentItem.getType() == owner.getType()) {
				if (ContentItemTypeManager.isExtendsClass(supercontentItem)) {
					return ((JavaFileContentManager) superItemManager.getContentItem()).getClassName(cxt)
							+ "."+GenerateJavaIdentifier.getContentClassName(cxt, superitemtype);
				}
			}
			itemtype = superitemtype;

		}
		return extendsClassName;
	}

	/**
	 * Generate all class variables.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 */
	protected void generateAllClassVariables(Item owner, GenStringBuilder sb, Set<String> imports) {
		StringAttributeType[] kinds = getResourceKindsName();

		if (kinds == null) {
			return;
		} else {
			for (int i = 0; i < kinds.length; i++) {
				StringAttributeType strKinds = kinds[i];
				String value = owner.getAttribute(strKinds);

				value = getDefaultValue(strKinds, value);
				if (value == null || value.length() ==0) {
					continue;
				}
				GenerateVariable.generateClassVariable(owner,
						GenerateVariable.getClassVariable(strKinds.getName(), true), value, sb, imports);
			}
		}
	}

	protected String getDefaultValue(StringAttributeType strKinds, String value) {
		return value;
	}

	/**
	 * Gets the resource kinds name.
	 * 
	 * @return the resource kinds name
	 */
	protected StringAttributeType[] getResourceKindsName() {
		return null;
	}

	/**
	 * Generate call init.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param newcontext
	 *            the newcontext
	 */
	protected void generateCallInit(GenStringBuilder sb, Set<String> imports, GenContext newcontext) {

	}

	/**
	 * Generate exporters parts.
	 * 
	 * @param item
	 *            the item
	 * @param sb
	 *            the sb
	 * @param type
	 *            the type
	 * @param kind
	 *            the kind
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	public void generateExportersParts(Item item, GenStringBuilder sb, String type, String kind,
			Set<String> imports, GenContext context) {
		Item mapping = item.getPartParent();
		for (Link l : mapping.getOutgoingLinks()) {
			if (l.getLinkType().isPart() && l.isLinkResolved()) {
				ContentItem cm = l.getResolvedDestination().getContentItem();
				if (!l.getLinkType().getName().equals(ContentItemTypeManager.EXPORTERS_LINK)) {
					continue;
				}
				if (cm != null) {
					cm.generate(sb, type, kind, imports, context);
				}
			}
		}
	}

	/**
	 * Generate composers parts.
	 * 
	 * @param item
	 *            the item
	 * @param sb
	 *            the sb
	 * @param type
	 *            the type
	 * @param kind
	 *            the kind
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	public void generateComposersParts(Item item, GenStringBuilder sb, String type, String kind,
			Set<String> imports, GenContext context) {
		Item managerItem = item.getPartParent();
		Item itemtype = ManagerManager.getItemType(managerItem);
		Item compositeItem = CompositeItemTypeManager.getCompositeItemFromItemType(itemtype);
		if (compositeItem != null) {
			for (Link l : compositeItem.getOutgoingLinks()) {
				if (l.getLinkType().isPart() && l.isLinkResolved()) {
					ContentItem cm = l.getResolvedDestination().getContentItem();
					if (l.getLinkType() != CadseGCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS) {
						continue;
					}
					if (cm != null) {
						cm.generate(sb, type, kind, imports, context);
					}
				}
			}
		}
	}

	/**
	 * Generate call arguments.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	protected void generateCallArguments(Item owner, GenStringBuilder sb, Set<String> imports, GenContext context) {
		StringAttributeType[] kinds = getResourceKindsName();
		if (kinds == null) {
			return;
		} else {
			for (int i = 0; i < kinds.length; i++) {
				StringAttributeType strKinds = kinds[i];
				String value = owner.getAttribute(strKinds);
				value = getDefaultValue(strKinds, value);
				if (value == null || value.length() == 0) {
					sb.append(" ").append("NullVariable.INSTANCE,");
					imports.add("fr.imag.adele.cadse.core.impl.var.NullVariable");
				} else {
					sb.append(" ").append(GenerateVariable.getClassVariable(strKinds.getName(), true)).append(".INSTANCE,");
				}
			}
		}
	}

	/**
	 * Generate construstor arguments.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("id,");
		StringAttributeType[] kinds = getResourceKindsName();
		if (kinds == null) {
			return;
		} else {
			for (int i = 0; i < kinds.length; i++) {
				StringAttributeType strKinds = kinds[i];
				sb.append(" ").append(GenerateVariable.getClassVariable(strKinds.getName(), false)).append(",");
			}
		}
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
		
		sb.append("UUID id,");
		StringAttributeType[] kinds = getResourceKindsName();

		if (kinds == null) {
			return;
		} else {
			for (int i = 0; i < kinds.length; i++) {
				StringAttributeType strKinds = kinds[i];
				sb.append(" Variable ").append(GenerateVariable.getClassVariable(strKinds.getName(), false)).append(",");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage(java.util.Set)
	 */
	public void computeExportsPackage(Set<String> exports) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
	 */
	public void computeImportsPackage(Set<String> imports) {
		Class<?> className = getRuntimeClassName();
		if (className != null) {
			String packageName = className.getPackage().getName();
			imports.add(packageName);
		}
		imports.add("fr.imag.adele.cadse.core");
		imports.add("org.eclipse.ltk.core.refactoring");
		imports.add("fr.imag.adele.cadse.core.build");
		imports.add("fr.imag.adele.cadse.core.var");
		imports.add("fr.imag.adele.cadse.core.impl.var");
		imports.add("fr.imag.adele.cadse.core.impl");
		imports.add("fr.imag.adele.cadse.core.content");
		imports.add("fr.imag.adele.cadse.util");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion(org.eclipse.pde.core.plugin.IPluginBase,
	 *      org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
	 */
	public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
	}

	@Override
	public Class<GContentType> getClassAdapt() {
		return GContentType.class;
	}

	public boolean hasParentContent() {
		return false;
	}

	/**
	 * <meta-attribute key="title-create"> <value type="string" value="Extended
	 * Content manager"/> </meta-attribute> <meta-attribute
	 * key="must-be-extended"> <value type="boolean" value="true"/>
	 * </meta-attribute> <meta-attribute key="class-name"> <value type="string"
	 * value="fede.workspace.domain.ContentManager"/> </meta-attribute>
	 * 
	 * @return
	 */
	public Class<? extends ContentItem> getRuntimeClassName() {
		return fr.imag.adele.cadse.core.impl.ContentItemImpl.class;
	}
}