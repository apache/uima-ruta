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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import org.antlr.runtime.Token;
import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.impl.ResourceManager_impl;
import org.apache.uima.resource.metadata.FeatureDescription;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.resource.metadata.impl.FeatureDescription_impl;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.IRutaKeywords;
import org.apache.uima.ruta.ide.core.RutaCorePreferences;
import org.apache.uima.ruta.ide.core.RutaExtensionManager;
import org.apache.uima.ruta.ide.core.RutaKeywordsManager;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.ide.core.extensions.IIDEActionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDEBlockExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDEBooleanFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDEConditionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDENumberFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDEStringFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDETypeFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IRutaExtension;
import org.apache.uima.ruta.ide.parser.ast.FeatureMatchExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaActionConstants;
import org.apache.uima.ruta.ide.parser.ast.RutaBlock;
import org.apache.uima.ruta.ide.parser.ast.RutaCondition;
import org.apache.uima.ruta.ide.parser.ast.RutaDeclareDeclarationsStatement;
import org.apache.uima.ruta.ide.parser.ast.RutaExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaFeatureDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaFunction;
import org.apache.uima.ruta.ide.parser.ast.RutaImportStatement;
import org.apache.uima.ruta.ide.parser.ast.RutaImportTypesStatement;
import org.apache.uima.ruta.ide.parser.ast.RutaListExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaPackageDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaRegExpRule;
import org.apache.uima.ruta.ide.parser.ast.RutaRuleElement;
import org.apache.uima.ruta.ide.parser.ast.RutaStatementConstants;
import org.apache.uima.ruta.ide.parser.ast.RutaStringExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaStructureAction;
import org.apache.uima.ruta.ide.parser.ast.RutaTypeConstants;
import org.apache.uima.ruta.ide.parser.ast.RutaTypeDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaVariableDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaVariableReference;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.ISourceLineTracker;
import org.eclipse.jface.preference.IPreferenceStore;

public class LanguageCheckerVisitor extends ASTVisitor {

  private IProblemReporter pr;

  private ISourceLineTracker linetracker;

  private ISourceModule sourceModule;

  private Map<String, IIDEConditionExtension> conditionExtensions;

  private Map<String, IIDEActionExtension> actionExtensions;

  private Map<String, IIDENumberFunctionExtension> numberFunctionExtensions;

  private Map<String, IIDEBooleanFunctionExtension> booleanFunctionExtensions;

  private Map<String, IIDEStringFunctionExtension> stringFunctionExtensions;

  private Map<String, IIDETypeFunctionExtension> typeFunctionExtensions;

  private Map<String, IIDEBlockExtension> blockExtensions;

  /**
   * Mapping from short type name (e.g. {@code W}) to their disambiguated long type names (e.g.
   * {@code org.apache.uima.ruta.type.W}).
   */
  private Map<String, String> namespaces;

  /**
   * Mapping from ambiguous short type names to all their possible long type names.
   */
  private Map<String, Set<String>> ambiguousTypeAlias;

  /**
   * Known variables for each block environment
   */
  private final Stack<Map<String, Integer>> knownLocalVariables;

  /**
   * Name of each block
   */
  private final Stack<String> blocks;

  /**
   * Caching the matched type of a rule element
   */
  private String matchedType;

  /**
   * Caching the declared package of the script
   */
  private String packageName = "";

  /**
   * Mapping all long type names of the type system to their type description
   */
  private Map<String, TypeDescription> typeDescriptionMap;

  /**
   * Mapping all long type names of all declared feature description
   */
  private Map<String, Set<FeatureDescription>> featureDescriptionMap;

  /**
   * The type system description of the script file
   */
  private TypeSystemDescription typeSystemDescription;

  private final String implicitString = "Implicit";

  private Set<String> finalTypes;

  private boolean reportWarningOnShortNames;

  private RutaCheckerProblemFactory problemFactory;

  private ResourceManager resourceManager;

  private Set<String> allLongTypeNames;

  private String parentTypeInDeclaration;

  private ClassLoader classLoader;

  public LanguageCheckerVisitor(IProblemReporter problemReporter, ISourceLineTracker linetracker,
          ISourceModule sourceModule, ClassLoader classLoader) {
    super();
    this.pr = problemReporter;
    this.linetracker = linetracker;
    this.sourceModule = sourceModule;
    this.classLoader = classLoader;
    this.problemFactory = new RutaCheckerProblemFactory(sourceModule.getElementName(), linetracker);

    namespaces = new TreeMap<String, String>();
    ambiguousTypeAlias = new TreeMap<String, Set<String>>();
    allLongTypeNames = new HashSet<String>();
    knownLocalVariables = new Stack<Map<String, Integer>>();
    knownLocalVariables.push(new HashMap<String, Integer>());
    blocks = new Stack<String>();

    initializePredefinedInformation();
    initializeExtensionInformation();

    IPreferenceStore store = RutaIdeUIPlugin.getDefault().getPreferenceStore();
    reportWarningOnShortNames = !store
            .getBoolean(RutaCorePreferences.BUILDER_IGNORE_DUPLICATE_SHORTNAMES);

  }

