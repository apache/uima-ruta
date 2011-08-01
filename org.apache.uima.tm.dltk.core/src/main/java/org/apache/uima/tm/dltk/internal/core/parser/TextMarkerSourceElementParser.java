package org.apache.uima.tm.dltk.internal.core.parser;

import org.apache.uima.tm.dltk.core.TextMarkerNature;
import org.apache.uima.tm.dltk.internal.core.parser.visitor.TextMarkerSourceElementRequestVisitor;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.core.AbstractSourceElementParser;


public class TextMarkerSourceElementParser extends AbstractSourceElementParser {

  /*
   * @see org.eclipse.dltk.core.AbstractSourceElementParser#createVisitor()
   */
  @Override
  protected SourceElementRequestVisitor createVisitor() {
    return new TextMarkerSourceElementRequestVisitor(getRequestor());
  }

  /*
   * @see org.eclipse.dltk.core.AbstractSourceElementParser#getNatureId()
   */
  @Override
  protected String getNatureId() {
    return TextMarkerNature.NATURE_ID;
  }

}
