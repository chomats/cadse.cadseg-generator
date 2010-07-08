package fr.imag.adele.cadse.cadseg.generator.action;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.MelusineProjectManager;
import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerJavaFileContentManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;
import fr.imag.adele.fede.workspace.si.view.View;

public class GAManagerFile extends GAction {
	
	/**
	 * The Class GenerateModel.
	 */
	static public class GenerateModel extends IGenerateContent.GenerateModel {

		/** The package name. */
		public String							packageName;

		/** The class name. */
		public String							className;

		/** The super class name. */
		public String							superClassName;

		/** The item name. */
		public String							itemName;

		/** The itemtype. */
		public Item								itemtype;

		/** The manager. */
		public Item								manager;

		/** The cm. */
		public ManagerJavaFileContentManager	cm;

		public boolean							overwriteClass;

		public Item getCadseDefinition() {
			return ItemTypeManager.getCadseDefinition(itemtype);
		}
	}
	/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * fr.imag.adele.cadse.core.IGenerateContent#generate(fr.imag.adele.
			 * cadse.core.var.ContextVariable)
			 */
			public void generate(JavaFileContentManager jf, ContextVariable cxt) {
				Item manager = jf.getOwnerItem();
	
				Item cadseDefinition = _getCadseDefinition(manager);
	
				GenerateModel cm = new GenerateModel();
	
				if (jf.getPartParent() == null) {
					// reconnect content...
					jf.setParentContent(cadseDefinition.getContentItem());
				}
				ICompilationUnit cu = jf.getCompilationUnit(cxt);
				if (cu == null) {
					Activator.getDefault().log(
							new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Cannot find cu of " + getFile(cxt) + " for "
									+ getOwnerItem().getQualifiedName()));
					return;
	
				}
				cm.itemtype = ManagerManager.getItemType(manager);
				if (cm.itemtype == null) {
					return;
				}
				cm.manager = manager;
				cm.itemName = cm.itemtype.getName();
				cm.className = jf.getClassName(cxt);
				cm.packageName = jf.getPackageName(cxt);
				IType type = cu.getType(cm.className);
	
				ItemType superItem = (ItemType) ItemTypeManager.getSuperType(cm.itemtype);
				if (superItem != null) {
					cm.superClassName = ItemTypeManager.getManagerClass(superItem, cxt, null);
					cm.overwriteClass = true;
				} else if (ItemTypeManager.isIsMetaItemTypeAttribute(cm.itemtype)) {
					cm.superClassName = "fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager";
					cm.overwriteClass = false;
				} else {
					cm.superClassName = "fr.imag.adele.cadse.core.DefaultItemManager";
					cm.overwriteClass = true;
				}
	
				/*
				 * // OLD CODE REMPLACED BY superItem.getItemManagerClass()
				 * 
				 * if (superItem == CadseGCST.ITEM) { cm.superClassName =
				 * "fr.imag.adele.cadse.core.DefaultItemManager"; cm.overwriteClass
				 * = true; } else if (superItem != null) { if
				 * (superItem.isRuntime()) { cm.superClassName =
				 * superItem.getAttribute(CadseGCST.ITEM_TYPE_at_ITEM_MANAGER_); }
				 * else { Item superItemManager = ItemTypeManager
				 * .getManager(superItem); JavaFileContentManager
				 * javaFileSuperContentManager = ((JavaFileContentManager)
				 * superItemManager .getContentItem()); cm.superClassName =
				 * javaFileSuperContentManager .getPackageName(cxt) + "." +
				 * javaFileSuperContentManager .getClassName(cxt);; }
				 * cm.overwriteClass = false; }
				 */
				cm.cm = jf;
	
				GenerateManager ge = new GenerateManager(cxt, cm, type);
				// ((IGenerateContent)
				// _getCadseDefinition(manager).getContentItem()).generate(cxt);
	
				String path = jf.getPath(cxt);
				try {
					EclipsePluginContentManger.generateJava(MelusineProjectManager.getProject(cadseDefinition).getFile(
							new Path(path)), ge.getContent(), View.getDefaultMonitor());
	
				} catch (CoreException e) {
					e.printStackTrace();
				}
	
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
			 * fr.imag.adele.cadse.core.ContentManager#generateParts(fr.imag.adele
			 * .cadse.core.GenStringBuilder, java.lang.String, java.lang.String,
			 * java.util.Set, fr.imag.adele.cadse.core.GenContext)
			 */
			@Override
			public void generateParts(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
				super.generateParts(sb, type, kind, imports, context);
				Item itemtype = getItemType(getOwnerItem());
				generateParts(itemtype, sb, type, kind, imports, context);
			}
	
			/*
			 * (non-Javadoc)
			 * 
			 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
			 * computeExportsPackage(java.util.Set)
			 */
			public void computeExportsPackage(Set<String> exports) {
				exports.add(getPackageName(ContextVariableImpl.DEFAULT));
			}
	
			/*
			 * (non-Javadoc)
			 * 
			 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
			 * computeImportsPackage(java.util.Set)
			 */
			public void computeImportsPackage(Set<String> imports) {
				imports.add("fede.workspace.model.manager");
				imports.add("org.eclipse.core.resources");
				imports.add("fede.workspace.tool.eclipse");
				imports.add("fede.workspace.tool.eclipse");
				imports.add("org.eclipse.core.runtime.jobs");
				imports.add("fr.imag.adele.cadse.cadseg.managers.dataModel");
				Item itemtype = getItemType(getOwnerItem());
				if (ItemTypeManager.isMetaItemTypeH(itemtype)) {
					imports.add("fr.imag.adele.cadse.cadseg.pages.dataModel");
				}
			}
	
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * fede.workspace.eclipse.composition.java.IPDEContributor#computeExtenstion
			 * (org.eclipse.pde.core.plugin.IPluginBase,
			 * org.eclipse.pde.internal.core.plugin.WorkspacePluginModel)
			 */
			public void computeExtenstion(IPluginBase pluginBase, WorkspacePluginModel workspacePluginModel) {
			}
}

