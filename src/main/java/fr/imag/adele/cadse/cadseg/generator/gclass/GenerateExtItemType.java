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

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class GenerateExtItemType.
 * 
 * @author chomats
 */

public class GenerateExtItemType extends GenerateClass {

	/** The cim. */
	JavaFileContentManager	cim;

	/**
	 * Instantiates a new generate ext item type.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param cim
	 *            the cim
	 */
	public GenerateExtItemType(ContextVariable cxt, JavaFileContentManager cim) {
		super(cxt, true, cim.getPackageName(cxt), cim.getClassName(cxt), null, (String) null, cim.getJavaType(cxt),
				false);
		this.cim = cim;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateMethods(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	protected void generateMethods(GenStringBuilder sb, Set<String> imports, GenContext context) {
		// imports.add("fr.imag.adele.cadse.core.ItemType");
		// cim.generateParts(sb, "manager", "constructors", imports, context);
		cim.generateParts(sb, "manager", "methods", imports, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateAttributes(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	@Override
	protected void generateAttributes(GenStringBuilder sb, Set<String> imports, GenContext context) {
		cim.generateParts(sb, "manager", "inner-class", imports, context);
		cim.generateParts(sb, "manager", "cstes", imports, context);
		cim.generateParts(sb, "manager", "attributes", imports, context);
		Item cadsedef = cim.getOwnerItem().getPartParent(CadseGCST.CADSE_DEFINITION);
		GenerateJavaIdentifier.addImportCST(cxt, cadsedef, imports);

	}
}
