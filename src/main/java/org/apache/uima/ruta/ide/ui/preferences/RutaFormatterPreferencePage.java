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

package org.apache.uima.ruta.ide.ui.preferences;

import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.apache.uima.ruta.ide.core.RutaNature;
import org.apache.uima.ruta.ide.ui.RutaPartitions;
import org.apache.uima.ruta.ide.ui.RutaPreferenceConstants;
import org.apache.uima.ruta.ide.ui.text.SimpleRutaSourceViewerConfiguration;
import org.eclipse.dltk.ui.formatter.AbstractFormatterPreferencePage;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * Preference page for Ruta debugging engines
 */
public class RutaFormatterPreferencePage extends AbstractFormatterPreferencePage {

  private static final PreferenceKey FORMATTER = new PreferenceKey(RutaIdePlugin.PLUGIN_ID,
          RutaPreferenceConstants.FORMATTER_ID);

  @Override
  protected String getNatureId() {
    return RutaNature.NATURE_ID;
  }

  @Override
  protected PreferenceKey getFormatterPreferenceKey() {
    return FORMATTER;
  }

  @Override
  protected IDialogSettings getDialogSettings() {
    return RutaIdePlugin.getDefault().getDialogSettings();
  }

  @Override
  protected String getPreferencePageId() {
    return "org.apache.uima.ruta.ide.preferences.formatter"; //$NON-NLS-1$
  }

  @Override
  protected String getPropertyPageId() {
    return "org.apache.uima.ruta.ide.propertyPage.formatter"; //$NON-NLS-1$
  }

  @Override
  protected ScriptSourceViewerConfiguration createSimpleSourceViewerConfiguration(
          IColorManager colorManager, IPreferenceStore preferenceStore, ITextEditor editor,
          boolean configureFormatter) {
    return new SimpleRutaSourceViewerConfiguration(colorManager, preferenceStore, editor,
            RutaPartitions.RUTA_PARTITIONING, configureFormatter);
  }

  @Override
  protected void setPreferenceStore() {
    setPreferenceStore(RutaIdePlugin.getDefault().getPreferenceStore());
  }

}
