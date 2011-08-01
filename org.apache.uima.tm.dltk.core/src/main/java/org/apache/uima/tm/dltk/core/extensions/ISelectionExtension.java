package org.apache.uima.tm.dltk.core.extensions;

import org.apache.uima.tm.dltk.internal.core.codeassist.TextMarkerSelectionEngine;
import org.apache.uima.tm.dltk.internal.core.codeassist.selection.SelectionOnKeywordOrFunction;
import org.eclipse.dltk.ast.ASTNode;


public interface ISelectionExtension {

  void selectionOnKeywordOrFunction(SelectionOnKeywordOrFunction key,
          TextMarkerSelectionEngine tmSelectionEngine);

  void selectionOnAST(ASTNode node, TextMarkerSelectionEngine tmSelectionEngine);

  void selectionOnNode(ASTNode node, int position, TextMarkerSelectionEngine tmSelectionEngine);

}
