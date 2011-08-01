package org.apache.uima.tm.textmarker.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.ListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class GetAction extends AbstractTextMarkerAction {

  private ListExpression<TextMarkerExpression> listExpr;

  private String var;

  private StringExpression opExpr;

  public GetAction(ListExpression<TextMarkerExpression> f, String string, StringExpression op) {
    super();
    this.listExpr = f;
    this.var = string;
    this.opExpr = op;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    String op = opExpr.getStringValue(element.getParent());
    List<TextMarkerExpression> list = listExpr.getList(element.getParent());
    if ("dominant".equals(op)) {
      element.getParent().getEnvironment().setVariableValue(var,
              getDominant(list, element.getParent()));
    }
  }

  private Object getDominant(List<TextMarkerExpression> list, TextMarkerBlock parent) {
    List<Object> objs = new ArrayList<Object>();
    List<Integer> counts = new ArrayList<Integer>();
    for (Object each : list) {
      Object value = each;// getValue(each, parent);
      if (objs.contains(value)) {
        int indexOf = objs.indexOf(value);
        Integer i = counts.get(indexOf);
        counts.set(indexOf, ++i);
      } else {
        counts.add(1);
        objs.add(each);
      }
    }
    Object dominant = null;
    int dominantCount = -1;
    int i = 0;
    for (Object each : objs) {
      int count = counts.get(i++);
      if (count > dominantCount) {
        dominantCount = count;
        dominant = each;
      }
    }
    return dominant;
  }

  public ListExpression<TextMarkerExpression> getListExpr() {
    return listExpr;
  }

  public String getVar() {
    return var;
  }

  public StringExpression getOpExpr() {
    return opExpr;
  }

}
