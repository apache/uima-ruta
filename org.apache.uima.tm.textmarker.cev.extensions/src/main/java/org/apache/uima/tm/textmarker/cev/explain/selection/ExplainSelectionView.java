package org.apache.uima.tm.textmarker.cev.explain.selection;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class ExplainSelectionView extends CEVPageBookView {

  public ExplainSelectionView() {
    super();
  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, IExplainSelectionViewPage.class);
  }
}
