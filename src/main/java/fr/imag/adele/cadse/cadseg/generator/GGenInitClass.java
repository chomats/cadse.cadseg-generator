package fr.imag.adele.cadse.cadseg.generator;

import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenClassState;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.as.generator.GenerateClass;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

public class GGenInitClass extends GenerateClass<GenClassState>{

	final public static GToken InitToken = new GToken("init-class");
	
	public GGenInitClass() {
		super(InitToken);
	}
	
	@Override
	protected void init(GenClassState state, Item currentItem, GGenerator g,
			GenContext cxt) {
		super.init(state, currentItem, g, cxt);
		state._packageName = GenerateJavaIdentifier.getInitPackageName(cxt, currentItem);
		state.fClassName =GenerateJavaIdentifier.getInitClassName(cxt, currentItem);
		state.fImplementsPackageName = new String[] { "fr.imag.adele.cadse.core" };
		state.fImplementsClassName = new String[] { "InitAction" };
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
			g.newline().appendGeneratedTag();
			g.newline().append("public void init() {");
			g.appendToken(GCadseGenerator.INIT_METHOD, state);
			g.newline().append("}");
		}
	}
}
