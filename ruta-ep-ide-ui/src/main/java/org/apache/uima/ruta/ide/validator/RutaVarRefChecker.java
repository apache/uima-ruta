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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.FeatureDescription;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.ide.core.IRutaKeywords;
import org.apache.uima.ruta.ide.core.RutaExtensionManager;
import org.apache.uima.ruta.ide.core.RutaKeywordsManager;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.ide.core.extensions.IIDEActionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDEBooleanFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDEConditionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDENumberFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDEStringFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDETypeFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IRutaCheckerProblemFactory;
import org.apache.uima.ruta.ide.core.extensions.IRutaExtension;
import org.apache.uima.ruta.ide.parser.ast.FeatureMatchExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaActionConstants;
import org.apache.uima.ruta.ide.parser.ast.RutaBlock;
import org.apache.uima.ruta.ide.parser.ast.RutaCondition;
import org.apache.uima.ruta.ide.parser.ast.RutaExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaFunction;
import org.apache.uima.ruta.ide.parser.ast.RutaRegExpRule;
import org.apache.uima.ruta.ide.parser.ast.RutaRuleElement;
import org.apache.uima.ruta.ide.parser.ast.RutaStringExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaStructureAction;
import org.apache.uima.ruta.ide.parser.ast.RutaTypeConstants;
import org.apache.uima.ruta.ide.parser.ast.RutaTypeDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaVariableDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaVariableReference;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension;
import org.eclipse.dltk.core.builder.ISourceLineTracker;

public class RutaVarRefChecker implements IBuildParticipant, IBuildParticipantExtension {

  private class VarRefVisitor extends ASTVisitor {
    private final Stack<Map<String, Integer>> knownLocalVariables;

    private Set<String> knownLocalTypeNames;

    private IProblemReporter rep;

    private ISourceModule currentFile;

    private ISourceLineTracker linetracker;

    private static final String errMsgHead = "Variable \"";

    private static final String errMsgTailDefault = " defined in this script or block!";

    private RutaCheckerProblemFactory problemFactory;

    private TypeSystemDescription description;

    private String matchedType;

    private Set<String> completeTypes;

    private Set<String> shortTypes;

    private Map<String, IIDEActionExtension> actionExtensions;

    private Map<String, IIDEConditionExtension> conditionExtensions;

    private Map<String, IIDEBooleanFunctionExtension> booleanFunctionExtensions;

    private Map<String, IIDENumberFunctionExtension> numberFunctionExtensions;

    private Map<String, IIDEStringFunctionExtension> stringFunctionExtensions;

    private Map<String, IIDETypeFunctionExtension> typeFunctionExtensions;

