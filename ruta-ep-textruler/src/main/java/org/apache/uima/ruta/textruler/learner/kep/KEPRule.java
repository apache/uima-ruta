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

import org.apache.uima.ruta.textruler.core.TextRulerBasicLearner;
import org.apache.uima.ruta.textruler.core.TextRulerMultiSlotRule;
import org.apache.uima.ruta.textruler.core.TextRulerRuleItem;
import org.apache.uima.ruta.textruler.core.TextRulerRulePattern;
import org.apache.uima.ruta.textruler.core.TextRulerSlotPattern;
import org.apache.uima.ruta.textruler.core.TextRulerTarget;

public class KEPRule extends TextRulerMultiSlotRule {

  private boolean isCorrectionRule = false;

  public KEPRule(KEPRule copyFrom) {
    super(copyFrom);
    this.isCorrectionRule = copyFrom.isCorrectionRule;
  }

  public KEPRule(TextRulerBasicLearner parentAlgorithm, TextRulerTarget target) {
    super(parentAlgorithm, target);
    slotPatterns.add(new TextRulerSlotPattern());
  }

  @Override
  public KEPRule copy() {
    return new KEPRule(this);
  }

  public double getLaplacian() {
    int e = 0;
    int n = 0;

    if (coveringStatistics != null) {
      e = coveringStatistics.getCoveredNegativesCount();
      n = coveringStatistics.getCoveredNegativesCount()
              + coveringStatistics.getCoveredPositivesCount();
    }
    return ((double) e + 1) / ((double) n + 1);
  }

  public boolean containsTerm(KEPRuleItem term) {
    for (TextRulerSlotPattern sp : slotPatterns) {
      for (TextRulerRuleItem i : sp.preFillerPattern)
        if (i.equals(term))
          return true;
      for (TextRulerRuleItem i : sp.fillerPattern)
        if (i.equals(term))
          return true;
      for (TextRulerRuleItem i : sp.postFillerPattern)
        if (i.equals(term))
          return true;
    }
    return false;
  }

  public KEPRuleItem searchNeighborOfItem(KEPRuleItem item, boolean goToLeft) {
    int slotIndex = -1;
    int patternIndex = -1;
    int slotI = 0;
    for (TextRulerSlotPattern sp : slotPatterns) {
      for (TextRulerRuleItem it : sp.preFillerPattern) {
        if (it == item) {
          slotIndex = slotI;
          patternIndex = 0; // 0=preFiller
          break;
        }
      }
      if (slotIndex < 0) {
        for (TextRulerRuleItem it : sp.fillerPattern) {
          if (it == item) {
            slotIndex = slotI;
            patternIndex = 1; // 1=filler
            break;
          }
        }
      }
      if (slotIndex < 0) {
        for (TextRulerRuleItem it : sp.postFillerPattern) {
          if (it == item) {
            slotIndex = slotI;
            patternIndex = 2; // 2=postFiller
            break;
          }
        }
      }
      if (slotIndex >= 0) {
        break;
      }
    }
    if (slotIndex < 0) // we didn't even find the item in our rule ?! how
      // can this happen ?
      return null;

    TextRulerRulePattern currentPattern = getPattern(slotIndex, patternIndex);
    while (currentPattern != null) {
      int startIndex = currentPattern.indexOf(item); // this is only >= 0
      // for the first
      // pattern...
      if (!goToLeft) // walk forward...
      {
        int startSearchFromIndex = startIndex + 1;
        if (startSearchFromIndex < currentPattern.size())
          return (KEPRuleItem) currentPattern.get(startSearchFromIndex);
        else // skip to next pattern
        {
          patternIndex++;
          if (patternIndex > 2) {
            patternIndex = 0;
            slotIndex++;
            if (slotIndex >= slotPatterns.size())
              return null; // not found!
          }
          currentPattern = getPattern(slotIndex, patternIndex);
        }
      } else {
        int startSearchFromIndex = startIndex >= 0 ? startIndex - 1 : currentPattern.size() - 1;
        if (startSearchFromIndex >= 0 && currentPattern.size() > 0)
          return (KEPRuleItem) currentPattern.get(startSearchFromIndex);
        else // skip to previous pattern
        {
          patternIndex--;
          if (patternIndex < 0) {
            patternIndex = 2;
            slotIndex--;
            if (slotIndex < 0)
              return null; // not found!
          }
          currentPattern = getPattern(slotIndex, patternIndex);
        }
      }
    }
    return null;
  }

  private TextRulerRulePattern getPattern(int slotIndex, int patternIndex) {
    TextRulerSlotPattern sp = slotPatterns.get(slotIndex);
    if (patternIndex == 0)
      return sp.preFillerPattern;
    else if (patternIndex == 1)
      return sp.fillerPattern;
    else if (patternIndex == 2)
      return sp.postFillerPattern;
    else
      return null;
  }

  public KEPRule addPostFillerItem(KEPRuleItem item) {
    this.getPostFiller().add(item);
    setNeedsCompile(true);
    return this;
  }

  public KEPRule addInFillerItem(KEPRuleItem item) {
    this.getInFiller().add(item);
    setNeedsCompile(true);
    return this;
  }

  public KEPRule addPreFillerItem(KEPRuleItem item) {
    this.getPreFiller().add(0, item);
    setNeedsCompile(true);
    return this;
  }

  public TextRulerRulePattern getPreFiller() {
    return this.slotPatterns.get(0).preFillerPattern;
  }

  public void setPreFiller(TextRulerRulePattern preFiller) {
    this.slotPatterns.get(0).preFillerPattern = preFiller;
    setNeedsCompile(true);
  }

  public TextRulerRulePattern getInFiller() {
    return this.slotPatterns.get(0).fillerPattern;
  }

  public void setInFiller(TextRulerRulePattern inFiller) {
    this.slotPatterns.get(0).fillerPattern = inFiller;
    setNeedsCompile(true);
  }

  public TextRulerRulePattern getPostFiller() {
    return this.slotPatterns.get(0).postFillerPattern;
  }

  public void setPostFiller(TextRulerRulePattern postFiller) {
    this.slotPatterns.get(0).postFillerPattern = postFiller;
    setNeedsCompile(true);
  }

  public KEPRule setCorrectionRule(boolean isCorrectionRule) {
    this.isCorrectionRule = isCorrectionRule;
    setNeedsCompile(true);
    return this;
  }

  public boolean isCorrectionRule() {
    return isCorrectionRule;
  }

  public TextRulerTarget getTarget() {
    return this.target;
  }

  public void setTarget(TextRulerTarget target) {
    this.target = target;
    setNeedsCompile(true);
  }

  public boolean coversSameExamples(KEPRule otherRule) {
    if (otherRule.getCoveringStatistics().getCoveredPositivesCount() != this
            .getCoveringStatistics().getCoveredPositivesCount()
            || !otherRule.getCoveringStatistics().getCoveredPositiveExamples().containsAll(
                    this.getCoveringStatistics().getCoveredPositiveExamples()))
      return false;
    return true;
  }
}