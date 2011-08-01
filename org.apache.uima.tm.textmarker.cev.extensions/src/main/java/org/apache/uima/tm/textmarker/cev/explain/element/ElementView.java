package org.apache.uima.tm.textmarker.cev.explain.element;

import org.apache.uima.tm.cev.views.CEVPageBookView;
import org.eclipse.ui.IWorkbenchPart;


public class ElementView extends CEVPageBookView {

  public ElementView() {
    super();
  }

  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    return doCreatePage(part, IElementViewPage.class);
  }

}
