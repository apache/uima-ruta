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

import org.apache.uima.ruta.textruler.core.TextRulerTarget;

public class AnnotationRule extends TrabalRule {

  private TrabalRuleItem frontBoundaryItem;

  private TrabalRuleItem rearBoundaryItem;

  public AnnotationRule(TrabalLearner parentAlgorithm, TextRulerTarget target,
          TrabalAnnotation targetAnnotation) {
    super(parentAlgorithm, target);
    this.targetAnnotation = targetAnnotation;
    this.errorType = AnnotationErrorType.ANNOTATION;
    addConditionTypes();
  }

  public AnnotationRule(AnnotationRule copyFrom) {
    super((TrabalLearner) copyFrom.algorithm, copyFrom.target);
    this.targetAnnotation = copyFrom.targetAnnotation;
    this.errorType = AnnotationErrorType.ANNOTATION;
    this.frontBoundaryItem = copyFrom.frontBoundaryItem;
    this.rearBoundaryItem = copyFrom.rearBoundaryItem;
    this.conditions = new ArrayList<Condition>();
    for (Condition c : copyFrom.conditions)
      conditions.add(c.clone());
    addConditionTypes();
  }

  private void addConditionTypes() {
    conditionTypes = new ArrayList<ConditionType>();
    conditionTypes.add(ConditionType.STARTSWITH);
    conditionTypes.add(ConditionType.ENDSWITH);
    conditionTypes.add(ConditionType.CONTAINS);
    conditionTypes.add(ConditionType.PARTOF);
  }

  public void compileRuleString() {
    boolean frontItemInBorders = frontBoundaryItem.getAnnotation().getBegin() == targetAnnotation
            .getBegin();
    boolean rearItemInBorders = rearBoundaryItem.getAnnotation().getEnd() == targetAnnotation
            .getEnd();

    // this string will be attached to the front boundary item
    String after = parseConditions(ConditionType.AFTER);
    if (after.length() > 0)
      after = "{" + after + "}";

    // this string will be attached to the rear boundary item
    String before = parseConditions(ConditionType.BEFORE);
    if (before.length() > 0)
      before = "{" + before + "}";

    // these strings will be attached to the brackets
    String conditions = parseConditions(conditionTypes);
    if (conditions.length() > 0)
      conditions += ", ";
    String nPartof = "-PARTOF(" + targetAnnotation.getType().getShortName() + ")";
    String mark;
    if (((TrabalLearner) algorithm).getEnableFeatures())
      mark = "-> CREATE(" + targetAnnotation.getType().getShortName() + parseFeatures() + ")";
    else
      mark = "-> MARK(" + targetAnnotation.getType().getShortName() + ")";
    String action = "{" + conditions + nPartof + " " + mark + "}";

    // These are the four possible patterns:
    // ( frontBoundaryItem after ANY*? rearBoundaryItem before ) action ;
    // frontBoundaryItem after ( ANY*? rearBoundaryItem before ) action ;
    // ( frontBoundaryItem after ANY*? ) action rearBoundaryItem before ;
    // frontBoundaryItem after ( ANY*? ) action rearBoundaryItem before ;

    ruleString = "";

    // If the front item is part of the future annotation, it has to be included in the brackets.
    if (frontItemInBorders)
      ruleString += "(" + frontBoundaryItem + after + " ";
    else
      ruleString += frontBoundaryItem + after + " (";

    // We include all tokens between the boundaries.
    ruleString += "ANY*{-PARTOF(" + rearBoundaryItem + ")}"; // like ANY*? but faster
//    ruleString += "#{-CONTAINS(" + rearBoundaryItem + ")}"; // like ... but faster
    
    
    // Check, if the rear item should be included and mark all tokens between the brackets as the
    // new annotation.
    if (rearItemInBorders)
      ruleString += " " + rearBoundaryItem + before + ")" + action + ";";
    else
      ruleString += ")" + action + " " + rearBoundaryItem + before + ";";

    setNeedsCompile(false);
  }

  public boolean contains(TrabalAnnotation target) {
    if (target != null) {
      if (targetAnnotation.getType().getShortName().equals(target.getType().getShortName())) {
        return true;
      }
      if (frontBoundaryItem.getName().equals(target.getType().getShortName()))
        return true;
      if (rearBoundaryItem.getName().equals(target.getType().getShortName()))
        return true;
      for (Condition each : getConditions())
        if (each.getItem().getName().equals(target.getType().getShortName()))
          return true;
    }
    return false;
  }

  public boolean hasSameBasicRule(TrabalRule rule) {
    if (rule.getAnnotation() != null)
      return false;
    if (rule.getTargetAnnotation() == null)
      return false;
    if (!rule.getTargetAnnotation().getType().getShortName()
            .equals(targetAnnotation.getType().getShortName()))
      return false;
    return true;
  }

  @Override
  public TrabalRuleItem getFrontBoundary() {
    return frontBoundaryItem;
  }

  public void setFrontBoundary(TrabalRuleItem item) {
    frontBoundaryItem = item;
  }

  @Override
  public TrabalRuleItem getRearBoundary() {
    return rearBoundaryItem;
  }

  public void setRearBoundary(TrabalRuleItem item) {
    rearBoundaryItem = item;
  }

  @Override
  public TrabalRule copy() {
    return new AnnotationRule(this);
  }

}
