package fr.imag.adele.cadse.cadseg.generator.attribute;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.attribute.DoubleAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.IAttributeCadsegForGenerate;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ValueTypeType;

public class GDoubleAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.DOUBLE;
	}

	@Override
	public Class<?> getTypeJava(boolean primitive) {
		return primitive ? Double.TYPE : Double.class;
	}

	@Override
	public String getJavaTypeConvertToMethod() {
		return "fr.imag.adele.cadse.core.util.Convert.toDouble";
	}

	@Override
	public Class<? extends IAttributeType<?>> getAttributeDefinitionTypeJava() {
		return fr.imag.adele.cadse.core.attribute.DoubleAttributeType.class;
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
	
	@Override
	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
			TypeDefinition parent, CValuesType type, String cadseName) throws CadseException {
		DoubleAttributeType ret = new DoubleAttributeType(initModel.getUUID(type.getId()), initModel.getFlag(type),
				type.getKey(), null, null, type.getValue());
		return ret;
	}

	@Override
	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
			IAttributeCadsegForGenerate cadsegManager, CValuesType cvt, Item attribute) {
		cvt.setType(ValueTypeType.DOUBLE);
		super.writeAttributeDefinition(factory, cxt, cadsegManager, cvt, attribute);
	}
}
