package fr.imag.adele.cadse.cadseg.generator.content;

import java.util.Set;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
	 * The Class ContentManager.
	 */
	public class GPackageContent extends GFolderContent {



//		/*
//		 * (non-Javadoc)
//		 * 
//		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
//		 */
//		@Override
//		protected void generateConstructorParameter(GenStringBuilder sb) {
//			super.generateConstructorParameter(sb);
//			sb.append(" String packageName,");
//		}
//
//		/*
//		 * (non-Javadoc)
//		 * 
//		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
//		 */
//		@Override
//		protected void generateConstrustorArguments(GenStringBuilder sb) {
//			super.generateConstrustorArguments(sb);
//			sb.append(" packageName,");
//		}

//		/*
//		 * (non-Javadoc)
//		 * 
//		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateCallInit(fr.imag.adele.cadse.core.GenStringBuilder,
//		 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
//		 */
//		@Override
//		protected void generateCallInit(GenStringBuilder sb, Set<String> imports, GenContext newcontext) {
//			String itemVar = newcontext.getAttribute("itemVar");
//			String value = getOwnerItem().getAttribute(CadseGCST.PACKAGE_CONTENT_MODEL_at_PACKAGE_NAME_);
//			if (value == null || value.length() == 0) {
//				value = "${#short-name}";
//			}
//			Item itemtype = ManagerManager.getItemType(getOwnerItem().getPartParent());
//
//			ParseTemplate pt = new ParseTemplate(itemtype, value, null);
//			try {
//				pt.main();
//				pt.build(itemVar, "sbPackage", sb, imports, null);
//			} catch (ParseException e) {
//			}
//
//		}
		
		@Override
		protected StringAttributeType[] getResourceKindsName() {
			return new StringAttributeType[]{ CadseGCST.PACKAGE_CONTENT_MODEL_at_PACKAGE_NAME_ };
		}

//		/*
//		 * (non-Javadoc)
//		 * 
//		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
//		 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
//		 */
//		@Override
//		protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, GenContext context) {
//			sb.append("sbPackage.toString(),");
//		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#computeImportsPackage(java.util.Set)
		 */
		@Override
		public void computeImportsPackage(Set<String> imports) {
			super.computeImportsPackage(imports);
			imports.add("fede.workspace.eclipse.composition");
		}
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager#mustBeExtended()
		 */
		@Override
		public boolean mustBeExtended() {
			return false;
		}

		@Override
		public boolean hasParentContent() {
			return true;
		}

		@Override
		public Class<? extends ContentItem> getRuntimeClassName() {
			return fede.workspace.eclipse.java.manager.JavaPackageFolderContentManager.class;
		}

	}