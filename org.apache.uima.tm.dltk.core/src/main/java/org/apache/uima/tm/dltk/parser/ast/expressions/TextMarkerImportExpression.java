/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast.expressions;

import org.eclipse.dltk.ast.expressions.ExpressionConstants;
import org.eclipse.dltk.ast.references.SimpleReference;

/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerImportExpression extends SimpleReference {

  public TextMarkerImportExpression(int start, int end, String name) {
    super(start, end, name);
  }

  @Override
  public int getKind() {
    return ExpressionConstants.E_IMPORT;
  }

}
