package org.apache.uima.tm.textmarker.kernel.expression.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;


public class ReferenceNumberListExpression extends NumberListExpression {

  private String var;

  public ReferenceNumberListExpression(String var) {
    super();
    this.var = var;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Number> getList(TextMarkerStatement parent) {
    List<Object> list = parent.getEnvironment().getVariableValue(var, List.class);
    List<Number> result = new ArrayList<Number>();
    for (Object each : list) {
      if (each instanceof NumberExpression) {
        result.add(((NumberExpression) each).getDoubleValue(parent));
      } else if (each instanceof Number) {
        result.add((Number) each);
      }
    }
    return result;
  }

  public String getVar() {
    return var;
  }
}
