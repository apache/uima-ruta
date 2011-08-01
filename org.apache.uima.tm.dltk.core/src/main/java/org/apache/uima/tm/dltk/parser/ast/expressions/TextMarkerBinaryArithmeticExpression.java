/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast.expressions;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerBinaryArithmeticExpression extends Expression {
  private Expression exprA;

  private Expression exprB;

  /**
   * Classifies the type of Expression represented (+,-,*,...).
   */
  private int kind;

  public TextMarkerBinaryArithmeticExpression(int start, int end, Expression exprA,
          Expression exprB, int kind) {
    super(start, end);
    this.exprA = exprA;
    this.exprB = exprB;
    this.kind = kind;
  }

  @Override
  public int getKind() {
    return this.kind;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (exprA != null) {
        exprA.traverse(visitor);
      }
      if (exprB != null) {
        exprB.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public void printNode(CorePrinter output) {
    output.formatPrintLn("[" + exprA.toString() + " " + getOperator() + " " + exprB.toString()
            + "]");
  }
}
