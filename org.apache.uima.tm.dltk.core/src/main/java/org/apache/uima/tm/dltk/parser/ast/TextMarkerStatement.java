package org.apache.uima.tm.dltk.parser.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ast.statements.StatementConstants;
import org.eclipse.dltk.utils.CorePrinter;

public class TextMarkerStatement extends Statement {
  private List<Expression> expressions;

  /**
   * Statement with bounds from first to last expression.
   * 
   * @param expressions
   */
  public TextMarkerStatement(List<Expression> expressions) {
    if (!expressions.isEmpty()) {
      // First
      Expression first = expressions.get(0);
      if (first != null) {
        this.setStart(first.sourceStart());
      }
      // Last
      Expression last = expressions.get(expressions.size() - 1);
      if (last != null) {
        this.setEnd(last.sourceEnd());
      }
    }
    this.expressions = expressions;
  }

  /**
   * Statement with specified bounds and expression list.
   * 
   * @param start
   * @param end
   * @param expressions
   */
  public TextMarkerStatement(int start, int end, List<Expression> expressions) {
    super(start, end);
    if (expressions == null) {
      this.expressions = new ArrayList<Expression>();
    } else {
      this.expressions = expressions;
    }
  }

  public List<Expression> getExpressions() {
    return this.expressions;
  }

  public Expression getAt(int index) {
    if (index >= 0 && index < this.expressions.size()) {
      return this.expressions.get(index);
    }

    return null;
  }

  public int getCount() {
    return this.expressions.size();
  }

  @Override
  public int getKind() {
    return StatementConstants.S_BLOCK;
    // return TextMarkerConstants.TM_STATEMENT;
  }

  @Override
  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      if (this.expressions != null) {
        for (int i = 0; i < this.expressions.size(); i++) {
          ASTNode node = this.expressions.get(i);
          if (node != null) {
            node.traverse(visitor);
          }
        }
      }
      visitor.endvisit(this);
    }
  }

  @Override
  public void printNode(CorePrinter output) {
    if (this.expressions != null) {
      output.formatPrintLn("");
      Iterator i = this.expressions.iterator();
      while (i.hasNext()) {
        ASTNode node = (ASTNode) i.next();
        node.printNode(output);
        output.formatPrintLn(" ");
      }
    }
  }

  @Override
  public String toString() {
    String value = "";
    if (this.expressions != null) {
      Iterator i = this.expressions.iterator();
      while (i.hasNext()) {
        ASTNode node = (ASTNode) i.next();
        value += node.toString();
        value += " ";
      }
    }

    return value;
  }

  public void setExpressions(List<Expression> asList) {
    this.expressions = asList;
  }

}
