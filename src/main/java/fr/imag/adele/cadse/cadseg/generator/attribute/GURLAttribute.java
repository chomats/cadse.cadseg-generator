package fr.imag.adele.cadse.cadseg.generator.attribute;

import java.net.URL;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.URLAttributeType;

public class GURLAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.URL;
	}
}
