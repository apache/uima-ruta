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

package org.apache.uima.tm.dltk.textmarker.launching;

import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.osgi.framework.BundleContext;

public class TextMarkerLaunchingPlugin extends Plugin {

  public static final String PLUGIN_ID = "org.apache.uima.tm.dltk.launching";

  private static TextMarkerLaunchingPlugin plugin;

  public TextMarkerLaunchingPlugin() {
    plugin = this;
  }

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  public static TextMarkerLaunchingPlugin getDefault() {
    return plugin;
  }

  public static String getUniqueIdentifier() {
    return PLUGIN_ID;
  }

  public IFileHandle getConsoleProxy(IExecutionEnvironment exeEnv) throws IOException {
    IDeployment deployment = exeEnv.createDeployment();
    IPath path = deployment.add(this.getBundle(), "console");
    path.append("ConsoleProxy.js");
    return deployment.getFile(path);

  }

}
