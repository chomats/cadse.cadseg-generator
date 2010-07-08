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
 */

package fr.imag.adele.cadse.cadseg.generator.gclass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class GenerateClass.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class GenerateClass {

	/** The package name. */
	private String				_packageName;

	/** The class name. */
	private String				fClassName;

	/** The extended class name. */
	private String				fExtendedClassName;

	/** The extended package name. */
	private String				fExtendedPackageName;

	/** The generate class. */
	private boolean				fGenerateClass;

	/** The implements class name. */
	private String[]			fImplementsClassName		= null;

	/** The implements package name. */
	private String[]			fImplementsPackageName;

	/** The is class. */
	private boolean				isClass						= true;

	/** The can overwrite etends class. */
	private boolean				fCanOverwriteEtendsClass	= false;

	/** The type. */
	final IType					type;

	/** The cxt. */
	protected ContextVariable	cxt;

	public Set<String>			imports						= new TreeSet<String>();

	/**
	 * Instantiates a new generate class.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param generateClass
	 *            the generate class
	 * @param packageName
	 *            the package name
	 * @param className
	 *            the class name
	 * @param extendedClassName
	 *            the extended class name
	 * @param implementsClassName
	 *            the implements class name
	 * @param type
	 *            the type
	 */
	public GenerateClass(ContextVariable cxt, boolean generateClass, String packageName, String className,
			String extendedClassName, String implementsClassName, IType type, boolean canOverwriteEtendsClass) {
		super();
		this.cxt = cxt;

		fGenerateClass = generateClass;
		_packageName = packageName;
		fClassName = className;
		fExtendedClassName = extendedClassName;
		fCanOverwriteEtendsClass = canOverwriteEtendsClass;
		if (type != null && fCanOverwriteEtendsClass) {
			try {
				if (type.exists()) {
					String e = type.getSuperclassName();
					if (e != null) {
						fExtendedClassName = e;
					}
				}
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (implementsClassName != null) {
			fImplementsClassName = new String[] { implementsClassName };
		}

		this.type = type;

		init();
	}

	/**
	 * Instantiates a new generate class.
	 * 
	 * @param generateClass
	 *            the generate class
	 * @param className
	 *            the class name
	 * @param extendedClassName
	 *            the extended class name
	 * @param implementsClassName
	 *            the implements class name
	 * @param type
	 *            the type
	 */
	public GenerateClass(boolean generateClass, String className, String extendedClassName, String implementsClassName,
			IType type) {
		super();
		fGenerateClass = generateClass;
		fClassName = className;
		fExtendedClassName = extendedClassName;
		if (implementsClassName != null) {
			fImplementsClassName = new String[] { implementsClassName };
		}
		this.type = type;

		init();
	}

	/**
	 * Instantiates a new generate class.
	 * 
	 * @param generateClass
	 *            the generate class
	 * @param packageName
	 *            the package name
	 * @param className
	 *            the class name
	 * @param extendedClassName
	 *            the extended class name
	 * @param implementsClassName
	 *            the implements class name
	 * @param type
	 *            the type
	 */
	public GenerateClass(boolean generateClass, String packageName, String className, String extendedClassName,
			String[] implementsClassName, IType type) {
		super();
		_packageName = packageName;
		fGenerateClass = generateClass;
		fClassName = className;
		fExtendedClassName = extendedClassName;
		fImplementsClassName = implementsClassName;
		this.type = type;
		init();
	}

	/**
	 * Inits the.
	 */
	private void init() {
		if (fExtendedClassName != null) {
			String[] p = JavaIdentifier.getPackageAndClassName(fExtendedClassName);
			if (p[0] != null) {
				fExtendedClassName = p[1];
				fExtendedPackageName = p[0];
			}
		}
		if (fImplementsClassName != null) {
			fImplementsPackageName = new String[fImplementsClassName.length];
			for (int i = 0; i < fImplementsClassName.length; i++) {
				String s = fImplementsClassName[i];
				String[] p = JavaIdentifier.getPackageAndClassName(s);
				if (p[0] != null) {
					fImplementsClassName[i] = p[1];
					fImplementsPackageName[i] = p[0];
				}
			}
		}

	}

	/**
	 * Generate class.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	public void generateClass(GenStringBuilder sb, Set<String> imports, GenContext context) {

		// String extendsClassName = fExtendedClassName;
		if (fExtendedClassName != null && fExtendedPackageName != null) {
			if (_packageName == null || !_packageName.equals(fExtendedPackageName)) {
				imports.add(fExtendedPackageName + "." + fExtendedClassName);
			}
		}

		if (fGenerateClass) {
			sb.newline();
			sb.appendGeneratedTag();
			sb.newline().append("public ");
			if (isClass) {
				sb.append("class ");
			} else {
				sb.append("interface ");
			}
			sb.append(fClassName);
			if (isClass && fExtendedClassName != null) {
				sb.append(" extends ").append(fExtendedClassName);
			}
			if (fImplementsClassName != null) {
				for (int i = 0; i < fImplementsClassName.length; i++) {
					String implClassName = fImplementsClassName[i];
					String packageName = fImplementsPackageName[i];
					if (packageName != null && (_packageName == null || !_packageName.equals(packageName))) {
						imports.add(packageName + "." + implClassName);
					}
				}
			}

			String[] implementsClassName = getImplementsClassName();
			if (implementsClassName != null && implementsClassName.length != 0) {
				if (isClass) {
					sb.append(" implements ");
				} else {
					sb.append(" extends ");
				}
				for (String itf : implementsClassName) {
					sb.append(" ").append(itf).append(",");
				}
				sb.decrementLength();
			}
			sb.append(" {");

			sb.begin();
			generateAttributes(sb, imports, context);
			sb.newline();
			if (isClass) {
				generateConstructor(sb, imports, context);
			}
			generateMethods(sb, imports, context);
			sb.end();
			sb.newline();
			sb.newline().append("}");
			sb.newline();

		}
	}

	/**
	 * Generate constructor.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	protected void generateConstructor(GenStringBuilder sb, Set<String> imports, GenContext context) {
		sb.appendGeneratedTag();
		sb.newline().append("public ").append(fClassName).append("(");
		generateConstructorParameter(sb);
		sb.decrementLength();
		sb.append(") {");
		if (isClass && fExtendedClassName != null) {
			sb.newline().append("	super(");
			generateConstrustorArguments(sb);
			sb.decrementLength();
			sb.append(");");
		}
		sb.newline().append("}");
	}

	/**
	 * Gets the implements class name.
	 * 
	 * @return the implements class name
	 */
	protected String[] getImplementsClassName() {
		HashSet<String> ret = new HashSet<String>();
		if (fImplementsClassName != null) {
			ret.addAll(Arrays.asList(fImplementsClassName));
		}

		try {
			if (type != null && type.exists()) {
				String[] itfs;
				itfs = type.getSuperInterfaceNames();
				ret.addAll(Arrays.asList(itfs));
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret.toArray(new String[ret.size()]);
	}

	/**
	 * Generate attributes.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	protected void generateAttributes(GenStringBuilder sb, Set<String> imports, GenContext context) {
	}

	/**
	 * Generate methods.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	protected void generateMethods(GenStringBuilder sb, Set<String> imports, GenContext context) {
	}

	/**
	 * Gets the class name.
	 * 
	 * @return the class name
	 */
	public String getClassName() {
		return fClassName;
	}

	/**
	 * Gets the extend class name.
	 * 
	 * @return the extend class name
	 */
	protected String getExtendClassName() {
		return fExtendedClassName;
	}

	/**
	 * Checks if is generate class.
	 * 
	 * @return true, if is generate class
	 */
	protected boolean isGenerateClass() {
		return fGenerateClass;
	}

	/**
	 * Generate call arguments.
	 * 
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	protected void generateCallArguments(GenStringBuilder sb, Set<String> imports, GenContext context) {
	}

	/**
	 * Generate construstor arguments.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstrustorArguments(GenStringBuilder sb) {
	}

	/**
	 * Generate constructor parameter.
	 * 
	 * @param sb
	 *            the sb
	 */
	protected void generateConstructorParameter(GenStringBuilder sb) {
	}

	/**
	 * Compute imports package.
	 * 
	 * @param imports
	 *            the imports
	 */
	public void computeImportsPackage(Set<String> imports) {
		String className = getExtendClassName();
		if (className != null) {
			String packageName = JavaIdentifier.packageName(className);
			imports.add(packageName);
		}
	}

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public String getContent() {
		GenStringBuilder sb = new GenStringBuilder();

		GenContext context = new GenContext(null);
		generateClass(sb, imports, context);

		GenStringBuilder sb2 = new GenStringBuilder();
		String license = getLicense();
		if (license != null) {
			sb.append(license).append("\n");
		}
		sb2.append("package ").append(_packageName).append(";");
		sb2.newline();
		sb2.newline();
		for (String itf : imports) {
			sb2.newline().append("import ").append(itf).append(";");
		}
		sb2.newline();
		sb2.newline();
		sb2.append(sb.toString());

		sb2.newline();

		return sb2.toString();

	}

	protected String getLicense() {
		Item cadseDefinition = getCadseDefinition();
		if (cadseDefinition == null) {
			return null;
		}

		return GenerateJavaIdentifier.ow(getCadseDefinition(), "license", null);
	}

	protected Item getCadseDefinition() {
		return null;
	}

	/**
	 * Gets the package name.
	 * 
	 * @return the package name
	 */
	public String getPackageName() {
		return _packageName;
	}

	/**
	 * Gets the user class name.
	 * 
	 * @return the user class name
	 */
	public String getUserClassName() {
		if (isGenerateClass()) {
			return getClassName();
		}
		if (fExtendedClassName != null) {
			return JavaIdentifier.getlastclassName(fExtendedClassName);
		}
		throw new IllegalArgumentException("Cannot find the user class name");
	}

	/**
	 * Generate instance.
	 * 
	 * @param var
	 *            the var
	 * @param sb
	 *            the sb
	 * @param imports
	 *            the imports
	 * @param context
	 *            the context
	 */
	public void generateInstance(String var, GenStringBuilder sb, Set<String> imports, GenContext context) {
		String userClassName = getUserClassName();
		sb.newline().append(userClassName).append(" ").append(var).append(" = ");

		sb.append("new ").append(userClassName).append("(");
		generateCallArguments(sb, imports, null);
		sb.decrementLength();
		sb.append(");");
	}

	/**
	 * Sets the class.
	 * 
	 * @param isClass
	 *            the new class
	 */
	public void setClass(boolean isClass) {
		this.isClass = isClass;
	}

	public void setCanOverwriteEtendsClass(boolean canOverwriteEtendsClass) {
		fCanOverwriteEtendsClass = canOverwriteEtendsClass;
	}
}