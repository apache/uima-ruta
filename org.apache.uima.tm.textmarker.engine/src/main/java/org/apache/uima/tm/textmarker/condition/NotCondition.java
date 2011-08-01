package org.apache.uima.tm.textmarker.condition;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class NotCondition extends ComposedTextMarkerCondition {

  public NotCondition(AbstractTextMarkerCondition condition) {
    super(condition);
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic annotation, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    AbstractTextMarkerCondition cond = conditions.get(0);
    crowd.beginVisit(cond, null);
    EvaluatedCondition eval = cond.eval(annotation, matchedType, element, stream, crowd);
    crowd.endVisit(cond, null);
    return new EvaluatedCondition(this, !eval.isValue(), eval);
  }

}
