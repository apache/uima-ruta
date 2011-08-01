package org.apache.uima.tm.textmarker.condition;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.bool.SimpleBooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.SimpleNumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class NearCondition extends TypeSentiveCondition {

  private final NumberExpression min;

  private final NumberExpression max;

  private final BooleanExpression forward;

  private final BooleanExpression filtered;

  public NearCondition(TypeExpression type, NumberExpression min, NumberExpression max,
          BooleanExpression forward, BooleanExpression filtered) {
    super(type);
    this.min = min == null ? new SimpleNumberExpression(1) : min;
    this.max = max == null ? new SimpleNumberExpression(1) : max;
    this.forward = forward == null ? new SimpleBooleanExpression(true) : forward;
    this.filtered = filtered == null ? new SimpleBooleanExpression(false) : filtered;
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    FSIterator<AnnotationFS> it = filtered.getBooleanValue(element.getParent()) ? stream : stream
            .getUnfilteredBasicIterator();
    it.moveTo(basic);
    int count = 0;
    while (count <= max.getIntegerValue(element.getParent())) {
      if (count >= min.getIntegerValue(element.getParent()) && it.isValid()) {
        FeatureStructure featureStructure = it.get();
        if (featureStructure instanceof TextMarkerBasic) {
          TextMarkerBasic each = (TextMarkerBasic) featureStructure;
          if (each.isPartOf(type.getType(element.getParent()).getName())) {
            return new EvaluatedCondition(this, true);
          }
        }
      }
      if (forward.getBooleanValue(element.getParent())) {
        it.moveToNext();
      } else {
        it.moveToPrevious();
      }
      count++;
    }
    return new EvaluatedCondition(this, false);
  }

  public NumberExpression getMin() {
    return min;
  }

  public NumberExpression getMax() {
    return max;
  }

  public BooleanExpression getForward() {
    return forward;
  }

  public BooleanExpression getFiltered() {
    return filtered;
  }
}
