package fr.imag.adele.cadse.cadseg.generator.composer;

import java.util.List;
import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.generator.GCadseGenerator;
import fr.imag.adele.cadse.cadseg.generator.content.GContentType;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.build.ComposerManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class ContentManager.
 * Must-be instanciate after the initialisation of GCadseGenerator.MANAGER
 */
public class GComposer extends GGenPartFile {

	public GComposer() {
		matchedToken(GCadseGenerator.MANAGER.relatif(GCst.t_inner_class), GContentType.COMPOSERS);
	}
	
	class GComposer_MF extends IPDEContributor {
		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Item currentItem, Set<String> imports) {
			ComposerManager cm = (ComposerManager) currentItem.getType().getItemManager();
			String className = cm.getDefaultClassName();
			String packageName = JavaIdentifier.packageName(className);
			imports.add(packageName);
			imports.add("fr.imag.adele.cadse.core");
			if (cm.mustBeExtended()) {
				imports.add("org.eclipse.core.resources");
				imports.add("fede.workspace.eclipse.composition");
			}
		}
	}

	@Override
	public void generatePartFile(GResult sb, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		ComposerManager cm = (ComposerManager) currentItem.getType().getItemManager();
		Set<String> imports = state.getImports();
		String defaultQualifiedClassName = cm.getDefaultClassName();
		String defaultClassName = JavaIdentifier.getlastclassName(defaultQualifiedClassName);

		if (GCst.t_inner_class == kind.abs()) {
			imports.add("fr.imag.adele.cadse.core.Item");
			imports.add("fr.imag.adele.cadse.core.CadseException");
			imports.add("fr.imag.adele.cadse.core.build.IExporterTarget");
			imports.add(defaultQualifiedClassName);

			boolean extendsClass = cm.mustBeExtended() || ComposerManager.isExtendsClass(currentItem);
			if (extendsClass) {

				String extendsClassName = defaultClassName;
				defaultClassName = JavaIdentifier.javaIdentifierFromString(currentItem.getName(), true, false,
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
				if (cm.generateGetTargetMethod()) {

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
				if (cm.callsuperPostCompose()) {
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

		if (GContentType.COMPOSERS == kind.abs()) {
			boolean extendsClass = cm.mustBeExtended() || ComposerManager.isExtendsClass(currentItem);

			if (extendsClass) {
				defaultClassName = JavaIdentifier.javaIdentifierFromString(currentItem.getName(), true, false,
						"Composer");
			}

			sb.newline().append("new ").append(defaultClassName).append(" (cm,");
			generateCallArguments(currentItem, sb, imports, context);
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
	protected void generateCallArguments(Item currentItem, GenStringBuilder sb, Set<String> imports, GenContext context) {
		List<String> types = ComposerManager.getTypesAttribute(currentItem);
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


	
}