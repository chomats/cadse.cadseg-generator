package fr.imag.adele.cadse.cadseg.generator.attribute;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.LongAttributeType;

public class GLongAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.LONG;
	}

	@Override
	public String getJavaTypeConvertToMethod() {
		return "fr.imag.adele.cadse.core.util.Convert.toLong";
	}

	@Override
	public String getJavaTypeDefaultValue(Item attribute) {
		String v = super.getJavaTypeDefaultValue(attribute);
		if (v.endsWith("L")) {
			return v;
		}
		if ("null".equals(v)) {
			return v;
		}
		return v + "L";
	}
}
