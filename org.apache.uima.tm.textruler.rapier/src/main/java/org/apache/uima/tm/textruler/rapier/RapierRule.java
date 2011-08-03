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

package org.apache.uima.tm.textruler.rapier;

import java.util.ArrayList;

import org.apache.uima.tm.textruler.core.TextRulerBasicLearner;
import org.apache.uima.tm.textruler.core.TextRulerRuleItem;
import org.apache.uima.tm.textruler.core.TextRulerRulePattern;
import org.apache.uima.tm.textruler.core.TextRulerSingleSlotRule;
import org.apache.uima.tm.textruler.core.TextRulerStatisticsCollector;
import org.apache.uima.tm.textruler.core.TextRulerTarget;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;


public class RapierRule extends TextRulerSingleSlotRule {

  protected double priority = 0;

  protected RapierRule parent1 = null;

  protected RapierRule parent2 = null;

  protected int parent1PreFiller_n = 0;

  protected int parent2PreFiller_n = 0;

  protected int parent1PostFiller_n = 0;

  protected int parent2PostFiller_n = 0;

  // copy constructor:
  protected RapierRule(RapierRule copyFrom) {
    super(copyFrom);
    priority = copyFrom.priority;
    parent1 = copyFrom.parent1;
    parent2 = copyFrom.parent2;
    parent1PreFiller_n = copyFrom.parent1PreFiller_n;
    parent2PreFiller_n = copyFrom.parent2PreFiller_n;
    parent1PostFiller_n = copyFrom.parent1PostFiller_n;
    parent2PostFiller_n = copyFrom.parent2PostFiller_n;
  }

  @Override
  public RapierRule copy() {
    return new RapierRule(this);
  }

  public RapierRule(TextRulerBasicLearner parentAlgorithm, TextRulerTarget target) {
    super(parentAlgorithm, target);
  }

  public boolean producesOnlyValidFillers() {
    return coveringStatistics.getTotalCoveredExamples() > 0
            && coveringStatistics.getCoveredNegativesCount() == 0;
  }

  public double noiseValue() {
    int p = coveringStatistics.getCoveredPositivesCount();
    int n = coveringStatistics.getCoveredNegativesCount();
    return ((double) (p - n)) / ((double) (p + n)); // p-n/p+n in,
    // p=positive fillers,
    // n=spurious fillers
  }

  public static double log2(double z) {
    return Math.log(z) / Math.log(2.0);
  }

  @Override
  public void setCoveringStatistics(TextRulerStatisticsCollector c) {
    super.setCoveringStatistics(c);
    int p = c.getCoveredPositivesCount();
    int n = c.getCoveredNegativesCount();
    if (p < 1) {
      TextRulerToolkit.log("ERROR, A RULE MAY NOT COVER ZERO POSITIVE EXAMPLES! WHAT'S WRONG ?");
      TextRulerToolkit.log("\tRULE: " + getRuleString());
      // make sure this rule is rated totally bad:
      priority = Double.MAX_VALUE;

    } else {
      // priority = -(Math.log( ((double)(p+1)) / ((double)(p+n+2))) /
      // Math.log( 2 )) + (((double)this.calculateRuleSize()) /
      // ((double)p*100.0));
      priority = ((this.calculateRuleSize()) / ((double) p * 100))
              - log2(((double) (p + 1)) / ((double) (p + n + 2)));

      if (priority > 10000 && p > 0) {
        TextRulerToolkit.log("STRANGE RULE PRIORITY ! CHECK THIS!");
      }
    }
  }

  public double getPriority() {
    return priority;
  }

  public RapierRule getParent1() {
    return parent1;
  }

  public RapierRule getParent2() {
    return parent2;
  }

  public void setParent1(RapierRule p) {
    parent1 = p;
  }

  public void setParent2(RapierRule p) {
    parent2 = p;
  }

  public int getParent1PreFiller_n() {
    return this.parent1PreFiller_n;
  }

  public int getParent2PreFiller_n() {
    return this.parent2PreFiller_n;
  }

  public void setParent1PreFiller_n(int n) {
    this.parent1PreFiller_n = n;
  }

