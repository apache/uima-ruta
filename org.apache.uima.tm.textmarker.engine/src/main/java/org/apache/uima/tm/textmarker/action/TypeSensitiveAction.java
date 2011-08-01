package org.apache.uima.tm.textmarker.action;

import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;

public abstract class TypeSensitiveAction extends AbstractTextMarkerAction {

  protected TypeExpression type;

  public TypeExpression getType() {
    return type;
  }

  public TypeSensitiveAction(TypeExpression type) {
    super();
    this.type = type;
  }

}
