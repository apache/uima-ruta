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

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

/**
 * Wrapper for top level expressions like number, boolean, string, type..
 * 
 * 
 */
public class TextMarkerExpression extends Expression {
  private Expression expression;

  private boolean inParantheses;

  /**
   * see {@link TMTypeConstants}
   */
  private int type;

  public TextMarkerExpression(int sourceStart, int sourceEnd, Expression expression, int type) {
    super(sourceStart, sourceEnd);
    this.expression = expression;
    this.type = type;
    this.inParantheses = false;
    if (expression instanceof TextMarkerExpression) {
      this.inParantheses = ((TextMarkerExpression) expression).isInParantheses();
    }
  }

  public int getType() {
    return this.type;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (expression != null) {
        expression.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  public Expression getExpression() {
    return this.expression == null ? this : this.expression;
  }

  @Override
  public int getKind() {
    return expression.getKind();
  }

  public boolean isInParantheses() {
    return inParantheses;
  }

  public void setInParantheses(boolean inParantheses) {
    this.inParantheses = inParantheses;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }
}
