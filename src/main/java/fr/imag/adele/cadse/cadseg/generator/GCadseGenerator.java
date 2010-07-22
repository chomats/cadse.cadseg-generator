package fr.imag.adele.cadse.cadseg.generator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;

import fede.workspace.eclipse.content.ProjectContentManager;
import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GRefer;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.cadseg.generator.gclass.GManagerSpecialMethod;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateCadseDefinitionModel;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateJavaFileCST;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateManager;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.transaction.delta.ImmutableItemDelta;
import fr.imag.adele.cadse.core.var.ContextVariable;

public class GCadseGenerator extends GGenerator {
	
	public static final GToken P_MODEL = new GToken( "project model");
	public static final GToken P_DMODEL = new GToken( "project data model");
	public static final GToken P_ECLIPSE = new GToken( "project eclipse");
	
	public final LicenseGenPart	license = new LicenseGenPart();
	public final GenerateManager manager = new GenerateManager();
	public final GManagerSpecialMethod mm = new GManagerSpecialMethod(manager.relatif(GCst.t_method));
	public final GenerateCadseDefinitionModel cadseModel = new GenerateCadseDefinitionModel();
	public final GenerateJavaFileCST cst = new GenerateJavaFileCST();
	
	GRefer itemTypeSubType = new GRefer() {
		
		public Item[] refers(Item currentItem, ImmutableItemDelta itemDelta) {
				if (itemDelta.hasModifiedAttribute(CadseGCST.ITEM_TYPE_at_MANAGER_CLASS_) ||
						itemDelta.hasModifiedAttribute(CadseGCST.ITEM_TYPE_at_MANAGER_CLASS_)) {
					if (currentItem instanceof ItemType) {
						return ((ItemType)currentItem).getSubTypes();
					}
				}
				return null;
		};
	};
	
	@Override
	public IProject getProject(GToken t, Item owner) {
		if (owner.getType() == CadseGCST.CADSE_DEFINITION)
			return ((ProjectContentManager) owner.getContentItem()).getProject();
		return super.getProject(t, owner);
	}
	
	@Override
	public IFile getFile(Item currentItem, GToken fileToken, GenContext cxt) {
		if (fileToken == cst.getKey()) {
			IFile cstFile = CadseDefinitionManager.getCSTCU(cxt, currentItem);
			return cstFile;
		}
		if (fileToken == cadseModel.getKey()) {
			IProject p = getProject(GCadseGenerator.P_DMODEL, currentItem);
			IFile f = p.getFile(new Path("model/cadse.xml"));
			return f;
		}
		
		return super.getFile(currentItem, fileToken, cxt);
	}
	
	void init() {
		manager.setGenerator(this);
		manager.addParticipant(mm);
		manager.addParticipant(license);
		CadseGCST.ITEM_TYPE.addAdapter(new GenerateManager.ManagerIter());
		CadseGCST.MANAGER.addAdapter(manager);
		
		CadseGCST.ITEM_TYPE.addAdapter(itemTypeSubType);
		CadseGCST.CADSE_DEFINITION.addAdapter(cst);
		CadseGCST.CADSE_DEFINITION.addAdapter(cadseModel);
		
	}
}
