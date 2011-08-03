package org.apache.uima.tm.textruler.whisk.token;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.uima.tm.textruler.extension.TextRulerLearner;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerDelegate;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerFactory;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerParameter;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerParameter.MLAlgorithmParamType;

public class WhiskFactory implements TextRulerLearnerFactory {

  public TextRulerLearner createAlgorithm(String inputFolderPath, String additionalFolderPath,
          String preprocessorTMfile, String tempFolderPath, String[] fullSlotTypeNames,
          Set<String> filterSet, TextRulerLearnerDelegate delegate) {
    return new Whisk(inputFolderPath, preprocessorTMfile, tempFolderPath, fullSlotTypeNames,
            filterSet, delegate);
  }

  public Map<String, Object> getAlgorithmParameterStandardValues() {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put(Whisk.WINDOSIZE_KEY, Whisk.STANDARD_WINDOWSIZE);
    result.put(Whisk.ERROR_THRESHOLD_KEY, Whisk.STANDARD_ERROR_THRESHOLD);
    result.put(Whisk.POSTAG_ROOTTYPE_KEY, Whisk.STANDARD_POSTAG_ROOTTYPE);
    return result;
  }

  public TextRulerLearnerParameter[] getAlgorithmParameters() {
    TextRulerLearnerParameter[] result = new TextRulerLearnerParameter[3];

    result[0] = new TextRulerLearnerParameter(Whisk.WINDOSIZE_KEY, "Window Size",
            MLAlgorithmParamType.ML_INT_PARAM);
    result[1] = new TextRulerLearnerParameter(Whisk.ERROR_THRESHOLD_KEY, "Maximum Error Threshold",
            MLAlgorithmParamType.ML_FLOAT_PARAM);
    result[2] = new TextRulerLearnerParameter(Whisk.POSTAG_ROOTTYPE_KEY, "PosTag Root Type",
            MLAlgorithmParamType.ML_STRING_PARAM);

    return result;
  }

}
