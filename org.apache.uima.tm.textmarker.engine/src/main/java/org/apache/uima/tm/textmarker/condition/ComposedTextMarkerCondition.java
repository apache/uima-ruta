package org.apache.uima.tm.textmarker.condition;

import java.util.ArrayList;
import java.util.List;

public abstract class ComposedTextMarkerCondition extends AbstractTextMarkerCondition {

  protected List<AbstractTextMarkerCondition> conditions;

  public ComposedTextMarkerCondition(List<AbstractTextMarkerCondition> conditions) {
    super();
    this.conditions = conditions;
  }

  public ComposedTextMarkerCondition(AbstractTextMarkerCondition condition) {
    super();
    this.conditions = new ArrayList<AbstractTextMarkerCondition>();
    this.conditions.add(condition);
  }

  public List<AbstractTextMarkerCondition> getConditions() {
    return conditions;
  }
}
