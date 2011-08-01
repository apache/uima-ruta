package org.apache.uima.tm.textmarker.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class FilterTypeAction extends AbstractTextMarkerAction {

  public List<TypeExpression> getList() {
    return list;
  }

  private List<TypeExpression> list;

  public FilterTypeAction(List<TypeExpression> list) {
    super();
    this.list = list;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<Type> types = new ArrayList<Type>();
    for (TypeExpression each : list) {
      types.add(each.getType(element.getParent()));
    }
    stream.filterTypes(types);
  }

}
