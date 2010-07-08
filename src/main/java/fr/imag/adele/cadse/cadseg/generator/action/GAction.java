package fr.imag.adele.cadse.cadseg.generator.action;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.objectadapter.ObjectAdapter;

public class GAction extends ObjectAdapter<GAction> {

	@Override
	public Class<GAction> getClassAdapt() {
		return GAction.class;
	}

	public void generate(JavaFileContentManager jf, ContextVariable cxt) {
	}

}
