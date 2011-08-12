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

package org.apache.uima.textmarker.utils.twl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.textmarker.resource.MultiTreeWordList;
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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;


public class MultiTWLConverterHandler implements IHandler {
  private class ConverterHandlerJob extends Job {
    ExecutionEvent event;

    ConverterHandlerJob(ExecutionEvent event) {
      super("Converting...");
      this.event = event;
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
          MultiTreeWordList trie = new MultiTreeWordList(paths.toArray(new String[0]));

          IPath parent = first.getLocation().removeLastSegments(1);
          IPath newPath = parent.append("generated.mtwl");

          File file = newPath.toFile();
          final String absolutePath = file.getAbsolutePath();

          trie.createMTWLFile(absolutePath);

          IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
          IContainer container = myWorkspaceRoot.getContainerForLocation(parent);
          final String localPath = container.getProjectRelativePath() + "/" + container.getName()
                  + "/" + "generated.mtwl";
          try {
            container.getParent().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
          } catch (CoreException e) {
          }
          // event.getShell().getDisplay().asyncExec(new Runnable() {
          // public void run() {
          // MessageDialog.openInformation(window.getShell(), "Multi TWL created",
          // "A multi TWL was created:\n" + localPath);
          // }
          // });
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

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    new ConverterHandlerJob(event).schedule();
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
