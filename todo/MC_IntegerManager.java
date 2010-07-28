/**
 * The Class MyContentItem.
 */
public class MC_IntegerManager extends ModelControllerManager.ModelControllerContent {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	public MyContentItem(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		sb.append(getOwnerItem().getAttributeWithDefaultValue(CadseGCST.MC_INTEGER_at_MIN_, 0)).append(", ");
		String maxAttribute = Convert.toString(getOwnerItem().getAttributeWithDefaultValue(CadseGCST.MC_INTEGER_at_MAX_, 0));
		if (maxAttribute == null || maxAttribute.length() == 0)
			maxAttribute = "0";
		sb.append(maxAttribute).append(", ");
		String minError = getOwnerItem().getAttribute(CadseGCST.MC_INTEGER_at_ERROR_MSG_MIN_);
		String maxError = getOwnerItem().getAttribute(CadseGCST.MC_INTEGER_at_ERROR_MSG_MAX_);
		if (minError == null || minError.length() == 0)
			sb.append("null, ");
		else
			sb.appendStringValue(minError).append(", ");
		if (maxError == null || maxError.length() == 0)
			sb.append("null, ");
		else
			sb.appendStringValue(maxError).append(", ");
		sb.append(Convert.toInteger(getOwnerItem().getAttribute(CadseGCST.MC_INTEGER_at_DEFAULT_VALUE_)))
				.append(",");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("min, max, msg_min, msg_max, defaultValue");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("int min, int max, String msg_min, String msg_max, Integer defaultValue");
	}
}

