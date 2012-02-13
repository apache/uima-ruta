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

package org.apache.uima.textmarker.testing.ui.handlers;

import org.apache.uima.textmarker.testing.ui.views.TestViewPage;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;

public class RunTestHandler implements IHandler {

  private IResource resource;

  public void addHandlerListener(IHandlerListener handlerListener) {
    // TODO Auto-generated method stub

  }

  public void dispose() {
    // TODO Auto-generated method stub

  }

  public Object execute(ExecutionEvent event) throws ExecutionException {

    // TODO create a new view for every resource file
    TestViewPage view = null;
    try {
      FileEditorInput input = (FileEditorInput) HandlerUtil.getActiveEditor(event).getEditorInput();
      IResource r = input.getFile();
      view = (TestViewPage) HandlerUtil.getActiveWorkbenchWindow(event).getWorkbench()
              .getActiveWorkbenchWindow().getActivePage()
              .showView("org.apache.uima.textmarker.testing.annotationTest");
      view.setResource(r);
    } catch (PartInitException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    /*
     * TODO Create a separate view for every tm project that is selected if
     * (HandlerUtil.getActiveMenuSelection(event) instanceof IStructuredSelection) { // if
     * (HandlerUtil.getCurrentSelection(event) instanceof IStructuredSelection) {
     * StructuredSelection selection =
     * (StructuredSelection)HandlerUtil.getActiveMenuSelection(event);; // StructuredSelection
     * selection = (StructuredSelection)HandlerUtil.getCurrentSelection(event); Iterator iter =
     * selection.iterator(); ArrayList<IResource> selectionList = new ArrayList<IResource>(); while
     * (iter.hasNext()) {
     * 
     * Object obj = iter.next(); if (obj instanceof IResource) { selectionList.add((IResource)obj);
     * } view.setResource(selectionList.get(0)); if (selectionList.get(0) == null) {
     * System.out.println("keks"); } //view.setTitle(selectionList.get(0).getName()); }
     * 
     * ArrayList<String> testList = new ArrayList<String>();
     * 
     * if (view.getViewer() != null) { List list = view.getViewer().getList(); for (int i = 0; i <
     * list.getItemCount(); i++) { String s = view.getViewer().getList().getItem(i).toString();
     * testList.add(s); } }
     * 
     * IResource resource = selectionList.get(0); for (String s : testList) {
     * 
     * }
     * 
     * }
     */
    return null;
  }

  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }

  public boolean isHandled() {
    // TODO Auto-generated method stub
    return true;
  }

  public void removeHandlerListener(IHandlerListener handlerListener) {
    // TODO Auto-generated method stub

  }

}
