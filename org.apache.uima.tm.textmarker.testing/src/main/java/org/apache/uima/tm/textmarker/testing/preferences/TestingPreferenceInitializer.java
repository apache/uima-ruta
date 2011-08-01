package org.apache.uima.tm.textmarker.testing.preferences;

import org.apache.uima.tm.textmarker.testing.TestingPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;


public class TestingPreferenceInitializer extends AbstractPreferenceInitializer {
  
  @Override
  public void initializeDefaultPreferences () {
    IPreferenceStore store = TestingPlugin.getDefault().getPreferenceStore();
    
    store.setDefault(TestingPreferenceConstants.EVALUATOR_FACTORY, 
            TestingPreferenceConstants.DEFAULT_EVALUATOR_FACTORY);
    
    store.setDefault(TestingPreferenceConstants.AUTOMATED_FILE_SYNCRONIZATION, "0");
    store.setDefault(TestingPreferenceConstants.LOAD_OLD_TEST_RESULTS, "0");
    
    
  }

}
