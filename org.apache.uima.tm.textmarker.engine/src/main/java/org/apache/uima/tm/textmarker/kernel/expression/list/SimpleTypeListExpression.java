package org.apache.uima.tm.textmarker.kernel.expression.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;


public class SimpleTypeListExpression extends TypeListExpression {

  private List<TypeExpression> list;

  public SimpleTypeListExpression(List<TypeExpression> list) {
    super();
    this.list = list;
  }

  @Override
  public List<Type> getList(TextMarkerStatement parent) {
    List<Type> result = new ArrayList<Type>();
    for (TypeExpression each : list) {
      result.add(each.getType(parent));
    }
    return result;
  }

  public List<TypeExpression> getList() {
    return list;
  }
}
