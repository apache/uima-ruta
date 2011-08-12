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

package org.apache.uima.textmarker.ide.parser.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;

public class TextMarkerRuleElement extends Expression {
  List<Expression> conditions;

  List<Expression> actions;

  Expression head;

  private List<Expression> quantifierExpressions;

  // TODO to be removed
  public TextMarkerRuleElement(int start, int end) {
    super(start, end);
  }

  public TextMarkerRuleElement(int start, int end, Expression head,
          List<Expression> quantifierPartExpressions, List<Expression> conditions,
          List<Expression> actions) {
    super(start, end);
    if (conditions != null) {
      this.conditions = conditions;
    } else {
      conditions = new ArrayList<Expression>();
    }
    if (actions != null) {
      this.actions = actions;
    } else {
      actions = new ArrayList<Expression>();
    }
    if (quantifierPartExpressions != null) {
      this.quantifierExpressions = quantifierPartExpressions;
    } else {
      this.quantifierExpressions = new ArrayList<Expression>();
    }
    this.head = head;
  }

  @Override
  public int getKind() {
    return ExpressionConstants.E_CALL;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (head != null) {
        head.traverse(visitor);
      }
      if (quantifierExpressions != null) {
        for (Expression qpe : quantifierExpressions) {
          qpe.traverse(visitor);
        }
      }
      if (conditions != null) {
        for (Expression cond : conditions) {
          cond.traverse(visitor);
        }
      }
      if (actions != null) {
        for (Expression action : actions) {
          action.traverse(visitor);
        }
      }
    }
  }

  public Expression getHead() {
    return head;
  }

  public List<Expression> getActions() {
    return actions;
  }

  public List<Expression> getConditions() {
    return conditions;
  }

  public List<Expression> getQuantifierExpressions() {
    return quantifierExpressions;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }

}
