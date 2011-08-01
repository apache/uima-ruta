package org.apache.uima.tm.textmarker.action;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;

public class VariableAction extends AbstractTextMarkerAction {

  private final String var;

  public VariableAction(String var) {
    super();
    this.var = var;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {

  }

  public String getVar() {
    return var;
  }

}
