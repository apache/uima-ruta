package org.apache.uima.tm.textmarker.cev.explain.matched;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class MatchedView extends CEVPageBookView {

  public MatchedView() {
    super();
  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, IMatchedViewPage.class);
  }

}
