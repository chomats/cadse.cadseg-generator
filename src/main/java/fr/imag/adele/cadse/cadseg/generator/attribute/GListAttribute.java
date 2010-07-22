package fr.imag.adele.cadse.cadseg.generator.attribute;

import java.util.List;
import java.util.Set;

import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.ListAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.IAttributeCadsegForGenerate;
import fr.imag.adele.fede.workspace.as.initmodel.IInitModel;
import fr.imag.adele.fede.workspace.as.initmodel.InitModelLoadAndWrite;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ValueTypeType;

public class GListAttribute extends GAttribute {
	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.LIST;
	}

	@Override
	public Class<?> getAttributeDefinitionTypeJava() {
		return ListAttributeType.class;
	}

	@Override
	public Class<?> getTypeJava(boolean primitive) {
		return List.class;
	}

	@Override
	public String getJavaTypeConvertToMethod() {
		return "fr.imag.adele.cadse.core.util.Convert.toList";
	}
	
	public void generateAttributeRefCst(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports) {
		Class<?> cl = getAttributeDefinitionTypeJava();
		if (cl != null) {
			ListAttributeType<?> listAttr = (ListAttributeType<?>) attribute;
			IAttributeType<?> eltAttr = listAttr.getSubAttributeType();
			GAttribute manager = eltAttr.getType().adapt(GAttribute.class);
			
			appendCST2(cxt, sb, absItemType, attribute, imports, 
					getAttributeDefinitionTypeJava(), manager.getTypeJava(false));
		}
	}
	
	@Override
	public IAttributeType<?> loadAttributeDefinition(IInitModel initModel, LogicalWorkspace theWorkspaceLogique,
			TypeDefinition parent, CValuesType type, String cadseName) throws CadseException {
		ListAttributeType<?> ret = new fr.imag.adele.cadse.core.impl.attribute.ListAttributeType(
			initModel.getUUID(type.getId()), 
				initModel.getFlag(type),
				type.getKey(), 
				0, -1, null);
		return ret;
	}

	@Override
	public void writeAttributeDefinition(ObjectFactory factory, ContextVariable cxt,
			IAttributeCadsegForGenerate cadsegManager, CValuesType cvt, Item attribute) {
		// cvt.setType(ValueTypeType.UUID);
		super.writeAttributeDefinition(factory, cxt, cadsegManager, cvt, attribute);
		cvt.setType(ValueTypeType.LIST);
		cvt.setMax(-1);
		cvt.setMin(0);
		
		ListAttributeType<?> listAttr = (ListAttributeType<?>) attribute;
		IAttributeType<?> eltAttr = listAttr.getSubAttributeType();
		if (eltAttr != null) {
			CValuesType ncvt = factory.createCValuesType();
			cvt.getElement().add(ncvt);

			cvt = ncvt;
			cvt.setKey("Element of "+attribute.getName());
			cvt.setTypeName(eltAttr.getId().toString());
			GAttribute manager = eltAttr.getType().adapt(GAttribute.class);
			ItemType cadseRootItemType = manager.getCadseRootType();
			if (cadseRootItemType != null) {
				if (cadseRootItemType.isAbstract()) {
					return;
				}

				InitModelLoadAndWrite cadseRootManager = (InitModelLoadAndWrite) cadseRootItemType.getItemManager();
				cadseRootManager.writeAttributeDefinition(factory, cxt, cadsegManager, ncvt, eltAttr);
			}
		}
		
	}
}
