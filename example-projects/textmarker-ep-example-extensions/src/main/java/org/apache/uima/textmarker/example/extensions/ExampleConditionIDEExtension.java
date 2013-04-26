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

package org.apache.uima.textmarker.example.extensions;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.apache.uima.textmarker.ide.core.extensions.IIDEConditionExtension;
import org.apache.uima.textmarker.ide.parser.ast.TMTypeConstants;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerCondition;
import org.apache.uima.textmarker.ide.validator.TextMarkerCheckerProblemFactory;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;



public class ExampleConditionIDEExtension implements IIDEConditionExtension {
  private final String[] strings = new String[] { "ExampleCondition" };

  public String[] getKnownExtensions() {
    return strings;
  }

  public boolean checkSyntax(Expression element, TextMarkerCheckerProblemFactory problemFactory,
          IProblemReporter rep) throws RecognitionException {
    if (element instanceof TextMarkerCondition) {
      TextMarkerCondition a = (TextMarkerCondition) element;
      String name = a.getName();
      if (!name.equals(strings[0])) {
        IProblem problem = problemFactory.createUnknownConditionProblem(a);
        rep.reportProblem(problem);
        return false;
      }
      boolean ok = true;
      List<Expression> childs = a.getChilds();
      if(childs.size() != 2) {
        IProblem problem = problemFactory.createWrongNumberOfArgumentsProblem(name, element, 2);
        rep.reportProblem(problem);
        ok = false;
      }
      for (Expression expression : childs) {
        if (expression.getKind() != TMTypeConstants.TM_TYPE_S) {
          IProblem problem = problemFactory.createWrongArgumentTypeProblem(expression, "StringExpression");
          rep.reportProblem(problem);
          ok = false;
        }
      }
      return ok;
    }
    return false;
  }

}
