package org.apache.uima.tm.textmarker.cev.explain.matched;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class MatchedViewFactory implements ICEVViewFactory {

  public MatchedViewFactory() {
  }

  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new MatchedViewPage(viewer, cevDocument, index);
  }

  public Class<?> getAdapterInterface() {
    return IMatchedViewPage.class;
  }

}
