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

import java.util.ArrayList;
import java.util.List;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.generator.template.EnumSkeltonTemplate;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

public class GenerateEnumType extends GGenFile<GenState> {

	public static final GToken FILE_ENUM_TYPE = new GToken("enum-type");

	public GenerateEnumType() {
		_key = FILE_ENUM_TYPE;
	}
	
	@Override
	public String generate(GGenerator g, Item enumType, GenContext cxt) {
		EnumTypeManager etm = (EnumTypeManager) enumType.getType().getItemManager();
		String packageName = etm.getPackage(cxt, enumType);
		String className = etm.getClassname(cxt, enumType);
		List<String> values = enumType.getAttribute(CadseGCST.ENUM_TYPE_at_VALUES_);
		if (values == null) {
			values = new ArrayList<String>();
		}
		EnumSkeltonTemplate temp = new EnumSkeltonTemplate();
		String content = temp.generate(packageName, className, values);
		
		return content;
	}
}
