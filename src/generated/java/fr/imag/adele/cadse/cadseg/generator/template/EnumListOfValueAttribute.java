package fr.imag.adele.cadse.cadseg.generator.template;

import fr.imag.adele.cadse.core.Item;
import java.util.Set;

import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import org.eclipse.jdt.core.IType;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class EnumListOfValueAttribute
 {


  protected static String nl;
  public static synchronized EnumListOfValueAttribute create(String lineSeparator)
  {
    nl = lineSeparator;
    EnumListOfValueAttribute result = new EnumListOfValueAttribute();
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
  protected final String TEXT_13 = "> valueList) {" + NL + "\t\ttry {" + NL + "\t\t\t";
  protected final String TEXT_14 = ".setAttribute(";
  protected final String TEXT_15 = "_, new ArrayList<";
  protected final String TEXT_16 = ">(valueList));" + NL + "\t\t} catch (Throwable t) {" + NL + "" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic static final void add";
  protected final String TEXT_17 = "Attribute(Item ";
  protected final String TEXT_18 = ", ";
  protected final String TEXT_19 = " value) {" + NL + "\t\ttry {" + NL + "\t\t\tList<";
  protected final String TEXT_20 = "> list = ";
  protected final String TEXT_21 = ".getAttribute(";
  protected final String TEXT_22 = "_);" + NL + "\t\t\tif (list == null) {" + NL + "\t\t\t\tlist = new ArrayList<";
  protected final String TEXT_23 = ">();" + NL + "\t\t\t}" + NL + "\t\t\tlist.add(value);" + NL + "\t\t\t";
  protected final String TEXT_24 = ".setAttribute(";
  protected final String TEXT_25 = "_, list);" + NL + "\t\t} catch (Throwable t) {" + NL + "" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic static final void remove";
  protected final String TEXT_26 = "Attribute(Item ";
  protected final String TEXT_27 = ", ";
  protected final String TEXT_28 = " value) {" + NL + "\t\ttry {" + NL + "" + NL + "\t\t\tList<";
  protected final String TEXT_29 = "> list = ";
  protected final String TEXT_30 = ".getAttribute(";
  protected final String TEXT_31 = "_);" + NL + "\t\t\tif (list == null) {" + NL + "\t\t\t\treturn;" + NL + "\t\t\t}" + NL + "\t\t\tlist.remove(value);" + NL + "\t\t\tif (list.size() == 0)" + NL + "\t\t\t\t";
  protected final String TEXT_32 = ".setAttribute(";
  protected final String TEXT_33 = "_, null);" + NL + "\t\t\telse" + NL + "\t\t\t\t";
  protected final String TEXT_34 = ".setAttribute(";
  protected final String TEXT_35 = "_, list);" + NL + "\t\t} catch (Throwable t) {" + NL + "" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_36 = NL;

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

	Item enumType = EnumManager.getEnumType(attribute);
	IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT,enumType);
	String enumClass = javaenumtype.getElementName();


	
    stringBuffer.append(TEXT_2);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(GenerateJavaIdentifier.cstGetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(min_short_name );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(GenerateJavaIdentifier.cstSetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(upper_first_att_name );
    stringBuffer.append(TEXT_17);
    stringBuffer.append(min_short_name );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(upper_first_att_name);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(min_short_name );
    stringBuffer.append(TEXT_27);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(TEXT_36);
    return stringBuffer.toString();
  }
}