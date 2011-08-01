/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerAction extends Expression {
  protected List<Expression> exprs;

  protected int kind;

  private int nameStart;

  public int getNameStart() {
    return nameStart;
  }

  public int getNameEnd() {
    return nameEnd;
  }

  private int nameEnd;

  private String name;

  /**
   * @param start
   * @param end
   * @param exprs
   * @param kind
   */
  public TextMarkerAction(int start, int end, List<Expression> exprs, int kind, String name,
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

  /**
   * Copy-Constructor
   * 
   * @param source
   */
  public TextMarkerAction(TextMarkerAction source) {
    super(source.sourceStart(), source.sourceEnd());
    this.exprs = source.exprs;
    this.kind = source.kind;
  }

  @Override
  public int getKind() {
    return this.kind;
  }

  public String getName() {
    return this.name;
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
}
