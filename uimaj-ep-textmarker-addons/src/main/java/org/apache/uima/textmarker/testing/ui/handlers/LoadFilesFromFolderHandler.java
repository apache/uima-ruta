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

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.uima.textmarker.testing.ui.views.TestCasData;
import org.apache.uima.textmarker.testing.ui.views.TestPageBookView;
import org.apache.uima.textmarker.testing.ui.views.TestViewPage;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.handlers.HandlerUtil;


public class LoadFilesFromFolderHandler implements IHandler {

  TestPageBookView debugView;

  TestViewPage debugPage;

  @Override
  public void addHandlerListener(IHandlerListener handlerListener) {

  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    debugView = (TestPageBookView) HandlerUtil.getActivePart(event);
    debugPage = (TestViewPage) debugView.getCurrentPage();
    IProject project = debugPage.getResource().getProject();

    IViewPart scriptExpl = HandlerUtil.getActiveSite(event).getWorkbenchWindow().getActivePage()
            .findView("org.eclipse.dltk.ui.ScriptExplorer");
    ISelection select = scriptExpl.getViewSite().getSelectionProvider().getSelection();

    if (select != null && select instanceof StructuredSelection) {
      StructuredSelection structSelect = (StructuredSelection) select;
      Iterator iter = structSelect.iterator();
      while (iter.hasNext()) {
        Object o = iter.next();
        if (o instanceof Folder) {
          Folder folder = (Folder) o;
          IPath path2Folder = folder.getFullPath().removeFirstSegments(1);
          IFolder testFolder = project.getFolder(path2Folder);

          loadFolder(testFolder);
        }
      }
    }
    return null;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isHandled() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public void removeHandlerListener(IHandlerListener handlerListener) {
    // TODO Auto-generated method stub

  }

  public void loadFolder(IFolder folder) {
    try {
      IResource[] children = folder.members();
      for (IResource r : children) {
        // if (r instanceof IFolder) {
        // loadFolder((IFolder)r);
        // }
        if (r instanceof IFile) {
          if (r != null && r instanceof IFile && r.getLocation().getFileExtension().equals("xmi")) {
            add2Viewer(r.getLocation());
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void add2Viewer(IPath p) {

    TestViewPage debugPage = (TestViewPage) debugView.getCurrentPage();

    TableViewer viewer = debugPage.getViewer();

    if (viewer.getInput() instanceof ArrayList) {
      ArrayList input = (ArrayList) debugPage.getViewer().getInput();
      input.add(new TestCasData(p));
      viewer.refresh();

      if (input.get(0) != null) {
        TestCasData data = (TestCasData) input.get(0);
        if (data.wasEvaluated()) {
          debugPage.updateSingleTestInformation(data);
        }
      }
    }

  }

}
