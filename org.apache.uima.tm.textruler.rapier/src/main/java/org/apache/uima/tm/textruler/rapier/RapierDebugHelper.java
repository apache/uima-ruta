package org.apache.uima.tm.textruler.rapier;

import java.util.Set;

import org.apache.uima.tm.textruler.core.TextRulerExample;

public class RapierDebugHelper {

  public static boolean debugCheckIfRuleCoversItsSeedRuleCoverings(RapierRule rule) {
    Set<TextRulerExample> parent1Positives = rule.getParent1().getCoveringStatistics()
            .getCoveredPositiveExamples();
    Set<TextRulerExample> parent2Positives = rule.getParent2().getCoveringStatistics()
            .getCoveredPositiveExamples();
    Set<TextRulerExample> rulePositives = rule.getCoveringStatistics().getCoveredPositiveExamples();
    return rulePositives.containsAll(parent1Positives)
            && rulePositives.containsAll(parent2Positives);
  }

}
