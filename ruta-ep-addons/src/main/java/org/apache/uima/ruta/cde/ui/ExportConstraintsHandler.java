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

package org.apache.uima.ruta.cde.ui;

import java.util.List;

import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.cde.utils.ConstraintData;
import org.apache.uima.ruta.cde.utils.ConstraintXMLUtils;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class ExportConstraintsHandler implements IHandler {

  private class ExportAllConstraintsJob extends Job {

    private String outputLocation;

    private List<ConstraintData> constraints;

    ExportAllConstraintsJob(String outputLocation, List<ConstraintData> constraints) {
      super("Exporting constraints");
      this.outputLocation = outputLocation;
      this.constraints = constraints;
    }

    public IStatus run(IProgressMonitor monitor) {
      try {
        ConstraintXMLUtils.writeConstraints(outputLocation, constraints);
        IPath path = Path.fromPortableString(outputLocation);
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IFile ifile = root.getFileForLocation(path);
        if(ifile != null) {
          ifile.getParent().refreshLocal(IResource.DEPTH_INFINITE, monitor);
        }
      } catch (Exception e) {
        RutaAddonsPlugin.error(e);
      }
      return Status.OK_STATUS;
    }
  }

  public void addHandlerListener(IHandlerListener arg0) {
  }

  public void dispose() {
  }

  public Object execute(ExecutionEvent event) throws ExecutionException {

    FileDialog dlg = new FileDialog(HandlerUtil.getActiveShell(event), SWT.SAVE);
    String[] extensions = new String[] { "*.xml" };
    dlg.setFilterExtensions(extensions);
    String s = dlg.open();
    if (s == null) {
      return Status.CANCEL_STATUS;
    }
    ConstraintSelectView constraintView;

    try {
      constraintView = (ConstraintSelectView) HandlerUtil
              .getActiveWorkbenchWindow(event)
              .getWorkbench()
              .getActiveWorkbenchWindow()
              .getActivePage()
              .showView("org.apache.uima.ruta.cde.ui.ConstraintSelectView");
      ConstraintSelectComposite composite = (ConstraintSelectComposite) constraintView
              .getComposite();
      List<ConstraintData> constraintList = composite.getConstraintList();
      ExportAllConstraintsJob job = new ExportAllConstraintsJob(s, constraintList);
      job.schedule();
    } catch (Exception e) {
      RutaAddonsPlugin.error(e);
    }
    return null;
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
