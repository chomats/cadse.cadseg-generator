/**
 * The Class MyContentItem.
 */
public class ModelControllerContent extends SubFileContentManager implements IPDEContributor {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 */
	public ModelControllerContent(UUID id) throws CadseException {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.ContentManager#generate(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.lang.String, java.lang.String, java.util.Set,
	 *      fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	public void generate(GenStringBuilder sb, String type, String kind, Set<String> imports, GenContext context) {
		String defaultQualifiedClassName = getDefaultClassName();
		String defaultClassName = JavaIdentifier.getlastclassName(defaultQualifiedClassName);

		if ("inner-class".equals(kind)) {
			generateParts(sb, type, kind, imports, null);
			boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());
			if (extendsClass) {
				Item mc = getOwnerItem();
				Item field = mc.getPartParent().getPartParent();

				String extendsClassName = defaultClassName;

				defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false, "MC");
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("static public class ").append(defaultClassName).append(" extends ").append(
						extendsClassName).append(" {");
				sb.begin();
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("public ").append(defaultClassName).append("(");
				generateConstructorParameter(sb);
				sb.decrementLength();
				sb.append(") {");
				sb.newline().append("	super(");
				generateConstrustorArguments(sb);
				sb.decrementLength();
				sb.append(");");
				sb.newline().append("}");
				sb.end();
				sb.newline();
				sb.newline().append("}");
				sb.newline();
			}
		}
		if (kind.equals("field-init")) {
			boolean extendsClass = mustBeExtended() || isExtendsClass(getOwnerItem());
			if (extendsClass) {
				Item mc = getOwnerItem();
				Item field = mc.getPartParent().getPartParent();

				defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false, "MC");
			}

			sb.newline().append(defaultClassName).append(" mc = ");

			sb.append("new ").append(defaultClassName).append("(");
			generateCallArguments(sb, imports, null);
			sb.decrementLength();
			sb.append(");");

			imports.add(defaultQualifiedClassName);
		}

	}

	/**
	 * Generate call arguments.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param object
	 *            the object
	 */
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, Object object) {
		ModelControllerManager.this.generateCallArguments(getOwnerItem(), sb, imports, object);
	}

	/**
	 * Generate construstor arguments.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		ModelControllerManager.this.generateConstrustorArguments(getOwnerItem(), sb);
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
		ModelControllerManager.this.generateConstructorParameter(getOwnerItem(), sb);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
	 */
	public void computeImportsPackage(Set<String> imports) {
		String defaultQualifiedClassName = getDefaultClassName();
		String packageName = JavaIdentifier.packageName(defaultQualifiedClassName);
		imports.add(packageName);
	}

}
