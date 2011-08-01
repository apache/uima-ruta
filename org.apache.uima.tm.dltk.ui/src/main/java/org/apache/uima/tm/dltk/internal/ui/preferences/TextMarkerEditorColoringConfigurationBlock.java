package org.apache.uima.tm.dltk.internal.ui.preferences;

import java.io.InputStream;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.apache.uima.tm.dltk.internal.ui.editor.TextMarkerDocumentSetupParticipant;
import org.apache.uima.tm.dltk.internal.ui.text.SimpleTextMarkerSourceViewerConfiguration;
import org.apache.uima.tm.dltk.ui.TextMarkerPreferenceConstants;
import org.apache.uima.tm.dltk.ui.text.TextMarkerPartitions;
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


public class TextMarkerEditorColoringConfigurationBlock extends
        AbstractScriptEditorColoringConfigurationBlock implements IPreferenceConfigurationBlock {

  private static final String PREVIEW_FILE_NAME = "PreviewFile.txt";

  private static final String[][] fSyntaxColorListModel = new String[][] {
      { PreferencesMessages.DLTKEditorPreferencePage_singleLineComment,
          TextMarkerPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR, sCommentsCategory },
      { PreferencesMessages.DLTKEditorPreferencePage_CommentTaskTags,
          TextMarkerPreferenceConstants.COMMENT_TASK_TAGS, sCommentsCategory },
      { PreferencesMessages.DLTKEditorPreferencePage_strings,
          TextMarkerPreferenceConstants.EDITOR_STRING_COLOR, sCoreCategory },

      { PreferencesMessages.DLTKEditorPreferencePage_numbers,
          TextMarkerPreferenceConstants.EDITOR_NUMBER_COLOR, sCoreCategory },
      { TextMarkerPreferencesMessages.TextMarkerEditorPreferencePage_condition_colors,
          TextMarkerPreferenceConstants.EDITOR_CONDITION_COLOR, sCoreCategory },

      { TextMarkerPreferencesMessages.TextMarkerEditorPreferencePage_action_colors,
          TextMarkerPreferenceConstants.EDITOR_ACTION_COLOR, sCoreCategory },

      { TextMarkerPreferencesMessages.TextMarkerEditorPreferencePage_declaration_colors,
          TextMarkerPreferenceConstants.EDITOR_DECLARATION_DEFINITION_COLOR, sCoreCategory },

      { TextMarkerPreferencesMessages.TextMarkerEditorPreferencePage_basicsymbols_colors,
          TextMarkerPreferenceConstants.EDITOR_BASICSYMBOL_DEFINITION_COLOR, sCoreCategory },

      { TextMarkerPreferencesMessages.TextMarkerEditorPreferencePage_function_colors,
          TextMarkerPreferenceConstants.EDITOR_FUNCTION_COLOR, sCoreCategory },

      { TextMarkerPreferencesMessages.TextMarkerEditorPreferencePage_then_colors,
          TextMarkerPreferenceConstants.EDITOR_THEN_COLOR, sCoreCategory },

      { TextMarkerPreferencesMessages.TodoTaskDescription,// .
          // DLTKEditorPreferencePage_CommentTaskTags
          // ,
          TextMarkerPreferenceConstants.COMMENT_TASK_TAGS, sCommentsCategory }
  /*
   * { PreferencesMessages.DLTKEditorPreferencePage_variables,
   * TextMarkerPreferenceConstants.EDITOR_VARIABLE_COLOR, sCoreCategory }
   */};

  public TextMarkerEditorColoringConfigurationBlock(OverlayPreferenceStore store) {
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
    return new SimpleTextMarkerSourceViewerConfiguration(colorManager, preferenceStore, editor,
            TextMarkerPartitions.TM_PARTITIONING, configureFormatter);
  }

  @Override
  protected void setDocumentPartitioning(IDocument document) {
    TextMarkerDocumentSetupParticipant participant = new TextMarkerDocumentSetupParticipant();
    participant.setup(document);
  }

  @Override
  protected InputStream getPreviewContentReader() {
    return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
  }

  @Override
  protected ScriptTextTools getTextTools() {
    return TextMarkerUI.getDefault().getTextTools();
  }

}
