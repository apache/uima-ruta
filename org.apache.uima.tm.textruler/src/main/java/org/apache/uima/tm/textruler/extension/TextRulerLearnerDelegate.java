package org.apache.uima.tm.textruler.extension;

import org.apache.uima.tm.textruler.extension.TextRulerLearner.TextRulerLearnerState;

/**
 * 
 * An TextRulerLearnerDelegate is used for an TextRulerLearner so that it can e.g. send status
 * updates to some one that might be interested (in our case the controller and though the UI). In
 * addition to that, the algorithm can ask the delegate if he should abort his work (e.g. because
 * the user clicked the abort button).
 * 
 */
public interface TextRulerLearnerDelegate {

  public void algorithmStatusUpdate(TextRulerLearner alg, String statusString,
          TextRulerLearnerState state, boolean ruleBaseChanged);

  public boolean shouldAbort();

}
