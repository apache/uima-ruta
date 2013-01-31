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

package org.apache.uima.textmarker.ide;

import java.io.IOException;

import org.apache.uima.textmarker.ide.ui.text.TextMarkerTextTools;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class TextMarkerIdePlugin extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.apache.uima.textmarker.ide"; //$NON-NLS-1$

  private TextMarkerTextTools textTools;

  // The shared instance
  private static TextMarkerIdePlugin plugin;

  /**
   * The constructor
   */
  public TextMarkerIdePlugin() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static TextMarkerIdePlugin getDefault() {
    return plugin;
  }

  public synchronized TextMarkerTextTools getTextTools() {
    if (textTools == null)
      textTools = new TextMarkerTextTools(true);
    return textTools;
  }

  public static void error(String message, Throwable t) {
    plugin.getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, message, t));
  }

  public static void error(Throwable t) {
    error(t.getMessage(), t);
  }

  public IFileHandle getConsoleProxy(IExecutionEnvironment exeEnv) throws IOException {
    IDeployment deployment = exeEnv.createDeployment();
    IPath path = deployment.add(this.getBundle(), "console");
    path.append("ConsoleProxy.tm");
    return deployment.getFile(path);

  }
}
