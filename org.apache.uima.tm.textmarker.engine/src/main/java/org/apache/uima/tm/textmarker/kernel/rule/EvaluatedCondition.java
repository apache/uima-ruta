package org.apache.uima.tm.textmarker.kernel.rule;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.tm.textmarker.condition.NotCondition;


public class EvaluatedCondition {

  private final AbstractTextMarkerCondition condition;

  private final boolean value;

  private final List<EvaluatedCondition> conditions;

  private final List<EvaluatedCondition> noConditions = new ArrayList<EvaluatedCondition>(0);

  public EvaluatedCondition(AbstractTextMarkerCondition condition, boolean value,
          List<EvaluatedCondition> conditions) {
    super();
    this.condition = condition;
    this.value = value;
    this.conditions = conditions;
  }

  public EvaluatedCondition(AbstractTextMarkerCondition condition, boolean value) {
    super();
    this.condition = condition;
    this.value = value;
    this.conditions = noConditions;
  }

  public EvaluatedCondition(NotCondition condition, boolean value, EvaluatedCondition eval) {
    super();
    this.condition = condition;
    this.value = value;
    this.conditions = new ArrayList<EvaluatedCondition>();
    conditions.add(eval);
  }

  public AbstractTextMarkerCondition getCondition() {
    return condition;
  }

  public boolean isValue() {
    return value;
  }

  public List<EvaluatedCondition> getConditions() {
    return conditions;
  }

}
