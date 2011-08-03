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

package org.apache.uima.tm.dltk.parser.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;

public class ComposedRuleElement extends TextMarkerRuleElement {
  List<Expression> elements;

  private List<Expression> quantifierExpressions;

  public ComposedRuleElement(int start, int end, List<Expression> elements,
          List<Expression> quantifierExpressions) {
    super(start, end);
    if (elements != null) {
      this.elements = elements;
    } else {
      conditions = new ArrayList<Expression>();
    }

    if (quantifierExpressions != null) {
      this.quantifierExpressions = quantifierExpressions;
    } else {
      this.quantifierExpressions = new ArrayList<Expression>();
    }
  }

  @Override
  public int getKind() {
    return ExpressionConstants.E_CALL;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (elements != null) {
        for (Expression cond : elements) {
          cond.traverse(visitor);
        }
      }
      if (quantifierExpressions != null) {
        for (Expression qpe : quantifierExpressions) {
          qpe.traverse(visitor);
        }
      }
    }
  }

  @Override
  public Expression getHead() {
    return head;
  }

  public List<Expression> getElements() {
    return elements;
  }

  @Override
  public List<Expression> getQuantifierExpressions() {
    return quantifierExpressions;
  }

}