  public void setParent2PreFiller_n(int n) {
    this.parent2PreFiller_n = n;
  }

  public int getParent1PostFiller_n() {
    return this.parent1PostFiller_n;
  }

  public int getParent2PostFiller_n() {
    return this.parent2PostFiller_n;
  }

  public void setParent1PostFiller_n(int n) {
    this.parent1PostFiller_n = n;
  }

  public void setParent2PostFiller_n(int n) {
    this.parent2PostFiller_n = n;
  }

  public double calculateRuleSize() {
    double result = 0;
    for (TextRulerRuleItem ri : slotPattern.preFillerPattern)
      result += ((RapierRuleItem) ri).getRuleSizePoints();
    for (TextRulerRuleItem ri : slotPattern.fillerPattern)
      result += ((RapierRuleItem) ri).getRuleSizePoints();
    for (TextRulerRuleItem ri : slotPattern.postFillerPattern)
      result += ((RapierRuleItem) ri).getRuleSizePoints();
    return result / 100.0;
  }

  public boolean hasListItemAtBorder() {
    int cnt = totalItemCount();
    if (cnt == 0)
      return false;
    RapierRuleItem ri = (RapierRuleItem) getRuleItemWithIndex(0);
    if (ri.isListItem())
      return true;
    if (cnt <= 1)
      return false;
    ri = (RapierRuleItem) getRuleItemWithIndex(cnt - 1);
    return (ri.isListItem());
  }

  public ArrayList<RapierRule> createAllTestRules() {
    if (!hasListItemAtBorder())
      return null;
    ArrayList<RapierRule> result = new ArrayList<RapierRule>();

    RapierRule strippedRule = copy();

    int leftType = 0; // 0=none; 1=prefiller; 2=filler; 3=postfiller
    RapierRuleItem left = null;
    if (slotPattern.preFillerPattern.size() > 0) {
      leftType = 1;
      left = (RapierRuleItem) slotPattern.preFillerPattern.get(0);
      if (left.isListItem())
        strippedRule.getPreFillerPattern().remove(0);
    } else if (slotPattern.fillerPattern.size() > 0) {
      leftType = 2;
      left = (RapierRuleItem) slotPattern.fillerPattern.get(0);
      if (left.isListItem())
        strippedRule.getFillerPattern().remove(0);
    } else if (slotPattern.postFillerPattern.size() > 0) {
      leftType = 3;
      left = (RapierRuleItem) slotPattern.postFillerPattern.get(0);
      if (left.isListItem())
        strippedRule.getPostFillerPattern().remove(0);
    }

    if (left != null && !left.isListItem()) {
      left = null;
      leftType = 0;
    }

    int rightType = 0; // 0=none; 1=postfiller; 2=filler; 3=prefiller
    RapierRuleItem right = null;
    if (totalItemCount() > 1) {
      if (slotPattern.postFillerPattern.size() > 0) {
        rightType = 1;
        right = (RapierRuleItem) slotPattern.postFillerPattern.get(slotPattern.postFillerPattern
                .size() - 1);
        if (right.isListItem())
          strippedRule.getPostFillerPattern()
                  .remove(strippedRule.getPostFillerPattern().size() - 1);
      } else if (slotPattern.fillerPattern.size() > 0) {
        rightType = 2;
        right = (RapierRuleItem) slotPattern.fillerPattern
                .get(slotPattern.fillerPattern.size() - 1);
        if (right.isListItem())
          strippedRule.getFillerPattern().remove(strippedRule.getFillerPattern().size() - 1);
      } else if (slotPattern.postFillerPattern.size() > 0) {
        rightType = 3;
        right = (RapierRuleItem) slotPattern.preFillerPattern.get(slotPattern.preFillerPattern
                .size() - 1);
        if (right.isListItem())
          strippedRule.getPreFillerPattern().remove(strippedRule.getPreFillerPattern().size() - 1);
      }
    }
    if (right != null && !right.isListItem()) {
      right = null;
      rightType = 0;
    }
    if (left == null && right == null) {
      TextRulerToolkit.logIfDebug("HOW CAN THIS BE ?");
      return null;
    }
    int leftCount = left != null ? left.listLen() : 0;
    int rightCount = right != null ? right.listLen() : 0;

    int leftStart;
    if (leftCount > 0 && !left.listBeginsAtZero())
      leftStart = 1;
    else
      leftStart = 0;
    int rightStart;
    if (rightCount > 0 && !right.listBeginsAtZero())
      rightStart = 1;
    else
      rightStart = 0;

    for (int leftI = leftStart; leftI <= leftCount; leftI++)
      for (int rightI = rightStart; rightI <= rightCount; rightI++) {
        RapierRule newRule = strippedRule.copy();
        if (leftI > 0) {
          TextRulerRulePattern thePattern = null;
          if (leftType == 1)
            thePattern = newRule.getPreFillerPattern();
          else if (leftType == 2)
            thePattern = newRule.getFillerPattern();
          else if (leftType == 3)
            thePattern = newRule.getPostFillerPattern();
          for (int i = 0; i < leftI; i++) {
            RapierRuleItem theItem = left.copy();
            theItem.setListLen(0); // remove List-Character but add
            // listI copies instead!!
            thePattern.add(0, theItem);
          }
        }
        if (rightI > 0) {
          TextRulerRulePattern thePattern = null;
          if (rightType == 1)
            thePattern = newRule.getPostFillerPattern();
          else if (rightType == 2)
            thePattern = newRule.getFillerPattern();
          else if (rightType == 3)
            thePattern = newRule.getPreFillerPattern();
          for (int i = 0; i < rightI; i++) {
            RapierRuleItem theItem = right.copy();
            theItem.setListLen(0); // remove List-Character but add
            // listI copies instead!!
            thePattern.add(theItem);
          }
        }
        newRule.setNeedsCompile(true);
        if (newRule.totalItemCount() > 0) {
          // TextRulerToolkit.log(newRule.getRuleString());
          result.add(newRule);
        }
      }
    return result;
  }

