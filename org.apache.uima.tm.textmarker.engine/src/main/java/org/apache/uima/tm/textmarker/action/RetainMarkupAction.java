package org.apache.uima.tm.textmarker.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class RetainMarkupAction extends AbstractTextMarkerAction {

  private List<StringExpression> markup;

  public RetainMarkupAction(List<StringExpression> markup) {
    super();
    this.markup = markup;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<String> list = new ArrayList<String>();
    for (StringExpression each : markup) {
      list.add(each.getStringValue(element.getParent()));
    }
    stream.retainTags(list);
  }

  public List<StringExpression> getMarkup() {
    return markup;
  }
}
