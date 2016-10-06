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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.ruta.ide.core.extensions.IRutaCheckerProblemFactory;
import org.apache.uima.ruta.ide.parser.ast.FeatureMatchExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaAbstractDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaCondition;
import org.apache.uima.ruta.ide.parser.ast.RutaFeatureDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaFunction;
import org.apache.uima.ruta.ide.parser.ast.RutaVariableReference;
import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.ISourceLineTracker;

public class RutaCheckerProblemFactory implements IRutaCheckerProblemFactory {
  private ISourceLineTracker linetracker;

  private String fileName;

  public RutaCheckerProblemFactory(String fileName, ISourceLineTracker linetracker) {
    this.fileName = fileName;
    this.linetracker = linetracker;
  }

  public IProblem createIdConflictsWithVariableProblem(RutaAbstractDeclaration varRef) {
    String message = generateVarAlreadyDeclaredProblemMsg(varRef);
    return new RutaCheckerDefaultProblem(this.fileName, message, varRef, getLine(varRef));
  }

  public IProblem createIdConflictsWithTypeProblem(RutaAbstractDeclaration varRef) {
    String message = generateVarConflictsWithTypeProblem(varRef);
    return new RutaCheckerDefaultProblem(this.fileName, message, varRef, getLine(varRef));
  }

  public IProblem createFileNotFoundProblem(ASTNode node, String localPath) {
    return new RutaCheckerDefaultProblem(this.fileName, generateFileNotFoundProblemMsg(localPath),
            node, getLine(node));
  }

  public IProblem createFileNotFoundProblem(ASTNode fileNode) {
    return createFileNotFoundProblem(fileNode, fileNode.toString());
  }

  public IProblem createDuplicateShortNameInImported(ASTNode node, String localPath,
          List<String> checkDuplicateShortNames, ProblemSeverity severity) {
    StringBuilder sb = new StringBuilder();
    Iterator<String> iterator = checkDuplicateShortNames.iterator();
    while (iterator.hasNext()) {
      String string = (String) iterator.next();
      sb.append(string);
      if(iterator.hasNext()) {
        sb.append(", ");
      }
    }
    String msg = String.format(
            "Types in %s share same short name, but with different namespaces: %s.", localPath,
            StringUtils.abbreviate(sb.toString(), 150));
    return new RutaCheckerDefaultProblem(this.fileName, msg, node, getLine(node), severity);
  }

  public IProblem createDuplicateShortName(RutaAbstractDeclaration var, ProblemSeverity severity) {
    return new RutaCheckerDefaultProblem(this.fileName, "The type " + var.getName()
            + " conflicts with other types with same short name, but different namespace.", var,
            getLine(var), severity);
  }

  public IProblem createXMLProblem(ASTNode node, String localPath) {
    return new RutaCheckerDefaultProblem(this.fileName, generateXMLProblemMsg(localPath), node,
            getLine(node));
  }

  public IProblem createWrongPackageProblem(ASTNode node) {
    String message = "The package declaration does not match the project structure.";
    if(node != null) {
      return new RutaCheckerDefaultProblem(this.fileName, message,node,  getLine(node));
    } else {
      return new RutaCheckerDefaultProblem(this.fileName, message);
    }
  }

  
  public IProblem createTypeProblem(RutaVariableReference ref, ISourceModule currentFile) {

    String errMsgHead = "Type \"";

    String errMsgTailDefault = " \" not defined in this script/block!";
    String errMsg = errMsgHead + ref.getName() + errMsgTailDefault;
    IProblem problem = new RutaCheckerDefaultProblem(currentFile.getElementName(), errMsg, ref,
            linetracker.getLineNumberOfOffset(ref.sourceStart()));
    return problem;
  }

  public IProblem createTypeProblem(FeatureMatchExpression ref, ISourceModule currentFile) {

    String errMsgHead = "Type \"";

    String errMsgTailDefault = " \" not defined in this script/block!";
    String errMsg = errMsgHead + ref.getFeature().getText() + errMsgTailDefault;
    IProblem problem = new RutaCheckerDefaultProblem(currentFile.getElementName(), errMsg, ref,
            linetracker.getLineNumberOfOffset(ref.sourceStart()));
    return problem;
  }

  private String generateFileNotFoundProblemMsg(String fileName) {
    return "error: \"" + fileName + "\" not found.";
  }

  private String generateXMLProblemMsg(String fileName) {
    return "error: " + fileName + " causes xml problem.";
  }

