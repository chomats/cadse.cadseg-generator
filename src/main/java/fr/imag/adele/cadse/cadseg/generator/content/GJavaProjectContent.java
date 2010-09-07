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

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
	 * The Class ContentManager.
	 */
	public class GJavaProjectContent extends GProjectContent {

		@Override
		public Class<? extends ContentItem> getRuntimeClassName() {
			return fede.workspace.eclipse.java.manager.JavaProjectContentManager.class;
		}
		
//		/*
//		 * (non-Javadoc)
//		 * 
//		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateConstructorParameter(fr.imag.adele.cadse.core.GenStringBuilder)
//		 */
//		@Override
//		protected void generateConstructorParameter(GResult sb) {
//			super.generateConstructorParameter(sb);
//			sb.append("Variable sourceFolder,");
//		}
//
//		/*
//		 * (non-Javadoc)
//		 * 
//		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#generateConstrustorArguments(fr.imag.adele.cadse.core.GenStringBuilder)
//		 */
//		@Override
//		protected void generateConstrustorArguments(GResult sb) {
//			super.generateConstrustorArguments(sb);
//			sb.append("sourceFolder,");
//		}

		@Override
		protected void generateCallArguments(Item owner, GResult sb, GGenFile gf,
			GenContext context, GenState state) {
			super.generateCallArguments(owner, sb, gf, context, state);
	
			if (owner.getAttributeWithDefaultValue(CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_,
					true)) {
				sb.append("fede.workspace.eclipse.java.JavaProjectManager.DEFAULT_SOURCES_FOLDER_NAME,");
			} else {
				sb.append("NullVariable.INSTANCE,");
				state.getImports().add("fr.imag.adele.cadse.core.impl.var.NullVariable");
			}
		}

	}
