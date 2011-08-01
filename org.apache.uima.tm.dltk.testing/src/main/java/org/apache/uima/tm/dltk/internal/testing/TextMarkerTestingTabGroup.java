package org.apache.uima.tm.dltk.internal.testing;

import org.apache.uima.tm.dltk.internal.debug.ui.interpreters.TextMarkerInterpreterTab;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.dltk.debug.ui.launchConfigurations.ScriptArgumentsTab;


public class TextMarkerTestingTabGroup extends AbstractLaunchConfigurationTabGroup {

  public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
    TextMarkerTestingMainLaunchConfigurationTab main = new TextMarkerTestingMainLaunchConfigurationTab(
            mode);
    ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] { main,
        new ScriptArgumentsTab(), new TextMarkerInterpreterTab(main), new EnvironmentTab(),
        new CommonTab() {
          @Override
          public void performApply(ILaunchConfigurationWorkingCopy configuration) {
            super.performApply(configuration);
            configuration.setAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_CONSOLE, (String) null);
          }
        } };
    setTabs(tabs);
  }

}
