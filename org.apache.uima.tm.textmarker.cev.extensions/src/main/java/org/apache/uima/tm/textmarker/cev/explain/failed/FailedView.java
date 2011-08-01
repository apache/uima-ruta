package org.apache.uima.tm.textmarker.cev.explain.failed;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class FailedView extends CEVPageBookView {

  public FailedView() {
    super();
  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, IFailedViewPage.class);
  }

}
