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

public abstract class ShiftingRule extends TrabalRule {

  protected TrabalRuleItem frontBoundaryItem;

  protected TrabalRuleItem rearBoundaryItem;

  public ShiftingRule(TrabalLearner parentAlgorithm, TextRulerTarget target,
          TrabalAnnotation annotation, TrabalAnnotation targetAnnotation,
          AnnotationErrorType errorType) {
    super(parentAlgorithm, target);
    this.annotation = annotation;
    this.targetAnnotation = targetAnnotation;
    this.errorType = errorType;
    addConditionTypes();
  }

  public ShiftingRule(ShiftingRule copyFrom) {
    super((TrabalLearner) copyFrom.algorithm, copyFrom.target);
    this.annotation = copyFrom.annotation;
    this.targetAnnotation = copyFrom.targetAnnotation;
    this.errorType = copyFrom.errorType;
    this.frontBoundaryItem = copyFrom.frontBoundaryItem;
    this.rearBoundaryItem = copyFrom.rearBoundaryItem;
    this.conditions = new ArrayList<Condition>();
    for (Condition c : copyFrom.conditions)
      conditions.add(c.clone());
    addConditionTypes();
  }

  protected void addConditionTypes() {
    conditionTypes = new ArrayList<ConditionType>();
    conditionTypes.add(ConditionType.STARTSWITH);
    conditionTypes.add(ConditionType.ENDSWITH);
    conditionTypes.add(ConditionType.CONTAINS);
    conditionTypes.add(ConditionType.PARTOF);
  }

  public boolean contains(TrabalAnnotation target) {
    if (target != null) {
      if (annotation.getType().getShortName().equals(target.getType().getShortName())) {
        return true;
      }
      if (targetAnnotation.getType().getShortName().equals(target.getType().getShortName())) {
        return true;
      }
      if (frontBoundaryItem != null)
        if (frontBoundaryItem.getAnnotation().equals(target))
          return true;
      if (rearBoundaryItem != null)
        if (rearBoundaryItem.getAnnotation().equals(target))
          return true;
      for (Condition each : getConditions()) {
        if (each.getItem().getAnnotation().getType().getShortName()
                .equals(target.getType().getShortName())) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean hasSameBasicRule(TrabalRule rule) {
    if (rule.getAnnotation() == null)
      return false;
    if (!rule.getAnnotation().getType().equals(annotation.getType()))
      return false;
    if (rule.getTargetAnnotation() == null)
      return false;
    if (!rule.getTargetAnnotation().getType().equals(targetAnnotation.getType()))
      return false;
    return true;
  }

  @Override
  public TrabalRuleItem getFrontBoundary() {
    if (annotation.getBegin() == targetAnnotation.getBegin())
      return new TrabalRuleItem(annotation);
    return frontBoundaryItem;
  }

  @Override
  public TrabalRuleItem getRearBoundary() {
    if (annotation.getEnd() == targetAnnotation.getEnd())
      return new TrabalRuleItem(annotation);
    return rearBoundaryItem;
  }

  public void setFrontBoundaryItem(TrabalRuleItem frontBoundaryItem) {
    this.frontBoundaryItem = frontBoundaryItem;
  }

  public void setRearBoundaryItem(TrabalRuleItem rearBoundaryItem) {
    this.rearBoundaryItem = rearBoundaryItem;
  }

}
