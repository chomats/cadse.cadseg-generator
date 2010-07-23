package fr.imag.adele.cadse.cadseg.generator.gclass.part;


import java.util.UUID;

import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;

public class IC_AbstractForListCIF extends InteractionControllerCIF {
	public IC_AbstractForListCIF(InteractionControllerManager interactionControllerManager) {
		super(interactionControllerManager);
	}

	@Override
	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		return new IC_AbstractForListContent(id, _manager);
	}

}
