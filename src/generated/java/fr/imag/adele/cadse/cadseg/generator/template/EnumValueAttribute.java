
package fr.imag.adele.cadse.cadseg.generator.template;

import fr.imag.adele.cadse.core.Item;
import java.util.Set;

import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.EnumManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.CadseGCST;
import org.eclipse.jdt.core.IType;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class EnumValueAttribute
 {


  protected static String nl;
  public static synchronized EnumValueAttribute create(String lineSeparator)
  {
    nl = lineSeparator;
    EnumValueAttribute result = new EnumValueAttribute();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tpublic static final ";
  protected final String TEXT_3 = " ";
  protected final String TEXT_4 = "(Item ";
  protected final String TEXT_5 = ") {" + NL + "\t\tObject value = ";
  protected final String TEXT_6 = ".getAttribute(";
  protected final String TEXT_7 = "_);" + NL + "\t\treturn Convert.toEnum(value,";
  protected final String TEXT_8 = "_,";
  protected final String TEXT_9 = ".";
  protected final String TEXT_10 = ");" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tpublic static final void ";
  protected final String TEXT_11 = "(Item ";
  protected final String TEXT_12 = ", ";
  protected final String TEXT_13 = " value) {" + NL + "\t\ttry {" + NL + "\t\t\t";
  protected final String TEXT_14 = ".setAttribute(";
  protected final String TEXT_15 = "_, value);" + NL + "\t\t} catch (Throwable t) {" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_16 = NL;

/* (non-javadoc)
    * @see IGenerator#generate(Object)
    */
	public String generate(String shortName, Item attribute, Set<String> imports)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    	String cstAttribute = GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, attribute, null, null, null);
	
	String min_short_name = JavaIdentifier.javaIdentifierFromString(shortName,false,true, null);
	
	Item enumType = EnumManager.getEnumType(attribute);
	IType javaenumtype = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, enumType);
	String enumClass = javaenumtype.getElementName();
	
	ItemType it = attribute.getType();
	 
	 String defaultValue = (String) attribute.getAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_);
	 if (defaultValue == null || defaultValue.length() == 0)
	 	defaultValue = (String) it.getAttribute(CadseGCST.ATTRIBUTE_ITEM_TYPE_at_DEFAULT_VALUE_);
	
    stringBuffer.append(TEXT_2);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(GenerateJavaIdentifier.cstGetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(min_short_name );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(GenerateJavaIdentifier.cstSetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(min_short_name );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(enumClass);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cstAttribute);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}