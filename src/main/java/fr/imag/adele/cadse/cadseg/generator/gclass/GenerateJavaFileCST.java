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

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenClassState;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.attribute.GAttribute;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class GenerateJavaFileCST.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class GenerateJavaFileCST extends GenerateClass<GenClassState> {

	public static GToken cstToken = new GToken("cadse-cst");

	public GenerateJavaFileCST() {
		super(cstToken );
	}

	@Override
	protected void init(GenClassState state, Item cadseDefinition, GGenerator g,
			GenContext cxt) {
		super.init(state, cadseDefinition, g, cxt);
		GenClassState gcs = (GenClassState) state;
		gcs._packageName = GenerateJavaIdentifier.javaPackageNameFileCST_FromCadseDefinition(cxt, cadseDefinition);
		gcs.fClassName = GenerateJavaIdentifier.javaClassNameFileCST_FromCadseDefinition(cxt, cadseDefinition);
		gcs.isClass = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.workspace.workspace.generate.GenerateClass#generateAttributes(fr.imag.adele.cadse.core.GenStringBuilder,
	 *      java.util.Set, fr.imag.adele.cadse.core.GenContext)
	 */
	protected void generateAttributes(GResult sb, Item cadseDefinition, Set<String> imports, GenContext context) {
		Item theDataModel = CadseDefinitionManager.getMainDataModel(cadseDefinition);
		Item[] itemTypes = ItemTypeManager.getAllItemType(theDataModel);
		Arrays.sort(itemTypes, new ItemShortNameComparator());
		imports.add("fr.imag.adele.cadse.core.ItemType");
		imports.add("fr.imag.adele.cadse.core.ExtendedType");
		imports.add("fr.imag.adele.cadse.core.LinkType");

		for (Item itemType : itemTypes) {
			sb.appendGeneratedTag();
			sb.newline().append("public static ");
			if (itemType.getType()== CadseGCST.EXT_ITEM_TYPE) {
				sb.append("ExtendedType ");;
			}
			else
				sb.append("ItemType ");
			sb.append(GenerateJavaIdentifier.cstItemType(context, itemType)).append(";");

			generateAttributesForItemTypeCST(sb, itemType, imports, context);
		}
	}

	/**
	 * Generate link cst.
	 * 
	 * @param sb
	 *            the sb
	 * @param absItemType
	 *            the abs item type
	 * @param cxt 
	 */
	private void generateAttributesForItemTypeCST(GResult sb, Item absItemType, Set<String> imports, ContextVariable cxt) {
		Collection<Item> outgoingItem = absItemType.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES,
				true);
		Item[] attributeItems = outgoingItem.toArray(new Item[outgoingItem.size()]);
		Arrays.sort(attributeItems, new ItemShortNameComparator());
		for (Item attribute : attributeItems) {
			if (!attribute.isResolved()) {
				continue;
			}

			sb.appendGeneratedTag();
			if (AttributeManager.isLinkAttribute(attribute)) {
				sb.newline().append("public static LinkType ").append(
						GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append(";");
			} else {
				sb.newline().append("public final static String ").append(
						GenerateJavaIdentifier.cstAttribute(cxt, attribute, absItemType)).append("=\"").append(
						attribute.getName()).append("\";");

				GAttribute manager = (GAttribute) attribute.getType().adapt(GAttribute.class);
				manager.generateAttributeRefCst(cxt, sb, absItemType, attribute, imports);
			}
		}
	}
	
	@Override
	public boolean match(GToken kind) {
		return kind.abs() == GCst.t_cstes || super.match(kind);
	}
	
	@Override
	public void generatePartFile(GResult g, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		super.generatePartFile(g, currentItem, gf, kind, context, gGenerator, state);
		if (kind.abs() == GCst.t_cstes) {
			generateAttributes(g, currentItem, state.getImports(), context);
		}
	}


}
