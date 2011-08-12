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

  // commented on 090508 because it wasn't called anywhere
  // public static TextMarkerScriptBlock createScriptBlock(Token id,
  // Token declare, List<TextMarkerRuleElement> res, Block block,
  // TextMarkerBlock parent) {
  // int bounds[] = getBounds(declare, id);
  // int nameBounds[] = getBounds(id);
  // String namespace = "";
  // if (parent != null) {
  // namespace = parent.getNamespace();
  // }
  // return new TextMarkerScriptBlock(id.getText(), namespace,
  // nameBounds[0], nameBounds[1], bounds[0], bounds[1]);
  // }

  public static TextMarkerRule createRule(List<Expression> elements) {
    return new TextMarkerRule(elements);
  }

  // public static TextMarkerRuleElement createRuleElement(int start, int end,
  // String text, List<TextMarkerCondition> cp,
  // List<TextMarkerAction> ap, String text2, TextMarkerBlock parent) {
  // return new TextMarkerRuleElement(start, end);
  // }

  public static TextMarkerRuleElement createRuleElement(Expression head, Expression quantifier,
          TextMarkerCondition c, TextMarkerAction a) {
    // TODO
    List<Expression> conditions = new ArrayList<Expression>();
    List<Expression> actions = new ArrayList<Expression>();
    List<Expression> quantifiers = new ArrayList<Expression>();
    conditions.add(c);
    actions.add(a);
    quantifiers.add(quantifier);
    filterNullObjects(quantifiers);
    filterNullObjects(conditions);
    filterNullObjects(actions);
    ASTNode quantifierPart = null;
    if (quantifiers != null && !quantifiers.isEmpty()) {
      quantifierPart = quantifiers.get(quantifiers.size());
    }
    int bounds[] = getSurroundingBounds(head, conditions, actions);
    if (quantifierPart != null) {
      bounds[1] = Math.max(bounds[1], quantifierPart.sourceEnd());
    }
    return new TextMarkerRuleElement(bounds[0], bounds[1], head, quantifiers, conditions, actions);
  }

  public static ComposedRuleElement createComposedRuleElement(List<Expression> res,
          List<Expression> q, TextMarkerBlock env) {
    int bounds[] = getSurroundingBounds((ASTNode) null, res);
    // taking care of null statements - errors should have been recognized
    // in parser
    filterNullObjects(res);
    filterNullObjects(q);
    ASTNode quantifierPart = null;
    if (q != null && !q.isEmpty()) {
      quantifierPart = q.get(q.size() - 1);
    }
    if (quantifierPart != null) {
      bounds[1] = Math.max(bounds[1], quantifierPart.sourceEnd());
    }
    return new ComposedRuleElement(bounds[0], bounds[1], res, q);
  }

  public static TextMarkerRuleElement createRuleElement(Expression head,
          List<Expression> quantifierPartExpressions, List conditions, List actions, Token end) {
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

  // commented on 090508 because it wasn't called anywhere
  // public static TextMarkerScriptBlock createScriptBlock(Token id,
  // Token declare, List<TextMarkerRuleElement> res, Block block,
  // TextMarkerBlock parent) {
  // int bounds[] = getBounds(declare, id);
  // int nameBounds[] = getBounds(id);
  // String namespace = "";
  // if (parent != null) {
  // namespace = parent.getNamespace();
  // }
  // return new TextMarkerScriptBlock(id.getText(), namespace,
  // nameBounds[0], nameBounds[1], bounds[0], bounds[1]);
  // }

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
  public static TextMarkerScriptBlock createScriptBlock(int declStart, int declEnd, int nameStart,
          int nameEnd, String string, List<TextMarkerRuleElement> res, Block block,
          String packageString) {
    // UNUSED parameter res unused
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
  public static TextMarkerBlock createScriptBlock(Token id, Token type,
          TextMarkerBlock textMarkerBlock) {
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

  public static void finalizeScriptBlock(TextMarkerBlock block, Token rc, TextMarkerRuleElement re,
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
    block.setRuleElement(re);
    block.setEnd(rc != null ? getBounds(rc)[1] : re.sourceEnd());
  }

  /**
   * @param body
   */
  private static void filterNullObjects(List body) {
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
