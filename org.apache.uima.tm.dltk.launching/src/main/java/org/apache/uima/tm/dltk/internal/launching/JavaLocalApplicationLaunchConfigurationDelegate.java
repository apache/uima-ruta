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

package org.apache.uima.tm.dltk.internal.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.ISourceLocator;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.IDbgpService;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.ui.ScriptStreamProxy;
import org.eclipse.dltk.internal.debug.core.model.ScriptDebugTarget;
import org.eclipse.jdt.launching.JavaLaunchDelegate;
import org.eclipse.ui.console.IOConsole;


public class JavaLocalApplicationLaunchConfigurationDelegate extends JavaLaunchDelegate implements
        ILaunchConfigurationDelegate {

  @Override
  public String getVMArguments(ILaunchConfiguration configuration) throws CoreException {
    return super.getVMArguments(configuration);
  }

  @Override
  public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch,
          IProgressMonitor monitor) throws CoreException {
    // System.out.println("launching");
    IDbgpService dbgpService = null;
    try {
      dbgpService = DLTKDebugPlugin.getDefault().getDbgpService();

      IScriptDebugTarget target = new ScriptDebugTarget("org.apache.uima.tm.dltk.debug.textmarkerModel",
              dbgpService, "hello", launch, null);
      IOConsole cs = new IOConsole("aa", null);
      ScriptStreamProxy proxy = new ScriptStreamProxy(cs);
      target.setStreamProxy(proxy);
      launch.addDebugTarget(target);
      final ISourceLocator sourceLocator = launch.getSourceLocator();
      final TextMarkerSourceLookupDirector l = new TextMarkerSourceLookupDirector();
      launch.setSourceLocator(new ISourceLocator() {

        public Object getSourceElement(IStackFrame stackFrame) {
          Object sourceElement = sourceLocator.getSourceElement(stackFrame);
          if (sourceElement != null)
            return sourceElement;
          return l.getSourceElement(stackFrame);
        }

      });
    } catch (Exception e) {

    }

    super.launch(configuration, mode, launch, monitor);
  }

  public static final String LOCAL_APPLICATION = "debug.localJavaApplication";

}
