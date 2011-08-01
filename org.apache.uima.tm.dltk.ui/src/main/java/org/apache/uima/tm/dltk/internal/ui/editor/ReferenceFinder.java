package org.apache.uima.tm.dltk.internal.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.dltk.parser.ast.ComponentDeclaration;
import org.apache.uima.tm.dltk.parser.ast.ComponentReference;
import org.apache.uima.tm.dltk.parser.ast.actions.TextMarkerAction;
import org.apache.uima.tm.dltk.parser.ast.conditions.TextMarkerCondition;
import org.apache.uima.tm.dltk.parser.ast.expressions.TextMarkerVariableReference;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;


public class ReferenceFinder extends ASTVisitor {

  private List<ASTNode> result = new ArrayList<ASTNode>();

  private ASTNode node;

  public ReferenceFinder(ASTNode node) {
    super();
    this.node = node;
  }

  @Override
  public boolean visit(Expression s) throws Exception {
    if (s instanceof TextMarkerVariableReference && node instanceof TextMarkerVariableReference) {
      TextMarkerVariableReference vr0 = (TextMarkerVariableReference) node;
      TextMarkerVariableReference vr1 = (TextMarkerVariableReference) s;
      if (vr1.getStringRepresentation().equals(vr0.getStringRepresentation())
              && vr1.getType() == vr0.getType()) {
        result.add(s);
      }
    } else if (s instanceof ComponentDeclaration && node instanceof ComponentDeclaration) {

    } else if (s instanceof ComponentReference && node instanceof ComponentReference) {
      ComponentReference cr1 = (ComponentReference) node;
      ComponentReference cr2 = (ComponentReference) s;
      if (cr1.getName().equals(cr2.getName())) {
        result.add(s);
      }
    } else if (s instanceof TextMarkerAction && node instanceof TextMarkerAction) {
      TextMarkerAction a1 = (TextMarkerAction) node;
      TextMarkerAction a2 = (TextMarkerAction) s;
      if (a1.getName().equals(a2.getName())) {
        result.add(s);
      }
    } else if (s instanceof TextMarkerCondition && node instanceof TextMarkerCondition) {
      TextMarkerCondition c1 = (TextMarkerCondition) node;
      TextMarkerCondition c2 = (TextMarkerCondition) s;
      if (c1.getName().equals(c2.getName())) {
        result.add(s);
      }

    }
    return super.visit(s);
  }

  public List<ASTNode> getResult() {
    return result;
  }
}
