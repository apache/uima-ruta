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

package org.apache.uima.textmarker.ide.ui.actions;

import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class OpenTextMarkerSearchPageAction implements IWorkbenchWindowActionDelegate {

  private static final String TM_SEARCH_PAGE_ID = "org.apache.uima.textmarker.ide.ui.TextMarkerSearchPage";

  private IWorkbenchWindow window;

  public OpenTextMarkerSearchPageAction() {
  }

  public void init(IWorkbenchWindow window) {
    this.window = window;
  }

  public void run(IAction action) {
    if (window == null || window.getActivePage() == null) {
      beep();
      return;
    }

    NewSearchUI.openSearchDialog(window, TM_SEARCH_PAGE_ID);
  }

  public void selectionChanged(IAction action, ISelection selection) {
  }

  public void dispose() {
    window = null;
  }

  protected void beep() {
    Shell shell = DLTKUIPlugin.getActiveWorkbenchShell();
    if (shell != null && shell.getDisplay() != null)
      shell.getDisplay().beep();
  }
}
