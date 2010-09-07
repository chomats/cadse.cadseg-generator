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

import java.util.Set;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.ListAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;

public class GListAttribute extends GAttribute {
	@Override
	public ItemType getCadseRootType() {
		return CadseGCST.LIST;
	}
	
	@Override
	public String getJavaTypeConvertToMethod() {
		return "fr.imag.adele.cadse.core.util.Convert.toList";
	}
	
	public void generateAttributeRefCst(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports) {
		Class<?> cl = lw().getAttributeDefinitionTypeJava();
		if (cl != null) {
			ListAttributeType<?> listAttr = (ListAttributeType<?>) attribute;
			IAttributeType<?> eltAttr = listAttr.getSubAttributeType();
			GAttribute manager = eltAttr.getType().adapt(GAttribute.class);
			
			appendCST2(cxt, sb, absItemType, attribute, imports, 
					lw().getAttributeDefinitionTypeJava(), lw().getTypeJava(false));
		}
	}
}
