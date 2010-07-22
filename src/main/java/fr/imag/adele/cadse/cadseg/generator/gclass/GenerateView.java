package fr.imag.adele.cadse.cadseg.generator.gclass;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.cadseg.managers.view.model.ViewModel;
import fr.imag.adele.cadse.cadseg.template.ViewerSkeltonTemplate;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class GenerateView extends GGenFile {
	
	public GenerateView() {
		_key = new GToken("view");
	}
	
	@Override
	public String generate(GGenerator g, Item view, GenContext cxt) {
		ViewerSkeltonTemplate skeltonTemplate = new ViewerSkeltonTemplate();
		ViewModel vm = new ViewModel(ContextVariableImpl.DEFAULT, view);
		return skeltonTemplate.generate(vm);
	}

}
