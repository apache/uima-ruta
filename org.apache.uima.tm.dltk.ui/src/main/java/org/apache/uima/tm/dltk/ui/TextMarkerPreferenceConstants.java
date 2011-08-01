package org.apache.uima.tm.dltk.ui;

import org.apache.uima.tm.dltk.internal.ui.text.TextMarkerColorConstants;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.preferences.NewScriptProjectPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;


public class TextMarkerPreferenceConstants extends PreferenceConstants {

  public static final String EDITOR_FOLDING_IMPORTS = "editor_folding_default_imports"; //$NON-NLS-1$

  public static final String EDITOR_FOLDING_BLOCKS = "editor_folding_blocks"; //$NON-NLS-1$

  public static final int EDITOR_FOLDING_BLOCKS_OFF = 0;

  public static final int EDITOR_FOLDING_BLOCKS_INCLUDE = 1;

  public static final int EDITOR_FOLDING_BLOCKS_EXCLUDE = 2;

  public static final String EDITOR_FOLDING_INCLUDE_LIST = "editor_folding_include_list"; //$NON-NLS-1$

  public static final String EDITOR_FOLDING_EXCLUDE_LIST = "editor_folding_exclude_list"; //$NON-NLS-1$

  public static final String EDITOR_FOLDING_INIT_COMMENTS = "editor_folding_init_comments"; //$NON-NLS-1$

  public static final String EDITOR_FOLDING_INIT_NAMESPACES = "editor_folding_init_namespaces"; //$NON-NLS-1$

  public static final String EDITOR_FOLDING_INIT_BLOCKS = "editor_folding_init_blocks"; //$NON-NLS-1$

  public static final String EDITOR_FOLDING_COMMENTS_WITH_NEWLINES = "editor_folding_comments_lines"; //$NON-NLS-1$

  public static final String DOC_MAN_PAGES_LOCATIONS = "doc_man_pages_locations";

  public static final String EDITOR_FOLDING_COMMENTS_FOLDING = "editor_folding_comments";

  public final static String EDITOR_SINGLE_LINE_COMMENT_COLOR = TextMarkerColorConstants.TM_SINGLE_LINE_COMMENT;

  public final static String EDITOR_SINGLE_LINE_COMMENT_BOLD = TextMarkerColorConstants.TM_SINGLE_LINE_COMMENT
          + EDITOR_BOLD_SUFFIX;

  public final static String EDITOR_SINGLE_LINE_COMMENT_ITALIC = TextMarkerColorConstants.TM_SINGLE_LINE_COMMENT
          + EDITOR_ITALIC_SUFFIX;

  public final static String EDITOR_SINGLE_LINE_COMMENT_STRIKETHROUGH = TextMarkerColorConstants.TM_SINGLE_LINE_COMMENT
          + EDITOR_STRIKETHROUGH_SUFFIX;

  public final static String EDITOR_SINGLE_LINE_COMMENT_UNDERLINE = TextMarkerColorConstants.TM_SINGLE_LINE_COMMENT
          + EDITOR_UNDERLINE_SUFFIX;

  public final static String EDITOR_NUMBER_COLOR = TextMarkerColorConstants.TM_NUMBER;

  public final static String EDITOR_NUMBER_BOLD = TextMarkerColorConstants.TM_NUMBER
          + EDITOR_BOLD_SUFFIX;

  public final static String EDITOR_NUMBER_ITALIC = TextMarkerColorConstants.TM_NUMBER
          + EDITOR_ITALIC_SUFFIX;

  public final static String EDITOR_NUMBER_STRIKETHROUGH = TextMarkerColorConstants.TM_NUMBER
          + EDITOR_STRIKETHROUGH_SUFFIX;

  public final static String EDITOR_NUMBER_UNDERLINE = TextMarkerColorConstants.TM_NUMBER
          + EDITOR_UNDERLINE_SUFFIX;

  public final static String EDITOR_STRING_COLOR = TextMarkerColorConstants.TM_STRING;

  public final static String EDITOR_STRING_BOLD = TextMarkerColorConstants.TM_STRING
          + EDITOR_BOLD_SUFFIX;

  public final static String EDITOR_STRING_ITALIC = TextMarkerColorConstants.TM_STRING
          + EDITOR_ITALIC_SUFFIX;

  public final static String EDITOR_STRING_STRIKETHROUGH = TextMarkerColorConstants.TM_STRING
          + EDITOR_STRIKETHROUGH_SUFFIX;

