package fr.imag.adele.cadse.cadseg.generator.content;

import java.util.Set;

import fr.imag.adele.cadse.core.content.ContentItem;

/**
 * The Class ContentManager.
 */
public class GJavaJarFileContent extends GContentResource {

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("org.eclipse.ui.model");
		imports.add("org.eclipse.jdt.core");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager#mustBeExtended()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	@Override
	public boolean hasParentContent() {
		return true;
	}

	@Override
	public Class<? extends ContentItem> getRuntimeClassName() {
		return fede.workspace.eclipse.java.manager.JarContentManager.class;
	}

}