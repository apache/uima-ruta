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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerCorePreferences;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.apache.uima.textmarker.ide.parser.ast.TMStatementConstants;
import org.apache.uima.textmarker.ide.parser.ast.TMTypeConstants;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerBlock;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerDeclareDeclarationsStatement;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerFeatureDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerImportStatement;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerPackageDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerTypeDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerVariableDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerVariableReference;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension;
import org.eclipse.dltk.core.builder.ISourceLineTracker;

public class TextMarkerTypeChecker implements IBuildParticipant, IBuildParticipantExtension {

  private class TypeCheckerVisitor extends ASTVisitor {
    private final Stack<String> blocks;

    private final Set<String> typeVariables;

    private final Set<String> otherVariables;

    private IProblemReporter rep;

    private ISourceModule currentFile;

    private ISourceLineTracker linetracker;

    private Set<String> completeTypes;

    private Set<String> shortTypes;

    private TextMarkerCheckerProblemFactory problemFactory;

    private String packageName;

    private TypeSystemDescription description;

    private Set<String> otherTypes;

    private Set<String> finalTypes;

    private boolean reportWarningOnShortNames;

    public TypeCheckerVisitor(IProblemReporter rep, ISourceLineTracker linetracker,
            ISourceModule curFile, Set<String> completeTypes, Set<String> shortTypes) {
      this.problemFactory = new TextMarkerCheckerProblemFactory(curFile.getElementName(),
              linetracker);
      this.linetracker = linetracker;
      this.rep = rep;
      this.typeVariables = new HashSet<String>();
      this.otherVariables = new HashSet<String>();
      this.blocks = new Stack<String>();
      this.packageName = "";
      this.currentFile = curFile;
      this.completeTypes = completeTypes;
      this.shortTypes = shortTypes;
      this.otherTypes = new HashSet<String>();
      Set<String> uimaPredefTypes = new HashSet<String>();
      uimaPredefTypes.addAll(Arrays.asList(new String[] { "uima.cas.Boolean", "uima.cas.Byte",
          "uima.cas.Short", "uima.cas.Integer", "uima.cas.Long", "uima.cas.Float",
          "uima.cas.Double", "uima.cas.String", "uima.cas.BooleanArray", "uima.cas.ByteArray",
          "uima.cas.ShortArray", "uima.cas.IntegerArray", "uima.cas.LongArray",
          "uima.cas.FloatArray", "uima.cas.DoubleArray", "uima.cas.StringArray",
          "uima.cas.FSArray", "uima.cas.AnnotationBase", "uima.tcas.Annotation",
          "uima.tcas.DocumentAnnotation", "uima.cas.FloatList", "uima.cas.IntegerList",
          "uima.cas.StringList", "uima.cas.FSList", "uima.cas.EmptyFloatList",
          "uima.cas.EmptyIntegerList", "uima.cas.EmptyStringList", "uima.cas.EmptyFSList",
          "uima.cas.NonEmptyFloatList", "uima.cas.NonEmptyIntegerList",
          "uima.cas.NonEmptyStringList", "uima.cas.NonEmptyFSList" }));
      for (String string : uimaPredefTypes) {
        int indexOf = string.lastIndexOf('.');
        otherTypes.add(string);
        otherTypes.add(string.substring(indexOf + 1, string.length()));
      }
      this.finalTypes = new HashSet<String>();
      Set<String> uimaFinalTypes = new HashSet<String>();
      uimaFinalTypes.addAll(Arrays.asList(new String[] { "uima.cas.Boolean", "uima.cas.Byte",
          "uima.cas.Short", "uima.cas.Integer", "uima.cas.Long", "uima.cas.Float",
          "uima.cas.Double", "uima.cas.BooleanArray", "uima.cas.ByteArray", "uima.cas.ShortArray",
          "uima.cas.IntegerArray", "uima.cas.LongArray", "uima.cas.FloatArray",
          "uima.cas.DoubleArray", "uima.cas.StringArray", "uima.cas.FSArray" }));

      for (String string : uimaFinalTypes) {
        int indexOf = string.lastIndexOf('.');
        finalTypes.add(string);
        finalTypes.add(string.substring(indexOf + 1, string.length()));
      }

      try {
        this.description = getTypeSystemOfScript();
      } catch (InvalidXMLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      Preferences store = TextMarkerIdePlugin.getDefault().getPluginPreferences();
      reportWarningOnShortNames = !store
              .getBoolean(TextMarkerCorePreferences.BUILDER_IGNORE_DUPLICATE_SHORTNAMES);
    }

    @Override
    public boolean endvisit(MethodDeclaration s) throws Exception {
      if (s instanceof TextMarkerBlock) {
        blocks.pop();
      }
      return super.endvisit(s);
    }

    public boolean visit(MethodDeclaration s) throws Exception {
      if (s instanceof TextMarkerBlock) {
        TextMarkerBlock b = (TextMarkerBlock) s;
        blocks.push(b.getName());
      }
      return true;
    }

    @Override
    public boolean visit(Statement s) throws Exception {
      if (s instanceof TextMarkerPackageDeclaration) {
        this.packageName = ((TextMarkerPackageDeclaration) s).getName();
        return false;
      }
      if (s instanceof TextMarkerDeclareDeclarationsStatement) {
        TextMarkerDeclareDeclarationsStatement dds = (TextMarkerDeclareDeclarationsStatement) s;
        ASTNode parent = dds.getParent();
        if (parent != null && parent instanceof TextMarkerVariableReference) {
          TextMarkerVariableReference p = (TextMarkerVariableReference) parent;
          String name = p.getName();
          if (finalTypes.contains(name)) {
            IProblem problem = problemFactory.createInheritenceFinalProblem(p);
            rep.reportProblem(problem);
          }
        }
        return true;
      }

      if (s instanceof TextMarkerVariableDeclaration) {
        TextMarkerVariableDeclaration newVar = (TextMarkerVariableDeclaration) s;
        // if (otherVariables.contains(newVar.getName())) {
        // IProblem problem = problemFactory
        // .createVarAlreadyDeclaredProblem(newVar);
        // rep.reportProblem(problem);
        // return false;
        // }
        if (shortTypes.contains(newVar.getName())) {
          IProblem problem = problemFactory.createIdConflictsWithTypeProblem(newVar);
          rep.reportProblem(problem);
          return false;
        }
        if ((newVar.getType() & TMTypeConstants.TM_TYPE_AT) != 0) {
          typeVariables.add(newVar.getName());
          return false;
        }
        otherVariables.add(newVar.getName());
        return false;
      }
      // if (s instanceof TextMarkerFeatureDeclaration) {
      // TextMarkerFeatureDeclaration fd = (TextMarkerFeatureDeclaration) s;
      // }
      if (s instanceof TextMarkerTypeDeclaration) {
        TextMarkerTypeDeclaration newType = (TextMarkerTypeDeclaration) s;
        if (reportWarningOnShortNames && shortTypes.contains(newType.getName())) {
          IProblem problem = problemFactory.createDuplicateShortName(newType,
                  TextMarkerCheckerDefaultProblem.WARNING);
          rep.reportProblem(problem);
          return false;
        }
        String longName = getLongLocalName(newType.getName());
        if (completeTypes.contains(longName)) {
          IProblem problem = problemFactory.createIdConflictsWithTypeProblem(newType);
          rep.reportProblem(problem);
          return false;
        }
        if (typeVariables.contains(newType.getName()) || otherVariables.contains(newType.getName())) {
          IProblem problem = problemFactory.createIdConflictsWithVariableProblem(newType);
          rep.reportProblem(problem);
          return false;
        }
        List<TextMarkerFeatureDeclaration> features = newType.getFeatures();
        if (features != null) {

          for (TextMarkerFeatureDeclaration each : features) {
            String type = each.getType();
            if (type.equals("INT") || type.equals("STRING") || type.equals("DOUBLE")|| type.equals("FLOAT")
                    || type.equals("BOOLEAN")) {
              continue;
            }
            if (!shortTypes.contains(type) && !completeTypes.contains(type)
                    && !otherTypes.contains(type)) {
              IProblem problem = problemFactory.createUnknownFeatureTypeProblem(each);
              rep.reportProblem(problem);
            }
          }
        }
        String[] nameSplit = newType.getName().split("[.]");
        String shortName = nameSplit[nameSplit.length - 1];

        completeTypes.add(longName);
        shortTypes.add(shortName);
        return false;
      }
      if (s instanceof TextMarkerImportStatement) {
        // handle type system imports
        if (((TextMarkerImportStatement) s).getType() == TMStatementConstants.S_IMPORT_TYPESYSTEM) {
          SimpleReference sRef = (SimpleReference) ((TextMarkerImportStatement) s).getExpression();
          String localPath = sRef.getName();
          try {
            boolean checkTypeSystemImport = TextMarkerCheckerUtils.checkTypeSystemImport(localPath,
                    project);
            if (!checkTypeSystemImport) {
              rep.reportProblem(problemFactory.createFileNotFoundProblem(sRef, localPath));
            }
            Set<String> importedTypes = importTypeSystem(localPath);
            Set<String> importedShortTypes = getShortTypeNames(importedTypes);

            if (reportWarningOnShortNames) {
              List<String> checkDuplicateShortNames = checkDuplicateShortNames(completeTypes,
                      importedTypes);
              if (!checkDuplicateShortNames.isEmpty()) {
                rep.reportProblem(problemFactory.createDuplicateShortNameInImported(sRef,
                        localPath, checkDuplicateShortNames,
                        TextMarkerCheckerDefaultProblem.WARNING));
              }
            }
            completeTypes.addAll(importedTypes);
            shortTypes.addAll(importedShortTypes);
          } catch (IOException e) {
            rep.reportProblem(problemFactory.createFileNotFoundProblem(sRef, localPath));
          } catch (InvalidXMLException e) {
            rep.reportProblem(problemFactory.createXMLProblem(sRef, localPath));
          }
          return false;
        }
        // handle script-imports
        if (((TextMarkerImportStatement) s).getType() == TMStatementConstants.S_IMPORT_SCRIPT) {
          SimpleReference sRef = (SimpleReference) ((TextMarkerImportStatement) s).getExpression();
          String localpath = sRef.getName();

          // HOTFIX Peter add also the imported types of the imported type system!
          try {
            boolean checkScriptImport = TextMarkerCheckerUtils
                    .checkScriptImport(localpath, project);
            if (!checkScriptImport) {
              rep.reportProblem(problemFactory.createFileNotFoundProblem(sRef, localpath));
            }
            Set<String> importedTypes = importTypeSystem(localpath + "TypeSystem");
            Set<String> importedShortTypes = getShortTypeNames(importedTypes);
            Preferences store = TextMarkerIdePlugin.getDefault().getPluginPreferences();
            if (reportWarningOnShortNames) {
              List<String> checkDuplicateShortNames = checkDuplicateShortNames(completeTypes,
                      importedTypes);
              if (!checkDuplicateShortNames.isEmpty()) {
                rep.reportProblem(problemFactory.createDuplicateShortNameInImported(sRef,
                        localpath, checkDuplicateShortNames,
                        TextMarkerCheckerDefaultProblem.WARNING));
              }
            }
            completeTypes.addAll(importedTypes);
            shortTypes.addAll(importedShortTypes);
          } catch (IOException e) {
            rep.reportProblem(problemFactory.createFileNotFoundProblem(sRef, localpath));
          }
          // old stuff
          Set<String> importedTypes = new HashSet<String>();
          try {
            importedTypes = TextMarkerCheckerUtils.importScript(localpath, IModelElement.FIELD,
                    project, true);
          } catch (IOException e) {
            rep.reportProblem(problemFactory.createFileNotFoundProblem(sRef, localpath));
          }
          shortTypes.addAll(importedTypes);
          return false;
        }
      }
      return true;
    }

    private String getLongLocalName(String name) {
      if (name.indexOf(".") == -1) {
        String longName = this.packageName
                + "."
                + currentFile.getElementName().substring(0,
                        currentFile.getElementName().length() - 3) + ".";
        for (String each : blocks) {
          longName += each + ".";
        }
        longName += name;
        return longName;
      }
      return name;
    }

    private List<String> checkDuplicateShortNames(Set<String> types, Set<String> importedTypes) {
      List<String> result = new ArrayList<String>();
      for (String t1 : importedTypes) {
        for (String t2 : types) {
          if (checkTypeName(t1, t2)) {
            result.add(t1);
            result.add(t2);
          }
        }
      }
      return result;
    }

    private boolean checkTypeName(String t1, String t2) {
      int lastIndexOf1 = t1.lastIndexOf(".");
      int lastIndexOf2 = t2.lastIndexOf(".");
      String shortName1 = t1.substring(lastIndexOf1 + 1, t1.length());
      String shortName2 = t2.substring(lastIndexOf2 + 1, t2.length());
      String namespace1 = "";
      String namespace2 = "";
      if (lastIndexOf1 >= 0) {
        namespace1 = t1.substring(0, lastIndexOf1);
      }
      if (lastIndexOf2 >= 0) {
        namespace2 = t2.substring(0, lastIndexOf2);
      }
      if (shortName1.equals(shortName2) && !namespace1.equals(namespace2)) {
        return true;
      }
      return false;
    }

    private TypeSystemDescription getTypeSystemOfScript() throws InvalidXMLException, IOException {
      IPath descriptorPath = TextMarkerProjectUtils.getTypeSystemDescriptorPath(currentFile
              .getResource().getLocation(), project.getProject());
      TypeSystemDescription typeSysDescr = null;
      if (descriptorPath.toFile().exists()) {
        typeSysDescr = UIMAFramework.getXMLParser().parseTypeSystemDescription(
                new XMLInputSource(descriptorPath.toPortableString()));
        ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
        IFolder folder = project.getProject().getFolder(
                TextMarkerProjectUtils.getDefaultDescriptorLocation());
        resMgr.setDataPath(folder.getLocation().toPortableString());
        typeSysDescr.resolveImports(resMgr);
      }
      return typeSysDescr;
    }

    @Override
    public boolean visit(Expression s) throws Exception {
      if (s instanceof TextMarkerVariableReference) {
        TextMarkerVariableReference ref = (TextMarkerVariableReference) s;
        // filter everything but AnnotationTypeReferences
        if ((ref.getType() & TMTypeConstants.TM_TYPE_AT) == 0) {
          return false;
        }
        if (typeVariables.contains(ref.getName()) || completeTypes.contains(ref.getName())
                || shortTypes.contains(ref.getName()) || otherTypes.contains(ref.getName())
                || isLongLocalATRef(ref.getName()) || isLongExternalATRef(ref.getName())) {
          return false;
        }
        rep.reportProblem(problemFactory.createTypeProblem(ref, currentFile));
        return false;
      }
      return true;
    }

    private boolean isLongExternalATRef(String name) {
      int lastIndexOf = name.lastIndexOf(".");
      if (lastIndexOf == -1) {
        return false;
      }
      for (String each : shortTypes) {
        lastIndexOf = each.lastIndexOf(".");
        if (lastIndexOf != -1 && each.endsWith(name)) {
          return true;
        }
      }
      return false;
    }

    private boolean isLongLocalATRef(String name) {
      // TODO this methods does not work for blocks
      String longName = this.packageName
              + "."
              + currentFile.getElementName()
                      .substring(0, currentFile.getElementName().length() - 3);
      if (!name.startsWith(longName)) {
        return false;
      }
      String ref;
      try {
        ref = name.substring(longName.length() + 1);
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
      return shortTypes.contains(ref);
    }
  }

  public TextMarkerTypeChecker(IScriptProject project) throws CoreException {
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

    Set<String> shortTypes = getShortTypeNames(types);
    try {
      ASTVisitor visitor = new TypeCheckerVisitor(problemReporter, linetracker, smod, types,
              shortTypes);
      md.traverse(visitor);
    } catch (Exception e) {
      TextMarkerIdePlugin.error(e);
    }
  }

  private Set<String> importBasicTypeSystem() throws InvalidXMLException, IOException,
          CoreException {
    final String basicXmlFile = "BasicTypeSystem";
    Set<String> types = importTypeSystem(basicXmlFile);
    types.add("uima.tcas.Annotation");
    types.add("uima.tcas.DocumentAnnotation");
    return types;
  }

  public Set<String> getShortTypeNames(Set<String> types) {
    Set<String> result = new HashSet<String>();
    for (String string : types) {
      String[] nameSpace = string.split("[.]");
      result.add(nameSpace[nameSpace.length - 1]);
    }
    return result;
  }

  /**
   * @param xmlFilePath
   *          absolute full path. i.e.: "org.apache.uima.mytypes" ".xml" will be added.
   * @return
   * @throws InvalidXMLException
   * @throws IOException
   *           when file not found
   * @throws CoreException
   */
  private Set<String> importTypeSystem(String xmlFilePath) throws InvalidXMLException, IOException,
          CoreException {

    List<IFolder> folders = TextMarkerProjectUtils.getAllDescriptorFolders(project.getProject());
    String[] split = xmlFilePath.split("[.]");
    String fileExtended = "";
    for (String string : split) {
      fileExtended += string;
      fileExtended += "/";
    }
    fileExtended = fileExtended.substring(0, fileExtended.length() - 1) + ".xml";
    Set<String> types = new HashSet<String>();
    for (IFolder folder : folders) {
      types.addAll(getTypes(folder, fileExtended));
    }
    return types;
  }

  private Set<String> getTypes(IFolder folder, String filePath) throws InvalidXMLException,
          IOException {
    Set<String> types = new HashSet<String>();
    IFile iFile = getFile(folder, filePath);
    if (iFile.exists()) {
      URL url;
      try {
        url = iFile.getLocationURI().toURL();
        ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
        resMgr.setDataPath(folder.getLocation().toPortableString());
        types = getTypes(url, resMgr);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
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
    IFile file = folder.getFile(fName);
    return file;
  }

  private Set<String> getTypes(URL resource, ResourceManager resMgr) throws IOException,
          InvalidXMLException {
    Set<String> types = new HashSet<String>();
    TypeSystemDescription typeSysDescr = null;
    typeSysDescr = UIMAFramework.getXMLParser().parseTypeSystemDescription(
            new XMLInputSource(resource));
    typeSysDescr.resolveImports(resMgr);
    for (TypeDescription each : typeSysDescr.getTypes()) {
      String name = each.getName();
      String[] nameSpace = name.split("[.]");
      // types.add(nameSpace[nameSpace.length - 1]);
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
