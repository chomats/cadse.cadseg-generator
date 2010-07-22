package fr.imag.adele.cadse.cadseg.generator;

import java.util.HashSet;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.cadseg.managers.IBuildManager;
import fr.imag.adele.cadse.cadseg.managers.build.CompositeItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemState;
import fr.imag.adele.cadse.core.WorkspaceListener;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.transaction.delta.ImmutableItemDelta;
import fr.imag.adele.cadse.core.transaction.delta.ImmutableWorkspaceDelta;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class CadseG_WorkspaceListener extends WorkspaceListener {

	public CadseG_WorkspaceListener() {
		setKind(ListenerKind.BUILD);
		CadseCore.getLogicalWorkspace().addListener(this, 0xFFFFF);
	}

	@Override
	public void workspaceChanged(ImmutableWorkspaceDelta wd) {
		HashSet<GGenFile> toRegenerate = new HashSet<GGenFile>();
		for (ImmutableItemDelta itemDelta : wd.getItems()) {
			Item item = itemDelta.getItem();
			if (item == null || item.getState() == ItemState.DELETED)
				continue;
			
			GGenFile[] gf =item.getType().adapts(GGenFile.class);
			if (gf != null) {
				for (GGenFile gGenFile : gf) {
					toRegenerate.add(gGenFile);
				}
			}
			GGenPartFile[] gpf = item.getType().adapts(GGenPartFile.class);
			if (gpf != null)
				for (GGenPartFile gGenPartFile : gpf) {
					add(toRegenerate, gGenPartFile);
				}
			computeToGenerate(toRegenerate, item);
			if (item.isInstanceOf(CadseGCST.ITEM_TYPE)) {
				if (itemDelta.hasModifiedAttribute(CadseGCST.ITEM_TYPE_at_MANAGER_CLASS_) ||
						itemDelta.hasModifiedAttribute(CadseGCST.ITEM_TYPE_at_MANAGER_CLASS_)) {
					for (Item subType : item.getOutgoingItems(CadseGCST.ITEM_TYPE_lt_SUB_TYPES, true)) {
						computeToGenerate(toRegenerate, subType);	
					}
				}
			}
		}

		for (Item item : toRegenerate) {
			final ContentItem contentItem = item.getContentItem();
			if (contentItem != null && (contentItem instanceof IGenerateContent)) {
				IGenerateContent cm = ((IGenerateContent) contentItem);
				cm.generate(ContextVariableImpl.DEFAULT);
			} else {
				if (item.getType().getItemManager() instanceof IBuildManager) {
					((IBuildManager) item.getType().getItemManager()).generate(ContextVariableImpl.DEFAULT, item);
				}
			}
		}
	}

	private void add(HashSet<GGenFile> toRegenerate, GGenPartFile gGenPartFile) {
		gGenPartFile.tokens();
	}

	private void addToGenerate(HashSet<Item> toRegenerate, Item manager) {
		if (manager != null && manager.getState() != ItemState.DELETED) {
			toRegenerate.add(manager);
		}
	}

	/**
	 * true find item to generate and stop cycle
	 * @param toRegenerate set of item to regenerate
	 * @param item the current item
	 * @return true if stop analyse
	 */
	boolean computeToGenerate(HashSet<Item> toRegenerate, Item item) {
		if (item == null) {
			return false;
		}
		if (item.getType() == CadseGCST.CADSE_DEFINITION) {
			addToGenerate(toRegenerate, item);
			return true;
		}
		
		if (item.getPartParent(CadseGCST.CADSE_DEFINITION) == null) 
			return false;
		
		if (item.getType() == CadseGCST.MANAGER) {
			addToGenerate(toRegenerate, item);
			addToGenerate(toRegenerate, item.getPartParent(CadseGCST.CADSE_DEFINITION));
			return true;
		}

		

		if (item.getType() == CadseGCST.DYNAMIC_ACTIONS) {
			addToGenerate(toRegenerate, item);
			addToGenerate(toRegenerate, item.getPartParent(CadseGCST.CADSE_DEFINITION));
			return true;
		}

		if (item.getType() == CadseGCST.MENU_ACTION) {
			addToGenerate(toRegenerate, item);
			addToGenerate(toRegenerate, item.getPartParent(CadseGCST.CADSE_DEFINITION));
			return true;
		}

		if (item.getType() == CadseGCST.COMPOSITE_ITEM_TYPE) {
			Item itemtype = CompositeItemTypeManager.getItemType(item);
			if (itemtype != null) {
				return computeToGenerate(toRegenerate, itemtype);
			}
			return true;
		}

		if (item.getType() == CadseGCST.PAGE) {
			addToGenerate(toRegenerate, item);
			addToGenerate(toRegenerate, item.getPartParent(CadseGCST.CADSE_DEFINITION));
			return true;
		}

		if (item.getType() == CadseGCST.ENUM_TYPE) {
			addToGenerate(toRegenerate, item);
			return true;
		}

		if (item.getType() == CadseGCST.EXT_ITEM_TYPE) {
			addToGenerate(toRegenerate, item);
			addToGenerate(toRegenerate, item.getPartParent(CadseGCST.CADSE_DEFINITION));
			return true;
		}

		if (item.isInstanceOf(CadseGCST.ATTRIBUTE)) {
			computeToGenerate(toRegenerate, item.getPartParent());
			addToGenerate(toRegenerate, item.getPartParent(CadseGCST.CADSE_DEFINITION));
			for (Item field : item.getIncomingItems(CadseGCST.FIELD_lt_ATTRIBUTE)) {
				computeToGenerate(toRegenerate, field);
			}
			return true;
		}

		if (item.getType() == CadseGCST.ITEM_TYPE) {
			Item managerItem = fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager.getManager(item);
			return computeToGenerate(toRegenerate, managerItem);
		}

		if (item.getType() == CadseGCST.VIEW) {
			addToGenerate(toRegenerate, item);
			return true;
		}

		while (item.getPartParent() != null) {
			item = item.getPartParent();
			if (computeToGenerate(toRegenerate, item)) {
				return true;
			}
		}
		return false;
	}

}
