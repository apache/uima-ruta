package org.apache.uima.tm.dltk.core.extensions;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.internal.core.search.matching.MatchingNodeSet;

public interface IMatchLocatorExtension {
  // This is match locator feature extension
  public void visitGeneral(ASTNode node, PatternLocator locator, MatchingNodeSet nodeSet);

}
