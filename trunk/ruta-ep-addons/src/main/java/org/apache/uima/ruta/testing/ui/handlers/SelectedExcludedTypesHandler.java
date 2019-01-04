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

package org.apache.uima.ruta.testing.ui.handlers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.apache.uima.ruta.ide.core.builder.RutaProjectUtils;
import org.apache.uima.ruta.testing.ui.views.TestPageBookView;
import org.apache.uima.ruta.testing.ui.views.TestViewPage;
import org.apache.uima.ruta.utils.ui.SelectTypesDialog;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class SelectedExcludedTypesHandler implements IHandler {

  public void addHandlerListener(IHandlerListener handlerListener) {

  }

  public void dispose() {

  }

  public Object execute(ExecutionEvent event) throws ExecutionException {
    TestPageBookView debugView = (TestPageBookView) HandlerUtil.getActivePart(event);
    if(!(debugView.getCurrentPage() instanceof TestViewPage)) {
      return Status.CANCEL_STATUS;
    }
    TestViewPage activePage = (TestViewPage) debugView.getCurrentPage();
    IResource resource = activePage.getResource();
    IPath location = resource.getLocation();
    String preFilePath = location.toPortableString();
    File preFile = new File(preFilePath);
    if (preFile.exists() == false || StringUtils.isEmpty(preFilePath)) {
      printErrorDialog("The preprocessing file was not found!");
      return null;
    }
    TypeSystemDescription defaultTypeSystemDescription = null;
    List<String> types = new ArrayList<String>();
    try {
      ClassLoader classLoader = RutaProjectUtils.getClassLoader(resource.getProject());
      String tsDesc = RutaProjectUtils.getTypeSystemDescriptorPath(location, resource.getProject(), classLoader)
              .toPortableString();

      defaultTypeSystemDescription = UIMAFramework.getXMLParser().parseTypeSystemDescription(
              new XMLInputSource(new File(tsDesc)));
      defaultTypeSystemDescription.resolveImports();
      TypeDescription[] systemTypes = defaultTypeSystemDescription.getTypes();
      Set<String> set = new HashSet<String>();
      for (TypeDescription typeDescription : systemTypes) {
        set.add(typeDescription.getName());
      }
      types.addAll(set);
      Collections.sort(types);
    } catch (InvalidXMLException e) {
      RutaAddonsPlugin.error(e);
    } catch (IOException e) {
      RutaAddonsPlugin.error(e);
    } catch (CoreException e) {
      RutaAddonsPlugin.error(e);
    }

    Display display = Display.getDefault();
    Shell shell = new Shell(display, SWT.RESIZE | SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
    shell.setText("Select excluded types");
    SelectTypesDialog dialog = new SelectTypesDialog(shell, types, activePage.getExcludedTypes());
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    List<String> selectedTypes = dialog.getSelectedTypes();
    activePage.setExcludedTypes(selectedTypes);
    return Status.OK_STATUS;
  }

  public boolean isEnabled() {
    return true;
  }

  public boolean isHandled() {
    return true;
  }

  public static void printErrorDialog(String error) {
    ErrorDialog.openError(Display.getCurrent().getActiveShell(), "File not Found!", error,
            new Status(IStatus.ERROR, "-1", "File not found!"));
  }

  public void removeHandlerListener(IHandlerListener handlerListener) {

  }

}
