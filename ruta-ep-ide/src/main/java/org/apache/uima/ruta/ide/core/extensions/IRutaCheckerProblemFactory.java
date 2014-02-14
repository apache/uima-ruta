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

package org.apache.uima.ruta.ide.core.extensions;

import java.util.List;

import org.apache.uima.ruta.ide.parser.ast.RutaAbstractDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaCondition;
import org.apache.uima.ruta.ide.parser.ast.RutaFeatureDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaFunction;
import org.apache.uima.ruta.ide.parser.ast.RutaVariableReference;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;
import org.eclipse.dltk.core.ISourceModule;

public interface IRutaCheckerProblemFactory {

   IProblem createIdConflictsWithVariableProblem(RutaAbstractDeclaration varRef);
   IProblem createIdConflictsWithTypeProblem(RutaAbstractDeclaration varRef);
   IProblem createFileNotFoundProblem(ASTNode node, String localPath);
   IProblem createFileNotFoundProblem(ASTNode fileNode);
   IProblem createDuplicateShortNameInImported(ASTNode node, String localPath,
          List<String> checkDuplicateShortNames, ProblemSeverity severity);
   IProblem createDuplicateShortName(RutaAbstractDeclaration var, ProblemSeverity severity) ;
   IProblem createXMLProblem(ASTNode node, String localPath);
   IProblem createTypeProblem(RutaVariableReference ref, ISourceModule currentFile);
   IProblem createUnknownFeatureTypeProblem(RutaFeatureDeclaration var);
   IProblem createUnknownFeatureProblem(Expression var, String matchedType);
   IProblem createWrongArgumentTypeProblem(Expression was, String expected);
   IProblem createInheritenceFinalProblem(RutaVariableReference parent);
   IProblem createUnknownConditionProblem(RutaCondition cond);
   IProblem createUnknownActionProblem(RutaAction action);
   IProblem createWrongNumberOfArgumentsProblem(String name, Expression element, int expected);
   IProblem createUnknownFunctionProblem(RutaFunction f);

}
