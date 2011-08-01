package org.apache.uima.tm.dltk.debugger;

import org.apache.uima.tm.dltk.internal.debug.TextMarkerDebugConstants;
import org.apache.uima.tm.dltk.internal.debug.TextMarkerDebugPlugin;
import org.apache.uima.tm.dltk.textmarker.internal.launching.TextMarkerInterpreterRunner;
import org.apache.uima.tm.dltk.textmarker.launching.IConfigurableRunner;
import org.apache.uima.tm.dltk.textmarker.launching.ITextMarkerInterpreterRunnerConfig;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.launching.DebuggingEngineRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;


public class TextMarkerDebuggerRunner extends DebuggingEngineRunner implements IConfigurableRunner {

  private static String ENGINE_ID = "org.apache.uima.tm.dltk.debugger.preferences.TextMarkerDebuggerRunnerFactory";

  ITextMarkerInterpreterRunnerConfig runnerconfig = TextMarkerInterpreterRunner.DEFAULT_CONFIG;

  public TextMarkerDebuggerRunner(IInterpreterInstall install) {
    super(install);
  }

  @Override
  public String getDebugModelId() {
    return TextMarkerDebugConstants.DEBUG_MODEL_ID;
  }

  @Override
  public void run(InterpreterConfig config, ILaunch launch, IProgressMonitor monitor)
          throws CoreException {
    initializeLaunch(launch, config, createPreferencesLookupDelegate(launch));
    TextMarkerInterpreterRunner.doRunImpl(config, launch, this.runnerconfig, monitor);
  }

  public void setRunnerConfig(ITextMarkerInterpreterRunnerConfig config) {
    this.runnerconfig = config;
  }

  @Override
  protected String getDebuggingEngineId() {
    return ENGINE_ID;
  }

  @Override
  protected String getDebugPreferenceQualifier() {
    return TextMarkerDebugPlugin.PLUGIN_ID;
  }

  @Override
  protected String getDebuggingEnginePreferenceQualifier() {
    return TextMarkerDebuggerPlugin.PLUGIN_ID;
  }

  protected String getLoggingEnabledPreferenceKey() {
    // not yet supported...
    return null;
  }

  @Override
  protected String getLogFileNamePreferenceKey() {
    // not yet supported...
    return null;
  }

  protected String getLogFilePathPreferenceKey() {
    // not yet supported...
    return null;
  }

  @Override
  protected InterpreterConfig addEngineConfig(InterpreterConfig config,
          PreferencesLookupDelegate delegate, ILaunch launch) throws CoreException {
    return config;
  }
}
