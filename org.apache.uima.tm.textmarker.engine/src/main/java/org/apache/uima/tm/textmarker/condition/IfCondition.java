package org.apache.uima.tm.textmarker.condition;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class IfCondition extends AbstractTextMarkerCondition {

  private final BooleanExpression expression;

  public IfCondition(BooleanExpression e) {
    super();
    this.expression = e;
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    return new EvaluatedCondition(this, expression.getBooleanValue(element.getParent()));
  }

  public BooleanExpression getExpression() {
    return expression;
  }

}
