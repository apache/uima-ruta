package org.apache.uima.tm.textmarker.action;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;

public class ClearAction extends AbstractTextMarkerAction {

  private String list;

  public ClearAction(String list) {
    super();
    this.list = list;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    element.getParent().getEnvironment().setVariableValue(list, null);
  }

}
