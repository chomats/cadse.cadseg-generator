/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * 
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.cadseg.generator.composer;

import java.util.List;
import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenClassState;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.GCadseGenerator;
import fr.imag.adele.cadse.cadseg.generator.content.ContentSate;
import fr.imag.adele.cadse.cadseg.generator.content.GContentType;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.build.ComposerManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class ContentManager.
 * Must-be instanciate after the initialisation of GCadseGenerator.MANAGER
 */
public class GComposer extends GenerateClass<ContentSate> {

	public static final GToken COMPOSER_FILE = new GToken("composer-file");

	public GComposer() {
		super(COMPOSER_FILE);
	}
	
	static public class GComposer_MF extends IPDEContributor {
		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Item currentItem, Set<String> imports) {
			ComposerManager cm = (ComposerManager) currentItem.getType().getItemManager();
			Class<?> className = cm.getDefaultClassName();
			imports.add(className.getPackage().getName());
			imports.add("fr.imag.adele.cadse.core");
			if (cm.mustBeExtended()) {
				imports.add("org.eclipse.core.resources");
				imports.add("fede.workspace.eclipse.composition");
			}
		}
	}

	@Override
	protected void init(ContentSate state, Item currentItem, GGenerator g,
			GenContext cxt) {
		super.init(state, currentItem, g, cxt);
		state.manager = currentItem.getPartParent(CadseGCST.MANAGER);

		state.itemtype = ManagerManager.getItemType(state.manager);
		ComposerManager cm = (ComposerManager) currentItem.getType().getItemManager();
		
		state.defaultQualifiedClassName = cm.getDefaultClassName();
		state._className = GenerateJavaIdentifier.getContentClassName(cxt, state.itemtype);
		state._packageName = GenerateJavaIdentifier.getContentPackageName(cxt, state.itemtype);

		state._extendedClassName = state.defaultQualifiedClassName.getSimpleName();
		state._extendedPackageName = state.defaultQualifiedClassName.getPackage().getName();
	}
	
	@Override
	protected ContentSate createState() {
		return new ContentSate();
	}
	
	@Override
	public void generatePartFile(GResult sb, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		ComposerManager cm = (ComposerManager) currentItem.getType().getItemManager();
		Set<String> imports = state.getImports();
		
		if (GCst.t_constructor == kind.abs()) {
			imports.add("fr.imag.adele.cadse.core.Item");
			imports.add("fr.imag.adele.cadse.core.CadseException");
			imports.add("fr.imag.adele.cadse.core.build.IExporterTarget");
			
			sb.newline();
			
			sb.begin();
			sb.newline();
			sb.newline().append("/**");
			sb.newline().append("	@generated");
			sb.newline().append("*/");
			sb.newline().append("public ").append(((ContentSate)state)._className).append(" (");
			generateConstructorParameter(sb);
			sb.decrementLength();
			sb.append(") {");
			sb.newline().append("	super(");
			generateConstrustorArguments(sb, currentItem);
			sb.decrementLength();
			sb.append(");");
			sb.newline().append("}");
		}
		if (kind.abs() == GCst.t_method) {
			if (cm.generateGetTargetMethod()) {

				imports.add("fr.imag.adele.cadse.core.build.IExporterTarget");

				sb.newline();
				sb.newline().append("@Override");
				sb.newline().append("protected IExporterTarget getTarget() {");
				sb.newline().append("	// TODO Auto-generated method stub");
				sb.newline().append("	return null;");
				sb.newline().append("}");

			}

			sb.newline();
			sb.newline().append("@Override");
			sb
					.newline()
					.append(
							"protected void postCompose("
									+ "IBuildingContext context, List<IExportedContent> listExportedContent, IExporterTarget target) {");
			if (cm.callsuperPostCompose()) {
				sb.newline().append("	super.postCompose(context, listExportedContent, target);");
			}
			sb.newline().append("	// TODO Auto-generated method stub");
			sb.newline().append("}");

			imports.add("java.util.List");
			imports.add("fr.imag.adele.cadse.core.build.Composer");
			imports.add("fr.imag.adele.cadse.core.build.IBuildingContext");
			imports.add("fr.imag.adele.cadse.core.build.IExportedContent");
		}
	}
	


	/**
	 * Generate the arguments to call the super constructor of the composer
	 * when the composer class extends the super class.
	 * 
	 * @param sb
	 *            A String builder to put generated code.
	 */
	protected void generateConstrustorArguments(GResult sb, Item currentItem) {
		sb.newline().append("owerItem,");
		List<String> types = ComposerManager.getTypesAttribute(currentItem);
		if (types != null) {
			for (String exporterType : types) {
				sb.append(' ').appendStringValue(exporterType).append(',');
			}
		}
	}

	/**
	 * Generate the parameters of the extended constructor of the composer
	 * when the composer class extends the super class.
	 * 
	 * @param sb
	 *            A String builder to put generated code.
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("Item owerItem,");
	}


	
}