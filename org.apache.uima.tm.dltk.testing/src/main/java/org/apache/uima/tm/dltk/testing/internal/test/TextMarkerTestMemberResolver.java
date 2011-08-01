package org.apache.uima.tm.dltk.testing.internal.test;

import org.apache.uima.tm.dltk.parser.ast.TextMarkerStatement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.testing.AbstractTestingElementResolver;
import org.eclipse.dltk.testing.ITestingElementResolver;


public class TextMarkerTestMemberResolver extends AbstractTestingElementResolver implements
        ITestingElementResolver {
  @Override
  protected ASTNode findNode(final String testName, ModuleDeclaration decl, String method) {
    final ASTNode[] nde = new ASTNode[] { null };
    try {
      decl.traverse(new ASTVisitor() {

        @Override
        public boolean visitGeneral(ASTNode node) throws Exception {
          if (node instanceof TextMarkerStatement && ((TextMarkerStatement) node).getCount() > 2) {
            TextMarkerStatement st = (TextMarkerStatement) node;
            Expression cmd = st.getAt(0);
            if (cmd instanceof SimpleReference) {
              String cmdName = ((SimpleReference) cmd).getName();
              if (cmdName.startsWith("::")) {
                cmdName = cmdName.substring(2);
              }
              if ("test".equals(cmdName) || "tmtest::test".equals(cmdName)) {

                // List findLevelsTo = findLevelsTo(decl, node);
                Expression name = st.getAt(1);
                if (name instanceof SimpleReference) {
                  String nameValue = ((SimpleReference) name).getName();
                  if (testName.equals(nameValue)) {
                    nde[0] = st;
                  }
                }
              }
            }
          }
          return true;
        }
      });
    } catch (CoreException e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }
    return nde[0];
  }
}
