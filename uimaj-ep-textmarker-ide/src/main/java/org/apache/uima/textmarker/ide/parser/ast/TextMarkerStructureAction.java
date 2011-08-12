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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class TextMarkerStructureAction extends TextMarkerAction {
  private Expression structure;

  private Map<Expression, Expression> assignments;

  public TextMarkerStructureAction(int start, int end, List<Expression> indexExprs, int kind,
          String name, int nameStart, int nameEnd, Map<Expression, Expression> assignments,
          Expression structure) {
    super(start, end, indexExprs, kind, name, nameStart, nameEnd);
    this.structure = structure;
    this.assignments = assignments;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      structure.traverse(visitor);
      for (Expression e : super.exprs) {
        e.traverse(visitor);
      }
      Iterator it = assignments.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry) it.next();
        if (pairs.getKey() == null || pairs.getValue() == null) {
          break;
        }
        ((Expression) pairs.getKey()).traverse(visitor);
        ((Expression) pairs.getValue()).traverse(visitor);
      }
    }
  }

  @Override
  public List getChilds() {
    List l = new ArrayList<Expression>();
    l.add(structure);
    l.addAll(super.getChilds());
    l.addAll(assignments.keySet());
    l.addAll(assignments.values());
    return l;
  }

  public Map<Expression, Expression> getAssignments() {
    return assignments;
  }

  public Expression getStructure() {
    return structure;
  }

  public List<Expression> getIndices() {
    return super.exprs;
  }
}
