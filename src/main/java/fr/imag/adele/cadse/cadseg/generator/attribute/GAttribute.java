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

import org.eclipse.jdt.core.IType;

import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.ListAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.objectadapter.ObjectAdapter;
import fr.imag.adele.fede.workspace.as.initmodel.InitModelLoadAndWrite;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CAttType;

public class GAttribute extends ObjectAdapter<GAttribute>  {

	public InitModelLoadAndWrite lw() {
		return getCadseRootType().adapt(InitModelLoadAndWrite.class);
	}
	
	public ItemType getCadseRootType() {
		return CadseGCST.ATTRIBUTE;
	}

	public void addCompleteAttributeDefinition(CAttType attType) {
	}

	public void generateAttributeRefCst(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports) {
		AttributeManager am = (AttributeManager) attribute.getType().getItemManager();
		Class<?> cl = lw().getAttributeDefinitionTypeJava();
		if (cl != null) {
			if (AttributeManager.isIsListAttribute(attribute)) {
				appendCST2(cxt, sb, absItemType, attribute, imports, ListAttributeType.class, lw().getTypeJava(false));
			}
			else {
				appendCST(cxt, sb, absItemType, attribute, imports, cl);
			}
		}
	}

	protected void appendCST(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports, Class<?> cl, IType... params) {
		sb.appendGeneratedTag();
		sb.newline().append("public static ");
		sb.append(cl.getSimpleName());
		if (cl.getTypeParameters().length != 0) {
			sb.append("<");
			if (params.length == 0) {
				sb.append("?");
			}
			else {
				for (int i = 0; i < params.length; i++) {
					IType p = params[i];
					if (i != 0) {
						sb.append(",");
					}
					sb.append(p.getElementName());
					imports.add(p.getFullyQualifiedName());
				}
			}
			sb.append(">");
		}

		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
		imports.add(cl.getName());
	}

	protected void appendCST(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports, Class<?> cl, Class<?> cl2, IType... params) {
		sb.appendGeneratedTag();
		sb.newline().append("public static ");
		sb.append(cl.getSimpleName());
		if (cl.getTypeParameters().length != 0) {
			sb.append("<");
			sb.append(cl2.getSimpleName());

			if (cl2.getTypeParameters().length != 0) {
				sb.append("<");
				if (params.length == 0) {
					sb.append("?");
				}
				else {
					for (int i = 0; i < params.length; i++) {
						IType p = params[i];
						if (i != 0) {
							sb.append(",");
						}
						sb.append(p.getElementName());
						imports.add(p.getFullyQualifiedName());
					}
				}
				sb.append(">");
			}

			sb.append(">");
		}

		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
		imports.add(cl.getName());
	}

	protected void appendCST2(ContextVariable cxt, GenStringBuilder sb, Item absItemType, Item attribute,
			Set<String> imports, Class<?> cl, Class<?>... params) {
		sb.appendGeneratedTag();
		sb.newline().append("public static ");
		sb.append(cl.getSimpleName());
		if (cl.getTypeParameters().length != 0) {
			sb.append("<");
			if (params.length == 0) {
				sb.append("?");
			}
			else {
				for (int i = 0; i < params.length; i++) {
					Class<?> p = params[i];
					if (i != 0) {
						sb.append(",");
					}
					sb.append(p.getSimpleName());
					imports.add(p.getName());
				}
			}
			sb.append(">");
		}

		sb.append(" ").append(GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("_;");
		imports.add(cl.getName());
	}

	

	public int getCadseRootFlag(Item attribute) {
		return (AttributeManager.isShowInDefaultCpAttribute(attribute) ? Item.SHOW_IN_DEFAULT_CP : 0)
				| (AttributeManager.isTransientAttribute(attribute) ? Item.TRANSIENT : 0)
				| (!AttributeManager.isCannotBeUndefinedAttribute(attribute) ? Item.CAN_BE_UNDEFINED : 0)
				| (AttributeManager.isShowInDefaultMpAttribute(attribute) ? Item.SHOW_IN_DEFAULT_MP : 0);
	}

	public Object getCadseRootAttributeValue(ContextVariable cxt, IAttributeType<?> attType, Item attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCadseRootRequireAttribute(Item attribute) {
		return AttributeManager.isRequireAttribute(attribute);
	}

	public String getJavaTypeDefaultValue(Item attribute) {
		String defaultValue = attribute.getAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
		if (defaultValue == null || defaultValue.length() == 0) {
			defaultValue = generatedDefaultValue();
		}

		if (defaultValue == null || defaultValue.length() == 0) {
			defaultValue = "null";
		}
		return defaultValue;
	}

	protected String generatedDefaultValue() {
		return null;
	}

	public String getJavaTypeConvertFromMethod() {
		return null;
	}

	public String getJavaTypeConvertToMethod() {
		return null;
	}

	public String exp_to_string() {
		return null;
	}

	@Override
	public Class<GAttribute> getClassAdapt() {
		return GAttribute.class;
	}
}
