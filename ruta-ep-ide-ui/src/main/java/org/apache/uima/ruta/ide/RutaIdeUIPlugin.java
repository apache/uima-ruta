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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.ide.core.RutaNature;
import org.apache.uima.ruta.ide.launching.GenericRutaInstallType;
import org.apache.uima.ruta.ide.ui.text.RutaTextTools;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.environment.FileAsFileHandle;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.core.internal.environment.LocalEnvironment;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.launching.ScriptRuntime.DefaultInterpreterEntry;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.packageadmin.PackageAdmin;

/**
 * The activator class controls the plug-in life cycle
 */
public class RutaIdeUIPlugin extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.apache.uima.ruta.ide.ui"; //$NON-NLS-1$

  private RutaTextTools textTools;

  private BundleContext bundleContext;

  // The shared instance
  private static RutaIdeUIPlugin plugin;

  /**
   * The constructor
   */
  public RutaIdeUIPlugin() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    bundleContext = context;

    checkRutaInterpreter();

//    IWorkbench workbench = PlatformUI.getWorkbench();
//    workbench.addWorkbenchListener(new IWorkbenchListener() {
//      public boolean preShutdown(IWorkbench workbench, boolean forced) {
//        // close all CAS Editors if on is focused when exiting Eclipse
//        final IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
//        IEditorPart activeEditor = activePage.getActiveEditor();
//        if (activeEditor != null
//                && activeEditor.getClass().getName()
//                        .equals("org.apache.uima.caseditor.editor.AnnotationEditor")) {
//          IEditorReference[] editorReferences = activePage.getEditorReferences();
//          List<IEditorReference> toClose = new ArrayList<IEditorReference>();
//          for (IEditorReference each : editorReferences) {
//            if (each.getId().equals("org.apache.uima.caseditor.editor")) {
//              toClose.add(each);
//            }
//          }
//          activePage.closeEditors(toClose.toArray(new IEditorReference[0]), true);
//        }
//        return true;
//      }
//
//      public void postShutdown(IWorkbench workbench) {
//
//      }
//    });
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
  public static RutaIdeUIPlugin getDefault() {
    return plugin;
  }

  public synchronized RutaTextTools getTextTools() {
    if (textTools == null)
      textTools = new RutaTextTools(true);
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

  private void checkRutaInterpreter() throws IOException, CoreException {
    // TODO move this method... it is called way too early when eclipse starts
    IEnvironment localEnv = LocalEnvironment.getInstance();
    DefaultInterpreterEntry defaultInterpreterEntry = new DefaultInterpreterEntry(
            RutaNature.NATURE_ID, localEnv.getId());
    IInterpreterInstall defaultInterpreterInstall = null;
    try {
      defaultInterpreterInstall = ScriptRuntime
              .getDefaultInterpreterInstall(defaultInterpreterEntry);
    } catch (Exception e) {
      error(e);
    }
    if(defaultInterpreterInstall == null) {
      // initialized too early
      return;
    }
    IFileHandle rawInstallLocation = defaultInterpreterInstall.getRawInstallLocation();
    if (!rawInstallLocation.exists()) {
      String pluginIdToJarPath = pluginIdToJarPath(RutaIdeUIPlugin.PLUGIN_ID);
      FileAsFileHandle fh = new FileAsFileHandle(new File(pluginIdToJarPath));
      defaultInterpreterInstall.setInstallLocation(fh);
      ScriptRuntime.setDefaultInterpreterInstall(defaultInterpreterInstall,
              new NullProgressMonitor());

    }
  }

}
