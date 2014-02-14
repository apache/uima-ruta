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

import org.apache.commons.lang3.StringUtils;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;

public class RutaBlock extends MethodDeclaration {

  private RutaRule rule;

  private String namespace;

  private List<Expression> args;

  public RutaBlock(String name, String namespace, int nameStart, int nameEnd, int declStart,
          int declEnd) {
    super(name, nameStart, nameEnd, declStart, declEnd);
    this.namespace = StringUtils.isEmpty(namespace) ? name : namespace + "." + name;
  }


  public String getNamespace() {
    return namespace;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (rule != null) {
        rule.traverse(visitor);
      }
      if(args != null) {
        for (Expression each : args) {
          each.traverse(visitor);
        }
      }
      traverseChildNodes(visitor);
      visitor.endvisit(this);
    }
  }

  /**
   * @param ruleElement
   *          the ruleElement to set
   */
  public void setRule(RutaRule rule) {
    this.rule = rule;
  }

  /**
   * @return the ruleElement
   */
  public RutaRule getRule() {
    return rule;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }


  public void setArguments(List<Expression> args) {
    this.args = args;
    
  }
}
