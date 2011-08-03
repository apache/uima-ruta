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

package org.apache.uima.tm.textmarker.testing.ui.views;

import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.MessagePage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;

public class TestPageBookView extends PageBookView {

  public TestPageBookView() {
    // TODO Auto-generated constructor stub
  }

  @Override
  protected IPage createDefaultPage(PageBook book) {
    // IWorkbenchPart activePart = getSite().getPage().getActivePart();
    // if (activePart != null && activePart.getSite().getId().equals(anObject))
    MessagePage messagePage = new MessagePage();
    initPage(messagePage);
    messagePage.setMessage("This view is not available");
    messagePage.createControl(book);
    return messagePage;
  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    // getPageBook().dispose();
    EditorPart source = (EditorPart) part;

    FileEditorInput fileInput = (FileEditorInput) source.getEditorInput();
    IResource r = fileInput.getFile();

    TestViewPage testPage = new TestViewPage(r);
    initPage(testPage);
    testPage.createControl(getPageBook());
    PageRec result = new PageRec(part, testPage);
    return result;
  }

  @Override
  protected void doDestroyPage(IWorkbenchPart part, PageRec pageRecord) {
    pageRecord.page.dispose();
  }

  @Override
  protected IWorkbenchPart getBootstrapPart() {
    IWorkbenchPage page = getSite().getPage();

    if (page != null) {
      // check whether the active part is important to us
      IWorkbenchPart activePart = page.getActivePart();
      if (activePart != null && isImportant(activePart)) {
        return activePart;
      }
    }
    return null;
  }

  @Override
  protected boolean isImportant(IWorkbenchPart part) {
    return part.getSite().getId().equals("org.apache.uima.tm.dltk.ui.editor.TextMarkerEditor");
  }

}
