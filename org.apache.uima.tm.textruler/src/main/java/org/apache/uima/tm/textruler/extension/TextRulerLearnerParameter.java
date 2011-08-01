package org.apache.uima.tm.textruler.extension;

/**
 * With your TextRulerLearnerFactory-class you can specify some parameters your algorithm has (e.g.
 * a window size for extracting features around a token or such). This is done with an array of
 * 
 * TextRulerLearnerParameter
 * 
 * objects. In the TextRuler UI in Eclipse some widgets get automatically created for those
 * parameters if the algorithm is selected in the algorithm list.
 */
public class TextRulerLearnerParameter {

  public enum MLAlgorithmParamType {
    ML_STRING_PARAM, ML_BOOL_PARAM, ML_INT_PARAM, ML_FLOAT_PARAM, ML_SELECT_PARAM
  };

  public static class MLParameterSelectValue {
    public String id;

    public String name;

    public MLParameterSelectValue(String id, String name) {
      this.id = id;
      this.name = name;
    }
  }

  public String id;

  public String name;

  public MLAlgorithmParamType type;

  public MLParameterSelectValue[] selectValues;

  public TextRulerLearnerParameter(String id, String name, MLAlgorithmParamType type) {
    this.id = id;
    this.name = name;
    this.type = type;
    selectValues = null;
  }

  public TextRulerLearnerParameter(String id, String name, MLAlgorithmParamType type,
          MLParameterSelectValue[] values) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.selectValues = values;
  }

}
