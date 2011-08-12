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

package org.apache.uima.textmarker.ide.ui.preferences;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.apache.uima.textmarker.ide.ui.TextMarkerPartitions;
import org.apache.uima.textmarker.ide.ui.TextMarkerPreferenceConstants;
import org.apache.uima.textmarker.ide.ui.text.SimpleTextMarkerSourceViewerConfiguration;
import org.eclipse.dltk.ui.formatter.AbstractFormatterPreferencePage;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * Preference page for TextMarker debugging engines
 */
public class TextMarkerFormatterPreferencePage extends AbstractFormatterPreferencePage {

  private static final PreferenceKey FORMATTER = new PreferenceKey(TextMarkerIdePlugin.PLUGIN_ID,
          TextMarkerPreferenceConstants.FORMATTER_ID);

  @Override
  protected String getNatureId() {
    return TextMarkerNature.NATURE_ID;
  }

  @Override
  protected PreferenceKey getFormatterPreferenceKey() {
    return FORMATTER;
  }

  @Override
  protected IDialogSettings getDialogSettings() {
    return TextMarkerIdePlugin.getDefault().getDialogSettings();
  }

  @Override
  protected String getPreferencePageId() {
    return "org.apache.uima.textmarker.ide.preferences.formatter"; //$NON-NLS-1$
  }

  @Override
  protected String getPropertyPageId() {
    return "org.apache.uima.textmarker.ide.propertyPage.formatter"; //$NON-NLS-1$
  }

  @Override
  protected ScriptSourceViewerConfiguration createSimpleSourceViewerConfiguration(
          IColorManager colorManager, IPreferenceStore preferenceStore, ITextEditor editor,
          boolean configureFormatter) {
    return new SimpleTextMarkerSourceViewerConfiguration(colorManager, preferenceStore, editor,
            TextMarkerPartitions.TM_PARTITIONING, configureFormatter);
  }

  @Override
  protected void setPreferenceStore() {
    setPreferenceStore(TextMarkerIdePlugin.getDefault().getPreferenceStore());
  }

}
