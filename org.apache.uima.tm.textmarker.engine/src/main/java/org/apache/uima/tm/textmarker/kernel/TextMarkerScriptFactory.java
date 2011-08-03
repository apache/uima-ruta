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

package org.apache.uima.tm.textmarker.kernel;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.tm.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.tm.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.SimpleTypeExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.ComposedRuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerLiteralMatcher;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRule;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerTypeMatcher;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.MinMaxGreedy;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.MinMaxReluctant;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.NormalQuantifier;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.PlusGreedy;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.PlusReluctant;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.QuestionGreedy;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.QuestionReluctant;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.StarGreedy;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.StarReluctant;


public class TextMarkerScriptFactory {

  private int idCounter = 0;

  public TextMarkerScriptFactory() {
    super();
  }

  public TextMarkerScriptBlock createScriptBlock(Token id, TextMarkerRuleElement ruleElement,
          List<TextMarkerStatement> body, TextMarkerBlock parent, CAS cas) {
    String text = id == null ? "root" : id.getText();
    String defaultNamespace = parent.getNamespace();
    if (id != null) {
      defaultNamespace = defaultNamespace + "." + text;
    }
    return createScriptBlock(text, ruleElement, body, parent, defaultNamespace, cas);
  }

  public TextMarkerScriptBlock createScriptBlock(String text, TextMarkerRuleElement ruleElement,
          List<TextMarkerStatement> body, TextMarkerBlock parent, String defaultNamespace, CAS cas) {
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
    return new TextMarkerScriptBlock(text, rule, elements, parent, defaultNamespace, cas);
  }

  public TextMarkerScriptBlock createRootScriptBlock(String module, String pack, CAS cas,
          TypeSystemDescription localTSD) {
    String defaultNamespace = pack + "." + module;
    TextMarkerScriptBlock result = createScriptBlock(module, null, null, null, defaultNamespace,
            cas);
    TextMarkerEnvironment environment = result.getEnvironment();
    try {
      Type topType = cas.getJCas().getCasType(TOP.type);
      List<Type> list = cas.getTypeSystem().getProperlySubsumedTypes(topType);
      for (Type type : list) {
        if (localTSD == null || localTSD.getType(type.getName()) != null) {
          environment.addType(type);
        }
      }
    } catch (CASRuntimeException e) {
      e.printStackTrace();
    } catch (CASException e) {
      e.printStackTrace();
    }
    result.setMatchRule(createRule(getDocumentRuleElement(result, cas), result));
    return result;
  }

  private List<RuleElement> getDocumentRuleElement(TextMarkerBlock parent, CAS cas) {
    List<RuleElement> result = new ArrayList<RuleElement>();

    result.add(createRuleElement(new SimpleTypeExpression(cas.getDocumentAnnotation().getType()),
            null, null, null, parent));
    return result;
  }

  public TextMarkerRule createRule(RuleElement element, TextMarkerBlock parent) {
    List<RuleElement> elements = new ArrayList<RuleElement>();
    elements.add(element);
    return new TextMarkerRule(elements, parent, idCounter++);
  }

  public TextMarkerRule createRule(List<RuleElement> elements, TextMarkerBlock parent) {
    return new TextMarkerRule(elements, parent, idCounter++);
  }

  public TextMarkerRuleElement createRuleElement(TypeExpression typeExpression,
          RuleElementQuantifier quantifier, List<AbstractTextMarkerCondition> conditions,
          List<AbstractTextMarkerAction> actions, TextMarkerBlock parent) {
    if (quantifier == null) {
      quantifier = new NormalQuantifier();
    }
    TextMarkerTypeMatcher matcher = new TextMarkerTypeMatcher(typeExpression);
    return new TextMarkerRuleElement(matcher, quantifier, conditions, actions, parent);
  }

  public TextMarkerRuleElement createRuleElement(StringExpression stringExpression,
          RuleElementQuantifier quantifier, List<AbstractTextMarkerCondition> conditions,
          List<AbstractTextMarkerAction> actions, TextMarkerBlock parent) {
    if (quantifier == null) {
      quantifier = new NormalQuantifier();
    }
    TextMarkerLiteralMatcher matcher = new TextMarkerLiteralMatcher(stringExpression);
    return new TextMarkerRuleElement(matcher, quantifier, conditions, actions, parent);
  }

  public ComposedRuleElement createComposedRuleElement(List<RuleElement> res,
          RuleElementQuantifier quantifier, TextMarkerBlock parent) {
    if (quantifier == null) {
      quantifier = new NormalQuantifier();
    }
    return new ComposedRuleElement(res, quantifier, parent);
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
          List<TextMarkerStatement> body, TextMarkerBlock env, CAS cas) {
    return null;
  }

}
