package fr.imag.adele.cadse.cadseg.generator.gclass;

import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenPartFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.ParseTemplate;
import fr.imag.adele.cadse.cadseg.exp.ParseException;
import fr.imag.adele.cadse.cadseg.exp.TokenMgrError;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

public class GManagerSpecialMethod extends GGenPartFile {

	
	public GManagerSpecialMethod(GToken t_method) {
		getMatchedToken().add(t_method);
	}
	
	@Override
	public void generatePartFile(GResult sb, Item manager, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		
		GenManagerState cim = (GenManagerState) state;
		String uniqueNameTemplate = ManagerManager
				.getUniqueNameTemplate(manager);
		if (uniqueNameTemplate != null && uniqueNameTemplate.length() != 0) {
			state.getImports().add("fr.imag.adele.cadse.core.LinkType");
			state.getImports().add("fr.imag.adele.cadse.core.Item");
			sb.newline();
			sb.newline().append("/**");
			sb.newline().append("	@generated");
			sb.newline().append("*/");
			sb.newline().append("@Override");
			sb.newline()
					.append("public String computeQualifiedName(Item item, String name, Item parent, LinkType lt) {");
			sb.begin();
			Item itemtype = cim.itemtype;
			ParseTemplate pt = new ParseTemplate(itemtype, uniqueNameTemplate,
					"name");
			try {
				pt.main();
				pt.build(sb, state.getImports(), true, cim.getPackageName());
			} catch (ParseException e) {
				sb.newline().append("//").append(e.getMessage());
			} catch (TokenMgrError e) {
				sb.newline().append("//").append(e.getMessage());
			}
			sb.end();
			sb.newline().append("}");
		}

		String displayTemplate = ManagerManager
				.getDisplayNameTemplateAttribute(manager);
		if (displayTemplate != null && displayTemplate.length() != 0) {
			sb.newline();
			state.getImports().add("fr.imag.adele.cadse.core.Item");
			sb.newline().append("/**");
			sb.newline().append("	@generated");
			sb.newline().append("*/");
			sb.newline().append("@Override");
			sb.newline().append("public String getDisplayName(Item item) {");
			sb.begin();
			Item itemtype = cim.itemtype;
			ParseTemplate pt = new ParseTemplate(itemtype, displayTemplate,
					null);
			try {
				pt.main();
				pt.build("item", "sb", sb, state.getImports(), null, true,
						cim.getPackageName());
			} catch (ParseException e) {
				sb.newline().append("//").append(e.getMessage());
			} catch (TokenMgrError e) {
				sb.newline().append("//").append(e.getMessage());
			}
			sb.end();
			sb.newline().append("}");
		}
	}
}
