/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast.declarations;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;

/**
 * @author Martin Toepfer
 * 
 */
public abstract class TextMarkerAbstractDeclaration extends FieldDeclaration {
  private SimpleReference ref;

  public TextMarkerAbstractDeclaration(String name, int nameStart, int nameEnd, int declStart,
          int declEnd, SimpleReference ref) {
    super(name, nameStart, nameEnd, nameStart, nameEnd); // declStart, declEnd);
    this.setName(name);
    this.ref = ref;
  }

  @Override
  public SimpleReference getRef() {
    return this.ref;
  }

  @Override
  public int getKind() {
    return D_VAR_DECL;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      this.ref.traverse(visitor);
      visitor.endvisit(this);
    }
  }

  @Override
  public int matchStart() {
    return this.getNameStart();
  }

  @Override
  public int matchLength() {
    return this.getName().length();
  }
}
