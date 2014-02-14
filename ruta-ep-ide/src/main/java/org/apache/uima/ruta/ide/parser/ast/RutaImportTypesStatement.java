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

import org.antlr.runtime.Token;

/**
 * AST Node for more compley statement that import type system, their types, or packages and
 * additionally consider an alias for them
 * 
 */
public class RutaImportTypesStatement extends RutaImportStatement {

  private Token typeToken;

  private Token pkgToken;

  private Token aliasToken;

  public RutaImportTypesStatement(int sourceStart, int sourceEnd, ComponentDeclaration ts,
          Token typeToken, Token pkgToken, Token aliasToken) {
    super(sourceStart, sourceEnd, ts, RutaStatementConstants.S_IMPORT_TYPESYSTEM);
    this.typeToken = typeToken;
    this.pkgToken = pkgToken;
    this.aliasToken = aliasToken;
  }

  public Token getTypeToken() {
    return typeToken;
  }

  public void setTypeToken(Token typeToken) {
    this.typeToken = typeToken;
  }

  public Token getPkgToken() {
    return pkgToken;
  }

  public void setPkgToken(Token pkgToken) {
    this.pkgToken = pkgToken;
  }

  public Token getAliasToken() {
    return aliasToken;
  }

  public void setAliasToken(Token aliasToken) {
    this.aliasToken = aliasToken;
  }

}
