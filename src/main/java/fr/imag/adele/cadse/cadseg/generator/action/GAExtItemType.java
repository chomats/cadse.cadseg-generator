package fr.imag.adele.cadse.cadseg.generator.action;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import fede.workspace.eclipse.MelusineProjectManager;
import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateExtItemType;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.si.view.View;

public class GAExtItemType extends GAction {

	@Override
	public void generate(JavaFileContentManager jf, ContextVariable cxt) {
		Item extit = jf.getOwnerItem();

		GenerateExtItemType ge = new GenerateExtItemType(cxt, jf);
		Item cadseDefinition = extit.getPartParent(CadseGCST.CADSE_DEFINITION);

		((IGenerateContent) cadseDefinition.getContentItem()).generate(cxt);

		String path = jf.getPath(cxt);
		try {
			EclipsePluginContentManger.generateJava(MelusineProjectManager.getProject(cadseDefinition).getFile(
					new Path(path)), ge.getContent(), View.getDefaultMonitor());

		} catch (CoreException e) {
			e.printStackTrace();
		}

	}
}
