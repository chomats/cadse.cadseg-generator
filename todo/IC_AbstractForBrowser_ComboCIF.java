package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.gclass.part.InteractionControllerContent;
import fr.imag.adele.cadse.cadseg.managers.CadseG_WLWCListener;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.build.exporter.ExporterManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager;
import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.cadseg.managers.mc.ModelControllerManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.IGenerateContent.GenerateModel;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;


/**
 * The Class MyContentItem.
 */
class IC_StaticArrayOfObjectForBrowser_ComboManager extends IC_AbstractForBrowser_ComboManager.MyContentItem {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		super.generateCallArguments(sb, imports, object);
		Item ic = getOwnerItem();

		List<String> values = ic.getAttributeWithDefaultValue(CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO_at_VALUES_,
				new ArrayList<String>());

		sb.append(" new Object[] {");
		sb.begin();
		for (String s : values) {
			sb.newline();
			sb.appendStringValue_vir(s);
		}
		sb.decrementLength();
		sb.append("}),");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append(" final Object[] values,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append(" values,");
	}
}

/**
 * The Class MyContentItem.
 */
class IC_StringListForListManager extends IC_AbstractForListManager.IC_AbstractForListContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForListManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		super.generateCallArguments(sb, imports, object);
		Item uc = getOwnerItem();
		sb.append_exp_vir(uc, CadseGCST.IC_STRING_LIST_FOR_LIST_at_ALLOW_DUPLICATE_, false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForListManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append(" boolean allowDuplicate,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForListManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append(" allowDuplicate,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForListManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("fede.workspace.model.manager.properties");
	}
}

/**
 * The Class MyContentItem.
 */
public class ModelControllerContent extends SubFileContentManager implements IPDEContributor {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 */
	public ModelControllerContent(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.lang.String, java.lang.String, java.util.Set,
	 *      fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
		String defaultQualifiedClassName = getDefaultClassName();
		String defaultClassName = JavaIdentifier.getlastclassName(defaultQualifiedClassName);

		if ("inner-class".equals(kind)) {
			generateParts(sb, type, kind, imports, null);
			boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());
			if (extendsClass) {
				Item mc = getOwnerItem();
				Item field = mc.getPartParent().getPartParent();

				String extendsClassName = defaultClassName;

				defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false, "MC");
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("static public class ").append(defaultClassName).append(" extends ").append(
						extendsClassName).append(" {");
				sb.begin();
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("public ").append(defaultClassName).append("(");
				generateConstructorParameter(sb);
				sb.decrementLength();
				sb.append(") {");
				sb.newline().append("	super(");
				generateConstrustorArguments(sb);
				sb.decrementLength();
				sb.append(");");
				sb.newline().append("}");
				sb.end();
				sb.newline();
				sb.newline().append("}");
				sb.newline();
			}
		}
		if (kind.equals("field-init")) {
			boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());
			if (extendsClass) {
				Item mc = getOwnerItem();
				Item field = mc.getPartParent().getPartParent();

				defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false, "MC");
			}

			sb.newline().append(defaultClassName).append(" mc = ");

			sb.append("new ").append(defaultClassName).append("(");
			generateCallArguments(sb, imports, null);
			sb.decrementLength();
			sb.append(");");

			imports.add(defaultQualifiedClassName);
		}

	}

	/**
	 * Generate call arguments.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param object
	 *            the object
	 */
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		ModelControllerManager.this.generateCallArguments(getOwnerItem(), sb, imports, object);
	}

	/**
	 * Generate construstor arguments.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		ModelControllerManager.this.generateConstrustorArguments(getOwnerItem(), sb);
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
		ModelControllerManager.this.generateConstructorParameter(getOwnerItem(), sb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage(java.util.Set)
	 */
	public void computeExportsPackage(Set<String> exports) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion(org.eclipse.pde.core.plugin.IPluginBase,
	 *      org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
	 */
	public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
	 */
	public void computeImportsPackage(Set<String> imports) {
		String defaultQualifiedClassName = getDefaultClassName();
		String packageName = JavaIdentifier.packageName(defaultQualifiedClassName);
		imports.add(packageName);
	}

}


/**
 * The Class MyContentItem.
 */
class MC_EnumManager extends ModelControllerManager.ModelControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	public MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		Item field = getOwnerItem().getPartParent().getPartParent();

		Item enumattribute = FieldManager.getAttribute(field);

		Item enumtype = EnumManager.getEnumType(enumattribute);

		IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumtype);

		String defaultValue = AttributeManager.getDefaultValueAttribute(enumattribute);

		sb.append(javaenumtype.getElementName()).append(".class").append(", ")
				.append(javaenumtype.getElementName()).append(".").append(defaultValue).append(",");
		imports.add(javaenumtype.getFullyQualifiedName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("enumclass, defaulfvalue");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		Item field = getOwnerItem().getPartParent().getPartParent();

		Item enumattribute = FieldManager.getAttribute(field);

		Item enumtype = EnumManager.getEnumType(enumattribute);

		IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumtype);
		sb.append("Class<").append(javaenumtype.getElementName()).append(")> enumclass, ").append(
				javaenumtype.getElementName()).append(" defaulfvalue,");
	}
}

