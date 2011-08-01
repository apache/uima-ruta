package org.apache.uima.tm.cev.views.selection;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


/**
 * SelektionView fuer den CASViewer
 * 
 * @author Marco Nehmeier
 */
public class CEVSelectionView extends CEVPageBookView {

  /**
   * Default-Konstruktor
   */
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
    // Erzeugt die Page
    return doCreatePage(part, ICEVSelectionPage.class);
  }
}
