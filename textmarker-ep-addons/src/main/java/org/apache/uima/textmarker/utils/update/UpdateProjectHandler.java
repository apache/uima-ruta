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

package org.apache.uima.textmarker.utils.update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.textmarker.addons.TextMarkerAddonsPlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.apache.uima.textmarker.ide.ui.wizards.TextMarkerProjectCreationWizard;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class UpdateProjectHandler implements IHandler {

  private class ConverterHandlerJob extends Job {
    ExecutionEvent event;

    ConverterHandlerJob(ExecutionEvent event) {
      super("Converting...");
      this.event = event;
      setUser(true);
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
      List<IScriptProject> projects = new ArrayList<IScriptProject>();
      ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
      if (currentSelection instanceof IStructuredSelection) {
        StructuredSelection selection = (StructuredSelection) currentSelection;
        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
          Object object = iter.next();
          if (object instanceof IScriptProject) {
            IScriptProject p = (IScriptProject) object;
            projects.add(p);
          }
        }
      }
      monitor.beginTask("Updating descriptor files...", projects.size());
      for (IScriptProject each : projects) {
        IProject project = each.getProject();

        // update old projects
//        try {
//          IProjectNature oldNature = project.getNature(TextMarkerNature.NATURE_ID);
//          if (oldNature != null) {
//            IProjectDescription description = project.getDescription();
//            String[] natureIds = description.getNatureIds();
//            int counter = 0;
//            for (String id : Arrays.asList(natureIds)) {
//              if (id.equals(TextMarkerNature.NATURE_ID)) {
//                natureIds[counter] = TextMarkerNature.NATURE_ID;
//              }
//              counter++;
//            }
//          }
//        } catch (CoreException e) {
//          TextMarkerAddonsPlugin.error(e);
//        }

        try {
          IProjectNature nature = project.getNature(TextMarkerNature.NATURE_ID);
          if (nature != null) {
            List<IFolder> descriptorFolders = TextMarkerProjectUtils.getDescriptorFolders(project);
            if (descriptorFolders != null && !descriptorFolders.isEmpty()) {
              IFolder descFolder = descriptorFolders.get(0);
              TextMarkerProjectCreationWizard.copyDescriptors(descFolder);
              descFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
            }
          }
        } catch (CoreException e) {
          TextMarkerAddonsPlugin.error(e);
        }
        monitor.worked(1);
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
    new ConverterHandlerJob(event).schedule();
    return null;
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
