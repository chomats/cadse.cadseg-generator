package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import java.util.Set;
import java.util.UUID;

import fr.imag.adele.cadse.cadseg.generator.gclass.part.InteractionControllerContent;
import fr.imag.adele.cadse.cadseg.managers.ic.InteractionControllerManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class MyContentItem.
 */
public class IC_AbstractForListContent extends InteractionControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @param _manager
	 * @throws CadseException
	 */
	protected IC_AbstractForListContent(UUID id, InteractionControllerManager manager)
			throws CadseException {
		super(id, manager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		Item uc = getOwnerItem();
		sb.append_string_vir(uc, CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_);
		sb.append_string_vir(uc, CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("String title, String message,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("title, message,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("fede.workspace.model.manager.properties");
	}
}