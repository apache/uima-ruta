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

package org.apache.uima.textmarker.rule;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;

public class RuleElementMatch {

  protected final RuleElement ruleElement;

  protected List<AnnotationFS> textsMatched;

  protected boolean conditionsMatched = true;

  protected boolean baseConditionMatched = true;

  protected List<EvaluatedCondition> conditions;

  protected ComposedRuleElementMatch containerMatch;

  public RuleElementMatch(RuleElement ruleElement, ComposedRuleElementMatch containerMatch) {
    super();
    this.ruleElement = ruleElement;
    this.containerMatch = containerMatch;
    textsMatched = new ArrayList<AnnotationFS>();
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
    if (containerMatch != null) {
      containerMatch.addInnerMatch(ruleElement, this);
    }
  }

  public boolean matched() {
    return baseConditionMatched && conditionsMatched;
  }

  public RuleElement getRuleElement() {
    return ruleElement;
  }

  public List<AnnotationFS> getTextsMatched() {
    return textsMatched;
  }

  public void setTextsMatched(List<AnnotationFS> textsMatched) {
    this.textsMatched = textsMatched;
  }

  public List<EvaluatedCondition> getConditions() {
    return conditions;
  }

  public void setConditions(List<EvaluatedCondition> conditions) {
    this.conditions = conditions;
  }

  public void setConditionsMatched(boolean conditionsMatched) {
    this.conditionsMatched = conditionsMatched;
  }

  public void setBaseConditionMatched(boolean baseConditionMatched) {
    this.baseConditionMatched = baseConditionMatched;
  }

  public void setContainerMatch(ComposedRuleElementMatch containerMatch) {
    this.containerMatch = containerMatch;
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

  public boolean isBaseConditionMatched() {
    return baseConditionMatched;
  }

  public ComposedRuleElementMatch getContainerMatch() {
    return containerMatch;
  }

  public RuleElementMatch copy() {
    RuleElementMatch copy = new RuleElementMatch(ruleElement, containerMatch);
    copy.setBaseConditionMatched(baseConditionMatched);
    copy.setConditions(conditions);
    copy.setConditionsMatched(conditionsMatched);
    copy.setTextsMatched(textsMatched);
    return copy;
  }
}
