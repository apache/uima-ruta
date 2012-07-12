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

import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.statements.Statement;

public class TextMarkerBlock extends MethodDeclaration {

  private TextMarkerRule rule;

  private String namespace;

  public TextMarkerBlock(String name, String namespace, int nameStart, int nameEnd, int declStart,
          int declEnd) {
    super(name, nameStart, nameEnd, declStart, declEnd);
    this.namespace = namespace == null || namespace.equals("") ? name : namespace + "." + name;
  }

  public void setElements(List<Statement> body) {
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
      traverseChildNodes(visitor);
      visitor.endvisit(this);
    }
  }

  /**
   * @param ruleElement
   *          the ruleElement to set
   */
  public void setRule(TextMarkerRule rule) {
    this.rule = rule;
  }

  /**
   * @return the ruleElement
   */
  public TextMarkerRule getRule() {
    return rule;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }
}
