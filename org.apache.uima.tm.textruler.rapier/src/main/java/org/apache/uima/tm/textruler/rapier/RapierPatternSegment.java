package org.apache.uima.tm.textruler.rapier;

import org.apache.uima.tm.textruler.core.TextRulerRuleItem;
import org.apache.uima.tm.textruler.core.TextRulerRulePattern;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;

public class RapierPatternSegment {

  TextRulerRulePattern shorterPattern = new TextRulerRulePattern();

  TextRulerRulePattern longerPattern = new TextRulerRulePattern();

  public void debugOutput() {

    TextRulerToolkit.log("\n-------------\nShorterList: ");
    for (TextRulerRuleItem t : shorterPattern)
      System.out.print(t.getStringForRuleString(null, null, 0, 1, 0, 1, 0) + "    ");
    TextRulerToolkit.log("");

    System.out.print("LongerList: ");
    for (TextRulerRuleItem t : longerPattern)
      System.out.print(t.getStringForRuleString(null, null, 0, 1, 0, 1, 0) + "    ");
    TextRulerToolkit.log("");
  }
}
