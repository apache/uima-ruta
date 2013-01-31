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

package org.apache.uima.textmarker.verbalize;

import java.util.Iterator;
import java.util.List;

import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerElement;
import org.apache.uima.textmarker.TextMarkerStatement;
import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.rule.ComposedRuleElement;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.TextMarkerDisjunctiveMatcher;
import org.apache.uima.textmarker.rule.TextMarkerMatcher;
import org.apache.uima.textmarker.rule.TextMarkerRule;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.rule.quantifier.MinMaxGreedy;
import org.apache.uima.textmarker.rule.quantifier.MinMaxReluctant;
import org.apache.uima.textmarker.rule.quantifier.NormalQuantifier;
import org.apache.uima.textmarker.rule.quantifier.PlusGreedy;
import org.apache.uima.textmarker.rule.quantifier.PlusReluctant;
import org.apache.uima.textmarker.rule.quantifier.QuestionGreedy;
import org.apache.uima.textmarker.rule.quantifier.QuestionReluctant;
import org.apache.uima.textmarker.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.textmarker.rule.quantifier.StarGreedy;
import org.apache.uima.textmarker.rule.quantifier.StarReluctant;

public class ScriptVerbalizer {

  private TextMarkerVerbalizer verbalizer;

  public ScriptVerbalizer(TextMarkerVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
  }

  public String verbalizeBlock(TextMarkerBlock block, boolean withElements) {
    StringBuilder result = new StringBuilder();
    TextMarkerRule rule = block.getRule();
    List<TextMarkerStatement> elements = block.getElements();
    result.append("BLOCK(");
    result.append(block.getId());
    result.append(")");
    result.append(" ");
    if (rule != null) {
      result.append(verbalizeRule(rule));
    }
    if (withElements) {
      result.append(" {\n");
      for (TextMarkerStatement each : elements) {
        if (each instanceof TextMarkerBlock) {
          result.append(verbalizeBlock((TextMarkerBlock) each, withElements));
        } else if (each instanceof TextMarkerRule) {
          result.append(verbalizeRule((TextMarkerRule) each));
        }
        result.append(";");
        result.append("\n");
      }
      result.append(" }\n");
    }
    return result.toString();
  }

  public String verbalizeRule(TextMarkerRule rule) {
    List<RuleElement> elements = rule.getRuleElements();
    StringBuilder result = new StringBuilder();
    for (RuleElement each : elements) {
      result.append(verbalizeRuleElement(each));
      result.append(" ");
    }
    return result.toString();
  }

  public String verbalizeRuleElement(RuleElement re) {
    List<AbstractTextMarkerCondition> conditions = re.getConditions();
    List<AbstractTextMarkerAction> actions = re.getActions();
    RuleElementQuantifier quantifier = re.getQuantifier();
    StringBuilder result = new StringBuilder();
    if (re instanceof ComposedRuleElement) {
      result.append(verbalizeComposed((ComposedRuleElement) re));
    } else if (re instanceof TextMarkerRuleElement) {
      TextMarkerRuleElement tmre = (TextMarkerRuleElement) re;
      result.append(verbalizeMatcher(tmre));
    }
    result.append(verbalizeQuantifier(quantifier));

    if (!conditions.isEmpty() || !actions.isEmpty()) {
      result.append("{");
      Iterator<AbstractTextMarkerCondition> cit = conditions.iterator();
      while (cit.hasNext()) {
        AbstractTextMarkerCondition each = cit.next();
        result.append(verbalizer.verbalize(each));
        if (cit.hasNext()) {
          result.append(",");
        }
      }
      if (!actions.isEmpty()) {
        result.append(" -> ");
        Iterator<AbstractTextMarkerAction> ait = actions.iterator();
        while (ait.hasNext()) {
          AbstractTextMarkerAction each = ait.next();
          result.append(verbalizer.verbalize(each));
          if (ait.hasNext()) {
            result.append(",");
          }
        }
      }
      result.append("}");
    }
    return result.toString();
  }

  public String verbalizeComposed(ComposedRuleElement cre) {
    StringBuilder result = new StringBuilder();
    List<RuleElement> ruleElements = cre.getRuleElements();
    result.append("(");
    for (RuleElement each : ruleElements) {
      if (cre.getRuleElements().indexOf(each) != 0) {
        result.append(" ");
      }
      result.append(verbalizeRuleElement(each));
    }
    result.append(")");
    return result.toString();
  }

  public String verbalizeMatcher(TextMarkerRuleElement tmre) {
    StringBuilder result = new StringBuilder();
    TextMarkerMatcher matcher = tmre.getMatcher();
    if (matcher instanceof TextMarkerDisjunctiveMatcher) {
      TextMarkerDisjunctiveMatcher dmatcher = (TextMarkerDisjunctiveMatcher) matcher;
      List<TextMarkerExpression> expressions = dmatcher.getExpressions();
      result.append("(");
      for (TextMarkerExpression each : expressions) {
        if (expressions.indexOf(each) != 0) {
          result.append(" | ");
        }
        result.append(verbalizer.verbalize(each));
      }
      result.append(")");
    } else {
      result.append(verbalizer.verbalize(matcher.getExpression()));
    }
    return result.toString();
  }

  public String verbalizeQuantifier(RuleElementQuantifier quantifier) {
    if (quantifier instanceof NormalQuantifier) {
      return "";
    } else if (quantifier instanceof MinMaxGreedy) {
      MinMaxGreedy mmg = (MinMaxGreedy) quantifier;
      return "[" + verbalizer.verbalize(mmg.getMin()) + "," + verbalizer.verbalize(mmg.getMax())
              + "]";
    } else if (quantifier instanceof MinMaxReluctant) {
      MinMaxReluctant mmr = (MinMaxReluctant) quantifier;
      return "[" + verbalizer.verbalize(mmr.getMin()) + "," + verbalizer.verbalize(mmr.getMax())
              + "]?";
    } else if (quantifier instanceof PlusGreedy) {
      return "+";
    } else if (quantifier instanceof PlusReluctant) {
      return "+?";
    } else if (quantifier instanceof QuestionGreedy) {
      return "?";
    } else if (quantifier instanceof QuestionReluctant) {
      return "??";
    } else if (quantifier instanceof StarGreedy) {
      return "*";
    } else if (quantifier instanceof StarReluctant) {
      return "*?";
    }
    return null;
  }

  public String verbalize(TextMarkerElement element) {
    if (element instanceof TextMarkerBlock) {
      return verbalizeBlock((TextMarkerBlock) element, false);
    } else if (element instanceof RuleElementQuantifier) {
      return verbalizeQuantifier((RuleElementQuantifier) element);
    } else if (element instanceof TextMarkerRule) {
      return verbalizeRule((TextMarkerRule) element);
    } else if (element instanceof TextMarkerRuleElement) {
      return verbalizeRuleElement((TextMarkerRuleElement) element);
    }
    return null;
  }

}
