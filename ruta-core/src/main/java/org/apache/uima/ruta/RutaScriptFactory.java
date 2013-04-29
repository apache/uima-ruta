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

package org.apache.uima.ruta;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.expression.string.StringExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.AbstractRuleElement;
import org.apache.uima.ruta.rule.ComposedRuleElement;
import org.apache.uima.ruta.rule.RegExpRule;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleElementContainer;
import org.apache.uima.ruta.rule.RuleElementIsolator;
import org.apache.uima.ruta.rule.RutaDisjunctiveMatcher;
import org.apache.uima.ruta.rule.RutaLiteralMatcher;
import org.apache.uima.ruta.rule.RutaRule;
import org.apache.uima.ruta.rule.RutaRuleElement;
import org.apache.uima.ruta.rule.RutaTypeMatcher;
import org.apache.uima.ruta.rule.WildCardRuleElement;
import org.apache.uima.ruta.rule.quantifier.MinMaxGreedy;
import org.apache.uima.ruta.rule.quantifier.MinMaxReluctant;
import org.apache.uima.ruta.rule.quantifier.PlusGreedy;
import org.apache.uima.ruta.rule.quantifier.PlusReluctant;
import org.apache.uima.ruta.rule.quantifier.QuestionGreedy;
import org.apache.uima.ruta.rule.quantifier.QuestionReluctant;
import org.apache.uima.ruta.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.ruta.rule.quantifier.StarGreedy;
import org.apache.uima.ruta.rule.quantifier.StarReluctant;

public class RutaScriptFactory {

  private int idCounter = 0;

  public RutaScriptFactory() {
    super();
  }

  public RutaScriptBlock createScriptBlock(Token id, RutaRuleElement ruleElement,
          List<RutaStatement> body, RutaBlock parent) {
    String text = id == null ? "root" : id.getText();
    String defaultNamespace = parent.getNamespace();
    if (id != null) {
      defaultNamespace = defaultNamespace + "." + text;
    }
    return createScriptBlock(text, ruleElement, body, parent, defaultNamespace);
  }

  public RutaScriptBlock createScriptBlock(String text, RutaRuleElement ruleElement,
          List<RutaStatement> body, RutaBlock parent, String defaultNamespace) {
    RutaRule rule = null;
    if (ruleElement != null) {
      rule = createRule(ruleElement, parent);
    }
    List<RutaStatement> elements = new ArrayList<RutaStatement>();
    if (body != null) {
      for (RutaStatement each : body) {
        if (each != null) {
          elements.add(each);
        }
      }
    }
    return new RutaScriptBlock(text, rule, elements, parent, defaultNamespace);
  }

  public RutaScriptBlock createRootScriptBlock(String module, String pack,
          TypeSystemDescription localTSD) {
    String defaultNamespace = pack + "." + module;
    RutaScriptBlock result = createScriptBlock(module, null, null, null, defaultNamespace);
    List<RuleElement> ruleElements = new ArrayList<RuleElement>();
    RuleElementIsolator container = new RuleElementIsolator();
    ruleElements.add(createRuleElement(new SimpleTypeExpression("uima.tcas.DocumentAnnotation"),
            null, null, null, container, result));
    RutaRule createRule = createRule(ruleElements, result);
    container.setContainer(createRule);

    result.setRule(createRule);
    return result;
  }

  public RutaRule createRule(RuleElement element, RutaBlock parent) {
    List<RuleElement> elements = new ArrayList<RuleElement>();
    elements.add(element);
    return createRule(elements, parent);
  }

  public RutaRule createRule(List<RuleElement> elements, RutaBlock parent) {
    return new RutaRule(elements, parent, idCounter++);
  }

  public RutaRuleElement createRuleElement(TypeExpression typeExpression,
          RuleElementQuantifier quantifier, List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container,
          RutaBlock parent) {
    RutaTypeMatcher matcher = new RutaTypeMatcher(typeExpression);
    return new RutaRuleElement(matcher, quantifier, conditions, actions, container, parent);
  }

  public AbstractRuleElement createWildCardRuleElement(List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container,
          RutaBlock parent) {
    return new WildCardRuleElement(conditions, actions, container, parent);
  }
  
  
  public RutaRuleElement createRuleElement(List<RutaExpression> exprs,
          RuleElementQuantifier quantifier, List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container,
          RutaBlock parent) {
    RutaDisjunctiveMatcher matcher = new RutaDisjunctiveMatcher(exprs);
    return new RutaRuleElement(matcher, quantifier, conditions, actions, container, parent);
  }

  public RutaRuleElement createRuleElement(StringExpression stringExpression,
          RuleElementQuantifier quantifier, List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container,
          RutaBlock parent) {
    RutaLiteralMatcher matcher = new RutaLiteralMatcher(stringExpression);
    return new RutaRuleElement(matcher, quantifier, conditions, actions, container, parent);
  }

  public ComposedRuleElement createComposedRuleElement(List<RuleElement> res,
          RuleElementQuantifier quantifier, List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container,
          RutaBlock parent) {

    return new ComposedRuleElement(res, quantifier, conditions, actions, container, parent);
  }

  public ComposedRuleElement createComposedRuleElement(RuleElementContainer container,
          RutaBlock parent) {
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

  public RutaBlock createAutomataBlock(Token id, RutaRuleElement re,
          List<RutaStatement> body, RutaBlock env) {
    return createScriptBlock(id, re, body, env);
  }

  public RegExpRule createRegExpRule(RutaBlock env) {
    return new RegExpRule(null, null, idCounter++, env);
  }



}
