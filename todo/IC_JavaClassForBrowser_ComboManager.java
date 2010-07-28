/**
 * The Class MyContentItem.
 */
public class IC_JavaClassForBrowser_ComboManager extends InteractionControllerManager.InteractionControllerContent {

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
		DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_TITLE_,
				true, "??", sb);
		DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
				true, "??", sb);
		sb.append(" IJavaElementSearchConstants.");
		String style = getOwnerItem().getAttribute(CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_STYLE_);
		String stylecst = style_values_cst[0];
		if (style != null) {
			for (int i = 0; i < IC_JavaClassForBrowser_Combo.style_values.length; i++) {
				if (style.equals(IC_JavaClassForBrowser_Combo.style_values[i])) {
					stylecst = style_values_cst[i];
					break;
				}
			}
		}
		sb.append(stylecst).append(",");
		DisplayManager.addAttributeInCall(getOwnerItem(), 
				CadseGCST.IC_JAVA_CLASS_FOR_BROWSER_COMBO_at_FILTER_, true, "", sb);
		imports.add("org.eclipse.jdt.ui.IJavaElementSearchConstants");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append(" String title, String message, int style, String filter,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append(" title, message, style, filter,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
		imports.add("org.eclipse.jdt.ui");
	}

}

//String title, String message, LinkType linkType
