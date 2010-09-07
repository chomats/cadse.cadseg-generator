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

import fr.imag.adele.cadse.cadseg.managers.view.model.ViewItemTypeModel;
import fr.imag.adele.cadse.cadseg.managers.view.model.ViewLinkTypeModel;
import fr.imag.adele.cadse.cadseg.managers.view.model.ViewModel;
import fr.imag.adele.cadse.cadseg.util.Util;

public class ViewerSkeltonTemplate
{
  protected static String nl;
  public static synchronized ViewerSkeltonTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ViewerSkeltonTemplate result = new ViewerSkeltonTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL;
  protected final String TEXT_3 = NL + "import ";
  protected final String TEXT_4 = ";";
  protected final String TEXT_5 = NL + NL + "/**" + NL + "\t@generated" + NL + "*/" + NL + "class ";
  protected final String TEXT_6 = "UI extends AbstractCadseTreeViewUI {" + NL + "" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tpublic ";
  protected final String TEXT_7 = "UI(IViewSite site) {" + NL + "\t\tsuper(site);" + NL + "\t\tsetRecomputeChildren(true);" + NL + "\t\tinit();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tHashSet<ItemType> firstItemType = null;" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tHashSet<ItemType> refItemType = null;" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tHashSet<ItemType> allItemType = null;" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tHashSet<LinkType> agLinkType = null;" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tHashMap<LinkType, String> displayString = null;" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tHashSet<LinkType> createLinkType = null;" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tHashSet<LinkType> allLinkType = null;" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tprivate void init() {" + NL + "\t";
  protected final String TEXT_8 = NL + "\t\tif (";
  protected final String TEXT_9 = " == null) return;" + NL + "\t\t";
  protected final String TEXT_10 = NL + "\t\tallItemType = new HashSet<ItemType>();";
  protected final String TEXT_11 = NL + "\t\tif (";
  protected final String TEXT_12 = " != null) {" + NL + "\t\t\tallItemType.add(";
  protected final String TEXT_13 = ");" + NL + "\t\t\tallItemType.addAll(Arrays.asList(";
  protected final String TEXT_14 = ".getSubTypes()));" + NL + "\t\t}";
  protected final String TEXT_15 = NL + NL + "\t\tfirstItemType = new HashSet<ItemType>();" + NL + "\t\t";
  protected final String TEXT_16 = NL + "\t\tif (";
  protected final String TEXT_17 = " != null) {" + NL + "\t\t\tfirstItemType.add(";
  protected final String TEXT_18 = ");" + NL + "\t\t\tfirstItemType.addAll(Arrays.asList(";
  protected final String TEXT_19 = ".getSubTypes()));" + NL + "\t\t}";
  protected final String TEXT_20 = NL + NL + "\t\tagLinkType = new HashSet<LinkType>();" + NL + "\t\t";
  protected final String TEXT_21 = NL + "\t\tif (";
  protected final String TEXT_22 = " != null)" + NL + "\t\t\tagLinkType.add(";
  protected final String TEXT_23 = ");";
  protected final String TEXT_24 = NL + NL + "\t\tcreateLinkType = new HashSet<LinkType>();";
  protected final String TEXT_25 = NL + "\t\tif (";
  protected final String TEXT_26 = " != null)" + NL + "\t\t\tcreateLinkType.add(";
  protected final String TEXT_27 = ");";
  protected final String TEXT_28 = NL + NL + "\t\tallLinkType = new HashSet<LinkType>();";
  protected final String TEXT_29 = NL + "\t\tif (";
  protected final String TEXT_30 = " != null)" + NL + "\t\t\tallLinkType.add(";
  protected final String TEXT_31 = ");";
  protected final String TEXT_32 = NL + NL + "\t\trefItemType = new HashSet<ItemType>();";
  protected final String TEXT_33 = NL + "\t\tif (";
  protected final String TEXT_34 = " != null) {" + NL + "\t\t\trefItemType.add(";
  protected final String TEXT_35 = ");" + NL + "\t\t\trefItemType.addAll(Arrays.asList(";
  protected final String TEXT_36 = ".getSubTypes()));" + NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_37 = NL + "\t\tdisplayString = new HashMap<LinkType, String>();" + NL + "\t\t\t\t\t";
  protected final String TEXT_38 = NL + "\t\tif (";
  protected final String TEXT_39 = " != null)" + NL + "\t\t\tdisplayString.put(";
  protected final String TEXT_40 = ", \"";
  protected final String TEXT_41 = "\");";
  protected final String TEXT_42 = NL + "\t}" + NL + "" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic ItemType[] getFirstItemType(LogicalWorkspace model) {" + NL + "\t\tif (firstItemType == null)" + NL + "\t\t\treturn new ItemType[0];" + NL + "\t\treturn (ItemType[]) firstItemType.toArray(new ItemType[firstItemType.size()]);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic boolean isAggregationLink(Link link) {" + NL + "\t\tif (agLinkType == null) " + NL + "\t\t\treturn false;" + NL + "\t\treturn agLinkType.contains(link.getLinkType());" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic boolean isCreateLink(LinkType link) {" + NL + "\t\tif (createLinkType == null)" + NL + "\t\t\treturn false;" + NL + "\t\treturn createLinkType.contains(link);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic boolean canCreateLinkFrom(Item parentitem, LinkType link) {" + NL + "\t\treturn isCreateLink(link);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic String getDislplayCreate(LinkType link) {" + NL + "\t\tif (displayString == null) return null;" + NL + "" + NL + "\t\treturn displayString.get(link);" + NL + "\t}" + NL + "" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic boolean isFirstItemType(ItemType it, LogicalWorkspace cadseModel) {" + NL + "\t\tif (firstItemType == null)" + NL + "\t\t\treturn false;" + NL + "\t\treturn firstItemType.contains(it);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic boolean isRefItemType(TypeDefinition it, LogicalWorkspace cadseModel) {" + NL + "\t\tif (refItemType == null)" + NL + "\t\t\treturn false;" + NL + "\t\treturn refItemType.contains(it);" + NL + "\t}" + NL + "" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic boolean isItemType(TypeDefinition it, LogicalWorkspace cadseModel) {" + NL + "\t\tif (allItemType == null)" + NL + "\t\t\treturn false;" + NL + "\t\treturn allItemType.contains(it);" + NL + "\t}" + NL + "" + NL + "" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tprotected boolean isLink(Link link) {" + NL + "\t\tif (allLinkType == null)" + NL + "\t\t\treturn false;" + NL + "\t\treturn allLinkType.contains(link.getLinkType());" + NL + "\t}" + NL + "\t" + NL + "\t@Override" + NL + "\tprotected void loadView() {" + NL + "\t\tinit();" + NL + "\t}" + NL + "" + NL + "}" + NL + "" + NL + "/**" + NL + "\t@generated" + NL + "*/" + NL + "public class ";
  protected final String TEXT_43 = " extends ";
  protected final String TEXT_44 = " {" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tprotected AbstractCadseTreeViewUI createUIController(IViewSite site) {" + NL + "\t\treturn new ";
  protected final String TEXT_45 = "UI(site);" + NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_46 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    ViewModel cim = (ViewModel) argument;

 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cim.packageName );
    stringBuffer.append(TEXT_2);
    for(String i : cim.importsArray) { 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_4);
    }
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cim.className);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cim.className);
    stringBuffer.append(TEXT_7);
    for(ViewItemTypeModel citm : cim.getAllItems()) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(citm.REF_cst);
    stringBuffer.append(TEXT_9);
    
