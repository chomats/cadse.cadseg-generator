/**
 * The Class MyContentItem.
 */
public class IC_AbstractTreeDialogForList_Browser_ComboContentItem extends InteractionControllerManager.InteractionControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	protected MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		DisplayManager.addAttributeInCall(getOwnerItem(), 
				CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_, true, "???", sb);
		DisplayManager.addAttributeInCall(getOwnerItem(), 
				CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_, true, "???", sb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append(" String title, String message,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append(" title, message,");

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

