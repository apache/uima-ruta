/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast.expressions;

import org.apache.uima.tm.dltk.parser.ast.TMExpressionConstants;
import org.eclipse.dltk.ast.expressions.Expression;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerQuantifierLiteralExpression extends Expression {
  private String operator;

  public TextMarkerQuantifierLiteralExpression(int start, int end, String operator) {
    super(start, end);
    this.operator = operator == null ? "" : operator;
  }

  @Override
  public String getOperator() {
    return this.operator;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.ast.statements.Statement#getKind()
   */
  @Override
  public int getKind() {
    return TMExpressionConstants.E_QUANTIFIER_LIT;
  }

}
