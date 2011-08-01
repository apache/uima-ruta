package org.apache.uima.tm.dltk.parser.ast;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;

public class TextMarkerBlockDeclaration extends MethodDeclaration {

  private Expression ruleElement;

  public TextMarkerBlockDeclaration(DLTKToken type, DLTKToken name, Expression re) {
    super(type, name);
    this.ruleElement = re;
  }

  public Expression getRuleElement() {
    return ruleElement;
  }

  public void setRuleElement(Expression ruleElement) {
    this.ruleElement = ruleElement;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }
}
