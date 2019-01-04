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

package org.apache.uima.ruta.type;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.apache.uima.ruta.ide.core.extensions.IIDETypeFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IRutaCheckerProblemFactory;
import org.apache.uima.ruta.ide.parser.ast.RutaFunction;
import org.apache.uima.ruta.ide.parser.ast.RutaTypeConstants;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public class TypeFromStringFunctionIDEExtension implements IIDETypeFunctionExtension {
  private final String[] strings = new String[] { "typeFromString" };

  public String[] getKnownExtensions() {
    return strings;
  }

  public boolean checkSyntax(Expression element, IRutaCheckerProblemFactory problemFactory,
          IProblemReporter rep) throws RecognitionException {
    if (element instanceof RutaFunction) {
      RutaFunction f = (RutaFunction) element;
      String name = f.getName();
      if (!name.equals(strings[0])) {
        IProblem problem = problemFactory.createUnknownFunctionProblem(f);
        rep.reportProblem(problem);
        return false;
      }
      boolean ok = true;
      List<ASTNode> childs = f.getChilds();
      if (childs.size() != 1) {
        IProblem problem = problemFactory.createWrongNumberOfArgumentsProblem(name, element, 1);
        rep.reportProblem(problem);
        ok = false;
      }
      ASTNode expr = childs.get(0);
      if (expr instanceof Expression &&  ((Expression)expr).getKind() != RutaTypeConstants.RUTA_TYPE_S) {
        IProblem problem = problemFactory.createWrongArgumentTypeProblem((Expression) expr, "StringExpression");
        rep.reportProblem(problem);
        ok = false;
      }
      return ok;
    }
    return false;
  }

}
