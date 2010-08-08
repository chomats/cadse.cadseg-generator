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

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.IAttributeGenerator;
import fr.imag.adele.cadse.cadseg.ItemShortNameComparator;
import fr.imag.adele.cadse.cadseg.ext.actions.ActionExtItemTypeExt;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.GGenInitClass;
import fr.imag.adele.cadse.cadseg.generator.attribute.GAttribute;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.actions.MenuAbstractManager;
import fr.imag.adele.cadse.cadseg.managers.actions.MenuManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ContentItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ContentLinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.content.MappingModelManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.enumdef.TWCommitKind;
import fr.imag.adele.cadse.core.enumdef.TWDestEvol;
import fr.imag.adele.cadse.core.enumdef.TWEvol;
import fr.imag.adele.cadse.core.enumdef.TWUpdateKind;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.attribute.AttributeType;
import fr.imag.adele.cadse.core.impl.attribute.ListAttributeType;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;
import fr.imag.adele.fede.workspace.as.initmodel.InitModelLoadAndWrite;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CAction;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CActionContributor;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CAttType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CCadse;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CCadseRef;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CExtBiding;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CExtensionItemType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CItem;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CItemType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CLink;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CLinkType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CMenuAction;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CTypeDefinition;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CommitKindType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.EvolutionDestinationKindType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.EvolutionKindType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ObjectFactory;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.UpdateKindType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.ValueTypeType;

