/**
 * The Class MyContentItem.
 */
public final class DBrowserManager extends DisplayManager.DisplayContent {

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
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("String key, String label, EPosLabel poslabel, "
				+ "IModelController mc, IInteractionControllerForBrowserOrCombo ic,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("key, label, poslabel, " + "mc, ic,");
	}

}
