package org.apache.uima.tm.textmarker.kernel.rule;

import java.util.Collections;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerElement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public abstract class AbstractRuleElement extends TextMarkerElement implements RuleElement {

  protected RuleElementQuantifier quantifier;

  public AbstractRuleElement(RuleElementQuantifier quantifier) {
    super();
    this.quantifier = quantifier;
  }

  @SuppressWarnings("unchecked")
  protected final InferenceCrowd emptyCrowd = new InferenceCrowd(Collections.EMPTY_LIST);

  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches,
          TextMarkerBlock parent) {
    return quantifier.evaluateMatches(matches, parent, emptyCrowd);
  }

  public boolean continueMatch(int index, List<RuleElement> elements, TextMarkerBasic next,
          RuleElementMatch match, List<RuleElementMatch> matches, TextMarkerStream stream) {
    return quantifier.continueMatch(index, elements, next, match, matches, stream, emptyCrowd);
  }

  public RuleElementQuantifier getQuantifier() {
    return quantifier;
  }

}
