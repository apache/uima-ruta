package org.apache.uima.tm.dltk.core;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.references.Reference;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.MatchLocatorParser;
import org.eclipse.dltk.core.search.matching.PatternLocator;

public class TextMarkerMatchLocatorParser extends MatchLocatorParser {

  public TextMarkerMatchLocatorParser(MatchLocator locator) {
    super(locator);
  }

  @Override
  protected void processStatement(ASTNode node, PatternLocator locator) {
    super.processStatement(node, locator);
    if (node instanceof VariableReference) {
      locator.match((Reference) node, getNodeSet());
    } else if (node instanceof FieldDeclaration) {
      locator.match((FieldDeclaration) node, getNodeSet());
    } else if (node instanceof MethodDeclaration) {
      locator.match((MethodDeclaration) node, getNodeSet());
    }
  }
}