  public final static String EDITOR_STRING_UNDERLINE = TextMarkerColorConstants.TM_STRING
          + EDITOR_UNDERLINE_SUFFIX;

  public final static String EDITOR_FUNCTION_COLOR = TextMarkerColorConstants.TM_FUNCTION;

  public final static String EDITOR_FUNCTION_COLOR_BOLD = TextMarkerColorConstants.TM_FUNCTION
          + EDITOR_BOLD_SUFFIX;

  public final static String EDITOR_FUNCTION_COLOR_ITALIC = TextMarkerColorConstants.TM_FUNCTION
          + EDITOR_ITALIC_SUFFIX;

  public final static String EDITOR_FUNCTION_COLOR_STRIKETHROUGH = TextMarkerColorConstants.TM_FUNCTION
          + EDITOR_STRIKETHROUGH_SUFFIX;

  public final static String EDITOR_FUNCTION_COLOR_UNDERLINE = TextMarkerColorConstants.TM_FUNCTION
          + EDITOR_UNDERLINE_SUFFIX;

  public final static String EDITOR_CONDITION_COLOR = TextMarkerColorConstants.TM_CONDITION;

  public final static String EDITOR_CONDITION_COLOR_BOLD = TextMarkerColorConstants.TM_CONDITION
          + EDITOR_BOLD_SUFFIX;

  public final static String EDITOR_CONDITION_COLOR_ITALIC = TextMarkerColorConstants.TM_CONDITION
          + EDITOR_ITALIC_SUFFIX;

  public final static String EDITOR_CONDITION_COLOR_STRIKETHROUGH = TextMarkerColorConstants.TM_CONDITION
          + EDITOR_STRIKETHROUGH_SUFFIX;

  public final static String EDITOR_CONDITION_COLOR_UNDERLINE = TextMarkerColorConstants.TM_CONDITION
          + EDITOR_UNDERLINE_SUFFIX;

  public final static String EDITOR_ACTION_COLOR = TextMarkerColorConstants.TM_ACTION;

  public final static String EDITOR_ACTION_COLOR_BOLD = TextMarkerColorConstants.TM_ACTION
          + EDITOR_BOLD_SUFFIX;

  public final static String EDITOR_ACTION_COLOR_ITALIC = TextMarkerColorConstants.TM_ACTION
          + EDITOR_ITALIC_SUFFIX;

  public final static String EDITOR_ACTION_COLOR_STRIKETHROUGH = TextMarkerColorConstants.TM_ACTION
          + EDITOR_STRIKETHROUGH_SUFFIX;

  public final static String EDITOR_ACTION_COLOR_UNDERLINE = TextMarkerColorConstants.TM_ACTION
          + EDITOR_UNDERLINE_SUFFIX;

  public final static String EDITOR_THEN_COLOR = TextMarkerColorConstants.TM_THEN;

  public final static String EDITOR_THEN_COLOR_BOLD = TextMarkerColorConstants.TM_THEN
          + EDITOR_BOLD_SUFFIX;

  public final static String EDITOR_THEN_COLOR_ITALIC = TextMarkerColorConstants.TM_THEN
          + EDITOR_ITALIC_SUFFIX;

  public final static String EDITOR_THEN_COLOR_STRIKETHROUGH = TextMarkerColorConstants.TM_THEN
          + EDITOR_STRIKETHROUGH_SUFFIX;

  public final static String EDITOR_THEN_COLOR_UNDERLINE = TextMarkerColorConstants.TM_THEN
          + EDITOR_UNDERLINE_SUFFIX;

  public final static String EDITOR_DECLARATION_DEFINITION_COLOR = TextMarkerColorConstants.TM_DECLARATION;

  public final static String EDITOR_DECLARATION_DEFINITION_COLOR_BOLD = TextMarkerColorConstants.TM_DECLARATION
          + EDITOR_BOLD_SUFFIX;

  public final static String EDITOR_DECLARATION_DEFINITION_COLOR_ITALIC = TextMarkerColorConstants.TM_DECLARATION
          + EDITOR_ITALIC_SUFFIX;

  public final static String EDITOR_DECLARATION_DEFINITION_COLOR_STRIKETHROUGH = TextMarkerColorConstants.TM_DECLARATION
          + EDITOR_STRIKETHROUGH_SUFFIX;

  public final static String EDITOR_DECLARATION_DEFINITION_COLOR_UNDERLINE = TextMarkerColorConstants.TM_DECLARATION
          + EDITOR_UNDERLINE_SUFFIX;

