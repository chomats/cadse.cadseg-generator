package fr.imag.adele.cadse.cadseg.generator.action;

import java.util.Set;

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class GPDEIE_View extends GPDEImporExport {
			
	
	/*
			 * (non-Javadoc)
			 * 
			 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage(java.util.Set)
			 */
			public void computeExportsPackage(Set<String> exports) {
				JavaFileContentManager jf = (JavaFileContentManager) _owner.get().getContentItem();
				exports.add(jf.getPackageName(ContextVariableImpl.DEFAULT));
			}
	
			/*
			 * (non-Javadoc)
			 * 
			 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
			 */
			public void computeImportsPackage(Set<String> imports) {
				imports.add("org.eclipse.ui.part");
				imports.add("org.eclipse.core.commands.common");
				imports.add("org.eclipse.swt.widgets");
				imports.add("fr.imag.adele.cadse.eclipse.view");
				imports.add("fr.imag.adele.cadse.core");
				imports.add("org.eclipse.ui");
				imports.add("fede.plugin.workspace.filters");
			}
			
			
}

