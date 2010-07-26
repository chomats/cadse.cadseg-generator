package fr.imag.adele.cadse.cadseg.generator.content;


import fr.imag.adele.cadse.core.content.ContentItem;

/**
	 * The Class ContentManager.
	 */
	public class GJavaProjectContent extends GProjectContent {

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ProjectContentModelManager#mustBeExtended()
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
			return fede.workspace.eclipse.java.manager.JavaProjectContentManager.class;
		}

	}