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
package fr.imag.adele.cadse.cadseg.generator.attribute;

import fr.imag.adele.cadse.cadseg.managers.attributes.StringManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;

public class GStringAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.STRING;
	}

	@Override
	public int getCadseRootFlag(Item attribute) {
		return super.getCadseRootFlag(attribute) | (StringManager.isNotEmptyAttribute(attribute) ? StringAttributeType.NOT_EMPTY : 0);
	}

	@Override
	public String getJavaTypeDefaultValue(Item attribute) {
		String defaultValue = attribute.getAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
		if (defaultValue == null || defaultValue.length() == 0) {
			defaultValue = generatedDefaultValue();
		}

		if (defaultValue == null || defaultValue.length() == 0) {
			return "null";
		}
		if ("".equals(defaultValue)) {
			return defaultValue;
		}
		if (defaultValue.startsWith("\"") && defaultValue.endsWith("\"")) {
			if (defaultValue.length() == 1 || defaultValue.length() == 2) {
				defaultValue = "";
			}
			else {
				defaultValue = defaultValue.substring(1, defaultValue.length() - 1);
			}
		}
		defaultValue = defaultValue.replace("\"", "\\\"");
		return "\"" + defaultValue + "\"";
	}
	


}
