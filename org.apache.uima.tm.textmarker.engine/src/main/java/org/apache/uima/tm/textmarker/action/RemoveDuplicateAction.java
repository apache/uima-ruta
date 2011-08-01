package org.apache.uima.tm.textmarker.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class RemoveDuplicateAction extends AbstractTextMarkerAction {

  private String var;

  public RemoveDuplicateAction(String var) {
    super();
    this.var = var;
  }

  public String getListExpr() {
    return var;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List list = element.getParent().getEnvironment().getVariableValue(var, List.class);
    Set<Object> set = new HashSet<Object>(list);
    element.getParent().getEnvironment().setVariableValue(var, new ArrayList<Object>(set));

  }
}
