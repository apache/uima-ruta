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

package org.apache.uima.textmarker.ide.testing;

import org.apache.uima.textmarker.ide.launching.TextMarkerLaunchConfigurationDelegate;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.dltk.compiler.util.Util;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.testing.DLTKTestingConstants;
import org.eclipse.dltk.testing.DLTKTestingCore;


public class TextMarkerTestingLaunchConfigurationDelegate extends
        TextMarkerLaunchConfigurationDelegate implements ILaunchConfigurationDelegate {
  private ITextMarkerTestingEngine engine;

  @Override
  protected InterpreterConfig createInterpreterConfig(ILaunchConfiguration configuration,
          ILaunch launch) throws CoreException {
    // We need to create correct execute script for this element.
    InterpreterConfig config = super.createInterpreterConfig(configuration, launch);
    ITextMarkerTestingEngine[] engines = TextMarkerTestingEngineManager.getEngines();
    String engineId = configuration.getAttribute(DLTKTestingConstants.ATTR_ENGINE_ID,
            Util.EMPTY_STRING);
    for (int i = 0; i < engines.length; i++) {
      if (engines[i].getId().equals(engineId)) {
        engines[i].correctLaunchConfiguration(config, configuration);
        this.engine = engines[i];
        break;
      }
    }
    return config;
  }

  @Override
  protected void runRunner(ILaunchConfiguration configuration, IInterpreterRunner runner,
          InterpreterConfig config, ILaunch launch, IProgressMonitor monitor) throws CoreException {

    if (engine != null) {
      DLTKTestingCore.registerTestingProcessor(launch, engine.getProcessor(launch));
    }

    super.runRunner(configuration, runner, config, launch, monitor);
  }
}
