package fr.imag.adele.cadse.cadseg.generator;

import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

public class LicenseGenPart extends GGenPartFile {
	public LicenseGenPart() {
		matchedToken(GCst.t_license);
	}
	
	protected String getLicense(Item cadseDefinition) {
		if (cadseDefinition == null) {
			return null;
		}

		return GenerateJavaIdentifier.ow(cadseDefinition, "license", null);
	}
	
	@Override
	public void generatePartFile(GResult r, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator, GenState state) {
		Item cadseDefinition = currentItem.getPartParent(CadseGCST.CADSE_DEFINITION);
		String l = getLicense(cadseDefinition );
		if (l != null)
			r.append(l);
	}
}
