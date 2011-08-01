package org.apache.uima.tm.textmarker.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class MatchedTextAction extends AbstractTextMarkerAction {

  private final String var;

  private final List<NumberExpression> list;

  public MatchedTextAction(String var, List<NumberExpression> list) {
    super();
    this.var = var;
    this.list = list;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<Integer> indexList = getIndexList(match, element);
    AnnotationFS matchedAnnotation = match.getMatchedAnnotation(stream, indexList);
    element.getParent().getEnvironment().setVariableValue(var, matchedAnnotation.getCoveredText());
  }

  public String getVar() {
    return var;
  }

  public List<NumberExpression> getList() {
    return list;
  }

  protected List<Integer> getIndexList(RuleMatch match, TextMarkerRuleElement element) {
    List<Integer> indexList = new ArrayList<Integer>();
    if (list == null || list.isEmpty()) {
      int self = match.getRule().getElements().indexOf(element) + 1;
      indexList.add(self);
      return indexList;
    }
    int last = Integer.MAX_VALUE - 1;
    for (NumberExpression each : list) {
      int value = each.getIntegerValue(element.getParent());
      for (int i = Math.min(value, last + 1); i < value; i++) {
        indexList.add(i);
      }
      indexList.add(value);
    }
    return indexList;
  }
}
