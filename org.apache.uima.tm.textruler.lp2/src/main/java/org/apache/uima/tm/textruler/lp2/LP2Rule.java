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

package org.apache.uima.tm.textruler.lp2;

import org.apache.uima.tm.textruler.core.TextRulerBasicLearner;
import org.apache.uima.tm.textruler.core.TextRulerRuleItem;
import org.apache.uima.tm.textruler.core.TextRulerSingleSlotRule;
import org.apache.uima.tm.textruler.core.TextRulerStatisticsCollector;
import org.apache.uima.tm.textruler.core.TextRulerTarget;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.tm.textruler.core.TextRulerTarget.MLTargetType;

public class LP2Rule extends TextRulerSingleSlotRule {

  protected float errorRate;

  protected boolean setIsPreFillerStartRule = false;

  protected boolean isContextualRule = false;

  public LP2Rule(TextRulerBasicLearner parentAlgorithm, TextRulerTarget target) {
    super(parentAlgorithm, target);

  }

  // copy constructor:
  protected LP2Rule(LP2Rule copyFrom) {
    super(copyFrom);
    errorRate = copyFrom.errorRate;
    setIsPreFillerStartRule = copyFrom.setIsPreFillerStartRule;
    isContextualRule = copyFrom.isContextualRule;
  }

  @Override
  public LP2Rule copy() {
    return new LP2Rule(this);
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
      errorRate = Float.MAX_VALUE;
    } else {
      errorRate = ((float) n) / ((float) p);
    }
  }

  public float getErrorRate() {
    return errorRate;
  }

  public int totalConstraintCount() {
    int result = 0;
    // every item itself counts 1 (so a wildcard "ANY" item counts also as a
    // constraint, since it says: HERE HAS TO BE A TOKEN!)
    // and every constraint on that token also counts 1.
    for (TextRulerRuleItem i : slotPattern.preFillerPattern)
      result += 1 + ((LP2RuleItem) i).totalConstraintCount();
    for (TextRulerRuleItem i : slotPattern.postFillerPattern)
      result += 1 + ((LP2RuleItem) i).totalConstraintCount();
    return result;
  }

  public int totalInnerConstraintCount() // counts only the constraints in the
  // items, but does not count the
  // items as a constraint themselves!
  {
    int result = 0;
    for (TextRulerRuleItem i : slotPattern.preFillerPattern)
      result += ((LP2RuleItem) i).totalConstraintCount();
    for (TextRulerRuleItem i : slotPattern.postFillerPattern)
      result += ((LP2RuleItem) i).totalConstraintCount();
    return result;
  }

  public boolean isPreFillerStartRule() {
    return setIsPreFillerStartRule;
  }

  public void setIsPreFillerStartRule(boolean flag) {
    setIsPreFillerStartRule = flag;
  }

  public LP2RuleItem getMarkingRuleItem() {
    if (target.type == MLTargetType.SINGLE_LEFT_BOUNDARY)
      return (LP2RuleItem) slotPattern.postFillerPattern.get(0);
    else
      return (LP2RuleItem) slotPattern.preFillerPattern
              .get(slotPattern.preFillerPattern.size() - 1);
  }

  public boolean isContextualRule() {
    return isContextualRule;
  }

  public void setIsContextualRule(boolean flag) {
    if (flag != isContextualRule) {
      isContextualRule = flag;
      setNeedsCompile(true);
    }
  }

}
