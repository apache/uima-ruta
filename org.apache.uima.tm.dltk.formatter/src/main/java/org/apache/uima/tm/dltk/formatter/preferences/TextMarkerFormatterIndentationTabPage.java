package org.apache.uima.tm.dltk.formatter.preferences;

import java.net.URL;

import org.apache.uima.tm.dltk.formatter.TextMarkerFormatterConstants;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.formatter.FormatterModifyTabPage;
import org.eclipse.dltk.ui.formatter.IFormatterControlManager;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.dltk.ui.preferences.FormatterMessages;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;


public class TextMarkerFormatterIndentationTabPage extends FormatterModifyTabPage {

  /**
   * @param dialog
   */
  public TextMarkerFormatterIndentationTabPage(IFormatterModifyDialog dialog) {
    super(dialog);
  }

  private Combo tabPolicy;

  private Text indentSize;

  private Text tabSize;

  private final String[] tabPolicyItems = new String[] { CodeFormatterConstants.SPACE,
      CodeFormatterConstants.TAB, CodeFormatterConstants.MIXED };

  private class TabPolicyListener extends SelectionAdapter implements
          IFormatterControlManager.IInitializeListener {

    private final IFormatterControlManager manager;

    public TabPolicyListener(IFormatterControlManager manager) {
      this.manager = manager;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
      int index = tabPolicy.getSelectionIndex();
      if (index >= 0) {
        final boolean tabMode = CodeFormatterConstants.TAB.equals(tabPolicyItems[index]);
        manager.enableControl(indentSize, !tabMode);
      }
    }

    public void initialize() {
      final boolean tabMode = CodeFormatterConstants.TAB.equals(manager
              .getString(TextMarkerFormatterConstants.FORMATTER_TAB_CHAR));
      manager.enableControl(indentSize, !tabMode);
    }

  }

  private TabPolicyListener tabPolicyListener;

  @Override
  protected void createOptions(final IFormatterControlManager manager, Composite parent) {
    Group tabPolicyGroup = SWTFactory.createGroup(parent,
            Messages.TextMarkerFormatterIndentationTabPage_generalSettings, 2, 1,
            GridData.FILL_HORIZONTAL);
    tabPolicy = manager.createCombo(tabPolicyGroup,
            TextMarkerFormatterConstants.FORMATTER_TAB_CHAR,
            FormatterMessages.IndentationTabPage_general_group_option_tab_policy, tabPolicyItems);
    tabPolicyListener = new TabPolicyListener(manager);
    tabPolicy.addSelectionListener(tabPolicyListener);
    manager.addInitializeListener(tabPolicyListener);
    indentSize = manager.createNumber(tabPolicyGroup,
            TextMarkerFormatterConstants.FORMATTER_INDENTATION_SIZE,
            FormatterMessages.IndentationTabPage_general_group_option_indent_size);
    tabSize = manager.createNumber(tabPolicyGroup, TextMarkerFormatterConstants.FORMATTER_TAB_SIZE,
            FormatterMessages.IndentationTabPage_general_group_option_tab_size);
    tabSize.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        int index = tabPolicy.getSelectionIndex();
        if (index >= 0) {
          final boolean tabMode = CodeFormatterConstants.TAB.equals(tabPolicyItems[index]);
          if (tabMode) {
            indentSize.setText(tabSize.getText());
          }
        }
      }
    });
    //
    Group indentGroup = SWTFactory.createGroup(parent,
            Messages.TextMarkerFormatterIndentationTabPage_indentWithinBlocks, 1, 1,
            GridData.FILL_HORIZONTAL);
    manager.createCheckbox(indentGroup, TextMarkerFormatterConstants.INDENT_BLOCK,
            Messages.TextMarkerFormatterIndentationTabPage_statementsWithinBlockBody);
    Group indentBlocks = SWTFactory.createGroup(parent,
            Messages.TextMarkerFormatterIndentationTabPage_indentWithinCreateActions, 1, 1,
            GridData.FILL_HORIZONTAL);
    manager.createCheckbox(indentBlocks, TextMarkerFormatterConstants.INDENT_STRUCTURE,
            Messages.TextMarkerFormatterIndentationTabPage_assignmentsWithinCreateAction);
  }

  @Override
  protected URL getPreviewContent() {
    return getClass().getResource("indentation-preview.tm");
  }
}
