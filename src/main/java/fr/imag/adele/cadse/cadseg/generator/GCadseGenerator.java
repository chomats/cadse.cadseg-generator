package fr.imag.adele.cadse.cadseg.generator;

import java.util.UUID;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.content.ProjectContentManager;
import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GRefer;
import fr.imag.adele.cadse.as.generator.GReferIncomingLink;
import fr.imag.adele.cadse.as.generator.GReferPart;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.as.generator.IGenerator;
import fr.imag.adele.cadse.as.generator.IRuntimeGenerator;
import fr.imag.adele.cadse.cadseg.contents.CadseDefinitionContent;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.generator.attribute.GAttribute;
import fr.imag.adele.cadse.cadseg.generator.attribute.GBoolAttribute;
import fr.imag.adele.cadse.cadseg.generator.attribute.GDateAttribute;
import fr.imag.adele.cadse.cadseg.generator.attribute.GDoubleAttribute;
import fr.imag.adele.cadse.cadseg.generator.attribute.GEnumAttribute;
import fr.imag.adele.cadse.cadseg.generator.attribute.GIntegerAttribute;
import fr.imag.adele.cadse.cadseg.generator.attribute.GLTAttribute;
import fr.imag.adele.cadse.cadseg.generator.attribute.GListAttribute;
import fr.imag.adele.cadse.cadseg.generator.attribute.GLongAttribute;
import fr.imag.adele.cadse.cadseg.generator.attribute.GStringAttribute;
import fr.imag.adele.cadse.cadseg.generator.content.GContentType;
import fr.imag.adele.cadse.cadseg.generator.content.GContentType.GContentInit;
import fr.imag.adele.cadse.cadseg.generator.content.GContentType_MF;
import fr.imag.adele.cadse.cadseg.generator.content.GFileContent;
import fr.imag.adele.cadse.cadseg.generator.content.GFolderContent;
import fr.imag.adele.cadse.cadseg.generator.content.GJavaFileContent;
import fr.imag.adele.cadse.cadseg.generator.content.GJavaFileContent_MF;
import fr.imag.adele.cadse.cadseg.generator.content.GJavaJarFileContent;
import fr.imag.adele.cadse.cadseg.generator.content.GJavaJarFileContent_MF;
import fr.imag.adele.cadse.cadseg.generator.content.GJavaProjectContent;
import fr.imag.adele.cadse.cadseg.generator.content.GJavaProjectContent_MF;
import fr.imag.adele.cadse.cadseg.generator.content.GPDEProjectContent;
import fr.imag.adele.cadse.cadseg.generator.content.GPackageContent;
import fr.imag.adele.cadse.cadseg.generator.content.GPackageContent_MF;
import fr.imag.adele.cadse.cadseg.generator.content.GProjectContent;
import fr.imag.adele.cadse.cadseg.generator.content.GProjectContent_MF;
import fr.imag.adele.cadse.cadseg.generator.content.GSourceFolderContent;
import fr.imag.adele.cadse.cadseg.generator.gclass.GManagerSpecialMethod;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateCadseDefinitionModel;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateEnumType;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateJavaFileCST;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateManager;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateView;
import fr.imag.adele.cadse.cadseg.generator.gclass.part.GPDEEI_Attribute;
import fr.imag.adele.cadse.cadseg.generator.gclass.part.GPDEIE_Manager;
import fr.imag.adele.cadse.cadseg.generator.gclass.part.GPDEIE_View;
import fr.imag.adele.cadse.cadseg.generator.gclass.part.GPDE_EI_CadseDefinition;
import fr.imag.adele.cadse.cadseg.generator.gclass.part.GenAttributeMethod;
import fr.imag.adele.cadse.cadseg.generator.gclass.part.GenEnumMethods;
import fr.imag.adele.cadse.cadseg.generator.gclass.part.GenLinkTypeMethod;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.build.CompositeItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.transaction.delta.ImmutableItemDelta;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;
import fr.imag.adele.fede.workspace.as.initmodel.InitModelLoadAndWrite;

@Component(name = "fr.imag.adele.cadse.cadseGenerator", immediate = true, architecture = true)
@Provides(specifications = { IGenerator.class })
@Instantiate(name="fr.imag.adele.cadse.cadseGenerator.instance")
public class GCadseGenerator extends GGenerator {
	
	public static final UUID ID = UUID.fromString("39F384F7-9635-49BF-B61C-75390AA2DD47");


