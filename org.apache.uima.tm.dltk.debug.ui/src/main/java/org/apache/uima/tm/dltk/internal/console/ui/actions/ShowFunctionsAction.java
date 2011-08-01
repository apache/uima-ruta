package org.apache.uima.tm.dltk.internal.console.ui.actions;

import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.core.model.IScriptVariable;
import org.eclipse.dltk.debug.ui.actions.ViewFilterAction;
import org.eclipse.jface.viewers.Viewer;

/**
 * Shows non-final static variables
 */
public class ShowFunctionsAction extends ViewFilterAction {

  public ShowFunctionsAction() {
    super();
  }

  @Override
  protected String getPreferenceKey() {
    return "show_functions";
  }

  @Override
  public boolean select(Viewer viewer, Object parentElement, Object element) {
    if (element instanceof IScriptVariable) {
      IScriptVariable variable = (IScriptVariable) element;
      try {
        return !((IScriptValue) variable.getValue()).getType().getName().equals("function");
      } catch (DebugException e) {
        if (DLTKCore.DEBUG) {
          e.printStackTrace();
        }
      }
    }
    return true;
  }
}