  private int getLine(ASTNode varRef) {
    return this.linetracker.getLineNumberOfOffset(varRef.sourceStart());
  }

  private String generateVarAlreadyDeclaredProblemMsg(RutaAbstractDeclaration var) {
    return "error: Id \"" + var.getName() + "\" conflicts with already declared variable.";
  }

  private String generateVarConflictsWithTypeProblem(RutaAbstractDeclaration var) {
    return "error: Identifier \"" + var.getName()
            + "\" conflicts with already declared annotation type.";
  }

  public IProblem createUnknownFeatureTypeProblem(RutaFeatureDeclaration var) {
    String message = "error: Type \"" + var.getType() + "\" of Feature \"" + var.getName()
            + "\" is not defined.";
    return new RutaCheckerDefaultProblem(this.fileName, message, var, getLine(var));
  }

  public IProblem createUnknownFeatureProblem(Expression var, String matchedType) {
    // TODO refactor and find better solution
    String feat = var.toString();
    List<?> childs = var.getChilds();
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
    String message = "error: Feature \"" + feat + "\" is not defined.";
    if (matchedType != null) {
      message = "error: Feature \"" + feat + "\" is not defined for type \"" + matchedType + "\".";
    }
    return new RutaCheckerDefaultProblem(this.fileName, message, var, getLine(var));
  }

  public IProblem createWrongArgumentTypeProblem(Expression was, String expected) {
    String message = "Wrong kind of argument: expected " + expected;
    return new RutaCheckerDefaultProblem(this.fileName, message, was, getLine(was));
  }

  public IProblem createInheritenceFinalProblem(RutaVariableReference parent) {
    String message = "Type \"" + parent.getName()
            + "\" is final and cannot be used as a parent type.";
    return new RutaCheckerDefaultProblem(this.fileName, message, parent, getLine(parent));
  }

  public IProblem createUnknownConditionProblem(RutaCondition cond) {
    String message = "error: Condition \"" + cond.getName() + "\" is not defined.";
    return new RutaCheckerDefaultProblem(this.fileName, message, cond, getLine(cond));
  }

  public IProblem createUnknownActionProblem(RutaAction action) {
    String message = "error: Action \"" + action.getName() + "\" is not defined.";
    return new RutaCheckerDefaultProblem(this.fileName, message, action, getLine(action));
  }

  public IProblem createUnknownVariableProblem(RutaVariableReference ref) {
    String message = "error: Variable \"" + ref.getName() + "\" is not defined.";
    return new RutaCheckerDefaultProblem(this.fileName, message, ref, getLine(ref));
  }
  
  public IProblem createWrongNumberOfArgumentsProblem(String name, Expression element, int expected) {
    String message = "error: The element " + name + " expects " + expected + " arguments.";
    return new RutaCheckerDefaultProblem(this.fileName, message, element, getLine(element));
  }

  public IProblem createUnknownFunctionProblem(RutaFunction f) {
    String message = "error: Function \"" + f.getName() + "\" is not defined.";
    return new RutaCheckerDefaultProblem(this.fileName, message, f, getLine(f));
  }

  public IProblem createAmbiguousShortName(RutaVariableReference ref, Collection<String> ambiguousTargets, ProblemSeverity error) {
    StringBuilder message = new StringBuilder(ref.getName());
    message.append(" is ambiguous, use one of the following instead : ");
    for (String target : ambiguousTargets) {
      message.append(target).append(' ');
    }
    return new RutaCheckerDefaultProblem(this.fileName, message.toString(), ref, getLine(ref));
  }

  public IProblem createIdenticalLongTypeNameProblem(String longName, ISourceModule sourceModule) {
    StringBuilder message = new StringBuilder(longName);
    message.append(" is ambiguous!");
    return new RutaCheckerDefaultProblem(this.fileName, message.toString());
  }

  public IProblem createClosedProjectsProblem(List<IProject> projects) {
    StringBuilder message = new StringBuilder();
    if(projects.size() == 1) {
      message.append("A referenced project is closed: ");
    } else {
      message.append("Referenced project are closed: ");
    }
    Iterator<IProject> iterator = projects.iterator();
    while (iterator.hasNext()) {
      IProject each = (IProject) iterator.next();
      message.append(each.getName());
      if(iterator.hasNext()) {
        message.append(", ");
      }
    }
    
    return new RutaCheckerDefaultProblem(this.fileName, message.toString());    
  }

}
