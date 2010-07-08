package fr.imag.adele.cadse.cadseg.generator.content;

import fr.imag.adele.cadse.core.content.ContentItem;

public class GPDEProjectContent extends GJavaProjectContent {
	@Override
		public boolean hasParentContent() {
			return false;
		}
	
		@Override
		public Class<? extends ContentItem> getRuntimeClassName() {
			return fede.workspace.eclipse.composition.java.EclipsePluginContentManger.class;
		}
}

