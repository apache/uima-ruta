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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.antlr.runtime.Token;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

public class ScriptFactory extends AbstractFactory {

  private int idCounter;

  public void resetRuleCounter() {
    idCounter = 0;
  }

  public RutaRule createRule(RutaRuleElement element) {
    List<Expression> elements = new ArrayList<Expression>();
    elements.add(element);
    return createRule(elements, null);
  }

  public RutaRule createRule(List<Expression> elements, Token s, boolean updateCounter) {
    RutaRule rule = new RutaRule(elements, idCounter);
    if (updateCounter) {
      idCounter++;
    }
    if (s != null) {
      int[] bounds = getBounds(s);
      rule.setEnd(bounds[1]);
    }
    return rule;
  }

  public RutaRule createRule(List<Expression> elements, Token s) {
    return createRule(elements, s, true);
  }

  public RutaRule createRegExpRule(List<Expression> exprs,
          Map<Expression, Map<Expression, Expression>> fa, Token s) {
    List<Expression> expressions = new ArrayList<Expression>();
    for (Expression each : exprs) {
      if (each != null) {
        expressions.add(each);
        Collection<Map<Expression, Expression>> values = fa.values();
        for (Map<Expression, Expression> map : values) {
          Set<Entry<Expression, Expression>> entrySet = map.entrySet();
          for (Entry<Expression, Expression> entry : entrySet) {
            expressions.add(entry.getKey());
            expressions.add(entry.getValue());
          }
        }
      }
    }
    RutaRegExpRule rule = new RutaRegExpRule(expressions, fa, idCounter++);
    if (s != null) {
      int[] bounds = getBounds(s);
      rule.setEnd(bounds[1]);
    }
    return rule;
  }

  public ComposedRuleElement createComposedRuleElement(List<Expression> res, List<Expression> q,
          List<RutaCondition> c, List<RutaAction> a, Boolean disjunctive, RutaBlock env,
          Token... tokens) {
    int bounds[] = getSurroundingBounds((ASTNode) null, res);
    // taking care of null statements - errors should have been recognized
    // in parser
    filterNullObjects(res);
    filterNullObjects(q);
    filterNullObjects(c);
    filterNullObjects(a);
    ASTNode quantifierPart = null;
    if (q != null && !q.isEmpty()) {
      quantifierPart = q.get(q.size() - 1);
    }
    if (quantifierPart != null) {
      bounds[1] = Math.max(bounds[1], quantifierPart.sourceEnd());
    }
    if (c != null && !c.isEmpty()) {
      bounds[1] = Math.max(bounds[1], c.get(c.size() - 1).sourceEnd());
    }
    if (a != null && !a.isEmpty()) {
      bounds[1] = Math.max(bounds[1], a.get(a.size() - 1).sourceEnd());
    }

    if (tokens != null && tokens.length > 0) {
      bounds[0] = Math.min(bounds[0], getBounds(tokens[0])[0]);
    }
    if (tokens != null && tokens.length == 2 && tokens[1] != null) {
      bounds[1] = Math.max(bounds[1], getBounds(tokens[1])[1]);
    }
    if (tokens != null && tokens.length == 3 && tokens[2] != null) {
      bounds[1] = Math.max(bounds[1], getBounds(tokens[2])[1]);
    }

    return new ComposedRuleElement(bounds[0], bounds[1], res, q, c, a, disjunctive);
  }

  public RutaRuleElement createRuleElement(Expression head,
          List<Expression> quantifierPartExpressions, List<RutaCondition> conditions,
          List<RutaAction> actions, Token end) {
    int bounds[] = getSurroundingBounds(head, conditions, actions);
    setMaxEnd(bounds, end);
    // taking care of null statements - errors should have been recognized
    // in parser
    filterNullObjects(quantifierPartExpressions);
    filterNullObjects(conditions);
    filterNullObjects(actions);
    ASTNode quantifierPart = null;
    if (quantifierPartExpressions != null && !quantifierPartExpressions.isEmpty()) {
      quantifierPart = quantifierPartExpressions.get(quantifierPartExpressions.size() - 1);
    }
    if (quantifierPart != null) {
      bounds[1] = Math.max(bounds[1], quantifierPart.sourceEnd());
    }
    return new RutaRuleElement(bounds[0], bounds[1], head, quantifierPartExpressions, conditions,
            actions);
  }

  public RutaRuleElement createRuleElement(Token w, List<RutaCondition> c, List<RutaAction> a,
          Token end) {
    int bounds[] = getSurroundingBounds(null, c, a);
    setMinBegin(bounds, w);
    filterNullObjects(c);
    filterNullObjects(a);
    RutaRuleElement rutaRuleElement = new RutaRuleElement(bounds[0], bounds[1], null, null, c, a);
    if (w != null && w.getText().equals("#")) {
      rutaRuleElement.setWildcard(true);
    }
    return rutaRuleElement;
  }

  /**
   * Creates Root-Block.
   * 
   * @param declStart
   * @param declEnd
   * @param nameStart
   * @param nameEnd
   * @param string
   * @param res
   * @param block
   * @param packageString
   * @return new RutaScriptBlock
   */
  public RutaScriptBlock createScriptBlock(int declStart, int declEnd, int nameStart, int nameEnd,
          String string, List<RutaRuleElement> res, Block block, String packageString) {
    createRule(new ArrayList<Expression>(), null);
    return new RutaScriptBlock(string, packageString, nameStart, nameEnd, declStart, declEnd);
  }

  /**
   * Creates Method-Blocks.<br>
   * Please call finalizeScriptBlock afterwards.
   * 
   * @param id
   * @param type
   * @param rutaBlock
   * @return RutaBlock
   */
  public RutaBlock createScriptBlock(Token id, Token type, RutaBlock rutaBlock) {
    int[] bounds = getBounds(type, id);
    int[] nameBounds = getBounds(id);
    if (rutaBlock == null) {
      RutaBlock block = new RutaBlock(id.getText(), "error", nameBounds[0], nameBounds[1],
              bounds[0], bounds[1]);
      return block;
    } else {
      RutaBlock block = new RutaBlock(id.getText(), rutaBlock.getNamespace(), nameBounds[0],
              nameBounds[1], bounds[0], bounds[1]);
      return block;
    }
  }

  /**
   * Creates an AST element for an external block construct
   * @param type
   * @param parent block
   * @return new external block construct
   */
  public RutaBlock createExternalBlock(Token type, RutaBlock parent) {
    int[] bounds = getBounds(type);
    int[] nameBounds = getBounds(type);
    RutaBlock block = new RutaBlock(type.getText(), type.getText(), nameBounds[0], nameBounds[1],
            bounds[0], bounds[1]);
    return block;
  }

  public void finalizeBlock(RutaBlock block, Token rc, RutaRule rule, List<Statement> body) {
    // taking care of null statements - errors should have been recognized
    // in parser
    filterNullObjects(body);
    int innerStart = 0;
    int innerEnd = 0;
    if (body != null && !body.isEmpty()) {
      innerStart = body.get(0).sourceStart();
      innerEnd = body.get(body.size() - 1).sourceEnd();
    }
    Block inner = new Block(innerStart, innerEnd, body);
    block.acceptBody(inner, false);
    block.setRule(rule);
    block.setEnd(rc != null ? getBounds(rc)[1] : rule.sourceEnd());
  }

  /**
   * @param body
   */
  private void filterNullObjects(List<?> body) {
    if (body == null) {
      return;
    }
    for (int i = 0; i < body.size(); i++) {
      Object obj = body.get(i);
      if (obj == null) {
        body.remove(i);
        i--;
      }
    }
  }

}