  @Override
  public boolean visit(Statement s) throws Exception {
    if (s instanceof RutaPackageDeclaration) {
      this.packageName = ((RutaPackageDeclaration) s).getName();
      return false;
    }
    if (s instanceof RutaImportTypesStatement) {
      RutaImportTypesStatement stmt = (RutaImportTypesStatement) s;
      SimpleReference tsExpr = (SimpleReference) stmt.getExpression();
      Token typeToken = stmt.getTypeToken();
      Token pkgToken = stmt.getPkgToken();
      Token aliasToken = stmt.getAliasToken();
      if (tsExpr != null) {
        String localPath = tsExpr.getName();
        processCompleteTypeSystemImport(tsExpr, localPath, typeToken, pkgToken, aliasToken);
      } else {
        // TODO package import not supported in Workbench
      }

    } else if (s instanceof RutaImportStatement) {
      // handle type system imports
      if (((RutaImportStatement) s).getType() == RutaStatementConstants.S_IMPORT_TYPESYSTEM) {
        SimpleReference sRef = (SimpleReference) ((RutaImportStatement) s).getExpression();
        String localPath = sRef.getName();
        processCompleteTypeSystemImport(sRef, localPath);
        return false;
      }
      // handle script-imports
      if (((RutaImportStatement) s).getType() == RutaStatementConstants.S_IMPORT_SCRIPT) {
        SimpleReference sRef = (SimpleReference) ((RutaImportStatement) s).getExpression();
        String localPath = sRef.getName();

        // HOTFIX Peter add also the imported types of the imported type system!
        try {
          URL url = null;
          IFile file = RutaCheckerUtils.checkScriptImport(localPath,
                  sourceModule.getScriptProject());
          if (file == null) {
            url = RutaCheckerUtils.checkImportExistence(localPath + "TypeSystem", "xml",
                    classLoader);
          }
          if (file == null && url == null) {
            pr.reportProblem(problemFactory.createFileNotFoundProblem(sRef, localPath));
          } else {
            IProject referredProject = sourceModule.getScriptProject().getProject();
            if (file != null) {
              // script in other project? use that if the file was found in the workspace
              referredProject = file.getProject();
            }
            IPath typeSystemDescriptorPath = RutaProjectUtils.getTypeSystemDescriptorPath(
                    file.getLocation(), referredProject);
            TypeSystemDescription tsDesc = importCompleteTypeSystem(typeSystemDescriptorPath, url);

            List<String> checkDuplicateShortNames = checkOnAmbiguousShortNames(tsDesc);
            if (!checkDuplicateShortNames.isEmpty()) {
              pr.reportProblem(problemFactory.createDuplicateShortNameInImported(sRef, localPath,
                      checkDuplicateShortNames, ProblemSeverity.WARNING));
            }
          }
        } catch (IOException e) {
          pr.reportProblem(problemFactory.createFileNotFoundProblem(sRef, localPath));
        }
        return false;
      }
    }

    if (s instanceof RutaDeclareDeclarationsStatement) {
      RutaDeclareDeclarationsStatement dds = (RutaDeclareDeclarationsStatement) s;
      ASTNode parent = dds.getParent();
      if (parent != null && parent instanceof RutaVariableReference) {
        RutaVariableReference p = (RutaVariableReference) parent;
        String name = p.getName();
        name = expand(name);
        parentTypeInDeclaration = name;
        // TODO remember name for new types and their features!
        if (finalTypes.contains(name)) {
          IProblem problem = problemFactory.createInheritenceFinalProblem(p);
          pr.reportProblem(problem);
        }
      }
      return true;
    }
    if (s instanceof RutaTypeDeclaration) {
      RutaTypeDeclaration newType = (RutaTypeDeclaration) s;
      String shortName = newType.getName();
      String longName = getLongNameOfNewType(shortName);

      if (namespaces.values().contains(longName)) {
        IProblem problem = problemFactory.createIdConflictsWithTypeProblem(newType);
        pr.reportProblem(problem);
        return false;
      }

      if (reportWarningOnShortNames && namespaces.containsKey(shortName)) {
        IProblem problem = problemFactory
                .createDuplicateShortName(newType, ProblemSeverity.WARNING);
        pr.reportProblem(problem);
        return false;
      }
      if (knowsVariable(shortName)) {
        IProblem problem = problemFactory.createIdConflictsWithVariableProblem(newType);
        pr.reportProblem(problem);
        return false;
      }
      List<RutaFeatureDeclaration> features = newType.getFeatures();
      Set<FeatureDescription> feats = new HashSet<FeatureDescription>();
      if (parentTypeInDeclaration != null) {
        Set<FeatureDescription> set = featureDescriptionMap.get(parentTypeInDeclaration);
        if (set != null) {
          feats.addAll(set);
        }
      }
      if (features != null) {
        for (RutaFeatureDeclaration each : features) {
          // TODO create correct feature description! Works right now because the type is not
          // checked
          String type = each.getType();
          type = translate(type);
          type = expand(type);
          FeatureDescription f = new FeatureDescription_impl(each.getName(), "", type);
          feats.add(f);
          if (type.equals("INT") || type.equals("STRING") || type.equals("DOUBLE")
                  || type.equals("FLOAT") || type.equals("BOOLEAN")) {
            continue;
          }
          if (!namespaces.keySet().contains(type) && !namespaces.values().contains(type)) {
            IProblem problem = problemFactory.createUnknownFeatureTypeProblem(each);
            pr.reportProblem(problem);
          }

        }
        featureDescriptionMap.put(longName, feats);
      }
      addDeclaredType(shortName);
      return false;
    }
    if (s instanceof RutaVariableDeclaration) {
      RutaVariableDeclaration newVar = (RutaVariableDeclaration) s;
      if (knowsVariable(newVar.getName())) {
        IProblem problem = problemFactory.createIdConflictsWithVariableProblem(newVar);
        pr.reportProblem(problem);
        return false;
      }
      if (namespaces.containsKey(newVar.getName())) {
        IProblem problem = problemFactory.createIdConflictsWithTypeProblem(newVar);
        pr.reportProblem(problem);
        return false;
      }
      knownLocalVariables.peek().put(newVar.getName(), newVar.getKind());
      return false;
    }
    if (s instanceof RutaRegExpRule) {
      RutaRegExpRule rule = (RutaRegExpRule) s;
      Map<Expression, Map<Expression, Expression>> faMap = rule.getFeats();
      Set<Entry<Expression, Map<Expression, Expression>>> typeEntrySet = faMap.entrySet();
      for (Entry<Expression, Map<Expression, Expression>> entry : typeEntrySet) {
        Expression struct = entry.getKey();
        String structure = "";
        if (struct != null) {
          structure = sourceModule.getSource().substring(struct.sourceStart(), struct.sourceEnd());
          structure = expand(structure);
        }
        Map<Expression, Expression> fmap = entry.getValue();
        Set<Expression> keySet = fmap.keySet();
        for (Expression fkey : keySet) {
          if (fkey instanceof RutaExpression && fkey.getKind() == RutaTypeConstants.RUTA_TYPE_S) {
            String feat = fkey.toString();
            feat = getFeatureName(fkey, feat);
            boolean findFeature = findFeature(structure, feat, -1);
            if (!findFeature) {
              IProblem problem = problemFactory.createUnknownFeatureProblem(fkey, structure);
              pr.reportProblem(problem);
            }
          }
        }
      }
    }
    return true;
  }

