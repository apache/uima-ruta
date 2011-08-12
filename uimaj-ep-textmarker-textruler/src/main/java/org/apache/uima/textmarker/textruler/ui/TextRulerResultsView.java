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

package org.apache.uima.textmarker.textruler.ui;

import org.apache.uima.textmarker.ide.core.TextMarkerLanguageToolkit;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class TextRulerResultsView extends ViewPart {

  public static final String ID = "org.apache.uima.textmarker.textruler.TextRulerResultsView";

  protected SourceViewer viewer;

  public TextRulerResultsView() {
    super();
  }

  @Override
  public void createPartControl(Composite parent) {
    IDLTKUILanguageToolkit toolkit = DLTKUILanguageManager
            .getLanguageToolkit(TextMarkerLanguageToolkit.getDefault().getNatureId());
    final ScriptTextTools textTools = toolkit.getTextTools();
    IPreferenceStore store = toolkit.getCombinedPreferenceStore();
    viewer = new ScriptSourceViewer(parent, null, null, false, SWT.BORDER | SWT.V_SCROLL
            | SWT.H_SCROLL, store);
    ScriptSourceViewerConfiguration configuration = textTools.createSourceViewerConfiguraton(store,
            (ITextEditor) null);
    viewer.configure(configuration);
    viewer.setDocument(new Document("No results available yet!"));
  }

  public void setViewTitle(String title) {
    setPartName(title);
  }

  public void setInformation(String content) {
    if (content == null) {
      viewer.setInput(null);
      return;
    }
    IDocument doc = new Document(content);
    IDLTKUILanguageToolkit uiToolkit = DLTKUILanguageManager
            .getLanguageToolkit(TextMarkerLanguageToolkit.getDefault().getNatureId());
    ScriptTextTools textTools = uiToolkit.getTextTools();
    if (textTools != null) {
      textTools.setupDocumentPartitioner(doc, uiToolkit.getPartitioningId());
    }
    viewer.setInput(doc);
  }

  public void setResultContents(String str) {
    setInformation(str);
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

}
