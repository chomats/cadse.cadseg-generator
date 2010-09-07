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
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class LinkAttributeOneTemplate
 {


  protected static String nl;
  public static synchronized LinkAttributeOneTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    LinkAttributeOneTemplate result = new LinkAttributeOneTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL;
  protected final String TEXT_2 = NL + "\t/**" + NL + "\t\tget a link '";
  protected final String TEXT_3 = "' from '";
  protected final String TEXT_4 = "' to '";
  protected final String TEXT_5 = "'." + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tstatic public Link ";
  protected final String TEXT_6 = "Link(Item ";
  protected final String TEXT_7 = ") {" + NL + "\t\treturn ";
  protected final String TEXT_8 = ".getOutgoingLink(";
  protected final String TEXT_9 = ");" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\tget all link destination '";
  protected final String TEXT_10 = "' from '";
  protected final String TEXT_11 = "' to '";
  protected final String TEXT_12 = "'." + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tstatic public Item ";
  protected final String TEXT_13 = "All(Item ";
  protected final String TEXT_14 = ") {" + NL + "\t\treturn ";
  protected final String TEXT_15 = ".getOutgoingItem(";
  protected final String TEXT_16 = ", false);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\tget resolved link destination '";
  protected final String TEXT_17 = "' from '";
  protected final String TEXT_18 = "' to '";
  protected final String TEXT_19 = "'." + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tstatic public Item ";
  protected final String TEXT_20 = "(Item ";
  protected final String TEXT_21 = ") {" + NL + "\t\treturn ";
  protected final String TEXT_22 = ".getOutgoingItem(";
  protected final String TEXT_23 = ", true);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\tset a link '";
  protected final String TEXT_24 = "' from '";
  protected final String TEXT_25 = "' to '";
  protected final String TEXT_26 = "'." + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tstatic public void ";
  protected final String TEXT_27 = "(Item ";
  protected final String TEXT_28 = ", Item value) throws CadseException {" + NL + "\t\t";
  protected final String TEXT_29 = ".setOutgoingItem(";
  protected final String TEXT_30 = ",value);" + NL + "\t}";

/* (non-javadoc)
    * @see IGenerator#generate(Object)
    */
	public String generate(String shortName, Item attribute, Set<String> imports)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
      String min_short_name = JavaIdentifier.javaIdentifierFromString(shortName,false,true,null);
	String cst_linktype = GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, attribute, null, null, null);
	Item  dest = LinkTypeManager.getDestination(attribute);
	Item source = LinkTypeManager.getSource(attribute);
	
    stringBuffer.append(TEXT_2);
    stringBuffer.append(attribute.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append((source== null?"unknown":source.getName()));
    stringBuffer.append(TEXT_4);
    stringBuffer.append((dest== null?"unknown":dest.getName()));
    stringBuffer.append(TEXT_5);
    stringBuffer.append(GenerateJavaIdentifier.cstGetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cst_linktype);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(attribute.getName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append((source== null?"unknown":source.getName()));
    stringBuffer.append(TEXT_11);
    stringBuffer.append((dest== null?"unknown":dest.getName()));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(GenerateJavaIdentifier.cstGetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cst_linktype);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(attribute.getName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append((source== null?"unknown":source.getName()));
    stringBuffer.append(TEXT_18);
    stringBuffer.append((dest== null?"unknown":dest.getName()));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(GenerateJavaIdentifier.cstGetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cst_linktype);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(attribute.getName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append((source== null?"unknown":source.getName()));
    stringBuffer.append(TEXT_25);
    stringBuffer.append((dest== null?"unknown":dest.getName()));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(GenerateJavaIdentifier.cstSetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cst_linktype);
    stringBuffer.append(TEXT_30);
    return stringBuffer.toString();
  }
}
