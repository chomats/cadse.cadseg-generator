

package fr.imag.adele.cadse.cadseg.generator.template;

import fr.imag.adele.cadse.cadseg.managers.view.model.ViewCategoryModel;
import fr.imag.adele.cadse.cadseg.managers.view.model.ViewModel;
import fr.imag.adele.cadse.cadseg.managers.view.model.ViewModels;

public class ViewExtensionTemplate
 {


  protected static String nl;
  public static synchronized ViewExtensionTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ViewExtensionTemplate result = new ViewExtensionTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? ("\n") : nl;
  protected final String TEXT_1 = NL + "\t<extension" + NL + "         point=\"org.eclipse.ui.views\">";
  protected final String TEXT_2 = NL + "      <category" + NL + "            name=\"";
  protected final String TEXT_3 = "\"" + NL + "            id=\"";
  protected final String TEXT_4 = "\">" + NL + "      </category> ";
  protected final String TEXT_5 = NL + "      <view" + NL + "            name=\"";
  protected final String TEXT_6 = "\"";
  protected final String TEXT_7 = NL + "            icon=\"";
  protected final String TEXT_8 = "\"";
  protected final String TEXT_9 = NL + "            category=\"";
  protected final String TEXT_10 = "\"";
  protected final String TEXT_11 = NL + "            class=\"";
  protected final String TEXT_12 = "\"" + NL + "            id=\"";
  protected final String TEXT_13 = "\"/>";
  protected final String TEXT_14 = NL + "   </extension>";

/* (non-javadoc)
    * @see IGenerator#generate(Object)
    */
	public String generate(ViewModels viewmodels)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
     for(ViewCategoryModel vc : viewmodels.categories) { 
    stringBuffer.append(TEXT_2);
    stringBuffer.append(vc.name);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(vc.id);
    stringBuffer.append(TEXT_4);
    }
     for(ViewModel v : viewmodels.views) { 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(v.name);
    stringBuffer.append(TEXT_6);
    if (v.icon!=null) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(v.icon);
    stringBuffer.append(TEXT_8);
    }
    if (v.category!=null) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(v.category);
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    stringBuffer.append(v.qualifiedClassName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(v.id);
    stringBuffer.append(TEXT_13);
    }
    stringBuffer.append(TEXT_14);
    return stringBuffer.toString();
  }
}