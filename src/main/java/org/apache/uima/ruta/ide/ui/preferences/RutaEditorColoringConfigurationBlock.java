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

import java.io.InputStream;

import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.apache.uima.ruta.ide.ui.RutaPartitions;
import org.apache.uima.ruta.ide.ui.RutaPreferenceConstants;
import org.apache.uima.ruta.ide.ui.editor.RutaDocumentSetupParticipant;
import org.apache.uima.ruta.ide.ui.text.SimpleRutaSourceViewerConfiguration;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.preferences.AbstractScriptEditorColoringConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.ITextEditor;

public class RutaEditorColoringConfigurationBlock extends
        AbstractScriptEditorColoringConfigurationBlock implements IPreferenceConfigurationBlock {

  private static final String PREVIEW_FILE_NAME = "PreviewFile.txt";

  private static final String[][] fSyntaxColorListModel = new String[][] {
      { PreferencesMessages.DLTKEditorPreferencePage_singleLineComment,
          RutaPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR, sCommentsCategory },
      { PreferencesMessages.DLTKEditorPreferencePage_CommentTaskTags,
          RutaPreferenceConstants.COMMENT_TASK_TAGS, sCommentsCategory },
      { PreferencesMessages.DLTKEditorPreferencePage_strings,
          RutaPreferenceConstants.EDITOR_STRING_COLOR, sCoreCategory },

      { PreferencesMessages.DLTKEditorPreferencePage_numbers,
          RutaPreferenceConstants.EDITOR_NUMBER_COLOR, sCoreCategory },
      { RutaPreferencesMessages.RutaEditorPreferencePage_condition_colors,
          RutaPreferenceConstants.EDITOR_CONDITION_COLOR, sCoreCategory },

      { RutaPreferencesMessages.RutaEditorPreferencePage_action_colors,
          RutaPreferenceConstants.EDITOR_ACTION_COLOR, sCoreCategory },

      { RutaPreferencesMessages.RutaEditorPreferencePage_declaration_colors,
          RutaPreferenceConstants.EDITOR_DECLARATION_DEFINITION_COLOR, sCoreCategory },

      { RutaPreferencesMessages.RutaEditorPreferencePage_basicsymbols_colors,
          RutaPreferenceConstants.EDITOR_BASICSYMBOL_DEFINITION_COLOR, sCoreCategory },

      { RutaPreferencesMessages.RutaEditorPreferencePage_function_colors,
          RutaPreferenceConstants.EDITOR_FUNCTION_COLOR, sCoreCategory },

      { RutaPreferencesMessages.RutaEditorPreferencePage_then_colors,
          RutaPreferenceConstants.EDITOR_THEN_COLOR, sCoreCategory },

      { RutaPreferencesMessages.TodoTaskDescription,// .
          // DLTKEditorPreferencePage_CommentTaskTags
          // ,
          RutaPreferenceConstants.COMMENT_TASK_TAGS, sCommentsCategory }
  /*
   * { PreferencesMessages.DLTKEditorPreferencePage_variables,
   * RutaPreferenceConstants.EDITOR_VARIABLE_COLOR, sCoreCategory }
   */};

  public RutaEditorColoringConfigurationBlock(OverlayPreferenceStore store) {
    super(store);
  }

  @Override
  protected String[][] getSyntaxColorListModel() {
    return fSyntaxColorListModel;
  }

  @Override
  protected ProjectionViewer createPreviewViewer(Composite parent, IVerticalRuler verticalRuler,
          IOverviewRuler overviewRuler, boolean showAnnotationsOverview, int styles,
          IPreferenceStore store) {
    return new ScriptSourceViewer(parent, verticalRuler, overviewRuler, showAnnotationsOverview,
            styles, store);
  }

  @Override
  protected ScriptSourceViewerConfiguration createSimpleSourceViewerConfiguration(
          IColorManager colorManager, IPreferenceStore preferenceStore, ITextEditor editor,
          boolean configureFormatter) {
    return new SimpleRutaSourceViewerConfiguration(colorManager, preferenceStore, editor,
            RutaPartitions.RUTA_PARTITIONING, configureFormatter);
  }

  @Override
  protected void setDocumentPartitioning(IDocument document) {
    RutaDocumentSetupParticipant participant = new RutaDocumentSetupParticipant();
    participant.setup(document);
  }

  @Override
  protected InputStream getPreviewContentReader() {
    return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
  }

  @Override
  protected ScriptTextTools getTextTools() {
    return RutaIdePlugin.getDefault().getTextTools();
  }

}
