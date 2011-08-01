package org.apache.uima.tm.dltk.ui.semantichighlighting;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ui.editor.highlighting.ISemanticHighlightingRequestor;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlighting;

public interface ISemanticHighlightingExtension {

  SemanticHighlighting[] getHighlightings();

  void processNode(ASTNode node, ISemanticHighlightingRequestor requestor);

}
