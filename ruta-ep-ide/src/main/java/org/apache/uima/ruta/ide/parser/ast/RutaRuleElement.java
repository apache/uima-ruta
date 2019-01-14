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

package org.apache.uima.ruta.ide.parser.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;

public class RutaRuleElement extends Expression {
  protected List<RutaCondition> conditions;

  protected List<RutaAction> actions;

  protected Expression head;

  protected List<Expression> quantifierExpressions;

  private List<List<RutaRule>> inlinedRuleBlocks = new ArrayList<>();

  private String inlineMode;

  private boolean afterConcat;

  private boolean wildcard;

  private String label;

  // TODO to be removed
  public RutaRuleElement(int start, int end) {
    super(start, end);
  }

  public RutaRuleElement(int start, int end, Expression head,
          List<Expression> quantifierPartExpressions, List<RutaCondition> conditions,
          List<RutaAction> actions) {
    super(start, end);
    if (conditions != null) {
      this.conditions = conditions;
    } else {
      this.conditions = new ArrayList<RutaCondition>();
    }
    if (actions != null) {
      this.actions = actions;
    } else {
      this.actions = new ArrayList<RutaAction>();
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
      if (inlinedRuleBlocks != null) {
        for (List<RutaRule> inlinedRules : inlinedRuleBlocks) {
          for (RutaRule rule : inlinedRules) {
            rule.traverse(visitor);
          }
        }
      }
      visitor.endvisit(this);
    }
  }

  public Expression getHead() {
    return head;
  }

  public List<RutaAction> getActions() {
    return actions;
  }

  public List<RutaCondition> getConditions() {
    return conditions;
  }

  public List<Expression> getQuantifierExpressions() {
    return quantifierExpressions;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }

  public void addInlinedRules(List<RutaRule> inlinedRules) {
    if (inlinedRules != null && !inlinedRules.isEmpty()) {
      RutaRule last = inlinedRules.get(inlinedRules.size() - 1);
      if (last != null) {
        setEnd(last.sourceEnd());
      }
    }
    inlinedRuleBlocks.add(inlinedRules);
  }

  public void setInlineMode(String mode) {
    this.inlineMode = mode;
  }

  public String getInlineMode() {
    return inlineMode;
  }

  public void setAfterConcat(Token p) {
    this.afterConcat = p != null;
  }

  public boolean isAfterConcat() {
    return afterConcat;
  }

  public void setAfterConcat(boolean afterConcat) {
    this.afterConcat = afterConcat;
  }

  public boolean isWildcard() {
    return wildcard;
  }

  public void setWildcard(boolean wildcard) {
    this.wildcard = wildcard;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  public List<List<RutaRule>> getInlinedRuleBlocks() {
    return inlinedRuleBlocks;
  }

  public void setInlinedRuleBlocks(List<List<RutaRule>> inlinedRuleBlocks) {
    this.inlinedRuleBlocks = inlinedRuleBlocks;
  }

}
