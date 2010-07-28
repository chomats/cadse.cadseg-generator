/**
 * The Class MyContentItem.
 */
public class DisplayContent extends SubFileContentManager implements IPDEContributor {

	/**
	 * Instantiates a new my content manager.
	 * 
	 * @param parent
	 *            the parent
	 * @param item
	 *            the item
	 */
	protected DisplayContent(UUID id) throws CadseException {
		super(id);
	}

	/**
	 * Compute local manifest imports.
	 * 
	 * @param item
	 *            the item
	 * @param imports
	 *            the imports
	 */
	public void computeLocalManifestImports(Item item, Set<String> imports) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage(java.util.Set)
	 */
	public void computeExportsPackage(Set<String> exports) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
	 */
	public void computeImportsPackage(Set<String> imports) {
		imports.add("fr.imag.adele.cadse.core.ui");
		String className = getClassName(getOwnerItem());
		if (className == null) {
			className = getDefaultClassName();
			if (className != null) {
				imports.add(JavaIdentifier.packageName(className));
			}
		}
		imports.add("fr.imag.adele.cadse.core");
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
			Item display = getOwnerItem();
			boolean extendsUI = DisplayManager.isExtendsUIAttribute(display);
			if (extendsUI) {
				Item field = display.getPartParent();

				String extendsClassName = defaultClassName;

				defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false,
						POSTFIXE_UI);
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
		if (kind.equals("in-field")) {
			generateParts(sb, type, kind, imports, context);

			Item display = getOwnerItem();
			Item field = display.getPartParent();
			Item attribute = CadseG_WLWCListener.tryToRecreateAttributeLink(field);
			if (attribute == null) {
				// erreur
				sb.append("/* ERROR not found attribut*/");
				// TODO register error in workspace error manager( create)
				return;
			}

			generateParts(sb, type, "field-init", imports, context);

			boolean extendsClass = DisplayManager.isExtendsUIAttribute(display);
			if (defaultQualifiedClassName != null) {
				imports.add(defaultQualifiedClassName);
			}
			imports.add("fr.imag.adele.cadse.core.ui.EPosLabel");

			if (extendsClass) {
				defaultClassName = JavaIdentifier.javaIdentifierFromString(field.getName(), true, false,
						POSTFIXE_UI);
			}

			sb.newline().append("return ");
			sb.append("new ").append(defaultClassName).append("(");
			generateCallArguments(sb, imports);

			sb.decrementLength();
			sb.append(");");
		}

	}

	/**
	 * Generate call arguments.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 */
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports) {

		Item display = getOwnerItem();
		Item field = display.getPartParent();
		Item attribute = CadseG_WLWCListener.tryToRecreateAttributeLink(field);
		if (attribute == null) {
			// erreur
			sb.append("/* ERROR not found attribut*/");
			// TODO register error in workspace error manager( create)
			return;
		}
		EPosLabel poslabel = FieldManager.getPositionAttribute(field);
		if (poslabel == null || EPosLabel.defaultpos == poslabel) {
			poslabel = getDefaultPosLabel();
		}
		String label = FieldManager.getLabelAttribute(field);
		if (label == null) {
			label = "???";
		}
		sb.append(GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, attribute, null, null,
				imports));
		if (AttributeManager.isLinkAttribute(attribute)) {
			sb.append(".getName()");
		}
		sb.append(",");
		sb.begin();
		sb.newline().appendStringValue_vir(label);
		sb.newline().append("EPosLabel.").append(poslabel.toString()).append(',');

		Item ic = getItemIC(display);
		Item mc = getItemMC(display);
		if (mc != null) {
			sb.append("mc, ");
		} else {
			sb.append("new MC_AttributesItem(), ");
			imports.add("fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem");
		}
		if (ic != null) {
			sb.append("ic,");
		} else {
			sb.append("null,");
		}
		sb.end();
	}

	/**
	 * Generate construstor arguments.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstrustorArguments(GenStringBuilder sb) {
		sb.append("key, label, poslabel, mc, ic");
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("String key, String label, EPosLabel poslabel, "
				+ "IModelController mc, IInteractionController ic,");
	}
}

