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

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.SimpleReference;

public class RutaFeatureDeclaration extends RutaAbstractDeclaration {

  private String type;

  private ASTNode declType;
  
  public RutaFeatureDeclaration(String name, String type, int nameStart, int nameEnd,
          int declStart, int declEnd, SimpleReference ref, Object declType) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref);
    this.type = type;
    if(declType instanceof ASTNode) {
      this.declType = (ASTNode) declType;
    }
  }

  
  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if(declType != null) {
        declType.traverse(visitor);
      }
      getRef().traverse(visitor);
      visitor.endvisit(this);
    }
  }

  
  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
