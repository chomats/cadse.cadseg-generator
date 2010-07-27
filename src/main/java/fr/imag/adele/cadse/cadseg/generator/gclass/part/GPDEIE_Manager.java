package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.Item;

public class GPDEIE_Manager extends IPDEContributor {
	

	/*
	 * (non-Javadoc)
	 * 
	 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
	 * computeImportsPackage(java.util.Set)
	 */
	public void computeImportsPackage(Item owner, Set<String> imports) {
		imports.add("fede.workspace.model.manager");
		imports.add("org.eclipse.core.resources");
		imports.add("fede.workspace.tool.eclipse");
		imports.add("fede.workspace.tool.eclipse");
		imports.add("org.eclipse.core.runtime.jobs");
		imports.add("fr.imag.adele.cadse.cadseg.managers.dataModel");
		Item itemtype = ManagerManager.getItemType(owner);
		if (ItemTypeManager.isMetaItemTypeH(itemtype)) {
			imports.add("fr.imag.adele.cadse.cadseg.pages.dataModel");
		}
	}
}
