package fr.imag.adele.cadse.cadseg.generator.attribute;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;

public class GDoubleAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.DOUBLE;
	}

	
	@Override
	public String getJavaTypeConvertToMethod() {
		return "fr.imag.adele.cadse.core.util.Convert.toDouble";
	}
	
	@Override
	protected String generatedDefaultValue() {
		return "-1.0";
	}

	@Override
	public String getJavaTypeDefaultValue(Item attribute) {
		final String v = super.getJavaTypeDefaultValue(attribute);
		if (v.indexOf('.') != -1) {
			return v;
		}
		if ("null".equals(v)) {
			return v;
		}
		return v + ".0";
	}

}
