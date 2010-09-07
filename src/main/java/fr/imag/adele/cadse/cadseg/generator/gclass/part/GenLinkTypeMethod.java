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
package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.template.LinkAttributeMultiTemplate;
import fr.imag.adele.cadse.cadseg.generator.template.LinkAttributeOneTemplate;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

public class GenLinkTypeMethod extends GGenPartFile {

	@Override
	public void generatePartFile(GResult r, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		Item cadseDefinition = currentItem.getPartParent(CadseGCST.CADSE_DEFINITION);
		GenerateJavaIdentifier.addImportCST(context, cadseDefinition, state.getImports());
		
		Item source = currentItem;
		int max = LinkTypeManager.getMax(source);
		if (max == 1) {
			LinkAttributeOneTemplate temp = new LinkAttributeOneTemplate();
			r.append(temp.generate(source.getPartParent().getName(), source,
					state.getImports()));
		} else {
			LinkAttributeMultiTemplate temp = new LinkAttributeMultiTemplate();
			r.append(temp.generate(source.getPartParent().getName(), source,
					state.getImports()));
			state.getImports().add("java.util.Collection");
			state.getImports().add("java.util.List");
		}
		state.getImports().add("fr.imag.adele.cadse.core.Item");
		state.getImports().add("fr.imag.adele.cadse.core.Link");
		state.getImports().add("fr.imag.adele.cadse.core.CadseException");
	}
}
