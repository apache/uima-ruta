package org.apache.uima.tm.textruler.extension;

import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;

/**
 * If you want to add an own algorithm to the TextRuler-System, you can do this by providing two
 * classes:
 * 
 * One algorithm class that implements the interface
 * 
 * TextRulerLearner
 * 
 * And one factory class that can create an instance of that algorithm. This factory has to
 * implement the interface
 * 
 * MLAlgoriothmFactory
 * 
 * which gets created and used by adding an extension point to
 * 
 * org.apache.uima.tm.textruler.algorithms
 * 
 */
public interface TextRulerLearner {

  enum TextRulerLearnerState {
    ML_UNDEFINED, ML_INITIALIZING, ML_RUNNING, ML_ERROR, ML_ABORTED, ML_DONE
  };

  /**
   * There the magic has to be placed...
   */
  void run();

  /**
   * this method gets called from the UI and passes a hashMap with key value coded parameters that
   * your corresponding TextRulerLearnerFactory declared by its getAlgorithmParameters method.
   */
  void setParameters(Map<String, Object> params);

  /**
   * If any TextMarker-Rules result is available (yet), the system asks your algorithm for it by
   * calling this method.
   */
  String getResultString();

  /**
   * 
   * @return
   */
  AnalysisEngine getAnalysisEngine();

  /**
   * 
   * @return
   */
  CAS getTestCAS();
}
