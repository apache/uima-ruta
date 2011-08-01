package org.apache.uima.tm.dltk.textmarker.launching;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.InterpreterConfig;


public class TextMarkerLaunchConfigurationDelegate extends
        AbstractScriptLaunchConfigurationDelegate {

  @Override
  protected void runRunner(ILaunchConfiguration configuration, IInterpreterRunner runner,
          InterpreterConfig config, ILaunch launch, IProgressMonitor monitor) throws CoreException {
    if (runner instanceof IConfigurableRunner) {
      ITextMarkerInterpreterRunnerConfig runnerConfig = getConfig();
      if (runnerConfig != null) {
        IConfigurableRunner rc = (IConfigurableRunner) runner;
        rc.setRunnerConfig(runnerConfig);
      }
    }
    runner.run(config, launch, monitor);
  }

  public ITextMarkerInterpreterRunnerConfig getConfig() {
    return null;
  }

  @Override
  public String getLanguageId() {
    return TextMarkerNature.NATURE_ID;
  }
}
