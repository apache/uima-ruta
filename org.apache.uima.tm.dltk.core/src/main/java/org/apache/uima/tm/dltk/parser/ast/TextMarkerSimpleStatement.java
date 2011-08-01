/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

/**
 * Simple statement with just one expression argument.
 * 
 * @author Martin Toepfer
 * 
 */
public abstract class TextMarkerSimpleStatement extends Statement {
  private Expression expression;

  public TextMarkerSimpleStatement(int sourceStart, int sourceEnd, Expression expression) {
    super(sourceStart, sourceEnd);
    this.expression = expression;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.ast.statements.Statement#getKind()
   */
  @Override
  public abstract int getKind();

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      expression.traverse(visitor);
      visitor.endvisit(this);
    }
  }

  public Expression getExpression() {
    return this.expression;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }
}
