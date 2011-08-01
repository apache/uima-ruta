package org.apache.uima.tm.textmarker.kernel.expression.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;


public class ReferenceBooleanListExpression extends BooleanListExpression {

  private String var;

  public ReferenceBooleanListExpression(String var) {
    super();
    this.var = var;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Boolean> getList(TextMarkerStatement parent) {
    List<Object> list = parent.getEnvironment().getVariableValue(var, List.class);
    List<Boolean> result = new ArrayList<Boolean>();
    for (Object each : list) {
      if (each instanceof BooleanExpression) {
        result.add(((BooleanExpression) each).getBooleanValue(parent));
      } else if (each instanceof Boolean) {
        result.add((Boolean) each);
      }
    }
    return result;
  }

  public String getVar() {
    return var;
  }

}
