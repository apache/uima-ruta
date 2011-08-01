/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast.expressions;

import org.eclipse.dltk.ast.declarations.TypeDeclaration;

/**
 * Represents AnnotationTypes (not references to them).
 * 
 * @author Martin Toepfer
 * 
 */
public class TextMarkerBasicAnnotationType extends TypeDeclaration {

  public TextMarkerBasicAnnotationType(String name, int nameStart, int nameEnd, int start, int end) {
    super(name, nameStart, nameEnd, start, end);
  }

}