		break;
		}
    stringBuffer.append(TEXT_10);
    for(ViewItemTypeModel citm : cim.getAllItems()) {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(citm.REF_cst);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(citm.REF_cst);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(citm.REF_cst);
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    for(ViewItemTypeModel citm : cim.getFirstItems()) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(citm.REF_cst);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(citm.REF_cst);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(citm.REF_cst);
    stringBuffer.append(TEXT_19);
    }
    stringBuffer.append(TEXT_20);
    for(ViewItemTypeModel vitm: cim.getAllItems())  {
			for(ViewLinkTypeModel vltm: vitm.links) {
				if (!vltm.aggregation) continue; 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(vltm.REF_cst);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(vltm.REF_cst);
    stringBuffer.append(TEXT_23);
    }}
    stringBuffer.append(TEXT_24);
    for(ViewItemTypeModel vitm: cim.getAllItems())  {
			for(ViewLinkTypeModel vltm: vitm.links) {
				if (!vltm.canCreateLink) continue; 
    stringBuffer.append(TEXT_25);
    stringBuffer.append(vltm.REF_cst);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(vltm.REF_cst);
    stringBuffer.append(TEXT_27);
    }}
    stringBuffer.append(TEXT_28);
    for(ViewItemTypeModel vitm: cim.getAllItems())  {
			for(ViewLinkTypeModel vltm: vitm.links) { 
    stringBuffer.append(TEXT_29);
    stringBuffer.append(vltm.REF_cst);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(vltm.REF_cst);
    stringBuffer.append(TEXT_31);
    }}
    stringBuffer.append(TEXT_32);
    for(ViewItemTypeModel vitm: cim.getAllItems())  {
		if (!vitm.isRef) continue; 
    stringBuffer.append(TEXT_33);
    stringBuffer.append(vitm.REF_cst);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(vitm.REF_cst);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(vitm.REF_cst);
    stringBuffer.append(TEXT_36);
    }
		boolean createMap = true;
		for(ViewItemTypeModel vitm: cim.getAllItems())  {
			for(ViewLinkTypeModel vltm: vitm.links)  {
					if (vltm.displayCreate == null) continue;
					if (createMap) {
						createMap = false;
					
    stringBuffer.append(TEXT_37);
    }
					
    stringBuffer.append(TEXT_38);
    stringBuffer.append(vltm.REF_cst);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(vltm.REF_cst);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(Util.addEscapes(vltm.displayCreate));
    stringBuffer.append(TEXT_41);
    }}
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cim.className);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cim.superClassName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cim.className);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(TEXT_46);
    return stringBuffer.toString();
  }
}
