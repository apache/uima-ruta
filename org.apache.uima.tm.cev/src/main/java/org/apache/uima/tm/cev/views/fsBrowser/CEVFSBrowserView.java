package org.apache.uima.tm.cev.views.fsBrowser;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class CEVFSBrowserView extends CEVPageBookView {

  public CEVFSBrowserView() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#doCreatePage(org.eclipse.ui.IWorkbenchPart)
   */
  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, ICEVFSBrowserPage.class);
  }

}
