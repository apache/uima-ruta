package org.apache.uima.tm.cev.views.annotationBrowser;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class CEVAnnotationBrowserViewFactory implements ICEVViewFactory {

  public CEVAnnotationBrowserViewFactory() {
  }

  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    CEVAnnotationBrowserPage page = new CEVAnnotationBrowserPage(viewer, cevDocument, index);
    return page;
  }

  public Class<?> getAdapterInterface() {
    return ICEVAnnotationBrowserPage.class;
  }

}
