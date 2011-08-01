package org.apache.uima.tm.dltk.parser.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;

public class TextMarkerRuleElement extends Expression {
  List<Expression> conditions;

  List<Expression> actions;

  Expression head;

  private List<Expression> quantifierExpressions;

  // TODO to be removed
  public TextMarkerRuleElement(int start, int end) {
    super(start, end);
  }

  public TextMarkerRuleElement(int start, int end, Expression head,
          List<Expression> quantifierPartExpressions, List<Expression> conditions,
          List<Expression> actions) {
    super(start, end);
    if (conditions != null) {
      this.conditions = conditions;
    } else {
      conditions = new ArrayList<Expression>();
    }
    if (actions != null) {
      this.actions = actions;
    } else {
      actions = new ArrayList<Expression>();
    }
    if (quantifierPartExpressions != null) {
      this.quantifierExpressions = quantifierPartExpressions;
    } else {
      this.quantifierExpressions = new ArrayList<Expression>();
    }
    this.head = head;
  }

  @Override
  public int getKind() {
    return ExpressionConstants.E_CALL;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (head != null) {
        head.traverse(visitor);
      }
      if (quantifierExpressions != null) {
        for (Expression qpe : quantifierExpressions) {
          qpe.traverse(visitor);
        }
      }
      if (conditions != null) {
        for (Expression cond : conditions) {
          cond.traverse(visitor);
        }
      }
      if (actions != null) {
        for (Expression action : actions) {
          action.traverse(visitor);
        }
      }
    }
  }

  public Expression getHead() {
    return head;
  }

  public List<Expression> getActions() {
    return actions;
  }

  public List<Expression> getConditions() {
    return conditions;
  }

  public List<Expression> getQuantifierExpressions() {
    return quantifierExpressions;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }

}
