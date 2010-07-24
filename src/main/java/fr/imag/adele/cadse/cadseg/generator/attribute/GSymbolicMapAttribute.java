package fr.imag.adele.cadse.cadseg.generator.attribute;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;

public class GSymbolicMapAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.SYMBOLIC_BIT_MAP;
	}

}
