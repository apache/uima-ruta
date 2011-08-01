package org.apache.uima.tm.textmarker.cev.statistics;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class StatisticsView extends CEVPageBookView {

  public StatisticsView() {
    super();
  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, IStatisticsViewPage.class);
  }

}
