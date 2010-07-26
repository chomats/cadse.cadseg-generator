package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.generator.template.LinkAttributeMultiTemplate;
import fr.imag.adele.cadse.cadseg.generator.template.LinkAttributeOneTemplate;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

public class GenLinkTypeMethod extends GGenPartFile {

	@Override
	public void generatePartFile(GResult r, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		Item source = currentItem;
		int max = LinkTypeManager.getMax(source);
		if (max == 1) {
			LinkAttributeOneTemplate temp = new LinkAttributeOneTemplate();
			r.append(temp.generate(source.getPartParent().getName(), source,
					state.getImports()));
		} else {
			LinkAttributeMultiTemplate temp = new LinkAttributeMultiTemplate();
			r.append(temp.generate(source.getPartParent().getName(), source,
					state.getImports()));
			state.getImports().add("java.util.Collection");
			state.getImports().add("java.util.List");
		}
		state.getImports().add("fr.imag.adele.cadse.core.Item");
		state.getImports().add("fr.imag.adele.cadse.core.Link");
		state.getImports().add("fr.imag.adele.cadse.core.CadseException");
	}
}
