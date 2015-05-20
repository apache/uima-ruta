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

import org.apache.uima.ruta.textruler.core.TextRulerTarget;

public class ShiftAllRule extends ShiftingRule {

  private TrabalRuleItem errorBoundaryItem;

  public ShiftAllRule(TrabalLearner parentAlgorithm, TextRulerTarget target,
          TrabalAnnotation annotation, TrabalAnnotation targetAnnotation,
          AnnotationErrorType errorType) {
    super(parentAlgorithm, target, annotation, targetAnnotation, errorType);
  }

  public ShiftAllRule(ShiftAllRule copyFrom) {
    super(copyFrom);
    this.errorBoundaryItem = copyFrom.errorBoundaryItem;
  }

  @Override
  public void compileRuleString() {
    ruleString = "";

    // shift to left or right?
    boolean shiftToLeft = errorType == AnnotationErrorType.SHIFTING_LEFT;

    // should the boundary items be part of the new annotation?
    boolean frontItemInBorders = frontBoundaryItem.getAnnotation().getBegin() == targetAnnotation
            .getBegin();
    boolean rearItemInBorders = rearBoundaryItem.getAnnotation().getEnd() == targetAnnotation
            .getEnd();

    // this string will be attached to the rear boundary item
    String before = parseConditions(ConditionType.BEFORE);
    if (before.length() > 0)
      before = "{" + before + "}";

    // the action string will be attached to the brackets, containing the conditions and mark action
    String conditions = parseConditions(conditionTypes);
    if (conditions.length() > 0)
      conditions += " ";
    String mark;
    if (((TrabalLearner) algorithm).getEnableFeatures())
      mark = "-> CREATE(" + targetAnnotation.getType().getShortName() + parseFeatures() + ")";
    else
      mark = "-> MARK(" + targetAnnotation.getType().getShortName() + ")";
    String action = "{" + conditions + mark + "}";

    if (errorBoundaryItem == null) {
      // We only have 2 boundary items (reduction rule, where only the right boundary changes)
      compileWithoutErrorRuleItem(frontItemInBorders, rearItemInBorders, before, action);
    } else {
      // this string will be attached to the front boundary item
      String after = parseConditions(ConditionType.AFTER);
      if (after.length() > 0)
        after = "{" + after + "}";

      // this part will delete the original annotation
      String unmark = errorBoundaryItem + (frontItemInBorders ? "" : " ANY") +"{STARTSWITH(" + annotation.getType().getShortName()
              + ") -> UNMARK(" + annotation.getType().getShortName() + ", true)}";

      if (shiftToLeft)
        compileShiftToLeft(frontItemInBorders, rearItemInBorders, before, after, action, unmark);
      else
        compileShiftToRight(frontItemInBorders, rearItemInBorders, before, after, action, unmark);
    }
    setNeedsCompile(false);
  }

  private void compileShiftToLeft(boolean frontItemInBorders, boolean rearItemInBorders,
          String before, String after, String action, String unmark) {
    // If the front item is part of the future annotation, it has to be included in the
    // brackets.
    if (frontItemInBorders)
      ruleString += "(" + frontBoundaryItem + after + " ";
    else
      ruleString += frontBoundaryItem + after + " (";

    // We include all tokens between the boundaries.
    ruleString += "ANY*{-PARTOF(" + errorBoundaryItem + ")} " + unmark + " ANY*{-PARTOF("
            + rearBoundaryItem + ")}"; // like ANY*? but faster
//    ruleString += "#{-CONTAINS(" + errorBoundaryItem + ")} " + unmark + " #{-CONTAINS("
//            + rearBoundaryItem + ")}"; // like ANY*? but faster
    
    
    // Check, if the rear item should be included and mark all tokens between the brackets as
    // the
    // new annotation.
    if (rearItemInBorders)
      ruleString += " " + rearBoundaryItem + before + ")" + action + ";";
    else
      ruleString += ")" + action + " " + rearBoundaryItem + before + ";";
  }

  private void compileShiftToRight(boolean frontItemInBorders, boolean rearItemInBorders,
          String before, String after, String action, String unmark) {
    // The old annotation begins before the new annotation
    ruleString += unmark + " ANY*{-PARTOF(" + frontBoundaryItem + ")} ";
//    ruleString += unmark + " #{-CONTAINS(" + frontBoundaryItem + ")} ";

    // If the front item is part of the future annotation, it has to be included in the
    // brackets.
    if (frontItemInBorders)
      ruleString += "(" + frontBoundaryItem + after + " ";
    else
      ruleString += frontBoundaryItem + after + " (";

    // We include all tokens between the boundaries.
    ruleString += "ANY*{-PARTOF(" + rearBoundaryItem + ")}"; // like ANY*? but faster
//    ruleString += "#{-CONTAINS(" + rearBoundaryItem + ")}"; // like ANY*? but faster

    
    
    // Check, if the rear item should be included and mark all tokens between the brackets as
    // the
    // new annotation.
    if (rearItemInBorders)
      ruleString += " " + rearBoundaryItem + before + ")" + action + ";";
    else
      ruleString += ")" + action + " " + rearBoundaryItem + before + ";";
  }

  private void compileWithoutErrorRuleItem(boolean frontItemInBorders, boolean rearItemInBorders,
          String before, String action) {
    // this string will be attached to the front boundary item
    String after = parseConditions(ConditionType.AFTER);
    if (after.length() > 0)
      after = ", " + after;

    // this string deletes the original annotation
    String unmark = "{STARTSWITH(" + annotation.getType().getShortName() + ")" + after
            + " -> UNMARK(" + annotation.getType().getShortName() + ", true)}";

    if (frontItemInBorders)
      ruleString += "(" + frontBoundaryItem + unmark + " ";
    else
      ruleString += frontBoundaryItem + unmark + " (";

    ruleString += "ANY*{-PARTOF(" + rearBoundaryItem + ")}"; // like ANY*? but faster
//    ruleString += "#{-CONTAINS(" + rearBoundaryItem + ")}"; // like ANY*? but faster

    // Check, if the rear item should be included and mark all tokens between the brackets as the
    // new annotation.
    if (rearItemInBorders)
      ruleString += " " + rearBoundaryItem + before + ")" + action + ";";
    else
      ruleString += ")" + action + " " + rearBoundaryItem + before + ";";
  }

  @Override
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
      if (errorBoundaryItem != null)
        if (errorBoundaryItem.getAnnotation().equals(target))
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

  /**
   * Sets the boundary item that marks the beginning of the original annotation.
   * 
   * @param errorBoundaryItem
   */
  public void setErrorBoundaryItem(TrabalRuleItem errorBoundaryItem) {
    this.errorBoundaryItem = errorBoundaryItem;
  }

  @Override
  public TrabalRule copy() {
    return new ShiftAllRule(this);
  }

}
