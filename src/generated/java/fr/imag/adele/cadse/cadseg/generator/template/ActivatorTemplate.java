

package fr.imag.adele.cadse.cadseg.generator.template;

import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.composition.java.IPDETemplate;

public class ActivatorTemplate implements IPDETemplate
 {
  protected static String nl;
  public static synchronized ActivatorTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ActivatorTemplate result = new ActivatorTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import org.eclipse.core.runtime.IStatus;" + NL + "import org.eclipse.core.runtime.Plugin;" + NL + "import org.osgi.framework.BundleContext;" + NL + "" + NL + "/**" + NL + "\t@generated" + NL + "*/" + NL + "public class ";
  protected final String TEXT_3 = " extends Plugin {" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tpublic static String PLUGIN_ID = \"";
  protected final String TEXT_4 = "\";" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tprivate static Activator _default;" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tpublic Activator() {" + NL + "\t\tActivator._default = this;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic void start(BundleContext context) throws Exception {" + NL + "\t\tsuper.start(context);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\t@Override" + NL + "\tpublic void stop(BundleContext context) throws Exception {" + NL + "\t\tsuper.stop(context);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tpublic static Activator getDefault() {" + NL + "\t\treturn _default;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t\t@generated" + NL + "\t*/" + NL + "\tpublic void log(IStatus status) {" + NL + "\t\tthis.getLog().log(status);" + NL + "\t}" + NL + "}";
  protected final String TEXT_5 = NL;

/* (non-javadoc)
    * @see IGenerator#generate(Object)
    */
	public String generate(EclipsePluginContentManger.PDEGenerateModel info)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(info.packageName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append( info.activatorName );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(info.pluginID);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}