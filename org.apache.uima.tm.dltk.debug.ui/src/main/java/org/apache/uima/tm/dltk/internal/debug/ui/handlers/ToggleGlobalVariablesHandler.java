package org.apache.uima.tm.dltk.internal.debug.ui.handlers;

import org.eclipse.dltk.debug.ui.handlers.AbstractToggleGlobalVariableHandler;
import org.eclipse.dltk.ui.PreferencesAdapter;
import org.eclipse.jface.preference.IPreferenceStore;

import org.apache.uima.tm.dltk.internal.debug.TextMarkerDebugConstants;
import org.apache.uima.tm.dltk.internal.debug.TextMarkerDebugPlugin;

public class ToggleGlobalVariablesHandler extends AbstractToggleGlobalVariableHandler {
  /*
   * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#getModelId()
   */
  @Override
  protected String getModelId() {
    return TextMarkerDebugConstants.DEBUG_MODEL_ID;
  }

  /*
   * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#getPreferenceStore()
   */
  @Override
  protected IPreferenceStore getPreferenceStore() {
    return new PreferencesAdapter(TextMarkerDebugPlugin.getDefault().getPluginPreferences());
  }
}
