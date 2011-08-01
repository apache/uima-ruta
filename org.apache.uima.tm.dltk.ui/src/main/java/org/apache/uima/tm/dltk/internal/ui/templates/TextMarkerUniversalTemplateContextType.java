package org.apache.uima.tm.dltk.internal.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;
import org.eclipse.jface.text.IDocument;

public class TextMarkerUniversalTemplateContextType extends ScriptTemplateContextType {
  public static final String CONTEXT_TYPE_ID = "tmUniversalTemplateContextType";

  public TextMarkerUniversalTemplateContextType() {
    // empty constructor
  }

  public TextMarkerUniversalTemplateContextType(String id) {
    super(id);
  }

  public TextMarkerUniversalTemplateContextType(String id, String name) {
    super(id, name);
  }

  @Override
  public ScriptTemplateContext createContext(IDocument document, int offset, int length,
          ISourceModule sourceModule) {
    return new TextMarkerTemplateContext(this, document, offset, length, sourceModule);
  }
}