    public VarRefVisitor(IProblemReporter rep, ISourceLineTracker linetracker, ISourceModule curFile) {
      this.linetracker = linetracker;
      this.rep = rep;
      this.knownLocalVariables = new Stack<Map<String, Integer>>();
      knownLocalVariables.push(new HashMap<String, Integer>());
      // this.knownLocalVariables = new HashMap<String, Integer>();
      this.currentFile = curFile;
      this.knownLocalTypeNames = new HashSet<String>();
      this.problemFactory = new RutaCheckerProblemFactory(currentFile.getElementName(), linetracker);
      conditionExtensions = new HashMap<String, IIDEConditionExtension>();
      actionExtensions = new HashMap<String, IIDEActionExtension>();
      numberFunctionExtensions = new HashMap<String, IIDENumberFunctionExtension>();
      booleanFunctionExtensions = new HashMap<String, IIDEBooleanFunctionExtension>();
      stringFunctionExtensions = new HashMap<String, IIDEStringFunctionExtension>();
      typeFunctionExtensions = new HashMap<String, IIDETypeFunctionExtension>();
      IIDEConditionExtension[] cextensions = RutaExtensionManager.getDefault()
              .getIDEConditionExtensions();
      for (IIDEConditionExtension each : cextensions) {
        String[] knownExtensions = each.getKnownExtensions();
        for (String string : knownExtensions) {
          conditionExtensions.put(string, each);
        }
      }
      IIDEActionExtension[] aextensions = RutaExtensionManager.getDefault()
              .getIDEActionExtensions();
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

      try {
        description = getTypeSystemOfScript();
      } catch (InvalidXMLException e) {
      } catch (IOException e) {
      }
      completeTypes = new TreeSet<String>();
      shortTypes = new TreeSet<String>();
      if (description != null) {
        TypeDescription[] types = description.getTypes();
        for (TypeDescription typeDescription : types) {
          completeTypes.add(typeDescription.getName());
          shortTypes.add(getShortName(typeDescription.getName()));
        }
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

    @Override
    public boolean endvisit(Expression s) throws Exception {
      if (s instanceof RutaRuleElement) {
        matchedType = null;
      }
      return super.endvisit(s);
    }

    @Override
    public boolean endvisit(MethodDeclaration s) throws Exception {
      if (s instanceof RutaBlock) {
        knownLocalVariables.pop();
      }
      return super.endvisit(s);
    }

    public boolean visit(MethodDeclaration s) throws Exception {
      if (s instanceof RutaBlock) {
        knownLocalVariables.push(new HashMap<String, Integer>());
      }
      return true;
    }

    @Override
    public boolean visit(Statement s) throws Exception {
      if (s instanceof RutaTypeDeclaration) {
        knownLocalTypeNames.add(((RutaTypeDeclaration) s).getName());
        return false;
      }
      if (s instanceof RutaVariableDeclaration) {
        RutaVariableDeclaration newVar = (RutaVariableDeclaration) s;
        if (knowsVariable(newVar.getName())) {
          IProblem problem = problemFactory.createIdConflictsWithVariableProblem(newVar);
          rep.reportProblem(problem);
          return false;
        }
        if (knownLocalTypeNames.contains(newVar.getName())) {
          IProblem problem = problemFactory.createIdConflictsWithTypeProblem(newVar);
          rep.reportProblem(problem);
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
            structure = currentFile.getSource().substring(struct.sourceStart(), struct.sourceEnd());
          }
          Map<Expression, Expression> fmap = entry.getValue();
          Set<Expression> keySet = fmap.keySet();
          for (Expression fkey : keySet) {
            if (fkey instanceof RutaExpression && fkey.getKind() == RutaTypeConstants.RUTA_TYPE_S) {
              String feat = fkey.toString();
              feat = getFeatureName(fkey, feat);
              boolean findFeature = findFeature(structure, feat);
              if (!findFeature) {
                IProblem problem = problemFactory.createUnknownFeatureProblem(fkey, structure);
                rep.reportProblem(problem);
              }
            }
          }
        }
      }
      return true;
    }

    private boolean knowsVariable(String name) {
      for (Map<String, Integer> each : knownLocalVariables) {
        if (each.containsKey(name)) {
          return true;
        }
      }
      return false;
    }

    private Integer getVariableType(String name) {
      for (Map<String, Integer> each : knownLocalVariables) {
        Integer integer = each.get(name);
        if (integer != null)
          return integer;
      }
      return 0;
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
          matchedType = isFeatureMatch(twf);
        } else if (head != null) {
          String type = currentFile.getSource().substring(head.sourceStart(), head.sourceEnd());
          matchedType = type;
        }
      }
      if (s instanceof RutaVariableReference) {
        RutaVariableReference ref = (RutaVariableReference) s;
        // filter AnnotationTypeReferences
        if ((ref.getType() & RutaTypeConstants.RUTA_TYPE_AT) != 0) {
          return false;
        }
        if (!isVariableDeclared(ref)) {
          return false;
        }
        checkTypeOfReference(ref);
        return false;
      }
      // check assign types
      if (s instanceof RutaAction) {
        RutaAction tma = (RutaAction) s;

        String actionName = currentFile.getSource().substring(tma.getNameStart(), tma.getNameEnd());
        String[] keywords = RutaKeywordsManager.getKeywords(IRutaKeywords.ACTION);
        List<String> asList = Arrays.asList(keywords);
        if (!StringUtils.isEmpty(actionName) && !"-".equals(actionName)
                && !asList.contains(actionName)) {
          IProblem problem = problemFactory.createUnknownActionProblem(tma);
          rep.reportProblem(problem);
        }

        IRutaExtension extension = actionExtensions.get(actionName);
        if (extension != null) {
          // boolean checkSyntax =
          extension.checkSyntax(tma, problemFactory, rep);
        }

        if (tma.getName().equals("GETFEATURE") || tma.getName().equals("SETFEATURE")) {
          List<?> childs = tma.getChilds();
          RutaStringExpression stringExpr = (RutaStringExpression) childs.get(0);
          String feat = stringExpr.toString();
          feat = getFeatureName(stringExpr, feat);
          boolean featureFound = findFeature(matchedType, feat);
          if (!featureFound) {
            IProblem problem = problemFactory.createUnknownFeatureProblem(stringExpr, matchedType);
            rep.reportProblem(problem);
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
            structure = currentFile.getSource().substring(struct.sourceStart(), struct.sourceEnd());
          }
          Map<Expression, Expression> assignments = sa.getAssignments();
          // hotfix... correct name in ast
          String action = currentFile.getSource().substring(sa.getNameStart(), sa.getNameEnd());
          if (assignments != null && !action.equals("TRIE")) {
            for (Expression each : assignments.keySet()) {
              // TODO refactor to visitor?
              String feat = each.toString();
              // List<?> childs = each.getChilds();
              feat = getFeatureName(each, feat);
              boolean featureFound = findFeature(structure, feat);
              if (!featureFound) {
                IProblem problem = problemFactory.createUnknownFeatureProblem(each, structure);
                rep.reportProblem(problem);
              }
            }
          }
        }
      }
      if (s instanceof RutaCondition) {
        RutaCondition cond = (RutaCondition) s;
        String conditionName = currentFile.getSource().substring(cond.getNameStart(),
                cond.getNameEnd());
        String[] keywords = RutaKeywordsManager.getKeywords(IRutaKeywords.CONDITION);
        List<String> asList = Arrays.asList(keywords);
        if (!StringUtils.isEmpty(conditionName) && !"-".equals(conditionName)
                && !asList.contains(conditionName)) {
          IProblem problem = problemFactory.createUnknownConditionProblem(cond);
          rep.reportProblem(problem);
        }

        IRutaExtension extension = conditionExtensions.get(conditionName);
        if (extension != null) {
          // boolean checkSyntax =
          extension.checkSyntax(cond, problemFactory, rep);
        }

        if (conditionName.equals("FEATURE")) {
          if (matchedType != null) {
            List<?> args = cond.getChilds();
            RutaStringExpression se = (RutaStringExpression) args.get(0);
            String feat = se.toString();
            feat = getFeatureName(se, feat);
            boolean featureFound = findFeature(matchedType, feat);
            if (!featureFound) {
              IProblem problem = problemFactory.createUnknownFeatureProblem(se, matchedType);
              rep.reportProblem(problem);
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
            extension.checkSyntax(s, problemFactory, rep);
          }
        } else if (s.getKind() == RutaTypeConstants.RUTA_TYPE_B) {
          IRutaExtension extension = booleanFunctionExtensions.get(name);
          if (extension != null) {
            extension.checkSyntax(s, problemFactory, rep);
          }
        } else if (s.getKind() == RutaTypeConstants.RUTA_TYPE_N) {
          IRutaExtension extension = numberFunctionExtensions.get(name);
          if (extension != null) {
            extension.checkSyntax(s, problemFactory, rep);
          }
        } else if (s.getKind() == RutaTypeConstants.RUTA_TYPE_S) {
          IRutaExtension extension = stringFunctionExtensions.get(name);
          if (extension != null) {
            extension.checkSyntax(s, problemFactory, rep);
          }
        }
      }
      return true;
    }

