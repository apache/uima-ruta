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

import java.util.Map;

import org.antlr.runtime.Token;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;

public class RutaMacroDeclaration extends RutaVariableDeclaration {

  private final Map<Token, Token> definition;
  
  public RutaMacroDeclaration(String name, int nameStart, int nameEnd, int declStart, int declEnd,
          SimpleReference ref, int type, Map<Token, Token> definition, Expression initExpression) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref, type, initExpression);
    this.definition = definition;
  }

  public Map<Token, Token> getDefinition() {
    return definition;
  }

  
}
