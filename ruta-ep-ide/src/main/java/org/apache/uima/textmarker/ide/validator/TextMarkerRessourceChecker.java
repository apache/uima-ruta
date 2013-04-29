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

package org.apache.uima.textmarker.ide.validator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerRessourceReference;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension;
import org.eclipse.dltk.core.builder.ISourceLineTracker;

public class TextMarkerRessourceChecker implements IBuildParticipant, IBuildParticipantExtension {

  private class TypeCheckerVisitor extends ASTVisitor {
    private IProblemReporter rep;

    private String currentFile;

    private ISourceLineTracker linetracker;

    private Set<String> types;

    private TextMarkerCheckerProblemFactory problemFactory;

    private String packageName;

    private static final String errMsgHead = "Cannot find resource \"";

    private static final String errMsgTailDefault = " \" in resource location.";

    public TypeCheckerVisitor(IProblemReporter rep, ISourceLineTracker linetracker, String curFile,
            Set<String> types) {
      this.problemFactory = new TextMarkerCheckerProblemFactory(curFile, linetracker);
      this.linetracker = linetracker;
      this.rep = rep;
      this.packageName = "";
      this.currentFile = curFile;
      this.types = types;
    }

    @Override
    public boolean visit(Expression s) throws Exception {
      // traverse everything but RessourceReference
      if (!(s instanceof TextMarkerRessourceReference)) {
        return true;
      }
      TextMarkerRessourceReference resRef = (TextMarkerRessourceReference) s;
      if (TextMarkerCheckerUtils.checkRessourceExistence(resRef.getValue(), project)) {
        return false;
      }
      // ressource not valid
      String errMsg = errMsgHead + resRef.getValue() + errMsgTailDefault;
      IProblem problem = new TextMarkerCheckerDefaultProblem(currentFile, errMsg, resRef,
              linetracker.getLineNumberOfOffset(resRef.sourceStart()));
      rep.reportProblem(problem);
      return false;
    }
  }

  public TextMarkerRessourceChecker(IScriptProject project) throws CoreException {
    this.project = project;
  }

  private IScriptProject project;

  public boolean beginBuild(int buildType) {
    return true;
  }

  public void endBuild(IProgressMonitor monitor) {
  }

  public void build(IBuildContext context) throws CoreException {
    // getAST:
    Object mdObj = context.get(IBuildContext.ATTR_MODULE_DECLARATION);
    if (!(mdObj instanceof ModuleDeclaration)) {
      return;
    }
    ModuleDeclaration md = (ModuleDeclaration) mdObj;
    IProblemReporter problemReporter = context.getProblemReporter();
    ISourceModule smod = context.getSourceModule();

    // get Types:
    Set<String> types = null;
    try {
      types = importBasicTypeSystem();
    } catch (InvalidXMLException e1) {
      System.err.println("ERROR: Failed to get BasicTypeSystem!! " + this.toString());
      // problemReporter.reportProblem(problem)
    } catch (IOException e1) {
      System.err.println("ERROR: Failed to get BasicTypeSystem!! " + this.toString());
    }
    if (types == null) {
      types = new HashSet<String>();
    }

    // traverse:
    ISourceLineTracker linetracker = context.getLineTracker();
    String fileName = smod.getElementName();
    try {
      ASTVisitor visitor = new TypeCheckerVisitor(problemReporter, linetracker, fileName, types);
      md.traverse(visitor);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Set<String> importBasicTypeSystem() throws InvalidXMLException, IOException {
    final String basicXmlFile = "BasicTypeSystem";
    Set<String> types = importTypeSystem(basicXmlFile);
    types.add("Annotation");
    types.add("DocumentAnnotation");
    return types;
  }

  /**
   * @param xmlFilePath
   *          absolute full path. i.e.: "org.apache.uima.mytypes" ".xml" will be added.
   * @return
   * @throws InvalidXMLException
   * @throws IOException
   *           when file not found
   */
  private Set<String> importTypeSystem(String xmlFilePath) throws InvalidXMLException, IOException {
    IFolder folder = project.getProject().getFolder(
            TextMarkerProjectUtils.getDefaultDescriptorLocation());
    String fileExtended = xmlFilePath + ".xml";
    return getTypes(folder, fileExtended);
  }

  private Set<String> getTypes(IFolder folder, String filePath) throws InvalidXMLException,
          IOException {
    Set<String> types = new HashSet<String>();
    IFile iFile = getFile(folder, filePath);
    URL url;
    try {
      url = iFile.getLocationURI().toURL();
      types = getTypes(url);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return types;
  }

  private IFile getFile(IFolder folder, String filePath) {
    int lastDot = filePath.lastIndexOf('.');
    int sndLastDot = filePath.lastIndexOf('.', lastDot - 1);
    String fName = filePath;
    if (sndLastDot >= 0) {
      String subFolder = filePath.substring(0, sndLastDot);
      folder = folder.getFolder(subFolder);
      fName = filePath.substring(sndLastDot + 1);
    }
    return folder.getFile(fName);
  }

  private Set<String> getTypes(URL resource) throws IOException, InvalidXMLException {
    Set<String> types = new HashSet<String>();
    TypeSystemDescription typeSysDescr = null;
    typeSysDescr = UIMAFramework.getXMLParser().parseTypeSystemDescription(
            new XMLInputSource(resource));
    for (TypeDescription each : typeSysDescr.getTypes()) {
      String name = each.getName();
      String[] nameSpace = name.split("[.]");
      types.add(nameSpace[nameSpace.length - 1]);
      StringBuffer fullName = new StringBuffer();
      if (nameSpace.length > 0) {
        fullName.append(nameSpace[0]);
      }
      for (int i = 1; i < nameSpace.length; i++) {
        // TODO fix workaround
        if (!nameSpace[i].equals("type")) {
          fullName.append('.');
          fullName.append(nameSpace[i]);
        }
      }
      types.add(fullName.toString());
    }
    return types;
  }
}