  private void processCompleteTypeSystemImport(SimpleReference sRef, String localPath)
          throws CoreException {
    processCompleteTypeSystemImport(sRef, localPath, null, null, null);
  }

  private void processCompleteTypeSystemImport(SimpleReference sRef, String localPath,
          Token typeToken, Token pkgToken, Token aliasToken) throws CoreException {
    try {
      URL url = null;
      IFile file = RutaCheckerUtils.checkTypeSystemImport(localPath,
              sourceModule.getScriptProject());
      if (file == null) {
        url = RutaCheckerUtils.checkImportExistence(localPath, "xml", classLoader);
      }
      if (file == null && url == null) {
        pr.reportProblem(problemFactory.createFileNotFoundProblem(sRef, localPath));
      } else {
        IPath path = file == null ? null : file.getLocation();
        TypeSystemDescription tsDesc = importTypeSystem(path, url, typeToken, pkgToken, aliasToken);
        if (reportWarningOnShortNames) {
          List<String> checkDuplicateShortNames = checkOnAmbiguousShortNames(tsDesc);
          if (!checkDuplicateShortNames.isEmpty()) {
            pr.reportProblem(problemFactory.createDuplicateShortNameInImported(sRef, localPath,
                    checkDuplicateShortNames, ProblemSeverity.WARNING));
          }
        }
      }
    } catch (IOException e) {
      pr.reportProblem(problemFactory.createFileNotFoundProblem(sRef, localPath));
    } catch (InvalidXMLException e) {
      pr.reportProblem(problemFactory.createXMLProblem(sRef, localPath));
    }
  }

  private List<String> checkOnAmbiguousShortNames(TypeSystemDescription tsDesc) {
    List<String> checkDuplicateShortNames = new ArrayList<String>();
    for (TypeDescription each : tsDesc.getTypes()) {
      String longName = each.getName();
      String shortName = getShortName(longName);
      Set<String> set = ambiguousTypeAlias.get(shortName);
      if (set != null && set.size() > 1) {
        checkDuplicateShortNames.addAll(set);
      }
    }
    return checkDuplicateShortNames;
  }

  private TypeSystemDescription importTypeSystem(IPath path, URL url, Token typeToken,
          Token pkgToken, Token aliasToken) throws InvalidXMLException, IOException,
          MalformedURLException, CoreException {
    TypeSystemDescription tsDesc = null;
    if (path != null) {
      tsDesc = UIMAFramework.getXMLParser().parseTypeSystemDescription(
              new XMLInputSource(path.toFile()));
    } else {
      tsDesc = UIMAFramework.getXMLParser().parseTypeSystemDescription(new XMLInputSource(url));
    }

    ResourceManager resMgr = getResourceManager(classLoader);
    tsDesc.resolveImports(resMgr);
    for (TypeDescription each : tsDesc.getTypes()) {
      String longName = each.getName();
      String shortName = getShortName(longName);
      if (pkgToken != null) {
        String pkg = pkgToken.getText();
        if (!longName.startsWith(pkg + ".")) {
          continue;
        }
      }
      if (typeToken != null) {
        String type = typeToken.getText();
        if (!longName.equals(type)) {
          continue;
        }
      }
      if (aliasToken != null) {
        String alias = aliasToken.getText();
        if (typeToken == null) {
          shortName = alias + "." + shortName;
        } else {
          shortName = alias;
        }
      }
      importType(longName, shortName);
    }
    return tsDesc;
  }

  private ResourceManager getResourceManager(ClassLoader classloader) throws MalformedURLException,
          CoreException {
    if (resourceManager == null) {
      resourceManager = new ResourceManager_impl(classloader);
      List<IFolder> folders = RutaProjectUtils.getAllDescriptorFolders(sourceModule
              .getScriptProject().getProject());
      StringBuilder sb = new StringBuilder();
      Iterator<IFolder> iterator = folders.iterator();
      while (iterator.hasNext()) {
        IFolder iFolder = (IFolder) iterator.next();
        sb.append(iFolder.getLocation().toPortableString());
        if (iterator.hasNext()) {
          sb.append(System.getProperty("path.separator"));
        }
      }
      resourceManager.setDataPath(sb.toString());
    }
    return resourceManager;
  }

