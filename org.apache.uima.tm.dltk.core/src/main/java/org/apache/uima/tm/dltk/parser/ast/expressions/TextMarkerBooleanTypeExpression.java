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

package org.apache.uima.tm.dltk.parser.ast.expressions;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.utils.CorePrinter;

public class TextMarkerBooleanTypeExpression extends Expression {
  /**
   * <,<=,>,>=,==,...
   */
  private int kind;

  private Expression e1;

  private Expression e2;

  public TextMarkerBooleanTypeExpression(int start, int end, int operatorID, Expression e1,
          Expression e2) {
    super(start, end);
    this.kind = operatorID;
    this.e1 = e1;
    this.e2 = e2;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.ast.statements.Statement#getKind()
   */
  @Override
  public int getKind() {
    return kind;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      // exprs.traverse(visitor);
      if (e1 != null) {
        e1.traverse(visitor);
      }
      if (e2 != null) {
        e2.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  public Expression getE1() {
    return e1;
  }

  public Expression getE2() {
    return e2;
  }

  @Override
  public void printNode(CorePrinter output) {
    // exprs.printNode(output);
    output.print("(");
    e1.printNode(output);
    output.print(" " + getOperator() + " ");
    e2.printNode(output);
    output.print(")");
  }

}
