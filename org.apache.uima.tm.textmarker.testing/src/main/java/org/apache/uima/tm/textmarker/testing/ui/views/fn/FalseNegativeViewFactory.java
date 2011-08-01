package org.apache.uima.tm.textmarker.testing.ui.views.fn;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class FalseNegativeViewFactory implements ICEVViewFactory {

  public FalseNegativeViewFactory() {

  }

  @Override
  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new FalseNegativeViewPage(viewer, cevDocument, index);
  }

  @Override
  public Class<?> getAdapterInterface() {
    return IFalseNegativeViewPage.class;
  }

}
