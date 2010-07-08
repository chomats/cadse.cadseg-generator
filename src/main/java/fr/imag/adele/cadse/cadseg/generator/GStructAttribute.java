package fr.imag.adele.cadse.cadseg.generator;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;

public class GStructAttribute extends GAttribute {
	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.STRUCT;
	}

	@Override
	public Class<? extends IAttributeType<?>> getAttributeDefinitionTypeJava() {
		return null;
	}
}
