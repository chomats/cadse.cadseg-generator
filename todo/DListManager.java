/**
 * The Class MyContentItem.
 */
public final class DListManager extends DisplayManager.DisplayContent {

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
		sb
				.append("String key, String label, EPosLabel poslabel, "
						+ "IModelController mc, IInteractionControllerForList ic, boolean edit, boolean showFilter, boolean order, boolean update,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("key, label, poslabel, " + "mc, ic, edit, showFilter, order, update,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports) {
		super.generateCallArguments(sb, imports);
		sb.append(" ").append(isEditableButtonAttribute(getOwnerItem())).append(',');
		sb.append(" ").append(isShowFilterAttribute(getOwnerItem())).append(',');
		sb.append(" ").append(isOrderButtonAttribute(getOwnerItem())).append(',');
		sb.append(" ").append(isUpdateButtonAttribute(getOwnerItem())).append(',');

		imports.add("fede.workspace.model.manager.properties.IInteractionControllerForList");
		imports.add("fr.imag.adele.cadse.core.ui.IModelController");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("org.eclipse.jface.fieldassist");
		imports.add("org.eclipse.ui.dialogs");

	}

}

