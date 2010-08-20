package fr.imag.adele.cadse.cadseg.generator.gclass;

import java.util.Collection;

import fr.imag.adele.cadse.as.generator.GenClassState;
import fr.imag.adele.cadse.cadseg.contents.PageContentManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldGenerateInfo;
import fr.imag.adele.cadse.core.Item;

public class PageState extends GenClassState {
	/** The id. */
	public String									id;

	/** The page. */
	public Item									page;

	/** The add internal short name. */
//	private boolean							addInternalShortName;

	/** The add internal attribute. */
	//private boolean							addInternalAttribute;

	/** The fields. */
	public Collection<FieldGenerateInfo>	fields;

	public Item							superPage;

	public PageContentManager				supercm;

	public boolean							heritage;

	public int								cas;
}