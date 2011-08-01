package org.apache.uima.tm.textruler.extension;

import org.apache.uima.tm.textruler.extension.TextRulerLearner.TextRulerLearnerState;

/**
 * TextRulerControllerDelegate provides some useful notificaion methods e.g. a UI class can
 * implement to get some important information.
 */
public interface TextRulerControllerDelegate {

  public void algorithmDidEnd(TextRulerLearnerController algController);

  public void algorithmStatusUpdate(TextRulerLearnerController algController, String statusString,
          TextRulerLearnerState state, boolean ruleBaseChanged);

  public void preprocessorStatusUpdate(TextRulerPreprocessor p, String statusString);

  public void globalStatusUpdate(String str);

}
