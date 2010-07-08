package fr.imag.adele.cadse.cadseg.generator;

import java.util.UUID;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.UUIDAttributeType;

public class GUUIDAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.UUID;
	}

	@Override
	public Class<?> getTypeJava(boolean primitive) {
		return UUID.class;
	}

	@Override
	public Class<? extends IAttributeType<?>> getAttributeDefinitionTypeJava() {
		return UUIDAttributeType.class;
	}
}
