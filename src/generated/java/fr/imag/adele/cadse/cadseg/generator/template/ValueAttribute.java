package fr.imag.adele.cadse.cadseg.generator.template;

import fr.imag.adele.cadse.core.Item;
import java.util.Set;

import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.attributes.StringManager;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;
import fr.imag.adele.cadse.cadseg.generator.attribute.GAttribute;

public class ValueAttribute
 {


  protected static String nl;
  public static synchronized ValueAttribute create(String lineSeparator)
  {
    nl = lineSeparator;
    ValueAttribute result = new ValueAttribute();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tpublic static final ";
  protected final String TEXT_3 = " ";
  protected final String TEXT_4 = "(Item ";
  protected final String TEXT_5 = ") {";
  protected final String TEXT_6 = NL + "\t\treturn ";
  protected final String TEXT_7 = ".getAttributeWithDefaultValue(";
  protected final String TEXT_8 = "_, ";
  protected final String TEXT_9 = ");";
  protected final String TEXT_10 = NL + "\t\treturn ";
  protected final String TEXT_11 = ".getAttributeWithDefaultValue(";
  protected final String TEXT_12 = "_, ";
  protected final String TEXT_13 = ");";
  protected final String TEXT_14 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tpublic static final void ";
  protected final String TEXT_15 = "(Item ";
  protected final String TEXT_16 = ", ";
  protected final String TEXT_17 = " value) {" + NL + "\t\ttry {";
  protected final String TEXT_18 = NL + "\t\t\t";
  protected final String TEXT_19 = ".setAttribute(";
  protected final String TEXT_20 = "_, value);";
  protected final String TEXT_21 = NL + "\t\t\tObject setvalue = ";
  protected final String TEXT_22 = ";" + NL + "\t\t\t";
  protected final String TEXT_23 = ".setAttribute(";
  protected final String TEXT_24 = "_, setvalue);";
  protected final String TEXT_25 = NL + "\t\t} catch (Throwable t) {" + NL + "" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_26 = NL;

/* (non-javadoc)
    * @see IGenerator#generate(Object)
    */
	public String generate(String shortName, Item attribute, Set<String> imports)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    	String min_short_name = JavaIdentifier.javaIdentifierFromString(shortName,false,true, null);
	String cstAttribute = GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, attribute, null, null, null);
	ItemType it = attribute.getType();
	GAttribute manager = it.adapt(GAttribute.class);

	 String typeJava = null;
	 Class<?> cl = manager.getTypeJava(true);
	 if (!cl.isPrimitive()) { imports.add(cl.getName()); }
	 typeJava = cl.getSimpleName();

	 String defaultValue = manager.getJavaTypeDefaultValue(attribute);

	
    stringBuffer.append(TEXT_2);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(GenerateJavaIdentifier.cstGetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(min_short_name );
    stringBuffer.append(TEXT_5);
      if (it == CadseGCST.BOOLEAN) { 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_9);
     } else {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_13);
     } 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(GenerateJavaIdentifier.cstSetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(min_short_name );
    stringBuffer.append(TEXT_16);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_17);
     String exp_to_string = manager.exp_to_string();
	if (exp_to_string == null || exp_to_string.trim().equals("value")) {
    stringBuffer.append(TEXT_18);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_20);
     } else { 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(exp_to_string);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    stringBuffer.append(TEXT_26);
    return stringBuffer.toString();
  }
}