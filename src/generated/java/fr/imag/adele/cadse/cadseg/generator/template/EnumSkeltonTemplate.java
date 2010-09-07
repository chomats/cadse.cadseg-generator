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
