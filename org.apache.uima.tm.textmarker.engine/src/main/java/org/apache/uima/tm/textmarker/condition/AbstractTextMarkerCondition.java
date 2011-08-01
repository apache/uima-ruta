package org.apache.uima.tm.textmarker.condition;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerElement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public abstract class AbstractTextMarkerCondition extends TextMarkerElement {

  public abstract EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd);

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }
}
