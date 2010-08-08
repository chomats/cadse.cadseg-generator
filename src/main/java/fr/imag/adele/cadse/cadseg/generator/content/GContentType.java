package fr.imag.adele.cadse.cadseg.generator.content;


import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenClassState;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.cadseg.contents.actions.MenuActionContent;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.GCadseGenerator;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateVariable;
import fr.imag.adele.cadse.cadseg.managers.build.CompositeItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ContentItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class MyContentItem.
 */
public class GContentType extends GenerateClass<ContentSate>  {
	
	public static class GContentInit extends GGenPartFile {
		
		@Override
		public void generatePartFile(GResult r, Item currentItem, GGenFile gf,
				GToken kind, GenContext context, GGenerator gGenerator,
				GenState state) {
			if (kind.abs() == GCadseGenerator.INIT_METHOD) {
				Item manager = currentItem.getPartParent();

				Item itemtype = ManagerManager.getItemType(manager);
				Item cadseDefinition = itemtype.getPartParent(CadseGCST.CADSE_DEFINITION);
				
				String cn = GenerateJavaIdentifier.getContentClassName(context, itemtype);

				
				//CadseGCST.MENU_ACTION.setContentItemClass(MenuActionContent.class);
				r.newline().append(GenerateJavaIdentifier.cstItemType(context, itemtype));
				r.append(".setContentItemClass(").append(cn).append(".class);");
				state.getImports().add(GenerateJavaIdentifier.getContentPackageName(context, itemtype)+"."+cn);
				GenerateJavaIdentifier.addImportCST(context, cadseDefinition, state.getImports());
			}
		}
	}
	
	public static final GToken CONTENT_FILE = new GToken("content-file");
	
	
	public GContentType() {
		super(CONTENT_FILE);
	}

	public static final GToken CONTENT_CONSTRUCTOR = new GToken("createcontent-init");
	
	@Override
	protected ContentSate createState() {
		return new ContentSate();
	}
	
	@Override
	protected void init(ContentSate state, Item currentItem, GGenerator g,
			GenContext cxt) {
		super.init(state, currentItem, g, cxt);
		
		state.manager = currentItem.getPartParent();

		state.itemtype = ManagerManager.getItemType(state.manager);
		
		state.defaultQualifiedClassName = this.getRuntimeClassName();
		state.fClassName = GenerateJavaIdentifier.getContentClassName(cxt, state.itemtype);
		state._packageName = GenerateJavaIdentifier.getContentPackageName(cxt, state.itemtype);

		state.fExtendedClassName = state.defaultQualifiedClassName.getSimpleName();
		state.fExtendedPackageName = state.defaultQualifiedClassName.getPackage().getName();
		
		findSuperClassName(state, currentItem, cxt, state.itemtype);
	}
	
	@Override
	public boolean match(GToken kind) {
		return GCst.t_constructor == kind.abs() || GCst.t_inner_class == kind.abs() || super.match(kind);
	}
	
