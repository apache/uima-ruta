package org.apache.uima.tm.textmarker.kernel.rule.quantifier;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElementMatch;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class QuestionGreedy implements RuleElementQuantifier {

  public boolean continueMatch(int index, List<RuleElement> elements, TextMarkerBasic next,
          RuleElementMatch match, List<RuleElementMatch> matches, TextMarkerStream stream,
          InferenceCrowd crowd) {
    return match == null;
  }

  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches,
          TextMarkerStatement element, InferenceCrowd crowd) {
    boolean result = true;
    for (RuleElementMatch match : matches) {
      result &= match.matched() || match.getTextsMatched().isEmpty();
    }
    if (!result) {
      matches.remove(0);
      result = true;
    }
    if (result) {
      return matches;
    } else {
      return null;
    }
  }
}
