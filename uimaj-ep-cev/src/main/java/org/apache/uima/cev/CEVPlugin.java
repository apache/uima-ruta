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

package org.apache.uima.cev;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cev.artifactViewer.ArtifactViewerComparator;
import org.apache.uima.cev.extension.ICEVArtifactViewerFactory;
import org.apache.uima.cev.extension.ICEVEditorFactory;
import org.apache.uima.cev.extension.ICEVSearchStrategy;
import org.apache.uima.cev.extension.ICEVSearchStrategyFactory;
import org.apache.uima.cev.extension.ICEVViewFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.IWorkbenchSiteProgressService;
import org.osgi.framework.BundleContext;

public class CEVPlugin extends AbstractUIPlugin {

  public static final String PLUGIN_ID = "org.apache.uima.cev";

  private static final String ATT_PRIORITY = "priority";

  public static final String ATT_ADAPTER = "adapter";

  public static final String ATT_FACTORY = "factory";

  // Shared instance
  private static CEVPlugin plugin;

  public CEVPlugin() {
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

  public static CEVPlugin getDefault() {
    return plugin;
  }

  public static ImageDescriptor getImageDescriptor(String path) {
    return imageDescriptorFromPlugin(PLUGIN_ID, path);
  }

  public static Map<Class<?>, ICEVEditorFactory> getEditorAdapters() {
    Map<Class<?>, ICEVEditorFactory> result = new HashMap<Class<?>, ICEVEditorFactory>();
    IExtension[] editorExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(CEVPlugin.PLUGIN_ID, "cevEditors").getExtensions();
    for (IExtension extension : editorExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object factoryObject = null;
        try {
          factoryObject = configurationElement.createExecutableExtension(ATT_FACTORY);
        } catch (CoreException e) {
          CEVPlugin.error(e);
        }
        if (factoryObject instanceof ICEVEditorFactory) {
          ICEVEditorFactory editorFactory = (ICEVEditorFactory) factoryObject;
          result.put(editorFactory.getAdapterInterface(), editorFactory);
        }
      }
    }
    return result;
  }

  public static Map<Class<?>, ICEVViewFactory> getViewAdapters() {
    Map<Class<?>, ICEVViewFactory> result = new HashMap<Class<?>, ICEVViewFactory>();
    IExtension[] viewExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(CEVPlugin.PLUGIN_ID, "cevViews").getExtensions();
    for (IExtension extension : viewExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object factoryObject = null;
        try {
          factoryObject = configurationElement.createExecutableExtension(ATT_FACTORY);
        } catch (CoreException e) {
          CEVPlugin.error(e);
        }
        if (factoryObject instanceof ICEVViewFactory) {
          ICEVViewFactory viewFactory = (ICEVViewFactory) factoryObject;
          result.put(viewFactory.getAdapterInterface(), viewFactory);
        }
      }
    }
    return result;
  }

  public static List<ICEVArtifactViewerFactory> getArtifactViewerFactories() {
    List<ICEVArtifactViewerFactory> result = new ArrayList<ICEVArtifactViewerFactory>();
    IExtension[] viewerExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(CEVPlugin.PLUGIN_ID, "cevArtifactViewers").getExtensions();
    for (IExtension extension : viewerExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object factoryObject = null;
        String priorityString = null;
        try {
          factoryObject = configurationElement.createExecutableExtension(ATT_FACTORY);
          priorityString = configurationElement.getAttribute(ATT_PRIORITY);
        } catch (CoreException e) {
          CEVPlugin.error(e);
        }
        if (factoryObject instanceof ICEVArtifactViewerFactory) {
          ICEVArtifactViewerFactory viewerFactory = (ICEVArtifactViewerFactory) factoryObject;
          int priority = 100;
          try {
            priority = Integer.parseInt(priorityString);
          } catch (NumberFormatException e) {
            // bad string
          }
          viewerFactory.setPriority(priority);
          result.add(viewerFactory);
        }
      }
    }
    Collections.sort(result, new ArtifactViewerComparator());
    return result;
  }

  public static List<ICEVSearchStrategy> getSearchStrategies() {
    List<ICEVSearchStrategy> result = new ArrayList<ICEVSearchStrategy>();
    IExtension[] searchExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(CEVPlugin.PLUGIN_ID, "cevSearchStrategies").getExtensions();
    for (IExtension extension : searchExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object factoryObject = null;
        String priorityString = null;
        try {
          factoryObject = configurationElement.createExecutableExtension(ATT_FACTORY);
          priorityString = configurationElement.getAttribute(ATT_PRIORITY);
        } catch (CoreException e) {
          CEVPlugin.error(e);
        }
        if (factoryObject instanceof ICEVSearchStrategyFactory) {
          ICEVSearchStrategyFactory searchFactory = (ICEVSearchStrategyFactory) factoryObject;
          int priority = 100;
          try {
            priority = Integer.parseInt(priorityString);
          } catch (NumberFormatException e) {
            // bad string
          }
          ICEVSearchStrategy strategy = searchFactory.createSearchStrategy(priority);
          result.add(strategy);
        }
      }
    }
    Collections.sort(result, new Comparator<ICEVSearchStrategy>() {

      public int compare(ICEVSearchStrategy o1, ICEVSearchStrategy o2) {
        if (o1.getPriority() < o2.getPriority()) {
          return -1;
        } else if (o1.getPriority() > o2.getPriority()) {
          return 1;
        } else {
          return 0;
        }
      }

    });
    return result;
  }

  public static void error(Throwable t) {
    plugin.getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, t.getMessage(), t));
  }

  public static void schedule(Job job, IWorkbenchSite site) {
    if (site != null) {
      IWorkbenchSiteProgressService siteProgress = (IWorkbenchSiteProgressService) site
              .getAdapter(IWorkbenchSiteProgressService.class);
      if (siteProgress != null) {
        siteProgress.schedule(job, 0, true /* use half-busy cursor */);
        return;
      }
    }
    job.schedule();
  }

  public static void runInUIThread(final Runnable runnable) {
    if (Display.getCurrent() != null) {
      BusyIndicator.showWhile(Display.getCurrent(), runnable);
    } else {
      Display.getDefault().syncExec(new Runnable() {
        public void run() {
          BusyIndicator.showWhile(Display.getCurrent(), runnable);
        }
      });
    }
  }
}
