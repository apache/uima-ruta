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

package org.apache.uima.textmarker.textruler.core;

import org.apache.uima.textmarker.textruler.core.TextRulerRuleItem.MLRuleItemType;
import org.apache.uima.textmarker.textruler.core.TextRulerTarget.MLTargetType;

/**
 * 
 * TextRulerSingleSlotRule adds single-slot specific stuff to the basic class TextRulerRule.
 * 
 * A single-slot-rule consists of one TextRulerSlotPattern which each consists of three patterns:
 * prefiller, filler an postfiller (see TextRulerSlotPattern).
 * 
 */
public class TextRulerSingleSlotRule extends TextRulerRule {

  protected TextRulerSlotPattern slotPattern = new TextRulerSlotPattern();

  public TextRulerSingleSlotRule(TextRulerBasicLearner parentAlgorithm, TextRulerTarget target) {
    super(parentAlgorithm, target);
  }

  public TextRulerSingleSlotRule(TextRulerSingleSlotRule copyFrom) {
    super(copyFrom);
    slotPattern = copyFrom.slotPattern.copy();
  }

  public TextRulerRulePattern getPreFillerPattern() {
    return slotPattern.preFillerPattern;
  }

  public TextRulerRulePattern getFillerPattern() {
    return slotPattern.fillerPattern;
  }

  public TextRulerRulePattern getPostFillerPattern() {
    return slotPattern.postFillerPattern;
  }

  public String getMarkName() {
    return TextRulerToolkit.getTypeShortName(target.getSingleSlotTypeName());
  }

  @Override
  public void compileRuleString() {
    String ruleString = "";
    int preCount = slotPattern.preFillerPattern.size();
    int postCount = slotPattern.postFillerPattern.size();
    int fillerCount = slotPattern.fillerPattern.size();
    int totalSize = preCount + postCount + fillerCount;
    int index = 0;
    int totalIndex = 0;
    for (TextRulerRuleItem item : slotPattern.preFillerPattern) {
      ruleString += item.getStringForRuleString(this, MLRuleItemType.PREFILLER, index, preCount,
              totalIndex, totalSize, 0)
              + " ";
      index++;
      totalIndex++;
    }

    index = 0;
    for (TextRulerRuleItem item : slotPattern.fillerPattern) {
      ruleString += item.getStringForRuleString(this, MLRuleItemType.FILLER, index, fillerCount,
              totalIndex, totalSize, 0)
              + " ";
      index++;
      totalIndex++;
    }

    index = 0;
    for (TextRulerRuleItem item : slotPattern.postFillerPattern) {
      ruleString += item.getStringForRuleString(this, MLRuleItemType.POSTFILLER, index, postCount,
              totalIndex, totalSize, 0)
              + " ";
      index++;
      totalIndex++;
    }

    ruleString = ruleString.trim();
    ruleString += ";";
    this.ruleString = ruleString;
    setNeedsCompile(false);
  }

  public void addPreFillerItem(TextRulerRuleItem item) {
    slotPattern.preFillerPattern.add(0, item);
    setNeedsCompile(true);
  }

  public void addPreFillerItemWithNormalOrder(TextRulerRuleItem item) {
    slotPattern.preFillerPattern.add(item);
    setNeedsCompile(true);
  }

  public void addPostFillerItem(TextRulerRuleItem item) {
    slotPattern.postFillerPattern.add(item);
    setNeedsCompile(true);
  }

  public void addFillerItem(TextRulerRuleItem item) {
    if (target.type == MLTargetType.SINGLE_WHOLE_SLOT) {
      slotPattern.fillerPattern.add(item);
      setNeedsCompile(true);
    } else {
      new Exception("[TextRulerRule] BOUNDARY SLOT RULES CANNOT HAVE FILLER ITEMS!");
    }
  }

  public TextRulerRuleItem getOutermostPreFillerItem() {
    if (slotPattern.preFillerPattern.size() == 0)
      return null;
    else
      return slotPattern.preFillerPattern.get(0);
  }

  public TextRulerRuleItem getOutermostPostFillerItem() {
    if (slotPattern.postFillerPattern.size() == 0)
      return null;
    else
      return slotPattern.postFillerPattern.get(slotPattern.postFillerPattern.size() - 1);
  }

  public void removeOutermostPreFillerItem() {
    if (slotPattern.preFillerPattern.size() > 0) {
      slotPattern.preFillerPattern.remove(0);
      setNeedsCompile(true);
    }
  }

  public void removeOutermostPostFillerItem() {
    if (slotPattern.postFillerPattern.size() > 0) {
      slotPattern.postFillerPattern.remove(slotPattern.postFillerPattern.size() - 1);
      setNeedsCompile(true);
    }
  }

  public TextRulerRuleItem getRuleItemWithIndex(int index) {
    int i = index;
    if (i < slotPattern.preFillerPattern.size())
      return slotPattern.preFillerPattern.get(i);
    else
      i -= slotPattern.preFillerPattern.size();

    if (i < slotPattern.fillerPattern.size())
      return slotPattern.fillerPattern.get(i);
    else
      i -= slotPattern.fillerPattern.size();

    if (i < slotPattern.postFillerPattern.size())
      return slotPattern.postFillerPattern.get(i);
    else
      return null;
  }

  public int totalItemCount() {
    return slotPattern.preFillerPattern.size() + slotPattern.fillerPattern.size()
            + slotPattern.postFillerPattern.size();
  }

  @Override
  public TextRulerSingleSlotRule copy() {
    return new TextRulerSingleSlotRule(this);
  }

}
