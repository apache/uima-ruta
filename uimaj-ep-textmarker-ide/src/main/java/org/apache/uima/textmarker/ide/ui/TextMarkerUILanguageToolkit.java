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

package org.apache.uima.textmarker.ide.ui;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerConstants;
import org.apache.uima.textmarker.ide.core.TextMarkerLanguageToolkit;
import org.apache.uima.textmarker.ide.core.parser.TextMarkerParseUtils;
import org.apache.uima.textmarker.ide.parser.ast.TMTypeConstants;
import org.apache.uima.textmarker.ide.ui.editor.TextMarkerEditor;
import org.apache.uima.textmarker.ide.ui.text.SimpleTextMarkerSourceViewerConfiguration;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;

public class TextMarkerUILanguageToolkit extends AbstractDLTKUILanguageToolkit {

  private static TextMarkerUILanguageToolkit toolkit = null;

  public static IDLTKUILanguageToolkit getInstance() {
    if (toolkit == null) {
      toolkit = new TextMarkerUILanguageToolkit();
    }
    return toolkit;
  }

  private static ScriptElementLabels sInstance = new ScriptElementLabels() {
    @Override
    protected void getScriptFolderLabel(IScriptFolder folder, StringBuffer buf) {
      String name = folder.getElementName();
      name = name.replace(IScriptFolder.PACKAGE_DELIMITER, '.');
      buf.append(name);
    }

    @Override
    protected void getFieldLabel(IField field, long flags, StringBuffer buf) {
      super.getFieldLabel(field, flags, buf);
      int i = TextMarkerParseUtils.getTypeOfIModelElement(field);
      String type = TMTypeConstants.typeStringOfInt.get(i);
      if (type != null) {
        type = type.toLowerCase();
        buf.append(" : ");
        buf.append(type);
      }
    }
  };

  @Override
  public ScriptElementLabels getScriptElementLabels() {
    return sInstance;
  }

  public IPreferenceStore getPreferenceStore() {
    return TextMarkerIdePlugin.getDefault().getPreferenceStore();
  }

  public IDLTKLanguageToolkit getCoreToolkit() {
    return TextMarkerLanguageToolkit.getDefault();
  }

  public IDialogSettings getDialogSettings() {
    return TextMarkerIdePlugin.getDefault().getDialogSettings();
  }

  @Override
  public String getPartitioningId() {
    return TextMarkerConstants.TM_PARTITIONING;
  }

  @Override
  public String getEditorId(Object inputElement) {
    return TextMarkerEditor.EDITOR_ID;
  }

  @Override
  public String getInterpreterContainerId() {
    return "org.apache.uima.textmarker.ide.launching.INTERPRETER_CONTAINER";
  }

  @Override
  public ScriptUILabelProvider createScriptUILabelProvider() {
    return null;
  }

  @Override
  public boolean getProvideMembers(ISourceModule element) {
    return true;
  }

  @Override
  public ScriptTextTools getTextTools() {
    return TextMarkerIdePlugin.getDefault().getTextTools();
  }

  @Override
  public ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
    return new SimpleTextMarkerSourceViewerConfiguration(getTextTools().getColorManager(),
            getPreferenceStore(), null, getPartitioningId(), false);
  }

  private static final String INTERPRETERS_PREFERENCE_PAGE_ID = "org.apache.uima.textmarker.ide.preferences.interpreters";

  private static final String DEBUG_PREFERENCE_PAGE_ID = "org.apache.uima.textmarker.ide.preferences.debug";

  @Override
  public String getInterpreterPreferencePage() {
    return INTERPRETERS_PREFERENCE_PAGE_ID;
  }

  @Override
  public String getDebugPreferencePage() {
    return DEBUG_PREFERENCE_PAGE_ID;
  }

  private static final String[] EDITOR_PREFERENCE_PAGES_IDS = {
      "org.apache.uima.textmarker.ide.preferences.editor",
      "org.apache.uima.textmarker.ide.ui.editor.SyntaxColoring",
      "org.apache.uima.textmarker.ide.ui.editor.SmartTyping",
      "org.apache.uima.textmarker.ide.ui.editor.TextMarkerFolding" };

  @Override
  public String[] getEditorPreferencePages() {
    return EDITOR_PREFERENCE_PAGES_IDS;
  }
}
