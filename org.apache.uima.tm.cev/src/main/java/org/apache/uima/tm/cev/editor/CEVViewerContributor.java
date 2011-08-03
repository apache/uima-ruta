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

package org.apache.uima.tm.cev.editor;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;

public class CEVViewerContributor extends MultiPageEditorActionBarContributor {

  private IEditorPart activeEditorPart;

  public CEVViewerContributor() {
    super();
  }

  protected IAction getAction(ITextEditor editor, String actionID) {
    return (editor == null ? null : editor.getAction(actionID));
  }

  /*
   * (non-JavaDoc) Method declared in AbstractMultiPageEditorActionBarContributor.
   */
  @Override
  public void setActivePage(IEditorPart part) {
    if (activeEditorPart == part)
      return;
    activeEditorPart = part;
  }
}