  public final static String EDITOR_BASICSYMBOL_DEFINITION_COLOR = TextMarkerColorConstants.TM_BASICSYMBOL;

  public final static String EDITOR_BASICSYMBOL_DEFINITION_COLOR_BOLD = TextMarkerColorConstants.TM_BASICSYMBOL
          + EDITOR_BOLD_SUFFIX;

  public final static String EDITOR_BASICSYMBOL_DEFINITION_COLOR_ITALIC = TextMarkerColorConstants.TM_BASICSYMBOL
          + EDITOR_ITALIC_SUFFIX;

  public final static String EDITOR_BASICSYMBOL_DEFINITION_COLOR_STRIKETHROUGH = TextMarkerColorConstants.TM_BASICSYMBOL
          + EDITOR_STRIKETHROUGH_SUFFIX;

  public final static String EDITOR_BASICSYMBOL_DEFINITION_COLOR_UNDERLINE = TextMarkerColorConstants.TM_BASICSYMBOL
          + EDITOR_UNDERLINE_SUFFIX;

  public final static String EDITOR_DOC_COMMENT_COLOR = TextMarkerColorConstants.TM_DOC_COMMENT;

  public final static String EDITOR_DOC_COMMENT_COLOR_BOLD = TextMarkerColorConstants.TM_DOC_COMMENT
          + EDITOR_BOLD_SUFFIX;

  public final static String EDITOR_DOC_COMMENT_COLOR_ITALIC = TextMarkerColorConstants.TM_DOC_COMMENT
          + EDITOR_ITALIC_SUFFIX;

  public final static String EDITOR_DOC_COMMENT_COLOR_STRIKETHROUGH = TextMarkerColorConstants.TM_DOC_COMMENT
          + EDITOR_STRIKETHROUGH_SUFFIX;

  public final static String EDITOR_DOC_COMMENT_COLOR_UNDERLINE = TextMarkerColorConstants.TM_DOC_COMMENT
          + EDITOR_UNDERLINE_SUFFIX;

  public final static String EDITOR_VARIABLE_COLOR = TextMarkerColorConstants.TM_DOC_COMMENT;

  public final static String EDITOR_VARIABLE_COLOR_BOLD = TextMarkerColorConstants.TM_DOC_COMMENT
          + EDITOR_BOLD_SUFFIX;

  public final static String EDITOR_VARIABLE_COLOR_ITALIC = TextMarkerColorConstants.TM_DOC_COMMENT
          + EDITOR_ITALIC_SUFFIX;

  public final static String EDITOR_VARIABLE_COLOR_STRIKETHROUGH = TextMarkerColorConstants.TM_DOC_COMMENT
          + EDITOR_STRIKETHROUGH_SUFFIX;

  public final static String EDITOR_VARIABLE_COLOR_UNDERLINE = TextMarkerColorConstants.TM_DOC_COMMENT
          + EDITOR_UNDERLINE_SUFFIX;

  public final static String EDITOR_SMART_PASTE_MODE = "smartPasteMode"; //$NON-NLS-1$

  public final static int EDITOR_SMART_PASTE_MODE_SIMPLE = 1;

  public final static int EDITOR_SMART_PASTE_MODE_FULL = 2;

  public static final String DOC_TM_PAGES_LOCATIONS = "doc_tm_pages_location";

  public static final String COMMENT_TASK_TAGS = TextMarkerColorConstants.TM_TODO_TAG;// TextMarkerColorConstants

  public static final String COMMENT_TASK_TAGS_BOLD = COMMENT_TASK_TAGS + EDITOR_BOLD_SUFFIX;

  /**
   * A preference that controls the selected formatter.
   */
  public static final String FORMATTER_ID = "formatterId"; //$NON-NLS-1$

