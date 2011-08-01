package org.apache.uima.tm.textmarker.kernel.expression.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;


public class SimpleNumberListExpression extends NumberListExpression {

  private List<NumberExpression> list;

  public SimpleNumberListExpression(List<NumberExpression> list) {
    super();
    this.list = list;
  }

  @Override
  public List<Number> getList(TextMarkerStatement parent) {
    List<Number> result = new ArrayList<Number>();
    for (NumberExpression each : list) {
      result.add(each.getDoubleValue(parent));
    }
    return result;
  }

  public List<NumberExpression> getList() {
    return list;
  }
}
