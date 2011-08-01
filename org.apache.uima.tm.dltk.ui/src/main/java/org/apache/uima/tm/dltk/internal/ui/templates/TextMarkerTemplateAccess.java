package org.apache.uima.tm.dltk.internal.ui.templates;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * Provides access to TextMarker template store.
 */
public class TextMarkerTemplateAccess extends ScriptTemplateAccess {
  // Template
  private static final String CUSTOM_TEMPLATES_KEY = "org.eclipse.textmarker.Templates";

  private static TextMarkerTemplateAccess instance;

  public static TextMarkerTemplateAccess getInstance() {
    if (instance == null) {
      instance = new TextMarkerTemplateAccess();
    }

    return instance;
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplateAccess#getContextTypeId()
   */
  @Override
  protected String getContextTypeId() {
    return TextMarkerUniversalTemplateContextType.CONTEXT_TYPE_ID;
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplateAccess#getCustomTemplatesKey()
   */
  @Override
  protected String getCustomTemplatesKey() {
    return CUSTOM_TEMPLATES_KEY;
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplateAccess#getPreferenceStore()
   */
  @Override
  protected IPreferenceStore getPreferenceStore() {
    return TextMarkerUI.getDefault().getPreferenceStore();
  }
}
