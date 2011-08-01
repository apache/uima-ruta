package org.apache.uima.tm.textmarker.testing.ui.views.tp;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class TruePositiveViewFactory implements ICEVViewFactory {

  public TruePositiveViewFactory() {
  }

  @Override
  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new TruePositiveViewPage(viewer, cevDocument, index);
  }

  @Override
  public Class<?> getAdapterInterface() {
    return ITruePositiveViewPage.class;
  }

}
