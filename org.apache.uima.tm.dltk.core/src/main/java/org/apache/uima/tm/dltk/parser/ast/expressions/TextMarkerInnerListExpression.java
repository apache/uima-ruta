/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast.expressions;

import org.apache.uima.tm.dltk.parser.ast.TMExpressionConstants;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerInnerListExpression extends Expression {
  private String innerList;

  /**
   * @param start
   * @param end
   */
  public TextMarkerInnerListExpression(int start, int end, String inner) {
    super(start, end);
    this.innerList = inner == null ? "" : inner;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      visitor.endvisit(this);
    }
  }

  @Override
  public String getOperator() {
    return TMExpressionConstants.E_INNERLIST_STR;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.ast.statements.Statement#getKind()
   */
  @Override
  public int getKind() {
    return TMExpressionConstants.E_INNERLIST;
  }

  /**
   * @param innerList
   *          the innerList to set
   */
  public void setInnerList(String innerList) {
    this.innerList = innerList;
  }

  /**
   * @return the innerList
   */
  public String getInnerList() {
    return innerList;
  }

}
