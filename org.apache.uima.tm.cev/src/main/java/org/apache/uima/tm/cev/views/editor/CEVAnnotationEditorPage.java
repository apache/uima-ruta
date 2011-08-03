package org.apache.uima.tm.cev.views.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.CEVPlugin;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVEditor;
import org.apache.uima.tm.cev.preferences.CEVPreferenceConstants;
import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;
import org.htmlparser.util.ParserUtils;
import org.htmlparser.util.Translate;

public class CEVAnnotationEditorPage extends Page implements ICEVAnnotationEditorPage,
        MouseListener, SelectionListener, IPropertyChangeListener, ICEVEditor, Listener {

  private enum Drag {
    start, end, none
  }

  private class Selection {
    private int start;

    private int end;

    public Selection(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public int getStart() {
      return start;
    }

    public void setStart(int start) {
      this.start = start;
    }

    public int getEnd() {
      return end;
    }

    public void setEnd(int end) {
      this.end = end;
    }
  }

  private Composite pane;

  private List<Selection> selections;

  private StyledText textView;

  private Table table;

  private CEVDocument casDocument;

  private Button createButton;

  private Drag dragState;

  private boolean text_repr;

  private int current;

  private Text typeFilterText;

  private Table typeTable;

  private List<String> types;

  private List<String> selected;

  public CEVAnnotationEditorPage(CEVViewer casView, CEVDocument casDocument, int index) {
    super();
    current = index;
    setTextRepr();

    this.casDocument = casDocument;
    selections = new ArrayList<Selection>();
  }

  private void setTextRepr() {
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();
    String repr = store.getString(CEVPreferenceConstants.P_ANNOTATION_REPR);

    if (repr.equals(CEVPreferenceConstants.P_ANNOTATION_REPR_TEXT))
      text_repr = true;
    else if (repr.equals(CEVPreferenceConstants.P_ANNOTATION_REPR_HTML))
      text_repr = false;
    else
      text_repr = true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#init(org.eclipse.ui.part.IPageSite)
   */
  @Override
  public void init(IPageSite pageSite) {
    super.init(pageSite);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void createControl(Composite parent) {
    pane = new Composite(parent, SWT.NONE);

    FormLayout formLayout = new FormLayout();
    FormData formData;
    pane.setLayout(formLayout);

    createButton = new Button(pane, SWT.PUSH);
    createButton.addSelectionListener(this);
    createButton.setText("Create Annotations");
    formData = new FormData();
    formData.left = new FormAttachment(null, 5);
    formData.bottom = new FormAttachment(100, -5);
    createButton.setLayoutData(formData);

    SashForm sashForm2 = new SashForm(pane, SWT.HORIZONTAL);
    formData = new FormData();
    formData.top = new FormAttachment(0, 0);
    formData.left = new FormAttachment(0, 0);
    formData.right = new FormAttachment(100, 0);
    formData.bottom = new FormAttachment(createButton);
    sashForm2.setLayoutData(formData);

    SashForm sashForm = new SashForm(sashForm2, SWT.VERTICAL);
    formData = new FormData();
    formData.top = new FormAttachment(0, 0);
    formData.left = new FormAttachment(0, 0);
    formData.right = new FormAttachment(100, 0);
    formData.bottom = new FormAttachment(createButton);
    sashForm.setLayoutData(formData);

    table = new Table(sashForm, SWT.SINGLE | SWT.BORDER);
    table.addSelectionListener(this);
    table.addMouseListener(this);

    TableColumn tableColumn = new TableColumn(table, SWT.BORDER);
    textView = new StyledText(sashForm, SWT.READ_ONLY | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
    textView.addMouseListener(this);

    selected = new ArrayList<String>();
    types = new ArrayList<String>(Arrays.asList(getCurrentCEVData().getTypeNames()));
    Collections.sort(types);

    Composite typeComposite = new Composite(sashForm2, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    layout.verticalSpacing = 0;
    layout.horizontalSpacing = 0;
    typeComposite.setLayout(layout);
    typeComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
    typeFilterText = newText(typeComposite, SWT.SINGLE, "Specify the type name");
    typeFilterText.addListener(SWT.Modify, this);
    typeTable = newTable(typeComposite, SWT.CHECK);
    ((GridData) typeTable.getLayoutData()).heightHint = 250;
    ((GridData) typeTable.getLayoutData()).minimumHeight = 100;
    typeFilterText.addListener(SWT.CHECK, this);
    displayFilteredTypes("");

    sashForm.setWeights(new int[] { 1, 1 });
    sashForm2.setWeights(new int[] { 2, 1 });
    casDataChanged();
    tableColumn.setWidth(1280);

    CEVPlugin.getDefault().getPluginPreferences().addPropertyChangeListener(this);

  }

  private void displayFilteredTypes(String aTypeName) {
    typeTable.setRedraw(false);
    typeTable.removeAll();
    String topEntry = "";
    aTypeName = aTypeName.toLowerCase();
    for (String type : types) {
      String candidateTypeName = type.toLowerCase();
      if (aTypeName.trim().equals("") || candidateTypeName.indexOf(aTypeName) != -1) {

        if (topEntry.equals("")) {
          topEntry = type;
        }
        TableItem item = new TableItem(typeTable, SWT.NULL);
        item.setText(type);
        if (selected.contains(item.getText())) {
          item.setChecked(true);
        }

      }
    }
    typeTable.setRedraw(true);
  }

  protected Table newTable(Composite parent, int style) {
    Table table = new Table(parent, style | SWT.BORDER);
    GridData gd = new GridData(GridData.FILL_BOTH);
    table.setLayoutData(gd);
    table.addListener(SWT.Selection, this);
    table.addListener(SWT.KeyUp, this);
    return table;
  }

  protected Text newText(Composite parent, int style, String tip) {
    Text t = new Text(parent, style | SWT.BORDER);
    t.setToolTipText(tip);
    t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    t.addListener(SWT.KeyUp, this);
    t.addListener(SWT.MouseUp, this);
    return t;
  }

  public void casDataChanged() {
    table.removeAll();
    selections.clear();
    dragState = Drag.none;
    if (getCurrentCEVData() != null) {
      if (getCurrentCEVData().getDocumentText() != null)
        textView.setText(getCurrentCEVData().getDocumentText());
      else
        textView.setText("");
    }
  }

  public void textSelected(int start, int end) {

    String text = getCurrentCEVData().getDocumentText().substring(start, end);
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();
    boolean trim = store.getBoolean(CEVPreferenceConstants.P_ANNOTATION_EDITOR_TRIM);

    if (trim) {
      String trimmed = text.trim();

      int indexOf = text.indexOf(trimmed);

      int trimmedStart = start + indexOf;
      int trimmedEnd = end - (text.length() - (indexOf + trimmed.length()));

      start = trimmedStart;
      end = trimmedEnd;
      text = getCurrentCEVData().getDocumentText().substring(start, end);
    }

    selections.add(new Selection(start, end));
    TableItem item = new TableItem(table, SWT.NONE);

    if (text_repr)
      text = ParserUtils.trimSpacesBeginEnd(ParserUtils.trimAllTags(Translate.decode(text), false),
              "");
    item.setText(text);
  }

  private void paintSelection() {
    int index = table.getSelectionIndex();

    if (index >= 0) {
      Selection sel = selections.get(index);
      if (sel.getStart() >= 0 && sel.getStart() <= sel.getEnd()
              && sel.getEnd() < textView.getCharCount()) {
        textView.setStyleRanges(new int[] { sel.getStart(), sel.getEnd() - sel.getStart() },
                new StyleRange[] { new StyleRange(sel.getStart(), sel.getEnd() - sel.getStart(),
                        Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT), Display
                                .getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION)) });

        textView.setSelection(sel.getStart(), sel.getEnd());
      } else {
        textView.setStyleRanges(new StyleRange[0]);
        textView.setSelection(textView.getCaretOffset());
      }
    }
  }

  private void clearAll() {
    casDataChanged();
  }

  private void deleteSelected() {
    int index = table.getSelectionIndex();

    if (index >= 0) {
      selections.remove(index);
      table.remove(index);

      if (table.getItemCount() > 0) {
        index = index < table.getItemCount() ? index : index - 1;

        table.setSelection(index);

        dragState = Drag.none;

        paintSelection();
      } else
        casDataChanged();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    pane.dispose();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#getControl()
   */
  @Override
  public Control getControl() {
    return pane;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#setFocus()
   */
  @Override
  public void setFocus() {
    pane.setFocus();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt .events.MouseEvent)
   */
  public void mouseDoubleClick(MouseEvent e) {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events .MouseEvent)
   */
  public void mouseDown(MouseEvent event) {
    if (event.getSource() == table && event.button == 3) {
      Display display = Display.getCurrent();
      Menu menu = new Menu(display.getActiveShell(), SWT.POP_UP);

      MenuItem itemFgDeleteSelected = new MenuItem(menu, SWT.PUSH);

      itemFgDeleteSelected.setText("Delete Selection");
      itemFgDeleteSelected.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event e) {
          deleteSelected();
        }
      });

      MenuItem itemFgClearAll = new MenuItem(menu, SWT.PUSH);

      itemFgClearAll.setText("Clear All");
      itemFgClearAll.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event e) {
          clearAll();
        }
      });

      menu.setVisible(true);

      while (!menu.isDisposed() && menu.isVisible()) {
        if (!display.readAndDispatch())
          display.sleep();
      }
      menu.dispose();

    } else if (event.getSource() == textView && event.button == 1 && table.getSelectionIndex() >= 0) {
      Selection sel = selections.get(table.getSelectionIndex());

      try {
        int pos = textView.getOffsetAtLocation(new Point(event.x, event.y));
        if (pos == sel.getStart())
          dragState = Drag.start;
        else if (pos == sel.getEnd())
          dragState = Drag.end;
      } catch (IllegalArgumentException e) {
        dragState = Drag.none;
      }
    } else {
      dragState = Drag.none;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events. MouseEvent)
   */
  public void mouseUp(MouseEvent event) {
    if (event.getSource() == textView) {
      if (event.button == 1 && dragState != Drag.none) {
        Selection sel = selections.get(table.getSelectionIndex());

        try {
          int pos = textView.getOffsetAtLocation(new Point(event.x, event.y));

          if (dragState == Drag.start && pos >= 0 && pos < sel.getEnd()) {
            sel.setStart(pos);

            String text = getCurrentCEVData().getDocumentText().substring(sel.getStart(),
                    sel.getEnd());

            if (text_repr)
              text = ParserUtils.trimSpacesBeginEnd(
                      ParserUtils.trimAllTags(Translate.decode(text), false), "");

            table.getItem(table.getSelectionIndex()).setText(text);

          } else if (dragState == Drag.end && pos > sel.getStart() && pos < textView.getCharCount()) {
            sel.setEnd(pos);

            String text = getCurrentCEVData().getDocumentText().substring(sel.getStart(),
                    sel.getEnd());

            if (text_repr)
              text = ParserUtils.trimSpacesBeginEnd(
                      ParserUtils.trimAllTags(Translate.decode(text), false), "");

            table.getItem(table.getSelectionIndex()).setText(text);
          }
        } catch (IllegalArgumentException e) {
        }
      }
      dragState = Drag.none;
      paintSelection();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse
   * .swt.events.SelectionEvent)
   */
  public void widgetDefaultSelected(SelectionEvent e) {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt
   * .events.SelectionEvent)
   */
  public void widgetSelected(SelectionEvent e) {
    if (e.getSource() == table) {
      dragState = Drag.none;

      paintSelection();

    } else if (e.getSource() == createButton) {
      for (String each : selected) {
        Type t = getCurrentCEVData().getCAS().getTypeSystem().getType(each);
        for (Selection s : selections) {
          boolean update = selected.indexOf(each) >= selected.size();
          getCurrentCEVData().addAnnotation(t, s.getStart(), s.getEnd(), update);
        }

      }
      clearAll();
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.runtime.Preferences$IPropertyChangeListener#propertyChange
   * (org.eclipse.core.runtime.Preferences.PropertyChangeEvent)
   */
  public void propertyChange(PropertyChangeEvent event) {
    if (event.getProperty().equals(CEVPreferenceConstants.P_ANNOTATION_REPR)) {
      setTextRepr();
    }
  }

  protected CEVData getCurrentCEVData() {
    return casDocument.getCASData(current);
  }

  public void viewChanged(int newIndex) {
    current = newIndex;
    casDataChanged();
  }

  public void annotationsAdded(List<AnnotationFS> annots) {

  }

  public void annotationsRemoved(List<AnnotationFS> annots) {

  }

  public void annotationStateChanged(Type type) {

  }

  public void annotationStateChanged(AnnotationFS annot) {

  }

  public void colorChanged(Type type) {

  }

  public void casChanged(CEVDocument casDocument) {
    this.casDocument = casDocument;
  }

  @Override
  public void handleEvent(Event event) {
    if (event.widget == typeFilterText && event.type == SWT.Modify) {
      displayFilteredTypes(typeFilterText.getText());
    } else if (event.widget == typeTable) {
      Widget item = event.item;
      if (item instanceof TableItem) {
        TableItem ti = (TableItem) item;
        if (ti.getChecked()) {
          selected.add(ti.getText());
        } else {
          selected.remove(ti.getText());
        }
      }
    }

  }
}
