


package fr.imag.adele.cadse.cadseg.generator.template;

public class PageActionTemplate
 {
  protected static String nl;
  public static synchronized PageActionTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    PageActionTemplate result = new PageActionTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import org.eclipse.core.runtime.IProgressMonitor;" + NL + "import fede.workspace.model.manager.properties.impl.AbstractItemActionController;" + NL + "import fede.workspace.tool.eclipse.IFieldDescription;" + NL + "" + NL + "public class ";
  protected final String TEXT_3 = " extends AbstractItemActionController {" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic void init(IFieldDescription fd) {" + NL + "\t\tsuper.init(fd);" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic void doCancel(IProgressMonitor monitor) {" + NL + "\t\tsuper.doCancel(monitor);" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic void doFinish(IProgressMonitor monitor) throws Exception {" + NL + "\t\tsuper.doFinish(monitor);" + NL + "\t}" + NL + "}";
  protected final String TEXT_4 = NL;

/* (non-javadoc)
    * @see IGenerator#generate(Object)
    */
	public String generate(String className, String packageName)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(packageName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}