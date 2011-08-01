package org.apache.uima.tm.textmarker.kernel.expression.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;


public class SimpleStringListExpression extends StringListExpression {

  private List<StringExpression> list;

  public SimpleStringListExpression(List<StringExpression> list) {
    super();
    this.list = list;
  }

  @Override
  public List<String> getList(TextMarkerStatement parent) {
    List<String> result = new ArrayList<String>();
    for (StringExpression each : list) {
      result.add(each.getStringValue(parent));
    }
    return result;
  }

  public List<StringExpression> getList() {
    return list;
  }
}
