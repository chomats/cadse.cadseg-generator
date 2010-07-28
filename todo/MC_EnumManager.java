/**
 * The Class MyContentItem.
 */
public class MC_EnumManager extends ModelControllerManager.ModelControllerContent {

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
		Item field = getOwnerItem().getPartParent().getPartParent();

		Item enumattribute = FieldManager.getAttribute(field);

		Item enumtype = EnumManager.getEnumType(enumattribute);

		IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumtype);

		String defaultValue = AttributeManager.getDefaultValueAttribute(enumattribute);

		sb.append(javaenumtype.getElementName()).append(".class").append(", ")
				.append(javaenumtype.getElementName()).append(".").append(defaultValue).append(",");
		imports.add(javaenumtype.getFullyQualifiedName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("enumclass, defaulfvalue");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		Item field = getOwnerItem().getPartParent().getPartParent();

		Item enumattribute = FieldManager.getAttribute(field);

		Item enumtype = EnumManager.getEnumType(enumattribute);

		IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumtype);
		sb.append("Class<").append(javaenumtype.getElementName()).append(")> enumclass, ").append(
				javaenumtype.getElementName()).append(" defaulfvalue,");
	}
}

