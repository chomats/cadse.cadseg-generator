package fr.imag.adele.cadse.cadseg.generator;

import java.util.UUID;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;

import fede.workspace.eclipse.content.ProjectContentManager;
import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GRefer;
import fr.imag.adele.cadse.as.generator.GReferIncomingLink;
import fr.imag.adele.cadse.as.generator.GReferPart;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.IGenerator;
import fr.imag.adele.cadse.as.generator.IRuntimeGenerator;
import fr.imag.adele.cadse.cadseg.generator.gclass.GManagerSpecialMethod;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateCadseDefinitionModel;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateJavaFileCST;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateManager;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateView;
import fr.imag.adele.cadse.cadseg.generator.gclass.part.GenAttributeMethod;
import fr.imag.adele.cadse.cadseg.generator.gclass.part.GenEnumMethods;
import fr.imag.adele.cadse.cadseg.generator.gclass.part.GenLinkTypeMethod;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.build.CompositeItemTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.transaction.delta.ImmutableItemDelta;
import fr.imag.adele.cadse.core.var.ContextVariable;

@Component(name = "fr.imag.adele.cadse.cadseGenerator", immediate = true, architecture = true)
@Provides(specifications = { IGenerator.class })
@Instantiate(name="fr.imag.adele.cadse.cadseGenerator.instance")
public class GCadseGenerator extends GGenerator {
	
	public static final UUID ID = UUID.fromString("39F384F7-9635-49BF-B61C-75390AA2DD47");


	static private final class ItemTypeSubTypeRefer extends GRefer {
		public Item[] refers(Item currentItem, ImmutableItemDelta itemDelta) {
				if (itemDelta.hasModifiedAttribute(CadseGCST.ITEM_TYPE_at_MANAGER_CLASS_) ||
						itemDelta.hasModifiedAttribute(CadseGCST.ITEM_TYPE_at_MANAGER_CLASS_)) {
					if (currentItem instanceof ItemType) {
						return ((ItemType)currentItem).getSubTypes();
					}
				}
				return null;
		}
	}

	public static final GToken P_MODEL = new GToken( "project model");
	public static final GToken P_DMODEL = new GToken( "project data model");
	public static final GToken P_ECLIPSE = new GToken( "project eclipse");
	
	public static final LicenseGenPart	LICENSE_PART = new LicenseGenPart();
	public static final GenerateManager MANAGER = new GenerateManager();
	public static final GManagerSpecialMethod MANAGER_SPECIAL_METHOD = new GManagerSpecialMethod(MANAGER.relatif(GCst.t_method));
	public static final GenerateCadseDefinitionModel CADSE_DEFINITION_MODEL = new GenerateCadseDefinitionModel();
	public static final GenerateJavaFileCST CST = new GenerateJavaFileCST();
	public static final GenerateView	VIEW = new GenerateView();
	
	public static final GenAttributeMethod GEN_ATTRIBUTE_METHOD = new GenAttributeMethod();
	public static final GenEnumMethods GEN_ENUM_METHODS = new GenEnumMethods().override(GEN_ATTRIBUTE_METHOD);
	public static final GenLinkTypeMethod GEN_LINK_TYPE_METHOD = new GenLinkTypeMethod().override(GEN_ATTRIBUTE_METHOD);
	
	
	GRefer itemTypeSubType = new ItemTypeSubTypeRefer();
	
	@Override
	public IProject getProject(GToken t, Item owner) {
		if (owner.getType() == CadseGCST.CADSE_DEFINITION)
			return ((ProjectContentManager) owner.getContentItem()).getProject();
		return super.getProject(t, owner);
	}
	
	@Override
	public IFile getFile(Item currentItem, GToken fileToken, GenContext cxt) {
		if (fileToken == CST.getKey()) {
			IFile cstFile = CadseDefinitionManager.getCSTCU(cxt, currentItem);
			return cstFile;
		}
		if (fileToken == CADSE_DEFINITION_MODEL.getKey()) {
			IProject p = getProject(GCadseGenerator.P_DMODEL, currentItem);
			IFile f = p.getFile(new Path("model/cadse.xml"));
			return f;
		}
		
		return super.getFile(currentItem, fileToken, cxt);
	}
	
	public GCadseGenerator() {
		super(ID);
	}
	
	
	@Override
	public void load(IRuntimeGenerator runtimeGenerator) {
		MANAGER.setGenerator(this);
		CST.setGenerator(this);
		CADSE_DEFINITION_MODEL.setGenerator(this);
		VIEW.setGenerator(this);
		
		MANAGER.addParticipant(MANAGER_SPECIAL_METHOD);
		MANAGER.addParticipant(LICENSE_PART);
		CST.addParticipant(LICENSE_PART);
		VIEW.addParticipant(LICENSE_PART);
		
		CadseGCST.ITEM_TYPE.addAdapter(new GenerateManager.ManagerIter());
		CadseGCST.MANAGER.addAdapter(MANAGER);
		
		//refer
		CadseGCST.ITEM_TYPE.addAdapter(itemTypeSubType);
		new GReferPart(CadseGCST.MANAGER, CadseGCST.CADSE_DEFINITION);
		new GReferPart(CadseGCST.DYNAMIC_ACTIONS, CadseGCST.CADSE_DEFINITION);
		new GReferPart(CadseGCST.MENU_ACTION, CadseGCST.CADSE_DEFINITION);
		new GRefer(CadseGCST.COMPOSITE_ITEM_TYPE) {
			@Override
			public Item refer(Item currentItem) {
				return CompositeItemTypeManager.getItemType(currentItem);
			}
		};

		new GReferPart(CadseGCST.PAGE, CadseGCST.CADSE_DEFINITION);
		new GReferPart(CadseGCST.EXT_ITEM_TYPE, CadseGCST.CADSE_DEFINITION);
		new GReferPart(CadseGCST.ATTRIBUTE, CadseGCST.CADSE_DEFINITION);
		new GReferIncomingLink(CadseGCST.ATTRIBUTE, CadseGCST.FIELD_lt_ATTRIBUTE);

		
		CadseGCST.CADSE_DEFINITION.addAdapter(CST);
		CadseGCST.CADSE_DEFINITION.addAdapter(CADSE_DEFINITION_MODEL);
		CadseGCST.VIEW.addAdapter(VIEW);
		
		CadseGCST.ATTRIBUTE.addAdapter(GEN_ATTRIBUTE_METHOD);
		CadseGCST.ENUM.addAdapter(GEN_ENUM_METHODS);
		CadseGCST.LINK_TYPE.addAdapter(GEN_LINK_TYPE_METHOD);
	}
	
	@Override
	public UUID[] getRequireCadse() {
		return new UUID[] { CadseGCST._CADSE_ID };
	}
}
