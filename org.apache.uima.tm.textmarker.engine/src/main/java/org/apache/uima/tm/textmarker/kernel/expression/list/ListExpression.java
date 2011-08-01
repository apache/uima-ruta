package org.apache.uima.tm.textmarker.kernel.expression.list;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;


public abstract class ListExpression<T> extends StringExpression {

  public abstract List<T> getList(TextMarkerStatement parent);

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return getList(parent).toString();
  }

}
