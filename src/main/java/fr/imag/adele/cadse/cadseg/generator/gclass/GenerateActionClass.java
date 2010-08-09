package fr.imag.adele.cadse.cadseg.generator.gclass;

import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenClassState;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

public class GenerateActionClass extends GenerateClass<GenClassState> {

	public static final GToken ACTION_CLASS = new GToken("action-class");
	public static final String super_pn = "fr.imag.adele.cadse.core.ui";
	public static final String super_cn = "MenuAction";

	public GenerateActionClass() {
		super(ACTION_CLASS);
	}
	
	@Override
	protected void init(GenClassState state, Item currentItem, GGenerator g,
			GenContext cxt) {
		super.init(state, currentItem, g, cxt);
		GenClassState gcs = (GenClassState) state;		
		gcs._extendedClassName = super_cn;
		gcs._extendedPackageName = super_pn;
		gcs._isClass = true;
		
	}
	
	@Override
	public boolean match(GToken kind) {
		return kind.abs() == GCst.t_method || super.match(kind);
	}
	
	@Override
	public void generatePartFile(GResult g, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {
		if (kind.abs() == GCst.t_method) {
			state.getImports().add("fr.imag.adele.cadse.core.IItemNode");
			state.getImports().add("fr.imag.adele.cadse.core.ui.MenuAction");
			state.getImports().add("fr.imag.adele.cadse.core.CadseException");

			g.newline().append("@Override");
			g.newline().append("public void run(IItemNode[] selection) throws CadseException {");
			g.newline().append("	// TODO Auto-generated method stub");
			g.newline();
			g.newline().append("}");
		}
		super.generatePartFile(g, currentItem, gf, kind, context, gGenerator, state);
	}
}
