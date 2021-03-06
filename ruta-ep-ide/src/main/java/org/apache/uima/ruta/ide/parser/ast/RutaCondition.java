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
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class RutaCondition extends Expression {
  protected List<ASTNode> exprs;

  protected int kind;

  private int nameStart;

  private int nameEnd;

  private String name;

  public RutaCondition(int start, int end, List<ASTNode> exprs, int kind, String name,
          int nameStart, int nameEnd) {
    super(start, end);
    if (exprs != null) {
      this.exprs = exprs;
    } else {
      this.exprs = new ArrayList<ASTNode>();
    }
    this.kind = kind;
    this.name = name;
    this.nameStart = nameStart;
    this.nameEnd = nameEnd;
  }

  @Override
  public int getKind() {
    return this.kind;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      for (Iterator<ASTNode> iterator = exprs.iterator(); iterator.hasNext();) {
        ASTNode expr = iterator.next();
        if (expr != null) {
          expr.traverse(visitor);
        }
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public List<ASTNode> getChilds() {
    return exprs;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }

  public String getName() {
    return name;
  }

  public int getNameStart() {
    return nameStart;
  }

  public int getNameEnd() {
    return nameEnd;
  }
}
