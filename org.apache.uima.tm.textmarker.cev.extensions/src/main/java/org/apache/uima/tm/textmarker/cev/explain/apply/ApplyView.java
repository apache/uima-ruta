package org.apache.uima.tm.textmarker.cev.explain.apply;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class ApplyView extends CEVPageBookView {

  public ApplyView() {
    super();
  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, IApplyViewPage.class);
  }
}
