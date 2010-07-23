package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import java.util.UUID;

import fr.imag.adele.cadse.cadseg.generator.gclass.part.InteractionControllerContent;
import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;

public class IC_AbstractForCheckedCIF extends InteractionControllerCIF {

	/**
	 * @generated
	 */
	static public class IC_AbstractForCheckedContent extends InteractionControllerContent {

		/**
		 * @generated
		 */
		public IC_AbstractForCheckedContent(UUID id, InteractionControllerManager interactionControllerManager)
				throws CadseException {
			super(id, interactionControllerManager);
		}

	}

	public IC_AbstractForCheckedCIF(InteractionControllerManager interactionControllerManager) {
		super(interactionControllerManager);
	}

	@Override
	public ContentItem createContentItem(UUID id, Item owerItem) throws CadseException {
		return new IC_AbstractForCheckedContent(id, _manager);
	}

}
