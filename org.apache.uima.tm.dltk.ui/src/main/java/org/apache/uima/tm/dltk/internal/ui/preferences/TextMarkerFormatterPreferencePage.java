package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.apache.uima.tm.dltk.internal.ui.text.SimpleTextMarkerSourceViewerConfiguration;
import org.apache.uima.tm.dltk.ui.TextMarkerPreferenceConstants;
import org.apache.uima.tm.dltk.ui.text.TextMarkerPartitions;
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

  private static final PreferenceKey FORMATTER = new PreferenceKey(TextMarkerUI.PLUGIN_ID,
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
    return TextMarkerUI.getDefault().getDialogSettings();
  }

  @Override
  protected String getPreferencePageId() {
    return "org.apache.uima.tm.dltk.preferences.formatter"; //$NON-NLS-1$
  }

  @Override
  protected String getPropertyPageId() {
    return "org.apache.uima.tm.dltk.propertyPage.formatter"; //$NON-NLS-1$
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
    setPreferenceStore(TextMarkerUI.getDefault().getPreferenceStore());
  }

}
