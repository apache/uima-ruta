package org.apache.uima.tm.textmarker.cev.explain.selection;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class ExplainSelectionViewFactory implements ICEVViewFactory {

  public ExplainSelectionViewFactory() {
  }

  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new ExplainSelectionViewPage(viewer, cevDocument, index);
  }

  public Class<?> getAdapterInterface() {
    return IExplainSelectionViewPage.class;
  }

}
