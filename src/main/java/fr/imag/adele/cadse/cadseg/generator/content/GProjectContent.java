package fr.imag.adele.cadse.cadseg.generator.content;

import java.util.Set;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
	 * The Class ContentManager.
	 */
	public class GProjectContent extends GContentType {


		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#getResourceKindsName()
		 */
		@Override
		protected StringAttributeType[] getResourceKindsName() {
			return new StringAttributeType[] { CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_ };
		}

		
//		@Override
//		public GResult generatePartFile(Item owner, GGenFile gf, GToken kind,
//			GenContext context, GGenerator gGenerator) {
//			GResult r = super.generatePartFile(owner, gf, kind, context, gGenerator);
//			gf.addImports("fede.workspace.eclipse.content.ProjectContentManager");
//			return r;
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
			imports.add("org.eclipse.ui.model");
			imports.add("fede.workspace.eclipse.content");

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
			return false;
		}

		@Override
		public Class<? extends ContentItem> getRuntimeClassName() {
			return fede.workspace.eclipse.content.ProjectContentManager.class;
		}

	}