/**
 * The Class MyContentItem.
 */
class MC_IntegerManager extends ModelControllerManager.ModelControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	public MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		sb.append(getOwnerItem().getAttributeWithDefaultValue(CadseGCST.MC_INTEGER_at_MIN_, 0)).append(", ");
		String maxAttribute = Convert.toString(getOwnerItem().getAttributeWithDefaultValue(CadseGCST.MC_INTEGER_at_MAX_, 0));
		if (maxAttribute == null || maxAttribute.length() == 0)
			maxAttribute = "0";
		sb.append(maxAttribute).append(", ");
		String minError = getOwnerItem().getAttribute(CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_);
		String maxError = getOwnerItem().getAttribute(CadseGCST.MC_INTEGER_at_ERROR_MSG_MAX_);
		if (minError == null || minError.length() == 0)
			sb.append("null, ");
		else
			sb.appendStringValue(minError).append(", ");
		if (maxError == null || maxError.length() == 0)
			sb.append("null, ");
		else
			sb.appendStringValue(maxError).append(", ");
		sb.append(Convert.toInteger(getOwnerItem().getAttribute(CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_)))
				.append(",");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("min, max, msg_min, msg_max, defaultValue");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("int min, int max, String msg_min, String msg_max, Integer defaultValue");
	}
}

/**
 * The Class MyContentItem.
 */
class MC_IntegerManager extends ModelControllerManager.ModelControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	public MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		sb.append(getOwnerItem().getAttributeWithDefaultValue(CadseGCST.MC_INTEGER_at_MIN_, 0)).append(", ");
		String maxAttribute = Convert.toString(getOwnerItem().getAttributeWithDefaultValue(CadseGCST.MC_INTEGER_at_MAX_, 0));
		if (maxAttribute == null || maxAttribute.length() == 0)
			maxAttribute = "0";
		sb.append(maxAttribute).append(", ");
		String minError = getOwnerItem().getAttribute(CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_);
		String maxError = getOwnerItem().getAttribute(CadseGCST.MC_INTEGER_at_ERROR_MSG_MAX_);
		if (minError == null || minError.length() == 0)
			sb.append("null, ");
		else
			sb.appendStringValue(minError).append(", ");
		if (maxError == null || maxError.length() == 0)
			sb.append("null, ");
		else
			sb.appendStringValue(maxError).append(", ");
		sb.append(Convert.toInteger(getOwnerItem().getAttribute(CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_)))
				.append(",");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("min, max, msg_min, msg_max, defaultValue");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("int min, int max, String msg_min, String msg_max, Integer defaultValue");
	}
}

/**
@generated
*/
public class MC_LinkContent extends ModelControllerManager.ModelControllerContent {

/**
	@generated
*/
public MC_LinkContent(UUID id) throws CadseException {
	super(id);
}

/*
 * (non-Javadoc)
 * 
 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
 *      java.util.Set, java.lang.Object)
 */
@Override
protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {

	Item a = ModelControllerManager.getAttribute(getOwnerItem());
	sb.append(Boolean.toString(LinkTypeManager.getMin(a) != 0)).append(", null,");
	sb.append(GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, a, null, null, imports))
			.append(",");
}

/*
 * (non-Javadoc)
 * 
 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
 */
@Override
protected void generateConstrustorArguments(GenStringBuilder sb) {
	sb.append("mandatory, msg, lt,");
}

/*
 * (non-Javadoc)
 * 
 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
 */
@Override
protected void generateConstructorParameter(GenStringBuilder sb) {
	sb.append("boolean mandatory, String msg, LinkType lt,");
}
}

/**
 * The Class MyContentItem.
 */
class MC_ListOfStringManager extends ModelControllerManager.ModelControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	public MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		Item mc = getOwnerItem();
		sb.append_exp_vir(mc, CadseGCST.MC_LIST_OF_STRING_at_MIN_, 0);
		sb.append_exp_vir(mc, CadseGCST.MC_LIST_OF_STRING_at_MAX_, -1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("min, max");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("int min, int max");
	}
}
/**
 * The Class MyContentItem.
 */
class MC_StringListToEnumListManager extends ModelControllerManager.ModelControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	public MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		Item field = getOwnerItem().getPartParent().getPartParent();

		Item enumattribute = FieldManager.getAttribute(field);

		Item enumtype = EnumManager.getEnumType(enumattribute);

		IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumtype);

		sb.append(javaenumtype.getElementName()).append(".class").append(",");
		imports.add(javaenumtype.getFullyQualifiedName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("enumclass");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		Item field = getOwnerItem().getPartParent().getPartParent();

		Item enumattribute = FieldManager.getAttribute(field);

		Item enumtype = EnumManager.getEnumType(enumattribute);

		IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumtype);

		sb.append("Class<").append(javaenumtype.getElementName()).append(")> enumclass,");
	}
}

/**
 * The Class MyContentItem.
 */
