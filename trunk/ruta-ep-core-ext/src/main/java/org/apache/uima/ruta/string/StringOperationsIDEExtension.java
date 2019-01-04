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

package org.apache.uima.ruta.string;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.uima.ruta.ide.core.extensions.IIDEStringFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IRutaCheckerProblemFactory;
import org.apache.uima.ruta.ide.parser.ast.RutaFunction;
import org.apache.uima.ruta.ide.parser.ast.RutaTypeConstants;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public class StringOperationsIDEExtension implements IIDEStringFunctionExtension {
  private final String[] strings = new String[] { "toUpperCase", "toLowerCase", "replaceFirst",
      "replaceAll", "substring", "firstCharToUpperCase" };

  public String[] getKnownExtensions() {
    return strings;
  }

  public boolean checkSyntax(Expression element, IRutaCheckerProblemFactory problemFactory,
          IProblemReporter rep) throws RecognitionException {
    if (element instanceof RutaFunction) {
      RutaFunction f = (RutaFunction) element;
      String name = f.getName();
      List<ASTNode> childs = f.getChilds();
      boolean ok = true;

      if (StringUtils.equals(name, strings[0]) || StringUtils.equals(name, strings[1])
              || StringUtils.equals(name, strings[5])) {
        if (childs.size() != 1) {
          IProblem problem = problemFactory.createWrongNumberOfArgumentsProblem(name, element, 1);
          rep.reportProblem(problem);
          ok = false;
        }
      } else if (StringUtils.equals(name, strings[2]) || StringUtils.equals(name, strings[3])
              || StringUtils.equals(name, strings[4])) {
        if (childs.size() != 3) {
          IProblem problem = problemFactory.createWrongNumberOfArgumentsProblem(name, element, 3);
          rep.reportProblem(problem);
          ok = false;
        }
      } else {
        IProblem problem = problemFactory.createUnknownFunctionProblem(f);
        rep.reportProblem(problem);
        return false;
      }

      if (StringUtils.equals(name, strings[4])) {
        Expression expr0 = (Expression) childs.get(0);
        if (expr0.getKind() != RutaTypeConstants.RUTA_TYPE_S && expr0.getKind() != -1) {
          IProblem problem = problemFactory.createWrongArgumentTypeProblem(expr0,
                  "StringExpression");
          rep.reportProblem(problem);
          ok = false;
        }
        Expression expr1 = (Expression) childs.get(1);
        if (expr1.getKind() != RutaTypeConstants.RUTA_TYPE_N && expr1.getKind() != -1) {
          IProblem problem = problemFactory.createWrongArgumentTypeProblem(expr1,
                  "NumberExpression");
          rep.reportProblem(problem);
          ok = false;
        }
        Expression expr2 = (Expression) childs.get(2);
        if (expr2.getKind() != RutaTypeConstants.RUTA_TYPE_N && expr2.getKind() != -1) {
          IProblem problem = problemFactory.createWrongArgumentTypeProblem(expr2,
                  "NumberExpression");
          rep.reportProblem(problem);
          ok = false;
        }
      } else {
        for (ASTNode astNode : childs) {
          Expression expr = (Expression) astNode;
          if (expr.getKind() != RutaTypeConstants.RUTA_TYPE_S && expr.getKind() != -1) {
            IProblem problem = problemFactory.createWrongArgumentTypeProblem(expr,
                    "StringExpression");
            rep.reportProblem(problem);
            ok = false;
          }
        }
      }

      return ok;
    }
    return false;
  }

}
