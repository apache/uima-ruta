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

import org.apache.uima.ruta.ide.parser.ast.RutaAbstractDeclaration;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;

public class RutaCheckerDefaultProblem extends DefaultProblem {

  public RutaCheckerDefaultProblem(String fileName, String message, RutaAbstractDeclaration node,
          int line, int column, ProblemSeverity severity) {
    super(fileName, message, RutaProblemIdentifier.PROBLEM, new String[] {}, severity, node
            .getNameStart(), node.getNameEnd(), line, column);
  }

  public RutaCheckerDefaultProblem(String fileName, String message, RutaAbstractDeclaration node,
          int line) {
    super(fileName, message, 0, new String[] {}, ProblemSeverity.ERROR, node.getNameStart(), node
            .getNameEnd(), line);
  }

  public RutaCheckerDefaultProblem(String fileName, String message, ASTNode node, int line) {
    this(fileName, message, node, line, ProblemSeverity.ERROR);
  }

  public RutaCheckerDefaultProblem(String fileName, String message, ASTNode node, int line,
          ProblemSeverity severity) {
    super(fileName, message, 0, new String[] {}, severity, node.sourceStart(), node.sourceEnd(),
            line);
  }

  public RutaCheckerDefaultProblem(String fileName, String message) {
    super(fileName, message, RutaProblemIdentifier.PROBLEM, new String[] {}, ProblemSeverity.ERROR,
            0, 0, 0, 0);
  }

}
