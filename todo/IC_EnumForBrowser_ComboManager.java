/**
 * The Class MyContentItem.
 */
public class IC_EnumForBrowser_ComboManager extends IC_AbstractForBrowser_ComboManager.MyContentItem {

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
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		super.generateCallArguments(sb, imports, object);
		Item uc = getOwnerItem();

		Item field = uc.getPartParent().getPartParent();

		Item enumattribute = FieldManager.getAttribute(field);

		Item enumtype = EnumManager.getEnumType(enumattribute);

		IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumtype);

		sb.append(javaenumtype.getElementName()).append(".class");
		sb.append(",");

		imports.add(javaenumtype.getFullyQualifiedName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append(" Class values,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_AbstractForBrowser_ComboManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append(" values,");
	}

}

///////////////////////////////////


