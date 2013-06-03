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

import java.util.ArrayList;

import org.apache.uima.ruta.cde.utils.ConstraintData;
import org.apache.uima.ruta.cde.utils.ConstraintXMLExporter;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class ExportAllConstraintsHandler implements IHandler {

  private class ExportAllConstraintsJob extends Job {

    private IPath outputPath;

    private ArrayList<ConstraintData> constraints;

    ExportAllConstraintsJob(IPath outputPath, ArrayList<ConstraintData> constraints) {
      super("Exporting all the constraints");
      this.outputPath = outputPath;
      this.constraints = constraints;

    }

    public IStatus run(IProgressMonitor monitor) {
      ConstraintXMLExporter exporter = new ConstraintXMLExporter(constraints);
      try {
        exporter.saveConstraints(outputPath);
        
        //ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(outputPath).getParent().refreshLocal(1, monitor);
        
        
      } catch (Exception e) {
        e.printStackTrace();
      }
      return Status.OK_STATUS;
    }
  }

  private class ExportAllConstraintsJobAdapter extends JobChangeAdapter {

    private ConstraintSelectComposite composite;

    ExportAllConstraintsJobAdapter(ConstraintSelectComposite composite) {
      super();
      this.composite = composite;
    }

    public void done(IJobChangeEvent event) {
      if (event.getResult().isOK()) {
        composite.getDisplay().asyncExec(new Runnable() {
          public void run() {
            try {

            } catch (Exception e) {

            }
          }
        });
      }
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
//    String test = dlg.open();
    String s = dlg.open();
    IPath outputPath = null;
    if (s != null) {
      outputPath = Path.fromPortableString(s);
    } else {
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
      ArrayList<ConstraintData> constraintList = composite.getConstraintList();
      

      
      ExportAllConstraintsJob job = new ExportAllConstraintsJob(outputPath, constraintList);
      job.schedule();

    } catch (Exception e) {
      e.printStackTrace();
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
