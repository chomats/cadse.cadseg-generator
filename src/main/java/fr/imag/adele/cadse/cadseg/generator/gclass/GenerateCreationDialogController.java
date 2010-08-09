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
 */

package fr.imag.adele.cadse.cadseg.generator.gclass;

import java.util.Set;

import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.cadseg.ParseTemplate;
import fr.imag.adele.cadse.cadseg.exp.ParseException;
import fr.imag.adele.cadse.cadseg.exp.TokenMgrError;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class CreationDialogController.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class GenerateCreationDialogController extends GenerateClass<GenCDState> {

	private static GToken CD_Token = new GToken("cd");

	public GenerateCreationDialogController() {
		super(CD_Token );
	}

	@Override
	protected GenCDState createState() {
		return new GenCDState();
	}
	/**
	 * Instantiates a new creation dialog controller.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param automatic
	 *            the automatic
	 * @param generateShorName
	 *            the generate shor name
	 * @param dialog
	 *            the dialog
	 * @param itemtype
	 *            the itemtype
	 * @param javatype
	 *            the javatype
	 * @param pn
	 *            the pn
	 * @param cn
	 *            the cn
	 */
	
	@Override
	protected void init(GenCDState state, Item currentItem, GGenerator g,
			GenContext cxt) {
		super.init(state, currentItem, g, cxt);
		GenCDState cdstate = (GenCDState) state;
		cdstate._packageName = "";
		cdstate._className = "";
		cdstate._extendedClassName = "CreationAction";
		cdstate._extendedPackageName = "fr.imag.adele.cadse.core.impl.ui";
		cdstate._isClass = true;
//		cdstate.fAutomatic = automatic;
//		cdstate.fGenerateShortName = generateShorName;
//		cdstate.fItemType = itemtype;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateConstructor(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	protected void generateConstructor(GenStringBuilder sb, Set<String> imports, GenContext context, GenCDState state) {
		sb.newline().appendGeneratedTag();
		sb.newline().append("public ").append(state.getClassName()).append(
				"(Item parent, ItemType type, LinkType lt, final String defaultName) {");
		sb.newline().append("	super(parent, type, lt, defaultName);");
		sb.newline().append("}");

		sb.newline().appendGeneratedTag();
		sb.newline().append("public ").append(state.getClassName()).append("(Item parent, ItemType type, LinkType lt) {");
		sb.newline().append("	super(parent, type, lt);");
		sb.newline().append("}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateMethods(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	protected void generateMethods(GenStringBuilder sb, Set<String> imports, GenContext context, GenCDState state) {
		imports.add("fr.imag.adele.cadse.core.*");
		if (state.fAutomatic) {
			sb.newline();
			sb.length();
			// if (!(fGenerateShortName == null || fGenerateShortName.length()
			// == 0)) {
			// sb.appendGeneratedTag();
			// }
			sb.newline().appendGeneratedTag();
			sb.newline().append("@Override");
			sb.newline().append("protected String getFinishAutomaticShortName() {");
			if (state.fGenerateShortName == null || state.fGenerateShortName.length() == 0) {
				sb.newline().append("// remove generated tag");
				sb.newline().append("// begin-user-code");
				sb.newline().append("	// TODO ");
				sb.newline().append("	//return the automatic short name with getItem()");
				sb.newline().append("// end-user-code");

			} else {
				ParseTemplate pt = new ParseTemplate(state.fItemType, state.fGenerateShortName, null);
				try {
					pt.main();
					pt.build("getItem()", "sb", sb, imports, null, true, state.getPackageName());
				} catch (ParseException e) {
					sb.newline().append("//").append(e.getMessage());
				} catch (TokenMgrError e) {
					sb.newline().append("//").append(e.getMessage());
				}
			}
			sb.newline().append("}");
			sb.newline();
		}
	}

}