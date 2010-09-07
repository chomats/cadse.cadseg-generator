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
package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class GPDEIE_View extends IPDEContributor {
			
	
	/*
			 * (non-Javadoc)
			 * 
			 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage(java.util.Set)
			 */
			public void computeExportsPackage(Item currentItem, Set<String> exports) {
				JavaFileContentManager jf = (JavaFileContentManager) currentItem.getContentItem();
				exports.add(jf.getPackageName(ContextVariableImpl.DEFAULT));
			}
	
			/*
			 * (non-Javadoc)
			 * 
			 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
			 */
			public void computeImportsPackage(Item currentItem, Set<String> imports) {
				imports.add("org.eclipse.ui.part");
				imports.add("org.eclipse.core.commands.common");
				imports.add("org.eclipse.swt.widgets");
				imports.add("fr.imag.adele.cadse.eclipse.view");
				imports.add("fr.imag.adele.cadse.core");
				imports.add("org.eclipse.ui");
				imports.add("fede.plugin.workspace.filters");
			}
			
			
}