public class DisplayContent extends SubFileContentManager implements IPDEContributor {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 */
	protected DisplayContent(UUID id) throws CadseException {
		super(id);
	}

	/**
	 * Compute local manifest imports.
	 * 
	 * @param item
	 *            the item
	 * @param imports
	 *            the imports
	 */
	public void computeLocalManifestImports(Item item, Set<String> imports) {

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
		imports.add("fr.imag.adele.cadse.core.ui");
		String className = getClassName(getOwnerItem());
		if (className == null) {
			className = getDefaultClassName();
			if (className != null) {
				imports.add(JavaIdentifier.packageName(className));
			}
		}
		imports.add("fr.imag.adele.cadse.core");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.lang.String, java.lang.String, java.util.Set,
	 *      fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
		String defaultQualifiedClassName = getDefaultClassName();

		String defaultClassName = JavaIdentifier.getlastclassName(defaultQualifiedClassName);
		if ("inner-class".equals(kind)) {
			generateParts(sb, type, kind, imports, null);
			Item display = getOwnerItem();
			boolean extendsUI = DisplayManager.isExtendsUIAttribute(display);
			if (extendsUI) {
				Item field = display.getPartParent();

				String extendsClassName = defaultClassName;

				defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false,
						POSTFIXE_UI);
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("static public class ").append(defaultClassName).append(" extends ").append(
						extendsClassName).append(" {");
				sb.begin();
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("public ").append(defaultClassName).append("(");
				generateConstructorParameter(sb);
				sb.decrementLength();
				sb.append(") {");
				sb.newline().append("	super(");
				generateConstrustorArguments(sb);
				sb.decrementLength();
				sb.append(");");
				sb.newline().append("}");
				sb.end();
				sb.newline();
				sb.newline().append("}");
				sb.newline();
			}
		}
		if (kind.equals("in-field")) {
			generateParts(sb, type, kind, imports, context);

			Item display = getOwnerItem();
			Item field = display.getPartParent();
			Item attribute = CadseG_WLWCListener.tryToRecreateAttributeLink(field);
			if (attribute == null) {
				// erreur
				sb.append("/* ERROR not found attribut*/");
				// TODO register error in workspace error manager( create)
				return;
			}

			generateParts(sb, type, "field-init", imports, context);

			boolean extendsClass = DisplayManager.isExtendsUIAttribute(display);
			if (defaultQualifiedClassName != null) {
				imports.add(defaultQualifiedClassName);
			}
			imports.add("fr.imag.adele.cadse.core.ui.EPosLabel");

			if (extendsClass) {
				defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false,
						POSTFIXE_UI);
			}

			sb.newline().append("return ");
			sb.append("new ").append(defaultClassName).append("(");
			generateCallArguments(sb, imports);

			sb.decrementLength();
			sb.append(");");
		}

	}

	/**
	 * Generate call arguments.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 */
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports) {

		Item display = getOwnerItem();
		Item field = display.getPartParent();
		Item attribute = CadseG_WLWCListener.tryToRecreateAttributeLink(field);
		if (attribute == null) {
			// erreur
			sb.append("/* ERROR not found attribut*/");
			// TODO register error in workspace error manager( create)
			return;
		}
		EPosLabel poslabel = FieldManager.getPositionAttribute(field);
		if (poslabel == null || EPosLabel.defaultpos == poslabel) {
			poslabel = getDefaultPosLabel();
		}
		String label = FieldManager.getLabelAttribute(field);
		if (label == null) {
			label = "???";
		}
		sb.append(GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, attribute, null, null,
				imports));
		if (AttributeManager.isLinkAttribute(attribute)) {
			sb.append(".getName()");
		}
		sb.append(",");
		sb.begin();
		sb.newline().appendStringValue_vir(label);
		sb.newline().append("EPosLabel.").append(poslabel.toString()).append(',');

		Item ic = getItemIC(display);
		Item mc = getItemMC(display);
		if (mc != null) {
			sb.append("mc, ");
		} else {
			sb.append("new MC_AttributesItem(), ");
			imports.add("fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem");
		}
		if (ic != null) {
			sb.append("ic,");
		} else {
			sb.append("null,");
		}
		sb.end();
	}

	/**
	 * Generate construstor arguments.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("key, label, poslabel, mc, ic");
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("String key, String label, EPosLabel poslabel, "
				+ "IModelController mc, IInteractionController ic,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion(org.eclipse.pde.core.plugin.IPluginBase,
	 *      org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
	 */
	public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
	}
}

/**
 * The Class MyContentItem.
 */
public final class DBrowserManager extends DisplayManager.DisplayContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("String key, String label, EPosLabel poslabel, "
				+ "IModelController mc, IInteractionControllerForBrowserOrCombo ic,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("key, label, poslabel, " + "mc, ic,");
	}

}
/**
 * The Class MyContentItem.
 */
public final class DComboManager extends DisplayManager.DisplayContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports) {
		super.generateCallArguments(sb, imports);
		sb.append(isEditableAttribute(getOwnerItem())).append(',');
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("String key, String label, EPosLabel poslabel, "
				+ "IModelController mc, IInteractionControllerForBrowserOrCombo ic, boolean edit,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("key, label, poslabel, " + "mc, ic, edit,");
	}

}
/**
 * The Class MyContentItem.
 */
