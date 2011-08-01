package org.apache.uima.tm.textruler.core;

/**
 * 
 * Every rule pattern (TextRulerRulePattern) consists of items. Those have to implement the
 * interface TextRulerRuleItem.
 * 
 *         hint: maybe we should change this to an abstract class instead of an interface ?!
 */
public interface TextRulerRuleItem {

  public enum MLRuleItemType {
    PREFILLER, FILLER, POSTFILLER
  };

  public String getStringForRuleString(TextRulerRule rule, MLRuleItemType type,
          int numberInPattern, int patternSize, int numberInRule, int ruleSize, int slotIndex);

  public boolean equals(TextRulerRuleItem o);

  public TextRulerRuleItem copy();

  public String toString();

  public int hashCode();

}
