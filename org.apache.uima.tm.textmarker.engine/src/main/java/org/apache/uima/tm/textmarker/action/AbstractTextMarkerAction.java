package org.apache.uima.tm.textmarker.action;

import org.apache.uima.tm.textmarker.kernel.TextMarkerElement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;

public abstract class AbstractTextMarkerAction extends TextMarkerElement {

  public abstract void execute(RuleMatch match, TextMarkerRuleElement element,
          TextMarkerStream stream, InferenceCrowd crowd);

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }

}
