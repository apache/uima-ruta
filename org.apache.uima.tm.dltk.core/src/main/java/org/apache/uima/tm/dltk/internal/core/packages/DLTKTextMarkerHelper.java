/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
*/

package org.apache.uima.tm.dltk.internal.core.packages;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.uima.tm.dltk.core.TextMarkerPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.ScriptLaunchUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class DLTKTextMarkerHelper {

  private static final String DLTK_TM = "scripts/dltk.tm"; //$NON-NLS-1$

  public static List getScriptOutput(InputStream stream) {
    final List elements = new ArrayList();
    final BufferedReader input = new BufferedReader(new InputStreamReader(stream));
    Thread t = new Thread(new Runnable() {
      public void run() {
        try {
          while (true) {
            String line;
            line = input.readLine();
            if (line == null) {
              break;
            }
            elements.add(line);
          }
        } catch (IOException e) {
          if (DLTKCore.DEBUG) {
            e.printStackTrace();
          }
        }
      }
    });
    t.start();
    try {
      t.join(50000);// No more then 50 seconds
    } catch (InterruptedException e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }
    return elements;
  }

  private static List deployExecute(IExecutionEnvironment exeEnv, String installLocation,
          String[] arguments, EnvironmentVariable[] env) {
    IDeployment deployment = exeEnv.createDeployment();
    IFileHandle script = deploy(deployment);
    if (script == null) {
      return null;
    }

    IFileHandle workingDir = script.getParent();
    InterpreterConfig config = ScriptLaunchUtil.createInterpreterConfig(exeEnv, script, workingDir,
            env);
    // For wish
    config.removeEnvVar("DISPLAY"); //$NON-NLS-1$

    if (arguments != null) {
      config.addScriptArgs(arguments);
    }

    Process process = null;
    try {
      process = ScriptLaunchUtil.runScriptWithInterpreter(exeEnv, installLocation, config);
    } catch (CoreException e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }
    if (process == null) {
      return new ArrayList();
    }
    List output = getScriptOutput(process.getInputStream());
    getScriptOutput(process.getErrorStream());
    process.destroy();
    deployment.dispose();
    return output;
  }

  private static IFileHandle deploy(IDeployment deployment) {
    IFileHandle script;
    try {
      IPath path = deployment.add(TextMarkerPlugin.getDefault().getBundle(), DLTK_TM);
      script = deployment.getFile(path);
    } catch (IOException e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
      return null;
    }
    return script;
  }

  public static String[] getDefaultPath(IFileHandle installLocation,
          EnvironmentVariable[] environment) {
    // Process process = deployExecute(installLocation.getAbsolutePath(),
    // new String[] { "get-paths" }, environment);
    // List content = getScriptOutput(process);
    // String[] autoPath = getAutoPath(content);
    // for (int i = 0; i < autoPath.length; i++) {
    // Path p = new Path(autoPath[i]);
    // if (p.lastSegment().startsWith("tcl8.")) {
    // return new String[] { autoPath[i] };
    // }
    // }
    // process.destroy();
    return new String[0];
    // return autoPath;
  }

  public static TextMarkerPackage[] getSrcs(IExecutionEnvironment exeEnv,
          IFileHandle installLocation, EnvironmentVariable[] environment, String packageName) {
    IDeployment deployment = exeEnv.createDeployment();
    IFileHandle script = deploy(deployment);
    if (script == null) {
      return null;
    }

    IFileHandle workingDir = script.getParent();
    InterpreterConfig config = ScriptLaunchUtil.createInterpreterConfig(exeEnv, script, workingDir,
            environment);
    String names = packageName;
    ByteArrayInputStream bais = new ByteArrayInputStream(names.getBytes());
    IPath packagesPath = null;
    try {
      packagesPath = deployment.add(bais, "packages.txt"); //$NON-NLS-1$
    } catch (IOException e1) {
      if (DLTKCore.DEBUG) {
        e1.printStackTrace();
      }
      return null;
    }
    IFileHandle file = deployment.getFile(packagesPath);
    // For wish
    config.removeEnvVar("DISPLAY"); //$NON-NLS-1$
    String[] arguments = new String[] { "get-srcs", "-fpkgs", //$NON-NLS-1$ //$NON-NLS-2$
        file.toOSString() };

    config.addScriptArgs(arguments);

    Process process = null;
    try {
      process = ScriptLaunchUtil.runScriptWithInterpreter(exeEnv, installLocation.toOSString(),
              config);
    } catch (CoreException e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }
    if (process == null) {
      return null;
    }
    List output = getScriptOutput(process.getInputStream());
    getScriptOutput(process.getErrorStream());
    process.destroy();
    deployment.dispose();
    return getPackagePath(output);
  }

  private static boolean isElementName(Node nde, String name) {
    if (nde != null) {
      if (nde.getNodeType() == Node.ELEMENT_NODE) {
        if (name.equalsIgnoreCase(nde.getNodeName())) {
          return true;
        }
      }
    }
    return false;
  }

  private static String[] getAutoPath(List content) {
    String text = getXMLContent(content);
    Document document = getDocument(text);

    Set paths = new HashSet();
    if (document != null) {
      Element element = document.getDocumentElement();
      NodeList childNodes = element.getChildNodes();
      int len = childNodes.getLength();
      for (int i = 0; i < len; i++) {
        Node nde = childNodes.item(i);
        if (isElementName(nde, "path")) { //$NON-NLS-1$
          Element el = (Element) nde;
          String path = el.getAttribute("name"); //$NON-NLS-1$
          if (path.length() > 0) {
            paths.add(path);
          }
        }
      }
    }
    return (String[]) paths.toArray(new String[paths.size()]);
  }

  public static class TextMarkerPackage {
    private String name;

    private Set paths = new HashSet();

    private Set dependencies = new HashSet();

    public TextMarkerPackage(String name) {
      this.name = name;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      TextMarkerPackage other = (TextMarkerPackage) obj;
      if (name == null) {
        if (other.name != null)
          return false;
      } else if (!name.equals(other.name))
        return false;
      return true;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Set getPaths() {
      return paths;
    }

    public void setPaths(Set paths) {
      this.paths = paths;
    }

    public Set getDependencies() {
      return dependencies;
    }

    public void setDependencies(Set dependencies) {
      this.dependencies = dependencies;
    }

    @Override
    public String toString() {
      StringBuffer sb = new StringBuffer(128);
      sb.append("TextMarkerPackage"); //$NON-NLS-1$
      sb.append('{');
      sb.append("name=").append(name); //$NON-NLS-1$
      sb.append(' ');
      sb.append("paths=").append(paths); //$NON-NLS-1$
      sb.append(' ');
      sb.append("dependencies=").append(dependencies); //$NON-NLS-1$
      sb.append('}');
      return sb.toString();
    }
  };

  public static TextMarkerPackage[] getPackagePath(List content) {
    String text = getXMLContent(content);
    Document document = getDocument(text);

    Map packages = new HashMap();
    if (document != null) {
      Element element = document.getDocumentElement();
      NodeList childNodes = element.getChildNodes();
      int len = childNodes.getLength();
      for (int i = 0; i < len; i++) {
        Node nde = childNodes.item(i);
        if (isElementName(nde, "path")) { //$NON-NLS-1$
          Element el = (Element) nde;
          NodeList elChilds = el.getChildNodes();
          for (int j = 0; j < elChilds.getLength(); j++) {
            Node pkgNde = elChilds.item(j);
            if (isElementName(pkgNde, "package")) { //$NON-NLS-1$
              populatePackage(packages, pkgNde);
            }
          }
        }
      }
    }
    return (TextMarkerPackage[]) packages.values().toArray(new TextMarkerPackage[packages.size()]);
  }

  private static void populatePackage(Map packages, Node pkgNde) {
    Element pkg = (Element) pkgNde;
    String pkgName = pkg.getAttribute("name"); //$NON-NLS-1$
    TextMarkerPackage tclPackage = new TextMarkerPackage(pkgName);
    if (packages.containsKey(tclPackage)) {
      tclPackage = (TextMarkerPackage) packages.get(tclPackage);
    } else {
      packages.put(tclPackage, tclPackage);
    }
    NodeList childs = pkg.getChildNodes();
    for (int i = 0; i < childs.getLength(); i++) {
      Node nde = childs.item(i);
      if (isElementName(nde, "source")) { //$NON-NLS-1$
        Element el = (Element) nde;
        String name = el.getAttribute("name"); //$NON-NLS-1$
        IPath path = new Path(name).removeLastSegments(1);
        tclPackage.getPaths().add(path);
      } else if (isElementName(nde, "require")) { //$NON-NLS-1$
        Element el = (Element) nde;
        String name = el.getAttribute("name"); //$NON-NLS-1$
        tclPackage.getDependencies().add(name);
      }
    }
  }

  private static Document getDocument(String text) {
    try {
      DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      parser.setErrorHandler(new DefaultHandler());
      Document document = parser.parse(new ByteArrayInputStream(text.getBytes()));
      return document;
    } catch (IOException e) {

    } catch (ParserConfigurationException e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    } catch (SAXException e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }
    return null;
  }

  private static String getXMLContent(List content) {
    StringBuffer newList = new StringBuffer();
    if (content != null) {
      for (Iterator iterator = content.iterator(); iterator.hasNext();) {
        String line = (String) iterator.next();
        if (line.trim().startsWith("<")) { //$NON-NLS-1$
          newList.append(line).append("\n"); //$NON-NLS-1$
        }
      }
    }
    return newList.toString();
  }

  public static Set getPackages(IInterpreterInstall install) {
    IExecutionEnvironment exeEnv = install.getExecEnvironment();
    List content = deployExecute(exeEnv, install.getInstallLocation().toOSString(),
            new String[] { "get-pkgs" }, install //$NON-NLS-1$
                    .getEnvironmentVariables());
    Set packages = new HashSet();
    TextMarkerPackage[] packagePath = getPackagePath(content);
    for (int i = 0; i < packagePath.length; i++) {
      packages.add(packagePath[i].getName());
    }
    return packages;
  }
}
