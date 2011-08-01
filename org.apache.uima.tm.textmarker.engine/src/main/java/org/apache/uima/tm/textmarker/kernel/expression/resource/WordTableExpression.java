package org.apache.uima.tm.textmarker.kernel.expression.resource;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.resource.TextMarkerTable;

public abstract class WordTableExpression extends TextMarkerExpression {

  public abstract TextMarkerTable getTable(TextMarkerStatement element);

}
