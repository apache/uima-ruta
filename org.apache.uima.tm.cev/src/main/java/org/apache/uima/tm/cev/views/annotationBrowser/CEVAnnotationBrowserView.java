package org.apache.uima.tm.cev.views.annotationBrowser;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class CEVAnnotationBrowserView extends CEVPageBookView {
  public CEVAnnotationBrowserView() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#doCreatePage(org.eclipse.ui.IWorkbenchPart)
   */
  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, ICEVAnnotationBrowserPage.class);
  }

}
