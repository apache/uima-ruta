package org.apache.uima.tm.textmarker.testing.preferences;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.testing.TestingPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;


public class TestingPreferenceConstants {

  public static final String EVALUATOR_FACTORY = "EVALUATOR_FACTORY";
  
  public static final String DEFAULT_EVALUATOR_FACTORY = "org.apache.uima.tm.textmarker.testing.exactCasEvaluator";

  public static final String AUTOMATED_FILE_SYNCRONIZATION = "AUTOMATED_FILE_SYNCRONIZATION";
  
  public static final String LOAD_OLD_TEST_RESULTS = "LOAD_OLD_TEST_RESULTS";

  public static String[][] EVALUATORS = getEvaluators();

  public static String[][] getEvaluators() {

    List<String[]> result = new ArrayList<String[]>();
    int size = 0;

    IExtension[] evalExtensions = Platform.getExtensionRegistry().getExtensionPoint(
            TestingPlugin.PLUGIN_ID, "evaluators").getExtensions();
    for (IExtension extension : evalExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        size++;
      }
    }

    String[][] evalFactories = new String[size][2];
    int count = 0;
    for (IExtension extension : evalExtensions) {
        IConfigurationElement[] configurationElements = extension.getConfigurationElements();
        for (IConfigurationElement configurationElement : configurationElements) {
          evalFactories[count][0] = configurationElement.getAttribute("name");
          evalFactories[count][1] = configurationElement.getAttribute("id");
          count++;
        }
      }
    return evalFactories;
  }

}
