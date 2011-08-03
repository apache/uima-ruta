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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.SimpleReference;

public class TextMarkerTypeDeclaration extends TextMarkerAbstractDeclaration {

  private List<TextMarkerFeatureDeclaration> features = new ArrayList<TextMarkerFeatureDeclaration>();

  public TextMarkerTypeDeclaration(String name, int nameStart, int nameEnd, int declStart,
          int declEnd, SimpleReference ref) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref);
  }

  public TextMarkerTypeDeclaration(String name, int nameStart, int nameEnd, int declStart,
          int declEnd, SimpleReference ref, List<TextMarkerFeatureDeclaration> features) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref);
    this.setFeatures(features);
  }

  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      getRef().traverse(visitor);
      for (TextMarkerFeatureDeclaration each : getFeatures()) {
        each.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  public void setFeatures(List<TextMarkerFeatureDeclaration> features) {
    this.features = features;
  }

  public List<TextMarkerFeatureDeclaration> getFeatures() {
    return features;
  }
}
