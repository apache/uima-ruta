package org.apache.uima.tm.textruler.wien;

import org.apache.uima.tm.textruler.core.TextRulerBasicLearner;
import org.apache.uima.tm.textruler.core.TextRulerMultiSlotRule;
import org.apache.uima.tm.textruler.core.TextRulerTarget;

public class WienRule extends TextRulerMultiSlotRule {

  public WienRule(TextRulerBasicLearner parentAlgorithm, TextRulerTarget target) {
    super(parentAlgorithm, target);
  }

  public WienRule(WienRule copyFrom) {
    super(copyFrom);
  }

  @Override
  public WienRule copy() {
    return new WienRule(this);
  }

}
