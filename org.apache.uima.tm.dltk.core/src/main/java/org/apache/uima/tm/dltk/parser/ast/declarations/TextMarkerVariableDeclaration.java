/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast.declarations;

import org.apache.uima.tm.dltk.parser.ast.TMTypeConstants;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerVariableDeclaration extends TextMarkerAbstractDeclaration {

  private int type;

  private boolean hasInitExpression;

  private Expression initExpression;

  //
  // @Deprecated
  // public TextMarkerVariableDeclaration(String name, int nameStart, int nameEnd, int declStart,
  // int declEnd, SimpleReference ref) {
  // super(name, nameStart, nameEnd, declStart, declEnd, ref);
  // hasInitExpression = false;
  // }

  public TextMarkerVariableDeclaration(String name, int nameStart, int nameEnd, int declStart,
          int declEnd, SimpleReference ref, int type) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref);
    this.type = type;
    hasInitExpression = false;
  }

  public TextMarkerVariableDeclaration(String name, int nameStart, int nameEnd, int declStart,
          int declEnd, SimpleReference ref, int type, Expression initExpression) {
    this(name, nameStart, nameEnd, declStart, declEnd, ref, type);
    this.initExpression = initExpression;
    if (initExpression != null) {
      hasInitExpression = true;
    }
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      getRef().traverse(visitor);
      if (hasInitExpression) {
        initExpression.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public String toString() {
    return this.getName() + ":: TextMarkerIntVariable :: " + super.toString();
  }

  /**
   * @return see {@link TMTypeConstants}
   */
  public int getType() {
    return this.type;
  }

  public boolean hasInitExpression() {
    return hasInitExpression;
  }
}
