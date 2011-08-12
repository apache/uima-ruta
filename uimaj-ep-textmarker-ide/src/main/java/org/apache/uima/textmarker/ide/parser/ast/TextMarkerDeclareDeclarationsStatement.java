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

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class TextMarkerDeclareDeclarationsStatement extends TextMarkerDeclarationsStatement {
  private ASTNode parent;

  // public TextMarkerDeclarationsStatement(int declStart, int declEnd,
  // List<TextMarkerAbstractDeclaration> declarations, Expression init) {
  // this(declStart, declEnd, declarations, init, 0, 0);
  // }

  public TextMarkerDeclareDeclarationsStatement(int stmtStart, int stmtEnd,
          List<TextMarkerAbstractDeclaration> declarations, ASTNode parent, int typeTokenStart,
          int typeTokenEnd) {
    super(stmtStart, stmtEnd, declarations, null, typeTokenStart, typeTokenEnd);
    this.parent = parent;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (parent != null) {
        parent.traverse(visitor);
      }
      if (getDeclarations() != null) {
        for (TextMarkerAbstractDeclaration decl : getDeclarations()) {
          decl.traverse(visitor);
        }
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public int getKind() {
    return TMStatementConstants.S_DECLARATIONS;
  }

  public ASTNode getParent() {
    return parent;
  }

}
