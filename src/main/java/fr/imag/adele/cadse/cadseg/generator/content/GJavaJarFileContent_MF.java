package fr.imag.adele.cadse.cadseg.generator.content;

import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.core.Item;

public class GJavaJarFileContent_MF extends IPDEContributor {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Item currentItem, Set<String> imports) {
		imports.add("org.eclipse.ui.model");
		imports.add("org.eclipse.jdt.core");
	}
}