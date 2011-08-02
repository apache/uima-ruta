package org.apache.uima.tm.dltk.internal.debug.ui.launcher;

import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.dltk.internal.debug.ui.launcher.AbstractScriptLaunchShortcut;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.apache.uima.tm.dltk.textmarker.launching.TextMarkerLaunchConfigurationConstants;

public class TextMarkerLaunchShortcut extends AbstractScriptLaunchShortcut {
  @Override
  protected ILaunchConfigurationType getConfigurationType() {
    return getLaunchManager().getLaunchConfigurationType(
            TextMarkerLaunchConfigurationConstants.ID_TM_SCRIPT);
  }

  @Override
  protected String getNatureId() {
    return TextMarkerNature.NATURE_ID;
  }
}
