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

import org.apache.uima.textmarker.ide.ui.editor.TextMarkerEditor;
import org.eclipse.dltk.internal.ui.editor.DLTKEditorMessages;
import org.eclipse.dltk.ui.actions.DLTKActionConstants;
import org.eclipse.dltk.ui.actions.GenerateActionGroup;
import org.eclipse.dltk.ui.actions.IScriptEditorActionDefinitionIds;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.TextOperationAction;

public class TextMarkerGenerateActionGroup extends GenerateActionGroup {
  public TextMarkerGenerateActionGroup(TextMarkerEditor editor, String groupName) {
    super(editor, groupName);
    Action action = new TextOperationAction(DLTKEditorMessages.getBundleForConstructedKeys(),
            "Format.", editor, ISourceViewer.FORMAT); //$NON-NLS-1$
    action.setActionDefinitionId(IScriptEditorActionDefinitionIds.FORMAT);
    editor.setAction(DLTKActionConstants.FORMAT, action);
    editor.markAsStateDependentAction(DLTKActionConstants.FORMAT, true);
    editor.markAsSelectionDependentAction(DLTKActionConstants.FORMAT, true);
  }
}
