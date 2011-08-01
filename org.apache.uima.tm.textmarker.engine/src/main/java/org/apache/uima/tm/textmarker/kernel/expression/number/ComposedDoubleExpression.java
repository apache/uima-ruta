package org.apache.uima.tm.textmarker.kernel.expression.number;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;


public class ComposedDoubleExpression extends AbstractNumberExpression {

  private final List<NumberExpression> expressions;

  private final List<String> ops;

  public ComposedDoubleExpression(List<NumberExpression> expressions, List<String> ops) {
    super();
    this.expressions = expressions;
    this.ops = ops;
  }

  @Override
  public double getDoubleValue(TextMarkerStatement parent) {
    NumberExpression numberExpression = getExpressions().get(0);
    if (numberExpression == null) {
      return 0;
    }
    double result = numberExpression.getDoubleValue(parent);
    for (int i = 0; i < getOperators().size(); i++) {
      double second = 0;
      if (getExpressions().size() > i + 1) {
        second = getExpressions().get(i + 1).getIntegerValue(parent);
      }
      result = calculate(result, second, getOperators().get(i));
    }
    return result;
  }

  @Override
  public int getIntegerValue(TextMarkerStatement parent) {
    int result = getExpressions().get(0).getIntegerValue(parent);
    for (int i = 0; i < getOperators().size(); i++) {
      int second = 0;
      if (getExpressions().size() > i + 1) {
        second = getExpressions().get(i + 1).getIntegerValue(parent);
      }
      result = calculate(result, second, getOperators().get(i));
    }
    return result;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return "" + getDoubleValue(parent);
  }

  public List<NumberExpression> getExpressions() {
    return expressions;
  }

  public List<String> getOperators() {
    return ops;
  }

}
