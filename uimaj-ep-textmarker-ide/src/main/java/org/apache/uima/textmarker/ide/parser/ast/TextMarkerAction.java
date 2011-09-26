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

public class TextMarkerAction extends Expression {
  protected List<Expression> exprs;

  protected int kind;

  private int nameStart;

  public int getNameStart() {
    return nameStart;
  }

  public int getNameEnd() {
    return nameEnd;
  }

  private int nameEnd;

  private String name;

  /**
   * @param start
   * @param end
   * @param exprs
   * @param kind
   */
  public TextMarkerAction(int start, int end, List<Expression> exprs, int kind, String name,
          int nameStart, int nameEnd) {
    super(start, end);
    if (exprs != null) {
      this.exprs = exprs;
    } else {
      this.exprs = new ArrayList<Expression>();
    }
    this.kind = kind;
    this.name = name;
    this.nameStart = nameStart;
    this.nameEnd = nameEnd;
  }

  /**
   * Copy-Constructor
   * 
   * @param source
   */
  public TextMarkerAction(TextMarkerAction source) {
    super(source.sourceStart(), source.sourceEnd());
    this.exprs = source.exprs;
    this.kind = source.kind;
  }

  @Override
  public int getKind() {
    return this.kind;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      for (Expression expr : exprs) {
        if (expr != null) {
          expr.traverse(visitor);
        }

      }
      visitor.endvisit(this);
    }
  }

  @Override
  public List<Expression> getChilds() {
    return exprs;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }
}
