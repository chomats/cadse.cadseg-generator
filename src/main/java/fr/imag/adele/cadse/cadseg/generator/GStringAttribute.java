package fr.imag.adele.cadse.cadseg.generator;

import fr.imag.adele.cadse.cadseg.managers.attributes.StringManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.IAttributeCadsegForGenerate;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ValueTypeType;

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
	public Class<? extends IAttributeType<?>> getAttributeDefinitionTypeJava() {
		return StringAttributeType.class;
	}

	@Override
	public Class<?> getTypeJava(boolean primitive) {
		return String.class;
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
	


	@Override
	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
			TypeDefinition parent, CValuesType type, String cadseName) throws CadseException {
		StringAttributeType ret = new fr.imag.adele.cadse.core.impl.attribute.StringAttributeType(initModel.getUUID(type.getId()), initModel.getFlag(type),
				type.getKey(), type.getValue());
		return ret;
	}

	@Override
	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
			IAttributeCadsegForGenerate cadsegManager, CValuesType cvt, Item attribute) {
		cvt.setType(ValueTypeType.STRING);
		super.writeAttributeDefinition(factory, cxt, cadsegManager, cvt, attribute);
	}
}
