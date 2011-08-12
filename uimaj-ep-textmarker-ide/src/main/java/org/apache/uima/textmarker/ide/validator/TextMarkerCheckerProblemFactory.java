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

import java.util.List;

import org.apache.uima.textmarker.ide.parser.ast.TextMarkerAbstractDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerFeatureDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerVariableReference;
import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.ISourceLineTracker;

public class TextMarkerCheckerProblemFactory {
  // private static final String errMsgHead = "Type \"";
  // private static final String errMsgTailDefault =
  // " \" not defined in this script!";

  private ISourceLineTracker linetracker;

  private String fileName;

  public TextMarkerCheckerProblemFactory(String fileName, ISourceLineTracker linetracker) {
    this.fileName = fileName;
    this.linetracker = linetracker;
  }

  public IProblem createIdConflictsWithVariableProblem(TextMarkerAbstractDeclaration varRef) {
    String message = generateVarAlreadyDeclaredProblemMsg(varRef);
    return new TextMarkerCheckerDefaultProblem(this.fileName, message, varRef, getLine(varRef));
  }

  public IProblem createIdConflictsWithTypeProblem(TextMarkerAbstractDeclaration varRef) {
    String message = generateVarConflictsWithTypeProblem(varRef);
    return new TextMarkerCheckerDefaultProblem(this.fileName, message, varRef, getLine(varRef));
  }

  public IProblem createFileNotFoundProblem(ASTNode node, String localPath) {
    return new TextMarkerCheckerDefaultProblem(this.fileName,
            generateFileNotFoundProblemMsg(localPath), node, getLine(node));
  }

  public IProblem createFileNotFoundProblem(ASTNode fileNode) {
    return createFileNotFoundProblem(fileNode, fileNode.toString());
    // generateFileNotFoundProblemMsg(fileNode));
  }

  public IProblem createDuplicateShortNameInImported(ASTNode node, String localPath,
          List<String> checkDuplicateShortNames, int severity) {
    StringBuilder sb = new StringBuilder();
    for (String string : checkDuplicateShortNames) {
      sb.append(string);
      sb.append(", ");
    }

    return new TextMarkerCheckerDefaultProblem(this.fileName, "Types in " + localPath
            + " share same short name, but with different namespaces: " + sb.toString(), node,
            getLine(node), severity);
  }

  public IProblem createDuplicateShortName(TextMarkerAbstractDeclaration var, int severity) {
    return new TextMarkerCheckerDefaultProblem(this.fileName, "The type " + var.getName()
            + " conflicts with other types with same short name, but different namespace.", var,
            getLine(var), severity);
  }

  public IProblem createXMLProblem(ASTNode node, String localPath) {
    return new TextMarkerCheckerDefaultProblem(this.fileName, generateXMLProblemMsg(localPath),
            node, getLine(node));
  }

  public IProblem createTypeProblem(TextMarkerVariableReference ref, ISourceModule currentFile) {

    String errMsgHead = "Type \"";

    String errMsgTailDefault = " \" not defined in this script/block!";
    String errMsg = errMsgHead + ref.getName() + errMsgTailDefault;
    IProblem problem = new TextMarkerCheckerDefaultProblem(currentFile.getElementName(), errMsg,
            ref, linetracker.getLineNumberOfOffset(ref.sourceStart()));
    return problem;
  }

  private String generateFileNotFoundProblemMsg(ASTNode node) {
    return generateFileNotFoundProblemMsg(node.toString());
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

  private String generateVarAlreadyDeclaredProblemMsg(TextMarkerAbstractDeclaration var) {
    return "error: Id \"" + var.getName() + "\" conflicts with already declared variable.";
  }

  private String generateVarConflictsWithTypeProblem(TextMarkerAbstractDeclaration var) {
    return "error: Identifier \"" + var.getName()
            + "\" conflicts with already declared annotation type.";
  }

  public IProblem createUnknownFeatureTypeProblem(TextMarkerFeatureDeclaration var) {
    String message = "error: Type \"" + var.getType() + "\" of Feature \"" + var.getName()
            + "\" is not defined.";
    return new TextMarkerCheckerDefaultProblem(this.fileName, message, var, getLine(var));
  }

  public IProblem createUnknownFeatureProblem(Expression var) {
    // TODO refactor and find better solution
    String feat = var.toString();
    List childs = var.getChilds();
    if (childs != null && !childs.isEmpty()) {
      Object object = childs.get(0);
      if (object instanceof ASTListNode) {
        List childs2 = ((ASTListNode) object).getChilds();
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
    return new TextMarkerCheckerDefaultProblem(this.fileName, message, var, getLine(var));
  }

  public IProblem createInheritenceFinalProblem(TextMarkerVariableReference parent) {
    String message = "Type \"" + parent.getName()
            + "\" is final and cannot be used as a parent type.";
    return new TextMarkerCheckerDefaultProblem(this.fileName, message, parent, getLine(parent));
  }
}
