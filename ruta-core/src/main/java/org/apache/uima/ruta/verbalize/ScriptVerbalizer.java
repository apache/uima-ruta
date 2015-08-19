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

package org.apache.uima.ruta.verbalize;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.AbstractRuleElement;
import org.apache.uima.ruta.rule.ComposedRuleElement;
import org.apache.uima.ruta.rule.ConjunctRulesRuleElement;
import org.apache.uima.ruta.rule.RegExpRule;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RutaMatcher;
import org.apache.uima.ruta.rule.RutaRule;
import org.apache.uima.ruta.rule.RutaRuleElement;
import org.apache.uima.ruta.rule.RutaTypeMatcher;
import org.apache.uima.ruta.rule.WildCardRuleElement;
import org.apache.uima.ruta.rule.quantifier.MinMaxGreedy;
import org.apache.uima.ruta.rule.quantifier.MinMaxReluctant;
import org.apache.uima.ruta.rule.quantifier.NormalQuantifier;
import org.apache.uima.ruta.rule.quantifier.PlusGreedy;
import org.apache.uima.ruta.rule.quantifier.PlusReluctant;
import org.apache.uima.ruta.rule.quantifier.QuestionGreedy;
import org.apache.uima.ruta.rule.quantifier.QuestionReluctant;
import org.apache.uima.ruta.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.ruta.rule.quantifier.StarGreedy;
import org.apache.uima.ruta.rule.quantifier.StarReluctant;

public class ScriptVerbalizer {

  private static final String CBCLOSE = "}";

  private static final String CBOPEN = "{";

  private static final String THEN = " -> ";

  private static final String THEN2 = " <- ";

  private RutaVerbalizer verbalizer;

  public ScriptVerbalizer(RutaVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
  }

  public String verbalizeBlock(RutaBlock block, boolean withElements) {
    StringBuilder result = new StringBuilder();
    RutaRule rule = block.getRule();
    List<RutaStatement> elements = block.getElements();
    String name = block.getName();
    result.append("BLOCK");
    if (name != null) {
      result.append("(");
      result.append(name);
      result.append(")");
    }
    result.append(" ");
    if (rule != null) {
      result.append(verbalizeRule(rule));
    }
    if (withElements) {
      result.append(" {\n");
      for (RutaStatement each : elements) {
        if (each instanceof RutaBlock) {
          result.append(verbalizeBlock((RutaBlock) each, withElements));
        } else if (each instanceof RutaRule) {
          result.append(verbalizeRule((RutaRule) each));
        }
        result.append(";");
        result.append("\n");
      }
      result.append(" }\n");
    }
    return result.toString();
  }

  public String verbalizeRule(RutaRule rule) {
    List<RuleElement> elements = rule.getRuleElements();
    StringBuilder result = new StringBuilder();
    for (RuleElement each : elements) {
      result.append(verbalizeRuleElement(each));
      result.append(" ");
    }
    return result.toString();
  }

  public String verbalizeRuleElement(RuleElement re) {
    List<AbstractRutaCondition> conditions = re.getConditions();
    List<AbstractRutaAction> actions = re.getActions();
    RuleElementQuantifier quantifier = re.getQuantifier();
    StringBuilder result = new StringBuilder();
    if (re.isStartAnchor()) {
      result.append("@");
    }
    if (re instanceof ConjunctRulesRuleElement) {
      result.append(verbalizeConjunct((ConjunctRulesRuleElement) re));
    } else if (re instanceof ComposedRuleElement) {
      result.append(verbalizeComposed((ComposedRuleElement) re));
    } else if (re instanceof RutaRuleElement) {
      RutaRuleElement tmre = (RutaRuleElement) re;
      RutaMatcher matcher = tmre.getMatcher();
      // action-only rule
      if (matcher instanceof RutaTypeMatcher) {
        RutaExpression expression = ((RutaTypeMatcher) matcher).getExpression();
        if (expression instanceof MatchReference) {
          MatchReference mr = (MatchReference) expression;
          if (mr.getMatch() == null) {
            Iterator<AbstractRutaAction> ait = actions.iterator();
            while (ait.hasNext()) {
              AbstractRutaAction each = ait.next();
              result.append(verbalizer.verbalize(each));
              if (ait.hasNext()) {
                result.append(",");
              }
            }
            return result.toString();
          }
        }
      }
      result.append(verbalizeMatcher(tmre));
    } else if (re instanceof WildCardRuleElement) {
      result.append("#");
    }
    result.append(verbalizeQuantifier(quantifier));

    if (!conditions.isEmpty() || !actions.isEmpty()) {
      result.append(CBOPEN);
      Iterator<AbstractRutaCondition> cit = conditions.iterator();
      while (cit.hasNext()) {
        AbstractRutaCondition each = cit.next();
        result.append(verbalizer.verbalize(each));
        if (cit.hasNext()) {
          result.append(",");
        }
      }
      if (!actions.isEmpty()) {
        result.append(THEN);
        Iterator<AbstractRutaAction> ait = actions.iterator();
        while (ait.hasNext()) {
          AbstractRutaAction each = ait.next();
          result.append(verbalizer.verbalize(each));
          if (ait.hasNext()) {
            result.append(",");
          }
        }
      }
      result.append(CBCLOSE);
    }
    if (re instanceof AbstractRuleElement) {
      AbstractRuleElement are = (AbstractRuleElement) re;
      List<RutaStatement> inlinedConditionRules = are.getInlinedConditionRules();
      if (inlinedConditionRules != null && !inlinedConditionRules.isEmpty()) {
        result.append(THEN2);
        result.append(CBOPEN);
        for (RutaStatement rutaStatement : inlinedConditionRules) {
          result.append(verbalize(rutaStatement));
          result.append(";");
        }
      }
      List<RutaStatement> inlinedActionRules = are.getInlinedActionRules();
      if (inlinedActionRules != null && !inlinedActionRules.isEmpty()) {
        result.append(THEN);
        result.append(CBOPEN);
        for (RutaStatement rutaStatement : inlinedActionRules) {
          result.append(verbalize(rutaStatement));
          result.append(";");
        }
      }
    }
    return result.toString();
  }

