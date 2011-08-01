package org.apache.uima.tm.cev.views.palette;

import org.apache.uima.tm.cev.views.ICEVViewPage;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.views.ViewsPlugin;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.MessagePage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;


/**
 * CEVAnnotationMarkerPaletteView des CASViewer
 * 
 * @author Marco Nehmeier
 */
public class CEVAnnotationMarkerPaletteView extends PageBookView {

  /**
   * Text wenn kein View vom Editor zur Verfuegung gestellt wird.
   */
  private static final String VIEW_IS_NOT_AVAILABLE = "View is not available.";

  /**
   * Defaultkonstruktor.
   */
  public CEVAnnotationMarkerPaletteView() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#createDefaultPage(org.eclipse.ui.part .PageBook)
   */
  @Override
  protected IPage createDefaultPage(PageBook book) {
    // MessagePage wenn der View nicht zur Verfuegung steht
    MessagePage page = new MessagePage();
    initPage(page);
    page.createControl(book);
    page.setMessage(VIEW_IS_NOT_AVAILABLE);
    return page;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#doCreatePage(org.eclipse.ui.IWorkbenchPart )
   */
  @Override
  protected PageRec doCreatePage(IWorkbenchPart part) {
    // vom Plugin holen
    Object obj = ViewsPlugin.getAdapter(part, ICEVAnnotationMarkerPalettePage.class, false);

    // Wenn ICASViewPage
    if (obj instanceof ICEVViewPage) {
      ICEVViewPage page = (ICEVViewPage) obj;

      // Control erzeugen
      page.createControl(getPageBook());
      return new PageRec(part, page);
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#doDestroyPage(org.eclipse.ui.IWorkbenchPart ,
   * org.eclipse.ui.part.PageBookView.PageRec)
   */
  @Override
  protected void doDestroyPage(IWorkbenchPart part, PageRec pageRecord) {
    pageRecord.page.dispose();
    pageRecord.dispose();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#getBootstrapPart()
   */
  @Override
  protected IWorkbenchPart getBootstrapPart() {
    IWorkbenchPage page = getSite().getPage();
    if (page != null) {
      return page.getActiveEditor();
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.PageBookView#isImportant(org.eclipse.ui.IWorkbenchPart )
   */
  @Override
  protected boolean isImportant(IWorkbenchPart part) {
    return (part instanceof IEditorPart);
  }

}
