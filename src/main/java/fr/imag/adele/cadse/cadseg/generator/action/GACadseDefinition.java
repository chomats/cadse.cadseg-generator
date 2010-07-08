package fr.imag.adele.cadse.cadseg.generator.action;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fede.workspace.tool.eclipse.MappingManager;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateCadseDefinitionModel;
import fr.imag.adele.cadse.cadseg.generator.gclass.GenerateJavaFileCST;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CCadse;

public class GACadseDefinition extends GAction {

	@Override
	public void generate(JavaFileContentManager jf, ContextVariable cxt) {
		if (!jf.getOwnerItem().exists())
			return;

		generateStrandardXML(jf);

		try {
			GenerateJavaFileCST gCST = new GenerateJavaFileCST(cxt, getOwnerItem());
			String content = gCST.getContent();

			IFile cstFile = CadseDefinitionManager.getCSTCU(cxt, getOwnerItem());
			MappingManager.generate(cstFile.getProject(), cstFile.getParent().getProjectRelativePath(), cstFile
					.getName(), content, null);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Generate strandard xml.
	 */
	public void generateStrandardXML(JavaFileContentManager jf) {
		IProject p = getProject();
		IFile f = p.getFile(new Path("model/cadse.xml"));
		CCadse cadse = GenerateCadseDefinitionModel.generateCADSE(getOwnerItem());
		StringWriter writer = new StringWriter();

		try {
			JAXBContext jc = JAXBContext.newInstance("fr.imag.adele.fede.workspace.as.initmodel.jaxb", this.getClass()
					.getClassLoader());
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(cadse, writer);
			MappingManager.generate(p, f.getParent().getProjectRelativePath(), f.getName(), writer.toString(), null);
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
