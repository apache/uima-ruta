package org.apache.uima.tm.cev.views.selection;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;

public class CEVSelectionView extends CEVPageBookView {

  public CEVSelectionView() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#doCreatePage(org.eclipse.ui.IWorkbenchPart )
   */
  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, ICEVSelectionPage.class);
  }
}
