package org.apache.uima.tm.dltk.internal.core.codeassist.selection;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.SimpleReference;

public class SelectionOnNode extends SimpleReference {
  private ASTNode node;

  private int position;

  public SelectionOnNode(ASTNode token) {
    super(token.sourceStart(), token.sourceEnd(), "");
    this.node = token;
  }

  public ASTNode getNode() {
    return this.node;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public int getPosition() {
    return this.position;
  }
}
