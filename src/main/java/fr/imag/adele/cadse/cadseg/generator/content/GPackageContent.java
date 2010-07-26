package fr.imag.adele.cadse.cadseg.generator.content;


import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
	 * The Class ContentManager.
	 */
	public class GPackageContent extends GFolderContent {

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