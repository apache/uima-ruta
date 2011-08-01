package org.apache.uima.tm.textmarker.kernel.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;

public class RuleElementMatch {

  private final RuleElement ruleElement;

  private List<AnnotationFS> textsMatched;

  private boolean conditionsMatched = true;

  private boolean baseConditionMatched = true;

  private List<EvaluatedCondition> conditions;

  private List<RuleElementMatch> innerMatches;

  public RuleElementMatch(RuleElement ruleElement) {
    super();
    this.ruleElement = ruleElement;
    textsMatched = Collections.emptyList();
  }

  public void setMatchInfo(boolean baseCondition, List<AnnotationFS> texts,
          List<EvaluatedCondition> conditionList) {
    baseConditionMatched = baseCondition;
    textsMatched = texts;
    conditions = conditionList;
    conditionsMatched = baseConditionMatched;
    if (baseConditionMatched) {
      for (EvaluatedCondition each : conditions) {
        conditionsMatched &= each.isValue();
      }
    }
  }

  public boolean matched() {
    return conditionsMatched;
  }

  public RuleElement getRuleElement() {
    return ruleElement;
  }

  public List<AnnotationFS> getTextsMatched() {
    if (textsMatched.size() > 1) {
      System.out.println("TEXTMATCHED > 1");
    }
    return textsMatched;
  }

  public List<EvaluatedCondition> getConditions() {
    return conditions;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(matched() + " : ");
    for (AnnotationFS each : textsMatched) {
      sb.append(each.getCoveredText());
    }
    String string = sb.toString();
    if (string.length() > 20) {
      string = string.substring(0, 20) + "...";
    }
    return string;
  }

  public void setMatched(boolean matched) {
    conditionsMatched = matched;
  }

  public boolean isBaseConditionMatched() {
    return baseConditionMatched;
  }

  public void setInnerMatches(List<RuleElementMatch> innerMatches) {
    this.innerMatches = innerMatches;
    textsMatched = new ArrayList<AnnotationFS>();
    boolean matched = true;
    for (RuleElementMatch ruleElementMatch : innerMatches) {
      List<AnnotationFS> list = ruleElementMatch.getTextsMatched();
      textsMatched.addAll(list);
      matched &= ruleElementMatch.matched();
    }
    baseConditionMatched = matched;
    conditionsMatched = matched;
  }

  public List<RuleElementMatch> getInnerMatches() {
    return innerMatches;
  }

}
