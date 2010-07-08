package fr.imag.adele.cadse.cadseg.generator.content;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
	 * The Class ContentManager.
	 */
	public class GSourceFolderContent extends GFolderContent {

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#getResourceKindsName()
		 */
		@Override
		protected StringAttributeType[] getResourceKindsName() {
			return new StringAttributeType[] { CadseGCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH_, CadseGCST.SOURCE_FOLDER_CONTENT_MODEL_at_OUTPUT_PATH_  };
		}

		@Override
		protected String getDefaultValue(StringAttributeType strKinds, String value) {
			if (strKinds == CadseGCST.SOURCE_FOLDER_CONTENT_MODEL_at_OUTPUT_PATH_) {
				if (value != null && value.length() == 0) {
					return null;
				}
				return value;
			}
			return super.getDefaultValue(strKinds, value);
		}
		
		@Override
		public boolean hasParentContent() {
			return true;
		}

		@Override
		public Class<? extends ContentItem> getRuntimeClassName() {
			return fede.workspace.eclipse.java.manager.JavaSourceFolderContentManager.class;
		}
	}