  @Override
  public boolean visit(Expression s) throws Exception {

    if (s instanceof RutaRuleElement) {
      RutaRuleElement re = (RutaRuleElement) s;
      Expression head = re.getHead();
      if (head instanceof FeatureMatchExpression) {
        FeatureMatchExpression fme = (FeatureMatchExpression) head;
        String text = fme.getFeature().getText();
        int lastIndexOf = text.lastIndexOf('.');
        String twf = text.substring(0, lastIndexOf);
        if (getVariableType(twf) == RutaTypeConstants.RUTA_TYPE_AT) {
          matchedType = twf;
        } else {
          twf = expand(twf);
          matchedType = isFeatureMatch(twf);
        }
      } else if (head != null) {
        matchedType = sourceModule.getSource().substring(head.sourceStart(), head.sourceEnd());
      }
      // cache long name
      matchedType = expand(matchedType);
      if (matchedType == null) {
        matchedType = "uima.tcas.Annotation";
      }
    }
    if (s instanceof FeatureMatchExpression) {
      FeatureMatchExpression fme = (FeatureMatchExpression) s;
      String featText = fme.getFeature().getText();
      // HOTFIX: parser creates wrong AST element
      if (allLongTypeNames.contains(featText)) {
        return true;
      }
      checkTypeOfFeatureMatch(featText, fme);
      return true;
    }
    if (s instanceof RutaVariableReference) {
      RutaVariableReference ref = (RutaVariableReference) s;
      if ((ref.getType() & RutaTypeConstants.RUTA_TYPE_AT) != 0) {
        // types
        String name = ref.getName();
        if (name.equals("Document")) {
          return false;
        }

        Set<String> set = ambiguousTypeAlias.get(name);
        if (set != null && !set.isEmpty()) {
          pr.reportProblem(problemFactory.createAmbiguousShortName(ref, set, ProblemSeverity.ERROR));
          return false;
        }
        if (namespaces.keySet().contains(name) || namespaces.values().contains(name)
                || allLongTypeNames.contains(name)
                || getVariableType(name) == RutaTypeConstants.RUTA_TYPE_AT) {
          return false;
        }
        if (isFeatureMatch(ref.getName()) != null) {
          return false;
        }
        pr.reportProblem(problemFactory.createTypeProblem(ref, sourceModule));
        return false;
      }
      if (!isVariableDeclared(ref)) {
        return false;
      }
      checkTypeOfVariable(ref);
      return false;
    }
    // check assign types
    if (s instanceof RutaAction) {
      RutaAction tma = (RutaAction) s;

      String actionName = sourceModule.getSource().substring(tma.getNameStart(), tma.getNameEnd());
      String[] keywords = RutaKeywordsManager.getKeywords(IRutaKeywords.ACTION);
      List<String> asList = Arrays.asList(keywords);
      if (!StringUtils.isEmpty(actionName) && !"-".equals(actionName)
              && !asList.contains(actionName) && !implicitString.equals(tma.getName())) {
        IProblem problem = problemFactory.createUnknownActionProblem(tma);
        pr.reportProblem(problem);
      }

      IRutaExtension extension = actionExtensions.get(actionName);
      if (extension != null) {
        extension.checkSyntax(tma, problemFactory, pr);
      }

      if (tma.getName().equals("GETFEATURE") || tma.getName().equals("SETFEATURE")) {
        List<?> childs = tma.getChilds();
        RutaStringExpression stringExpr = (RutaStringExpression) childs.get(0);
        String feat = stringExpr.toString();
        feat = getFeatureName(stringExpr, feat);
        boolean featureFound = findFeature(matchedType, feat, -1);
        if (!featureFound) {
          IProblem problem = problemFactory.createUnknownFeatureProblem(stringExpr, matchedType);
          pr.reportProblem(problem);
        }
      }

      if (tma.getKind() == RutaActionConstants.A_ASSIGN) {
        List<?> childs = tma.getChilds();
        try {
          RutaVariableReference ref = (RutaVariableReference) childs.get(0);
          RutaExpression expr = (RutaExpression) childs.get(1);
          int type = expr.getKind();
          if (ref.getType() == RutaTypeConstants.RUTA_TYPE_G) {
            ref.setType(type);
          }
        } catch (IndexOutOfBoundsException e) {
          // exception should have been recognized and reported in
          // parser
          return false;
        } catch (ClassCastException e) {
          // exception should have been recognized and reported in
          // parser
          return false;
        }
      }

      if (s instanceof RutaStructureAction) {
        RutaStructureAction sa = (RutaStructureAction) s;
        Expression struct = sa.getStructure();
        String structure = null;
        if (struct != null) {
          structure = sourceModule.getSource().substring(struct.sourceStart(), struct.sourceEnd());
          structure = expand(structure);
        }
        Map<Expression, Expression> assignments = sa.getAssignments();
        // hotfix... correct name in ast
        String action = sourceModule.getSource().substring(sa.getNameStart(), sa.getNameEnd());
        if (assignments != null && !action.equals("TRIE")) {
          for (Expression each : assignments.keySet()) {
            // TODO refactor to visitor?
            String feat = each.toString();
            // List<?> childs = each.getChilds();
            feat = getFeatureName(each, feat);
            boolean featureFound = findFeature(structure, feat, -1);
            if (!featureFound) {
              IProblem problem = problemFactory.createUnknownFeatureProblem(each, structure);
              pr.reportProblem(problem);
            }
          }
        } else if (assignments != null && action.equals("TRIE")) {
          for (Expression each : assignments.values()) {
            if (each instanceof RutaListExpression) {
              RutaListExpression rle = (RutaListExpression) each;
              List<?> childs = rle.getExprs().getChilds();
              if (childs.size() != 2 && childs.size() != 3) {
                IProblem problem = problemFactory.createWrongNumberOfArgumentsProblem(actionName,
                        rle, 2);
                pr.reportProblem(problem);
              }
              Object arg1 = childs.get(0);
              if(arg1 instanceof RutaExpression) {
                RutaExpression e1 = (RutaExpression) arg1;
                if(e1.getKind() != RutaTypeConstants.RUTA_TYPE_AT) {
                  IProblem problem = problemFactory.createWrongArgumentTypeProblem(e1, "Type");
                  pr.reportProblem(problem);
                }
              }
              Object arg2 = childs.get(1);
              if(arg2 instanceof RutaExpression) {
                RutaExpression e2 = (RutaExpression) arg2;
                if(e2.getKind() != RutaTypeConstants.RUTA_TYPE_S) {
                  IProblem problem = problemFactory.createWrongArgumentTypeProblem(e2, "String");
                  pr.reportProblem(problem);
                }
              }
            }
          }
        }
      }
    }
    if (s instanceof RutaCondition) {
      RutaCondition cond = (RutaCondition) s;
      String conditionName = sourceModule.getSource().substring(cond.getNameStart(),
              cond.getNameEnd());
      String[] keywords = RutaKeywordsManager.getKeywords(IRutaKeywords.CONDITION);
      List<String> asList = Arrays.asList(keywords);
      if (!StringUtils.isEmpty(conditionName) && !"-".equals(conditionName)
              && !asList.contains(conditionName) && !implicitString.equals(cond.getName())) {
        IProblem problem = problemFactory.createUnknownConditionProblem(cond);
        pr.reportProblem(problem);
      }

      IRutaExtension extension = conditionExtensions.get(conditionName);
      if (extension != null) {
        // boolean checkSyntax =
        extension.checkSyntax(cond, problemFactory, pr);
      }

      if (conditionName.equals("FEATURE")) {
        if (matchedType != null) {
          List<?> args = cond.getChilds();
          RutaStringExpression se = (RutaStringExpression) args.get(0);
          String feat = se.toString();
          feat = getFeatureName(se, feat);
          boolean featureFound = findFeature(matchedType, feat, -1);
          if (!featureFound) {
            String featureMatch = isFeatureMatch(matchedType);
            if (featureMatch != null) {
              featureFound = findFeature(featureMatch, feat, -1);
            }
          }
          if (!featureFound) {
            IProblem problem = problemFactory.createUnknownFeatureProblem(se, matchedType);
            pr.reportProblem(problem);
          }
        }
      }
    }
    if (s instanceof RutaFunction) {
      RutaFunction f = (RutaFunction) s;
      String name = f.getName();
      if (s.getKind() == RutaTypeConstants.RUTA_TYPE_AT) {
        IRutaExtension extension = typeFunctionExtensions.get(name);
        if (extension != null) {
          extension.checkSyntax(s, problemFactory, pr);
        }
      } else if (s.getKind() == RutaTypeConstants.RUTA_TYPE_B) {
        IRutaExtension extension = booleanFunctionExtensions.get(name);
        if (extension != null) {
          extension.checkSyntax(s, problemFactory, pr);
        }
      } else if (s.getKind() == RutaTypeConstants.RUTA_TYPE_N) {
        IRutaExtension extension = numberFunctionExtensions.get(name);
        if (extension != null) {
          extension.checkSyntax(s, problemFactory, pr);
        }
      } else if (s.getKind() == RutaTypeConstants.RUTA_TYPE_S) {
        IRutaExtension extension = stringFunctionExtensions.get(name);
        if (extension != null) {
          extension.checkSyntax(s, problemFactory, pr);
        }
      }
    }
    return true;
  }

