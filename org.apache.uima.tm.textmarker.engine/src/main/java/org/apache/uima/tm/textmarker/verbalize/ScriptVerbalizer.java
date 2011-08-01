package org.apache.uima.tm.textmarker.verbalize;

import java.util.Iterator;
import java.util.List;

import org.apache.uima.tm.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.tm.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerElement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.rule.ComposedRuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerMatcher;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRule;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
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
    List<RuleElement> elements = rule.getElements();
    StringBuilder result = new StringBuilder();
    for (RuleElement each : elements) {
      result.append(verbalizeRuleElement(each));
      result.append(" ");
    }
    return result.toString();
  }

  public String verbalizeRuleElement(RuleElement re) {
    if (re instanceof TextMarkerRuleElement) {
      TextMarkerRuleElement element = (TextMarkerRuleElement) re;
      TextMarkerMatcher matcher = element.getMatcher();
      List<AbstractTextMarkerCondition> conditions = element.getConditions();
      List<AbstractTextMarkerAction> actions = element.getActions();
      RuleElementQuantifier quantifier = element.getQuantifier();
      StringBuilder result = new StringBuilder();
      result.append(verbalizer.verbalize(matcher.getExpression()));
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
    } else if (re instanceof ComposedRuleElement) {
      ComposedRuleElement cre = (ComposedRuleElement) re;
      StringBuilder result = new StringBuilder("(");
      for (RuleElement each : cre.getElements()) {
        if (cre.getElements().indexOf(each) != 0) {
          result.append(" ");
        }
        result.append(verbalizeRuleElement(each));
      }
      result.append(")");
      result.append(verbalizeQuantifier(cre.getQuantifier()));
      return result.toString();
    }
    return "";
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
