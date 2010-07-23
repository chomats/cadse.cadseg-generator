package fr.imag.adele.cadse.cadseg.template;

import fr.imag.adele.cadse.core.Item;
import java.util.Set;

import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.generator.attribute.GAttribute;

public class ListOfValueAttribute
 {


  protected static String nl;
  public static synchronized ListOfValueAttribute create(String lineSeparator)
  {
    nl = lineSeparator;
    ListOfValueAttribute result = new ListOfValueAttribute();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic static final List<";
  protected final String TEXT_3 = "> ";
  protected final String TEXT_4 = "(Item ";
  protected final String TEXT_5 = ") {" + NL + "\t\ttry {" + NL + "\t\t\tList<";
  protected final String TEXT_6 = "> list = ";
  protected final String TEXT_7 = ".getAttribute(";
  protected final String TEXT_8 = "_);" + NL + "" + NL + "\t\t\tif (list == null)" + NL + "\t\t\t\treturn null;" + NL + "" + NL + "\t\t\treturn new ArrayList<";
  protected final String TEXT_9 = ">(list);" + NL + "\t\t} catch (Throwable t) {" + NL + "\t\t\treturn new ArrayList<";
  protected final String TEXT_10 = ">();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic static final void ";
  protected final String TEXT_11 = "(Item ";
  protected final String TEXT_12 = ", List<";
  protected final String TEXT_13 = "> valueList) {" + NL + "\t\ttry {" + NL + "\t\t\tList<";
  protected final String TEXT_14 = "> list = new ArrayList<";
  protected final String TEXT_15 = ">(valueList);" + NL + "\t\t\t";
  protected final String TEXT_16 = ".setAttribute(";
  protected final String TEXT_17 = "_, list);" + NL + "\t\t} catch (Throwable t) {" + NL + "" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic static final void add";
  protected final String TEXT_18 = "Attribute(Item ";
  protected final String TEXT_19 = ", ";
  protected final String TEXT_20 = " value) {" + NL + "\t\ttry {" + NL + "\t\t\tList<";
  protected final String TEXT_21 = "> list = ";
  protected final String TEXT_22 = ".getAttribute(";
  protected final String TEXT_23 = "_);" + NL + "\t\t\tif (list == null) {" + NL + "\t\t\t\tlist = new ArrayList<";
  protected final String TEXT_24 = ">();" + NL + "\t\t\t}" + NL + "\t\t\tlist.add(value);" + NL + "\t\t\t";
  protected final String TEXT_25 = ".setAttribute(";
  protected final String TEXT_26 = "_, list);" + NL + "\t\t} catch (Throwable t) {" + NL + "" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic static final void remove";
  protected final String TEXT_27 = "Attribute(Item ";
  protected final String TEXT_28 = ", ";
  protected final String TEXT_29 = " value) {" + NL + "\t\ttry {" + NL + "" + NL + "\t\t\tList<";
  protected final String TEXT_30 = "> list = ";
  protected final String TEXT_31 = ".getAttribute(";
  protected final String TEXT_32 = "_);" + NL + "\t\t\tif (list == null) {" + NL + "\t\t\t\treturn;" + NL + "\t\t\t}" + NL + "\t\t\tlist.remove(value);" + NL + "\t\t\tif (list.size() == 0)" + NL + "\t\t\t\t";
  protected final String TEXT_33 = ".setAttribute(";
  protected final String TEXT_34 = "_, null);" + NL + "\t\t\telse" + NL + "\t\t\t\t";
  protected final String TEXT_35 = ".setAttribute(";
  protected final String TEXT_36 = "_, list);" + NL + "\t\t} catch (Throwable t) {" + NL + "" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_37 = NL;

/* (non-javadoc)
    * @see IGenerator#generate(Object)
    */
	public String generate(String shortName, Item attribute, Set<String> imports)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    	String upper_first_att_name = JavaIdentifier.javaIdentifierFromString(attribute.getName(),true, false, null);
	String min_short_name = JavaIdentifier.javaIdentifierFromString(shortName,false, true, null);
	String cstAttribute = GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, attribute, null, null, null);

	ItemType it = attribute.getType();
	GAttribute manager = it.adapt(GAttribute.class);

	 String typeJava = null;
	  Class<?> cl = manager.getTypeJava(false);
	 if (!cl.isPrimitive()) { imports.add(cl.getName()); }
	 typeJava = cl.getSimpleName();

	
    stringBuffer.append(TEXT_2);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(GenerateJavaIdentifier.cstGetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(min_short_name );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(GenerateJavaIdentifier.cstSetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(upper_first_att_name );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(min_short_name );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(upper_first_att_name);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(min_short_name );
    stringBuffer.append(TEXT_28);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(typeJava);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(TEXT_37);
    return stringBuffer.toString();
  }
}