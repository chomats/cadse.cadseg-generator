/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.cadseg.generator.template;

public class WizardActionTemplate
 {
  protected static String nl;
  public static synchronized WizardActionTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    WizardActionTemplate result = new WizardActionTemplate();
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
