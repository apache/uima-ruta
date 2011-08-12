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

package org.apache.uima.textmarker.ide.ui.text;

import org.eclipse.core.resources.IFile;
import org.eclipse.dltk.internal.ui.editor.DLTKEditorMessages;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

public class ExternalTypeHyperlink implements IHyperlink {
  private final IRegion region;

  private final IFile file;

  private final String nodeText;

  private final String tsString;

  private final ITextEditor textEditor;

  public ExternalTypeHyperlink(String nodeText, IRegion region, IFile file, String tsString,
          ITextEditor textEditor) {
    this.nodeText = nodeText;
    this.region = region;
    this.file = file;
    this.tsString = tsString;
    this.textEditor = textEditor;
  }

  @Override
  public IRegion getHyperlinkRegion() {
    return region;
  }

  @Override
  public String getHyperlinkText() {
    return nodeText + " in " + tsString;
  }

  @Override
  public String getTypeLabel() {
    return DLTKEditorMessages.ModelElementHyperlink_typeLabel;
  }

  @Override
  public void open() {
    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    try {
      page.openEditor(new FileEditorInput(file), "taeconfigurator.editors.MultiPageEditor");
    } catch (PartInitException e) {
      e.printStackTrace();
    }

  }

}
