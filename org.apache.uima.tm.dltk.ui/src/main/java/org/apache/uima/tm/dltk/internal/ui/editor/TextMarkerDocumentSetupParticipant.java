package org.apache.uima.tm.dltk.internal.ui.editor;

import org.apache.uima.tm.dltk.internal.ui.TextMarkerUI;
import org.apache.uima.tm.dltk.internal.ui.text.TextMarkerTextTools;
import org.apache.uima.tm.dltk.ui.text.TextMarkerPartitions;
import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;


public class TextMarkerDocumentSetupParticipant implements IDocumentSetupParticipant {

  public TextMarkerDocumentSetupParticipant() {
  }

  /*
   * @see
   * org.eclipse.core.filebuffers.IDocumentSetupParticipant#setup(org.eclipse.jface.text.IDocument)
   */
  public void setup(IDocument document) {
    TextMarkerTextTools tools = TextMarkerUI.getDefault().getTextTools();
    tools.setupDocumentPartitioner(document, TextMarkerPartitions.TM_PARTITIONING);
  }
}
