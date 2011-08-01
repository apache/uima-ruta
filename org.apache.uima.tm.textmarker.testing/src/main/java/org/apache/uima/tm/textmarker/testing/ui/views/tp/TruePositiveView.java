package org.apache.uima.tm.textmarker.testing.ui.views.tp;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class TruePositiveView extends CEVPageBookView {

  public TruePositiveView() {
    super();
  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, ITruePositiveViewPage.class);
  }
}
