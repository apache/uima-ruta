package org.apache.uima.tm.textruler.wien;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.uima.tm.textruler.extension.TextRulerLearner;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerParameter;

public class WienFactory implements TextRulerLearnerFactory {

  public TextRulerLearner createAlgorithm(String inputFolderPath, String additionalFolderPath,
          String preprocessorTMfile, String tempFolderPath, String[] fullSlotTypeNames,
          Set<String> filterSet, TextRulerLearnerDelegate delegate) {
    return new Wien(inputFolderPath, preprocessorTMfile, tempFolderPath, fullSlotTypeNames,
            filterSet, delegate);
  }

  public Map<String, Object> getAlgorithmParameterStandardValues() {
    return new HashMap<String, Object>();
  }

  public TextRulerLearnerParameter[] getAlgorithmParameters() {
    TextRulerLearnerParameter[] result = new TextRulerLearnerParameter[0];
    return result;
  }

}
