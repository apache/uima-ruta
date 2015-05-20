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

package org.apache.uima.ruta.addons;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.testing.evaluator.ICasEvaluatorFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RutaAddonsPlugin extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.apache.uima.ruta.addons"; //$NON-NLS-1$

  public static final String ATT_FACTORY = "factory";

  // The shared instance
  private static RutaAddonsPlugin plugin;

  /**
   * The constructor
   */
  public RutaAddonsPlugin() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static RutaAddonsPlugin getDefault() {
    return plugin;
  }

  /**
   * Returns an image descriptor for the image file at the given plug-in relative path
   * 
   * @param path
   *          the path
   * @return the image descriptor
   */
  public static ImageDescriptor getImageDescriptor(String path) {
    return imageDescriptorFromPlugin(PLUGIN_ID, path);
  }

  public static List<ICasEvaluatorFactory> getCasEvaluators() {
    List<ICasEvaluatorFactory> result = new ArrayList<ICasEvaluatorFactory>();
    IExtension[] evalExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaAddonsPlugin.PLUGIN_ID, "evaluators").getExtensions();
    for (IExtension extension : evalExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object factoryObject = null;
        try {
          factoryObject = configurationElement.createExecutableExtension(ATT_FACTORY);
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (factoryObject instanceof ICasEvaluatorFactory) {
          ICasEvaluatorFactory evalFactory = (ICasEvaluatorFactory) factoryObject;
          result.add(evalFactory);
        }
      }
    }
    return result;
  }

  public static ICasEvaluatorFactory getCasEvaluatorFactoryById(String id) {
    IExtension[] evalExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaAddonsPlugin.PLUGIN_ID, "evaluators").getExtensions();
    for (IExtension extension : evalExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        String elementId = configurationElement.getAttribute("id");
        if (elementId.equals(id)) {
          try {
            Object factoryObject = configurationElement.createExecutableExtension(ATT_FACTORY);
            if (factoryObject instanceof ICasEvaluatorFactory) {
              return ((ICasEvaluatorFactory) factoryObject);

            }
          } catch (CoreException e) {
            e.printStackTrace();
          }
        }

      }
    }
    return null;
  }

  public static void error(Throwable t) {
    plugin.getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, t.getMessage(), t));
  }

  public static IFile getIFile(String location) {
    IPath s = Path.fromOSString(location);
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IFile ifile = workspace.getRoot().getFileForLocation(s);
    return ifile;
  }
  
}
