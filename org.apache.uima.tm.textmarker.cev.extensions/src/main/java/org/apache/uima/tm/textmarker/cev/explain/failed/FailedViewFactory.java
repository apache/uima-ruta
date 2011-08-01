package org.apache.uima.tm.textmarker.cev.explain.failed;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class FailedViewFactory implements ICEVViewFactory {

  public FailedViewFactory() {
  }

  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new FailedViewPage(viewer, cevDocument, index);
  }

  public Class<?> getAdapterInterface() {
    return IFailedViewPage.class;
  }

}
