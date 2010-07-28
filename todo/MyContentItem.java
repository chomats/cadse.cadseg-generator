
/**
 * The Class MyContentItem.
 */
public class MyContentItem extends ExporterManager.ExporterContent {

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
	 * @see
	 * model.workspace.workspace.managers.build.exporter.ExporterManager
	 * .ContentManager
	 * #generateConstrustorArguments(fr.imag.adele.cadse.core.
	 * GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("contentItem, type, path");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.exporter.ExporterManager
	 * .ContentManager
	 * #generateConstructorParameter(fr.imag.adele.cadse.core.
	 * GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("ContentItem contentItem, " + "String type, String path");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.exporter.ExporterManager
	 * .ContentManager
	 * #generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 * java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb,
			Set<String> imports, GenContext context) {
		List<String> type = getTypesAttribute(getOwnerItem());
		if (type != null && type.size() == 1) {
			sb.append(' ').appendStringValue(type.get(0)).append(',');
		} else {
			sb.append(' ').append("error").append(',');
		}
		String path = getPathAttribute(getOwnerItem());
		sb.append(' ').appendStringValue(path).append(',');
	}
}