public final class DListManager extends DisplayManager.DisplayContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb
				.append("String key, String label, EPosLabel poslabel, "
						+ "IModelController mc, IInteractionControllerForList ic, boolean edit, boolean showFilter, boolean order, boolean update,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("key, label, poslabel, " + "mc, ic, edit, showFilter, order, update,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports) {
		super.generateCallArguments(sb, imports);
		sb.append(" ").append(isEditableButtonAttribute(getOwnerItem())).append(',');
		sb.append(" ").append(isShowFilterAttribute(getOwnerItem())).append(',');
		sb.append(" ").append(isOrderButtonAttribute(getOwnerItem())).append(',');
		sb.append(" ").append(isUpdateButtonAttribute(getOwnerItem())).append(',');

		imports.add("fede.workspace.model.manager.properties.IInteractionControllerForList");
		imports.add("fr.imag.adele.cadse.core.ui.IModelController");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("org.eclipse.jface.fieldassist");
		imports.add("org.eclipse.ui.dialogs");

	}

}

/**
 * The Class MyContentItem.
 */
public final class DTextManager extends DisplayManager.DisplayContent {

	/** The Constant STYLE_ATTRIBUTE. */
	private static final String	STYLE_ATTRIBUTE	= "style_attribute";

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		imports.add("fr.imag.adele.cadse.core");
		imports.add("fede.workspace.model.manager.properties");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports) {
		super.generateCallArguments(sb, imports);
		Item display = getOwnerItem();
		sb.append_exp_vir(display, CadseGCST.DTEXT_at_VERTICAL_SPAN_, 1);
		sb.append_string_vir(display, CadseGCST.DTEXT_at_TOOL_TIP_);
		sb.append(Convert.toBoolean(display.getAttribute(CadseGCST.DTEXT_at_MULTI_LINE_), false)).append(",");
		sb.append(Convert.toBoolean(display.getAttribute(CadseGCST.DTEXT_at_NO_BORDER_), false)).append(",");
		sb.append(Convert.toBoolean(display.getAttribute(CadseGCST.DTEXT_at_WRAP_LINE_), false)).append(",");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb
				.append("String key, String label, EPosLabel poslabel, "
						+ "IModelController mc, IInteractionControllerForBrowserOrCombo ic, int vspan, String tooltip, boolean multiLine, boolean wrapLine, boolean noBorder,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("key, label, poslabel, " + "mc, ic, style, vspan, tooltip, multiLine, wrapLine, noBorder,");
	}

}

/**
 * The Class MyContentItem.
 */
public final class DTreeManager extends DisplayManager.DisplayContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		imports.add("fr.imag.adele.cadse.core");
		imports.add("fede.workspace.model.manager.properties");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("String key, String label, EPosLabel poslabel, "
				+ "IModelController mc, InteractifTreeController ic");
	}

}

/**
 * The Class ContentManager.
 */
public class ComposerContent extends SubFileContentManager implements IGenerateContent, IPDEContributor {


	/**
	 * Instantiates a new content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 */
	public ComposerContent(UUID id) throws CadseException {
		super(id);
	}	

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
	@Override
	public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
		String defaultQualifiedClassName = getDefaultClassName();
		String defaultClassName = JavaIdentifier.getlastclassName(defaultQualifiedClassName);

