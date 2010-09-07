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
package fr.imag.adele.cadse.cadseg.generator.template;

import fr.imag.adele.cadse.core.Item;
import java.util.Set;

import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.fede.workspace.as.initmodel.InitModelLoadAndWrite;

public class ValueClassAttribute
 {


  protected static String nl;
  public static synchronized ValueClassAttribute create(String lineSeparator)
  {
    nl = lineSeparator;
    ValueClassAttribute result = new ValueClassAttribute();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tpublic static final ";
  protected final String TEXT_3 = " ";
  protected final String TEXT_4 = "() {" + NL + "\t\treturn ";
  protected final String TEXT_5 = ".getAttribute(";
  protected final String TEXT_6 = "_);" + NL + "\t}";
  protected final String TEXT_7 = NL;

/* (non-javadoc)
    * @see IGenerator#generate(Object)
    */
	public String generate(String shortName, Item attribute, Set<String> imports)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	Item itemType = attribute.getPartParent();
	String cstAttribute = GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, attribute, null, null, null);
	String cstType = GenerateJavaIdentifier.cstQualifiedAttributeItemType(ContextVariableImpl.DEFAULT, itemType, null, imports);
	ItemType it = attribute.getType();

	String typeJava = null;
	InitModelLoadAndWrite manager = it.adapt(InitModelLoadAndWrite.class);

     typeJava = manager.getTypeJava(true).getSimpleName();
	 if (typeJava == null) typeJava = "Object";

    stringBuffer.append(TEXT_2);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(GenerateJavaIdentifier.cstGetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cstType);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
