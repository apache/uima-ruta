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

package org.apache.uima.textmarker.textruler.learner.whisk.token;

import org.apache.uima.textmarker.textruler.core.TextRulerBasicLearner;
import org.apache.uima.textmarker.textruler.core.TextRulerExample;
import org.apache.uima.textmarker.textruler.core.TextRulerMultiSlotRule;
import org.apache.uima.textmarker.textruler.core.TextRulerRuleItem;
import org.apache.uima.textmarker.textruler.core.TextRulerRulePattern;
import org.apache.uima.textmarker.textruler.core.TextRulerSlotPattern;
import org.apache.uima.textmarker.textruler.core.TextRulerStatisticsCollector;
import org.apache.uima.textmarker.textruler.core.TextRulerTarget;
import org.apache.uima.textmarker.textruler.core.TextRulerToolkit;

public class WhiskRule extends TextRulerMultiSlotRule {

  TextRulerExample seedExample;

  public WhiskRule(WhiskRule copyFrom) {
    super(copyFrom);
    seedExample = copyFrom.seedExample;
  }

  public WhiskRule(TextRulerBasicLearner parentAlgorithm, TextRulerTarget target,
          TextRulerExample seedExample) {
    super(parentAlgorithm, target);
    this.seedExample = seedExample;
  }

  @Override
  public WhiskRule copy() {
    return new WhiskRule(this);
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

  public TextRulerExample getSeedExample() {
    return seedExample;
  }

  @Override
  public void setCoveringStatistics(TextRulerStatisticsCollector c) {
    super.setCoveringStatistics(c);
    if (TextRulerToolkit.DEBUG && c != null) {
      if (!c.getCoveredPositiveExamples().contains(seedExample)) {
        TextRulerToolkit.log("ERROR, A WHISK RULE MUST COVER AT LEAST ITS SEED EXAMPLE!");
        TextRulerToolkit.log("\tRULE: " + getRuleString());
      }
    }
  }

  public boolean containsTerm(WhiskRuleItem term) {
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

  public WhiskRuleItem searchItemWithTermNumber(int no) {
    for (TextRulerSlotPattern sp : slotPatterns) {
      for (TextRulerRuleItem i : sp.preFillerPattern) {
        if (((WhiskRuleItem) i).getTermNumberInExample() == no) {
          return (WhiskRuleItem) i;
        }
      }
      for (TextRulerRuleItem i : sp.fillerPattern) {
        if (((WhiskRuleItem) i).getTermNumberInExample() == no) {
          return (WhiskRuleItem) i;
        }
      }
      for (TextRulerRuleItem i : sp.postFillerPattern) {
        if (((WhiskRuleItem) i).getTermNumberInExample() == no) {
          return (WhiskRuleItem) i;
        }
      }
    }
    return null;
  }

  // TODO this could be moved to the core framework (TextRulerMultiSlotRule)
  public WhiskRuleItem searchNeighborOfItem(WhiskRuleItem item, boolean goToLeft) {
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
          return (WhiskRuleItem) currentPattern.get(startSearchFromIndex);
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
          return (WhiskRuleItem) currentPattern.get(startSearchFromIndex);
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

  public int totalConstraintPoints() {
    int result = 0;
    for (TextRulerSlotPattern sl : slotPatterns) {
      for (TextRulerRuleItem i : sl.preFillerPattern) {
        result += ((WhiskRuleItem) i).constraintPoints();
      }
      for (TextRulerRuleItem i : sl.fillerPattern) {
        result += ((WhiskRuleItem) i).constraintPoints();
      }
      for (TextRulerRuleItem i : sl.postFillerPattern) {
        result += ((WhiskRuleItem) i).constraintPoints();
      }
    }
    return result;
  }

}
