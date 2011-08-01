package org.apache.uima.tm.textmarker.kernel.expression.resource;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.resource.TextMarkerWordList;

public class ReferenceWordListExpression extends WordListExpression {

  private String ref;

  public ReferenceWordListExpression(String ref) {
    super();
    this.ref = ref;
  }

  @Override
  public TextMarkerWordList getList(TextMarkerStatement element) {
    return element.getEnvironment().getVariableValue(ref, TextMarkerWordList.class);
  }

  public String getRef() {
    return ref;
  }
}
