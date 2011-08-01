package org.apache.uima.tm.dltk.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.LibraryLabelProvider;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IBaseLabelProvider;

import org.apache.uima.tm.dltk.internal.debug.ui.TextMarkerDebugUIPlugin;

/**
 * Control used to edit the libraries associated with a Interpreter install
 */
public class TextMarkerInterpreterLibraryBlock extends AbstractInterpreterLibraryBlock {

  public TextMarkerInterpreterLibraryBlock(AddScriptInterpreterDialog d) {
    super(d);
  }

  @Override
  protected IBaseLabelProvider getLabelProvider() {
    return new LibraryLabelProvider();
  }

  @Override
  protected IDialogSettings getDialogSettions() {
    return TextMarkerDebugUIPlugin.getDefault().getDialogSettings();
  }
}
