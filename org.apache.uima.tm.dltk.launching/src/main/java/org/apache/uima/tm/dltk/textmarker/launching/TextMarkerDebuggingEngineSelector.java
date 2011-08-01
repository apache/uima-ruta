package org.apache.uima.tm.dltk.textmarker.launching;

import org.apache.uima.tm.dltk.internal.debug.TextMarkerDebugConstants;
import org.apache.uima.tm.dltk.internal.debug.TextMarkerDebugPlugin;
import org.eclipse.dltk.core.DLTKIdContributionSelector;
import org.eclipse.dltk.core.PreferencesLookupDelegate;


public class TextMarkerDebuggingEngineSelector extends DLTKIdContributionSelector {
  @Override
  protected String getSavedContributionId(PreferencesLookupDelegate delegate) {
    return delegate.getString(TextMarkerDebugPlugin.PLUGIN_ID,
            TextMarkerDebugConstants.DEBUGGING_ENGINE_ID_KEY);
  }

}
