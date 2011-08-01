package org.apache.uima.tm.dltk.internal.debug.ui.launchConfigurations;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.dltk.debug.ui.launchConfigurations.ScriptCommonTab;

import org.apache.uima.tm.dltk.internal.debug.ui.interpreters.TextMarkerInterpreterTab;

public class TextMarkerTabGroup extends AbstractLaunchConfigurationTabGroup {
  public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
    TextMarkerMainLaunchConfigurationTab main = new TextMarkerMainLaunchConfigurationTab(mode);
    ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] { main,
        new TextMarkerArgumentsTab(), new TextMarkerInterpreterTab(main), new EnvironmentTab(),
        new ScriptCommonTab(main) };
    setTabs(tabs);
  }
}
