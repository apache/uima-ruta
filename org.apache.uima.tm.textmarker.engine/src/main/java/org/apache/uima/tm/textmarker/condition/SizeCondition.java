package org.apache.uima.tm.textmarker.condition;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.list.ListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.SimpleNumberExpression;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class SizeCondition extends AbstractTextMarkerCondition {

  private ListExpression<?> listExpr;

  private NumberExpression minExpr;

  private NumberExpression maxExpr;

  private String varExpr;

  public SizeCondition(ListExpression<?> list, NumberExpression min, NumberExpression max,
          String string) {
    super();
    this.listExpr = list;
    this.minExpr = min == null ? new SimpleNumberExpression(Integer.MIN_VALUE) : min;
    this.maxExpr = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.varExpr = string;
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    int count = listExpr.getList(element.getParent()).size();
    boolean value = count >= minExpr.getIntegerValue(element.getParent())
            && count <= maxExpr.getIntegerValue(element.getParent());
    if (varExpr != null) {
      element.getParent().getEnvironment().setVariableValue(varExpr, count);
    }
    return new EvaluatedCondition(this, value);
  }

  public ListExpression<?> getListExpr() {
    return listExpr;
  }

  public NumberExpression getMinExpr() {
    return minExpr;
  }

  public NumberExpression getMaxExpr() {
    return maxExpr;
  }

  public String getVarExpr() {
    return varExpr;
  }
}
