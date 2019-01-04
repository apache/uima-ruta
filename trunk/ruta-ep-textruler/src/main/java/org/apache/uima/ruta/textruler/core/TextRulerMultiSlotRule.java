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

package org.apache.uima.ruta.textruler.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.textruler.core.TextRulerRuleItem.MLRuleItemType;


/**
 * 
 * TextRulerMultiSlotRule adds multi-slot specific stuff to the basic class TextRulerRule.
 * 
 * A multi-slot-rule consists of an TextRulerSlotPattern for each slot which of each consists of
 * three patterns: prefiller, filler an postfiller (see TextRulerSlotPattern).
 * 
 */
public class TextRulerMultiSlotRule extends TextRulerRule {

  protected List<TextRulerSlotPattern> slotPatterns = new ArrayList<TextRulerSlotPattern>();

  public TextRulerMultiSlotRule(TextRulerMultiSlotRule copyFrom) {
    super(copyFrom);

    for (TextRulerSlotPattern origP : copyFrom.slotPatterns)
      slotPatterns.add(origP.copy());
  }

  public String getMarkName(int slotIndex) {
    return TextRulerToolkit.getTypeShortName(target.getMultiSlotTypeName(slotIndex));
  }

  public TextRulerMultiSlotRule(TextRulerBasicLearner parentAlgorithm, TextRulerTarget target) {
    super(parentAlgorithm, target);
  }

  protected String getInterslotWildCard() {
    return "# ";
  }

  @Override
  public void compileRuleString() {
    String ruleString = "";

    int totalSize = 0;
    int totalIndex = 0;
    int interSlotWildcards = slotPatterns.size() - 1;
    if (interSlotWildcards < 0)
      interSlotWildcards = 0;
    for (TextRulerSlotPattern sp : slotPatterns) {
      totalSize += sp.preFillerPattern.size();
      totalSize += sp.fillerPattern.size();
      totalSize += sp.postFillerPattern.size();
    }
    totalSize += interSlotWildcards;

    for (int slotIndex = 0; slotIndex < slotPatterns.size(); slotIndex++) {
      TextRulerSlotPattern sPattern = slotPatterns.get(slotIndex);
      int index = 0;
      for (TextRulerRuleItem item : sPattern.preFillerPattern) {
        ruleString += item.getStringForRuleString(this, MLRuleItemType.PREFILLER, index,
                sPattern.preFillerPattern.size(), totalIndex, totalSize, slotIndex)
                + " ";
        index++;
        totalIndex++;
      }
      index = 0;
      for (TextRulerRuleItem item : sPattern.fillerPattern) {
        ruleString += item.getStringForRuleString(this, MLRuleItemType.FILLER, index,
                sPattern.fillerPattern.size(), totalIndex, totalSize, slotIndex)
                + " ";
        index++;
        totalIndex++;
      }

      index = 0;
      for (TextRulerRuleItem item : sPattern.postFillerPattern) {
        ruleString += item.getStringForRuleString(this, MLRuleItemType.POSTFILLER, index,
                sPattern.postFillerPattern.size(), totalIndex, totalSize, slotIndex)
                + " ";
        index++;
        totalIndex++;
      }

      if (slotPatterns.size() > 1 && slotIndex < slotPatterns.size() - 1) {
        // add interslot wildcard:
        ruleString += getInterslotWildCard();
        totalIndex++;
      }
    }

    ruleString = ruleString.trim();
    ruleString += ";";
    this.ruleString = ruleString;
    setNeedsCompile(false);
  }

  public List<TextRulerSlotPattern> getPatterns() {
    return slotPatterns;
  }

  @Override
  public TextRulerMultiSlotRule copy() {
    return new TextRulerMultiSlotRule(this);
  }

}
