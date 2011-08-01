package org.apache.uima.tm.dltk.internal.core.codeassist.selection;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.SimpleReference;

public class SelectionOnAST extends SimpleReference {

  private ASTNode method;

  public SelectionOnAST(ASTNode token) {
    super(token.sourceStart(), token.sourceEnd(), "");
    this.method = token;
  }

  public ASTNode getNode() {
    return method;
  }
}
