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
import java.util.Map;
import java.util.TreeMap;

import org.antlr.runtime.Token;
import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.block.ForEachBlock;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.block.RutaScriptBlock;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.expression.AnnotationTypeExpression;
import org.apache.uima.ruta.expression.ExpressionFactory;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.AbstractRuleElement;
import org.apache.uima.ruta.rule.ComposedRuleElement;
import org.apache.uima.ruta.rule.ConjunctRulesRuleElement;
import org.apache.uima.ruta.rule.RegExpRule;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleElementContainer;
import org.apache.uima.ruta.rule.RuleElementIsolator;
import org.apache.uima.ruta.rule.RutaAnnotationTypeMatcher;
import org.apache.uima.ruta.rule.RutaLiteralMatcher;
import org.apache.uima.ruta.rule.RutaMatcher;
import org.apache.uima.ruta.rule.RutaOptionalRuleElement;
import org.apache.uima.ruta.rule.RutaRule;
import org.apache.uima.ruta.rule.RutaRuleElement;
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

  private UimaContext context;

  private ExpressionFactory expressionFactory;

  @SuppressWarnings("unused")
  private TypeUsageInformation typeUsage;

  public RutaScriptFactory(ExpressionFactory expressionFactory, TypeUsageInformation typeUsage) {
    super();
    if (expressionFactory == null) {
      this.expressionFactory = new ExpressionFactory();
    } else {
      this.expressionFactory = expressionFactory;
    }
    this.typeUsage = typeUsage;
  }

  public RutaScriptFactory(ExpressionFactory expressionFactory) {
    this(expressionFactory, null);
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
    List<RutaStatement> elements = new ArrayList<>();
    if (body != null) {
      for (RutaStatement each : body) {
        if (each != null) {
          elements.add(each);
        }
      }
    }
    return new RutaScriptBlock(text, rule, elements, parent, defaultNamespace);
  }

  public RutaScriptBlock createRootScriptBlock(String module, String pack) {
    String defaultNamespace = getDefaultNamespace(module, pack);

    RutaScriptBlock result = createScriptBlock(module, null, null, null, defaultNamespace);
    List<RuleElement> ruleElements = new ArrayList<>();
    RuleElementIsolator container = new RuleElementIsolator();
    ITypeExpression documentExpression = expressionFactory
            .createSimpleTypeExpression(CAS.TYPE_NAME_DOCUMENT_ANNOTATION, null);
    MatchReference mr = expressionFactory.createMatchReference(documentExpression);
    ruleElements.add(createRuleElement(mr, container, result));
    RutaRule createRule = createRule(ruleElements, result);
    container.setContainer(createRule);

    result.setRule(createRule);
    result.setContext(context);
    return result;
  }

  private String getDefaultNamespace(String module, String pack) {
    String defaultNamespace = module;
    if (StringUtils.isBlank(module) && StringUtils.isBlank(pack)) {
      defaultNamespace = "";
    } else if (!StringUtils.isBlank(module) && !StringUtils.isBlank(pack)) {
      defaultNamespace = pack + "." + module;
    } else if (!StringUtils.isBlank(module)) {
      defaultNamespace = module;
    } else if (!StringUtils.isBlank(pack)) {
      defaultNamespace = pack;
    }
    return defaultNamespace;
  }

  public RutaBlock createForEachBlock(Token varToken, IBooleanExpression direction,
          RutaRuleElement ruleElement, List<RutaStatement> body, RutaBlock parent) {

    if (varToken == null) {
      throw new IllegalArgumentException(
              "A FOREACH block requires that the annotation variable is named.");
    }

    String varName = varToken.getText();
    RutaRule rule = null;
    if (ruleElement != null) {
      rule = createRule(ruleElement, parent);
    }
    String namespace = parent.getNamespace() + "." + varName;
    return new ForEachBlock(varName, direction, rule, body, parent, namespace);
  }

  public RutaRule createRule(RuleElement element, RutaBlock parent) {
    List<RuleElement> elements = new ArrayList<>();
    elements.add(element);
    return createRule(elements, parent);
  }

  public RutaStatement createImplicitRule(List<AbstractRutaAction> actions, RutaBlock parent) {
    List<RuleElement> elements = new ArrayList<>();
    ITypeExpression documentExpression = expressionFactory.createSimpleTypeExpression("Document",
            parent);
    MatchReference mr = expressionFactory.createMatchReference(documentExpression);
    RutaRuleElement element = createRuleElement(mr, null, null, actions, null, parent);
    elements.add(element);
    RutaRule rule = createRule(elements, parent);
    element.setContainer(rule.getRoot());
    return rule;
  }

  public RutaRule createRule(List<RuleElement> elements, RutaBlock parent) {
    return new RutaRule(elements, parent, idCounter++);
  }

  public RutaRuleElement createRuleElement(MatchReference matchReference,
          RuleElementContainer container, RutaBlock parent) {
    return createRuleElement(matchReference, null, null, null, container, parent);
  }

  public RutaRuleElement createRuleElement(MatchReference matchReference,
          RuleElementQuantifier quantifier, List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container, RutaBlock parent) {
    AnnotationTypeExpression expression = expressionFactory
            .createAnnotationTypeExpression(matchReference);
    return createRuleElement(expression, quantifier, conditions, actions, container, parent);
  }

  public RutaRuleElement createRuleElement(IRutaExpression expression,
          RuleElementQuantifier quantifier, List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container, RutaBlock parent) {
    RutaMatcher matcher = null;
    if (expression instanceof AnnotationTypeExpression) {
      matcher = new RutaAnnotationTypeMatcher((AnnotationTypeExpression) expression);
    } else if (expression instanceof IStringExpression) {
      matcher = new RutaLiteralMatcher((IStringExpression) expression);
    } else {
      throw new RuntimeException(expression.getClass().getSimpleName()
              + " is not a valid expression for a matching condition.");
    }
    return new RutaRuleElement(matcher, quantifier, conditions, actions, container, parent);
  }

  public AbstractRuleElement createWildCardRuleElement(List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container, RutaBlock parent) {
    return new WildCardRuleElement(conditions, actions, container, parent);
  }

  public AbstractRuleElement createOptionalRuleElement(List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container, RutaBlock parent) {

    return new RutaOptionalRuleElement(conditions, actions, container, parent);
  }

  public ComposedRuleElement createComposedRuleElement(List<RuleElement> res,
          RuleElementQuantifier quantifier, List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container, RutaBlock parent) {
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

  public static RuleElementQuantifier createMinMaxGreedyQuantifier(INumberExpression min,
          INumberExpression max, Token comma) {
    return new MinMaxGreedy(min, max, comma != null);
  }

  public static RuleElementQuantifier createMinMaxReluctantQuantifier(INumberExpression min,
          INumberExpression max, Token comma) {
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

  public RutaBlock createAutomataBlock(Token id, RutaRuleElement re, List<RutaStatement> body,
          RutaBlock env) {
    return createScriptBlock(id, re, body, env);
  }

  public RegExpRule createRegExpRule(RutaBlock env) {
    return new RegExpRule(null, null, idCounter++, env);
  }

  public List<RuleElement> processConjunctRules(List<RuleElement> reList, List<Token> conList,
          RuleElementContainer container, RutaBlock env) {
    boolean isConjunct = false;
    for (Token token : conList) {
      if (token != null) {
        isConjunct = true;
        break;
      }
    }
    if (!isConjunct) {
      return reList;
    }
    Map<Integer, List<RuleElement>> map = new TreeMap<>();
    List<String> connectors = new ArrayList<>();
    int reCounter = 0;
    int conCounter = 0;
    for (Token token : conList) {
      if (token == null) {
        List<RuleElement> list = map.get(conCounter);
        if (list == null) {
          list = new ArrayList<>();
          map.put(conCounter, list);
        }
        RuleElement e = reList.get(reCounter);
        list.add(e);
        reCounter++;
      } else {
        connectors.add(token.getText());
        conCounter++;
      }
    }
    List<RuleElement> elements = new ArrayList<>();

    ConjunctRulesRuleElement cr = new ConjunctRulesRuleElement(null, container, env);
    for (List<RuleElement> each : map.values()) {
      ComposedRuleElement cre = createComposedRuleElement(each, null, null, null, cr, env);
      for (RuleElement ruleElement : each) {
        ruleElement.setContainer(cre);
      }
      elements.add(cre);
    }
    cr.setElements(elements);
    cr.setContainer(null);
    List<RuleElement> result = new ArrayList<>();
    result.add(cr);
    return result;
  }

  public UimaContext getContext() {
    return context;
  }

  public void setContext(UimaContext context) {
    this.context = context;
  }

}
