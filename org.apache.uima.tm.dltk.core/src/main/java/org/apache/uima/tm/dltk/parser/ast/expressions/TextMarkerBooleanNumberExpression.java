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
public class TextMarkerBooleanNumberExpression extends Expression {
  /**
   * <,<=,>,>=,==,...
   */
  private int kind;

  private Expression e1;

  private Expression e2;

  public TextMarkerBooleanNumberExpression(int start, int end, int operatorID, Expression e1,
          Expression e2) {
    super(start, end);
    this.kind = operatorID;
    // List list = new ArrayList<Expression>();
    // list.add(e1);
    // list.add(e2);
    // exprs = new ASTListNode(start, end, list);
    this.e1 = e1;
    this.e2 = e2;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.ast.statements.Statement#getKind()
   */
  @Override
  public int getKind() {
    return kind;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      // exprs.traverse(visitor);
      if (e1 != null) {
        e1.traverse(visitor);
      }
      if (e2 != null) {
        e2.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  public Expression getE1() {
    return e1;
  }

  public Expression getE2() {
    return e2;
  }

  @Override
  public void printNode(CorePrinter output) {
    // exprs.printNode(output);
    output.print("(");
    e1.printNode(output);
    output.print(" " + getOperator() + " ");
    e2.printNode(output);
    output.print(")");
  }

}
