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

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

/**
 * Container for declarations,<br>
 * i.e.:<br>
 * "INT a, b, c = 2;"<br>
 * is a RutaDeclarationsStatement with 3 RutaVariableDeclarations.
 * 
 * 
 */
public class RutaDeclarationsStatement extends Statement {
  private List<RutaAbstractDeclaration> declarations;

  private int typeTokenStart;

  private int typeTokenEnd;

  private Expression initExpr;

  // public RutaDeclarationsStatement(int declStart, int declEnd,
  // List<RutaAbstractDeclaration> declarations, Expression init) {
  // this(declStart, declEnd, declarations, init, 0, 0);
  // }

  public RutaDeclarationsStatement(int stmtStart, int stmtEnd,
          List<RutaAbstractDeclaration> declarations, Expression init, int typeTokenStart,
          int typeTokenEnd) {
    super(stmtStart, stmtEnd);
    this.declarations = declarations == null ? new ArrayList<RutaAbstractDeclaration>()
            : declarations;
    this.initExpr = init;
    this.typeTokenStart = typeTokenStart;
    this.typeTokenEnd = typeTokenEnd;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (declarations != null) {
        for (RutaAbstractDeclaration decl : declarations) {
          decl.traverse(visitor);
        }
      }
      if (initExpr != null) {
        initExpr.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public int getKind() {
    return RutaStatementConstants.S_DECLARATIONS;
  }

  public void addDeclaration(RutaAbstractDeclaration decl) {
    if (decl != null) {
      this.declarations.add(decl);
    }
  }

  public int getDeclarationsCount() {
    return this.declarations == null ? 0 : this.declarations.size();
  }

  public List<RutaAbstractDeclaration> getDeclarations() {
    return declarations;
  }

  public Expression getInitExpr() {
    return initExpr;
  }

  public int getTypeTokenStart() {
    return typeTokenStart;
  }

  public int getTypeTokenEnd() {
    return typeTokenEnd;
  }

}
