package org.apache.uima.tm.textmarker.condition;

import org.apache.uima.tm.textmarker.kernel.expression.list.TypeListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;

public abstract class TypeSentiveCondition extends TerminalTextMarkerCondition {

  protected final TypeExpression type;

  private final TypeListExpression list;

  public TypeExpression getType() {
    return type;
  }

  public TypeSentiveCondition(TypeExpression type) {
    super();
    this.type = type;
    this.list = null;
  }

  public TypeSentiveCondition(TypeListExpression list) {
    super();
    this.type = null;
    this.list = list;
  }

  public boolean isWorkingOnList() {
    return getList() != null;
  }

  public TypeListExpression getList() {
    return list;
  }
}
