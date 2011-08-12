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

import java.util.Iterator;
import java.util.List;

import org.apache.uima.textmarker.testing.ui.views.TestCasData;
import org.apache.uima.textmarker.testing.ui.views.TestPageBookView;
import org.apache.uima.textmarker.testing.ui.views.TestViewPage;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.handlers.HandlerUtil;


public class RemoveTestsHandler implements IHandler {

  @Override
  public void addHandlerListener(IHandlerListener handlerListener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    TestPageBookView debugView = (TestPageBookView) HandlerUtil.getActivePart(event);
    TestViewPage activePage = (TestViewPage) debugView.getCurrentPage();
    activePage.saveState();
    TableViewer viewer = activePage.getViewer();
    List list = (List) viewer.getInput();
    if (viewer.getSelection() == null) {
      // TODO Refactor this
    } else if (viewer.getSelection() instanceof StructuredSelection) {
      StructuredSelection selection = (StructuredSelection) viewer.getSelection();
      Iterator<TestCasData> iter = selection.iterator();
      while (iter.hasNext()) {
        list.remove(iter.next());
      }
    }
    viewer.refresh(false);
    // debugView.saveState();
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

}
