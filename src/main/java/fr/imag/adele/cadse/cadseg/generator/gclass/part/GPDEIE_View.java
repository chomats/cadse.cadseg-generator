package fr.imag.adele.cadse.cadseg.generator.gclass.part;

import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class GPDEIE_View extends IPDEContributor {
			
	
	/*
			 * (non-Javadoc)
			 * 
			 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeExportsPackage(java.util.Set)
			 */
			public void computeExportsPackage(Item currentItem, Set<String> exports) {
				JavaFileContentManager jf = (JavaFileContentManager) currentItem.getContentItem();
				exports.add(jf.getPackageName(ContextVariableImpl.DEFAULT));
			}
	
			/*
			 * (non-Javadoc)
			 * 
			 * @see fede.workspace.eclipse.composition.java.IPDEContributor#computeImportsPackage(java.util.Set)
			 */
			public void computeImportsPackage(Item currentItem, Set<String> imports) {
				imports.add("org.eclipse.ui.part");
				imports.add("org.eclipse.core.commands.common");
				imports.add("org.eclipse.swt.widgets");
				imports.add("fr.imag.adele.cadse.eclipse.view");
				imports.add("fr.imag.adele.cadse.core");
				imports.add("org.eclipse.ui");
				imports.add("fede.plugin.workspace.filters");
			}
			
			
}

