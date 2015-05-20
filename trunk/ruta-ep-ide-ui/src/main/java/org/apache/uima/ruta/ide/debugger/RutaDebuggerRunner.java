/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.ide.debugger;

import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.debug.RutaDebugConstants;
import org.apache.uima.ruta.ide.launching.IConfigurableRunner;
import org.apache.uima.ruta.ide.launching.IRutaInterpreterRunnerConfig;
import org.apache.uima.ruta.ide.launching.RutaInterpreterRunner;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.launching.DebuggingEngineRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;

public class RutaDebuggerRunner extends DebuggingEngineRunner implements IConfigurableRunner {

  private static String ENGINE_ID = "org.apache.uima.ruta.ide.debugger.preferences.RutaDebuggerRunnerFactory";

  IRutaInterpreterRunnerConfig runnerconfig = RutaInterpreterRunner.DEFAULT_CONFIG;

  public RutaDebuggerRunner(IInterpreterInstall install) {
    super(install);
  }

  @Override
  public String getDebugModelId() {
    return RutaDebugConstants.DEBUG_MODEL_ID;
  }

  @Override
  public void run(InterpreterConfig config, ILaunch launch, IProgressMonitor monitor)
          throws CoreException {
    initializeLaunch(launch, config, createPreferencesLookupDelegate(launch));
    RutaInterpreterRunner.doRunImpl(config, launch, this.runnerconfig, monitor);
  }

  public void setRunnerConfig(IRutaInterpreterRunnerConfig config) {
    this.runnerconfig = config;
  }

  @Override
  protected String getDebuggingEngineId() {
    return ENGINE_ID;
  }

  @Override
  protected String getDebugPreferenceQualifier() {
    return RutaIdeUIPlugin.PLUGIN_ID;
  }

  @Override
  protected String getDebuggingEnginePreferenceQualifier() {
    return RutaIdeUIPlugin.PLUGIN_ID;
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
