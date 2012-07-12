package org.apache.uima.textmarker.ide.core.codeassist;

import org.apache.uima.textmarker.ide.parser.ast.TextMarkerRule;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class TextMarkerRuleIdVisitor extends ASTVisitor {

  private int id;

  private TextMarkerRule result;

  public TextMarkerRuleIdVisitor(int id) {
    super();
    this.id = id;
  }

  @Override
  public boolean visit(Statement s) throws Exception {
    if (result != null) {
      return false;
    }
    if (s instanceof TextMarkerRule) {
      TextMarkerRule rule = (TextMarkerRule) s;
      if (id == rule.getId()) {
        result = rule;
      }
    }
    return true;
  }


  public TextMarkerRule getResult() {
    return result;
  }

}
