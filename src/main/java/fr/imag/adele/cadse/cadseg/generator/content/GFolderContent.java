package fr.imag.adele.cadse.cadseg.generator.content;

import fede.workspace.eclipse.content.FolderContentManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
 * The Class ContentManager.
 */
public class GFolderContent extends GContentResource {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.content.ContentModelManager.MyContentItem
	 * #getResourceKindsName()
	 */
	@Override
	protected StringAttributeType[] getResourceKindsName() {
		return new StringAttributeType[] { CadseGCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH_ };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.content.ContentModelManager#mustBeExtended
	 * ()
	 */
	@Override
	public boolean mustBeExtended() {
		return false;
	}

	@Override
	public Class<? extends ContentItem> getRuntimeClassName() {
		return FolderContentManager.class;
	}
}