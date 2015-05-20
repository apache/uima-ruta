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

package org.apache.uima.ruta.ide.launching;

import org.apache.uima.ruta.ide.core.RutaNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.InterpreterConfig;

public class RutaLaunchConfigurationDelegateOld extends AbstractScriptLaunchConfigurationDelegate {

  @Override
  protected void runRunner(ILaunchConfiguration configuration, IInterpreterRunner runner,
          InterpreterConfig config, ILaunch launch, IProgressMonitor monitor) throws CoreException {
    if (runner instanceof IConfigurableRunner) {
      IRutaInterpreterRunnerConfig runnerConfig = getConfig();
      if (runnerConfig != null) {
        IConfigurableRunner rc = (IConfigurableRunner) runner;
        rc.setRunnerConfig(runnerConfig);
      }
    }
    runner.run(config, launch, monitor);
  }

  public IRutaInterpreterRunnerConfig getConfig() {
    return null;
  }

  @Override
  public String getLanguageId() {
    return RutaNature.NATURE_ID;
  }
}
