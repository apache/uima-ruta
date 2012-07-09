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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.FeatureDescription;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.textmarker.ide.core.ITextMarkerKeywords;
import org.apache.uima.textmarker.ide.core.TextMarkerKeywords;
import org.apache.uima.textmarker.ide.core.TextMarkerKeywordsManager;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.apache.uima.textmarker.ide.parser.ast.TMActionConstants;
import org.apache.uima.textmarker.ide.parser.ast.TMTypeConstants;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerAction;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerBlock;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerCondition;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerExpression;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerRuleElement;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerStringExpression;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerStructureAction;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerTypeDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerVariableDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerVariableReference;
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

public class TextMarkerVarRefChecker implements IBuildParticipant, IBuildParticipantExtension {

  private class VarRefVisitor extends ASTVisitor {
    private final Stack<Map<String, Integer>> knownLocalVariables;

    private Set<String> knownLocalTypeNames;

    private IProblemReporter rep;

    private ISourceModule currentFile;

    private ISourceLineTracker linetracker;

    private static final String errMsgHead = "Variable \"";

    private static final String errMsgTailDefault = " defined in this script or block!";

    private TextMarkerCheckerProblemFactory problemFactory;

    private TypeSystemDescription description;

    private String matchedType;

    public VarRefVisitor(IProblemReporter rep, ISourceLineTracker linetracker, ISourceModule curFile) {
      this.linetracker = linetracker;
      this.rep = rep;
      this.knownLocalVariables = new Stack<Map<String, Integer>>();
      knownLocalVariables.push(new HashMap<String, Integer>());
      // this.knownLocalVariables = new HashMap<String, Integer>();
      this.currentFile = curFile;
      this.knownLocalTypeNames = new HashSet<String>();
      this.problemFactory = new TextMarkerCheckerProblemFactory(currentFile.getElementName(),
              linetracker);
      try {
        description = getTypeSystemOfScript();
      } catch (InvalidXMLException e) {
      } catch (IOException e) {
      }
    }

    @Override
    public boolean endvisit(Expression s) throws Exception {
      if (s instanceof TextMarkerRuleElement) {
        matchedType = null;
      }
      return super.endvisit(s);
    }

    @Override
    public boolean endvisit(MethodDeclaration s) throws Exception {
      if (s instanceof TextMarkerBlock) {
        knownLocalVariables.pop();
      }
      return super.endvisit(s);
    }

    public boolean visit(MethodDeclaration s) throws Exception {
      if (s instanceof TextMarkerBlock) {
        knownLocalVariables.push(new HashMap<String, Integer>());
      }
      return true;
    }

