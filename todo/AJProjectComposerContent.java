/**
 * The Class ContentManager.
 */
public class AJProjectComposerContent extends InteractionControllerContent {

	/**
	 * Instantiates a new content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 * @throws CadseException
	 */
	public AJProjectComposerContent(UUID id, InteractionControllerManager manager) throws CadseException {
		super(id, manager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.ComposerManager.ContentManager
	 * #
	 * generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder
	 * )
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append("Path skipWeaving,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.ComposerManager.ContentManager
	 * #
	 * generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder
	 * )
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append("skipWeaving,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.workspace.workspace.managers.build.ComposerManager.ContentManager
	 * #generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 * java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, GenContext context) {
		sb.append(getOwnerItem().getAttributeWithDefaultValue(CadseGCST.AJPROJECT_COMPOSER_at_SKIP_WEAVING_, false));
	}

}