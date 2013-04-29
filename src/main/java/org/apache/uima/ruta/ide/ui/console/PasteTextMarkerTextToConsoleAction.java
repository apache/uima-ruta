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

package org.apache.uima.textmarker.ide.ui.console;

import org.eclipse.dltk.console.ui.IScriptConsole;
import org.eclipse.dltk.console.ui.ScriptConsoleManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class PasteTextMarkerTextToConsoleAction implements IEditorActionDelegate {

  private ISelection selection;

  private IEditorPart targetEditor;

  protected IDocument getDocument() {
    if (!(targetEditor instanceof ITextEditor))
      return null;

    ITextEditor editor = (ITextEditor) targetEditor;
    IDocumentProvider dp = editor.getDocumentProvider();
    return dp.getDocument(editor.getEditorInput());
  }

  public void setActiveEditor(IAction action, IEditorPart targetEditor) {
    this.targetEditor = targetEditor;
  }

  public void run(IAction action) {
    ScriptConsoleManager manager = ScriptConsoleManager.getInstance();

    IScriptConsole console = manager.getActiveScriptConsole(TextMarkerConsole.CONSOLE_TYPE);

    if (console == null) {
      return;
    }

    if (selection instanceof ITextSelection) {
      String text = ((ITextSelection) selection).getText();
      console.insertText(text);
    }
  }

  public void selectionChanged(IAction action, ISelection selection) {
    this.selection = selection;
  }
}
