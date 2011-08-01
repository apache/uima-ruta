/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

/**
 * Wrapper for top level expressions like number, boolean, string, type..
 * 
 * @author Martin Toepfer
 * 
 */
public class TextMarkerExpression extends Expression {
  private Expression expression;

  private boolean inParantheses;

  /**
   * see {@link TMTypeConstants}
   */
  private int type;

  public TextMarkerExpression(int sourceStart, int sourceEnd, Expression expression, int type) {
    super(sourceStart, sourceEnd);
    this.expression = expression;
    this.type = type;
    this.inParantheses = false;
    if (expression instanceof TextMarkerExpression) {
      this.inParantheses = ((TextMarkerExpression) expression).isInParantheses();
    }
  }

  public int getType() {
    return this.type;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (expression != null) {
        expression.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  public Expression getExpression() {
    return this.expression == null ? this : this.expression;
  }

  @Override
  public int getKind() {
    return expression.getKind();
  }

  public boolean isInParantheses() {
    return inParantheses;
  }

  public void setInParantheses(boolean inParantheses) {
    this.inParantheses = inParantheses;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }
}
