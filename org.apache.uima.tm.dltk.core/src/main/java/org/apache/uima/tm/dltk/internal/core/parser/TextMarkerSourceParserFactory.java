package org.apache.uima.tm.dltk.internal.core.parser;

import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;

public class TextMarkerSourceParserFactory implements ISourceParserFactory {

  public ISourceParser createSourceParser() {
    return new TextMarkerSourceParser();
  }

}
