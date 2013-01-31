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

package org.apache.uima.textmarker;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.SimpleTypeExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.ComposedRuleElement;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleElementContainer;
import org.apache.uima.textmarker.rule.RuleElementIsolator;
import org.apache.uima.textmarker.rule.TextMarkerDisjunctiveMatcher;
import org.apache.uima.textmarker.rule.TextMarkerLiteralMatcher;
import org.apache.uima.textmarker.rule.TextMarkerRule;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.rule.TextMarkerTypeMatcher;
import org.apache.uima.textmarker.rule.quantifier.MinMaxGreedy;
import org.apache.uima.textmarker.rule.quantifier.MinMaxReluctant;
import org.apache.uima.textmarker.rule.quantifier.PlusGreedy;
import org.apache.uima.textmarker.rule.quantifier.PlusReluctant;
import org.apache.uima.textmarker.rule.quantifier.QuestionGreedy;
import org.apache.uima.textmarker.rule.quantifier.QuestionReluctant;
import org.apache.uima.textmarker.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.textmarker.rule.quantifier.StarGreedy;
import org.apache.uima.textmarker.rule.quantifier.StarReluctant;

public class TextMarkerScriptFactory {

  private int idCounter = 0;

  public TextMarkerScriptFactory() {
    super();
  }

  public TextMarkerScriptBlock createScriptBlock(Token id, TextMarkerRuleElement ruleElement,
          List<TextMarkerStatement> body, TextMarkerBlock parent) {
    String text = id == null ? "root" : id.getText();
    String defaultNamespace = parent.getNamespace();
    if (id != null) {
      defaultNamespace = defaultNamespace + "." + text;
    }
    return createScriptBlock(text, ruleElement, body, parent, defaultNamespace);
  }

  public TextMarkerScriptBlock createScriptBlock(String text, TextMarkerRuleElement ruleElement,
          List<TextMarkerStatement> body, TextMarkerBlock parent, String defaultNamespace) {
    TextMarkerRule rule = null;
    if (ruleElement != null) {
      rule = createRule(ruleElement, parent);
    }
    List<TextMarkerStatement> elements = new ArrayList<TextMarkerStatement>();
    if (body != null) {
      for (TextMarkerStatement each : body) {
        if (each != null) {
          elements.add(each);
        }
      }
    }
    return new TextMarkerScriptBlock(text, rule, elements, parent, defaultNamespace);
  }

  public TextMarkerScriptBlock createRootScriptBlock(String module, String pack,
          TypeSystemDescription localTSD) {
    String defaultNamespace = pack + "." + module;
    TextMarkerScriptBlock result = createScriptBlock(module, null, null, null, defaultNamespace);
    List<RuleElement> ruleElements = new ArrayList<RuleElement>();
    RuleElementIsolator container = new RuleElementIsolator();
    ruleElements.add(createRuleElement(new SimpleTypeExpression("uima.tcas.DocumentAnnotation"),
            null, null, null, container, result));
    TextMarkerRule createRule = createRule(ruleElements, result);
    container.setContainer(createRule);

    result.setRule(createRule);
    return result;
  }

  public TextMarkerRule createRule(RuleElement element, TextMarkerBlock parent) {
    List<RuleElement> elements = new ArrayList<RuleElement>();
    elements.add(element);
    return createRule(elements, parent);
  }

  public TextMarkerRule createRule(List<RuleElement> elements, TextMarkerBlock parent) {
    return new TextMarkerRule(elements, parent, idCounter++);
  }

  public TextMarkerRuleElement createRuleElement(TypeExpression typeExpression,
          RuleElementQuantifier quantifier, List<AbstractTextMarkerCondition> conditions,
          List<AbstractTextMarkerAction> actions, RuleElementContainer container,
          TextMarkerBlock parent) {
    TextMarkerTypeMatcher matcher = new TextMarkerTypeMatcher(typeExpression);
    return new TextMarkerRuleElement(matcher, quantifier, conditions, actions, container, parent);
  }

  public TextMarkerRuleElement createRuleElement(List<TextMarkerExpression> exprs,
          RuleElementQuantifier quantifier, List<AbstractTextMarkerCondition> conditions,
          List<AbstractTextMarkerAction> actions, RuleElementContainer container,
          TextMarkerBlock parent) {
    TextMarkerDisjunctiveMatcher matcher = new TextMarkerDisjunctiveMatcher(exprs);
    return new TextMarkerRuleElement(matcher, quantifier, conditions, actions, container, parent);
  }

  public TextMarkerRuleElement createRuleElement(StringExpression stringExpression,
          RuleElementQuantifier quantifier, List<AbstractTextMarkerCondition> conditions,
          List<AbstractTextMarkerAction> actions, RuleElementContainer container,
          TextMarkerBlock parent) {

    TextMarkerLiteralMatcher matcher = new TextMarkerLiteralMatcher(stringExpression);
    return new TextMarkerRuleElement(matcher, quantifier, conditions, actions, container, parent);
  }

  public ComposedRuleElement createComposedRuleElement(List<RuleElement> res,
          RuleElementQuantifier quantifier, List<AbstractTextMarkerCondition> conditions,
          List<AbstractTextMarkerAction> actions, RuleElementContainer container,
          TextMarkerBlock parent) {

    return new ComposedRuleElement(res, quantifier, conditions, actions, container, parent);
  }

  public ComposedRuleElement createComposedRuleElement(RuleElementContainer container,
          TextMarkerBlock parent) {
    return new ComposedRuleElement(null, null, null, null, container, parent);
  }

  public static RuleElementQuantifier createStarGreedyQuantifier() {
    return new StarGreedy();
  }

  public static RuleElementQuantifier createPlusReluctantQuantifier() {
    return new PlusReluctant();
  }

  public static RuleElementQuantifier createStarReluctantQuantifier() {
    return new StarReluctant();
  }

  public static RuleElementQuantifier createMinMaxGreedyQuantifier(NumberExpression min,
          NumberExpression max, Token comma) {
    return new MinMaxGreedy(min, max, comma != null);
  }

  public static RuleElementQuantifier createMinMaxReluctantQuantifier(NumberExpression min,
          NumberExpression max, Token comma) {
    return new MinMaxReluctant(min, max, comma != null);
  }

  public static RuleElementQuantifier createPlusGreedyQuantifier() {
    return new PlusGreedy();
  }

  public static RuleElementQuantifier createQuestionReluctantQuantifier() {
    return new QuestionReluctant();
  }

  public static RuleElementQuantifier createQuestionGreedyQuantifier() {
    return new QuestionGreedy();
  }

  public TextMarkerBlock createAutomataBlock(Token id, TextMarkerRuleElement re,
          List<TextMarkerStatement> body, TextMarkerBlock env) {
    return createScriptBlock(id, re, body, env);
  }

}
