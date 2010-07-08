package fr.imag.adele.cadse.cadseg.generator.content;

import fr.imag.adele.cadse.core.content.ContentItem;

public class GSubContent extends GContentType
{
	@Override
		public boolean hasParentContent() {
			return true;
		}
	
		@Override
		public Class<? extends ContentItem> getRuntimeClassName() {
			return fede.workspace.eclipse.content.SubFileContentManager.class;
		}
}

