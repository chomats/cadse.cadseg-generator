package fr.imag.adele.cadse.cadseg.generator.action;

import java.util.Set;

import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.objectadapter.ObjectAdapter;

public class GPDEImporExport extends ObjectAdapter<GPDEImporExport> implements IPDEContributor {

	public ThreadLocal<Item> _owner = new ThreadLocal<Item>();
	
	public void setOwner(Item owner) {
		_owner.set(owner);
	}
	
	@Override
	public Class<GPDEImporExport> getClassAdapt() {
		return GPDEImporExport.class;
	}

	@Override
	public void computeExportsPackage(Set<String> arg0) {
	}

	@Override
	public void computeExtenstion(IPluginBase arg0, WorkspacePluginModel arg1) {
	}

	@Override
	public void computeImportsPackage(Set<String> arg0) {
		
	}

}
