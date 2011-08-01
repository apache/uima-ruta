package org.apache.uima.tm.textmarker.cev.explain.rulelist;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class RuleListSelectionView extends CEVPageBookView {

  public RuleListSelectionView() {
    super();
  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, IRuleListViewPage.class);
  }
}
