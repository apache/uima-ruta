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

package org.apache.uima.ruta.ide.core.codeassist;

import org.apache.uima.ruta.ide.parser.ast.RutaRule;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;

public class RutaRuleIdVisitor extends ASTVisitor {

  private int id;

  private RutaRule result;

  public RutaRuleIdVisitor(int id) {
    super();
    this.id = id;
  }

  @Override
  public boolean visit(Statement s) throws Exception {
    if (result != null) {
      return false;
    }
    if (s instanceof RutaRule) {
      RutaRule rule = (RutaRule) s;
      if (id == rule.getId()) {
        result = rule;
      }
    }
    return true;
  }

  public RutaRule getResult() {
    return result;
  }

}