	@Override
	public void generatePartFile(GResult r, Item owner, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		
		if (GCst.t_inner_class == kind.abs()) {
			generateAllClassVariables(owner, r, gf, state);
		}
		if (GCst.t_constructor == kind.abs()) {
			
			//generateComposersParts(owner, r, gf, kind, null, gGenerator, state);

			//boolean extendsClass = mustBeExtended() | ContentItemTypeManager.isExtendsClass(owner);
			//if (extendsClass) {
			ContentSate stateContent = (ContentSate) state;
		
			r.newline();
			r.newline().append("/**");
			r.newline().append("	@generated");
			r.newline().append("*/");
			r.newline().append("public ").append(stateContent.fClassName).append("(UUID id) {");
			
			r.decrementLength();
			r.append(") throws CadseException {");
			
			/* 2 */r.begin();
			r.newline().append("super(id,");
			generateCallArguments(owner, r, gf, context, state);
			r.decrementLength();
			r.append(");");
			r.append(CONTENT_CONSTRUCTOR);
			r.end();
			r.newline().append("}");
			r.end();
			r.newline();
			r.newline().append("}");
			r.newline();
			state.addImports("fr.imag.adele.cadse.core.CadseException");
			//}
		}
//		if (GCst.t_method == kind.abs()) {
//			boolean extendsClass = mustBeExtended() | ContentItemTypeManager.isExtendsClass(owner);
//			
//			Item manager = owner.getPartParent();
//			Item itemtype = ManagerManager.getItemType(manager);
//
//			String className = (extendsClass ? GenerateJavaIdentifier.getContentClassName(context, itemtype) : defaultClassName);
//			String itemVar = JavaIdentifier.javaIdentifierFromString(itemtype.getName(), false, true, null);
//			r.newline();
//			r.newline().append("/**");
//			r.newline().append("	@generated");
//			r.newline().append("*/");
//			r.newline().append("@Override");
//			r.newline().append("public ContentItem createContentItem(UUID id, Item owerItem ").append(
//					") throws CadseException {");
//			/* 1 */r.begin();
//			GenContext newcontext = new GenContext(context);
//			newcontext.setAttribute("itemVar", itemVar);
//			
//			r.appendToken(CREATECONTENT_INIT, newcontext, state);
//			generateCallInit(r, gf, newcontext);
//			r.newline().append(className).append(" cm = new ").append(className).append("(");
//			/* 2 */r.begin();
//			r.newline().append("id,");
//			/* 3 */r.begin();
//			generateCallArguments(owner, r, gf, newcontext, state);
//			r.decrementLength();
//			/* 3 */r.end();
//
//			r.newline().append(");");
//			/* 2 */r.end();
//			r.newline().append("owerItem.setComposers(");
//			/* 2 */r.begin();
//			r.appendToken(COMPOSERS, newcontext, state);
//			
//			r.decrementLength();
//			/* 2 */r.end();
//			r.newline().append(");");
//			r.newline().append("owerItem.setExporters(");
//			/* 2 */r.begin();
//			r.appendToken(EXPORTERS, newcontext, state);
//			r.decrementLength();
//			/* 2 */r.end();
//			r.newline().append(");");
//			r.newline().append("return cm;");
//			/* 1 */r.end();
//			r.newline().append("}").newline();
//			state.addImports("fr.imag.adele.cadse.core.content.ContentItem");
//			state.addImports("java.util.UUID");
//			state.addImports("fr.imag.adele.cadse.core.Item");
//			state.addImports("fr.imag.adele.cadse.core.var.Variable");
//			state.addImports("fr.imag.adele.cadse.core.var.ContextVariable");
//			state.addImports("fr.imag.adele.cadse.core.CadseException");
//			state.addImports(defaultQualifiedClassName.getCanonicalName());
//
//		}
	}

	/**
	 * Must be extended.
	 * 
	 * @return true, if successful
	 */
	public boolean mustBeExtended() {
		return true;
	}
	
	
	/**
	 * Recherche la class du contentmanager et le manager provenant d'un
	 * super type...
	 * 
	 * @param extendsClassName
	 *            default valeur;
	 * @param itemtype
	 *            le type auquel on veut associer ce content manager.
	 * @param cxt
	 *            the cxt
	 * 
	 * @return le nom de la class ou la valeur par default.
	 */
	private void findSuperClassName(GenClassState state, Item owner, ContextVariable cxt, Item itemtype) {
		while (true) {
			Item superitemtype = ItemTypeManager.getSuperType(itemtype);
			if (superitemtype == null) {
				break;
			}
			Item superItemManager = ItemTypeManager.getManager(superitemtype);
			if (superItemManager == null) {
				Class<? extends ContentItem> clCt = ((ItemType)superitemtype).getContentItemClass();
				if (clCt == null) continue;
				state.fExtendedClassName = clCt.getSimpleName();
				state.fExtendedPackageName = clCt.getPackage().getName();
				return;
			}
			Item supercontentItem = ManagerManager.getContentModel(superItemManager);
			if (supercontentItem != null && supercontentItem.getType() == owner.getType()) {
				if (ContentItemTypeManager.isExtendsClass(supercontentItem)) {
					state.fExtendedClassName = GenerateJavaIdentifier.getContentClassName(cxt, superitemtype);
					state.fExtendedPackageName = GenerateJavaIdentifier.getContentPackageName(cxt, superitemtype);

					return;
				}
			}
			itemtype = superitemtype;

		}
		return ;
	}

