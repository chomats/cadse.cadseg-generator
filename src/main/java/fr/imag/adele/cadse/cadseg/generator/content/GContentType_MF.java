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
package fr.imag.adele.cadse.cadseg.generator.content;

import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemFilter;
import fr.imag.adele.cadse.core.impl.CadseCore;

public class GContentType_MF extends IPDEContributor {


	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
	 */
	@Override
	public void computeImportsPackage(Item currentItem, Set<String> imports) {
		GContentType ct = (GContentType) CadseCore.adapt(currentItem, GGenFile.class, new ItemFilter<GGenFile>() {
			@Override
			public boolean accept(GGenFile item) {
				return item instanceof GContentType;
			}

			@Override
			public boolean stop() {
				return false;
			}
		});
		
		if (ct != null) {
			Class<?> className = ct.getRuntimeClassName();
			if (className != null) {
				String packageName = className.getPackage().getName();
				imports.add(packageName);
			}
		}
		imports.add("fr.imag.adele.cadse.core");
		imports.add("org.eclipse.ltk.core.refactoring");
		imports.add("fr.imag.adele.cadse.core.build");
		imports.add("fr.imag.adele.cadse.core.var");
		imports.add("fr.imag.adele.cadse.core.impl.var");
		imports.add("fr.imag.adele.cadse.core.impl");
		imports.add("fr.imag.adele.cadse.core.content");
		imports.add("fr.imag.adele.cadse.util");
	}
}