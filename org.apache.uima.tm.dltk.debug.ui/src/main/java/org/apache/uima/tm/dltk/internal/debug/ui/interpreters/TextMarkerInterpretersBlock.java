package org.apache.uima.tm.dltk.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;

import org.apache.uima.tm.dltk.core.TextMarkerNature;

public class TextMarkerInterpretersBlock extends InterpretersBlock {
  @Override
  protected AddScriptInterpreterDialog createInterpreterDialog(IInterpreterInstall standin) {
    AddTextMarkerInterpreterDialog dialog = new AddTextMarkerInterpreterDialog(this, getShell(),
            ScriptRuntime.getInterpreterInstallTypes(getCurrentNature()), standin);
    return dialog;
  }

  @Override
  protected String getCurrentNature() {
    return TextMarkerNature.NATURE_ID;
  }
}
