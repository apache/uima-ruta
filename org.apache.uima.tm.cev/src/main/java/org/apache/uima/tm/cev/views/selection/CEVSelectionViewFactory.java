package org.apache.uima.tm.cev.views.selection;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class CEVSelectionViewFactory implements ICEVViewFactory {

  public CEVSelectionViewFactory() {
  }

  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new CEVSelectionPage(viewer, cevDocument, index);
  }

  public Class<?> getAdapterInterface() {
    return ICEVSelectionPage.class;
  }

}
