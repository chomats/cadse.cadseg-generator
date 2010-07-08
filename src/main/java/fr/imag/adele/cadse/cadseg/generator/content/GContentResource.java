package fr.imag.adele.cadse.cadseg.generator.content;

import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.ContentItemImpl;

public class GContentResource extends GContentType {





	@Override
	public boolean hasParentContent() {
		return true;
	}

	@Override
	public Class<? extends ContentItem> getRuntimeClassName() {
		return ContentItemImpl.class;
	}
}
