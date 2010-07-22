package fr.imag.adele.cadse.cadseg.generator.attribute;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.LongAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.IAttributeCadsegForGenerate;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;

public class GLongAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.LONG;
	}

	@Override
	public Class<?> getAttributeDefinitionTypeJava() {
		return LongAttributeType.class;
	}

	@Override
	public Class<?> getTypeJava(boolean primitive) {
		return primitive ? Long.TYPE : Long.class;
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
	
	@Override
	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
			TypeDefinition parent, CValuesType type, String cadseName) throws CadseException {
		LongAttributeType ret = new fr.imag.adele.cadse.core.impl.attribute.LongAttributeType(
				initModel.getUUID(type.getId()), type.getKey(), initModel
				.getFlag(type), type.getValue());
		return ret;
	}

	@Override
	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
			IAttributeCadsegForGenerate cadsegManager, CValuesType cvt, Item attribute) {
		// cvt.setType(ValueTypeType.UUID);
		super.writeAttributeDefinition(factory, cxt, cadsegManager, cvt, attribute);
	}
}
