package org.apache.uima.tm.textmarker.kernel.expression.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;


public class ReferenceTypeListExpression extends TypeListExpression {

  private String var;

  public ReferenceTypeListExpression(String var) {
    super();
    this.var = var;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Type> getList(TextMarkerStatement parent) {
    List<Object> list = parent.getEnvironment().getVariableValue(var, List.class);
    List<Type> result = new ArrayList<Type>();
    for (Object each : list) {
      if (each instanceof TypeExpression) {
        result.add(((TypeExpression) each).getType(parent));
      } else if (each instanceof Type) {
        result.add((Type) each);
      }
    }
    return result;
  }

  public String getVar() {
    return var;
  }
}
