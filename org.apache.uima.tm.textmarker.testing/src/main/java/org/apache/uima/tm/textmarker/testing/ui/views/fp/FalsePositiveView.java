package org.apache.uima.tm.textmarker.testing.ui.views.fp;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class FalsePositiveView extends CEVPageBookView {

  public FalsePositiveView() {
    super();
  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, IFalsePositiveViewPage.class);
  }
}