    private String getFeatureName(Expression e, String feat) {
      List<?> childs = e.getChilds();
      if (childs != null && !childs.isEmpty()) {
        Object object = childs.get(0);
        if (object instanceof ASTListNode) {
          List<?> childs2 = ((ASTListNode) object).getChilds();
          if (childs2 != null && !childs2.isEmpty()) {
            Object object2 = childs2.get(0);
            if (object2 instanceof StringLiteral) {
              StringLiteral sl = (StringLiteral) object2;
              feat = sl.getValue().replaceAll("\"", "");
            }
          }
        }
      }
      return feat;
    }

    private boolean findFeature(String structure, String feat) {
      if (description == null) {
        return true;
      }
      if (structure == null) {
        return false;
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
            if (featureName.equals(feat)) {
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

    private TypeSystemDescription getTypeSystemOfScript() throws InvalidXMLException, IOException {
      IPath descriptorPath = RutaProjectUtils.getTypeSystemDescriptorPath(currentFile.getPath()
              .removeFirstSegments(1), project.getProject());
      TypeSystemDescription typeSysDescr = null;
      typeSysDescr = UIMAFramework.getXMLParser().parseTypeSystemDescription(
              new XMLInputSource(descriptorPath.toPortableString()));
      ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
      IFolder folder = project.getProject().getFolder(
              RutaProjectUtils.getDefaultDescriptorLocation());
      resMgr.setDataPath(folder.getLocation().toPortableString());
      typeSysDescr.resolveImports(resMgr);
      return typeSysDescr;
    }

    /**
     * @param ref
     * @return
     * @throws IllegalAccessException
     *           if ref not declared
     */
    private boolean checkTypeOfReference(RutaVariableReference ref) throws IllegalAccessException {
      Integer vt = getVariableType(ref.getName());
      if (vt == null) {
        throw new IllegalAccessException(ref.getName() + " not declared.");
      }
      int variableType = vt.intValue();
      int requiredType = ref.getType();
      // reject generic types
      if ((requiredType & RutaTypeConstants.RUTA_TYPE_G) != 0) {
        return true;
      }
      if ((variableType & requiredType) == 0) {
        String errMsg = errMsgHead + ref.getName() + "\" has type "
                + RutaTypeConstants.typeStringOfInt.get(variableType) + ". \nBut type "
                + RutaTypeConstants.typeStringOfInt.get(requiredType) + " required.";
        IProblem problem = new RutaCheckerDefaultProblem(currentFile.getElementName(), errMsg, ref,
                linetracker.getLineNumberOfOffset(ref.sourceStart()));
        rep.reportProblem(problem);
        return false;
      }
      return true;
    }

    /**
     * @param ref
     */
    private boolean isVariableDeclared(RutaVariableReference ref) {
      if (!knowsVariable(ref.getName())) {
        // declared as type?
        if (knownLocalTypeNames.contains(ref.getName())) {
          String errMsg = "\"" + ref.getName()
                  + "\" declared as a ANNOTATION_TYPE. Variable of type "
                  + RutaTypeConstants.typeStringOfInt.get(ref.getType()) + " required.";
          IProblem problem = new RutaCheckerDefaultProblem(currentFile.getElementName(), errMsg,
                  ref, linetracker.getLineNumberOfOffset(ref.sourceStart()));
          rep.reportProblem(problem);
          return false;
        }
        // not found
        String errMsg = errMsgHead + ref.getName() + "\" not" + errMsgTailDefault;
        IProblem problem = new RutaCheckerDefaultProblem(currentFile.getElementName(), errMsg, ref,
                linetracker.getLineNumberOfOffset(ref.sourceStart()));
        rep.reportProblem(problem);
        return false;
      }
      return true;
    }
  }

  private IScriptProject project;

  public RutaVarRefChecker() throws CoreException {
  }

  public boolean beginBuild(int buildType) {
    return true;
  }

  public void endBuild(IProgressMonitor monitor) {
  }

  public void build(IBuildContext context) throws CoreException {
    // getAST:
    this.project = context.getSourceModule().getScriptProject();
    Object mdObj = context.get(IBuildContext.ATTR_MODULE_DECLARATION);
    if (!(mdObj instanceof ModuleDeclaration)) {
      return;
    }
    ModuleDeclaration md = (ModuleDeclaration) mdObj;

    IProblemReporter problemReporter = context.getProblemReporter();
    // get Types:
    ISourceModule smod = context.getSourceModule();
    ISourceLineTracker linetracker = context.getLineTracker();
    try {
      ASTVisitor visitor = new VarRefVisitor(problemReporter, linetracker, smod);
      md.traverse(visitor);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
