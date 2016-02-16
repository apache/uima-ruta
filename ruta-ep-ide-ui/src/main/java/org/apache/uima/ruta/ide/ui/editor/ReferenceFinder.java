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

package org.apache.uima.ruta.ide.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.ide.parser.ast.ActionFactory;
import org.apache.uima.ruta.ide.parser.ast.ComponentDeclaration;
import org.apache.uima.ruta.ide.parser.ast.ComponentReference;
import org.apache.uima.ruta.ide.parser.ast.ConditionFactory;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaCondition;
import org.apache.uima.ruta.ide.parser.ast.RutaVariableReference;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class ReferenceFinder extends ASTVisitor {

  private List<ASTNode> result = new ArrayList<ASTNode>();

  private ASTNode node;

  public ReferenceFinder(ASTNode node) {
    super();
    this.node = getRealNode(node);
  }

  private ASTNode getRealNode(ASTNode node) {
    if (node instanceof RutaAction) {
      RutaAction a = (RutaAction) node;
      if (ActionFactory.IMPLICIT.equals(a.getName()) && !a.getChilds().isEmpty()) {
        ASTNode expression = a.getChilds().get(0);
        if (expression != null && !expression.getChilds().isEmpty()) {
          return (ASTNode) expression.getChilds().get(0);
        }
      }
    }
    if (node instanceof RutaCondition) {
      RutaCondition c = (RutaCondition) node;
      if (ConditionFactory.IMPLICIT.equals(c.getName()) && !c.getChilds().isEmpty()) {
        ASTNode expression = c.getChilds().get(0);
        if (expression != null && !expression.getChilds().isEmpty()) {
          return (ASTNode) expression.getChilds().get(0);
        }
      }
    }
    return node;
  }

  @Override
  public boolean visit(Expression s) throws Exception {
    if (s instanceof RutaVariableReference && node instanceof RutaVariableReference) {
      RutaVariableReference vr0 = (RutaVariableReference) node;
      RutaVariableReference vr1 = (RutaVariableReference) s;
      if (vr1.getStringRepresentation().equals(vr0.getStringRepresentation())
              && vr1.getType() == vr0.getType()) {
        result.add(s);
      }
    } else if (s instanceof ComponentDeclaration && node instanceof ComponentDeclaration) {

    } else if (s instanceof ComponentReference && node instanceof ComponentReference) {
      ComponentReference cr1 = (ComponentReference) node;
      ComponentReference cr2 = (ComponentReference) s;
      if (cr1.getName().equals(cr2.getName())) {
        result.add(s);
      }
    } else if (s instanceof RutaAction && node instanceof RutaAction) {
      RutaAction a1 = (RutaAction) node;
      RutaAction a2 = (RutaAction) s;
      if (a1.getName().equals(a2.getName())) {
        result.add(s);
      }
    } else if (s instanceof RutaCondition && node instanceof RutaCondition) {
      RutaCondition c1 = (RutaCondition) node;
      RutaCondition c2 = (RutaCondition) s;
      if (c1.getName().equals(c2.getName())) {
        result.add(s);
      }

    }
    return super.visit(s);
  }

  public List<ASTNode> getResult() {
    return result;
  }
}
