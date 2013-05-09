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

import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.expressions.Expression;

public class RutaRegExpRule extends RutaRule {

  private Map<Expression, Map<Expression, Expression>> feats;

  public RutaRegExpRule(List<Expression> exprs, Map<Expression, Map<Expression, Expression>> fa, int i) {
    super(exprs, i);
    this.feats = fa;
  }

  public Map<Expression, Map<Expression, Expression>> getFeats() {
    return feats;
  }

  public void setFeats(Map<Expression, Map<Expression, Expression>> feats) {
    this.feats = feats;
  }

}