  @Override
  protected String getRulesFileContent() {
    String theString;
    if (hasListItemAtBorder()) {
      ArrayList<RapierRule> rules = createAllTestRules();

      theString = "// " + getRuleString() + "\n\n";
      for (RapierRule r : rules)
        theString += r.getRuleString() + "\n";
    } else
      theString = getRuleString() + "\n";
    ;
    return algorithm.getTMFileHeaderString() + theString;
  }

  public boolean isInitialRule() {
    return parent1 == null || parent2 == null;
  }

  protected boolean compressFirstOccurenceOfSubsequentEqualPatternLists(TextRulerRulePattern p) {
    for (int i = 0; i < p.size() - 1; i++) {
      RapierRuleItem it1 = (RapierRuleItem) p.get(i);
      RapierRuleItem it2 = (RapierRuleItem) p.get(i + 1);
      if (it1.toStringAsNonPatternList().equals(it2.toStringAsNonPatternList())) {
        if (it1.isListItem() || it2.isListItem()) {
          boolean fromZero = it1.listBeginsAtZero() && it2.listBeginsAtZero();
          int listLen = (it1.isListItem() ? it1.listLen() : 1)
                  + (it2.isListItem() ? it2.listLen() : 1);
          it1.setListLen(listLen);
          it1.setListBeginsAtZero(fromZero);
          p.remove(i + 1); // remove i2 !
          return true;
        }
      }
    }
    return false;
  }

  public void combineSenselessPatternListItems() {
    boolean didCompress = false;
    // String old = new String(getRuleString());

    while (true) {
      boolean thisRoundCompressed = compressFirstOccurenceOfSubsequentEqualPatternLists(slotPattern.preFillerPattern);
      thisRoundCompressed |= compressFirstOccurenceOfSubsequentEqualPatternLists(slotPattern.fillerPattern);
      thisRoundCompressed |= compressFirstOccurenceOfSubsequentEqualPatternLists(slotPattern.postFillerPattern);
      didCompress |= thisRoundCompressed;
      if (!thisRoundCompressed)
        break;
    }

    if (didCompress) {
      setNeedsCompile(true);
      // TextRulerToolkit.log("old: "+old);
      // TextRulerToolkit.log("new: "+getRuleString());
    }
  }

}
