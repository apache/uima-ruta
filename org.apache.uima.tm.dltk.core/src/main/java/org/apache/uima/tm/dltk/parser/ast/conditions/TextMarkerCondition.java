package org.apache.uima.tm.dltk.parser.ast.conditions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class TextMarkerCondition extends Expression {
  protected List<Expression> exprs;

  protected int kind;

  private int nameStart;

  private int nameEnd;

  private String name;

  /**
   * @param start
   * @param end
   * @param exprs
   * @param kind
   */
  public TextMarkerCondition(int start, int end, List<Expression> exprs, int kind, String name,
          int nameStart, int nameEnd) {
    super(start, end);
    if (exprs != null) {
      this.exprs = exprs;
    } else {
      this.exprs = new ArrayList<Expression>();
    }
    this.kind = kind;
    this.name = name;
    this.nameStart = nameStart;
    this.nameEnd = nameEnd;
  }

  @Override
  public int getKind() {
    return this.kind;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      for (Iterator iterator = exprs.iterator(); iterator.hasNext();) {
        Expression expr = (Expression) iterator.next();
        if (expr != null) {
          expr.traverse(visitor);
        }
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public List getChilds() {
    return exprs;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }

  public String getName() {
    return name;
  }

  public int getNameStart() {
    return nameStart;
  }

  public int getNameEnd() {
    return nameEnd;
  }
}