	private final class GenerateManifestAndPlugin extends GGenFile<GenState> {
		@Override
		public String generate(GGenerator g, Item currentItem,
				GenContext cxt) {
			CadseDefinitionContent cdc = (CadseDefinitionContent) currentItem.getContentItem();
			cdc.generate( cdc.getGenerateModel(), ContextVariableImpl.DEFAULT);
			return null;
		}
	}

	

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
	public static final GToken INIT_METHOD = new GToken( "init-method");
	
	public static final LicenseGenPart	LICENSE_PART = new LicenseGenPart();
	public static final GenerateManager MANAGER = new GenerateManager();
	public static final GManagerSpecialMethod MANAGER_SPECIAL_METHOD = new GManagerSpecialMethod(MANAGER.relatif(GCst.t_method));
	public static final GenerateCadseDefinitionModel CADSE_DEFINITION_MODEL = new GenerateCadseDefinitionModel();
	public static final GenerateJavaFileCST CST = new GenerateJavaFileCST();
	public static final GenerateView	VIEW = new GenerateView();
	public static final GGenInitClass INIT = new GGenInitClass();
	
	public static final GenAttributeMethod GEN_ATTRIBUTE_METHOD = new GenAttributeMethod();
	public static final GenEnumMethods GEN_ENUM_METHODS = new GenEnumMethods().override(GEN_ATTRIBUTE_METHOD);
	public static final GenLinkTypeMethod GEN_LINK_TYPE_METHOD = new GenLinkTypeMethod().override(GEN_ATTRIBUTE_METHOD);
	
	public static final GenerateEnumType GENERATE_ENUM_TYPE = new GenerateEnumType();
	
	GRefer itemTypeSubType = new ItemTypeSubTypeRefer();
	public static final GContentInit INIT_CONTENT = new GContentInit();
	
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
		
		if (fileToken == GenerateEnumType.FILE_ENUM_TYPE) {
			EnumTypeManager etm = (EnumTypeManager) currentItem.getType().getItemManager();
			String packageName = etm.getPackage(cxt, currentItem);
			String className = etm.getClassname(cxt, currentItem);
			
			Item cadseDefinition = currentItem.getPartParent(CadseGCST.CADSE_DEFINITION);
			return CadseDefinitionManager.getJavaFile(cadseDefinition, "enum-type", packageName, className);
		}
		
		if (fileToken == GContentType.CONTENT_FILE) {
			Item manager = currentItem.getPartParent();
			Item type = ManagerManager.getItemType(manager);
			String packageName = GenerateJavaIdentifier.getContentPackageName(cxt, type);
			String className = GenerateJavaIdentifier.getContentClassName(cxt, type);
			
			Item cadseDefinition = currentItem.getPartParent(CadseGCST.CADSE_DEFINITION);
			return CadseDefinitionManager.getJavaFile(cadseDefinition, "content", packageName, className);
		}
		if (fileToken == GGenInitClass.InitToken) {
			String packageName = GenerateJavaIdentifier.getInitPackageName(cxt, currentItem);
			String className =GenerateJavaIdentifier.getInitClassName(cxt, currentItem);
			return CadseDefinitionManager.getJavaFile(currentItem, "init", packageName, className);
		}
		
