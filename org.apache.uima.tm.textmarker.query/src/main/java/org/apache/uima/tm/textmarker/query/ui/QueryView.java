package org.apache.uima.tm.textmarker.query.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class QueryView extends ViewPart {

  public static final String ID = "org.apache.uima.tm.textmarker.query.ui.QueryView";

  private QueryComposite viewContent;

  private IMemento memento;

  public QueryView() {
    super();
  }

  @Override
  public void createPartControl(Composite parent) {
    viewContent = new QueryComposite(parent, SWT.NULL);
    if (memento != null) {
      viewContent.restoreState(memento);
      memento = null;
    }
  }

  public void setViewTitle(String title) {
    setPartName(title);
  }

  @Override
  public void setFocus() {
    viewContent.setFocus();
  }

  @Override
  public void saveState(IMemento memento) {
    viewContent.saveState(memento);
  }

  @Override
  public void init(IViewSite site, IMemento memento) throws PartInitException {
    this.memento = memento;
    super.init(site, memento);
  }

  public QueryComposite getComposite() {
    return viewContent;
  }
}
