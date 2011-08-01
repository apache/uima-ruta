package org.apache.uima.tm.dltk.internal.debug.ui.launchConfigurations;

import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.apache.uima.tm.dltk.internal.debug.TextMarkerDebugPlugin;

public class TextMarkerMainLaunchConfigurationTab extends MainLaunchConfigurationTab {

  public TextMarkerMainLaunchConfigurationTab(String mode) {
    super(mode);
  }

  /*
   * @seeorg.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#
   * breakOnFirstLinePrefEnabled(org.eclipse.dltk.core.PreferencesLookupDelegate)
   */
  @Override
  protected boolean breakOnFirstLinePrefEnabled(PreferencesLookupDelegate delegate) {
    return delegate.getBoolean(TextMarkerDebugPlugin.PLUGIN_ID,
            DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE);
  }

  /*
   * @see
   * org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#dbpgLoggingPrefEnabled
   * (org.eclipse.dltk.core.PreferencesLookupDelegate)
   */
  @Override
  protected boolean dbpgLoggingPrefEnabled(PreferencesLookupDelegate delegate) {
    return delegate.getBoolean(TextMarkerDebugPlugin.PLUGIN_ID,
            DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);
  }

  /*
   * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#getNatureID()
   */
  @Override
  protected String getNatureID() {
    return TextMarkerNature.NATURE_ID;
  }

}
