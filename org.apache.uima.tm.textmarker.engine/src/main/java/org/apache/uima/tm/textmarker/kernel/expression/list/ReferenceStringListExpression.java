package org.apache.uima.tm.textmarker.kernel.expression.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;


public class ReferenceStringListExpression extends StringListExpression {

  private String var;

  public ReferenceStringListExpression(String var) {
    super();
    this.var = var;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<String> getList(TextMarkerStatement parent) {
    List<Object> list = parent.getEnvironment().getVariableValue(var, List.class);
    List<String> result = new ArrayList<String>();
    for (Object each : list) {
      if (each instanceof StringExpression) {
        result.add(((StringExpression) each).getStringValue(parent));
      } else if (each instanceof String) {
        result.add((String) each);
      }
    }
    return result;
  }

  public String getVar() {
    return var;
  }
}
