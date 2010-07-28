/**
 * The Class MyContentItem.
 */
public class IC_PartLinkForBrowser_Combo_ListManager extends IC_LinkForBrowser_Combo_ListManager.MyContentItem {

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
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, java.lang.Object)
	 */
	@Override
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		super.generateCallArguments(sb, imports, object);
		Item ic = getOwnerItem();

		Item a = FieldManager.getAttribute(ic.getPartParent().getPartParent());
		Item itemtypedest = LinkTypeManager.getDestination(a);

		Item[] incomingLinkType = ItemTypeManager.getIncomingLinkTypesOfPart(itemtypedest);

		if (incomingLinkType.length == 1 && incomingLinkType[0] != a) {
			Item partLinkTytpe = incomingLinkType[0];
			sb.append(
					GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, partLinkTytpe, null,
							null, imports)).append(",");
		} else {
			sb.append("null /*error cannot find incoming part from ").append(a.getName()).append("*/,");
		}
		DisplayManager.addAttributeInCall(getOwnerItem(), CadseGCST.IC_WITH_TITLE_FOR_DIALOG_at_SELECT_MESSAGE_,
				true, "??", sb);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstructorParameter(GenStringBuilder sb) {
		super.generateConstructorParameter(sb);
		sb.append(" LinkType partLinkType, String errormessage,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
	 */
	@Override
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		super.generateConstrustorArguments(sb);
		sb.append(" partLinkType, errormessage,");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.managers.ic.IC_LinkForBrowser_Combo_ListManager.MyContentItem#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Set<String> imports) {
		super.computeImportsPackage(imports);
	}
}
