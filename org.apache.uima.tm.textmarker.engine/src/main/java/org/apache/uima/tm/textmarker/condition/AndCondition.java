package org.apache.uima.tm.textmarker.condition;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class AndCondition extends ComposedTextMarkerCondition {

  public AndCondition(List<AbstractTextMarkerCondition> conditions) {
    super(conditions);
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic currentSymbol, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream symbolStream, InferenceCrowd crowd) {
    boolean result = true;
    List<EvaluatedCondition> evals = new ArrayList<EvaluatedCondition>();
    for (AbstractTextMarkerCondition each : conditions) {
      crowd.beginVisit(each, null);
      EvaluatedCondition eval = each.eval(currentSymbol, matchedType, element, symbolStream, crowd);
      crowd.endVisit(each, null);
      result &= eval.isValue();
      evals.add(eval);
    }
    return new EvaluatedCondition(this, result, evals);
  }

}
