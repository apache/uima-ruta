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

package org.apache.uima.ruta.ide.validator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.FeatureDescription;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.RutaCorePreferences;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.ide.parser.ast.FeatureMatchExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaBlock;
import org.apache.uima.ruta.ide.parser.ast.RutaDeclareDeclarationsStatement;
import org.apache.uima.ruta.ide.parser.ast.RutaFeatureDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaImportStatement;
import org.apache.uima.ruta.ide.parser.ast.RutaPackageDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaStatementConstants;
import org.apache.uima.ruta.ide.parser.ast.RutaTypeConstants;
import org.apache.uima.ruta.ide.parser.ast.RutaTypeDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaVariableDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaVariableReference;
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
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension;
import org.eclipse.dltk.core.builder.ISourceLineTracker;

public class RutaTypeChecker implements IBuildParticipant, IBuildParticipantExtension {

  private class TypeCheckerVisitor extends ASTVisitor {
    private final Stack<String> blocks;

    private final Set<String> typeVariables;

    private final Set<String> otherVariables;

    private IProblemReporter rep;

    private ISourceModule currentFile;

    private ISourceLineTracker linetracker;

    private Set<String> completeTypes;

    private Set<String> shortTypes;

    private RutaCheckerProblemFactory problemFactory;

    private String packageName;

    private TypeSystemDescription description;

    private Set<String> otherTypes;

    private Set<String> finalTypes;

    private boolean reportWarningOnShortNames;

    public TypeCheckerVisitor(IProblemReporter rep, ISourceLineTracker linetracker,
            ISourceModule curFile, Set<String> completeTypes, Set<String> shortTypes) {
      this.problemFactory = new RutaCheckerProblemFactory(curFile.getElementName(), linetracker);
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
      } catch (Exception e) {
        RutaIdeUIPlugin.error(e);
      }

      Preferences store = RutaIdeUIPlugin.getDefault().getPluginPreferences();
      reportWarningOnShortNames = !store
              .getBoolean(RutaCorePreferences.BUILDER_IGNORE_DUPLICATE_SHORTNAMES);
    }

    @Override
    public boolean endvisit(MethodDeclaration s) throws Exception {
      if (s instanceof RutaBlock) {
        blocks.pop();
      }
      return super.endvisit(s);
    }

    public boolean visit(MethodDeclaration s) throws Exception {
      if (s instanceof RutaBlock) {
        RutaBlock b = (RutaBlock) s;
        blocks.push(b.getName());
      }
      return true;
    }