/**
 * The Class WSModelGenerateModel.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class GenerateCadseDefinitionModel extends GGenFile<GenState> {

	static Map<ItemType, IAttributeGenerator>		generators		= new HashMap<ItemType, IAttributeGenerator>();

	static List<GenerateCadseDefinitionModelExt>	generatorsExt	= new ArrayList<GenerateCadseDefinitionModelExt>();

	static int intID =0;
	
	public GenerateCadseDefinitionModel() {
		_key = new GToken("cadse.xml");
	}
	
	@Override
	public String generate(GGenerator g, Item currentItem, GenContext cxt) {
		CCadse cadse = generateCADSE(currentItem, cxt);
		StringWriter writer = new StringWriter();

		try {
			JAXBContext jc = JAXBContext.newInstance("fr.imag.adele.fede.workspace.as.initmodel.jaxb", this.getClass()
					.getClassLoader());
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(cadse, writer);
			return writer.toString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Generate cadse.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param context 
	 * 
	 * @return the c cadse
	 */
	public CCadse generateCADSE(Item cadseDefinition, GenContext context) {
		intID =0;
		ObjectFactory factory = new ObjectFactory();
		ContextVariable cxt = new ContextVariableImpl();
		CCadse cadse = factory.createCCadse();
		cadse.setName(cadseDefinition.getQualifiedName());
		// ItemTypeManager
		int version = CadseDefinitionManager.getVersion(cadseDefinition);
		cadse.setVersion(version);
		cadse.setId(CadseDefinitionManager.getIdRuntime(cadseDefinition).toString());
		cadse.setIdCadseDefinition(cadseDefinition.getId().toString());
		cadse.setDescription(CadseDefinitionManager.getDescriptionAttribute(cadseDefinition));
		
		// init action
		IFile f = getGenerator().getFile(cadseDefinition, GGenInitClass.InitToken, context);
		if (f.exists()) {
			cadse.getInitClasses().add(GenerateJavaIdentifier.getInitPackageName(context, cadseDefinition)+"."+GenerateJavaIdentifier.getInitClassName(context, cadseDefinition));
		}
		String displayNameAttribute = CadseDefinitionManager.getCadseNameAttribute(cadseDefinition);
		if (displayNameAttribute != null && displayNameAttribute.length() > 0) {
			cadse.setDisplayName(displayNameAttribute);
		}			
		String classNameCST = GenerateJavaIdentifier.javaClassNameFileCST_FromCadseDefinition(cxt, cadseDefinition);
		String packageNameCST = GenerateJavaIdentifier.javaPackageNameFileCST_FromCadseDefinition(cxt, cadseDefinition);

		Collection<Item> extendsItems = CadseDefinitionManager.getExtends(cadseDefinition);
		for (Item item : extendsItems) {
			if (!item.isResolved()) {
				CadseRuntime[] cr = item.getLogicalWorkspace().getCadseRuntime();
				for (CadseRuntime cadseRuntime : cr) {
					if (cadseRuntime.getQualifiedName().equals(item.getQualifiedName())) 
						{
						item = cadseRuntime;
						break;
						}
				}
				if (item == null) continue;
			}
			
			CCadseRef cadseref = factory.createCCadseRef();
			cadseref.setName(item.getQualifiedName());
			cadseref.setId(CadseDefinitionManager.getIdRuntime(item).toString());
			cadseref.setIdCadseDefinition(CadseDefinitionManager.getIdDef(item).toString());
			cadse.getCadseRef().add(cadseref);
		}
		// create a sorted properties.
		Properties labelproperties = new Properties() {

			  @Override
			  public Set<Object> keySet()
			  {
			    return Collections.unmodifiableSet(new TreeSet<Object>(super.keySet()));
			  }

			};
			
		// load existing properties : keep this user changed in file.
		Properties owlabelproperties = new Properties();
		IProject p = cadseDefinition.getMainMappingContent(IProject.class);
		try {
			File labelPropertiesFile = p.getFile("model/labels.properties").getLocation().toFile();
			if (labelPropertiesFile.exists()) {
				owlabelproperties.load(	new FileReader(labelPropertiesFile));
			}
		} catch (IOException e) {
		}
		
		
		cadse.setCstClass(packageNameCST + "." + classNameCST);
		// name="" bundle-id="" cst-class="" id=""
		Item theDataModel = CadseDefinitionManager.getMainDataModel(cadseDefinition);
		Item[] itemTypes = ItemTypeManager.getAllItemType(theDataModel);
		Arrays.sort(itemTypes, new ItemShortNameComparator());
		for (Item itemType : itemTypes) {
			if (itemType.getType() == CadseGCST.EXT_ITEM_TYPE) continue;
			Item manager = ItemTypeManager.getManager(itemType);
			if (manager == null) {
				manager = tryToReconnectOrCreate(itemType);
				if (manager == null) {
					System.err.println("Generate cadse model : No manager found !! for " + itemType);
					continue;
				}
			}

			CItemType cit = factory.createCItemType();
			cit.setIntID(intID++);
			cadse.getItemType().add(cit);
			setItemType(cxt, (ItemType) itemType, manager, cit);
			generateCommonInformation(cxt, factory, cadse, itemType, cit, labelproperties, owlabelproperties);
		}

		Item[] extItemTypes = ItemTypeManager.getAllExtItemType(theDataModel);
		for (Item extIt : extItemTypes) {
			CExtensionItemType ceit = factory.createCExtensionItemType();
			cadse.getExtItemType().add(ceit);
			ceit.setName(extIt.getName());
			UUID ext_idRuntime = ItemTypeManager.getIdRuntime(extIt);
			ceit.setId(ext_idRuntime.toString());
			ceit.setPackageName(ItemTypeManager.getSubPackageFromCategory(extIt));
			ceit.setQualifiedName(ceit.getPackageName()+"."+extIt.getName());
			ceit.setCstName(GenerateJavaIdentifier.cstItemType(cxt, extIt));
			//ceit.setIntID(intID++);
			
			// TODO set the name and other information
			// ceit.setMetaType(value)
			// ceit.setSuperTypeName(value);
			Collection<Item> refItemTypes = extIt.getOutgoingItems(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE, true);
			for (Item refIt : refItemTypes) {
				if (refIt.isResolved()) {
					UUID uuid = ItemTypeManager.getIdRuntime((ItemType) refIt);
					CExtBiding binding = factory.createCExtBiding();
					binding.setUuidExt(ext_idRuntime.toString());
					binding.setUuidIt(uuid.toString());
					cadse.getExtBinding().add(binding);					
				}
			}

			generateCommonInformation(cxt, factory, cadse, extIt, ceit, labelproperties, owlabelproperties);
		}

		for (GenerateCadseDefinitionModelExt g : generatorsExt) {
			g.generate(cadse);
		}
		try {
			File labelPropertiesFile = p.getFile("model/labels.properties").getLocation().toFile();
			if (!labelPropertiesFile.exists()) {
				labelPropertiesFile.getParentFile().mkdirs();
				labelPropertiesFile.createNewFile();
			}
			labelproperties.store(
					new FileWriter(labelPropertiesFile), new Date().toString());
			 p.getFile("model/labels.properties").refreshLocal(0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cadse;
	}

	private static Item tryToReconnectOrCreate(Item itemType) {
		Item cadse = ItemTypeManager.getCadseDefinition(itemType);
		if (cadse != null) {
			Item mapping = CadseDefinitionManager.getMapping(cadse);
			Collection<Item> managers = MappingModelManager.getManagers(mapping);
			for (Item m : managers) {
				if (m.getName().equals(itemType.getName()+"-manager")) {
					Item it_m = ManagerManager.getItemType(m);
					if (it_m == null)
						try {
							CadseCore.createLinkIfNeed(m, itemType, CadseGCST.MANAGER_lt_ITEM_TYPE);
						} catch (CadseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					if (it_m != null && it_m != itemType) 
						continue;
					return m;
				}
			}
		}
		return null;
	}

	/**
	 * Generate page link action.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory
	 * @param abstractItemType
	 *            the abstract item type
	 * @param cit
	 *            the cit
	 */
	private static void generateCommonInformation(ContextVariable cxt, ObjectFactory factory, CCadse cadse, Item abstractItemType,
			CTypeDefinition cit, Properties labelproperties, Properties owlabelproperties) {
		// creation page
		//generateCreationDialog(cxt, factory, abstractItemType, cit);
		//generateModificationDialog(cxt, factory, abstractItemType, cit);
		generateAttributes(cxt, factory, cadse, abstractItemType, cit, labelproperties, owlabelproperties);

		Item menu = ActionExtItemTypeExt.getActionsModel(abstractItemType);
		if (menu != null) {
			generateMenu(cxt, factory, menu, cit, null);
		}
	}

	/**
	 * Generate attributes.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory pour creer des factory
	 * @param abstractItemType
	 *            un item type ou un extended item type
	 * @param cit
	 *            the cit
	 */
	private static void generateAttributes(ContextVariable cxt, ObjectFactory factory, CCadse cadse, 
			Item abstractItemType,
			CTypeDefinition cit, Properties labelproperties, Properties owlabelproperties) {
		Collection<Item> outgoingItem = abstractItemType.getOutgoingItems(
				CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES, true);
		Item[] attributeItems = outgoingItem.toArray(new Item[outgoingItem.size()]);
		Arrays.sort(attributeItems, new ItemShortNameComparator());
		for (Item attribute : attributeItems) {
			if (!attribute.isResolved()) {
				continue;
			}
			final String labelKey = "label."+abstractItemType.getName()+"."+attribute.getName();
			if (owlabelproperties.contains(labelKey))
				labelproperties.setProperty(labelKey, owlabelproperties.getProperty(labelKey));
			else
				labelproperties.setProperty(labelKey, attribute.getDisplayName());

			if (AttributeManager.isLinkAttribute(attribute)) {

				Item itemTypeDest = LinkTypeManager.getDestination(attribute);
				if (itemTypeDest == null) {
					continue;
				}

				CLinkType clt = generateOutgoingLink(cxt, factory, abstractItemType, cit, attribute, itemTypeDest);
				clt.setIntID(intID++);
				setLinkEvolutionInfo(clt, attribute);
				if (attribute.isInstanceOf(CadseGCST.CONTENT_LINK_TYPE)) {
					CItem c = addItem(factory, attribute);
					cadse.getItem().add(c);
					CLink l = factory.createCLink();
					l.setDestinationId(c.getId());
					l.setType(CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION.getId().toString());
					l.setIsReadonly(true);
					l.setIsHidden(true);
					l.setKey(CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION.getName());
					clt.getLink().add(l);
					
					l = factory.createCLink();
					l.setDestinationId(c.getId());
					l.setType(CadseGCST.ITEM_TYPE_lt_CONTENT_MODEL.getId().toString());
					l.setIsReadonly(true);
					l.setIsHidden(true);
					l.setKey(CadseGCST.ITEM_TYPE_lt_CONTENT_MODEL.getName());
					cit.getLink().add(l);
					
				}
			} else {
				CValuesType cvt;
				GAttribute manager = attribute.getType().adapt(GAttribute.class);
				ItemType cadseRootItemType = manager.getCadseRootType();
				if (cadseRootItemType != null) {
					if (cadseRootItemType.isAbstract()) {
						continue;
					}

					InitModelLoadAndWrite cadseRootManager = cadseRootItemType.adapt(InitModelLoadAndWrite.class);
					cvt = factory.createCValuesType();
					// attribute name
					cvt.setKey(attribute.getName());

					// attribute id
					cvt.setId(AttributeManager.getIdRuntime(attribute).toString());

					// add constance if the meta attributes, cst_class_name and
					// cst_package_name, are defined.
					cvt.setCstName(GenerateJavaIdentifier.cstAttribute(cxt, attribute, abstractItemType) + "_");

					// evolution information
					cvt.setTwEvolution(convert(AttributeManager.getTWEvolAttribute(attribute)));
					cvt.setTwCommit(convert(AttributeManager.getTWCommitKindAttribute(attribute)));
					cvt.setTwUpdate(convert(AttributeManager.getTWUpdateKindAttribute(attribute)));
					cvt.setTwRevSpecific(AttributeManager.isTWRevSpecificAttribute(attribute));

					// default value
					// add in type
					cit.getAttributeType().add(cvt);

					// cas de la list
					if (AttributeManager.isIsListAttribute(attribute)) {
						ItemType cadseRootList = CadseGCST.LIST;
						cvt.setTypeName(cadseRootList.getId().toString());
						InitModelLoadAndWrite cadseListRootManager = cadseRootList
								.adapt(InitModelLoadAndWrite.class);
						ListAttributeType lAttribute = new ListAttributeType(null, ((AttributeType) attribute).getFlag(), attribute.getName(), 0, -1, (IAttributeType) attribute);
						cadseListRootManager.writeAttributeDefinition(factory, cxt, cvt, lAttribute);
					}
					else {
						cvt.setValue(AttributeManager.getDefaultValueAttribute(attribute));
						cadseRootManager.writeAttributeDefinition(factory, cxt, cvt, attribute);
					}
					continue;
				}

				IAttributeGenerator gen = generators.get(attribute.getType());
				cvt = (gen != null) ? gen.attributeTypeToCValueType(cxt, factory, abstractItemType, attribute)
						: attributeToCValueType(cxt, factory, abstractItemType, attribute);

				cvt.setTwEvolution(convert(AttributeManager.getTWEvolAttribute(attribute)));
				cvt.setTwCommit(convert(AttributeManager.getTWCommitKindAttribute(attribute)));
				cvt.setTwUpdate(convert(AttributeManager.getTWUpdateKindAttribute(attribute)));
				cvt.setTwRevSpecific(AttributeManager.isTWRevSpecificAttribute(attribute));
				cvt.setValue(AttributeManager.getDefaultValueAttribute(attribute));
				//cvt.setIntID(intID++);
				cit.getAttributeType().add(cvt);

				if (cvt == null) {
					CAttType attType = factory.createCAttType();
					// attType.set
					if (cadseRootItemType == null || cadseRootItemType.isAbstract()) {
						return;
					}

					attType.setType(cadseRootItemType.getId().toString());
					attType.setTwEvolution(convert(AttributeManager.getTWEvolAttribute(attribute)));
					attType.setTwCommit(convert(AttributeManager.getTWCommitKindAttribute(attribute)));
					attType.setTwUpdate(convert(AttributeManager.getTWUpdateKindAttribute(attribute)));
					attType.setTwRevSpecific(AttributeManager.isTWRevSpecificAttribute(attribute));
					attType.setValue(AttributeManager.getDefaultValueAttribute(attribute));

					attType.setFlag(manager.getCadseRootFlag(attribute));
					attType.setShortName(attribute.getName());
					attType.setId(AttributeManager.getIdRuntime(attribute).toString());

					attType.setCstName(GenerateJavaIdentifier.cstAttribute(cxt, attribute, abstractItemType) + "_");
				//	attType.setIntID(intID++);
					
					manager.addCompleteAttributeDefinition(attType);

					cit.getAttributeDefinition().add(attType);
				}
			}
		}
	}

	private static CItem addItem(ObjectFactory factory, Item attribute) {
		CItem c = factory.createCItem();
		Item cItem = ContentLinkTypeManager.getContentDefinition(attribute);
		
		c.setId(ContentItemTypeManager.getIdRuntime(cItem).toString());
		c.setType(cItem.getType().getId().toString());
		IAttributeType<?>[] attributesItem = cItem.getLocalAllAttributeTypes();
		for (IAttributeType<?> attType : attributesItem) {
			if (attType == CadseGCST.ITEM_at_ID_) continue;
			
			if (attType instanceof LinkType) continue;
			Object v = cItem.getAttributeOwner(attType);
			if (v == null || isEmpty(v)) continue;
			CValuesType value = factory.createCValuesType();
			value.setId(attType.getId().toString());
			value.setValue(v.toString());
			value.setKey(attType.getName());
			c.getAttributesValue().add(value);
		}
		return c;
	}

	private static boolean isEmpty(Object v) {
		return v instanceof String && ((String)v).length() == 0;
	}

	private static void setLinkEvolutionInfo(CLinkType clt, Item attribute) {
		clt.setTwEvolution(convert(AttributeManager.getTWEvolAttribute(attribute)));
		clt.setTwCommit(convert(AttributeManager.getTWCommitKindAttribute(attribute)));
		clt.setTwUpdate(convert(AttributeManager.getTWUpdateKindAttribute(attribute)));
		clt.setTwRevSpecific(AttributeManager.isTWRevSpecificAttribute(attribute));
		clt.setTwEvolDestination(convert(LinkTypeManager.getTWDestEvolAttribute(attribute)));
		clt.setTwCoupled(LinkTypeManager.isTWCoupledAttribute(attribute));
	}

	private static UpdateKindType convert(TWUpdateKind updateKindAttribute) {
		if (updateKindAttribute == null) {
			return UpdateKindType.MERGE;
		}
		switch (updateKindAttribute) {
			case compute:
				return UpdateKindType.COMPUTE;
			case merge:
				return UpdateKindType.MERGE;
			case none:
				return UpdateKindType.NONE;

			default:
				break;
		}
		return UpdateKindType.MERGE;
	}

	private static EvolutionDestinationKindType convert(TWDestEvol destEvolAttribute) {
		if (destEvolAttribute == null) {
			return EvolutionDestinationKindType.IMMUTABLE;
		}
		switch (destEvolAttribute) {
			case branch:
				return EvolutionDestinationKindType.BRANCH;
			case effective:
				return EvolutionDestinationKindType.EFFECTIVE;
			case finalDest:
				return EvolutionDestinationKindType.FINAL;
			case immutable:
				return EvolutionDestinationKindType.IMMUTABLE;
			case mutable:
				return EvolutionDestinationKindType.MUTABLE;

			default:
				break;
		}
		return EvolutionDestinationKindType.MUTABLE;
	}

	private static CommitKindType convert(TWCommitKind commitKindAttribute) {
		if (commitKindAttribute == null) {
			return CommitKindType.CONFLICT;
		}
		switch (commitKindAttribute) {
			case conflict:
				return CommitKindType.CONFLICT;
			case none:
				return CommitKindType.NONE;
			case reconcile:
				return CommitKindType.RECONCILE;

			default:
				break;
		}
		return CommitKindType.CONFLICT;
	}

	private static EvolutionKindType convert(TWEvol evolAttribute) {
		if (evolAttribute == null) {
			return EvolutionKindType.IMMUTABLE;
		}
		switch (evolAttribute) {
			case twFinal:
				return EvolutionKindType.FINAL;
			case twImmutable:
				return EvolutionKindType.IMMUTABLE;
			case twMutable:
				return EvolutionKindType.MUTABLE;
			case twTransient:
				return EvolutionKindType.TRANSIENT;

			default:
				break;
		}
		return EvolutionKindType.IMMUTABLE;
	}

	/**
	 * Attribute to c value type.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory
	 * @param abstractItemType
	 *            the abstract item type
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the c values type
	 */
	private static CValuesType attributeToCValueType(ContextVariable cxt, ObjectFactory factory, Item abstractItemType,
			Item attribute) {
		CValuesType cvt = factory.createCValuesType();
		// On retrourne toujours cette objet. la cvt pointe sur la description �
		// remplir
		final CValuesType ret = cvt;

		cvt.setKey(attribute.getName());
		cvt.setId(AttributeManager.getIdRuntime(attribute).toString());

		// add constance if the meta attributes, cst_class_name and
		// cst_package_name, are defined.
		cvt.setCstName(GenerateJavaIdentifier.cstAttribute(cxt, attribute, abstractItemType) + "_");

		if (AttributeManager.isIsListAttribute(attribute)) {
			cvt.setType(ValueTypeType.LIST);
			cvt.setMax(-1);
			cvt.setMin(0);

			// creer une deuxième description pour dècrire le type du contenu de
			// la liste
			CValuesType ncvt = factory.createCValuesType();
			cvt.getElement().add(ncvt);

			cvt = ncvt;
		} else if (attribute.getType() == CadseGCST.SYMBOLIC_BIT_MAP) {
			cvt.setType(ValueTypeType.SYMBOLIC_BIT_MAP);
			cvt.setMin(AttributeManager.isRequireAttribute(attribute) ? 1 : 0);
			// cvt.setFlag(getFlag(attribute));
			CValuesType symbols = factory.createCValuesType();
			// symbols
			// cvt.getElement().add(symbols );
		}
		return ret;
	}

	/**
	 * Generate menu.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory
	 * @param menu
	 *            the menu
	 * @param cit
	 *            the cit
	 * @param pathAttribute
	 *            the path attribute
	 */
	private static void generateMenu(ContextVariable cxt, ObjectFactory factory, Item menu, CTypeDefinition cit,
			String pathAttribute) {
		Collection<Item> children = MenuManager.getChildren(menu);
		for (Item child : children) {
			if (child.getType() == CadseGCST.MENU) {
				CMenuAction cma = factory.createCMenuAction();
				cit.getMenu().add(cma);
				cma.setName(child.getName());
				cma.setLabel(MenuAbstractManager.getLabelAttribute(child));
				cma.setIcon(MenuAbstractManager.getIconPath(child));
				cma.setKind(0);
				String newpathAttribute = null;
				if (pathAttribute != null) {
					cma.setPath(pathAttribute);
					newpathAttribute = pathAttribute + "/" + menu.getName();
				} else {
					cma.setPath(MenuAbstractManager.getPathAttribute(child));
					newpathAttribute = child.getName();
				}
				generateMenu(cxt, factory, child, cit, newpathAttribute);
			} else if (child.getType() == CadseGCST.MENU_ACTION) {
				CAction ca = factory.createCAction();
				cit.getAction().add(ca);
				ca.setName(child.getName());
				ca.setLabel(MenuAbstractManager.getLabelAttribute(child));
				ca.setIcon(MenuAbstractManager.getIconPath(child));
				ca.setForNb(-1);
				if (pathAttribute != null) {
					ca.setPath(pathAttribute);
				} else {
					ca.setPath(MenuAbstractManager.getPathAttribute(child));
				}
				ca.setClassAction(GenerateJavaIdentifier.qualifiedMenuAction(cxt, child));
			} else if (child.getType() == CadseGCST.DYNAMIC_ACTIONS) {
				CActionContributor cac = factory.createCActionContributor();
				cit.getActionContributor().add(cac);
				JavaFileContentManager cm = (JavaFileContentManager) child.getContentItem();
				cac.setClazz(cm.getPackageName(cxt) + "." + cm.getClassName(cxt));
			}
		}
	}

	/**
	 * Sets the item type.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param itemType
	 *            the item type
	 * @param manager
	 *            the manager
	 * @param cit
	 *            the cit
	 */
	private static void setItemType(ContextVariable cxt, ItemType itemType, Item manager, CItemType cit) {
		cit.setId(ItemTypeManager.getIdRuntime(itemType).toString());
		cit.setIntID(ItemTypeManager.getIntID(itemType));
		cit.setName(itemType.getName());
		cit.setIsRootElement(ItemTypeManager.isIsRootElementAttribute(itemType));
		cit.setHasContent(ItemTypeManager.isHasContentAttribute(itemType));
		cit.setQualifiedName(ItemTypeManager.getQualifiedName(itemType));

		cit.setHidden(ItemTypeManager.isIsInstanceHiddenAttribute(itemType));
		cit.setType(ItemTypeManager.getMetaType(itemType));
		cit.setMetaType(ItemTypeManager.getMetaType(itemType));
		IJavaElement je = getItemFactoryType(itemType);
		cit.setFactoryClass((je != null && je instanceof IType) ? ((IType) je).getFullyQualifiedName() : null);
		cit.setPackageName(ItemTypeManager.getSubPackageFromCategory(itemType));

		Item superItemType = ItemTypeManager.getSuperType(itemType);
		if (superItemType != null) {
			cit.setSuperTypeName(ItemTypeManager.getIdRuntime((ItemType) superItemType).toString());
		} else {
			if (ItemTypeManager.isIsMetaItemTypeAttribute(itemType)) {
				cit.setSuperTypeName(CadseGCST.ITEM_TYPE.getId().toString());
			}
		}

		String humanTypeName = ManagerManager.getHumanNameAttribute(manager);
		if (humanTypeName != null && humanTypeName.length() != 0) {
			cit.setDisplayName(humanTypeName);
		} else {
			cit.setDisplayName(itemType.getName());
		}

		cit.setIcon(ItemTypeManager.getIconPath(itemType));
		cit.setIsAbstract(ItemTypeManager.isIsInstanceAbstractAttribute(itemType));
		cit.setPatternId(ManagerManager.getUniqueNameTemplate(manager));
		String error_valid_id = notempty(ManagerManager.getMessageErrorIdAttribute(manager));
		cit.setErrorValidId(error_valid_id);

		String pattern_valid_id = ManagerManager.getValidPatternIdAttribute(manager);
		if (pattern_valid_id != null && pattern_valid_id.length() == 0) {
			pattern_valid_id = null;
		}
		cit.setPatternValidId(pattern_valid_id);
		cit.setManagerClass(ItemTypeManager.getManagerClass(itemType,null, null));
		cit.setCstName(GenerateJavaIdentifier.cstItemType(cxt, itemType));
		cit.setDefaultShortName(notempty(ItemTypeManager.getDefaultInstanceNameAttribute(itemType)));
	}

	private static IJavaElement getItemFactoryType(ItemType itemType) {
		final String itemFactoryClass = ItemTypeManager.getItemFactoryClass(itemType);
		final IJavaElement ret = JavaCore.create(itemFactoryClass);
		return ret;
	}

	/**
	 * Generate outgoing link.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param factory
	 *            the factory
	 * @param itemType
	 *            the item type
	 * @param cit
	 *            the cit
	 * @param linkType
	 *            the link type
	 * @param itemTypeDest
	 *            the item type dest
	 * 
	 * @return the c link type
	 */
	private static CLinkType generateOutgoingLink(ContextVariable cxt, ObjectFactory factory, Item itemType,
			CTypeDefinition cit, Item linkType, Item itemTypeDest) {
		CLinkType clt = factory.createCLinkType();
		cit.getOutgoingLink().add(clt);

		clt.setId(LinkTypeManager.getIdRuntime(linkType).toString());
		clt.setIntID(0); // LinkTypeManager.getIntID(linkType)
		IType manager = LinkTypeManager.getLinkTypeManagerType(linkType);
		if (manager != null) {
			clt.setManagerClass(manager.getFullyQualifiedName());
		}

		clt.setName(linkType.getName());
		clt.setMin(LinkTypeManager.getMin(linkType));
		clt.setMax(LinkTypeManager.getMax(linkType));
		clt.setDestination(ItemTypeManager.getIdRuntime((TypeDefinition) itemTypeDest).toString());
		clt.setIsComposition(LinkTypeManager.isComposition(linkType));
		clt.setIsAggregation(LinkTypeManager.isAggregation(linkType));
		clt.setIsPart(LinkTypeManager.isPart(linkType));
		clt.setIsRequire(LinkTypeManager.isRequireAttribute(linkType));
		clt.setIsAnnotation(LinkTypeManager.isAnnotation(linkType));
		clt.setIsNatif(LinkTypeManager.isNatif(linkType));
		clt.setIsGroup(LinkTypeManager.isGroupAttribute(linkType));
		clt.setIsMapping(LinkTypeManager.isMappingAttribute(linkType));
		clt.setSelectionExpression(notempty(LinkTypeManager.getSelectionExpression(linkType)));
		clt.setAssociationItemType(null);
		clt.setCstName(GenerateJavaIdentifier.cstAttribute(cxt, linkType, itemType));
		clt.setInverseLink(notempty(LinkTypeManager.getInverseLinkName(linkType)));
		clt.setHidden(LinkTypeManager.isHiddenAttribute(linkType));
		clt.setType(linkType.getType().getId().toString());
		
		GAttribute managerLT = linkType.getType().adapt(GAttribute.class);
		clt.setFlag(managerLT.getCadseRootFlag(linkType));
		return clt;
	}

//	/**
//	 * Generate creation dialog.
//	 * 
//	 * @param cxt
//	 *            the cxt
//	 * @param factory
//	 *            the factory
//	 * @param itemType
//	 *            the item type
//	 * @param cit
//	 *            the cit
//	 * 
//	 * @return the item
//	 */
//	private static Item generateCreationDialog(ContextVariable cxt, ObjectFactory factory, Item itemType,
//			CAbsItemType cit) {
//		Item creationDialog = ItemTypeManager.getCreationDialog(itemType);
//		if (creationDialog != null) {
//			CPages pages = factory.createCPages();
//			cit.setCreationPages(pages);
//			if (CreationDialogManager.hasGlobalActionPage(creationDialog)) {
//				pages.setMainActionClass(GenerateJavaIdentifier.qualifiedGlobalCreationActionFromCreationDialog(cxt,
//						creationDialog));
//			}
//			String defaultShortName = notempty(CreationDialogManager.getDefaultShortNameAttribute(creationDialog));
//
//			pages.setDefaultShortName(defaultShortName);
//
//			Collection<Item> pagesItems = CreationDialogManager.getPages(creationDialog);
//			generatePages(cxt, factory, pages, pagesItems);
//		}
//		return creationDialog;
//	}

	/**
	 * Notempty.
	 * 
	 * @param aString
	 *            the a string
	 * 
	 * @return the string
	 */
	private static String notempty(String aString) {
		if (aString == null) {
			return null;
		}
		if (aString.length() == 0) {
			return null;
		}
		return aString;
	}

//	/**
//	 * Generate modification dialog.
//	 * 
//	 * @param cxt
//	 *            the cxt
//	 * @param factory
//	 *            the factory
//	 * @param itemType
//	 *            the item type
//	 * @param cit
//	 *            the cit
//	 * 
//	 * @return the item
//	 */
//	private static Item generateModificationDialog(ContextVariable cxt, ObjectFactory factory, Item itemType,
//			CAbsItemType cit) {
//		Item modificationDialog = ItemTypeManager.getModificationDialog(itemType);
//		if (modificationDialog != null) {
//			CPages pages = factory.createCPages();
//			cit.setModificationPages(pages);
//
//			Collection<Item> pagesItems = ModificationDialogManager.getPages(modificationDialog);
//			generatePages(cxt, factory, pages, pagesItems);
//		}
//		return modificationDialog;
//	}

//	/**
//	 * Generate pages.
//	 * 
//	 * @param cxt
//	 *            the cxt
//	 * @param factory
//	 *            the factory
//	 * @param pages
//	 *            the pages
//	 * @param pagesItems
//	 *            the pages items
//	 */
//	private static void generatePages(ContextVariable cxt, ObjectFactory factory, CPages pages,
//			Collection<Item> pagesItems) {
//		for (Item aPageItem : pagesItems) {
//			CPage aPage = factory.createCPage();
//			pages.getPage().add(aPage);
//			aPage.setCas(PageManager.isModificationPage(aPageItem) ? ConfigurablePageFactory.PAGE_PROPERTY_ITEM
//					: ConfigurablePageFactory.PAGE_CREATION_ITEM);
//
//			aPage.setClassName(GenerateJavaIdentifier.qualifiedPageFactoryFromPage(cxt, aPageItem));
//			aPage.setId(aPageItem.getName());
//			aPage.setUuid(PageManager.getIdRuntime(aPageItem).toString());
//			aPage.setTitre(PageManager.getTitle(aPageItem));
//		}
//	}

	public static void addGenerateCadseDefinitionModelExt(GenerateCadseDefinitionModelExt g) {
		if (!generatorsExt.contains(g)) {
			generatorsExt.add(g);
		}
	}

}
