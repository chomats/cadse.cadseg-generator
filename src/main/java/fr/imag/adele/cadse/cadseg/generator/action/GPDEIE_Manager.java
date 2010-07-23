package fr.imag.adele.cadse.cadseg.generator.action;

import java.util.Set;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.Item;

public class GPDEIE_Manager extends GPDEImporExport {
	/*
	 * (non-Javadoc)
	 * 
	 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
	 * computeExportsPackage(java.util.Set)
	 */
	public void computeExportsPackage(Set<String> exports) {
		exports.add(getPackageName());
	}

	private String getPackageName() {
		Item ow = _owner.get();
		ManagerManager.getPackageName(ow);
		return null;
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
		Item itemtype = ManagerManager.getItemType(_owner.get());
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
	public void computeExtenstion(IPluginBase pluginBase,
			WorkspacePluginModel workspacePluginModel) {
	}
}