		if ("inner-class".equals(kind)) {
			imports.add("fr.imag.adele.cadse.core.Item");
			imports.add("fr.imag.adele.cadse.core.CadseException");
			imports.add("fr.imag.adele.cadse.core.build.IExporterTarget");
			imports.add(defaultQualifiedClassName);

			boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());
			if (extendsClass) {

				String extendsClassName = defaultClassName;
				defaultClassName = JavaIdentifier.javaIdentifierFromString(getOwnerItem().getName(), true, false,
						"Composer");
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
				sb.newline().append("public ").append(defaultClassName).append(" (");
				generateConstructorParameter(sb);
				sb.decrementLength();
				sb.append(") {");
				sb.newline().append("	super(");
				generateConstrustorArguments(sb);
				sb.decrementLength();
				sb.append(");");
				sb.newline().append("}");
				if (generateGetTargetMethod()) {

					imports.add("fr.imag.adele.cadse.core.build.IExporterTarget");

					sb.newline();
					sb.newline().append("@Override");
					sb.newline().append("protected IExporterTarget getTarget() {");
					sb.newline().append("	// TODO Auto-generated method stub");
					sb.newline().append("	return null;");
					sb.newline().append("}");

				}

				sb.newline();
				sb.newline().append("@Override");
				sb
						.newline()
						.append(
								"protected void postCompose("
										+ "IBuildingContext context, List<IExportedContent> listExportedContent, IExporterTarget target) {");
				if (callsuperPostCompose()) {
					sb.newline().append("	super.postCompose(context, listExportedContent, target);");
				}
				sb.newline().append("	// TODO Auto-generated method stub");
				sb.newline().append("}");

				generateOtherMethods(sb, imports, context);

				sb.end();

				sb.newline().append("}");

				imports.add("java.util.List");
				imports.add("fr.imag.adele.cadse.core.build.Composer");
				imports.add("fr.imag.adele.cadse.core.build.IBuildingContext");
				imports.add("fr.imag.adele.cadse.core.build.IExportedContent");

			}
		}

		if ("composers".equals(kind)) {
			boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());

			if (extendsClass) {
				defaultClassName = JavaIdentifier.javaIdentifierFromString(getOwnerItem().getName(), true, false,
						"Composer");
			}

			sb.newline().append("new ").append(defaultClassName).append(" (cm,");
			generateCallArguments(sb, imports, context);
			sb.decrementLength();
			sb.append("),");
		}

	}

	/**
	 * Generate other methods in the composer class when the composer
	 * extends the super class.
	 * 
	 * @param sb
	 *            A String builder to put generated code.
	 * @param imports
	 *            The list of the import package
	 * @param context
	 *            A context.
	 */
	protected void generateOtherMethods(GenStringBuilder sb, Set<String> imports, GenContext context) {

	}

	/**
	 * Generate the arguments to call the extended constructor of the
	 * composer when the composer class extends the super class.
	 * 
	 * @param sb
	 *            A String builder to put generated code.
	 * @param imports
	 *            The list of the import package
	 * @param context
	 *            A context.
	 */
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, GenContext context) {
		List<String> types = getTypesAttribute(getOwnerItem());
		if (types != null) {
			for (String exporterType : types) {
				sb.append(' ').appendStringValue(exporterType).append(',');
			}
		}
	}

	/**
	 * Generate the arguments to call the super constructor of the composer
	 * when the composer class extends the super class.
	 * 
	 * @param sb
	 *            A String builder to put generated code.
	 */
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.newline().append("contentItem, exporterTypes,");
	}

	/**
	 * Generate the parameters of the extended constructor of the composer
	 * when the composer class extends the super class.
	 * 
	 * @param sb
	 *            A String builder to put generated code.
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("ContentItem contentItem, String... exporterTypes,");
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
		String className = getDefaultClassName();
		String packageName = JavaIdentifier.packageName(className);
		imports.add(packageName);
		imports.add("fr.imag.adele.cadse.core");
		if (mustBeExtended()) {
			imports.add("org.eclipse.core.resources");
			imports.add("fede.workspace.eclipse.composition");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion(org.eclipse.pde.core.plugin.IPluginBase,
	 *      org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
	 */
	public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
	}

}
/**
 * The Class ContentManager.
 */
public class AJProjectComposerContent extends InteractionControllerContent {

