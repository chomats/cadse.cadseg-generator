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
package fr.imag.adele.cadse.cadseg.generator.gclass;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.eclipse.ParseTemplate;
import fr.imag.adele.cadse.cadseg.exp.ParseException;
import fr.imag.adele.cadse.cadseg.exp.TokenMgrError;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

public class GManagerSpecialMethod extends GGenPartFile {

	
	public GManagerSpecialMethod(GToken t_method) {
		getMatchedToken().add(t_method);
	}
	
	@Override
	public void generatePartFile(GResult sb, Item manager, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		
		GenManagerState cim = (GenManagerState) state;
		String uniqueNameTemplate = ManagerManager
				.getUniqueNameTemplate(manager);
		if (uniqueNameTemplate != null && uniqueNameTemplate.length() != 0) {
			state.getImports().add("fr.imag.adele.cadse.core.LinkType");
			state.getImports().add("fr.imag.adele.cadse.core.Item");
			sb.newline();
			sb.newline().append("/**");
			sb.newline().append("	@generated");
			sb.newline().append("*/");
			sb.newline().append("@Override");
			sb.newline()
					.append("public String computeQualifiedName(Item item, String name, Item parent, LinkType lt) {");
			sb.begin();
			Item itemtype = cim.itemtype;
			ParseTemplate pt = new ParseTemplate(itemtype, uniqueNameTemplate,
					"name");
			try {
				pt.main();
				pt.build(sb, state.getImports(), true, cim.getPackageName());
			} catch (ParseException e) {
				sb.newline().append("//").append(e.getMessage());
			} catch (TokenMgrError e) {
				sb.newline().append("//").append(e.getMessage());
			}
			sb.end();
			sb.newline().append("}");
		}

		String displayTemplate = ManagerManager
				.getDisplayNameTemplateAttribute(manager);
		if (displayTemplate != null && displayTemplate.length() != 0) {
			sb.newline();
			state.getImports().add("fr.imag.adele.cadse.core.Item");
			sb.newline().append("/**");
			sb.newline().append("	@generated");
			sb.newline().append("*/");
			sb.newline().append("@Override");
			sb.newline().append("public String getDisplayName(Item item) {");
			sb.begin();
			Item itemtype = cim.itemtype;
			ParseTemplate pt = new ParseTemplate(itemtype, displayTemplate,
					null);
			try {
				pt.main();
				pt.build("item", "sb", sb, state.getImports(), null, true,
						cim.getPackageName());
			} catch (ParseException e) {
				sb.newline().append("//").append(e.getMessage());
			} catch (TokenMgrError e) {
				sb.newline().append("//").append(e.getMessage());
			}
			sb.end();
			sb.newline().append("}");
		}
	}
}
