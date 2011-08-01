package org.apache.uima.tm.cev.editor;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * MultiPageEditorActionBarContributor fuer das CEV-Plugin
 * 
 * @author Marco Nehmeier
 */
public class CEVViewerContributor extends MultiPageEditorActionBarContributor {

  private IEditorPart activeEditorPart;

  /**
   * Konstruktor
   */
  public CEVViewerContributor() {
    super();
  }

  /**
   * Gibt die reg. Action zurueck
   * 
   * @return IAction oder null.
   */
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
