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

package org.apache.uima.ruta.utils.update;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.ide.ui.wizards.RutaProjectCreationWizard;
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
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class UpdateProjectHandler implements IHandler {

  private class ConverterHandlerJob extends Job {
    ExecutionEvent event;

    ConverterHandlerJob(ExecutionEvent event) {
      super("Updating...");
      this.event = event;
      setUser(true);
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
      List<IProject> projects = new ArrayList<IProject>();
      final ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
      if (currentSelection instanceof IStructuredSelection) {
        StructuredSelection selection = (StructuredSelection) currentSelection;
        Iterator<?> iter = selection.iterator();
        while (iter.hasNext()) {
          Object object = iter.next();
          if (object instanceof IScriptProject) {
            IScriptProject p = (IScriptProject) object;
            projects.add(p.getProject());
          } else if (object instanceof IProject) {
            projects.add((IProject) object);
          }
        }
      }
      monitor.beginTask("Updating UIMA Ruta project...", projects.size());
      for (IProject each : projects) {
        // update old projects
        try {
          IProjectDescription description = each.getDescription();
          String[] natureIds = description.getNatureIds();
          int counter = 0;
          boolean oldProject = false;
          for (String id : Arrays.asList(natureIds)) {
            if (id.equals("org.apache.uima.textmarker.ide.nature")) {
              natureIds[counter] = RutaNature.NATURE_ID;
              oldProject = true;
            }
            counter++;
          }
          if (oldProject) {
            description.setNatureIds(natureIds);
            each.setDescription(description, monitor);
            List<File> files = getFiles(new File(each.getLocation().toPortableString()));
            for (File file : files) {
              String absolutePath = file.getAbsolutePath();
              if (file.getName().endsWith(".tm")) {
                File newFile = new File(absolutePath.substring(0, absolutePath.length() - 3)
                        + ".ruta");
                file.renameTo(newFile);
              }
            }
            IScriptProject sp = DLTKCore.create(each);
            List<IFolder> scriptFolders = RutaProjectUtils.getScriptFolders(sp);
            for (IFolder iFolder : scriptFolders) {
              iFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
            }
          }
        } catch (CoreException e) {
          RutaAddonsPlugin.error(e);
        }

        try {
          IProjectNature nature = each.getNature(RutaNature.NATURE_ID);
          if (nature != null) {
            List<IFolder> descriptorFolders = RutaProjectUtils.getDescriptorFolders(each);
            if (descriptorFolders != null && !descriptorFolders.isEmpty()) {
              IFolder descFolder = descriptorFolders.get(0);
              RutaProjectCreationWizard.copyDescriptors(descFolder);
              descFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
            }
          }
        } catch (CoreException e) {
          RutaAddonsPlugin.error(e);
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

  private static List<File> getFiles(File dir) {
    List<File> result = new ArrayList<File>();
    for (File each : dir.listFiles()) {
      if (each.isHidden()) {
        continue;
      }
      if (each.getName().endsWith(".tm")) {
        result.add(each);
      }
      if (each.isDirectory()) {
        result.addAll(getFiles(each));
      }
    }
    return result;
  }

}
