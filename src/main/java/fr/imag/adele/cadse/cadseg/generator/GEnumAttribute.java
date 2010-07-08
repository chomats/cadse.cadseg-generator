package fr.imag.adele.cadse.cadseg.generator;

import java.util.Set;

import org.eclipse.jdt.core.IType;

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.EnumAttributeType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.ListAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;
import fr.imag.adele.fede.workspace.as.initmodel.IAttributeCadsegForGenerate;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ValueTypeType;

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

	@Override
	public Class<?> getAttributeDefinitionTypeJava() {
		return fr.imag.adele.cadse.core.attribute.EnumAttributeType.class;
	}
	
	@Override
	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
			TypeDefinition parent, CValuesType type, String cadseName) throws CadseException {
		String enumTypeName = type.getTypeName();
		if (type.getElement().size() == 1) {
			CValuesType clazzElement = type.getElement().get(0);
			enumTypeName = clazzElement.getValue();
		}

		// Probleme de compilation avec javac
		Class<? extends Enum> clazz = (Class<? extends Enum>) (Class<?>) initModel.loadClass(cadseName, enumTypeName);
		if (clazz == null) {
			throw new CadseException("cannot create type from {0}", type.getKey());
		}
		return new fr.imag.adele.cadse.core.impl.attribute.EnumAttributeType(initModel.getUUID(type.getId()), initModel.getFlag(type), type.getKey(), clazz,
				type.getValue());
	}

	@Override
	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
			IAttributeCadsegForGenerate cadsegManager, CValuesType cvt, Item attribute) {
		cvt.setType(ValueTypeType.ENUMERATION);
		String enumQualifiedClass = (String) cadsegManager.getCadseRootAttributeValue(cxt,
				CadseGCST.ENUM_at_ENUM_CLAZZ_, attribute);
		if (enumQualifiedClass != null) {
			CValuesType ncvt = factory.createCValuesType();
			cvt.getElement().add(ncvt);
			ncvt.setValue(enumQualifiedClass);
			ncvt.setKey(CadseGCST.ENUM_at_ENUM_CLAZZ);
		}
		super.writeAttributeDefinition(factory, cxt, cadsegManager, cvt, attribute);
	}
}
