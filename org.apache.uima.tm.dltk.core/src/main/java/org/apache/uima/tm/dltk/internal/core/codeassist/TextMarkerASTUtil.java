package org.apache.uima.tm.dltk.internal.core.codeassist;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.dltk.internal.core.parser.TextMarkerParseUtils;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.core.DLTKCore;


public class TextMarkerASTUtil {
  public static List getStatements(ASTNode node) {
    if (node instanceof ModuleDeclaration) {
      return ((ModuleDeclaration) node).getStatements();
    } else if (node instanceof TypeDeclaration) {
      return ((TypeDeclaration) node).getStatements();
    } else if (node instanceof MethodDeclaration) {
      return ((MethodDeclaration) node).getStatements();
    } else if (node instanceof Block) {
      return ((Block) node).getStatements();
    } else {
      final List innerBlockStatements = new ArrayList();
      // Lets traverse to see inner blocks.
      ASTVisitor visitor = new ASTVisitor() {
        @Override
        public boolean visit(Expression s) throws Exception {
          if (s instanceof Block) {
            List tStatements = ((Block) s).getStatements();
            innerBlockStatements.addAll(tStatements);
          }
          return false;
        }

        @Override
        public boolean visit(MethodDeclaration s) throws Exception {
          return false;
        }

        @Override
        public boolean visit(TypeDeclaration s) throws Exception {
          return false;
        }
      };
      try {
        node.traverse(visitor);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return innerBlockStatements;
    }
    // return null;
  }

  /**
   * We need to extend all statements to end of lines or to begining of next statement. This is
   * needed to for correct completion in statements. Such as variable completion and so on.
   */
  public static void extendStatements(ASTNode node, String content) {
    List statements = getStatements(node);

    if (statements != null) {
      int len = statements.size();
      for (int i = 0; i < len; ++i) {
        ASTNode nde = (ASTNode) statements.get(i);

        extendStatement(nde, content);
        extendStatements(nde, content);
      }
    }
  }

  public static void extendStatement(ASTNode node, String content) {
    int newValueStart = startLineOrSymbol(node, content);
    int newValueEnd = endLineOrSymbol(node, content);
    if (DLTKCore.DEBUG_COMPLETION) {
      if (node.sourceEnd() != newValueEnd || node.sourceStart() != newValueStart) {
        // TODO
      }
    }
    node.setStart(newValueStart);
    node.setEnd(newValueEnd);
  }

  public static int endLineOrSymbol(ASTNode node, String content) {
    return TextMarkerParseUtils.endLineOrSymbol(node.sourceEnd(), content);
  }

  public static int startLineOrSymbol(ASTNode node, String content) {
    return TextMarkerParseUtils.startLineOrSymbol(node.sourceStart(), content);
  }
}
