package org.apache.uima.tm.dltk.parser.ast;

import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.statements.Statement;

public class TextMarkerBlock extends MethodDeclaration {

  private TextMarkerRuleElement ruleElement;

  private String namespace;

  public TextMarkerBlock(String name, String namespace, int nameStart, int nameEnd, int declStart,
          int declEnd) {
    super(name, nameStart, nameEnd, declStart, declEnd);
    this.namespace = namespace == null || namespace.equals("") ? name : namespace + "." + name;
  }

  public void setElements(List<Statement> body) {
  }

  public String getNamespace() {
    return namespace;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (ruleElement != null) {
        ruleElement.traverse(visitor);
      }
      traverseChildNodes(visitor);
      visitor.endvisit(this);
    }
  }

  /**
   * @param ruleElement
   *          the ruleElement to set
   */
  public void setRuleElement(TextMarkerRuleElement ruleElement) {
    this.ruleElement = ruleElement;
  }

  /**
   * @return the ruleElement
   */
  public TextMarkerRuleElement getRuleElement() {
    return ruleElement;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }
}
