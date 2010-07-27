package fr.imag.adele.cadse.cadseg.generator.attribute;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;

public class GURLAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.URL;
	}
}
