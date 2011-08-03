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

import org.apache.uima.tm.dltk.parser.ast.TMExpressionConstants;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerInnerListExpression extends Expression {
  private String innerList;

  /**
   * @param start
   * @param end
   */
  public TextMarkerInnerListExpression(int start, int end, String inner) {
    super(start, end);
    this.innerList = inner == null ? "" : inner;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      visitor.endvisit(this);
    }
  }

  @Override
  public String getOperator() {
    return TMExpressionConstants.E_INNERLIST_STR;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.ast.statements.Statement#getKind()
   */
  @Override
  public int getKind() {
    return TMExpressionConstants.E_INNERLIST;
  }

  /**
   * @param innerList
   *          the innerList to set
   */
  public void setInnerList(String innerList) {
    this.innerList = innerList;
  }

  /**
   * @return the innerList
   */
  public String getInnerList() {
    return innerList;
  }

}