  public static void initializeDefaultValues(IPreferenceStore store) {
    PreferenceConstants.initializeDefaultValues(store);

    PreferenceConverter.setDefault(store, COMMENT_TASK_TAGS, new RGB(127, 159, 191));
    store.setDefault(COMMENT_TASK_TAGS_BOLD, true);

    PreferenceConverter.setDefault(store,
            TextMarkerPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR, new RGB(63, 127, 95));
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_BOLD, false);
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_ITALIC, false);

    PreferenceConverter.setDefault(store, TextMarkerPreferenceConstants.EDITOR_DOC_COMMENT_COLOR,
            new RGB(63, 127, 95));

    PreferenceConverter.setDefault(store, TextMarkerPreferenceConstants.EDITOR_STRING_COLOR,
            new RGB(42, 0, 255));
    PreferenceConverter.setDefault(store, TextMarkerPreferenceConstants.EDITOR_NUMBER_COLOR,
            new RGB(128, 0, 0));

    PreferenceConverter.setDefault(store, TextMarkerPreferenceConstants.EDITOR_VARIABLE_COLOR,
            new RGB(200, 0, 0));

    // Functions
    PreferenceConverter.setDefault(store, TextMarkerPreferenceConstants.EDITOR_FUNCTION_COLOR,
            new RGB(50, 50, 200));
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_FUNCTION_COLOR_BOLD, true);

    // Conditions
    PreferenceConverter.setDefault(store, TextMarkerPreferenceConstants.EDITOR_CONDITION_COLOR,
            new RGB(0, 128, 0));
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_CONDITION_COLOR_BOLD, true);

    // Actions
    PreferenceConverter.setDefault(store, TextMarkerPreferenceConstants.EDITOR_ACTION_COLOR,
            new RGB(0, 0, 128));
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_ACTION_COLOR_BOLD, true);

    // Then
    PreferenceConverter.setDefault(store, TextMarkerPreferenceConstants.EDITOR_THEN_COLOR, new RGB(
            0, 0, 0));
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_THEN_COLOR_BOLD, true);

    // Declarations
    PreferenceConverter.setDefault(store,
            TextMarkerPreferenceConstants.EDITOR_DECLARATION_DEFINITION_COLOR, new RGB(128, 0, 0));
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_DECLARATION_DEFINITION_COLOR_BOLD, true);

    // basic tokens
    PreferenceConverter.setDefault(store,
            TextMarkerPreferenceConstants.EDITOR_BASICSYMBOL_DEFINITION_COLOR, new RGB(128, 128,
                    128));
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_BASICSYMBOL_DEFINITION_COLOR_BOLD, true);

    store.setDefault(PreferenceConstants.EDITOR_CLOSE_STRINGS, false);
    store.setDefault(PreferenceConstants.EDITOR_CLOSE_BRACKETS, true);
    store.setDefault(PreferenceConstants.EDITOR_CLOSE_BRACES, true);
    store.setDefault(PreferenceConstants.EDITOR_SMART_TAB, true);
    store.setDefault(PreferenceConstants.EDITOR_SMART_PASTE, true);
    store.setDefault(PreferenceConstants.EDITOR_SMART_HOME_END, true);
    store.setDefault(PreferenceConstants.EDITOR_SUB_WORD_NAVIGATION, true);
    store.setDefault(PreferenceConstants.EDITOR_TAB_WIDTH, 4);
    store.setDefault(PreferenceConstants.EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE, true);

    // folding
    store.setDefault(PreferenceConstants.EDITOR_FOLDING_ENABLED, true);
    store.setDefault(PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED, true);
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS,
            TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS_EXCLUDE);
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_FOLDING_INCLUDE_LIST, "BLOCK");
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_FOLDING_EXCLUDE_LIST, "");
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_FOLDING_COMMENTS_WITH_NEWLINES, true);
    store.setDefault(TextMarkerPreferenceConstants.EDITOR_FOLDING_INIT_COMMENTS, true);
    store.setDefault(PreferenceConstants.EDITOR_FOLDING_LINES_LIMIT, 5);

    store.setDefault(CodeFormatterConstants.FORMATTER_TAB_CHAR, CodeFormatterConstants.SPACE);
    store.setDefault(CodeFormatterConstants.FORMATTER_TAB_SIZE, "4");
    store.setDefault(CodeFormatterConstants.FORMATTER_INDENTATION_SIZE, "4");

    NewScriptProjectPreferencePage.initDefaults(store);

    store.setDefault(PreferenceConstants.APPEARANCE_COMPRESS_PACKAGE_NAMES, false);
    store.setDefault(PreferenceConstants.APPEARANCE_METHOD_RETURNTYPE, false);
    store.setDefault(PreferenceConstants.APPEARANCE_METHOD_TYPEPARAMETERS, true);
    store.setDefault(PreferenceConstants.APPEARANCE_PKG_NAME_PATTERN_FOR_PKG_VIEW, ""); //$NON-NLS-1$

    store.setDefault(PreferenceConstants.SHOW_SOURCE_MODULE_CHILDREN, true);

    store.setDefault(PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS, ".");

  }
}
