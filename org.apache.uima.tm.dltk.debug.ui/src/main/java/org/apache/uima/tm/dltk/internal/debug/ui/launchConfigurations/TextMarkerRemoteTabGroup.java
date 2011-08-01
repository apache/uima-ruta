package org.apache.uima.tm.dltk.internal.debug.ui.launchConfigurations;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

public class TextMarkerRemoteTabGroup extends AbstractLaunchConfigurationTabGroup {

  public TextMarkerRemoteTabGroup() {
  }

  public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
    ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] { new TextMarkerRemoteTab() };
    setTabs(tabs);
  }

}
