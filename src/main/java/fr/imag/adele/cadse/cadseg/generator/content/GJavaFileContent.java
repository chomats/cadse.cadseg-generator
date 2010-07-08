package fr.imag.adele.cadse.cadseg.generator.content;

import java.util.Set;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
 * The Class ContentManager.
 */
public class GJavaFileContent extends GFileContent {


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
		return fede.workspace.eclipse.java.manager.JavaFileContentManager.class;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#getResourceKindsName()
	 */
	@Override
	protected StringAttributeType[] getResourceKindsName() {
		return new StringAttributeType[] { CadseGCST.JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_,
				CadseGCST.JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_ };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("fede.workspace.eclipse.composition");
		imports.add("fede.workspace.eclipse.content");
		imports.add("org.eclipse.jdt.core");
	}

}