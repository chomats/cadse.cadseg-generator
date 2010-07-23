package fr.imag.adele.cadse.cadseg.generator.template;

import java.util.List;

public class EnumSkeltonTemplate
 {
 	
	
  protected static String nl;
  public static synchronized EnumSkeltonTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    EnumSkeltonTemplate result = new EnumSkeltonTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "/**" + NL + "\t@generated" + NL + "*/" + NL + "public enum ";
  protected final String TEXT_3 = "  {" + NL + "\t";
  protected final String TEXT_4 = NL + "\t";
  protected final String TEXT_5 = ",";
  protected final String TEXT_6 = NL + "}";
  protected final String TEXT_7 = NL;

/* (non-javadoc)
    * @see IGenerator#generate(Object)
    */
	public String generate(String packageName, String className, List values)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(packageName );
    stringBuffer.append(TEXT_2);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_3);
     for(Object v : values) { 
    stringBuffer.append(TEXT_4);
    stringBuffer.append(v.toString());
    stringBuffer.append(TEXT_5);
    }
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}