package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import java.util.Set;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fr.imag.adele.cadse.cadseg.generator.action.GPDEImporExport;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.Item;

public class GPDEEI_Attribute extends GPDEImporExport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage
	 * (java.util.Set)
	 */
	public void computeExportsPackage(Set<String> exports) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage
	 * (java.util.Set)
	 */
	public void computeImportsPackage(Set<String> imports) {
		Item currentItem = _owner.get();
		((AttributeManager) currentItem.getType().getItemManager())
				.computeImportsManifest(imports);
		imports.add("fr.imag.adele.cadse.core.util");
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
