package org.apache.uima.tm.textmarker.verbalize;

import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;


public class VerbalizerUtils {

  private TextMarkerVerbalizer verbalizer;

  public VerbalizerUtils(TextMarkerVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
  }

  public String verbalizeList(List<?> list) {
    StringBuilder result = new StringBuilder();
    Iterator<?> it = list.iterator();
    while (it.hasNext()) {
      Object obj = it.next();
      result.append(obj.toString());
      if (it.hasNext()) {
        result.append(", ");
      }
    }
    return result.toString();
  }

  public String verbalizeTypeList(List<Type> list) {
    StringBuilder result = new StringBuilder();
    Iterator<Type> it = list.iterator();
    while (it.hasNext()) {
      Type type = it.next();
      result.append(verbalizer.verbalizeType(type));
      if (it.hasNext()) {
        result.append(", ");
      }
    }
    return result.toString();
  }

  public String verbalizeExpressionList(List<? extends TextMarkerExpression> list) {
    StringBuilder result = new StringBuilder();
    Iterator<? extends TextMarkerExpression> it = list.iterator();
    while (it.hasNext()) {
      TextMarkerExpression e = it.next();
      result.append(verbalizer.verbalize(e));
      if (it.hasNext()) {
        result.append(", ");
      }
    }
    return result.toString();
  }

}
