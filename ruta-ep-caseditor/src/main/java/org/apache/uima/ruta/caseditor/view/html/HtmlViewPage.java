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

package org.apache.uima.ruta.caseditor.view.html;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.caseditor.editor.AnnotationEditor;
import org.apache.uima.caseditor.editor.ICasDocument;
import org.apache.uima.caseditor.editor.ICasEditorInputListener;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.resource.metadata.impl.TypeSystemDescription_impl;
import org.apache.uima.ruta.caseditor.RutaCasEditorPlugin;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.FileUtils;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;
import org.xml.sax.SAXException;

public class HtmlViewPage extends Page implements ICasEditorInputListener {

  private AnnotationEditor editor;

  private Browser browser;

  public HtmlViewPage(AnnotationEditor editor) {
    super();
    this.editor = editor;
  }

  @Override
  public void init(IPageSite pageSite) {
    super.init(pageSite);
  }

  @Override
  public void createControl(Composite parent) {
    Composite composite = parent;

    browser = new Browser(composite, SWT.NONE);
    updateHtml();
    getSite().setSelectionProvider(new ISelectionProvider() {

      public void setSelection(ISelection selection) {

      }

      public void removeSelectionChangedListener(ISelectionChangedListener listener) {

      }

      public ISelection getSelection() {
        return null;
      }

      public void addSelectionChangedListener(ISelectionChangedListener listener) {

      }
    });
    editor.addCasEditorInputListener(this);
    browser.refresh();
  }

  @Override
  public void setFocus() {
  }

  @Override
  public void dispose() {
    browser.dispose();
  }

  public void casDocumentChanged(IEditorInput oldInput, ICasDocument oldDocument,
          IEditorInput newInput, ICasDocument newDocument) {
    updateHtml();
  }

  @Override
  public Control getControl() {
    return browser;
  }

  private void updateHtml() {
    if (editor == null) {
      return;
    }
    IPreferenceStore store = RutaCasEditorPlugin.getDefault().getPreferenceStore();
    String htmlSource = store.getString(HtmlView.HTML_SOURCE);
    if (StringUtils.isBlank(htmlSource)) {
      setHtmlWithEditor();
    } else {
      IEditorInput editorInput = editor.getEditorInput();
      if (editorInput instanceof FileEditorInput) {
        FileEditorInput fei = (FileEditorInput) editorInput;
        String name = fei.getFile().getName();
        String[] split = name.split("[.]");
        File dir = new File(htmlSource);
        File[] listFiles = dir.listFiles();
        File selected = null;
        for (File file : listFiles) {
          String[] split2 = file.getName().split("[.]");
          if(split[0].equals(split2[0])) {
            selected = file;
            break;
          }
        }
        if(selected == null) {
          setHtmlWithEditor();
          return;
        }
        try {
          String html = "";
          if (selected.getName().endsWith(".html") || selected.getName().endsWith(".txt")) {
            html = FileUtils.file2String(selected);
          } else if(selected.getName().endsWith(".xmi")){
            try {
              TypeSystemDescription tsd = new TypeSystemDescription_impl();
              CAS dummyCas = CasCreationUtils.createCas(tsd, null, null);
              XmiCasDeserializer.deserialize(new FileInputStream(selected), dummyCas, true);
              html = dummyCas.getDocumentText();
              dummyCas.release();
            } catch (ResourceInitializationException e) {
              RutaCasEditorPlugin.error(e);
            } catch (SAXException e) {
              RutaCasEditorPlugin.error(e);
            }
          }
          browser.setText(html);
          browser.refresh();
        } catch (IOException e) {
          RutaCasEditorPlugin.error(e);
        }
      }
    }
  }

  private void setHtmlWithEditor() {
    CAS cas = editor.getDocument().getCAS();
    String html = cas.getDocumentText();
    browser.setText(html);
    browser.refresh();
  }

}
