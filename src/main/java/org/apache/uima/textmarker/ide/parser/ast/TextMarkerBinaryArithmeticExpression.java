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
import org.eclipse.dltk.utils.CorePrinter;

public class TextMarkerBinaryArithmeticExpression extends Expression {
  private Expression exprA;

  private Expression exprB;

  /**
   * Classifies the type of Expression represented (+,-,*,...).
   */
  private int kind;

  public TextMarkerBinaryArithmeticExpression(int start, int end, Expression exprA,
          Expression exprB, int kind) {
    super(start, end);
    this.exprA = exprA;
    this.exprB = exprB;
    this.kind = kind;
  }

  @Override
  public int getKind() {
    return this.kind;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (exprA != null) {
        exprA.traverse(visitor);
      }
      if (exprB != null) {
        exprB.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public void printNode(CorePrinter output) {
    output.formatPrintLn("[" + exprA.toString() + " " + getOperator() + " " + exprB.toString()
            + "]");
  }
}
