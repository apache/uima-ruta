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

import java.util.List;

import org.apache.uima.ruta.textruler.core.TextRulerTarget;

public class ExpansionRule extends ShiftingRule {

  public ExpansionRule(TrabalLearner parentAlgorithm, TextRulerTarget target,
          TrabalAnnotation annotation, TrabalAnnotation targetAnnotation,
          AnnotationErrorType errorType) {
    super(parentAlgorithm, target, annotation, targetAnnotation, errorType);
  }

  public ExpansionRule(ExpansionRule copyFrom) {
    super(copyFrom);
  }

  @Override
  public void compileRuleString() {
    ruleString = "";

    int errorBegin = annotation.getBegin();
    int truthBegin = targetAnnotation.getBegin();
    int truthEnd = targetAnnotation.getEnd();
    boolean expandToLeft = truthBegin < errorBegin;

    if (expandToLeft) // (frontBoundaryItem ANY*? annotation){-> MARK(targetAnnotation)};
      expandToLeft(truthBegin);
    else
      // (annotation ANY*? rearBoundaryItem){-> MARK(targetAnnotation)};
      expandToRight(truthEnd);
    setNeedsCompile(false);
  }

  private void expandToLeft(int truthBegin) {
    // should the boundary item be part of the new annotation?
    boolean frontItemInBorders = frontBoundaryItem.getAnnotation().getBegin() == truthBegin;

    // parse the positive conditions
    String conditions = parseConditions(conditionTypes, true);
    if (conditions.length() > 0)
      conditions += " ";

    // parse the negative conditions
    conditionTypes.add(ConditionType.BEFORE);
    String negativeConditions = parseConditions(conditionTypes, false);
    if (negativeConditions.length() > 0)
      negativeConditions += " ";

    String before = parseConditions(ConditionType.BEFORE, true);
    if (before.length() > 0 && negativeConditions.length() > 0)
      negativeConditions = before + ", " + negativeConditions;
    else if (before.length() > 0)
      negativeConditions = before + " ";

    // this strings will be attached to the original annotation
    String unmark = "{" + negativeConditions + "-> UNMARK(" + annotation.getType().getShortName()
            + ")}";

    // this string will be attached to the front boundary item
    String after = parseConditions(ConditionType.AFTER);
    if (after.length() > 0)
      after = "{" + after + "}";

    // If the front item is part of the future annotation, it has to be included in the brackets.
    if (frontItemInBorders)
      ruleString += "(" + frontBoundaryItem + after + " ";
    else
      ruleString += frontBoundaryItem + after + " (";

    // We include all tokens between the boundaries.
    ruleString += "ANY*{-PARTOF(" + annotation.getType().getShortName() + ")} "; // like ANY*? but
    // faster

    // these strings will be attached to the brackets
    String mark;
    if (((TrabalLearner) algorithm).getEnableFeatures())
      mark = "-> CREATE(" + targetAnnotation.getType().getShortName() + parseFeatures() + ")";
    else
      mark = "-> MARK(" + targetAnnotation.getType().getShortName() + ")";
    String action = "{" + conditions + mark + "}";

    // The original annotation represents the rear boundary item
    ruleString += annotation.getType().getShortName() + unmark + ")" + action + ";";
  }

  private void expandToRight(int truthEnd) {
    // should the boundary items be part of the new annotation?
    boolean rearItemInBorders = rearBoundaryItem.getAnnotation().getEnd() == truthEnd;

    // parse the positive conditions
    String conditions = parseConditions(conditionTypes, true);
    if (conditions.length() > 0)
      conditions += " ";

    // parse the negative conditions
    conditionTypes.add(ConditionType.AFTER);
    String negativeConditions = parseConditions(conditionTypes, false);
    if (negativeConditions.length() > 0)
      negativeConditions += " ";

    String after = parseConditions(ConditionType.AFTER, true);
    if (after.length() > 0 && negativeConditions.length() > 0)
      negativeConditions = after + ", " + negativeConditions;
    else if (after.length() > 0)
      negativeConditions = after + " ";

    // this strings will be attached to the original annotation
    String unmark = "{" + negativeConditions + "-> UNMARK(" + annotation.getType().getShortName()
            + ")}";

    // The original annotation represents the front boundary item
    ruleString += "(" + annotation.getType().getShortName() + unmark + " ";

    // We include all tokens between the boundaries.
    ruleString += "ANY*{-PARTOF(" + rearBoundaryItem + ")}"; // like ANY*? but faster

    // these strings will be attached to the brackets
    String mark;
    if (((TrabalLearner) algorithm).getEnableFeatures())
      mark = "-> CREATE(" + targetAnnotation.getType().getShortName() + parseFeatures() + ")";
    else
      mark = "-> MARK(" + targetAnnotation.getType().getShortName() + ")";
    String action = "{" + conditions + mark + "}";

    // this string will be attached to the rear boundary item
    String before = parseConditions(ConditionType.BEFORE);
    if (before.length() > 0)
      before = "{" + before + "}";

    // If the rear item is part of the future annotation, it has to be included in the brackets
    if (rearItemInBorders)
      ruleString += " " + rearBoundaryItem + before + ")" + action + ";";
    else
      ruleString += ")" + action + " " + rearBoundaryItem + before + ";";
  }

  /**
   * Parse either the positive or the negative conditions without negation mark.
   * 
   * @param types
   *          the types, that should be parsed
   * @param positive
   *          true for positive, false for negative conditions
   * @return String with parsed conditions
   */
  private String parseConditions(List<ConditionType> types, boolean positive) {
    String result = "";
    for (Condition each : conditions)
      if (types.contains(each.getType()) && each.isNegative() != positive)
        if (each.isNegative()) {
          each.changePosNegValue();
          result += each + ", ";
          each.changePosNegValue();
        } else
          result += each + ", ";
    if (result.length() > 0)
      return result.substring(0, result.length() - 2);
    return "";
  }

  /**
   * Parse either the positive or the negative conditions without negation mark.
   * 
   * @param type
   *          the type, that should be parsed
   * @param positive
   *          true for positive, false for negative conditions
   * @return String with parsed conditions
   */
  private String parseConditions(ConditionType type, boolean positive) {
    String result = "";
    for (Condition each : conditions)
      if (each.getType() == type && each.isNegative() != positive)
        if (each.isNegative()) {
          each.changePosNegValue();
          result += each + ", ";
          each.changePosNegValue();
        } else
          result += each + ", ";
    if (result.length() > 0)
      return result.substring(0, result.length() - 2);
    return "";
  }

  @Override
  public TrabalRule copy() {
    return new ExpansionRule(this);
  }

}
