package org.apache.uima.tm.textmarker.testing.ui.views.fn;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class FalseNegativeView extends CEVPageBookView {

  public FalseNegativeView() {
    super();
  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, IFalseNegativeViewPage.class);
  }

}