	/**
	 * Generate all class variables.
	 * 
	 * @param r
	 *            the r
	 * @param gf
	 *            the imports
	 * @param state 
	 */
	protected void generateAllClassVariables(Item owner, GResult r, GGenFile gf, GenState state) {
		StringAttributeType[] kinds = getResourceKindsName();

		if (kinds == null) {
			return;
		} else {
			for (int i = 0; i < kinds.length; i++) {
				StringAttributeType strKinds = kinds[i];
				String value = owner.getAttribute(strKinds);

				value = getDefaultValue(strKinds, value);
				if (value == null || value.length() ==0) {
					continue;
				}
				GenerateVariable.generateClassVariable(owner,
						GenerateVariable.getClassVariable(strKinds.getName(), true), value, r, gf, state);
			}
		}
	}

	protected String getDefaultValue(StringAttributeType strKinds, String value) {
		return value;
	}

	/**
	 * Gets the resource kinds name.
	 * 
	 * @return the resource kinds name
	 */
	protected StringAttributeType[] getResourceKindsName() {
		return null;
	}

	/**
	 * Generate call init.
	 * 
	 * @param r
	 *            the r
	 * @param imports
	 *            the imports
	 * @param newcontext
	 *            the newcontext
	 */
	protected void generateCallInit(GResult r, GGenFile gf, GenContext newcontext) {

	}

	/**
	 * Generate composers parts.
	 * 
	 * @param item
	 *            the item
	 * @param r
	 *            the r
	 * @param type
	 *            the type
	 * @param kind
	 *            the kind
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 * @param state 
	 */
	public void generateComposersParts(Item item, GResult r, GGenFile	gf, GToken kind, GenContext context, GGenerator g, GenState state) {
		Item managerItem = item.getPartParent();
		Item itemtype = ManagerManager.getItemType(managerItem);
		Item compositeItem = CompositeItemTypeManager.getCompositeItemFromItemType(itemtype);
		if (compositeItem != null) {
			r.append(g.generate(CadseGCST.COMPOSITE_ITEM_TYPE_lt_COMPOSERS, compositeItem, gf, kind, context, state));
		}
	}

	/**
	 * Generate call arguments.
	 * 
	 * @param r
	 *            the r
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	protected void generateCallArguments(Item owner, GResult r, GGenFile gf, GenContext context, GenState state) {
		StringAttributeType[] kinds = getResourceKindsName();
		if (kinds == null) {
			return;
		} else {
			for (int i = 0; i < kinds.length; i++) {
				StringAttributeType strKinds = kinds[i];
				String value = owner.getAttribute(strKinds);
				value = getDefaultValue(strKinds, value);
				if (value == null || value.length() == 0) {
					r.append(" ").append("NullVariable.INSTANCE,");
					state.addImports("fr.imag.adele.cadse.core.impl.var.NullVariable");
				} else {
					r.append(" ").append(GenerateVariable.getClassVariable(strKinds.getName(), true)).append(".INSTANCE,");
				}
			}
		}
	}

//	/**
//	 * Generate construstor arguments.
//	 * 
//	 * @param r
//	 *            the r
//	 */
//	protected void generateConstrustorArguments(GResult r) {
//		r.append("id,");
//		StringAttributeType[] kinds = getResourceKindsName();
//		if (kinds == null) {
//			return;
//		} else {
//			for (int i = 0; i < kinds.length; i++) {
//				StringAttributeType strKinds = kinds[i];
//				r.append(" ").append(GenerateVariable.getClassVariable(strKinds.getName(), false)).append(",");
//			}
//		}
//	}
//
//	/**
//	 * Generate constructor parameter.
//	 * 
//	 * @param r
//	 *            the r
//	 */
//	protected void generateConstructorParameter(GResult r) {
//		
//		r.append("UUID id,");
//		StringAttributeType[] kinds = getResourceKindsName();
//
//		if (kinds == null) {
//			return;
//		} else {
//			for (int i = 0; i < kinds.length; i++) {
//				StringAttributeType strKinds = kinds[i];
//				r.append(" Variable ").append(GenerateVariable.getClassVariable(strKinds.getName(), false)).append(",");
//			}
//		}
//	}

	public boolean hasParentContent() {
		return false;
	}

	/**
	 * <meta-attribute key="title-create"> <value type="string" value="Extended
	 * Content manager"/> </meta-attribute> <meta-attribute
	 * key="must-be-extended"> <value type="boolean" value="true"/>
	 * </meta-attribute> <meta-attribute key="class-name"> <value type="string"
	 * value="fede.workspace.domain.ContentManager"/> </meta-attribute>
	 * 
	 * @return
	 */
	public Class<? extends ContentItem> getRuntimeClassName() {
		return fr.imag.adele.cadse.core.impl.ContentItemImpl.class;
	}
}