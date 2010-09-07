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

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenClassState;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

/**
 * The Class GenerateExtItemType.
 * 
 * @author chomats
 */

public class GenerateExtItemType extends GenerateClass<GenClassState> {

	public static final GToken EXT_MANAGER = new GToken("ext-manager");
	
	public GenerateExtItemType() {
		super(EXT_MANAGER);
	}
	
	@Override
	protected void init(GenClassState state, Item currentItem, GGenerator g,
			GenContext cxt) {
		super.init(state, currentItem, g, cxt);
		GenClassState gcs = (GenClassState) state;
		JavaFileContentManager cim = g.getJavaFileContentManager(EXT_MANAGER , currentItem);
		gcs._packageName = cim.getPackageName(cxt);
		gcs._isClass = true;
		gcs._type = cim.getJavaType(cxt);
		gcs._className = cim.getClassName(cxt);
		gcs._canOverwriteEtendsClass = false;
		
	}
	
	@Override
	public boolean match(GToken kind) {
		return kind.abs() == GCst.t_import || super.match(kind);
	}
	
	@Override
	public void generatePartFile(GResult g, Item currentItem, GGenFile gf, GToken kind,
			GenContext context, GGenerator gGenerator, GenState state) {
		if (kind.equals(GCst.t_import)) {
			Item cadsedef = currentItem.getPartParent(CadseGCST.CADSE_DEFINITION);
			g.appendLines(GenerateJavaIdentifier.getImportCST(context, cadsedef));
		}
		super.generatePartFile(g, currentItem, gf, kind, context, gGenerator,state);
	}
}

	