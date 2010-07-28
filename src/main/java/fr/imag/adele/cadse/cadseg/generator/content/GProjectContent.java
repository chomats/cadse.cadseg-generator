package fr.imag.adele.cadse.cadseg.generator.content;


import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
	 * The Class ContentManager.
	 */
	public class GProjectContent extends GContentType {


		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#getResourceKindsName()
		 */
		@Override
		protected StringAttributeType[] getResourceKindsName() {
			return new StringAttributeType[] { CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_ };
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
			return false;
		}

		@Override
		public Class<? extends ContentItem> getRuntimeClassName() {
			return fede.workspace.eclipse.content.ProjectContentManager.class;
		}

	}