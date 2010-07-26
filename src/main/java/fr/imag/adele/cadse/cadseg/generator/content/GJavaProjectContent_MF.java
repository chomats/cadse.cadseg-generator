package fr.imag.adele.cadse.cadseg.generator.content;

import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.core.Item;

public class GJavaProjectContent_MF extends IPDEContributor {
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ProjectContentModelManager.ContentManager#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Item item, Set<String> imports) {
		imports.add("org.eclipse.ui.model");
		imports.add("fede.workspace.eclipse.java");
		imports.add("fr.imag.adele.cadse.core.var");
		imports.add("org.eclipse.jdt.core");

	}
}