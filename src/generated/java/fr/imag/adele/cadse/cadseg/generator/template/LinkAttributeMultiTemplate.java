package fr.imag.adele.cadse.cadseg.generator.template;

import fr.imag.adele.cadse.core.Item;
import java.util.Set;

import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class LinkAttributeMultiTemplate
 {


  protected static String nl;
  public static synchronized LinkAttributeMultiTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    LinkAttributeMultiTemplate result = new LinkAttributeMultiTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t/**" + NL + "\t\tget  links '";
  protected final String TEXT_2 = "' from '";
  protected final String TEXT_3 = "' to '";
  protected final String TEXT_4 = "'." + NL + "        @generated" + NL + "    */" + NL + "    static public List<Link> ";
  protected final String TEXT_5 = "Link(Item ";
  protected final String TEXT_6 = ") {" + NL + "        return ";
  protected final String TEXT_7 = ".getOutgoingLinks(";
  protected final String TEXT_8 = ");" + NL + "    }" + NL + "" + NL + "    /**" + NL + "        @generated" + NL + "    */" + NL + "    static public Collection<Item> ";
  protected final String TEXT_9 = "All(Item ";
  protected final String TEXT_10 = ") {" + NL + "        return ";
  protected final String TEXT_11 = ".getOutgoingItems(";
  protected final String TEXT_12 = ", false);" + NL + "    }" + NL + "" + NL + "    /**" + NL + "        @generated" + NL + "    */" + NL + "    static public Collection<Item> ";
  protected final String TEXT_13 = "(Item ";
  protected final String TEXT_14 = ") {" + NL + "        return ";
  protected final String TEXT_15 = ".getOutgoingItems(";
  protected final String TEXT_16 = ",true);" + NL + "    }" + NL + "" + NL + "    /**" + NL + "        @generated" + NL + "    */" + NL + "    static public void add";
  protected final String TEXT_17 = "(Item ";
  protected final String TEXT_18 = ", Item value) throws CadseException {";
  protected final String TEXT_19 = NL + "        ";
  protected final String TEXT_20 = ".addOutgoingItem(";
  protected final String TEXT_21 = ",value);" + NL + "    }" + NL + "" + NL + "    /**" + NL + "        @generated" + NL + "    */" + NL + "    static public void remove";
  protected final String TEXT_22 = "(Item ";
  protected final String TEXT_23 = ", Item value) throws CadseException {";
  protected final String TEXT_24 = NL + "        ";
  protected final String TEXT_25 = ".removeOutgoingItem(";
  protected final String TEXT_26 = ",value);" + NL + "    }";
  protected final String TEXT_27 = NL;

/* (non-javadoc)
    * @see IGenerator#generate(Object)
    */
	public String generate(String shortName, Item attribute, Set<String> imports)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	String upper_linktype_name = JavaIdentifier.javaIdentifierFromString(attribute.getName(),true,false,null);
	String cst_linktype = GenerateJavaIdentifier.cstQualifiedAttribute(ContextVariableImpl.DEFAULT, attribute, null, null, null);
	//<!--int max = LinkTypeManager.getMax(attribute);
	String min_short_name = JavaIdentifier.javaIdentifierFromString(shortName,false,true,null);

	Item  dest = LinkTypeManager.getDestination(attribute);
	Item source = LinkTypeManager.getSource(attribute);
	
    stringBuffer.append(TEXT_1);
    stringBuffer.append(attribute.getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append((source== null?"unknown":source.getName()));
    stringBuffer.append(TEXT_3);
    stringBuffer.append((dest== null?"unknown":dest.getName()));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(GenerateJavaIdentifier.cstGetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_5);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cst_linktype);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(GenerateJavaIdentifier.cstGetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cst_linktype);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(GenerateJavaIdentifier.cstGetAttribute(ContextVariableImpl.DEFAULT, attribute));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cst_linktype);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(upper_linktype_name);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cst_linktype);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(upper_linktype_name);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(min_short_name);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cst_linktype);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(TEXT_27);
    return stringBuffer.toString();
  }
}