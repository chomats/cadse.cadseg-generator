package fr.imag.adele.cadse.cadseg.generator.attribute;

import java.util.Set;

import org.eclipse.jdt.core.IType;

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.EnumAttributeType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.ListAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class GEnumAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.ENUM;
	}

	@Override
	public Object getCadseRootAttributeValue(ContextVariable cxt, IAttributeType<?> attType, Item attribute) {
		if (attType == CadseGCST.ENUM_at_ENUM_CLAZZ_) {
			Item enumType = EnumManager.getEnumType(attribute);
			IType enumQualifiedClass = null;
			if (enumType != null) {
				enumQualifiedClass = EnumTypeManager.getEnumQualifiedClass(cxt, enumType);
			}
			if (enumQualifiedClass != null) {
				return enumQualifiedClass.getFullyQualifiedName();
			}
		}
		return super.getCadseRootAttributeValue(cxt, attType, attribute);

	}

	@Override
	public void generateAttributeRefCst(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports) {

		Item enumType = EnumManager.getEnumType(attribute);
		if (enumType != null) {
			IType enumTypeClass = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumType);
			if (enumTypeClass != null) {
				if (AttributeManager.isIsListAttribute(attribute)) {
					appendCST(cxt, sb, absItemType, attribute, imports, ListAttributeType.class, enumTypeClass);
				} else {
					appendCST(cxt, sb, absItemType, attribute, imports, EnumAttributeType.class, enumTypeClass);
				}
				return;
			}
		}
		if (AttributeManager.isIsListAttribute(attribute)) {
			appendCST(cxt, sb, absItemType, attribute, imports, ListAttributeType.class);
		} else {
			appendCST(cxt, sb, absItemType, attribute, imports, EnumAttributeType.class);
		}

	}

	
}
