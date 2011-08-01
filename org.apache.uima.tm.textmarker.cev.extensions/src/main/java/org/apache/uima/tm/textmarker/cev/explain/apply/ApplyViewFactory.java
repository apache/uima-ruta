package org.apache.uima.tm.textmarker.cev.explain.apply;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class ApplyViewFactory implements ICEVViewFactory {

  public ApplyViewFactory() {
  }

  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new ApplyViewPage(viewer, cevDocument, index);
  }

  public Class<?> getAdapterInterface() {
    return IApplyViewPage.class;
  }

}
