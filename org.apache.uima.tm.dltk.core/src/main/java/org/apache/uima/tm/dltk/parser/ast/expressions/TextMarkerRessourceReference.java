/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast.expressions;

import org.apache.uima.tm.dltk.parser.ast.TMExpressionConstants;
import org.eclipse.dltk.ast.expressions.StringLiteral;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerRessourceReference extends StringLiteral {

  public TextMarkerRessourceReference(int start, int end, String value) {
    super(start, end, value);
  }

  @Override
  public int getKind() {
    return TMExpressionConstants.E_RESSOURCE;
  }

}
