package org.apache.uima.tm.textmarker.kernel.constraint;

import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FeatureStructure;

public class NotMatchConstraint implements FSMatchConstraint {
  private static final long serialVersionUID = 1115953538613617791L;

  private final FSMatchConstraint constraint;

  public NotMatchConstraint(FSMatchConstraint constraint) {
    super();
    this.constraint = constraint;
  }

  public boolean match(FeatureStructure fs) {
    return !constraint.match(fs);
  }

  @Override
  public String toString() {
    return "NOT " + constraint.toString();
  }

}
