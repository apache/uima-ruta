package org.apache.uima.tm.textmarker.cev.explain.basic;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class BasicTokenStreamViewFactory implements ICEVViewFactory {

  public BasicTokenStreamViewFactory() {
  }

  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new BasicTokenStreamViewPage(viewer, cevDocument, index);
  }

  public Class<?> getAdapterInterface() {
    return IBasicTokenStreamViewPage.class;
  }

}
