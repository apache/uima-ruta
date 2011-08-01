package org.apache.uima.tm.dltk.internal.ui.templates;

import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;

public class TextMarkerTemplateCompletionProcessor extends ScriptTemplateCompletionProcessor {

  private static char[] IGNORE = new char[] { '.' };

  public TextMarkerTemplateCompletionProcessor(ScriptContentAssistInvocationContext context) {
    super(context);
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor#getContextTypeId()
   */
  @Override
  protected String getContextTypeId() {
    return TextMarkerUniversalTemplateContextType.CONTEXT_TYPE_ID;
  }

  /*
   * @see org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor#getTemplateAccess()
   */
  @Override
  protected ScriptTemplateAccess getTemplateAccess() {
    return TextMarkerTemplateAccess.getInstance();
  }

  @Override
  protected char[] getIgnore() {
    return IGNORE;
  }
}
