package org.apache.uima.tm.dltk.parser.ast.declarations;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.dltk.parser.ast.TMStatementConstants;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;


/**
 * Container for declarations,<br>
 * i.e.:<br>
 * "INT a, b, c = 2;"<br>
 * is a TextMarkerDeclarationsStatement with 3 TextMarkerVariableDeclarations.
 * 
 * @author Martin Toepfer
 * 
 */
public class TextMarkerDeclarationsStatement extends Statement {
  private List<TextMarkerAbstractDeclaration> declarations;

  private int typeTokenStart;

  private int typeTokenEnd;

  private Expression initExpr;

  // public TextMarkerDeclarationsStatement(int declStart, int declEnd,
  // List<TextMarkerAbstractDeclaration> declarations, Expression init) {
  // this(declStart, declEnd, declarations, init, 0, 0);
  // }

  public TextMarkerDeclarationsStatement(int stmtStart, int stmtEnd,
          List<TextMarkerAbstractDeclaration> declarations, Expression init, int typeTokenStart,
          int typeTokenEnd) {
    super(stmtStart, stmtEnd);
    this.declarations = declarations == null ? new ArrayList<TextMarkerAbstractDeclaration>()
            : declarations;
    this.initExpr = init;
    this.typeTokenStart = typeTokenStart;
    this.typeTokenEnd = typeTokenEnd;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (declarations != null) {
        for (TextMarkerAbstractDeclaration decl : declarations) {
          decl.traverse(visitor);
        }
      }
      if (initExpr != null) {
        initExpr.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public int getKind() {
    return TMStatementConstants.S_DECLARATIONS;
  }

  public void addDeclaration(TextMarkerAbstractDeclaration decl) {
    if (decl != null) {
      this.declarations.add(decl);
    }
  }

  public int getDeclarationsCount() {
    return this.declarations == null ? 0 : this.declarations.size();
  }

  public List<TextMarkerAbstractDeclaration> getDeclarations() {
    return declarations;
  }

  public Expression getInitExpr() {
    return initExpr;
  }

  public int getTypeTokenStart() {
    return typeTokenStart;
  }

  public int getTypeTokenEnd() {
    return typeTokenEnd;
  }

}
