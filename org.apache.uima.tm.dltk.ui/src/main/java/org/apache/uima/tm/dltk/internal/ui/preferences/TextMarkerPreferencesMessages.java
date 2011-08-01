package org.apache.uima.tm.dltk.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class TextMarkerPreferencesMessages extends NLS {
  private static final String BUNDLE_NAME = "org.apache.uima.tm.dltk.internal.ui.preferences.TextMarkerPreferencesMessages";//$NON-NLS-1$	

  private TextMarkerPreferencesMessages() {
    // Do not instantiate
  }

  static {
    NLS.initializeMessages(BUNDLE_NAME, TextMarkerPreferencesMessages.class);
  }

  public static String TextMarkerGlobalPreferencePage_description;

  public static String TextMarkerEditorPreferencePage_general;

  public static String TextMarkerSmartTypingConfigurationBlock_typing_smartTab;

  public static String TextMarkerSmartTypingConfigurationBlock_closeBrackets;

  public static String TextMarkerSmartTypingConfigurationBlock_closeStrings;

  public static String TextMarkerSmartTypingConfigurationBlock_closeBraces;

  public static String TextMarkerSmartTypingConfigurationBlock_typing_tabTitle;

  public static String TextMarkerEditorPreferencePage_condition_colors;

  public static String TextMarkerEditorPreferencePage_action_colors;

  public static String TextMarkerEditorPreferencePage_declaration_colors;

  public static String TextMarkerEditorPreferencePage_basicsymbols_colors;

  public static String TextMarkerEditorPreferencePage_function_colors;

  public static String TextMarkerEditorPreferencePage_then_colors;

  public static String TextMarkerSmartTypingConfigurationBlock_smartPaste_full;

  public static String TextMarkerSmartTypingConfigurationBlock_smartPaste_simple;

  public static String TextMarkerSmartTypingConfigurationBlock_tabs_title;

  public static String TextMarkerSmartTypingConfigurationBlock_autoclose_title;

  public static String TodoTaskDescription;

  public static String BuilderResolveImports;

  public static String BuilderImportByName;

  public static String BuilderIgnoreDuplicateShortnames;
}
