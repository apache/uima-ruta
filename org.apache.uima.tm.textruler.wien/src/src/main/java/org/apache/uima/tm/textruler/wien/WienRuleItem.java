package org.apache.uima.tm.textruler.wien;

import java.util.ArrayList;

import org.apache.uima.tm.textruler.core.TextRulerAnnotation;
import org.apache.uima.tm.textruler.core.TextRulerMultiSlotRule;
import org.apache.uima.tm.textruler.core.TextRulerRule;
import org.apache.uima.tm.textruler.core.TextRulerRuleItem;

public class WienRuleItem implements TextRulerRuleItem {

  WienWordConstraint wordConstraint;

  ArrayList<String> conditions = new ArrayList<String>();

  ArrayList<String> actions = new ArrayList<String>();

  public WienRuleItem(WienRuleItem copyFrom) {
    super();
    if (copyFrom.wordConstraint != null)
      wordConstraint = copyFrom.wordConstraint.copy();
  }

  public WienRuleItem(TextRulerAnnotation tokenAnnotation, boolean generalizeLinkMarkUp) {
    super();
    if (tokenAnnotation != null)
      setWordConstraint(tokenAnnotation, generalizeLinkMarkUp);
  }

  public WienRuleItem(TextRulerAnnotation tokenAnnotation) {
    super();
    if (tokenAnnotation != null)
      setWordConstraint(tokenAnnotation);
  }

  public WienRuleItem copy() {
    return new WienRuleItem(this);
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  public boolean equals(TextRulerRuleItem o) {
    return toString().equals(((WienRuleItem) o).toString());
  }

  @Override
  public String toString() {
    return getStringForRuleString(null, null, 0, 0, 0, 0, 0);
  }

  public void addAction(String s) {
    actions.add(s);
  }

  public void addCondition(String s) {
    conditions.add(s);
  }

  public String getStringForRuleString(TextRulerRule rule, MLRuleItemType type,
          int numberInPattern, int patternSize, int numberInRule, int ruleSize, int slotIndex) {

    ArrayList<String> theConditions = new ArrayList<String>();
    String anchor = null;
    if (wordConstraint != null) {
      if (wordConstraint.isRegExpConstraint()) {
        anchor = wordConstraint.typeShortName();
        theConditions.add("REGEXP(\"" + wordConstraint + "\")");
      } else
        anchor = wordConstraint.toString();
    } else
      anchor = "ALL*?";

    theConditions.addAll(conditions);
    String cStr = "";
    if (theConditions.size() > 0) {
      for (String c : theConditions) {
        if (cStr.length() > 0)
          cStr += ", ";
        cStr += c;
      }
      cStr = "{" + cStr;
    }

    ArrayList<String> theActions = new ArrayList<String>();
    if (type == MLRuleItemType.FILLER)
      theActions.add("MARKONCE(" + ((TextRulerMultiSlotRule) rule).getMarkName(slotIndex) + ")");
    // theActions.add("MARK, "+((TextRulerMultiSlotRule)rule).getMarkName(slotIndex)+", "+(numberInRule+1));
    theActions.addAll(actions);
    String aStr = "";
    if (theActions.size() > 0) {
      for (String a : theActions) {
        if (aStr.length() > 0)
          aStr += ", ";
        aStr += a;
      }
      if (theConditions.size() == 0)
        aStr = "{->" + aStr + "}";
      else
        aStr = "->" + aStr + "}";
    } else if (theConditions.size() != 0)
      aStr = "}";

    return anchor + cStr + aStr;
  }

  public void setWordConstraint(TextRulerAnnotation tokenAnnotation, boolean generalizeLinkMarkUp) {
    WienWordConstraint c = new WienWordConstraint(tokenAnnotation);
    c.setGeneralizeLinkMarkUp(generalizeLinkMarkUp);
    setWordConstraint(c);
  }

  public void setWordConstraint(TextRulerAnnotation tokenAnnotation) {
    setWordConstraint(new WienWordConstraint(tokenAnnotation));
  }

  public void setWordConstraint(WienWordConstraint c) {
    wordConstraint = c;
  }

  public WienWordConstraint getWordConstraint() {
    return wordConstraint;
  }

}
