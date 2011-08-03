package org.apache.uima.tm.cev.editor;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;

public class CEVViewerContributor extends MultiPageEditorActionBarContributor {

  private IEditorPart activeEditorPart;

  public CEVViewerContributor() {
    super();
  }

  protected IAction getAction(ITextEditor editor, String actionID) {
    return (editor == null ? null : editor.getAction(actionID));
  }

  /*
   * (non-JavaDoc) Method declared in AbstractMultiPageEditorActionBarContributor.
   */
  @Override
  public void setActivePage(IEditorPart part) {
    if (activeEditorPart == part)
      return;
    activeEditorPart = part;
  }
}
