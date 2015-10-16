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

package org.apache.uima.ruta.utils.twl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.core.RutaCorePreferences;
import org.apache.uima.ruta.resource.MultiTreeWordList;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class MultiTWLConverterHandler implements IHandler {
  private class ConverterHandlerJob extends Job {
    ExecutionEvent event;
    private boolean compress;

    ConverterHandlerJob(ExecutionEvent event, boolean compress) {
      super("Converting...");
      this.event = event;
      this.compress = compress;
      setUser(true);
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
      monitor.beginTask("Collecting files...", 1);

      if (HandlerUtil.getCurrentSelection(event) instanceof IStructuredSelection) {
        StructuredSelection selection = (StructuredSelection) HandlerUtil
                .getCurrentSelection(event);
        Iterator<?> iter = selection.iterator();
        IResource first = null;

        List<String> paths = new ArrayList<String>();
        while (iter.hasNext()) {
          Object object = iter.next();
          if (object instanceof IResource) {
            IResource resource = (IResource) object;
            if (first == null) {
              first = resource;
            }
            paths.addAll(getPaths(resource));
          }
        }
        monitor.beginTask("Compiling generated.mtwl...", 1);
        if (!paths.isEmpty()) {
          MultiTreeWordList trie;
          try {
            trie = new MultiTreeWordList(paths.toArray(new String[0]), null);
          } catch (IOException e) {
            RutaAddonsPlugin.error(e);
            return Status.CANCEL_STATUS;
          }

          IPath parent = first.getLocation().removeLastSegments(1);
          IPath newPath = parent.append("generated.mtwl");

          File file = newPath.toFile();
          final String absolutePath = file.getAbsolutePath();

          try {
            trie.createMTWLFile(absolutePath, compress, "UTF-8");
          } catch (IOException e) {
            RutaAddonsPlugin.error(e);
          }

          IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
          IContainer container = myWorkspaceRoot.getContainerForLocation(parent);
          if (container != null) {
            try {
              container.getParent().refreshLocal(IResource.DEPTH_INFINITE,
                      new NullProgressMonitor());
            } catch (CoreException e) {
              RutaAddonsPlugin.error(e);
            }
          }
        }

      }

      monitor.done();
      return Status.OK_STATUS;

    }
  }

  public void addHandlerListener(IHandlerListener handlerListener) {
  }

  public void dispose() {
  }

  public Object execute(ExecutionEvent event) throws ExecutionException {
    IPreferenceStore preferenceStore = RutaIdeUIPlugin.getDefault().getPreferenceStore();
    boolean compress = preferenceStore.getBoolean(RutaCorePreferences.COMPRESS_WORDLISTS);
    new ConverterHandlerJob(event, compress).schedule();
    return null;
  }

  private List<String> getPaths(IResource resource) {
    List<String> paths = new ArrayList<String>();
    if (resource instanceof IFile) {
      IFile file = (IFile) resource;
      String fileExtension = file.getFileExtension();
      if ("txt".equals(fileExtension)) {
        String path = file.getRawLocation().toString();
        paths.add(path);
      }
    } else if (resource instanceof IFolder) {
      IFolder folder = (IFolder) resource;
      try {
        IResource[] members = folder.members();
        for (IResource each : members) {
          paths.addAll(getPaths(each));
        }
      } catch (CoreException e) {
      }
    }
    return paths;
  }

  public boolean isEnabled() {
    return true;
  }

  public boolean isHandled() {
    return true;
  }

  public void removeHandlerListener(IHandlerListener handlerListener) {

  }
}
