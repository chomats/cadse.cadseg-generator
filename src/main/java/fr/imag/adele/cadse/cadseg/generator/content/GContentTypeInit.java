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
package fr.imag.adele.cadse.cadseg.generator.content;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.GCadseGenerator;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

public class GContentTypeInit extends GGenPartFile {
	
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
			r.newline().append(
					GenerateJavaIdentifier.cstQualifiedAttributeItemType(context, itemtype, cadseDefinition, state.getImports()));
			r.append(".setContentItemClass(").append(cn).append(".class);");
			state.getImports().add(GenerateJavaIdentifier.getContentPackageName(context, itemtype)+"."+cn);
			GenerateJavaIdentifier.addImportCST(context, cadseDefinition, state.getImports());
		}
	}
}
