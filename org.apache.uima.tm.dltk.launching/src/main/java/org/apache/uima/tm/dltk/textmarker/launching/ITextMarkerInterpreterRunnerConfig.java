package org.apache.uima.tm.dltk.textmarker.launching;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.VMRunnerConfiguration;

public interface ITextMarkerInterpreterRunnerConfig {

  public String getRunnerClassName(InterpreterConfig config, ILaunch launch, IJavaProject project);

  public String[] computeClassPath(InterpreterConfig config, ILaunch launch, IJavaProject project)
          throws Exception;

  public String[] getProgramArguments(InterpreterConfig config, ILaunch launch, IJavaProject project);

  public void adjustRunnerConfiguration(VMRunnerConfiguration vconfig, InterpreterConfig iconfig,
          ILaunch launch, IJavaProject project);
}
