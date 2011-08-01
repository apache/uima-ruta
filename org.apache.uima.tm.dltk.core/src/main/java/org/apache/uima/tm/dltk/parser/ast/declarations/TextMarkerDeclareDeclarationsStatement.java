package org.apache.uima.tm.dltk.parser.ast.declarations;

import java.util.List;

import org.apache.uima.tm.dltk.parser.ast.TMStatementConstants;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerDeclareDeclarationsStatement extends TextMarkerDeclarationsStatement {
  private ASTNode parent;

  // public TextMarkerDeclarationsStatement(int declStart, int declEnd,
  // List<TextMarkerAbstractDeclaration> declarations, Expression init) {
  // this(declStart, declEnd, declarations, init, 0, 0);
  // }

  public TextMarkerDeclareDeclarationsStatement(int stmtStart, int stmtEnd,
          List<TextMarkerAbstractDeclaration> declarations, ASTNode parent, int typeTokenStart,
          int typeTokenEnd) {
    super(stmtStart, stmtEnd, declarations, null, typeTokenStart, typeTokenEnd);
    this.parent = parent;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (parent != null) {
        parent.traverse(visitor);
      }
      if (getDeclarations() != null) {
        for (TextMarkerAbstractDeclaration decl : getDeclarations()) {
          decl.traverse(visitor);
        }
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public int getKind() {
    return TMStatementConstants.S_DECLARATIONS;
  }

  public ASTNode getParent() {
    return parent;
  }

}
