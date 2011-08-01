package org.apache.uima.tm.dltk.core;

import java.util.regex.Pattern;

import org.eclipse.dltk.core.ScriptContentDescriber;

public class TextMarkerContentDescriber extends ScriptContentDescriber {
  protected static Pattern[] header_patterns = { Pattern.compile("\\s*PACKAGE", Pattern.MULTILINE), }; //$NON-NLS-1$

  @Override
  protected Pattern[] getHeaderPatterns() {
    return header_patterns;
  }
}
