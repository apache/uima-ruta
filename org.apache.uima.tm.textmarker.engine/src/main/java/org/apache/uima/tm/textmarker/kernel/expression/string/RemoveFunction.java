package org.apache.uima.tm.textmarker.kernel.expression.string;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;


public class RemoveFunction extends StringFunctionExpression {

  private List<StringExpression> list;

  private String var;

  public RemoveFunction(String v, List<StringExpression> list) {
    super();
    this.var = v;
    this.list = list;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    StringBuilder result = new StringBuilder();
    String value = parent.getEnvironment().getVariableValue(var, String.class);
    for (StringExpression each : list) {
      String string = each.getStringValue(parent);
      String[] split = value.split(string);
      for (String r : split) {
        result.append(r);
      }
    }
    return result.toString();
  }

  public List<StringExpression> getList() {
    return list;
  }

  public String getVar() {
    return var;
  }

}
