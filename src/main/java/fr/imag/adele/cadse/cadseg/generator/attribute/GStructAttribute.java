package fr.imag.adele.cadse.cadseg.generator.attribute;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;

public class GStructAttribute extends GAttribute {
	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.STRUCT;
	}

}
