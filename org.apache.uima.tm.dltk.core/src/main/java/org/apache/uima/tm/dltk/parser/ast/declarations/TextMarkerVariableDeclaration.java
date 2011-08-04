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

package org.apache.uima.tm.dltk.parser.ast.declarations;

import org.apache.uima.tm.dltk.parser.ast.TMTypeConstants;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;

public class TextMarkerVariableDeclaration extends TextMarkerAbstractDeclaration {

  private int type;

  private boolean hasInitExpression;

  private Expression initExpression;

  public TextMarkerVariableDeclaration(String name, int nameStart, int nameEnd, int declStart,
          int declEnd, SimpleReference ref, int type) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref);
    this.type = type;
    hasInitExpression = false;
  }

  public TextMarkerVariableDeclaration(String name, int nameStart, int nameEnd, int declStart,
          int declEnd, SimpleReference ref, int type, Expression initExpression) {
    this(name, nameStart, nameEnd, declStart, declEnd, ref, type);
    this.initExpression = initExpression;
    if (initExpression != null) {
      hasInitExpression = true;
    }
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      getRef().traverse(visitor);
      if (hasInitExpression) {
        initExpression.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public String toString() {
    return this.getName() + ":: TextMarkerIntVariable :: " + super.toString();
  }

  /**
   * @return see {@link TMTypeConstants}
   */
  public int getType() {
    return this.type;
  }

  public boolean hasInitExpression() {
    return hasInitExpression;
  }
}
