package org.apache.uima.tm.textmarker.kernel.expression.resource;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.resource.TextMarkerWordList;

public abstract class WordListExpression extends TextMarkerExpression {

  public abstract TextMarkerWordList getList(TextMarkerStatement element);

}