	/**
	 * Instantiates a new content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	public AJProjectComposerContent(UUID id, InteractionControllerManager manager) throws CadseException {
		super(id, manager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.ComposerManager.ContentManager
	 * #
	 * generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder
	 * )
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append("Path skipWeaving,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.ComposerManager.ContentManager
	 * #
	 * generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder
	 * )
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append("skipWeaving,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.ComposerManager.ContentManager
	 * #generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 * java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, GenContext context) {
		sb.append(getOwnerItem().getAttributeWithDefaultValue(CadseGCST.AJPROJECT_COMPOSER_at_SKIP_WEAVING_, false));
	}

}

/**
 * The Class ContentManager.
 */
public class ExporterContent extends SubFileContentManager implements
		IGenerateContent, IPDEContributor {

	/**
	 * Instantiates a new content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 */
	public ExporterContent(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.core.IGenerateContent#generate(fr.imag.adele.
	 * cadse.core.var.ContextVariable)
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
	 * @see
	 * fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse
	 * .core.GenStringBuilder, java.lang.String, java.lang.String,
	 * java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	public void generate(GenStringBuilder sb, String type, String kind,
			Set<String> imports, GenContext context) {
		// /ItemType it = getItem().getType();
		String defaultQualifiedClassName = getDefaultClassName();
		String defaultClassName = JavaIdentifier
				.getlastclassName(defaultQualifiedClassName);

		if ("inner-class".equals(kind)) {
			generateParts(sb, type, kind, imports, null);
			boolean extendsClass = mustBeExtended()
					|| isExtendsClass(getOwnerItem());

			if (extendsClass) {

				String extendsClassName = defaultClassName;
				defaultClassName = JavaIdentifier.javaIdentifierFromString(
						getOwnerItem().getName(), true, false, "Exporter");

				Item manager = getOwnerItem().getPartParent();

				Item itemtype = ManagerManager.getItemType(manager);

				Item superitemtype = ItemTypeManager.getSuperType(itemtype);
				if (superitemtype != null) {
					Item superItemManager = ItemTypeManager
							.getManager(superitemtype);
					Item supercontentItem = null;
					if (superItemManager != null)
						supercontentItem = ManagerManager
							.getContentModel(superItemManager);
					if (supercontentItem != null) {
						if (isExtendsClass(supercontentItem)) {
							JavaFileContentManager javaFileContentManager = (JavaFileContentManager) superItemManager
									.getContentItem();
							extendsClassName = javaFileContentManager
									.getClassName(context)
									+ ".MyContentItem";
						}
					}
				}
				sb.newline();
				sb.appendGeneratedTag();
				sb.newline().append("public class ").append(
						defaultClassName).append(" extends ").append(
						extendsClassName).append(" {");
				sb.begin();
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("public ").append(defaultClassName)
						.append("(");
				generateConstructorParameter(sb);
				sb.decrementLength();
				sb.append(") {");
				sb.newline().append("	super(");
				generateConstrustorArguments(sb);
				sb.decrementLength();
				sb.append(");");
				sb.newline().append("}");
				sb.end();
				sb.newline().newline().append("@Override");
				sb
						.newline()
						.append(
								"public IExportedContent exportItem(IBuildingContext context,"+
			" IExporterTarget target, String exporterType, boolean fullExport) throws CadseException {");
				sb.newline().append("	// TODO Auto-generated method stub");
				sb.newline().append("	return null;");
				sb.newline().append("}");
				sb.newline();
				sb.newline().append("}");
				sb.newline();

				
				imports.add("fr.imag.adele.cadse.core.build.Exporter");
				imports
						.add("fr.imag.adele.cadse.core.build.IBuildingContext");
				imports
						.add("fr.imag.adele.cadse.core.build.IExportedContent");
				imports
						.add("fr.imag.adele.cadse.core.build.IExporterTarget");

				imports.add("fr.imag.adele.cadse.core.content.ContentItem");
			}
		}
		if ("exporters".equals(kind)) {
			boolean extendsClass = mustBeExtended()
					|| isExtendsClass(getOwnerItem());

			if (extendsClass) {
				defaultClassName = JavaIdentifier.javaIdentifierFromString(
						getOwnerItem().getName(), true, false, "Exporter");
			}

			sb.newline().append("new ").append(defaultClassName).append(
					"(cm,");
			generateCallArguments(sb, imports, null);
			sb.decrementLength();
			sb.append("),");

			imports.add("fr.imag.adele.cadse.core.content.ContentItem");
			imports.add("fr.imag.adele.cadse.core.Item");
			imports.add("fr.imag.adele.cadse.core.CadseException");
			imports.add(defaultQualifiedClassName);
			imports.add("fr.imag.adele.cadse.core.build.IExportedContent");
			imports.add("fr.imag.adele.cadse.core.build.Exporter");
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
	protected void generateCallArguments(GenStringBuilder sb,
			Set<String> imports, GenContext context) {
		List<String> types = getTypesAttribute(getOwnerItem());
		if (types != null) {
			for (String exporterType : types) {
				sb.append(' ').appendStringValue(exporterType).append(',');
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
		sb.append("contentItem, exporterTypes,");
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("ContentItem contentItem, String... exporterTypes,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
	 * computeExportsPackage(java.util.Set)
	 */
	public void computeExportsPackage(Set<String> exports) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
	 * computeImportsPackage(java.util.Set)
	 */
	public void computeImportsPackage(Set<String> imports) {
		String className = getDefaultClassName();
		String packageName = JavaIdentifier.packageName(className);
		imports.add(packageName);
		imports.add("fr.imag.adele.cadse.core");
		imports.add("fr.imag.adele.cadse.core.build");
		imports.add("fr.imag.adele.cadse.core.content");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion
	 * (org.eclipse.pde.core.plugin.IPluginBase,
	 * org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
	 */
	public void computeExtenstion(IPluginBase pluginBase,
			WorkspacePluginModel workspacePluginModel) {
	}

}
/**
 * The Class MyContentItem.
 */
public class MyContentItem extends ExporterManager.ExporterContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	public MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.exporter.ExporterManager
	 * .ContentManager
	 * #generateConstrustorArguments(fr.imag.adele.cadse.core.
	 * GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("contentItem, type, path");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.exporter.ExporterManager
	 * .ContentManager
	 * #generateConstructorParameter(fr.imag.adele.cadse.core.
	 * GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("ContentItem contentItem, " + "String type, String path");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.exporter.ExporterManager
	 * .ContentManager
	 * #generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 * java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb,
			Set<String> imports, GenContext context) {
		List<String> type = getTypesAttribute(getOwnerItem());
		if (type != null && type.size() == 1) {
			sb.append(' ').appendStringValue(type.get(0)).append(',');
		} else {
			sb.append(' ').append("error").append(',');
		}
		String path = getPathAttribute(getOwnerItem());
		sb.append(' ').appendStringValue(path).append(',');
	}
}
/**
 * The Class MyContentItem.
 */
public class JavaExporterGenerator extends ExporterManager.ExporterContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	public MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.exporter.ExporterManager
	 * .ContentManager
	 * #generateConstrustorArguments(fr.imag.adele.cadse.core.
	 * GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("contentItem");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.exporter.ExporterManager
	 * .ContentManager
	 * #generateConstructorParameter(fr.imag.adele.cadse.core.
	 * GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("ContentItem contentItem");
	}

}
/**
 * The Class MyContentItem.
 */
public class InteractionControllerContent extends SubFileContentManager implements IPDEContributor {

	/** The Constant POSTFIXE_IC. */
	private static final String	POSTFIXE_IC	= "IC";

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 */
	protected InteractionControllerContent(UUID id) throws CadseException {
		super(id);
	}

	/**
	 * Compute local manifest imports.
	 * 
	 * @param item
	 *            the item
	 * @param imports
	 *            the imports
	 */
	public void computeLocalManifestImports(Item item, Set<String> imports) {

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
		String defaultQualifiedClassName = getDefaultClassName();
		String packageName = JavaIdentifier.packageName(defaultQualifiedClassName);
		imports.add(packageName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion(org.eclipse.pde.core.plugin.IPluginBase,
	 *      org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
	 */
	public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.lang.String, java.lang.String, java.util.Set,
	 *      fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
		String defaultQualifiedClassName = getDefaultClassName();
		String defaultClassName = JavaIdentifier.getlastclassName(defaultQualifiedClassName);

		if ("inner-class".equals(kind)) {
			generateParts(sb, type, kind, imports, null);
			boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());
			if (extendsClass) {
				Item uc = getOwnerItem();
				Item field = uc.getPartParent().getPartParent();

				String extendsClassName = defaultClassName;

				defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false,
						POSTFIXE_IC);
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("static public class ").append(defaultClassName);
				if (isInterface()) {
					sb.append(" implements ");
				} else {
					sb.append(" extends ");
				}
				sb.append(extendsClassName).append(" {");
				sb.begin();
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("public ").append(defaultClassName).append("(");
				generateConstructorParameter(sb);
				sb.decrementLength();
				sb.append(") {");
				sb.newline().append("	super(");
				generateConstrustorArguments(sb);
				sb.decrementLength();
				sb.append(");");
				sb.newline().append("}");
				sb.end();
				sb.newline();
				sb.newline().append("}");
				sb.newline();
			}
		}
		if (kind.equals("field-init")) {
			Item ic = getOwnerItem();
			boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());
			if (extendsClass) {
				Item field = ic.getPartParent().getPartParent();
				defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false,
						POSTFIXE_IC);
			}

			sb.newline().append(defaultClassName).append(" ic = ");

			sb.append("new ").append(defaultClassName).append("(");
			generateCallArguments(sb, imports, null);
			sb.decrementLength();
			sb.append(");");
			imports.add(defaultQualifiedClassName);

			imports.add(getDefaultClassName());
		}
	}

	/**
	 * Generate call arguments.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param object
	 *            the object
	 */
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
	}

	/**
	 * Generate construstor arguments.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstrustorArguments(GenStringBuilder sb) {
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
	}

}
/**
 * The Class MyContentItem.
 */
class IC_AbstractForBrowser_ComboContentItem extends InteractionControllerManager.InteractionControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, true, "??", sb);
		DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, true, "??", sb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append(" String title, String message,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append(" title, message,");
	}
}
/**
 * The Class MyContentItem.
 */
public class IC_AbstractForListContent extends InteractionControllerManager.InteractionControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected IC_AbstractForListContent(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		Item uc = getOwnerItem();
		sb.append_string_vir(uc, CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_);
		sb.append_string_vir(uc, CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("String title, String message,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("title, message,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("fede.workspace.model.manager.properties");
	}
}
/**
 * The Class MyContentItem.
 */
class IC_AbstractTreeDialogForList_Browser_ComboContentItem extends InteractionControllerManager.InteractionControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		DisplayManager.addAttributeInCall(getOwnerItem(), 
				CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, true, "???", sb);
		DisplayManager.addAttributeInCall(getOwnerItem(), 
				CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, true, "???", sb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append(" String title, String message,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append(" title, message,");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("fede.workspace.model.manager.properties");
	}

}

/**
 * The Class MyContentItem.
 */
class IC_EnumForBrowser_ComboManager extends IC_AbstractForBrowser_ComboManager.MyContentItem {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		super.generateCallArguments(sb, imports, object);
		Item uc = getOwnerItem();

		Item field = uc.getPartParent().getPartParent();

		Item enumattribute = FieldManager.getAttribute(field);

		Item enumtype = EnumManager.getEnumType(enumattribute);

		IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumtype);

		sb.append(javaenumtype.getElementName()).append(".class");
		sb.append(",");

		imports.add(javaenumtype.getFullyQualifiedName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append(" Class values,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append(" values,");
	}

}


/**
 * The Class MyContentItem.
 */
class IC_EnumForListManager extends IC_AbstractForBrowser_ComboManager.MyContentItem {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		super.generateCallArguments(sb, imports, object);
		Item uc = getOwnerItem();

		Item field = uc.getPartParent().getPartParent();

		Item enumattribute = FieldManager.getAttribute(field);

		Item enumtype = EnumManager.getEnumType(enumattribute);

		IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumtype);

		sb.append(javaenumtype.getElementName()).append(".class");
		sb.append(",");

		imports.add(javaenumtype.getFullyQualifiedName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append(" Class values,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append(" values,");
	}

}
/**
 * The Class MyContentItem.
 */
class IC_JavaClassForBrowser_ComboManager extends InteractionControllerManager.InteractionControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
				true, "??", sb);
		DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
				true, "??", sb);
		sb.append(" IJavaElementSearchConstants.");
		String style = getOwnerItem().getAttribute(CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_STYLE_);
		String stylecst = style_values_cst[0];
		if (style != null) {
			for (int i = 0; i < IC_JavaClassForBrowser_Combo.style_values.length; i++) {
				if (style.equals(IC_JavaClassForBrowser_Combo.style_values[i])) {
					stylecst = style_values_cst[i];
					break;
				}
			}
		}
		sb.append(stylecst).append(",");
		DisplayManager.addAttributeInCall(getOwnerItem(), 
				CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_FILTER_, true, "", sb);
		imports.add("org.eclipse.jdt.ui.IJavaElementSearchConstants");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append(" String title, String message, int style, String filter,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append(" title, message, style, filter,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("org.eclipse.jdt.ui");
	}

}

