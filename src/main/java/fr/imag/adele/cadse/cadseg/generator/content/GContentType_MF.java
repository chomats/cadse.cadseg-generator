package fr.imag.adele.cadse.cadseg.generator.content;

import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.core.Item;

public class GContentType_MF extends IPDEContributor {
	
	private GContentType _c;

	public GContentType_MF(GContentType c) {
		_c = c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Item currentItem, Set<String> imports) {
		Class<?> className = _c.getRuntimeClassName();
		if (className != null) {
			String packageName = className.getPackage().getName();
			imports.add(packageName);
		}
		imports.add("fr.imag.adele.cadse.core");
		imports.add("org.eclipse.ltk.core.refactoring");
		imports.add("fr.imag.adele.cadse.core.build");
		imports.add("fr.imag.adele.cadse.core.var");
		imports.add("fr.imag.adele.cadse.core.impl.var");
		imports.add("fr.imag.adele.cadse.core.impl");
		imports.add("fr.imag.adele.cadse.core.content");
		imports.add("fr.imag.adele.cadse.util");
	}
}