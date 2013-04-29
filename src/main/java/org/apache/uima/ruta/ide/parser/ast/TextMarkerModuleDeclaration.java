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

import org.apache.uima.textmarker.ide.core.builder.DescriptorManager;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;


public class TextMarkerModuleDeclaration extends ModuleDeclaration {

  public DescriptorManager descriptorInfo;

  public TextMarkerModuleDeclaration(int sourceLength) {
    super(sourceLength);
  }

  public TextMarkerModuleDeclaration(int length, boolean rebuild) {
    super(length, rebuild);
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }
}
