package org.apache.uima.tm.dltk.parser.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;

public class ComposedRuleElement extends TextMarkerRuleElement {
  List<Expression> elements;

  private List<Expression> quantifierExpressions;

  public ComposedRuleElement(int start, int end, List<Expression> elements,
          List<Expression> quantifierExpressions) {
    super(start, end);
    if (elements != null) {
      this.elements = elements;
    } else {
      conditions = new ArrayList<Expression>();
    }

    if (quantifierExpressions != null) {
      this.quantifierExpressions = quantifierExpressions;
    } else {
      this.quantifierExpressions = new ArrayList<Expression>();
    }
  }

  @Override
  public int getKind() {
    return ExpressionConstants.E_CALL;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (elements != null) {
        for (Expression cond : elements) {
          cond.traverse(visitor);
        }
      }
      if (quantifierExpressions != null) {
        for (Expression qpe : quantifierExpressions) {
          qpe.traverse(visitor);
        }
      }
    }
  }

  @Override
  public Expression getHead() {
    return head;
  }

  public List<Expression> getElements() {
    return elements;
  }

  @Override
  public List<Expression> getQuantifierExpressions() {
    return quantifierExpressions;
  }

}
