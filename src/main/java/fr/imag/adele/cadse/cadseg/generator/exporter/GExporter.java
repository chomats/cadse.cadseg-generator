package fr.imag.adele.cadse.cadseg.generator.exporter;

import java.util.List;
import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.generator.GCadseGenerator;
import fr.imag.adele.cadse.cadseg.generator.content.GContentType;
import fr.imag.adele.cadse.cadseg.managers.build.ComposerManager;
import fr.imag.adele.cadse.cadseg.managers.build.exporter.ExporterManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class ContentManager.
 */
public class GExporter extends GGenPartFile {
	
	public GExporter() {
		matchedToken(GCadseGenerator.MANAGER.relatif(GCst.t_inner_class), GContentType.EXPORTERS);
	}
	
	public static class GExporter_MF extends IPDEContributor {
		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Item currentItem, Set<String> imports) {
			ExporterManager cm = (ExporterManager) currentItem.getType().getItemManager();
			String className = cm.getDefaultClassName();
			String packageName = JavaIdentifier.packageName(className);
			imports.add(packageName);
			imports.add("fr.imag.adele.cadse.core");
			imports.add("fr.imag.adele.cadse.core.build");
			imports.add("fr.imag.adele.cadse.core.content");
		}

	}

	@Override
	public void generatePartFile(GResult sb, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		
		ExporterManager cm = (ExporterManager) currentItem.getType().getItemManager();
		Set<String> imports = state.getImports();
		// /ItemType it = getItem().getType();
		String defaultQualifiedClassName = cm.getDefaultClassName();
		String defaultClassName = JavaIdentifier
				.getlastclassName(defaultQualifiedClassName);

		if (kind.abs() == GCst.t_inner_class) {
			boolean extendsClass = cm.mustBeExtended()
					|| ExporterManager.isExtendsClass(currentItem);

			if (extendsClass) {

				String extendsClassName = defaultClassName;
				defaultClassName = JavaIdentifier.javaIdentifierFromString(
						currentItem.getName(), true, false, "Exporter");

				Item manager = currentItem.getPartParent();

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
						if (ExporterManager.isExtendsClass(supercontentItem)) {
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
		if (kind.abs() == GContentType.EXPORTERS) {
			boolean extendsClass = cm.mustBeExtended()
					|| ExporterManager.isExtendsClass(currentItem);

			if (extendsClass) {
				defaultClassName = JavaIdentifier.javaIdentifierFromString(
						currentItem.getName(), true, false, "Exporter");
			}

			sb.newline().append("new ").append(defaultClassName).append(
					"(cm,");
			generateCallArguments(sb, imports, null, currentItem);
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
	 * @param currentItem 
	 */
	protected void generateCallArguments(GenStringBuilder sb,
			Set<String> imports, GenContext context, Item currentItem) {
		List<String> types = ExporterManager.getTypesAttribute(currentItem);
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

}