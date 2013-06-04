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

import org.apache.uima.ruta.cde.utils.ConstraintData;
import org.apache.uima.ruta.cde.utils.ConstraintXMLUtils;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class ImportConstraintsHandler implements IHandler {
  
  private class ImportJob extends Job {
   
    String inputLocation;
    ConstraintSelectComposite composite;
    
    public ImportJob(ExecutionEvent event, ConstraintSelectComposite composite, String inputLocation) {
      super("Importing constraints");
      this.inputLocation = inputLocation; 
      this.composite = composite;
    }

    protected IStatus run(IProgressMonitor monitor) {
      try {
        final List<ConstraintData> constraints = ConstraintXMLUtils.readConstraints(inputLocation);
        composite.getDisplay().asyncExec(new Runnable() {
          public void run() {
            composite.getConstraintList().addAll(constraints);
            composite.getViewer().setInput(composite.getConstraintList());
            composite.getViewer().refresh();
            composite.update();
          } 
        }); 
      
      } catch (Exception e) {
        e.printStackTrace();
      }
      return Status.OK_STATUS;
    }
  }
  
  public void addHandlerListener(IHandlerListener arg0) {
  }

  public void dispose() {
  }

  public Object execute(ExecutionEvent event) throws ExecutionException {
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
    FileDialog dlg = new FileDialog(HandlerUtil.getActiveShell(event), SWT.OPEN);
    String [] extensions = {"*.xml"};
    dlg.setFilterExtensions(extensions);
    String s = dlg.open();
    if (s != null) {
      ImportJob job = new ImportJob(event, composite, s);
      job.schedule();
    } else {
      return Status.CANCEL_STATUS;
    }
    }catch (Exception e) {
      e.printStackTrace();
    }
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
