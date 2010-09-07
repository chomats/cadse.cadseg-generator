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

import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.eclipse.ParseTemplate;
import fr.imag.adele.cadse.cadseg.exp.ParseException;
import fr.imag.adele.cadse.cadseg.exp.TokenMgrError;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.Item;

public class GenerateVariable {

	/**
	 * Gets the class variable.
	 * 
	 * @param strKinds
	 *            the str kinds
	 * @param classname
	 *            the classname
	 * 
	 * @return the class variable
	 */
	public static String getClassVariable(String strKinds, boolean classname) {
		return JavaIdentifier.javaIdentifierFromString(strKinds, classname, true, "Variable");
	}

	/**
	 * Generate class variable.
	 * 
	 * @param variableClassName
	 *            the variable class name
	 * @param value
	 *            the value
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 */
	public static void generateClassVariable(Item item, String variableClassName, String value, GResult sb,
			GGenFile gf, GenState state) {
		state.addImports("fr.imag.adele.cadse.core.var.ContextVariable");
		state.addImports("fr.imag.adele.cadse.core.impl.var.VariableImpl");
		state.addImports("fr.imag.adele.cadse.core.var.Variable");
		state.addImports("fr.imag.adele.cadse.core.Item");
		
		
		sb.newline().appendGeneratedTag();
		sb.newline().append("static final class ").append(variableClassName).append(" extends VariableImpl {");
		sb.begin();
		sb.newline().appendGeneratedTag();
		sb.newline().append("public final static Variable INSTANCE = new ").append(variableClassName).append("();");
		sb.newline().appendGeneratedTag();
		sb.newline().append("public String compute(ContextVariable context, Item itemCurrent) {");
		if (value == null || value.length() == 0) {
			value = "";
		}
		sb.begin();
		Item itemtype = ManagerManager.getItemType(item.getPartParent());
		ParseTemplate pt = new ParseTemplate(itemtype, value, null);
		try {
			pt.main();
			pt.build("itemCurrent", "sb", sb, state.getImports(), null, true, true, null);
		} catch (ParseException e) {
		} catch (TokenMgrError e) {
		}

		sb.end();
		sb.newline().append("}");
		sb.end();
		sb.newline().append("}");
	}
}
