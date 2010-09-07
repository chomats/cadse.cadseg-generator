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

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.attribute.StringAttributeType;
import fr.imag.adele.cadse.core.content.ContentItem;

/**
	 * The Class ContentManager.
	 */
	public class GSourceFolderContent extends GFolderContent {

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.workspace.workspace.managers.content.ContentModelManager.MyContentItem#getResourceKindsName()
		 */
		@Override
		protected StringAttributeType[] getResourceKindsName() {
			return new StringAttributeType[] { CadseGCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH_, CadseGCST.SOURCE_FOLDER_CONTENT_MODEL_at_OUTPUT_PATH_  };
		}

		@Override
		protected String getDefaultValue(StringAttributeType strKinds, String value) {
			if (strKinds == CadseGCST.SOURCE_FOLDER_CONTENT_MODEL_at_OUTPUT_PATH_) {
				if (value != null && value.length() == 0) {
					return null;
				}
				return value;
			}
			return super.getDefaultValue(strKinds, value);
		}
		
		@Override
		public boolean hasParentContent() {
			return true;
		}

		@Override
		public Class<? extends ContentItem> getRuntimeClassName() {
			return fede.workspace.eclipse.java.manager.JavaSourceFolderContentManager.class;
		}
	}