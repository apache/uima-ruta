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
import java.util.List;

import org.antlr.runtime.Token;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

public class ScriptFactory extends AbstractFactory {

  private int idCounter;

  public TextMarkerRule createRule(TextMarkerRuleElement element) {
    List<Expression> elements = new ArrayList<Expression>();
    elements.add(element);
    return createRule(elements, null);
  }

  public TextMarkerRule createRule(List<Expression> elements, Token s) {
    TextMarkerRule rule = new TextMarkerRule(elements, idCounter++);
    if (s != null) {
      int[] bounds = getBounds(s);
      rule.setEnd(bounds[1]);
    }
    return rule;
  }

  public ComposedRuleElement createComposedRuleElement(List<Expression> res, List<Expression> q,
          List<TextMarkerCondition> c, List<TextMarkerAction> a, boolean disjunctive,
          TextMarkerBlock env, Token... tokens) {
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

  public TextMarkerRuleElement createRuleElement(Expression head,
          List<Expression> quantifierPartExpressions, List<TextMarkerCondition> conditions,
          List<TextMarkerAction> actions, Token end) {
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
    return new TextMarkerRuleElement(bounds[0], bounds[1], head, quantifierPartExpressions,
            conditions, actions);
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
   * @return
   */
  public TextMarkerScriptBlock createScriptBlock(int declStart, int declEnd, int nameStart,
          int nameEnd, String string, List<TextMarkerRuleElement> res, Block block,
          String packageString) {
    createRule(new ArrayList<Expression>(), null);
    return new TextMarkerScriptBlock(string, packageString, nameStart, nameEnd, declStart, declEnd);
  }

  /**
   * Creates Method-Blocks.<br>
   * Please call finalizeScriptBlock afterwards.
   * 
   * @param id
   * @param type
   * @param textMarkerBlock
   * @return
   */
  public TextMarkerBlock createScriptBlock(Token id, Token type, TextMarkerBlock textMarkerBlock) {
    int[] bounds = getBounds(type, id);
    int[] nameBounds = getBounds(id);
    if (textMarkerBlock == null) {
      TextMarkerBlock block = new TextMarkerBlock(id.getText(), "error", nameBounds[0],
              nameBounds[1], bounds[0], bounds[1]);
      return block;
    } else {
      TextMarkerBlock block = new TextMarkerBlock(id.getText(), textMarkerBlock.getNamespace(),
              nameBounds[0], nameBounds[1], bounds[0], bounds[1]);
      return block;
    }
  }

  public void finalizeScriptBlock(TextMarkerBlock block, Token rc, TextMarkerRule rule,
          List<Statement> body) {
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
