package org.apache.uima.tm.textruler.rapier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.uima.tm.textruler.core.TextRulerRule;
import org.apache.uima.tm.textruler.core.TextRulerRuleItem;
import org.apache.uima.tm.textruler.core.TextRulerSingleSlotRule;
import org.apache.uima.tm.textruler.core.TextRulerWordConstraint;

public class RapierRuleItem implements TextRulerRuleItem {

  protected Set<TextRulerWordConstraint> words = new HashSet<TextRulerWordConstraint>(); // direct

  // word
  // match
  // constraint(s)
  protected Set<String> tags = new HashSet<String>(); // pos tag constraint(s)

  protected Set<String> classes = new HashSet<String>(); // semantic classes

  // constraint(s)
  protected int listLen = 0;

  protected boolean listBeginsAtZero = false; // special case for TM RAPIER

  // interpretation...

  public boolean equals(TextRulerRuleItem o) {
    return getStringForRuleString(null, null, 0, 1, 0, 1, 0).equals(
            o.getStringForRuleString(null, null, 0, 1, 0, 1, 0));
  }

  public String toStringAsNonPatternList() {
    int original = listLen;
    listLen = 0;
    String result = toString();
    listLen = original;
    return result;
  }

  public String getStringForRuleString(TextRulerRule rule, MLRuleItemType type,
          int numberInPattern, int patternSize, int numberInRule, int ruleSize, int slotIndex) {
    int listStart = listBeginsAtZero ? 0 : 1;
    String quantifierString = isListItem() ? "[" + listStart + "," + (listLen) + "]?" : "";
    String anchor = null;
    ArrayList<String> constraints = new ArrayList<String>();

    if (words.size() > 0) {
      ArrayList<TextRulerWordConstraint> regExpConstraints = new ArrayList<TextRulerWordConstraint>();
      ArrayList<TextRulerWordConstraint> tmTypeConstraints = new ArrayList<TextRulerWordConstraint>();
      for (TextRulerWordConstraint w : this.words) {
        if (w.isRegExpConstraint())
          regExpConstraints.add(w);
        else
          tmTypeConstraints.add(w);
      }
      int regExpCount = regExpConstraints.size();
      int tmCount = tmTypeConstraints.size();

      String regExpString = null;
      for (TextRulerWordConstraint w : regExpConstraints) {
        if (regExpString == null)
          regExpString = w.toString();
        else
          regExpString += "|" + w.toString();
      }
      if (regExpString != null) {
        regExpString = "REGEXP(\"" + regExpString + "\")";
      }

      String tmString = null;
      if (tmCount > 1 || (regExpCount > 0 && tmCount > 0)) {
        for (TextRulerWordConstraint w : tmTypeConstraints) {
          if (tmString == null)
            tmString = "IS(" + w.toString() + ")";
          else
            tmString += ",IS(" + w.toString() + ")";
        }
        String cString = "OR(" + tmString;
        if (regExpCount > 0) {
          if (tmCount > 0)
            cString += ",";
          cString += regExpString;
        }
        cString += ")";
        constraints.add(cString);
      } else { // tmCount can here be 0 or 1,
        // if it is 1, then regExpCount == 0
        // if it is 0, regExpCount can be anything
        if (tmCount == 1)
          anchor = tmTypeConstraints.get(0).toString(); // +quantifierString;
        else {
          if (regExpCount > 0)
            constraints.add(regExpString);
        }
      }
    }

    if (tags.size() > 0) {
      if (tags.size() == 1 && anchor == null)
        anchor = tags.iterator().next().toString();
      else {
        String tagsString = null;
        for (String w : this.tags) {
          if (tagsString == null)
            tagsString = "IS(" + w.toString() + ")";
          else
            tagsString += ",IS(" + w.toString() + ")";
        }
        tagsString = "OR(" + tagsString + ")";
        constraints.add(tagsString);
      }
    }

    if (classes.size() > 0) {
      if (classes.size() == 1 && anchor == null)
        anchor = classes.iterator().next().toString();
      else {
        String classesString = null;
        for (String w : this.classes) {
          if (classesString == null)
            classesString = "IS(" + w.toString() + ")";
          else
            classesString += ",IS(" + w.toString() + ")";
        }
        classesString = "OR(" + classesString + ")";
        constraints.add(classesString);
      }
    }

    if (anchor == null)
      anchor = "ALL";

    String result = anchor + quantifierString;

    if (constraints.size() > 0) {
      String cStr = null;
      for (String c : constraints) {
        if (cStr == null)
          cStr = c.toString();
        else
          cStr += "," + c.toString();
      }
      // TODO richtig?
      result += "{" + cStr;
    }

    if (type == MLRuleItemType.FILLER && (numberInPattern == 0)) {
      if (constraints.size() == 0)
        result += "{";
      result += "->MARKONCE(" + ((TextRulerSingleSlotRule) rule).getMarkName();
      if (patternSize > 1)
        result += ", " + (numberInRule + 1) + ", " + (numberInRule + patternSize);
      // for(int i=0;i < patternSize;i++) {
      // result += ","+(i+numberInRule+1);
      // }
      result += ")}";
    } else if (constraints.size() != 0)
      result += "}";
    return result;
  }

  public Set<TextRulerWordConstraint> getWordConstraints() {
    return words;
  }

  public Set<String> getTagConstraints() {
    return tags;
  }

  public Set<String> getClassConstraints() {
    return classes;
  }

  public void setListLen(int val) {
    this.listLen = val;
  }

  public void setListBeginsAtZero(boolean flag) {
    this.listBeginsAtZero = flag;
  }

  public boolean listBeginsAtZero() {
    return listBeginsAtZero;
  }

  public int listLen() {
    return listLen;
  }

  public boolean isListItem() {
    return (listBeginsAtZero && listLen > 0) || (listLen > 1);
  }

  public RapierRuleItem copy() {
    RapierRuleItem newItem = new RapierRuleItem();
    newItem.words = new HashSet<TextRulerWordConstraint>(words);
    newItem.tags = new HashSet<String>(tags);
    newItem.classes = new HashSet<String>(classes);
    newItem.listLen = listLen;
    newItem.listBeginsAtZero = listBeginsAtZero;
    return newItem;
  }

  public int getRuleSizePoints() {
    int result = this.isListItem() ? 3 : 2; // 3 for a list pattern item. 2
    // for a normal pattern item
    if (words.size() > 1)
      result += (words.size() - 1) * 2; // every disjunct in WORD
    // constraint counts 2
    if (tags.size() > 1)
      result += tags.size() - 1; // every disjunct in POS TAG constraint
    // counts 1
    if (classes.size() > 1)
      result += classes.size() - 1; // every disjunct in CLASS constraint
    // counts 1
    return result;
  }

  public void addWordConstraints(Collection<TextRulerWordConstraint> constraints) {
    words.addAll(constraints);
  }

  public void addTagConstraints(Collection<String> constraints) {
    tags.addAll(constraints);
  }

  public void addClassConstraints(Collection<String> constraints) {
    classes.addAll(constraints);
  }

  public void addWordConstraint(TextRulerWordConstraint constraint) {
    words.add(constraint);
  }

  public void addTagConstraint(String constraint) {
    tags.add(constraint);
  }

  public void addClassConstraint(String constraint) {
    classes.add(constraint);
  }

  @Override
  public String toString() {
    return getStringForRuleString(null, null, 0, 0, 0, 0, 0);
  }

}
