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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.textmarker.textruler.core.TextRulerAnnotation;
import org.apache.uima.textmarker.textruler.core.TextRulerRule;
import org.apache.uima.textmarker.textruler.core.TextRulerRuleItem;
import org.apache.uima.textmarker.textruler.core.TextRulerWordConstraint;

public class WhiskRuleItem implements TextRulerRuleItem {

  private TextRulerWordConstraint wordConstraint;

  private boolean isStarWildCard = false;

  private int termNumberInExample = -1;

  private boolean hideRegExp = false;

  protected List<MLWhiskOtherConstraint> otherConstraints = new ArrayList<MLWhiskOtherConstraint>();

  public static class MLWhiskOtherConstraint {

    TextRulerAnnotation tokenAnnotation;

    TextRulerAnnotation constraintAnnotation;

    boolean canBeAnchor;

    Type type;

    public MLWhiskOtherConstraint(TextRulerAnnotation tokenAnnotation,
            TextRulerAnnotation constraintAnnotation) {
      this.tokenAnnotation = tokenAnnotation;
      this.constraintAnnotation = constraintAnnotation;
      this.type = constraintAnnotation.getType();
      canBeAnchor = (tokenAnnotation.getBegin() == constraintAnnotation.getBegin())
              && (tokenAnnotation.getEnd() == constraintAnnotation.getEnd());
      // TODO is the matching END also a requirement ?
    }

    public boolean isTMBasicTypeTokenConstraint() {
      return tokenAnnotation == constraintAnnotation;
    }

    public boolean canBeAnchorConstraint() {
      return canBeAnchor;
    }

    @Override
    public boolean equals(Object o) {
      MLWhiskOtherConstraint co = (MLWhiskOtherConstraint) o;

      return toString().equals(co.toString()) && (canBeAnchor == co.canBeAnchor);
    }

    @Override
    public int hashCode() {
      return toString().hashCode() * (canBeAnchor ? 2 : 1);
    }

    @Override
    public String toString() {
      return type.getShortName();
    }

    public MLWhiskOtherConstraint copy() {
      return new MLWhiskOtherConstraint(tokenAnnotation, constraintAnnotation);
    }

  }

  public WhiskRuleItem() {
    super();
    wordConstraint = null;
    termNumberInExample = -1;
  }

  public static WhiskRuleItem newWildCardItem(int startTermNumber) {
    WhiskRuleItem i = new WhiskRuleItem();
    i.setIsStarWildCard(true);
    i.setTermNumberInExample(startTermNumber);
    return i;
  }

  public WhiskRuleItem(WhiskRuleItem copyFrom) {
    super();
    if (copyFrom.wordConstraint != null)
      wordConstraint = copyFrom.wordConstraint.copy();
    isStarWildCard = copyFrom.isStarWildCard;
    termNumberInExample = copyFrom.termNumberInExample;
    hideRegExp = copyFrom.hideRegExp;
    for (MLWhiskOtherConstraint c : copyFrom.otherConstraints)
      otherConstraints.add(c.copy());
  }

  public WhiskRuleItem(TextRulerAnnotation tokenAnnotation) {
    super();
    setWordConstraint(new TextRulerWordConstraint(tokenAnnotation));
  }

  public void setWordConstraint(TextRulerWordConstraint c) {
    wordConstraint = c;
  }

  public TextRulerWordConstraint getWordConstraint() {
    return wordConstraint;
  }

  public TextRulerRuleItem copy() {
    return new WhiskRuleItem(this);
  }

  public String getStringForRuleString(TextRulerRule rule, MLRuleItemType type,
          int numberInPattern, int patternSize, int numberInRule, int ruleSize, int slotIndex) {

    String result = "";
    WhiskRule whiskRule = (WhiskRule) rule;
    boolean isMarkingItem = type == MLRuleItemType.FILLER && numberInPattern == 0;
    ArrayList<String> constraints = new ArrayList<String>();

    String anchor = null;

    if (wordConstraint != null) {
      if (wordConstraint.isRegExpConstraint()) {
        anchor = wordConstraint.typeShortName();
        if (!hideRegExp)
          constraints.add("REGEXP(\"" + wordConstraint + "\")");
      } else
        anchor = wordConstraint.toString();
    }

    MLWhiskOtherConstraint anchorConstraint = null;
    if (anchor == null) {
      for (MLWhiskOtherConstraint c : otherConstraints)
        if (c.canBeAnchorConstraint()) {
          anchorConstraint = c;
          break;
        }
    }

    for (MLWhiskOtherConstraint oc : otherConstraints) {
      if (oc != anchorConstraint) {
        if (oc.canBeAnchorConstraint())
          constraints.add("IS(" + oc + ")");
        else
          constraints.add("PARTOF(" + oc + ")");
      }
    }
    if (anchor == null) {
      if (anchorConstraint != null)
        anchor = anchorConstraint.toString();
      else
        anchor = "ALL";
    }

    if (constraints.size() > 0) {
      String cStr = "";
      for (String constraintStr : constraints) {
        if (cStr.length() > 0)
          cStr += ", ";
        cStr += constraintStr;
      }
      result += "{" + cStr;
      if (!isMarkingItem)
        result += "}";
    }

    if (isMarkingItem) {
      if (constraints.size() == 0)
        result += "{";
      result += "->MARKONCE(" + whiskRule.getMarkName(slotIndex);
      if (patternSize > 1)
        result += ", " + (numberInRule + 1) + ", " + (numberInRule + patternSize);
      result += ")}";
    }
    if (isStarWildCard)
      anchor += "*?";
    return anchor + result;
  }

  public void setIsStarWildCard(boolean flag) {
    isStarWildCard = flag;
  }

  public boolean isStarWildCard() {
    return isStarWildCard;
  }

  public void setTermNumberInExample(int i) {
    termNumberInExample = i;
  }

  public int getTermNumberInExample() {
    return termNumberInExample;
  }

  public boolean equals(TextRulerRuleItem o) {
    WhiskRuleItem it = (WhiskRuleItem) o;
    if (wordConstraint != null)
      if (!wordConstraint.equals(it.wordConstraint))
        return false;

    return isStarWildCard == it.isStarWildCard && termNumberInExample == it.termNumberInExample;
  }

  @Override
  public String toString() {
    return getStringForRuleString(null, null, 0, 0, 0, 0, 0);
  }

  public void setHideRegExp(boolean flag) {
    hideRegExp = flag;
  }

  public void addOtherConstraint(MLWhiskOtherConstraint c) {
    if (!otherConstraints.contains(c))
      otherConstraints.add(c);
  }

  public List<MLWhiskOtherConstraint> getOtherConstraints() {
    return otherConstraints;
  }

  public int constraintPoints() {
    int result = 0;
    if (wordConstraint != null)
      result += hideRegExp ? 1 : 3; // a regexp constraint is less general
    // so point it bad here!
    result += otherConstraints.size();
    return result;
  }

}
