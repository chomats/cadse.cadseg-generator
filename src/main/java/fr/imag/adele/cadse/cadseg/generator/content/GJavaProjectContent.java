package fr.imag.adele.cadse.cadseg.generator.content;

import java.util.Set;

import fr.imag.adele.cadse.core.content.ContentItem;

/**
	 * The Class ContentManager.
	 */
	public class GJavaProjectContent extends GProjectContent {

//		/*
//		 * (non-Javadoc)
//		 * 
//		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
//		 */
//		@Override
//		protected void generateConstructorParameter(GenStringBuilder sb) {
//			super.generateConstructorParameter(sb);
//			sb.append("Variable sourceFolder,");
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
//			sb.append("sourceFolder,");
//		}
//
//		/*
//		 * (non-Javadoc)
//		 * 
//		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateCallArguments(fr.imag.adele.cadse.core.GenStringBuilder,
//		 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
//		 */
//		@Override
//		protected void generateCallArguments(Item owner, GenStringBuilder sb, Set<String> imports, GenContext context) {
//			super.generateCallArguments(owner, sb, imports, context);
//			if (owner.getAttributeWithDefaultValue(CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_,
//					true)) {
//				// la classe dans lequels on ecrit ce code peut s'appeler
//				// JavaProjectManager.
//				// TODO: savoir si cela est le cas et ecrire ce code
//				// sinon ecrire l'ancien code (svn : 10839)
//				sb.append("fede.workspace.eclipse.java.JavaProjectManager.DEFAULT_SOURCES_FOLDER_NAME,");
//				// imports.add("fede.workspace.eclipse.java.JavaProjectManager");
//			} else {
//				sb.append("NullVariable.INSTANCE,");
//				imports.add("fr.imag.adele.cadse.core.impl.var.NullVariable");
//			}
//		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ProjectContentModelManager.ContentManager#computeImportsPackage(java.util.Set)
		 */
		@Override
		public void computeImportsPackage(Set<String> imports) {
			super.computeImportsPackage(imports);
			imports.add("org.eclipse.ui.model");
			imports.add("fede.workspace.eclipse.java");
			imports.add("fr.imag.adele.cadse.core.var");
			imports.add("org.eclipse.jdt.core");

		}
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ProjectContentModelManager#mustBeExtended()
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
			return fede.workspace.eclipse.java.manager.JavaProjectContentManager.class;
		}

	}