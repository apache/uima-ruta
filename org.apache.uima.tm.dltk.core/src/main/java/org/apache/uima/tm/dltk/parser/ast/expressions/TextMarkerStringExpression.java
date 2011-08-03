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

import java.util.List;

import org.apache.uima.tm.dltk.parser.ast.TMTypeConstants;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerExpression;
import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;
import org.eclipse.dltk.utils.CorePrinter;


/**
 * Simple string literal or concatenation of strings / string expressions
 * 
 * @author Martin Toepfer
 * 
 */
public class TextMarkerStringExpression extends TextMarkerExpression {
  private ASTListNode exprs;

  public TextMarkerStringExpression(int start, int end, List<Expression> exprList) {
    super(start, end, null, TMTypeConstants.TM_TYPE_S);
    this.exprs = new ASTListNode(start, end, exprList);
  }

  @Override
  public int getKind() {
    return ExpressionConstants.E_CONCAT;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      this.exprs.traverse(visitor);
    }
  }

  @Override
  public void printNode(CorePrinter output) {
    exprs.printNode(output);
  }

  @Override
  public String getOperator() {
    return "+";
  }

}
