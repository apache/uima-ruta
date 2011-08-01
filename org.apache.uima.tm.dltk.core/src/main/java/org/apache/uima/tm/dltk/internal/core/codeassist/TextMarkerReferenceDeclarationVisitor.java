/**
 * 
 */
package org.apache.uima.tm.dltk.internal.core.codeassist;

import org.apache.uima.tm.dltk.parser.ast.declarations.TextMarkerDeclarationsStatement;
import org.apache.uima.tm.dltk.parser.ast.expressions.TextMarkerVariableReference;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerReferenceDeclarationVisitor extends ASTVisitor {
  private ASTNode result = null;

  private int start = 0;

  public TextMarkerReferenceDeclarationVisitor(int start) {
    this.start = start;
  }

  @Override
  public boolean visitGeneral(ASTNode node) throws Exception {
    if (node.sourceStart() <= start && start <= node.sourceEnd()) {
      return true;
    }
    return false;
  }

  @Override
  public boolean visit(Statement s) throws Exception {
    if (s instanceof TextMarkerDeclarationsStatement || s instanceof FieldDeclaration) {
      return true && super.visit(s);
    }
    return false;
  }

  @Override
  public boolean visit(Expression s) throws Exception {
    if (s instanceof TextMarkerVariableReference) {
      result = s;
    }
    return super.visit(s);
  }

  public ASTNode getResult() {
    return result;
  }
}
