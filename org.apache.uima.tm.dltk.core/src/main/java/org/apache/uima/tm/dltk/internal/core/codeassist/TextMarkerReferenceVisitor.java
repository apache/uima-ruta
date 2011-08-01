package org.apache.uima.tm.dltk.internal.core.codeassist;

import org.apache.uima.tm.dltk.parser.ast.ComponentDeclaration;
import org.apache.uima.tm.dltk.parser.ast.ComponentReference;
import org.apache.uima.tm.dltk.parser.ast.actions.TextMarkerAction;
import org.apache.uima.tm.dltk.parser.ast.conditions.TextMarkerCondition;
import org.apache.uima.tm.dltk.parser.ast.expressions.TextMarkerVariableReference;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;


public class TextMarkerReferenceVisitor extends ASTVisitor {
  private ASTNode result = null;

  private int start = 0;

  public TextMarkerReferenceVisitor(int start) {
    this.start = start;
  }

  @Override
  public boolean visitGeneral(ASTNode node) throws Exception {
    if (result == null && node.sourceStart() <= start && start <= node.sourceEnd()) {
      return true;
    }
    return false;
  }

  @Override
  public boolean visit(Expression s) throws Exception {
    if (result != null) {
      return false;
    }
    if (s instanceof TextMarkerVariableReference || s instanceof ComponentDeclaration
            || s instanceof ComponentReference) {
      result = s;
    } else if (s instanceof TextMarkerAction && ((TextMarkerAction) s).getNameStart() <= start
            && start <= ((TextMarkerAction) s).getNameEnd()) {
      result = s;
    } else if (s instanceof TextMarkerCondition
            && ((TextMarkerCondition) s).getNameStart() <= start
            && start <= ((TextMarkerCondition) s).getNameEnd()) {
      result = s;
    }
    return super.visit(s);
  }

  public ASTNode getResult() {
    return result;
  }
}
