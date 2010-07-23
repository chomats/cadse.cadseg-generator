package fr.imag.adele.cadse.cadseg.generator.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import fede.workspace.tool.eclipse.MappingManager;
import fr.imag.adele.cadse.as.generator.GAction;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.cadseg.generator.GCadseGenerator;
import fr.imag.adele.cadse.cadseg.template.LaunchApplicationTemplate;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.fede.workspace.si.view.View;

public class GLauncher extends GAction {

	
	@Override
	public void generate(GGenerator g, Item currentItem, GenContext cxt) throws CoreException {

		IProgressMonitor monitor = View.getDefaultMonitor();
		IProject p = g.getProject(GCadseGenerator.P_ECLIPSE, currentItem);
		

		IFile launchAppli = p.getFile("run-cadse-" + currentItem.getName() + ".launch");
		if (!launchAppli.exists()) {
			LaunchApplicationTemplate lat = new LaunchApplicationTemplate();
			MappingManager.generate(p, null, launchAppli.getName(), lat.generate(currentItem),
					monitor);
		}
		
	}
}