//String title, String message, LinkType linkType
/**
 * The Class MyContentItem.
 */
class IC_LinkForBrowser_Combo_ListManager extends IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		super.generateCallArguments(sb, imports, object);
		Item ic = getOwnerItem();
		Item a = FieldManager.getAttribute(ic.getPartParent().getPartParent());
		sb.append(GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, a, null, null, imports))
				.append(",");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append(" LinkType linkType,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append(" linkType,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("fede.workspace.model.manager.properties");
		imports.add("fede.workspace.model.manager.properties.impl.ic");
	}
}

/**
 * The Class MyContentItem.
 */
class IC_PartLinkForBrowser_Combo_ListManager extends IC_LinkForBrowser_Combo_ListManager.MyContentItem {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		super.generateCallArguments(sb, imports, object);
		Item ic = getOwnerItem();

		Item a = FieldManager.getAttribute(ic.getPartParent().getPartParent());
		Item itemtypedest = LinkTypeManager.getDestination(a);

		Item[] incomingLinkType = ItemTypeManager.getIncomingLinkTypesOfPart(itemtypedest);

		if (incomingLinkType.length == 1 && incomingLinkType[0] != a) {
			Item partLinkTytpe = incomingLinkType[0];
			sb.append(
					GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, partLinkTytpe, null,
							null, imports)).append(",");
		} else {
			sb.append("null /*error cannot find incoming part from ").append(a.getName()).append("*/,");
		}
		DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
				true, "??", sb);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append(" LinkType partLinkType, String errormessage,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append(" partLinkType, errormessage,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
	}
}
/**
 * The Class MyContentItem.
 */
