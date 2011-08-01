package org.apache.uima.tm.textmarker.kernel.expression.resource;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.resource.TextMarkerTable;

public class ReferenceWordTableExpression extends WordTableExpression {

  private String ref;

  public ReferenceWordTableExpression(String ref) {
    super();
    this.ref = ref;
  }

  @Override
  public TextMarkerTable getTable(TextMarkerStatement element) {
    return element.getEnvironment().getVariableValue(ref, TextMarkerTable.class);
  }

  public String getRef() {
    return ref;
  }

}
