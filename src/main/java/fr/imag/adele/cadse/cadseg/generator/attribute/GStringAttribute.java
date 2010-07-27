package fr.imag.adele.cadse.cadseg.generator.attribute;

import fr.imag.adele.cadse.cadseg.managers.attributes.StringManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;

public class GStringAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.STRING;
	}

	@Override
	public int getCadseRootFlag(Item attribute) {
		return super.getCadseRootFlag(attribute) | (StringManager.isNotEmptyAttribute(attribute) ? StringAttributeType.NOT_EMPTY : 0);
	}

	@Override
	public String getJavaTypeDefaultValue(Item attribute) {
		String defaultValue = attribute.getAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
		if (defaultValue == null || defaultValue.length() == 0) {
			defaultValue = generatedDefaultValue();
		}

		if (defaultValue == null || defaultValue.length() == 0) {
			return "null";
		}
		if ("".equals(defaultValue)) {
			return defaultValue;
		}
		if (defaultValue.startsWith("\"") && defaultValue.endsWith("\"")) {
			if (defaultValue.length() == 1 || defaultValue.length() == 2) {
				defaultValue = "";
			}
			else {
				defaultValue = defaultValue.substring(1, defaultValue.length() - 1);
			}
		}
		defaultValue = defaultValue.replace("\"", "\\\"");
		return "\"" + defaultValue + "\"";
	}
	


}
