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

package org.apache.uima.textmarker.ide.launching;

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
