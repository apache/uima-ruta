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

package org.apache.uima.ruta.textruler.learner.trabal;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.textruler.core.TextRulerRule;
import org.apache.uima.ruta.textruler.core.TextRulerStatisticsCollector;
import org.apache.uima.ruta.textruler.core.TextRulerTarget;

public abstract class TrabalRule extends TextRulerRule implements Comparable<TrabalRule> {

  protected TrabalAnnotation annotation;

  protected TrabalAnnotation targetAnnotation;

  protected float errorRate;

  protected AnnotationErrorType errorType;
  
  protected List<ConditionType> conditionTypes;

  protected List<Condition> conditions = new ArrayList<Condition>();

  protected int rating = 0;

  public TrabalRule(TrabalLearner parentAlgorithm, TextRulerTarget target) {
    super(parentAlgorithm, target);
  }

  @Override
  public void setCoveringStatistics(TextRulerStatisticsCollector c) {
    super.setCoveringStatistics(c);
    int p = c.getCoveredPositivesCount();
    int n = c.getCoveredNegativesCount();
    if (p < 1) {
      errorRate = Float.MAX_VALUE;
    } else {
      errorRate = ((float) n) / ((float) p);
    }
  }

  public List<Condition> getConditions() {
    return conditions;
  }

  public void addCondition(Condition condition) {
    if (!conditions.contains(condition))
      conditions.add(condition);
  }

  public void addCondition(Condition condition, int rating) {
    if (!conditions.contains(condition)) {
      conditions.add(condition);
      this.rating += rating;
    }
  }

  protected String parseConditions(List<ConditionType> types) {
    String result = "";
    for (Condition each : conditions)
      if (types.contains(each.getType()))
        result += each + ", ";
    if (result.length() > 0)
      return result.substring(0, result.length() - 2);
    return "";
  }

  protected String parseConditions(ConditionType type) {
    String result = "";
    for (Condition each : conditions)
      if (each.getType() == type)
        result += each + ", ";
    if (result.length() > 0)
      return result.substring(0, result.length() - 2);
    return "";
  }
  
  public float getErrorRate() {
    return errorRate;
  }

  public AnnotationErrorType getErrorType() {
    return errorType;
  }

  public TrabalAnnotation getAnnotation() {
    return annotation;
  }

  public TrabalAnnotation getTargetAnnotation() {
    return targetAnnotation;
  }

  public TrabalAnnotation getMatchingAnnotation() {
    switch (errorType) {
      case CORRECTION:
      case DELETION:
        return annotation;
      default:
        return targetAnnotation;
    }
  }

  public int getRating() {
    return this.rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    String result = "// " + getAnnotation() + " -> " + getTargetAnnotation();
    result = result.replaceAll("\n", " ");
    result = result.replaceAll("\f", " ");
    result = result.replaceAll("\r", " ");
    result = result.replaceAll("\t", " ");
    result += "\n";
    return result + ruleString + "\t// " + getCoveringStatistics() + "\n\n";
  }

  public int compareTo(TrabalRule obj) {
    if (rating < obj.getRating())
      return -1;
    if (rating == obj.getRating())
      return 0;
    return 1;
  }

  public String parseFeatures() {
    String result = "";
    for (String key : targetAnnotation.getFeatures().keySet()) {
      result += ", \"" + key + "\" = \"" + targetAnnotation.getFeatures().get(key) + "\"";
    }
    return result;
  }
  
  @Override
  public abstract void compileRuleString();

  public abstract boolean contains(TrabalAnnotation target);

  public abstract boolean hasSameBasicRule(TrabalRule rule);

  public abstract TrabalRuleItem getFrontBoundary();

  public abstract TrabalRuleItem getRearBoundary();

  public abstract TrabalRule copy();

}
