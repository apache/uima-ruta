package org.apache.uima.tm.dltk.formatter.preferences;

import java.net.URL;

import org.apache.uima.tm.dltk.formatter.TextMarkerFormatterConstants;
import org.eclipse.dltk.ui.formatter.FormatterModifyTabPage;
import org.eclipse.dltk.ui.formatter.IFormatterControlManager;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;


public class TextMarkerFormatterBlankLinesPage extends FormatterModifyTabPage {

  /**
   * @param dialog
   */
  public TextMarkerFormatterBlankLinesPage(IFormatterModifyDialog dialog) {
    super(dialog);
  }

  @Override
  protected void createOptions(IFormatterControlManager manager, Composite parent) {
    Group emptyLinesGroup = SWTFactory.createGroup(parent,
            Messages.TextMarkerFormatterBlankLinesPage_blankLinesBeforeDeclarations, 2, 1,
            GridData.FILL_HORIZONTAL);
    manager.createNumber(emptyLinesGroup,
            TextMarkerFormatterConstants.LINES_BEFORE_LONG_DECLARATIONS,
            Messages.TextMarkerFormatterBlankLinesPage_beforeDeclarations);
    // manager.createNumber(emptyLinesGroup,
    // TextMarkerFormatterConstants.LINES_FILE_BETWEEN_BLOCK,
    // Messages.TextMarkerFormatterBlankLinesPage_betweenBlocks);
    // manager.createNumber(emptyLinesGroup,
    // TextMarkerFormatterConstants.LINES_FILE_BETWEEN_CLASS,
    // Messages.TextMarkerFormatterBlankLinesPage_betweenClasses);
    // manager.createNumber(emptyLinesGroup,
    // TextMarkerFormatterConstants.LINES_FILE_BETWEEN_METHOD,
    // Messages.TextMarkerFormatterBlankLinesPage_betweenMethods);
    // //
    // Group emptyLinesInternalGroup = SWTFactory.createGroup(parent,
    // Messages.TextMarkerFormatterBlankLinesPage_blankLinesWithingClassModuleDeclarations, 2, 1,
    // GridData.FILL_HORIZONTAL);
    // manager.createNumber(emptyLinesInternalGroup,
    // TextMarkerFormatterConstants.LINES_BEFORE_FIRST,
    // Messages.TextMarkerFormatterBlankLinesPage_befireFirstDeclaration);
    // manager.createNumber(emptyLinesInternalGroup,
    // TextMarkerFormatterConstants.LINES_BEFORE_MODULE,
    // Messages.TextMarkerFormatterBlankLinesPage_beforeNestedModuleDeclarations);
    // manager.createNumber(emptyLinesInternalGroup,
    // TextMarkerFormatterConstants.LINES_BEFORE_CLASS,
    // Messages.TextMarkerFormatterBlankLinesPage_beforeNestedClassDeclarations);
    // manager.createNumber(emptyLinesInternalGroup,
    // TextMarkerFormatterConstants.LINES_BEFORE_METHOD,
    // Messages.TextMarkerFormatterBlankLinesPage_beforeMethodDeclarations);
    // //
    // Group preserveGroup = SWTFactory.createGroup(parent,
    // Messages.TextMarkerFormatterBlankLinesPage_existingBlankLines, 2, 1,
    // GridData.FILL_HORIZONTAL);
    // manager.createNumber(preserveGroup,
    // TextMarkerFormatterConstants.LINES_PRESERVE,
    // Messages.TextMarkerFormatterBlankLinesPage_numberOfEmptyLinesToPreserve);
  }

  @Override
  protected URL getPreviewContent() {
    return getClass().getResource("blank-lines-preview.tm"); //$NON-NLS-1$
  }

}
