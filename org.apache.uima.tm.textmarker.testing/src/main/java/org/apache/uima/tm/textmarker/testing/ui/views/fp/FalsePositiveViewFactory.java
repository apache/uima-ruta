package org.apache.uima.tm.textmarker.testing.ui.views.fp;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class FalsePositiveViewFactory implements ICEVViewFactory {

  public FalsePositiveViewFactory() {
  }

  @Override
  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new FalsePositiveViewPage(viewer, cevDocument, index);
  }

  @Override
  public Class<?> getAdapterInterface() {
    return IFalsePositiveViewPage.class;
  }

}
