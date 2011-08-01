package org.apache.uima.tm.textmarker.condition;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class MOfNCondition extends ComposedTextMarkerCondition {

  private final NumberExpression min;

  private final NumberExpression max;

  public MOfNCondition(List<AbstractTextMarkerCondition> conditions, NumberExpression min,
          NumberExpression max) {
    super(conditions);
    this.min = min;
    this.max = max;
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    int result = 0;
    List<EvaluatedCondition> evals = new ArrayList<EvaluatedCondition>();
    for (AbstractTextMarkerCondition each : conditions) {
      crowd.beginVisit(each, null);
      EvaluatedCondition eval = each.eval(basic, matchedType, element, stream, crowd);
      crowd.endVisit(each, null);
      evals.add(eval);
      if (eval.isValue()) {
        result++;
      }
    }
    boolean value = result >= min.getIntegerValue(element.getParent())
            && result <= max.getIntegerValue(element.getParent());
    return new EvaluatedCondition(this, value, evals);
  }

  public NumberExpression getMin() {
    return min;
  }

  public NumberExpression getMax() {
    return max;
  }
}
