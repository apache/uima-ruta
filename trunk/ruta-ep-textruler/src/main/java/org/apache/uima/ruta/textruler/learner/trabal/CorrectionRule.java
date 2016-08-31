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

public class CorrectionRule extends TrabalRule {

  public CorrectionRule(TrabalLearner parentAlgorithm, TextRulerTarget target,
          TrabalAnnotation annotation, TrabalAnnotation targetAnnotation) {
    super(parentAlgorithm, target);
    this.annotation = annotation;
    this.targetAnnotation = targetAnnotation;
    this.errorType = AnnotationErrorType.CORRECTION;
    addConditionTypes();
  }

  public CorrectionRule(CorrectionRule copyFrom) {
    super((TrabalLearner) copyFrom.algorithm, copyFrom.target);
    this.annotation = copyFrom.annotation;
    this.targetAnnotation = copyFrom.targetAnnotation;
    this.errorType = AnnotationErrorType.CORRECTION;
    this.conditions = new ArrayList<Condition>();
    for (Condition c : copyFrom.conditions)
      conditions.add(c.clone());
    addConditionTypes();
  }

  private void addConditionTypes() {
    conditionTypes = new ArrayList<ConditionType>();
    conditionTypes.add(ConditionType.BEFORE);
    conditionTypes.add(ConditionType.AFTER);
    conditionTypes.add(ConditionType.STARTSWITH);
    conditionTypes.add(ConditionType.ENDSWITH);
    conditionTypes.add(ConditionType.CONTAINS);
    conditionTypes.add(ConditionType.PARTOF);
  }

  public void compileRuleString() {
    // parse conditions and add space if needed
    String conditions = parseConditions(conditionTypes);
    if (conditions.length() > 0)
      conditions += " ";
    // assemble rule string
    if (((TrabalLearner) algorithm).getEnableFeatures()) {
      ruleString = annotation.getType().getShortName() + "{" + conditions + "-> UNMARK("
              + annotation.getType().getShortName() + ", true), CREATE("
              + targetAnnotation.getType().getShortName() + parseFeatures() + ")};";
    } else {
      ruleString = annotation.getType().getShortName() + "{" + conditions + "-> UNMARK("
              + annotation.getType().getShortName() + ", true), MARK("
              + targetAnnotation.getType().getShortName() + ")};";
    }
    setNeedsCompile(false);
  }

  public boolean contains(TrabalAnnotation target) {
    if (target == null)
      return false;
    if (annotation.getType().getShortName().equals(target.getType().getShortName()))
      return true;
    if (targetAnnotation.getType().getShortName().equals(target.getType().getShortName()))
      return true;
    for (Condition each : getConditions())
      if (each.getItem().getName().equals(target.getType().getShortName()))
        return true;
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
    return new TrabalRuleItem(annotation);
  }

  @Override
  public TrabalRuleItem getRearBoundary() {
    return new TrabalRuleItem(annotation);
  }

  @Override
  public TrabalRule copy() {
    return new CorrectionRule(this);
  }

}