class IC_ResourceTreeDialogForBrowser_Combo_ListManager extends IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		super.generateCallArguments(sb, imports, object);
		DisplayManager.addAttributeInCall(getOwnerItem(), 
				CadseGCST.IC_RESOURCE_TREE_DIALOG_FOR_BROWSER_COMBO_LIST_at_SELECT_TYPE_ROOT_, false, 0, sb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append(" int selectRoot,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append(" selectRoot,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractTreeDialogForList_Browser_ComboManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("fede.workspace.model.manager.properties.impl.ic");
	}
}
/**
 * The Class MyContentItem.
 */
class IC_FileResourceForBrowser_Combo_ListManager extends IC_ResourceTreeDialogForBrowser_Combo_ListManager.MyContentItem {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		super.generateCallArguments(sb, imports, object);

		DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST_at_PATTERN_SELECT_FILE_, true, ".*", sb);
		DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_FILE_RESOURCE_FOR_BROWSER_COMBO_LIST_at_SELECT_FOLDER_, false, "false", sb);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append(" String pattern, boolean selectFolder,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_ResourceTreeDialogForBrowser_Combo_ListManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append(" pattern, selectFolder,");
	}

}
public class IC_AbstractForBrowser_ComboCIF extends InteractionControllerCIF {
	/**
	 * The Class MyContentItem.
	 */
	public static class IC_AbstractForBrowser_ComboContent extends InteractionControllerContent {

		/**
		 * Instantiates a new my content manager.
		 * 
		 * @param parent
		 *            the parent
		 * @param item
		 *            the item
		 * @throws CadseException
		 */
		protected IC_AbstractForBrowser_ComboContent(UUID id, InteractionControllerManager interactionControllerManager)
				throws CadseException {
			super(id, interactionControllerManager);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
		 *      java.util.Set, java.lang.Object)
		 */
		@Override
		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
			DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
					true, "??", sb);
			DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
					true, "??", sb);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstructorParameter(GenStringBuilder sb) {
			sb.append(" String title, String message,");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
		 */
		@Override
		protected void generateConstrustorArguments(GenStringBuilder sb) {
			sb.append(" title, message,");
		}
	}

	public IC_AbstractForBrowser_ComboCIF(InteractionControllerManager interactionControllerManager) {
		super(interactionControllerManager);
	}

	@Override
	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		return new IC_AbstractForBrowser_ComboContent(id, _manager);
	}
}
