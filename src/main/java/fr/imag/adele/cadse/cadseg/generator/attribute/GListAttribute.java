package fr.imag.adele.cadse.cadseg.generator.attribute;

import java.util.Set;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.ListAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;

public class GListAttribute extends GAttribute {
	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.LIST;
	}
	
	@Override
	public String getJavaTypeConvertToMethod() {
		return "fr.imag.adele.cadse.core.util.Convert.toList";
	}
	
	public void generateAttributeRefCst(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports) {
		Class<?> cl = lw().getAttributeDefinitionTypeJava();
		if (cl != null) {
			ListAttributeType<?> listAttr = (ListAttributeType<?>) attribute;
			IAttributeType<?> eltAttr = listAttr.getSubAttributeType();
			GAttribute manager = eltAttr.getType().adapt(GAttribute.class);
			
			appendCST2(cxt, sb, absItemType, attribute, imports, 
					lw().getAttributeDefinitionTypeJava(), lw().getTypeJava(false));
		}
	}
}
