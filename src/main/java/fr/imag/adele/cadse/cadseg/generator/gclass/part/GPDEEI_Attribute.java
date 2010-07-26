package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.Item;

public class GPDEEI_Attribute extends IPDEContributor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage
	 * (java.util.Set)
	 */
	public void computeImportsPackage(Item currentItem, Set<String> imports) {
		((AttributeManager) currentItem.getType().getItemManager())
				.computeImportsManifest(imports);
		
	}

}
