package org.apache.uima.tm.textmarker.testing.ui.handlers;

import org.apache.uima.tm.textmarker.testing.preferences.TestingPreferencePage;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.handlers.HandlerUtil;


public class SelectEvaluatorHandler implements IHandler {

  @Override
  public void addHandlerListener(IHandlerListener handlerListener) {

  }

  @Override
  public void dispose() {

  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    Dialog dialog = PreferencesUtil.createPreferenceDialogOn(HandlerUtil.getActiveShell(event),
            TestingPreferencePage.ID, new String[] { TestingPreferencePage.ID }, null);
    dialog.open();
    return null;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean isHandled() {
    return true;
  }

  @Override
  public void removeHandlerListener(IHandlerListener handlerListener) {

  }

}
