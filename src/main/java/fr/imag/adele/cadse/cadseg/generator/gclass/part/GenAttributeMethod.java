package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.template.ListOfValueAttribute;
import fr.imag.adele.cadse.cadseg.generator.template.ValueAttribute;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class GenAttributeMethod extends GGenPartFile {

	@Override
	public void generatePartFile(GResult r, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {

		Item cadseDefinition = currentItem.getPartParent(CadseGCST.CADSE_DEFINITION);
		GenerateJavaIdentifier.addImportCST(context, cadseDefinition, state.getImports());
		Item source = currentItem;
		if (AttributeManager.isIsListAttribute(source)) {
			ListOfValueAttribute temp = new ListOfValueAttribute();
			r.append(temp.generate(source.getPartParent().getName(), source,
					state.getImports()));
			state.addImports("java.util.List");
			state.addImports("java.util.ArrayList");

		} else {
			ValueAttribute temp = new ValueAttribute();
			r.append(temp.generate(source.getPartParent().getName(), source,
					state.getImports()));
		}
		((AttributeManager) currentItem.getType().getItemManager())
				.addJavaImport(state.getImports());
	}
	
	@Override
	public boolean match(GGenFile gf, GToken t, Item currentItem) {
		if (currentItem.getPartParent() != null &&
			currentItem.getPartParent().isInstanceOf(CadseGCST.TYPE_DEFINITION))
			return super.match(gf, t, currentItem);
		return false;
	}
	
	

}
