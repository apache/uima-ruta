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

package org.apache.uima.ruta.caseditor.view.browser;

import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.CasEditorView;
import org.apache.uima.caseditor.editor.ICasEditor;
import org.apache.uima.ruta.caseditor.view.tree.AnnotationTreeViewPage;
import org.eclipse.ui.part.IPageBookViewPage;

public class AnnotationBrowserView extends CasEditorView {
  public static final String ID = "org.apache.uima.caseditor.browser";

  private AnnotationTreeViewPage page;

  public AnnotationBrowserView() {
    super("The instance view is currently not available.");
  }

  @Override
  protected IPageBookViewPage doCreatePage(ICasEditor editor) {
    IPageBookViewPage result = null;
    if (editor.getDocument() != null && editor instanceof AnnotationEditor) {
      page = new AnnotationTreeViewPage(false, (AnnotationEditor) editor);
      initPage(page);
      page.createControl(getPageBook());
      result = page;
    }
    return result;
  }

  public AnnotationTreeViewPage getAnnotationTreeViewPage() {
    return page;
  }

}
