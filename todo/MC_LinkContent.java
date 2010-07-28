/**
@generated
*/
public class MC_LinkContent extends ModelControllerManager.ModelControllerContent {

/**
	@generated
*/
public MC_LinkContent(UUID id) throws CadseException {
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

	Item a = ModelControllerManager.getAttribute(getOwnerItem());
	sb.append(Boolean.toString(LinkTypeManager.getMin(a) != 0)).append(", null,");
	sb.append(GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, a, null, null, imports))
			.append(",");
}

/*
 * (non-Javadoc)
 * 
 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
 */
@Override
protected void generateConstrustorArguments(GenStringBuilder sb) {
	sb.append("mandatory, msg, lt,");
}

/*
 * (non-Javadoc)
 * 
 * @see model.workspace.workspace.managers.mc.ModelControllerManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
 */
@Override
protected void generateConstructorParameter(GenStringBuilder sb) {
	sb.append("boolean mandatory, String msg, LinkType lt,");
}
}

