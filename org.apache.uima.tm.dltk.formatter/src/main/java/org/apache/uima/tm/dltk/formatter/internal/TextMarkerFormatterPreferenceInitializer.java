package org.apache.uima.tm.dltk.formatter.internal;

import org.apache.uima.tm.dltk.formatter.TextMarkerFormatterConstants;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;


public class TextMarkerFormatterPreferenceInitializer extends AbstractPreferenceInitializer {

  @Override
  public void initializeDefaultPreferences() {
    TextMarkerFormatterPlugin plugin = TextMarkerFormatterPlugin.getDefault();
    IPreferenceStore store = plugin.getPreferenceStore();

    store.setDefault(TextMarkerFormatterConstants.INDENT_BLOCK, true);
    store.setDefault(TextMarkerFormatterConstants.INDENT_STRUCTURE, true);

    store.setDefault(TextMarkerFormatterConstants.LINES_BEFORE_LONG_DECLARATIONS, 1);

    store.setDefault(TextMarkerFormatterConstants.LINES_PRESERVE, 1);

    store.setDefault(TextMarkerFormatterConstants.MAX_LINE_LENGTH, 100);
  }
}
