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

package org.apache.uima.tm.cev.views.editor;

import org.apache.uima.tm.cev.views.ICEVViewPage;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.views.ViewsPlugin;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.MessagePage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;

public class CEVAnnotationEditorView extends PageBookView {

  private static final String VIEW_IS_NOT_AVAILABLE = "View is not available.";

  public CEVAnnotationEditorView() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#createDefaultPage(org.eclipse.ui.part .PageBook)
   */
  @Override
  protected IPage createDefaultPage(PageBook book) {
    MessagePage page = new MessagePage();
    initPage(page);
    page.createControl(book);
    page.setMessage(VIEW_IS_NOT_AVAILABLE);
    return page;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#doCreatePage(org.eclipse.ui.IWorkbenchPart )
   */
  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    Object obj = ViewsPlugin.getAdapter(part, ICEVAnnotationEditorPage.class, false);

    if (obj instanceof ICEVViewPage) {
      ICEVViewPage page = (ICEVViewPage) obj;

      page.createControl(getPageBook());
      return new PageRec(part, page);
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#doDestroyPage(org.eclipse.ui.IWorkbenchPart ,
   * org.eclipse.ui.part.PageBookView.PageRec)
   */
  @Override
  protected void doDestroyPage(IWorkbenchPart part, PageRec pageRecord) {
    pageRecord.page.dispose();
    pageRecord.dispose();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#getBootstrapPart()
   */
  @Override
  protected IWorkbenchPart getBootstrapPart() {
    IWorkbenchPage page = getSite().getPage();
    if (page != null) {
      return page.getActiveEditor();
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#isImportant(org.eclipse.ui.IWorkbenchPart )
   */
  @Override
  protected boolean isImportant(IWorkbenchPart part) {
    return (part instanceof IEditorPart);
  }

}
