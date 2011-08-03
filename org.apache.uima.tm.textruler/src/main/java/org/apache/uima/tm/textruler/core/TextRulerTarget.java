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

package org.apache.uima.tm.textruler.core;

/**
 * 
 * TextRulerTarget encapsulates a learning target of an ML-algorithm. It currently can be
 * 
 * * multi slot (full slots, not boundary!) * single slot (full slot) * single boundary (single slot
 * left or right boundary)
 * 
 * An TextRulerRule for example is induced for an TextRulerTarget or a testCAS for a given
 * TextRulerExampleDocument is created especially for a given target.
 * 
 */
public class TextRulerTarget {

  public enum MLTargetType {
    MULTI_SLOT, SINGLE_WHOLE_SLOT, SINGLE_LEFT_BOUNDARY, SINGLE_RIGHT_BOUNDARY, SINGLE_LEFT_CORRECTION, SINGLE_RIGHT_CORRECTION
  };

  public String slotNames[];

  public MLTargetType type;

  private TextRulerBasicLearner learner;

  private int maxShiftDistance = 0;

  // copy constructor
  public TextRulerTarget(TextRulerTarget copyFrom, TextRulerBasicLearner owner) {
    this.slotNames = copyFrom.slotNames.clone();
    this.type = copyFrom.type;
    this.learner = owner;
  }

  public TextRulerTarget(String slotNames[], TextRulerBasicLearner owner) {
    this.slotNames = slotNames;
    type = MLTargetType.MULTI_SLOT;
    this.learner = owner;
  }

  public TextRulerTarget(String slotName, TextRulerBasicLearner owner) {
    slotNames = new String[1];
    slotNames[0] = slotName;
    type = MLTargetType.SINGLE_WHOLE_SLOT;
    this.learner = owner;
  }

  public TextRulerBasicLearner getLearner() {
    return learner;
  }

  public TextRulerTarget(String slotName, MLTargetType type, TextRulerBasicLearner owner) {
    slotNames = new String[1];
    slotNames[0] = slotName;
    this.type = type;
    this.learner = owner;
  }

  public String getMultiSlotTypeName(int slotIndex) {
    return slotNames[slotIndex];
  }

  public String getSingleSlotRawTypeName() {
    return slotNames[0];
  }

  public String getSingleSlotTypeName() {
    return getSingleSlotTypeName(type, slotNames[0]);
    // if (type == MLTargetType.MULTI_SLOT)
    // return null;
    // if (type == MLTargetType.SINGLE_LEFT_BOUNDARY)
    // return slotNames[0]+TextRulerToolkit.LEFT_BOUNDARY_EXTENSION;
    // else if (type == MLTargetType.SINGLE_RIGHT_BOUNDARY)
    // return slotNames[0]+TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION;
    // else
    // return slotNames[0];
  }

  public static String getSingleSlotTypeName(MLTargetType t, String slotName) {
    if (t == MLTargetType.MULTI_SLOT)
      return null;
    if (t == MLTargetType.SINGLE_LEFT_BOUNDARY)
      return slotName + TextRulerToolkit.LEFT_BOUNDARY_EXTENSION;
    else if (t == MLTargetType.SINGLE_RIGHT_BOUNDARY)
      return slotName + TextRulerToolkit.RIGHT_BOUNDARY_EXTENSION;
    else
      return slotName;
  }

  public String[] getRawSlotNames() {
    return slotNames;
  }

  public String[] getSlotTypeNames() {
    if (type == MLTargetType.MULTI_SLOT)
      return slotNames;
    else {
      String result[] = new String[1];
      result[0] = getSingleSlotTypeName();
      return result;
    }
  }

  public boolean isMultiSlot() {
    return type == MLTargetType.MULTI_SLOT;
  }

  public boolean isBoundary() {
    return type == MLTargetType.SINGLE_LEFT_BOUNDARY || type == MLTargetType.SINGLE_RIGHT_BOUNDARY;
  }

  public boolean isLeftBoundary() {
    return type == MLTargetType.SINGLE_LEFT_BOUNDARY;
  }

  public boolean isRightBoundary() {
    return type == MLTargetType.SINGLE_RIGHT_BOUNDARY;
  }

  public boolean isLeftCorrection() {
    return type == MLTargetType.SINGLE_LEFT_CORRECTION;
  }

  public boolean isRightCorrection() {
    return type == MLTargetType.SINGLE_RIGHT_CORRECTION;
  }

  public TextRulerTarget getCounterPartBoundaryTarget() {
    if (!isBoundary())
      return null;
    else if (type == MLTargetType.SINGLE_LEFT_BOUNDARY)
      return new TextRulerTarget(slotNames[0], MLTargetType.SINGLE_RIGHT_BOUNDARY, learner);
    else
      return new TextRulerTarget(slotNames[0], MLTargetType.SINGLE_LEFT_BOUNDARY, learner);
  }

  public int getMaxShiftDistance() {
    return maxShiftDistance;
  }

  public void setMaxShiftDistance(int maxShiftDistance) {
    this.maxShiftDistance = maxShiftDistance;
  }

  @Override
  public boolean equals(Object o) {
    TextRulerTarget t = (TextRulerTarget) o;
    if (t.slotNames.length != slotNames.length)
      return false;
    for (int i = 0; i < slotNames.length; i++)
      if (!slotNames[i].equals(t.slotNames[i]))
        return false;
    return type == t.type;
  }

  @Override
  public int hashCode() {
    return slotNames.hashCode() * type.hashCode();
  }
}
