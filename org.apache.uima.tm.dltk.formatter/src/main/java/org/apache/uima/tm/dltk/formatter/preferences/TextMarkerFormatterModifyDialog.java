package org.apache.uima.tm.dltk.formatter.preferences;

import org.eclipse.dltk.ui.formatter.FormatterModifyDialog;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialogOwner;
import org.eclipse.dltk.ui.formatter.IScriptFormatterFactory;

public class TextMarkerFormatterModifyDialog extends FormatterModifyDialog {

  /**
   * @param parent
   */
  public TextMarkerFormatterModifyDialog(IFormatterModifyDialogOwner dialogOwner,
          IScriptFormatterFactory formatterFactory) {
    super(dialogOwner, formatterFactory);
    setTitle(Messages.TextMarkerFormatterModifyDialog_TextMarkerFormatter);
  }

  @Override
  protected void addPages() {
    addTabPage(Messages.TextMarkerFormatterModifyDialog_indentation,
            new TextMarkerFormatterIndentationTabPage(this));
    addTabPage(Messages.TextMarkerFormatterModifyDialog_blankLines,
            new TextMarkerFormatterBlankLinesPage(this));
    addTabPage(Messages.TextMarkerFormatterModifyDialog_lineWrapping,
            new TextMarkerFormatterLineWrappingPage(this));
    // addTabPage(Messages.TextMarkerFormatterModifyDialog_comments,
    // new TextMarkerFormatterCommentsPage(this));
  }

}
