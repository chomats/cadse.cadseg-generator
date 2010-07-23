package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import java.util.Set;
import java.util.UUID;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.SubFileContentManager;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class MyContentItem.
 */
public class InteractionControllerContent extends SubFileContentManager implements IPDEContributor {
	InteractionControllerManager	_manager;

	/** The Constant POSTFIXE_IC. */
	private static final String		POSTFIXE_IC	= "IC";

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @param _manager2
	 */
	protected InteractionControllerContent(UUID id, InteractionControllerManager manager) {
		super(id);
		_manager = manager;
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
		String defaultQualifiedClassName = _manager.getDefaultClassName();
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
		String defaultQualifiedClassName = _manager.getDefaultClassName();
		String defaultClassName = JavaIdentifier.getlastclassName(defaultQualifiedClassName);

		if ("inner-class".equals(kind)) {
			generateParts(sb, type, kind, imports, null);
			boolean extendsClass = _manager.mustBeExtended()
					|| InteractionControllerManager.isExtendsClass(getOwnerItem());
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
				if (_manager.isInterface()) {
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
			boolean extendsClass = _manager.mustBeExtended()
					|| InteractionControllerManager.isExtendsClass(getOwnerItem());
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

			imports.add(_manager.getDefaultClassName());
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