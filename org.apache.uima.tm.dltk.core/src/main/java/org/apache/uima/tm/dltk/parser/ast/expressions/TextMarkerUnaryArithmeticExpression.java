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

import java.util.Iterator;
import java.util.Map;

import org.apache.uima.tm.dltk.parser.ast.TMExpressionConstants;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerUnaryArithmeticExpression extends Expression {
  private int opID;

  private Expression expr;

  public TextMarkerUnaryArithmeticExpression(int start, int end, Expression expr, int opID) {
    super(start, end);
    this.opID = opID;
    this.expr = expr;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.ast.statements.Statement#getKind()
   */
  @Override
  public int getKind() {
    return opID;
  }

  @Override
  public String getOperator() {
    Map<String, Integer> map = TMExpressionConstants.opIDs;
    for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
      String key = (String) iterator.next();
      Integer intID = map.get(key);
      if (intID.equals(opID)) {
        return key;
      }
    }
    return super.getOperator();
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (expr != null) {
        this.expr.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

}
