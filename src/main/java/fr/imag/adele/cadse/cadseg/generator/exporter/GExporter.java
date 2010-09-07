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
package fr.imag.adele.cadse.cadseg.generator.exporter;

import java.util.List;
import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
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
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.GCadseGenerator;
import fr.imag.adele.cadse.cadseg.generator.content.ContentSate;
import fr.imag.adele.cadse.cadseg.generator.content.GContentType;
import fr.imag.adele.cadse.cadseg.managers.build.ComposerManager;
import fr.imag.adele.cadse.cadseg.managers.build.exporter.ExporterManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class ContentManager.
 */
public class GExporter extends GenerateClass<ContentSate> {
	
	public static final GToken EXPORTER_FILE = new GToken("exporter-file");

	public GExporter() {
		super(EXPORTER_FILE);
	}
	
	@Override
	protected void init(ContentSate state, Item currentItem, GGenerator g,
			GenContext cxt) {
		super.init(state, currentItem, g, cxt);
		state.manager = currentItem.getPartParent(CadseGCST.MANAGER);

		state.itemtype = ManagerManager.getItemType(state.manager);
		ExporterManager cm = (ExporterManager) currentItem.getType().getItemManager();
		
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
	
	public static class GExporter_MF extends IPDEContributor {
		/*
		 * (non-Javadoc)
		 * 
		 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
		 * computeImportsPackage(java.util.Set)
		 */
		public void computeImportsPackage(Item currentItem, Set<String> imports) {
			ExporterManager cm = (ExporterManager) currentItem.getType().getItemManager();
			Class<?> className = cm.getDefaultClassName();
			imports.add(className.getPackage().getName());
			imports.add("fr.imag.adele.cadse.core");
			imports.add("fr.imag.adele.cadse.core.build");
			imports.add("fr.imag.adele.cadse.core.content");
		}

	}

	@Override
	public void generatePartFile(GResult sb, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		
		ExporterManager cm = (ExporterManager) currentItem.getType().getItemManager();
		Set<String> imports = state.getImports();

		if (kind.abs() == GCst.t_constructor) {
			

				Item manager = currentItem.getPartParent();

				Item itemtype = ManagerManager.getItemType(manager);

				Item superitemtype = ItemTypeManager.getSuperType(itemtype);
				
				
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("public ").append(((GenClassState)state)._className)
						.append("(");
				generateConstructorParameter(sb);
				sb.decrementLength();
				sb.append(") {");
				sb.newline().append("	super(");
				generateConstrustorArguments(sb, currentItem);
				sb.decrementLength();
				sb.append(");");
				sb.newline().append("}");
				sb.end();
				
		}
		if (kind.abs() == GCst.t_method) {
				sb.newline().newline().append("@Override");
				sb
						.newline()
						.append(
								"public IExportedContent exportItem(IBuildingContext context,"+
			" IExporterTarget target, String exporterType, boolean fullExport) throws CadseException {");
				sb.newline().append("	// TODO Auto-generated method stub");
				sb.newline().append("	return null;");
				sb.newline().append("}");
				sb.newline();
				sb.newline().append("}");
				sb.newline();

				
				imports.add("fr.imag.adele.cadse.core.build.Exporter");
				imports
						.add("fr.imag.adele.cadse.core.build.IBuildingContext");
				imports
						.add("fr.imag.adele.cadse.core.build.IExportedContent");
				imports
						.add("fr.imag.adele.cadse.core.build.IExporterTarget");

				imports.add("fr.imag.adele.cadse.core.content.ContentItem");
		
				imports.add("fr.imag.adele.cadse.core.content.ContentItem");
				imports.add("fr.imag.adele.cadse.core.Item");
				imports.add("fr.imag.adele.cadse.core.CadseException");
				imports.add("fr.imag.adele.cadse.core.build.IExportedContent");
				imports.add("fr.imag.adele.cadse.core.build.Exporter");
		}
	}


	/**
	 * Generate construstor arguments.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstrustorArguments(GenStringBuilder sb, Item currentItem) {
		sb.append("contentItem,");
		List<String> types = ExporterManager.getTypesAttribute(currentItem);
		if (types != null) {
			for (String exporterType : types) {
				sb.append(' ').appendStringValue(exporterType).append(',');
			}
		}
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
		sb.append("Item ownerItem,");
	}

}