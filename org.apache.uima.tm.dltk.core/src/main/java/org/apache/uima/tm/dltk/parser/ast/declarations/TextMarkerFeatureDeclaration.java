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

import org.eclipse.dltk.ast.references.SimpleReference;

public class TextMarkerFeatureDeclaration extends TextMarkerAbstractDeclaration {

  private String type;

  public TextMarkerFeatureDeclaration(String name, String type, int nameStart, int nameEnd,
          int declStart, int declEnd, SimpleReference ref) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref);
    this.setType(type);
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
