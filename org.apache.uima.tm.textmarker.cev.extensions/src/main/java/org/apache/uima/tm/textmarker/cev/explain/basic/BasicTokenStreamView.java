package org.apache.uima.tm.textmarker.cev.explain.basic;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


//import org.apache.uima.tm.cas.ui.views.CASAnnotationTreeViewPage;

public class BasicTokenStreamView extends CEVPageBookView {

  public BasicTokenStreamView() {
    super();

  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {

    return doCreatePage(part, IBasicTokenStreamViewPage.class);

  }

}
