package org.apache.uima.tm.cev.views.fsBrowser;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.cev.extension.ICEVViewFactory;

public class CEVFSBrowserViewFactory implements ICEVViewFactory {

  public CEVFSBrowserViewFactory() {
  }

  public ICEVView createView(CEVViewer viewer, CEVDocument cevDocument, int index) {
    return new CEVFSBrowserPage(viewer, cevDocument, index);
  }

  public Class<?> getAdapterInterface() {
    return ICEVFSBrowserPage.class;
  }

}