  private String verbalizeConjunct(ConjunctRulesRuleElement re) {
    StringBuilder result = new StringBuilder();
    String sep = " % ";
    List<RuleElement> ruleElements = re.getRuleElements();
    for (RuleElement each : ruleElements) {
      if (re.getRuleElements().indexOf(each) != 0) {
        result.append(sep);
      }
      result.append(verbalizeRuleElement(each));
    }
    return result.toString();
  }

  public String verbalizeComposed(ComposedRuleElement cre) {
    StringBuilder result = new StringBuilder();
    List<RuleElement> ruleElements = cre.getRuleElements();
    result.append("(");
    String sep = " ";
    Boolean conjunct = cre.getConjunct();
    if (conjunct != null) {
      if (conjunct) {
        sep = " & ";
      } else {
        sep = " | ";
      }
    }
    for (RuleElement each : ruleElements) {
      if (cre.getRuleElements().indexOf(each) != 0) {
        result.append(sep);
      }
      result.append(verbalizeRuleElement(each));
    }
    result.append(")");
    return result.toString();
  }

  public String verbalizeMatcher(RutaRuleElement tmre) {
    StringBuilder result = new StringBuilder();
    RutaMatcher matcher = tmre.getMatcher();
    result.append(verbalizer.verbalize(matcher.getExpression()));
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

  public String verbalize(RutaElement element) {
    if (element instanceof RutaBlock) {
      return verbalizeBlock((RutaBlock) element, false);
    } else if (element instanceof RuleElementQuantifier) {
      return verbalizeQuantifier((RuleElementQuantifier) element);
    } else if (element instanceof RutaRule) {
      return verbalizeRule((RutaRule) element);
    } else if (element instanceof RegExpRule) {
      return verbalizeRegExpRule((RegExpRule) element);
    } else if (element instanceof RutaRuleElement) {
      return verbalizeRuleElement((RutaRuleElement) element);
    }
    return null;
  }

  private String verbalizeRegExpRule(RegExpRule rule) {
    StringBuilder sb = new StringBuilder();
    String regexp = verbalizer.verbalize(rule.getRegExp());
    sb.append(regexp);
    sb.append(THEN);

    Iterator<Entry<TypeExpression, INumberExpression>> iterator = rule.getTypeMap().entrySet()
            .iterator();
    while (iterator.hasNext()) {
      Entry<TypeExpression, INumberExpression> next = iterator.next();
      String type = verbalizer.verbalize(next.getKey());
      INumberExpression value = next.getValue();
      if (value != null) {
        String group = verbalizer.verbalize(value);
        sb.append(group + " = " + type);
      } else {
        sb.append(type);
      }
      Map<TypeExpression, Map<IStringExpression, IRutaExpression>> featureAssignments = rule
              .getFeatureAssignments();
      if (featureAssignments != null) {
        Map<IStringExpression, IRutaExpression> map = featureAssignments.get(next.getKey());
        if (map != null) {
          sb.append("(");
          Iterator<Entry<IStringExpression, IRutaExpression>> fit = map.entrySet().iterator();
          while (fit.hasNext()) {
            Map.Entry<IStringExpression, IRutaExpression> entry = (Map.Entry<IStringExpression, IRutaExpression>) fit
                    .next();
            sb.append(verbalizer.verbalize(entry.getKey()));
            sb.append(" = ");
            sb.append(verbalizer.verbalize(entry.getValue()));
            if (fit.hasNext()) {
              sb.append(", ");
            }
          }
          sb.append(")");
        }

      }
      if (iterator.hasNext()) {
        sb.append(", ");
      }
    }
    sb.append(";");
    return sb.toString();
  }

}
