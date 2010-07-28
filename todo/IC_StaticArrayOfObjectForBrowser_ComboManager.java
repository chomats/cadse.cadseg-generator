/**
 * The Class MyContentItem.
 */
public class IC_StaticArrayOfObjectForBrowser_ComboManager extends IC_AbstractForBrowser_ComboManager.MyContentItem {

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
		super.generateCallArguments(sb, imports, object);
		Item ic = getOwnerItem();

		List<String> values = ic.getAttributeWithDefaultValue(CadseGCST.IC_STATIC_ARRAY_OF_OBJECT_FOR_BROWSER_COMBO_at_VALUES_,
				new ArrayList<String>());

		sb.append(" new Object[] {");
		sb.begin();
		for (String s : values) {
			sb.newline();
			sb.appendStringValue_vir(s);
		}
		sb.decrementLength();
		sb.append("}),");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append(" final Object[] values,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.InteractionControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append(" values,");
	}
}

