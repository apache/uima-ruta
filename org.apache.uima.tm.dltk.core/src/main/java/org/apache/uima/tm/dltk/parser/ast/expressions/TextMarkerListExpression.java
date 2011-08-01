package org.apache.uima.tm.dltk.parser.ast.expressions;

import java.util.List;

import org.apache.uima.tm.dltk.parser.ast.TMTypeConstants;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerExpression;
import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;
import org.eclipse.dltk.utils.CorePrinter;


public class TextMarkerListExpression extends TextMarkerExpression {
  private ASTListNode exprs;

  private int type;

  public TextMarkerListExpression(int start, int end, List<Expression> exprList, int type) {
    super(start, end, null, TMTypeConstants.TM_TYPE_S);
    this.setExprs(new ASTListNode(start, end, exprList));
    this.type = type;
  }

  @Override
  public int getKind() {
    return ExpressionConstants.E_CONCAT;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      this.getExprs().traverse(visitor);
    }
  }

  @Override
  public void printNode(CorePrinter output) {
    getExprs().printNode(output);
  }

  @Override
  public String getOperator() {
    return ",";
  }

  public void setExprs(ASTListNode exprs) {
    this.exprs = exprs;
  }

  public ASTListNode getExprs() {
    return exprs;
  }

}
