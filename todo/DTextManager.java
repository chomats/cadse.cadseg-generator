/**
 * The Class MyContentItem.
 */
public final class DTextManager extends DisplayManager.DisplayContent {

	/** The Constant STYLE_ATTRIBUTE. */
	private static final String	STYLE_ATTRIBUTE	= "style_attribute";

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
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		imports.add("fr.imag.adele.cadse.core");
		imports.add("fede.workspace.model.manager.properties");
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
		Item display = getOwnerItem();
		sb.append_exp_vir(display, CadseGCST.DTEXT_at_VERTICAL_SPAN_, 1);
		sb.append_string_vir(display, CadseGCST.DTEXT_at_TOOL_TIP_);
		sb.append(Convert.toBoolean(display.getAttribute(CadseGCST.DTEXT_at_MULTI_LINE_), false)).append(",");
		sb.append(Convert.toBoolean(display.getAttribute(CadseGCST.DTEXT_at_NO_BORDER_), false)).append(",");
		sb.append(Convert.toBoolean(display.getAttribute(CadseGCST.DTEXT_at_WRAP_LINE_), false)).append(",");

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
						+ "IModelController mc, IInteractionControllerForBrowserOrCombo ic, int vspan, String tooltip, boolean multiLine, boolean wrapLine, boolean noBorder,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ui.DisplayManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("key, label, poslabel, " + "mc, ic, style, vspan, tooltip, multiLine, wrapLine, noBorder,");
	}

}

