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

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;

public class GDoubleAttribute extends GAttribute {

	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.DOUBLE;
	}

	
	@Override
	public String getJavaTypeConvertToMethod() {
		return "fr.imag.adele.cadse.core.util.Convert.toDouble";
	}
	
	@Override
	protected String generatedDefaultValue() {
		return "-1.0";
	}

	@Override
	public String getJavaTypeDefaultValue(Item attribute) {
		final String v = super.getJavaTypeDefaultValue(attribute);
		if (v.indexOf('.') != -1) {
			return v;
		}
		if ("null".equals(v)) {
			return v;
		}
		return v + ".0";
	}

}
