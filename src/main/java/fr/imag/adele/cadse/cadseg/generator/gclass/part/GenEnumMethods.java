package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import org.eclipse.jdt.core.IType;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.generator.template.EnumListOfValueAttribute;
import fr.imag.adele.cadse.cadseg.generator.template.EnumValueAttribute;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

public class GenEnumMethods extends GenAttributeMethod {

	@Override
	public void generatePartFile(GResult r, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {

		Item enumType = EnumManager.getEnumType(currentItem);
		IType enumQualifiedClass = EnumTypeManager.getEnumQualifiedClass(
				context, enumType);
		state.getImports().add(enumQualifiedClass.getFullyQualifiedName());
		Item source = currentItem;
		if (AttributeManager.isIsListAttribute(source)) {
			EnumListOfValueAttribute temp = new EnumListOfValueAttribute();
			r.append(temp.generate(source.getPartParent().getName(), source,
					state.getImports()));
			state.getImports().add("java.util.List");
			state.getImports().add("java.util.ArrayList");

		} else {
			EnumValueAttribute temp = new EnumValueAttribute();
			r.append(temp.generate(source.getPartParent().getName(), source,
					state.getImports()));
			state.getImports().add("fr.imag.adele.cadse.core.util.Convert");
		}
		state.getImports().add("fr.imag.adele.cadse.core.Item");
		state.getImports().add("fr.imag.adele.cadse.core.CadseException");
	}

}
