package fr.imag.adele.cadse.cadseg.generator.content;

import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.core.Item;

public class GJavaFileContent_MF extends IPDEContributor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Item currentItem, Set<String> imports) {
		imports.add("fede.workspace.eclipse.composition");
		imports.add("fede.workspace.eclipse.content");
		imports.add("org.eclipse.jdt.core");
	}
}