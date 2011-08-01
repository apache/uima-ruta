package org.apache.uima.tm.dltk.internal.ui.actions;

import org.eclipse.dltk.internal.ui.editor.DLTKEditorMessages;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.ui.texteditor.AbstractRulerActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;

public class TextMarkerSelectRulerAction extends AbstractRulerActionDelegate {

  @Override
  protected IAction createAction(ITextEditor editor, IVerticalRulerInfo rulerInfo) {
    return new TextMarkerSelectAnnotationRulerAction(DLTKEditorMessages
            .getBundleForConstructedKeys(), "SelectAnnotationRulerAction.", editor, rulerInfo); //$NON-NLS-1$
  }
}
