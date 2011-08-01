package org.apache.uima.tm.textruler.core;

/**
 * 
 * TextRulerSlotPattern holds the three slot patterns we usually have for a TextMarker-Rule: a
 * preFiller pattern, a filler pattern and a post filler pattern
 * 
 */
public class TextRulerSlotPattern {

  public TextRulerRulePattern preFillerPattern = new TextRulerRulePattern();

  public TextRulerRulePattern fillerPattern = new TextRulerRulePattern();

  public TextRulerRulePattern postFillerPattern = new TextRulerRulePattern();

  public TextRulerSlotPattern() {
    super();
  }

  public TextRulerSlotPattern(TextRulerSlotPattern copyFrom) {
    super();
    for (TextRulerRuleItem i : copyFrom.preFillerPattern)
      preFillerPattern.add(i.copy());
    for (TextRulerRuleItem i : copyFrom.fillerPattern)
      fillerPattern.add(i.copy());
    for (TextRulerRuleItem i : copyFrom.postFillerPattern)
      postFillerPattern.add(i.copy());
  }

  public TextRulerSlotPattern copy() {
    return new TextRulerSlotPattern(this);
  }

}