  private String expand(String shortName) {
    if (shortName == null) {
      return null;
    }
    String longName = shortName;
    String string = namespaces.get(shortName);
    if (string != null) {
      longName = string;
    }
    return longName;
  }

  private void checkTypeOfFeatureMatch(String featText, FeatureMatchExpression fme) {
    int lastIndexOf = featText.lastIndexOf(".");
    int firstIndexOf = featText.indexOf(".");
    if (lastIndexOf == -1) {
      return;
    }
    String bref = featText.substring(0, firstIndexOf);
    String aref = featText.substring(0, lastIndexOf);
    String fref = featText.substring(lastIndexOf + 1, featText.length());
    String match = isFeatureMatch(aref);
    if (match == null
            && (getVariableType(aref) == RutaTypeConstants.RUTA_TYPE_AT || getVariableType(bref) == RutaTypeConstants.RUTA_TYPE_AT)) {
      // do not check on variables!
      return;
    }
    match = expand(match);
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
        pr.reportProblem(problemFactory.createUnknownFeatureProblem(fme, aref));
      }
    } else {
      pr.reportProblem(problemFactory.createTypeProblem(fme, sourceModule));
      pr.reportProblem(problemFactory.createUnknownFeatureProblem(fme, aref));
    }
  }

  @Override
  public boolean endvisit(Expression s) throws Exception {
    if (s instanceof RutaRuleElement) {
      matchedType = null;
    }
    return super.endvisit(s);
  }

  @Override
  public boolean endvisit(Statement s) throws Exception {
    if (s instanceof RutaDeclareDeclarationsStatement) {
      parentTypeInDeclaration = null;
    }
    return super.endvisit(s);
  }

  @Override
  public boolean endvisit(MethodDeclaration s) throws Exception {
    if (s instanceof RutaBlock) {
      knownLocalVariables.pop();
      blocks.pop();
    }
    return super.endvisit(s);
  }

  @Override
  public boolean visit(MethodDeclaration s) throws Exception {
    if (s instanceof RutaBlock) {
      RutaBlock b = (RutaBlock) s;
      knownLocalVariables.push(new HashMap<String, Integer>());
      String name = b.getName();
      blocks.push(name);
      // TODO add syntax check for block extensions
    }

    return true;
  }

  private boolean findFeature(String longTypeName, String featureName, int kind) {
    if (longTypeName == null) {
      return false;
    }
    if (longTypeName.equals("Document")
            || longTypeName.equals("org.apache.uima.ruta.type.Document")
            || longTypeName.equals("uima.tcas.DocumentAnnotation")) {
      if (featureName.equals("language") || featureName.equals("begin")
              || featureName.equals("end")) {
        return true;
      }
    }
    if (featureName.equals("begin") || featureName.equals("end")) {
      return kind == -1 || kind == RutaTypeConstants.RUTA_TYPE_N;
    }
    Set<FeatureDescription> set = featureDescriptionMap.get(longTypeName);
    if (set != null) {
      for (FeatureDescription featureDescription : set) {
        String fName = featureDescription.getName();
        // TODO check on correct feature type, e.g., the type of the annotation
        if (fName.equals(featureName) && (kind == -1 || checkFeatureKind(featureDescription, kind))) {
          return true;
        }
      }
    }
    return false;
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

  private void addDeclaredType(String shortName) {
    String longName = getLongNameOfNewType(shortName);
    importType(longName, shortName);
  }

  private String getLongNameOfNewType(String shortName) {
    String moduleName = sourceModule.getElementName();
    moduleName = moduleName.substring(0, moduleName.length() - 5);
    String packagePrefix = "";
    if (!packageName.isEmpty()) {
      packagePrefix = packageName + ".";
    }
    for (String each : blocks) {
      packagePrefix += each + ".";
    }
    String longName = packagePrefix + moduleName + "." + shortName;
    return longName;
  }

  /**
   * Import a type in the current namespace.
   * 
   * @param longName
   *          Complete type name.
   * @param shortName
   *          Short type name (without namespace).
   */
  private void importType(String longName, String shortName) {
    if (allLongTypeNames.contains(longName)) {
      // TODO: in conflict with double import
      // pr.reportProblem(problemFactory.createIdenticalLongTypeNameProblem(longName,
      // sourceModule));
    } else {
      allLongTypeNames.add(longName);
    }
    Set<String> targets = ambiguousTypeAlias.get(shortName);
    if (targets != null) {
      // shortName is already ambiguous, add longName to its list of possible targets
      targets.add(longName);
    } else {
      String existing = namespaces.put(shortName, longName);

      if (existing != null && !existing.equals(longName)) {
        // shortName can now be resolved to "existing" or "longName"
        targets = new HashSet<String>(2);
        targets.add(existing);
        targets.add(longName);

        // add existing mapping and longName to its list of possible targets
        ambiguousTypeAlias.put(shortName, targets);

        // remove shortName from the namespace because it is ambiguous
        namespaces.remove(shortName);
      }
    }
  }

  private boolean knowsVariable(String name) {
    for (Map<String, Integer> each : knownLocalVariables) {
      if (each.containsKey(name)) {
        return true;
      }
    }
    return false;
  }

  private int getVariableType(String name) {
    for (Map<String, Integer> each : knownLocalVariables) {
      Integer integer = each.get(name);
      if (integer != null)
        return integer;
    }
    return 0;
  }

  private String getFeatureName(Expression expression, String defaultValue) {
    // TODO refactor AST. This is not a really straightforward!
    String result = defaultValue;
    List<?> childs = expression.getChilds();
    if (childs != null && !childs.isEmpty()) {
      Object object = childs.get(0);
      if (object instanceof ASTListNode) {
        List<?> childs2 = ((ASTListNode) object).getChilds();
        if (childs2 != null && !childs2.isEmpty()) {
          Object object2 = childs2.get(0);
          if (object2 instanceof StringLiteral) {
            StringLiteral sl = (StringLiteral) object2;
            result = sl.getValue().replaceAll("\"", "");
          }
        }
      }
    }
    return result;
  }

  private void initializePredefinedInformation() {

    typeDescriptionMap = new HashMap<String, TypeDescription>();
    featureDescriptionMap = new HashMap<String, Set<FeatureDescription>>();

    try {
      typeSystemDescription = getTypeSystemOfScript();
      IPath descriptorRootPath = RutaProjectUtils.getDescriptorRootPath(sourceModule
              .getScriptProject().getProject());
      IPath basicTSD = descriptorRootPath.append("BasicTypeSystem.xml");
      importCompleteTypeSystem(basicTSD, null);
    } catch (Exception e) {
      RutaIdeUIPlugin.error(e);
    }
    if (typeSystemDescription != null) {
      TypeDescription[] descriptions = typeSystemDescription.getTypes();
      for (TypeDescription typeDescription : descriptions) {
        String typeName = typeDescription.getName();
        typeDescriptionMap.put(typeName, typeDescription);
      }

      for (TypeDescription typeDescription : descriptions) {
        Set<FeatureDescription> allFeatures = getAllDeclaredFeatures(typeDescription,
                typeDescriptionMap);
        featureDescriptionMap.put(typeDescription.getName(), allFeatures);
      }
    }

    List<String> uimaPredefTypes = Arrays.asList(new String[] { "uima.cas.Boolean",
        "uima.cas.Byte", "uima.cas.Short", "uima.cas.Integer", "uima.cas.Long", "uima.cas.Float",
        "uima.cas.Double", "uima.cas.String", "uima.cas.BooleanArray", "uima.cas.ByteArray",
        "uima.cas.ShortArray", "uima.cas.IntegerArray", "uima.cas.LongArray",
        "uima.cas.FloatArray", "uima.cas.DoubleArray", "uima.cas.StringArray", "uima.cas.FSArray",
        "uima.cas.AnnotationBase", "uima.tcas.Annotation", "uima.tcas.DocumentAnnotation",
        "uima.cas.FloatList", "uima.cas.IntegerList", "uima.cas.StringList", "uima.cas.FSList",
        "uima.cas.EmptyFloatList", "uima.cas.EmptyIntegerList", "uima.cas.EmptyStringList",
        "uima.cas.EmptyFSList", "uima.cas.NonEmptyFloatList", "uima.cas.NonEmptyIntegerList",
        "uima.cas.NonEmptyStringList", "uima.cas.NonEmptyFSList" });
    for (String longName : uimaPredefTypes) {
      String shortName = getShortName(longName);
      importType(longName, shortName);
    }

    this.finalTypes = new HashSet<String>();
    Set<String> uimaFinalTypes = new HashSet<String>();
    uimaFinalTypes.addAll(Arrays.asList(new String[] { "uima.cas.Boolean", "uima.cas.Byte",
        "uima.cas.Short", "uima.cas.Integer", "uima.cas.Long", "uima.cas.Float", "uima.cas.Double",
        "uima.cas.BooleanArray", "uima.cas.ByteArray", "uima.cas.ShortArray",
        "uima.cas.IntegerArray", "uima.cas.LongArray", "uima.cas.FloatArray",
        "uima.cas.DoubleArray", "uima.cas.StringArray", "uima.cas.FSArray" }));

    for (String string : uimaFinalTypes) {
      int indexOf = string.lastIndexOf('.');
      finalTypes.add(string);
      finalTypes.add(string.substring(indexOf + 1, string.length()));
    }
  }

  private TypeSystemDescription importCompleteTypeSystem(IPath path, URL url)
          throws InvalidXMLException, MalformedURLException, IOException, CoreException {
    return importTypeSystem(path, url, null, null, null);
  }

  private void initializeExtensionInformation() {
    conditionExtensions = new HashMap<String, IIDEConditionExtension>();
    actionExtensions = new HashMap<String, IIDEActionExtension>();
    numberFunctionExtensions = new HashMap<String, IIDENumberFunctionExtension>();
    booleanFunctionExtensions = new HashMap<String, IIDEBooleanFunctionExtension>();
    stringFunctionExtensions = new HashMap<String, IIDEStringFunctionExtension>();
    typeFunctionExtensions = new HashMap<String, IIDETypeFunctionExtension>();
    blockExtensions = new HashMap<String, IIDEBlockExtension>();
    IIDEConditionExtension[] cextensions = RutaExtensionManager.getDefault()
            .getIDEConditionExtensions();
    for (IIDEConditionExtension each : cextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        conditionExtensions.put(string, each);
      }
    }
    IIDEActionExtension[] aextensions = RutaExtensionManager.getDefault().getIDEActionExtensions();
    for (IIDEActionExtension each : aextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        actionExtensions.put(string, each);
      }
    }
    IIDENumberFunctionExtension[] nfextensions = RutaExtensionManager.getDefault()
            .getIDENumberFunctionExtensions();
    for (IIDENumberFunctionExtension each : nfextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        numberFunctionExtensions.put(string, each);
      }
    }
    IIDEBooleanFunctionExtension[] bfextensions = RutaExtensionManager.getDefault()
            .getIDEBooleanFunctionExtensions();
    for (IIDEBooleanFunctionExtension each : bfextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        booleanFunctionExtensions.put(string, each);
      }
    }
    IIDEStringFunctionExtension[] sfextensions = RutaExtensionManager.getDefault()
            .getIDEStringFunctionExtensions();
    for (IIDEStringFunctionExtension each : sfextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        stringFunctionExtensions.put(string, each);
      }
    }
    IIDETypeFunctionExtension[] tfextensions = RutaExtensionManager.getDefault()
            .getIDETypeFunctionExtensions();
    for (IIDETypeFunctionExtension each : tfextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        typeFunctionExtensions.put(string, each);
      }
    }
    IIDEBlockExtension[] bextensions = RutaExtensionManager.getDefault().getIDEBlockExtensions();
    for (IIDEBlockExtension each : bextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        blockExtensions.put(string, each);
      }
    }

  }

  private Set<FeatureDescription> getAllDeclaredFeatures(TypeDescription typeDescription,
          Map<String, TypeDescription> typeMap) {
    Set<FeatureDescription> result = new HashSet<FeatureDescription>();
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

  private String isFeatureMatch(String text) {
    for (String each : namespaces.values()) {
      String t = checkFeatureMatch(text, each);
      if (t != null) {
        return t;
      }
    }
    for (String each : namespaces.keySet()) {
      String t = checkFeatureMatch(text, each);
      if (t != null) {
        return t;
      }
    }
    return null;
  }

  private String checkFeatureMatch(String name, String type) {
    if (name.startsWith(type)) {
      boolean foundAll = true;
      if (name.length() > type.length()) {
        String tail = name.substring(type.length() + 1);
        String[] split = tail.split("[.]");
        String typeToCheck = type;
        for (String feat : split) {
          typeToCheck = expand(typeToCheck);
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

  private String checkFSFeatureOfType(String featureName, String longTypeName) {
    TypeDescription t = typeDescriptionMap.get(longTypeName);
    if (t == null) {
      return null;
    }
    FeatureDescription[] features = t.getFeatures();
    for (FeatureDescription featureDescription : features) {
      String name = featureDescription.getName();
      String rangeTypeName = featureDescription.getRangeTypeName();
      boolean isFS = isFeatureStructure(rangeTypeName);
      if (name.equals(featureName) && isFS) {
        return rangeTypeName;
      }
    }
    return null;
  }

  private boolean isFeatureStructure(String rangeTypeName) {
    if (rangeTypeName.equals("uima.tcas.Annotation") || rangeTypeName.equals("uima.cas.TOP")) {
      return true;
    }
    TypeDescription type = typeDescriptionMap.get(rangeTypeName);
    if (type == null) {
      return false;
    }
    String supertypeName = type.getSupertypeName();
    if (supertypeName != null) {
      return isFeatureStructure(supertypeName);
    }
    return false;
  }

  private boolean checkTypeOfVariable(RutaVariableReference ref) {
    Integer vt = getVariableType(ref.getName());
    if (vt == null) {
      IProblem problem = problemFactory.createUnknownVariableProblem(ref);
      pr.reportProblem(problem);
      return false;
    } else {
      int variableType = vt.intValue();
      int requiredType = ref.getType();
      // reject generic types
      if ((requiredType & RutaTypeConstants.RUTA_TYPE_G) != 0) {
        return true;
      }
      if ((variableType & requiredType) == 0) {
        String errMsg = "Variable \"" + ref.getName() + "\" has type "
                + RutaTypeConstants.typeStringOfInt.get(variableType) + ". But type "
                + RutaTypeConstants.typeStringOfInt.get(requiredType) + " is required.";
        IProblem problem = new RutaCheckerDefaultProblem(sourceModule.getElementName(), errMsg,
                ref, linetracker.getLineNumberOfOffset(ref.sourceStart()));
        pr.reportProblem(problem);
        return false;
      }
    }
    return true;
  }

  private boolean isVariableDeclared(RutaVariableReference ref) {
    if (!knowsVariable(ref.getName())) {
      // declared as type?
      if (namespaces.keySet().contains(ref.getName())) {
        String errMsg = "\"" + ref.getName() + "\" declared as a Type. Variable of type "
                + RutaTypeConstants.typeStringOfInt.get(ref.getType()) + " required.";
        IProblem problem = new RutaCheckerDefaultProblem(sourceModule.getElementName(), errMsg,
                ref, linetracker.getLineNumberOfOffset(ref.sourceStart()));
        pr.reportProblem(problem);
        return false;
      }
      String errMsgHead = "Variable \"";
      String errMsgTailDefault = " defined in this script or block!";

      // not found
      String errMsg = errMsgHead + ref.getName() + "\" not" + errMsgTailDefault;
      IProblem problem = new RutaCheckerDefaultProblem(sourceModule.getElementName(), errMsg, ref,
              linetracker.getLineNumberOfOffset(ref.sourceStart()));
      pr.reportProblem(problem);
      return false;
    }
    return true;
  }

  private String getShortName(String typeName) {
    String[] nameSpace = typeName.split("[.]");
    return nameSpace[nameSpace.length - 1];
  }

  private TypeSystemDescription getTypeSystemOfScript() throws InvalidXMLException, IOException,
          CoreException {
    IPath descriptorPath = RutaProjectUtils.getTypeSystemDescriptorPath(sourceModule.getResource()
            .getLocation(), sourceModule.getScriptProject().getProject());
    TypeSystemDescription typeSysDescr = null;
    if (descriptorPath.toFile().exists()) {
      typeSysDescr = UIMAFramework.getXMLParser().parseTypeSystemDescription(
              new XMLInputSource(descriptorPath.toPortableString()));
      ResourceManager resMgr = getResourceManager(classLoader);
      typeSysDescr.resolveImports(resMgr);
    }
    return typeSysDescr;
  }

  private String translate(String name) {
    if (name == null) {
      return null;
    }
    if (name.equals("Annotation")) {
      return "uima.tcas.Annotation";
    } else if (name.equals("STRING")) {
      return UIMAConstants.TYPE_STRING;
    } else if (name.equals("INT")) {
      return UIMAConstants.TYPE_INTEGER;
    } else if (name.equals("DOUBLE")) {
      return UIMAConstants.TYPE_DOUBLE;
    } else if (name.equals("FLOAT")) {
      return UIMAConstants.TYPE_FLOAT;
    } else if (name.equals("BOOLEAN")) {
      return UIMAConstants.TYPE_BOOLEAN;
    } else if (name.equals("TYPE")) {
      return UIMAConstants.TYPE_STRING;
    }
    return name;
  }
}
