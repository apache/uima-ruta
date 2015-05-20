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

package org.apache.uima.ruta.textruler.learner.kep;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.textruler.core.TextRulerAnnotation;
import org.apache.uima.ruta.textruler.core.TextRulerRule;
import org.apache.uima.ruta.textruler.core.TextRulerRuleItem;

public class KEPRuleItem implements TextRulerRuleItem {

  private boolean isStarWildCard = false;

  private boolean isReluctant = false;

  private Type type;

  private TextRulerAnnotation annotation;

  private int min = 1;

  private int max = 1;

  private List<List<KEPRuleItemCondition>> conditions = new ArrayList<List<KEPRuleItemCondition>>();

  public KEPRuleItem(KEPRuleItem copyFrom) {
    super();
    this.annotation = copyFrom.annotation;
    this.isStarWildCard = copyFrom.isStarWildCard;
    this.type = copyFrom.type;
    this.conditions = new ArrayList<List<KEPRuleItemCondition>>();
    for (List<KEPRuleItemCondition> cList : copyFrom.conditions) {
      this.conditions.add(new ArrayList<KEPRuleItemCondition>(cList));
    }
    this.isReluctant = copyFrom.isReluctant;
    this.min = copyFrom.min;
    this.max = copyFrom.max;
  }

  public KEPRuleItem(Type type) {
    super();
    this.type = type;
  }

  public KEPRuleItem(TextRulerAnnotation a) {
    super();
    this.type = a.getType();
    this.annotation = a;
  }

  public KEPRuleItem(AnnotationFS afs) {
    super();
    this.annotation = new TextRulerAnnotation(afs);
    this.type = this.annotation.getType();
  }

  public KEPRuleItem(Type type, String regExpString) {
    super();
    this.type = type;
    List<KEPRuleItemCondition> list = new ArrayList<KEPRuleItemCondition>();
    list.add(new KEPRuleItemCondition(regExpString));
    this.conditions.add(list);
  }

  public KEPRuleItem() {
    this.type = null;
  }

  public KEPRuleItem copy() {
    return new KEPRuleItem(this);
  }

  public String getStringForRuleString(TextRulerRule rule, MLRuleItemType type,
          int numberInPattern, int patternSize, int numberInRule, int ruleSize, int slotIndex) {

    String mark = "";
    KEPRule kepRule = (KEPRule) rule;
    boolean isMarkingItem = type == MLRuleItemType.FILLER && numberInPattern == 0;
    String cStr = "";

    String anchor = (this.type == null ? "ANY" : this.type.getShortName())
            + (isStarWildCard ? "*" : "")
            + ((min == 1 && max == 1) ? "" : ("[" + min + "," + max + "]"))
            + (isReluctant ? "?" : "") + ((isMarkingItem || !this.conditions.isEmpty()) ? "{" : "");

    if (!this.conditions.isEmpty() && !this.conditions.get(0).isEmpty()) {
      for (List<KEPRuleItemCondition> cList : this.conditions) {
        if (cList.size() == 1) {
          cStr += cList.get(0) + ", ";
        } else {
          cStr += "OR(";
          for (KEPRuleItemCondition condition : cList) {
            cStr += condition + ", ";
          }
          cStr = cStr.substring(0, cStr.lastIndexOf(","));
          cStr += "), ";
        }
      }
      cStr = cStr.substring(0, cStr.lastIndexOf(","));
    }

    if (isMarkingItem) {
      if (kepRule.isCorrectionRule())
        mark += "->UNMARK(" + kepRule.getMarkName(slotIndex);
      else
        mark += "->MARKONCE(" + kepRule.getMarkName(slotIndex);
      if (patternSize > 1)
        mark += ", " + (numberInRule + 1) + ", " + (numberInRule + patternSize);
      mark += ")";
    }
    return anchor + cStr + mark + ((isMarkingItem || !this.conditions.isEmpty()) ? "}" : "");
  }

  @Override
  public String toString() {
    return getStringForRuleString(null, null, 0, 0, 0, 0, 0);
  }

  public boolean isStarWildCard() {
    return isStarWildCard;
  }

  public KEPRuleItem setStarWildCard(boolean isStarWildCard) {
    this.isStarWildCard = isStarWildCard;
    if (isStarWildCard) {
      this.min = 1;
      this.max = 1;
    }
    return this;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public boolean equals(TextRulerRuleItem o) {
    return o.toString().equals(this.toString());
  }

  public int getBegin() {
    return this.annotation.getBegin();
  }

  public int getEnd() {
    return this.annotation.getEnd();
  }

  public int getMin() {
    return min;
  }

  public KEPRuleItem setMin(int min) {
    this.min = min;
    if (min > this.max)
      this.max = min;
    isStarWildCard = false;
    return this;
  }

  public int getMax() {
    return max;
  }

  public KEPRuleItem setMax(int max) {
    this.max = max;
    if (max < this.min)
      this.min = max;
    isStarWildCard = false;
    return this;
  }

  public boolean isReluctant() {
    return isReluctant;
  }

  public KEPRuleItem setReluctant(boolean isReluctant) {
    this.isReluctant = isReluctant;
    return this;
  }

  public KEPRuleItem addAndCondition(KEPRuleItemCondition condition) {
    List<KEPRuleItemCondition> list = new ArrayList<KEPRuleItemCondition>();
    list.add(condition);
    this.conditions.add(list);
    return this;
  }

  public List<List<KEPRuleItemCondition>> getConditions() {
    return this.conditions;
  }

  public void setAnnotation(AnnotationFS afs) {
    this.annotation = new TextRulerAnnotation(afs);
    this.type = this.annotation.getType();
  }

  public KEPRuleItem setConditions(List<List<KEPRuleItemCondition>> conditions) {
    this.conditions = conditions;
    return this;
  }

  public KEPRuleItem addConditions(List<KEPRuleItemCondition> toMerge) {
    this.conditions.add(toMerge);
    return this;
  }

  public boolean containsAndCondition(Type type2) {
    for (List<KEPRuleItemCondition> list : this.conditions) {
      if (list.size() == 1)
        for (KEPRuleItemCondition c : list) {
          if (c.equals(type2))
            return true;
        }
    }
    return false;
  }
}