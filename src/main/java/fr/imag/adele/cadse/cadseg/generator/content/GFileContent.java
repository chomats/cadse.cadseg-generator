package fr.imag.adele.cadse.cadseg.generator.content;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
 * The Class MyContentItem.
 */
public class GFileContent extends GContentResource {

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#getResourceKindsName()
	 */
	@Override
	protected StringAttributeType[] getResourceKindsName() {
		return new StringAttributeType[] { CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_ };
	}

	@Override
	public boolean mustBeExtended() {
		return false;
	}
	
	@Override
	public Class<? extends ContentItem> getRuntimeClassName() {
		return fede.workspace.eclipse.content.FileContentManager.class;
	}
	
	@Override
	public boolean hasParentContent() {
		return true;
	}

}