    @Override
    public boolean visit(Statement s) throws Exception {
      if (s instanceof RutaPackageDeclaration) {
        this.packageName = ((RutaPackageDeclaration) s).getName();
        return false;
      }
      if (s instanceof RutaDeclareDeclarationsStatement) {
        RutaDeclareDeclarationsStatement dds = (RutaDeclareDeclarationsStatement) s;
        ASTNode parent = dds.getParent();
        if (parent != null && parent instanceof RutaVariableReference) {
          RutaVariableReference p = (RutaVariableReference) parent;
          String name = p.getName();
          if (finalTypes.contains(name)) {
            IProblem problem = problemFactory.createInheritenceFinalProblem(p);
            rep.reportProblem(problem);
          }
        }
        return true;
      }

      if (s instanceof RutaVariableDeclaration) {
        RutaVariableDeclaration newVar = (RutaVariableDeclaration) s;
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
        if ((newVar.getKind() & RutaTypeConstants.RUTA_TYPE_AT) != 0) {
          typeVariables.add(newVar.getName());
          return false;
        }
        otherVariables.add(newVar.getName());
        return false;
      }
      // if (s instanceof RutaFeatureDeclaration) {
      // RutaFeatureDeclaration fd = (RutaFeatureDeclaration) s;
      // }
      if (s instanceof RutaTypeDeclaration) {
        RutaTypeDeclaration newType = (RutaTypeDeclaration) s;
        String longName = getLongLocalName(newType.getName());
        if (completeTypes.contains(longName)) {
          IProblem problem = problemFactory.createIdConflictsWithTypeProblem(newType);
          rep.reportProblem(problem);
          return false;
        }
        if (reportWarningOnShortNames && shortTypes.contains(newType.getName())) {
          IProblem problem = problemFactory.createDuplicateShortName(newType,
                  ProblemSeverity.WARNING);
          rep.reportProblem(problem);
          return false;
        }
        if (typeVariables.contains(newType.getName()) || otherVariables.contains(newType.getName())) {
          IProblem problem = problemFactory.createIdConflictsWithVariableProblem(newType);
          rep.reportProblem(problem);
          return false;
        }
        List<RutaFeatureDeclaration> features = newType.getFeatures();
        if (features != null) {

          for (RutaFeatureDeclaration each : features) {
            String type = each.getType();
            if (type.equals("INT") || type.equals("STRING") || type.equals("DOUBLE")
                    || type.equals("FLOAT") || type.equals("BOOLEAN")) {
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
      if (s instanceof RutaImportStatement) {
        // handle type system imports
        if (((RutaImportStatement) s).getType() == RutaStatementConstants.S_IMPORT_TYPESYSTEM) {
          SimpleReference sRef = (SimpleReference) ((RutaImportStatement) s).getExpression();
          String localPath = sRef.getName();
          try {
            boolean checkTypeSystemImport = RutaCheckerUtils.checkTypeSystemImport(localPath,
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
                        localPath, checkDuplicateShortNames, ProblemSeverity.WARNING));
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
        if (((RutaImportStatement) s).getType() == RutaStatementConstants.S_IMPORT_SCRIPT) {
          SimpleReference sRef = (SimpleReference) ((RutaImportStatement) s).getExpression();
          String localpath = sRef.getName();

          // HOTFIX Peter add also the imported types of the imported type system!
          try {
            boolean checkScriptImport = RutaCheckerUtils.checkScriptImport(localpath, project);
            if (!checkScriptImport) {
              rep.reportProblem(problemFactory.createFileNotFoundProblem(sRef, localpath));
            }
            Set<String> importedTypes = importTypeSystem(localpath + "TypeSystem");
            Set<String> importedShortTypes = getShortTypeNames(importedTypes);
            Preferences store = RutaIdeUIPlugin.getDefault().getPluginPreferences();
            if (reportWarningOnShortNames) {
              List<String> checkDuplicateShortNames = checkDuplicateShortNames(completeTypes,
                      importedTypes);
              if (!checkDuplicateShortNames.isEmpty()) {
                rep.reportProblem(problemFactory.createDuplicateShortNameInImported(sRef,
                        localpath, checkDuplicateShortNames, ProblemSeverity.WARNING));
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
            importedTypes = RutaCheckerUtils.importScript(localpath, IModelElement.FIELD, project,
                    true);
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
        String pack = packageName.equals("") ? "" : packageName + ".";
        String longName = pack
                + currentFile.getElementName().substring(0,
                        currentFile.getElementName().length() - 5) + ".";
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
      IPath descriptorPath = RutaProjectUtils.getTypeSystemDescriptorPath(currentFile.getResource()
              .getLocation(), project.getProject());
      TypeSystemDescription typeSysDescr = null;
      if (descriptorPath.toFile().exists()) {
        typeSysDescr = UIMAFramework.getXMLParser().parseTypeSystemDescription(
                new XMLInputSource(descriptorPath.toPortableString()));
        ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
        IFolder folder = project.getProject().getFolder(
                RutaProjectUtils.getDefaultDescriptorLocation());
        resMgr.setDataPath(folder.getLocation().toPortableString());
        typeSysDescr.resolveImports(resMgr);
      }
      return typeSysDescr;
    }

    @Override
    public boolean visit(Expression s) throws Exception {
      if (s instanceof RutaVariableReference) {
        RutaVariableReference ref = (RutaVariableReference) s;
        // filter everything but AnnotationTypeReferences
        int kind = ref.getKind();
        int rutaTypeAt = RutaTypeConstants.RUTA_TYPE_AT;
        int rutaTypeS = RutaTypeConstants.RUTA_TYPE_S;
        if ((ref.getType() & RutaTypeConstants.RUTA_TYPE_AT) == 0) {
          return false;
        }
        if (typeVariables.contains(ref.getName()) || completeTypes.contains(ref.getName())
                || shortTypes.contains(ref.getName()) || otherTypes.contains(ref.getName())
                || isLongLocalATRef(ref.getName()) || isLongExternalATRef(ref.getName())) {
          return false;
        }
        if (isFeatureMatch(ref.getName()) != null) {
          return false;
        }
        rep.reportProblem(problemFactory.createTypeProblem(ref, currentFile));
        return false;
      } else if (s instanceof FeatureMatchExpression) {
        FeatureMatchExpression fme = (FeatureMatchExpression) s;
        String featText = fme.getFeature().getText();
        checkTypeOfFeatureMatch(featText, fme);
        return true;
      }
      return true;
    }

    private void checkTypeOfFeatureMatch(String featText, FeatureMatchExpression fme) {
      int lastIndexOf = featText.lastIndexOf(".");
      if(lastIndexOf == -1) {
        return;
      }
      String aref = featText.substring(0, lastIndexOf);
      String fref = featText.substring(lastIndexOf + 1, featText.length());
      String match = isFeatureMatch(aref);
      if (match != null) {
        int kind = -1;
        if (fme.getValue() != null) {
          kind = fme.getValue().getKind();
          if (fme.getValue() instanceof StringLiteral) {
            kind = RutaTypeConstants.RUTA_TYPE_S;
          } else if (fme.getValue() instanceof NumericLiteral) {
            kind = RutaTypeConstants.RUTA_TYPE_N;
          }
        }
        boolean findFeature = findFeature(match, fref, kind);
        if (findFeature) {

        } else {
          rep.reportProblem(problemFactory.createUnknownFeatureProblem(fme, aref));
        }
      } else {
        rep.reportProblem(problemFactory.createTypeProblem(fme, currentFile));
        rep.reportProblem(problemFactory.createUnknownFeatureProblem(fme, aref));
      }
    }

    private String isFeatureMatch(String text) {
      for (String each : shortTypes) {
        String t = checkFeatureMatch(text, each);
        if (t != null) {
          return t;
        }
      }
      for (String each : completeTypes) {
        String t = checkFeatureMatch(text, each);
        if (t != null) {
          return t;
        }
      }
      return null;
    }

    private boolean findFeature(String structure, String feat, int kind) {
      if (description == null) {
        return true;
      }
      if (structure == null) {
        return false;
      }
      // TODO HOTFIX
      if("begin".equals(feat)|| "end".equals(feat)) {
        return true;
      }
      // TODO HOTFIX
      if (structure.equals("Document") || structure.equals("DocumentAnnotation")
              || structure.equals("uima.tcas.DocumentAnnotation")) {
        if (feat.equals("language")) {
          return true;
        }
      }

      boolean featureFound = false;
      TypeDescription[] descriptions = description.getTypes();
      Map<String, TypeDescription> typeMap = new HashMap<String, TypeDescription>();
      for (TypeDescription typeDescription : descriptions) {
        String typeName = typeDescription.getName();
        typeMap.put(typeName, typeDescription);
      }

      for (TypeDescription typeDescription : descriptions) {
        String typeName = typeDescription.getName();
        String shortName = getShortName(typeName);
        if (typeName.equals(structure) || shortName.equals(structure)) {
          Collection<FeatureDescription> allFeatures = getAllDeclaredFeatures(typeDescription,
                  typeMap);
          for (FeatureDescription featureDescription : allFeatures) {
            String featureName = featureDescription.getName();
            if (featureName.equals(feat) && checkFeatureKind(featureDescription, kind)) {
              featureFound = true;
              break;
            }
          }
        }

        if (featureFound) {
          break;
        }
      }
      return featureFound;
    }

    private boolean checkFeatureKind(FeatureDescription f, int kind) {
      if (kind == -1) {
        return true;
      }
      String t = f.getRangeTypeName();
      if (t.equals(UIMAConstants.TYPE_BOOLEAN) && RutaTypeConstants.RUTA_TYPE_B == kind) {
        return true;
      } else if (t.equals(UIMAConstants.TYPE_STRING) && RutaTypeConstants.RUTA_TYPE_S == kind) {
        return true;
      } else if ((t.equals(UIMAConstants.TYPE_BYTE) || t.equals(UIMAConstants.TYPE_DOUBLE)
              || t.equals(UIMAConstants.TYPE_FLOAT) || t.equals(UIMAConstants.TYPE_INTEGER)
              || t.equals(UIMAConstants.TYPE_LONG) || t.equals(UIMAConstants.TYPE_SHORT))
              && RutaTypeConstants.RUTA_TYPE_N == kind) {
        return true;
      }
      return false;
    }

    private String getShortName(String typeName) {
      String[] nameSpace = typeName.split("[.]");
      return nameSpace[nameSpace.length - 1];
    }

    private Collection<FeatureDescription> getAllDeclaredFeatures(TypeDescription typeDescription,
            Map<String, TypeDescription> typeMap) {
      Collection<FeatureDescription> result = new HashSet<FeatureDescription>();
      if (typeDescription == null) {
        return result;
      }
      FeatureDescription[] features = typeDescription.getFeatures();
      if (features == null) {
        return result;
      }
      result.addAll(Arrays.asList(features));
      String supertypeName = typeDescription.getSupertypeName();
      if (supertypeName != null) {
        TypeDescription parent = typeMap.get(supertypeName);
        result.addAll(getAllDeclaredFeatures(parent, typeMap));
      }
      return result;
    }

    private String checkFeatureMatch(String name, String type) {
      if (name.startsWith(type)) {
        boolean foundAll = true;
        if (name.length() > type.length()) {
          String tail = name.substring(type.length() + 1);
          String[] split = tail.split("[.]");
          String typeToCheck = type;
          for (String feat : split) {
            typeToCheck = checkFSFeatureOfType(feat, typeToCheck);
            foundAll &= (typeToCheck != null);
            if (!foundAll) {
              return null;
            }
          }
          return typeToCheck;
        } else {
          return type;
        }
      } else {
        return null;
      }
    }

    private String checkFSFeatureOfType(String feat, String type) {
      if (type.indexOf(".") == -1) {
        for (String each : completeTypes) {
          String[] split = each.split("[.]");
          if (split[split.length - 1].equals(type)) {
            type = each;
            break;
          }
        }
      }
      TypeDescription t = description.getType(type);
      if (t == null)
        return null;
      FeatureDescription[] features = t.getFeatures();
      for (FeatureDescription featureDescription : features) {
        String name = featureDescription.getName();
        String rangeTypeName = featureDescription.getRangeTypeName();
        boolean isFS = isFeatureStructure(rangeTypeName);
        if (name.equals(feat) && isFS) {
          return rangeTypeName;
        }
      }
      return null;
    }

    private boolean isFeatureStructure(String rangeTypeName) {
      if (rangeTypeName.equals("uima.tcas.Annotation") || rangeTypeName.equals("uima.cas.TOP")) {
        return true;
      }
      TypeDescription type = description.getType(rangeTypeName);
      if (type == null) {
        return false;
      }
      String supertypeName = type.getSupertypeName();
      if (supertypeName != null) {
        return isFeatureStructure(supertypeName);
      }
      return false;
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
                      .substring(0, currentFile.getElementName().length() - 5);
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

  public RutaTypeChecker(IScriptProject project) throws CoreException {
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
      types = new TreeSet<String>();
    }

    // traverse:
    ISourceLineTracker linetracker = context.getLineTracker();

    Set<String> shortTypes = getShortTypeNames(types);
    try {
      ASTVisitor visitor = new TypeCheckerVisitor(problemReporter, linetracker, smod, types,
              shortTypes);
      md.traverse(visitor);
    } catch (Exception e) {
      RutaIdeUIPlugin.error(e);
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
    Set<String> result = new TreeSet<String>();
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
   * @throws Exception
   */
  private Set<String> importTypeSystem(String xmlFilePath) throws InvalidXMLException, IOException,
          CoreException {

    List<IFolder> folders = RutaProjectUtils.getAllDescriptorFolders(project.getProject());
    String[] split = xmlFilePath.split("[.]");
    String fileExtended = "";
    for (String string : split) {
      fileExtended += string;
      fileExtended += "/";
    }
    fileExtended = fileExtended.substring(0, fileExtended.length() - 1) + ".xml";
    Set<String> types = new TreeSet<String>();
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
      types.add(name);
    }
    return types;
  }
}
