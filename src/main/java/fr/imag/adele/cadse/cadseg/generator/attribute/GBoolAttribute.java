package fr.imag.adele.cadse.cadseg.generator.attribute;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;

public class GBoolAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.BOOLEAN;
	}

	@Override
	public String getJavaTypeConvertToMethod() {
		return "fr.imag.adele.cadse.core.util.Convert.toBoolean";
	}
}
