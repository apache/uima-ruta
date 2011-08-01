package org.apache.uima.tm.textmarker.kernel.expression.number;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;


public class ComposedIntegerExpression extends AbstractNumberExpression {

  private final List<NumberExpression> expressions;

  private final List<String> ops;

  public ComposedIntegerExpression(List<NumberExpression> expressions, List<String> ops) {
    super();
    this.expressions = expressions;
    this.ops = ops;
  }

  @Override
  public double getDoubleValue(TextMarkerStatement parent) {
    double result = getExpressions().get(0).getDoubleValue(parent);
    for (int i = 0; i < getOperators().size(); i++) {
      result = calculate(result, getExpressions().get(i + 1).getDoubleValue(parent), getOperators()
              .get(i));
    }
    return result;
  }

  @Override
  public int getIntegerValue(TextMarkerStatement parent) {
    int result = getExpressions().get(0).getIntegerValue(parent);
    for (int i = 0; i < getOperators().size(); i++) {
      result = calculate(result, getExpressions().get(i + 1).getIntegerValue(parent),
              getOperators().get(i));
    }
    return result;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    try {
      String string = "" + getDoubleValue(parent);
      return string;
    } catch (Exception e) {
    }
    return "" + getIntegerValue(parent);

  }

  public List<NumberExpression> getExpressions() {
    return expressions;
  }

  public List<String> getOperators() {
    return ops;
  }

}
