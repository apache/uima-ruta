package org.apache.uima.tm.dltk.internal.core.codeassist.completion;

import org.apache.uima.tm.dltk.parser.ast.TextMarkerStatement;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.codeassist.complete.ICompletionOnKeyword;
import org.eclipse.dltk.utils.CorePrinter;


public class CompletionOnKeywordArgumentOrFunctionArgument extends SimpleReference implements
        ICompletionOnKeyword {

  private String[] possibleKeywords;

  private TextMarkerStatement statement;

  private ASTNode completionNode;

  public CompletionOnKeywordArgumentOrFunctionArgument(String token, ASTNode completionNode,
          TextMarkerStatement node, String[] KeywordspossibleKeywords) {
    super(completionNode.sourceStart(), completionNode.sourceEnd(), token);
    this.possibleKeywords = KeywordspossibleKeywords;
    this.statement = node;
    this.completionNode = completionNode;
  }

  public CompletionOnKeywordArgumentOrFunctionArgument(String token, TextMarkerStatement node,
          String[] possibleKeywords, int position) {
    super(position, position, token);
    this.possibleKeywords = possibleKeywords;
    this.statement = node;
    this.completionNode = null;
  }

  public char[] getToken() {
    if (getName() != null) {
      return getName().toCharArray();
    }
    return "".toCharArray();
  }

  public String[] getPossibleKeywords() {
    return this.possibleKeywords;
  }

  @Override
  public void printNode(CorePrinter output) {
  }

  @Override
  public void traverse(ASTVisitor pVisitor) throws Exception {
  }

  public boolean canCompleteEmptyToken() {
    return true;
  }

  public TextMarkerStatement getStatement() {
    return this.statement;
  }

  public int argumentIndex() {
    if (this.completionNode == null) {
      if (this.statement.getCount() == 1) {
        return 1;
      }
      if (statement.getCount() > 2 && statement.getAt(0).sourceEnd() <= sourceStart()
              && sourceEnd() <= statement.getAt(1).sourceStart()) {
        return 1;
      }
      return -1;
    }
    for (int i = 0; i < this.statement.getCount(); ++i) {
      if (this.statement.getAt(i).equals(this.completionNode)) {
        return i;
      }
    }
    return -1;
  }

  public ASTNode getCompletionNode() {
    return this.completionNode;
  }
}
