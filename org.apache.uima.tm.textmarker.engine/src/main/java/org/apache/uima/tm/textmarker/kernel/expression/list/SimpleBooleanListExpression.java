package org.apache.uima.tm.textmarker.kernel.expression.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;


public class SimpleBooleanListExpression extends BooleanListExpression {

  private List<BooleanExpression> list;

  public SimpleBooleanListExpression(List<BooleanExpression> list) {
    super();
    this.list = list;
  }

  @Override
  public List<Boolean> getList(TextMarkerStatement parent) {
    List<Boolean> result = new ArrayList<Boolean>();
    for (BooleanExpression each : list) {
      result.add(each.getBooleanValue(parent));
    }
    return result;
  }

  public List<BooleanExpression> getList() {
    return list;
  }

}
