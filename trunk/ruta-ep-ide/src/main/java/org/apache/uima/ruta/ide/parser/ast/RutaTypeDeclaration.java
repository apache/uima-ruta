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
import org.eclipse.dltk.ast.references.SimpleReference;

public class RutaTypeDeclaration extends RutaAbstractDeclaration {

  private List<RutaFeatureDeclaration> features = new ArrayList<RutaFeatureDeclaration>();

  public RutaTypeDeclaration(String name, int nameStart, int nameEnd, int declStart, int declEnd,
          SimpleReference ref) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref);
  }

  public RutaTypeDeclaration(String name, int nameStart, int nameEnd, int declStart, int declEnd,
          SimpleReference ref, List<RutaFeatureDeclaration> features) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref);
    this.setFeatures(features);
  }

  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      getRef().traverse(visitor);
      for (RutaFeatureDeclaration each : getFeatures()) {
        each.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public int getKind() {
    return RutaTypeConstants.RUTA_TYPE_AT;
  }

  public void setFeatures(List<RutaFeatureDeclaration> features) {
    this.features = features;
  }

  public List<RutaFeatureDeclaration> getFeatures() {
    return features;
  }
}
