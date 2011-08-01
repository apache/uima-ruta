package org.apache.uima.tm.dltk.internal.core.codeassist.completion;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.SimpleReference;

public class CompletionOnVariable extends SimpleReference {
  private ASTNode parentNode;

  private ASTNode inNode;

  private ASTNode completionNode;

  private boolean canHandleEmpty;

  private boolean provideDollar = true;

  public CompletionOnVariable(String completionToken, ASTNode completionNode, ASTNode node,
          ASTNode inNode, boolean canHandleEmpty) {
    super(completionNode.sourceStart(), completionNode.sourceEnd(), completionToken);
    this.parentNode = node;
    this.completionNode = completionNode;
    this.inNode = inNode;
    this.canHandleEmpty = canHandleEmpty;
  }

  public CompletionOnVariable(String completionToken, ASTNode completionNode, ASTNode node,
          ASTNode inNode, boolean canHandleEmpty, boolean provideDollar) {
    this(completionToken, completionNode, node, inNode, canHandleEmpty);
    this.provideDollar = provideDollar;
  }

  public ASTNode getParentNode() {
    return this.parentNode;
  }

  public ASTNode getInNode() {
    return this.inNode;
  }

  public ASTNode getCompletionNode() {
    return this.completionNode;
  }

  public char[] getToken() {
    return this.getName().toCharArray();
  }

  public boolean canHandleEmpty() {
    return this.canHandleEmpty;
  }

  public boolean getProvideDollar() {
    return this.provideDollar;
  }
}
