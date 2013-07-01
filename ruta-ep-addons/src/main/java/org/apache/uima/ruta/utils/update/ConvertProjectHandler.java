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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.apache.uima.ruta.ide.ui.wizards.RutaProjectCreationWizard;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.utils.ResourceUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class ConvertProjectHandler implements IHandler {

  private class ConvertProjectHandlerJob extends Job {
    ExecutionEvent event;

    ConvertProjectHandlerJob(ExecutionEvent event) {
      super("Converting...");
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
      monitor.beginTask("Converting to UIMA Ruta project...", projects.size());
      for (IProject each : projects) {
        try {
          IScriptProject scriptProject = DLTKCore.create(each);
          ResourceUtil.addNature(each, monitor, RutaNature.NATURE_ID);
          RutaProjectCreationWizard.createRutaProject(scriptProject, null, monitor);
        } catch (CoreException e) {
          RutaAddonsPlugin.error(e);
        }
      }
      monitor.done();
      return Status.OK_STATUS;
    }
  }

  public void addHandlerListener(IHandlerListener arg0) {

  }

  public void dispose() {

  }

  public Object execute(ExecutionEvent event) throws ExecutionException {
    new ConvertProjectHandlerJob(event).schedule();
    return Status.OK_STATUS;
  }

  public boolean isEnabled() {
    return true;
  }

  public boolean isHandled() {
    return true;
  }

  public void removeHandlerListener(IHandlerListener arg0) {

  }

}