		return super.getFile(currentItem, fileToken, cxt);
	}
	
	public GCadseGenerator() {
		super(ID);
	}
	
	
	@Override
	public void load(IRuntimeGenerator runtimeGenerator) {
		CADSE_DEFINITION_MODEL.setGenerator(this);
		CST.setGenerator(this);
		GENERATE_ENUM_TYPE.setGenerator(this);
		MANAGER.setGenerator(this);
		VIEW.setGenerator(this);
		INIT.setGenerator(this);
		
		CST.addParticipant(LICENSE_PART);
		MANAGER.addParticipant(MANAGER_SPECIAL_METHOD);
		MANAGER.addParticipant(LICENSE_PART);
		VIEW.addParticipant(LICENSE_PART);
		INIT.addParticipant(LICENSE_PART);
		
		CadseGCST.CADSE_DEFINITION.addAdapter(CST);
		CadseGCST.CADSE_DEFINITION.addAdapter(CADSE_DEFINITION_MODEL);
		CadseGCST.CADSE_DEFINITION.addAdapter(INIT);
		CadseGCST.ENUM_TYPE.addAdapter(GENERATE_ENUM_TYPE);
		CadseGCST.MANAGER.addAdapter(new GenerateManager.ManagerIter());
		CadseGCST.MANAGER.addAdapter(MANAGER);
		CadseGCST.VIEW.addAdapter(VIEW);
		
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
		new GReferIncomingLink(CadseGCST.ITEM_TYPE, CadseGCST.MANAGER_lt_ITEM_TYPE);

		
		
		
		GenerateManifestAndPlugin genMFandPlugin = new GenerateManifestAndPlugin();
		genMFandPlugin.setGenerator(this);
		CadseGCST.CADSE_DEFINITION.addAdapter(genMFandPlugin);
		
		CadseGCST.VIEW.addAdapter(VIEW);
		
		CadseGCST.ATTRIBUTE.addAdapter(GEN_ATTRIBUTE_METHOD);
		GEN_ATTRIBUTE_METHOD.matchedToken(MANAGER.relatif(GCst.t_method));
		CadseGCST.ENUM.addAdapter(GEN_ENUM_METHODS);
		GEN_ENUM_METHODS.matchedToken(MANAGER.relatif(GCst.t_method));
		CadseGCST.LINK_TYPE.addAdapter(GEN_LINK_TYPE_METHOD);
		GEN_LINK_TYPE_METHOD.matchedToken(MANAGER.relatif(GCst.t_method));
		
		// GAttribute adapter
		
		CadseGCST.ATTRIBUTE.addAdapter(new GAttribute());
		CadseGCST.BOOLEAN.addAdapter(new GBoolAttribute());
		CadseGCST.DATE.addAdapter(new GDateAttribute());
		CadseGCST.DOUBLE.addAdapter(new GDoubleAttribute());
		CadseGCST.ENUM.addAdapter(new GEnumAttribute());
		CadseGCST.INTEGER.addAdapter(new GIntegerAttribute());
		CadseGCST.LIST.addAdapter(new GListAttribute());
		CadseGCST.LONG.addAdapter(new GLongAttribute());
		CadseGCST.LINK_TYPE.addAdapter(new GLTAttribute());
		CadseGCST.STRING.addAdapter(new GStringAttribute());
		
		// Import/export package 
		CadseGCST.MANAGER.addAdapter(new GPDEIE_Manager());
		CadseGCST.VIEW.addAdapter(new GPDEIE_View());
		CadseGCST.ATTRIBUTE.addAdapter(new GPDEEI_Attribute());
		CadseGCST.CADSE_DEFINITION.addAdapter(new GPDE_EI_CadseDefinition());
		
		
		INIT_CONTENT.setGenfile(INIT);
		INIT_CONTENT.matchedToken(GCadseGenerator.INIT_METHOD);
		CadseGCST.CONTENT_ITEM_TYPE.addAdapter(INIT_CONTENT);
		
		// content
		content(new GContentType(), new GContentType_MF(), CadseGCST.CONTENT_ITEM_TYPE);
		content(new GFileContent(), null, CadseGCST.FILE_CONTENT_MODEL);
		content(new GFolderContent(), null, CadseGCST.FOLDER_CONTENT_MODEL);
		
		content(new GProjectContent(), new GProjectContent_MF(), CadseGCST.PROJECT_CONTENT_MODEL);
		
		content(new GSourceFolderContent(), null, CadseGCST.SOURCE_FOLDER_CONTENT_MODEL);
		content(new GJavaFileContent(), new GJavaFileContent_MF(), CadseGCST.JAVA_FILE_CONTENT_MODEL);
		content(new GJavaJarFileContent(), new GJavaJarFileContent_MF(), CadseGCST.JAVA_JAR_FILE_CONTENT_MODEL);
		content(new GJavaProjectContent(), new GJavaProjectContent_MF(), CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		content(new GPackageContent(), new GPackageContent_MF(), CadseGCST.PACKAGE_CONTENT_MODEL);
		
		content(new GPDEProjectContent(), null, CadseGCST.PDEPROJECT_CONTENT_MODEL);
		
	}
	
	public void content(GContentType gContentType, IPDEContributor mf, ItemType it) {
		it.addAdapter(gContentType);
		gContentType.addParticipant(LICENSE_PART);
		GGenFile[] contentSuper = it.getSuperType().adapts(GGenFile.class);
		if (contentSuper != null)
			for (GGenFile gpf : contentSuper) {
				if (gpf instanceof GContentType) {
					gContentType.override(gpf);
				}
			}
		if (mf != null)
			it.addAdapter(mf);
	}

	@Override
	public UUID[] getRequireCadse() {
		return new UUID[] { CadseGCST._CADSE_ID };
	}
}
