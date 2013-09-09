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

package org.apache.uima.ruta.ide;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.packageadmin.PackageAdmin;

/**
 * The activator class controls the plug-in life cycle
 */
public class RutaIdeCorePlugin extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.apache.uima.ruta.ide"; //$NON-NLS-1$

  // The shared instance
  private static RutaIdeCorePlugin plugin;

  private BundleContext bundleContext;
  
  /**
   * The constructor
   */
  public RutaIdeCorePlugin() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    bundleContext = context;
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
  public static RutaIdeCorePlugin getDefault() {
    return plugin;
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
    path.append("ConsoleProxy.ruta");
    return deployment.getFile(path);
  }
  
  public Bundle getBundle(String bundleName) {
    Bundle[] bundles = getBundles(bundleName, null);
    if (bundles != null && bundles.length > 0)
      return bundles[0];
    return null;
  }

  public Bundle[] getBundles(String bundleName, String version) {
    Bundle[] bundles = Platform.getBundles(bundleName, version);
    if (bundles != null)
      return bundles;
    // Accessing bundle which is not resolved
    PackageAdmin admin = (PackageAdmin) bundleContext.getService(bundleContext
            .getServiceReference(PackageAdmin.class.getName()));
    bundles = admin.getBundles(bundleName, version);
    if (bundles != null && bundles.length > 0)
      return bundles;
    return null;
  }

  public String pluginIdToJarPath(String pluginId) throws IOException {
    Bundle bundle = getBundle(pluginId);
    URL url = bundle.getEntry("/");
    if (url == null) {
      throw new IOException();
    }
    return FileLocator.toFileURL(url).getFile();
  }

}
