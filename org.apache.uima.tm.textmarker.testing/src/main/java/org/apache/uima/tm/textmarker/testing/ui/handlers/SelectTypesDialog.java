package org.apache.uima.tm.textmarker.testing.ui.handlers;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.testing.ui.views.TestViewPage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;


public class SelectTypesDialog extends Dialog implements Listener {

  private Shell shell;

  private Text typeNameUI;

  private Table matchingTypesUI;

  // private Table nameSpacesUI;

  public String typeName = "error-TypeName-never-set";

  private Button okButton;

  private Button cancelButton;

  private TestViewPage owner;

  private List<String> types;

  private List<String> excluded;

  public SelectTypesDialog(Shell shell, List<String> types, TestViewPage owner) {
    super(shell);
    this.shell = shell;
    this.types = types;
    this.owner = owner;
    this.excluded = owner.getExcludedTypes();
    createDialogArea();
    init();
  }

  protected Control createDialogArea() {
    Composite mainArea = (Composite) createDialogArea(shell);
    createWideLabel(mainArea, "  Type Name:");

    typeNameUI = newText(mainArea, SWT.SINGLE, "Specify the type name");
    typeNameUI.addListener(SWT.Modify, this);

    createWideLabel(mainArea, "  Matching Types:");

    matchingTypesUI = newTable(mainArea, SWT.CHECK);
    ((GridData) matchingTypesUI.getLayoutData()).heightHint = 250;
    ((GridData) matchingTypesUI.getLayoutData()).minimumHeight = 100;
    typeNameUI.addListener(SWT.CHECK, this);

    displayFilteredTypes("");
    createButtonBar(mainArea);
    return mainArea;
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
    layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
    layout.verticalSpacing = 3;
    layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
    composite.setLayout(layout);
    composite.setLayoutData(new GridData(GridData.FILL_BOTH));
    applyDialogFont(composite);
    return composite;
  }

  @Override
  protected Control createButtonBar(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 0;
    layout.makeColumnsEqualWidth = true;
    layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
    layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
    layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
    layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
    composite.setLayout(layout);
    GridData data = new GridData(SWT.CENTER, SWT.CENTER, false, false);
    composite.setLayoutData(data);

    createButtonsForButtonBar(composite);
    return composite;
  }

  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    okButton = createButton(parent, IDialogConstants.OK_ID, "OK", true);
    cancelButton = createButton(parent, IDialogConstants.CANCEL_ID, "    Cancel    ", false);
  }

  private void init() {

    TableItem[] items = matchingTypesUI.getItems();
    for (TableItem tableItem : items) {
      if (excluded.contains(tableItem.getText())) {
        tableItem.setChecked(true);
      }
    }

    okButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        TableItem[] items = matchingTypesUI.getItems();
        List<String> selection = new ArrayList<String>();
        for (TableItem tableItem : items) {
          if (tableItem.getChecked()) {
            selection.add(tableItem.getText());
          }
        }
        owner.setExcludedTypes(selection);
        shell.dispose();
      }
    });

    cancelButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        shell.dispose();
      }
    });

    Display display = Display.getDefault();
    int width = 300;
    int height = 400;
    shell.setSize(width, height);
    shell.setMinimumSize(200, 250);
    shell.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2, (Toolkit
            .getDefaultToolkit().getScreenSize().height - height) / 2);
    shell.setLayout(new FillLayout());
    shell.layout();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }

  }

  protected Label createWideLabel(Composite parent, String message) {
    Label label = null;
    label = new Label(parent, SWT.WRAP);
    label.setText(null != message ? message : "");
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
    data.verticalAlignment = SWT.CENTER;
    label.setLayoutData(data);
    return label;
  }

  protected Text newText(Composite parent, int style, String tip) {
    Text t = new Text(parent, style | SWT.BORDER);
    t.setToolTipText(tip);
    t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    t.addListener(SWT.KeyUp, this);
    t.addListener(SWT.MouseUp, this);
    return t;
  }

  protected Table newTable(Composite parent, int style) {
    Table table = new Table(parent, style | SWT.BORDER);
    GridData gd = new GridData(GridData.FILL_BOTH);
    table.setLayoutData(gd);
    table.addListener(SWT.Selection, this);
    table.addListener(SWT.KeyUp, this);
    return table;
  }

  private void displayFilteredTypes(String aTypeName) {
    matchingTypesUI.setRedraw(false);
    matchingTypesUI.removeAll();
    String topEntry = "";
    aTypeName = aTypeName.toLowerCase();
    for (String type : types) {
      String candidateTypeName = type.toLowerCase();
      if (aTypeName.trim().equals("") || candidateTypeName.indexOf(aTypeName) != -1) {

        if (topEntry.equals("")) {
          topEntry = type;
        }
        TableItem item = new TableItem(matchingTypesUI, SWT.NULL);
        item.setText(type);
        if (excluded.contains(item.getText())) {
          item.setChecked(true);
        }

      }
    }
    matchingTypesUI.setRedraw(true);
  }

  public void handleEvent(Event event) {
    if (event.widget == typeNameUI && event.type == SWT.Modify) {
      displayFilteredTypes(typeNameUI.getText());
    } else if (event.widget == matchingTypesUI) {
      Widget item = event.item;
      if (item instanceof TableItem) {
        TableItem ti = (TableItem) item;
        if (ti.getChecked()) {
          excluded.add(ti.getText());
        } else {
          excluded.remove(ti.getText());
        }
      }
    }
  }

  public boolean isValid() {
    return true;
  }
}
