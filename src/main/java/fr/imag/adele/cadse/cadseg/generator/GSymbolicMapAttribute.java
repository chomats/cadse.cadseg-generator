package fr.imag.adele.cadse.cadseg.generator;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;

public class GSymbolicMapAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.SYMBOLIC_BIT_MAP;
	}

	@Override
	public Class<?> getTypeJava(boolean primitive) {
		return Integer.class;
	}

	@Override
	public Class<? extends IAttributeType<?>> getAttributeDefinitionTypeJava() {
		return fr.imag.adele.cadse.core.attribute.SymbolicBitMapAttributeType.class;
	}

}
