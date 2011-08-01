package org.apache.uima.tm.textruler.extension;

import java.util.Map;
import java.util.Set;

/**
 * The TextRuler system uses this interface to create an algorithm you added to it via the extension
 * point.
 * 
 * Please see TextRulerLearner for more detailed information.
 */
public interface TextRulerLearnerFactory {

  /**
   * @param inputFolderPath
   *          the input folder path where e.g. example XMIs are located
   * @param additionalFolderPath TODO
   * @param preprocessorTMfile
   *          the preprocessing TextMarker file (this is important for finding the type system and
   *          analysis engine descriptor file)
   * @param tempFolderPath
   *          a folder name that you can use as temporary folder (caution: you need to create it if
   *          you want to use it!)
   * @param fullSlotTypeNames
   *          an array with the slot-names to learn (full qualified UIMA type names)
   * @param delegate
   *          a delegate that can be notified for status updates and asked for aborting the
   *          algorithm
   * @param the
   *          TextMarker filter set (full qualified UIMA type names)
   * @return the algorithm of your class that implements TextRulerLearner
   */
  public TextRulerLearner createAlgorithm(String inputFolderPath, String additionalFolderPath,
          String preprocessorTMfile, String tempFolderPath, String[] fullSlotTypeNames,
          Set<String> filterSet, TextRulerLearnerDelegate delegate);

  /**
   * @return null or an array with MLAlgorithmParameters your TextRulerLearner wants to get from the
   *         GUI
   */
  public TextRulerLearnerParameter[] getAlgorithmParameters();

  /**
   * @return null or a key value set for the standard values of your algorithm's parameters
   */
  public Map<String, Object> getAlgorithmParameterStandardValues();

}
