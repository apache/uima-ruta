package org.apache.uima.tm.textmarker.action;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.ListExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class RemoveAction extends AbstractTextMarkerAction {

  private String var;

  private List<TextMarkerExpression> elements;

  public RemoveAction(String var, List<TextMarkerExpression> list) {
    super();
    this.var = var;
    this.elements = list;
  }

  public String getListExpr() {
    return var;
  }

  public List<TextMarkerExpression> getElements() {
    return elements;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List list = element.getParent().getEnvironment().getVariableValue(var, List.class);
    for (TextMarkerExpression each : elements) {
      if (each instanceof ListExpression) {
        ListExpression l = (ListExpression) each;
        list.removeAll(l.getList(element.getParent()));
      } else {
        list.remove(each);
      }
    }
  }

}
