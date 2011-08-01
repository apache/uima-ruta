package org.apache.uima.tm.dltk.internal.core.codeassist.selection;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.SimpleReference;

public class SelectionOnKeywordOrFunction extends SimpleReference {
  private ASTNode originalNode;

  public SelectionOnKeywordOrFunction(String completionToken, ASTNode completionNode, ASTNode node) {
    super(completionNode.sourceStart(), completionNode.sourceEnd(), completionToken);
    this.originalNode = node;
  }

  public ASTNode getOriginalNode() {
    return this.originalNode;
  }
}
