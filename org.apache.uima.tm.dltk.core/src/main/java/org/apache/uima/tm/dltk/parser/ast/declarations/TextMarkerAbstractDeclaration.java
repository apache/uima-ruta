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

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;

/**
 * @author Martin Toepfer
 * 
 */
public abstract class TextMarkerAbstractDeclaration extends FieldDeclaration {
  private SimpleReference ref;

  public TextMarkerAbstractDeclaration(String name, int nameStart, int nameEnd, int declStart,
          int declEnd, SimpleReference ref) {
    super(name, nameStart, nameEnd, nameStart, nameEnd); // declStart, declEnd);
    this.setName(name);
    this.ref = ref;
  }

  @Override
  public SimpleReference getRef() {
    return this.ref;
  }

  @Override
  public int getKind() {
    return D_VAR_DECL;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      this.ref.traverse(visitor);
      visitor.endvisit(this);
    }
  }

  @Override
  public int matchStart() {
    return this.getNameStart();
  }

  @Override
  public int matchLength() {
    return this.getName().length();
  }
}
