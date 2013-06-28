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

package org.apache.uima.ruta.check;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

public class SelectTypesHandler implements IHandler {

  public void addHandlerListener(IHandlerListener arg0) {

  }

  public void dispose() {

  }

  public Object execute(ExecutionEvent event) throws ExecutionException {
    AnnotationCheckView acView;
    try {
      acView = (AnnotationCheckView) HandlerUtil.getActiveWorkbenchWindow(event).getWorkbench()
              .getActiveWorkbenchWindow().getActivePage().showView(AnnotationCheckView.ID);
      AnnotationCheckComposite composite = (AnnotationCheckComposite) acView.getComposite();
      List<String> types = new ArrayList<String>();
      TypeSystemDescription tsd = UIMAFramework.getXMLParser().parseTypeSystemDescription(
              new XMLInputSource(new File(composite.getTypeSystem())));
      tsd.resolveImports();
      TypeDescription[] systemTypes = tsd.getTypes();
      for (TypeDescription typeDescription : systemTypes) {
        types.add(typeDescription.getName());
      }
      Collections.sort(types);
      Display display = Display.getDefault();
      Shell shell = new Shell(display, SWT.RESIZE | SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
      shell.setText("Included types");
      new SelectTypesDialog(shell, types, false, composite);
    } catch (InvalidXMLException e) {
      RutaAddonsPlugin.error(e);
      return Status.CANCEL_STATUS;
    } catch (IOException e) {
      RutaAddonsPlugin.error(e);
      return Status.CANCEL_STATUS;
    } catch (PartInitException e) {
      RutaAddonsPlugin.error(e);
      return Status.CANCEL_STATUS;
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