    @Override
    public boolean visit(Statement s) throws Exception {
      if (s instanceof TextMarkerTypeDeclaration) {
        knownLocalTypeNames.add(((TextMarkerTypeDeclaration) s).getName());
        return false;
      }
      if (s instanceof TextMarkerVariableDeclaration) {
        TextMarkerVariableDeclaration newVar = (TextMarkerVariableDeclaration) s;
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
        knownLocalVariables.peek().put(newVar.getName(), newVar.getType());
        return false;
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
      if (s instanceof TextMarkerRuleElement) {
        TextMarkerRuleElement re = (TextMarkerRuleElement) s;
        Expression head = re.getHead();
        if (head != null) {
          String type = currentFile.getSource().substring(head.sourceStart(), head.sourceEnd());
          matchedType = type;
        }
      }
      if (s instanceof TextMarkerVariableReference) {
        TextMarkerVariableReference ref = (TextMarkerVariableReference) s;
        // filter AnnotationTypeReferences
        if ((ref.getType() & TMTypeConstants.TM_TYPE_AT) != 0) {
          return false;
        }
        if (!isVariableDeclared(ref)) {
          return false;
        }
        checkTypeOfReference(ref);
        return false;
      }
      // check assign types
      if (s instanceof TextMarkerAction) {
        TextMarkerAction tma = (TextMarkerAction) s;

        String actionName = currentFile.getSource().substring(tma.getNameStart(), tma.getNameEnd());
        String[] keywords = TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.ACTION);
        List<String> asList = Arrays.asList(keywords);
        if (!"".equals(actionName) && !"-".equals(actionName) && !asList.contains(actionName)) {
          IProblem problem = problemFactory.createUnknownActionProblem(tma);
          rep.reportProblem(problem);
        }

        if (tma.getKind() == TMActionConstants.A_ASSIGN) {
          List<?> childs = tma.getChilds();
          try {
            TextMarkerVariableReference ref = (TextMarkerVariableReference) childs.get(0);
            TextMarkerExpression expr = (TextMarkerExpression) childs.get(1);
            int type = expr.getType();
            if (ref.getType() == TMTypeConstants.TM_TYPE_G) {
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
        if (s instanceof TextMarkerStructureAction) {
          TextMarkerStructureAction sa = (TextMarkerStructureAction) s;
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
                IProblem problem = problemFactory.createUnknownFeatureProblem(each);
                rep.reportProblem(problem);
              }
            }
          }
        }
      }
      if (s instanceof TextMarkerCondition) {
        TextMarkerCondition cond = (TextMarkerCondition) s;
        String conditionName = currentFile.getSource().substring(cond.getNameStart(),
                cond.getNameEnd());
        String[] keywords = TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.CONDITION);
        List<String> asList = Arrays.asList(keywords);
        if (!"".equals(conditionName) && !"-".equals(conditionName)
                && !asList.contains(conditionName)) {
          IProblem problem = problemFactory.createUnknownConditionProblem(cond);
          rep.reportProblem(problem);
        }

        if (conditionName.equals("FEATURE")) {
          if (matchedType != null) {
            List<?> args = cond.getChilds();
            TextMarkerStringExpression se = (TextMarkerStringExpression) args.get(0);
            String feat = se.toString();
            feat = getFeatureName(se, feat);
            boolean featureFound = findFeature(matchedType, feat);
            if (!featureFound) {
              IProblem problem = problemFactory.createUnknownFeatureProblem(se);
              rep.reportProblem(problem);
            }
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
      boolean featureFound = false;
      if (description == null || structure == null) {
        return featureFound;
      }
      TypeDescription[] descriptions = description.getTypes();
      Map<String, TypeDescription> typeMap = new HashMap<String, TypeDescription>();
      for (TypeDescription typeDescription : descriptions) {
        String typeName = typeDescription.getName();
        typeMap.put(typeName, typeDescription);
      }
      for (TypeDescription typeDescription : descriptions) {
        String typeName = typeDescription.getName();
        if (typeName.endsWith(structure)) {
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
      IPath descriptorPath = TextMarkerProjectUtils.getTypeSystemDescriptorPath(currentFile
              .getPath().removeFirstSegments(1), project.getProject());
      TypeSystemDescription typeSysDescr = null;
      typeSysDescr = UIMAFramework.getXMLParser().parseTypeSystemDescription(
              new XMLInputSource(descriptorPath.toPortableString()));
      ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
      IFolder folder = project.getProject().getFolder(
              TextMarkerProjectUtils.getDefaultDescriptorLocation());
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
    private boolean checkTypeOfReference(TextMarkerVariableReference ref)
            throws IllegalAccessException {
      Integer vt = getVariableType(ref.getName());
      if (vt == null) {
        throw new IllegalAccessException(ref.getName() + " not declared.");
      }
      int variableType = vt.intValue();
      int requiredType = ref.getType();
      // reject generic types
      if ((requiredType & TMTypeConstants.TM_TYPE_G) != 0) {
        return true;
      }
      if ((variableType & requiredType) == 0) {
        String errMsg = errMsgHead + ref.getName() + "\" has type "
                + TMTypeConstants.typeStringOfInt.get(variableType) + ". \nBut type "
                + TMTypeConstants.typeStringOfInt.get(requiredType) + " required.";
        IProblem problem = new TextMarkerCheckerDefaultProblem(currentFile.getElementName(),
                errMsg, ref, linetracker.getLineNumberOfOffset(ref.sourceStart()));
        rep.reportProblem(problem);
        return false;
      }
      return true;
    }

    /**
     * @param ref
     */
    private boolean isVariableDeclared(TextMarkerVariableReference ref) {
      if (!knowsVariable(ref.getName())) {
        // declared as type?
        if (knownLocalTypeNames.contains(ref.getName())) {
          String errMsg = "\"" + ref.getName()
                  + "\" declared as a ANNOTATION_TYPE. Variable of type "
                  + TMTypeConstants.typeStringOfInt.get(ref.getType()) + " required.";
          IProblem problem = new TextMarkerCheckerDefaultProblem(currentFile.getElementName(),
                  errMsg, ref, linetracker.getLineNumberOfOffset(ref.sourceStart()));
          rep.reportProblem(problem);
          return false;
        }
        // not found
        String errMsg = errMsgHead + ref.getName() + "\" not" + errMsgTailDefault;
        IProblem problem = new TextMarkerCheckerDefaultProblem(currentFile.getElementName(),
                errMsg, ref, linetracker.getLineNumberOfOffset(ref.sourceStart()));
        rep.reportProblem(problem);
        return false;
      }
      return true;
    }
  }

  private IScriptProject project;

  public TextMarkerVarRefChecker() throws CoreException {
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
