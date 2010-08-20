package fr.imag.adele.cadse.cadseg.generator.gclass;

import fr.imag.adele.cadse.as.generator.GenClassState;
import fr.imag.adele.cadse.cadseg.contents.ManagerJavaFileContentManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class GenerateModel.
 */
public class GenManagerState extends GenClassState {

	/** The super class name. */
	public String superClassName;

	/** The item name. */
	public String itemName;

	/** The itemtype. */
	public Item itemtype;

	/** The manager. */
	public Item manager;

	/** The cm. */
	public ManagerJavaFileContentManager cm;

	public boolean overwriteClass;

	public Item getCadseDefinition() {
		return ItemTypeManager.getCadseDefinition(itemtype);
	}
}