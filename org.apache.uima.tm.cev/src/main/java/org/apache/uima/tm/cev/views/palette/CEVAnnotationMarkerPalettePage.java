package org.apache.uima.tm.cev.views.palette;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.CEVPlugin;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVEditor;
import org.apache.uima.tm.cev.preferences.CEVPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;


/**
 * CEVAnnotationMarkerPalettePage
 * 
 * @author Martin Toepfer
 */
public class CEVAnnotationMarkerPalettePage extends Page implements
        ICEVAnnotationMarkerPalettePage, ICEVEditor {

  /**
   * Wrapperklasse fuer Typ-Buttons.
   * 
   * @author Felix
   * 
   */
  private class AnnotationTypeButtonPanel {
    private Composite composite;

    private Button button;

    private Type type;

    private static final int LETTER_WIDTH_FACTOR = 8;

    private static final int BUTTON_WIDTH_OFFSET = 25;

    private static final int ICON_SIZE = 10;

    private static final int MAX_TITLE_LENGTH = 40;

    /**
     * Konstruktor.
     * 
     * @param parent
     * @param type
     */
    private AnnotationTypeButtonPanel(Composite parent, Type type) {
      this(parent, type, MAX_TITLE_LENGTH);
    }

    /**
     * Konstruktor.
     * 
     * @param parent
     * @param type
     */
    private AnnotationTypeButtonPanel(Composite parent, Type type, int maxTitleLenght) {
      // type merken
      this.type = type;
      // composite zusammensetzen
      composite = new Composite(parent, SWT.NULL | SWT.LEFT);
      composite.setLayout(new RowLayout(SWT.HORIZONTAL));
      // toggleButton, text links ausrichten:
      button = new Button(composite, SWT.TOGGLE | SWT.LEFT);
      // Text des Buttons setzen:
      button.setText(type.getShortName());
      final int width = BUTTON_WIDTH_OFFSET + maxTitleLenght * LETTER_WIDTH_FACTOR;
      // initiale breite setzen:
      button.setLayoutData(new RowData(width, SWT.DEFAULT));
      button.setAlignment(SWT.LEFT);
      // Farbe setzen
      updateColor();

      composite.layout(true);
    }

    private void updateColor() {
      Color bgCol = new Color(composite.getDisplay(), getCurrentCEVData().getBackgroundColor(
              this.type).getRGB());
      // TODO Vordergrundfarbe ungenutzt
      // Color fgCol = new Color(composite.getDisplay(), casData
      // .getForegroundColor(type).getRGB());
      RGB[] rgbs = { bgCol.getRGB() };
      ImageData imd = new ImageData(ICON_SIZE, ICON_SIZE, 8, new PaletteData(rgbs));
      Image image = new Image(composite.getDisplay(), imd);
      button.setImage(image);
    }

    public Button getButton() {
      return this.button;
    }

    public Type getType() {
      return this.type;
    }

    /**
     * Referenzen loesen und zum geloeschtwerden vorbereiten.
     */
    public void dispose() {
      button.dispose();
      composite.dispose();
      type = null;
    }

  }

  /**
   * Vergleichsklasse fuer Typen.
   * 
   * @author Felix
   * 
   */
  private class TypeComparator implements Comparator<Type> {
    public int compare(Type type0, Type type1) {
      return type0.getShortName().compareToIgnoreCase(type1.getShortName());
    }

  }

  /**
   * Wrapperklasse fuer Typ-Buttons.
   * 
   * @author Felix
   * 
   */
  private class AnnotationTypeCheckButtonPanel {
    private Composite composite;

    private Button button;

    private Type type;

    private static final int LETTER_WIDTH_FACTOR = 8;

    private static final int BUTTON_WIDTH_OFFSET = 25;

    private static final int ICON_SIZE = 10;

    private static final int MAX_TITLE_LENGTH = 40;

    /**
     * Konstruktor.
     * 
     * @param parent
     * @param type
     */
    private AnnotationTypeCheckButtonPanel(Composite parent, Type type) {
      this(parent, type, MAX_TITLE_LENGTH);
    }

    /**
     * Konstruktor.
     * 
     * @param parent
     * @param type
     */
    private AnnotationTypeCheckButtonPanel(Composite parent, Type type, int maxTitleLenght) {
      // type merken
      this.type = type;
      // composite zusammensetzen
      composite = new Composite(parent, SWT.NULL | SWT.LEFT);
      composite.setLayout(new RowLayout(SWT.HORIZONTAL));
      // toggleButton, text links ausrichten:
      button = new Button(composite, SWT.TOGGLE | SWT.LEFT);
      // Text des Buttons setzen:
      button.setText(type.getShortName());
      final int width = BUTTON_WIDTH_OFFSET + maxTitleLenght * LETTER_WIDTH_FACTOR;
      // initiale breite setzen:
      button.setLayoutData(new RowData(width, SWT.DEFAULT));
      button.setAlignment(SWT.LEFT);
      // Farbe setzen
      updateColor();

      composite.layout(true);
    }

    private void updateColor() {
      Color backgroundColor = getCurrentCEVData().getBackgroundColor(this.type);
      if (backgroundColor == null) {
        backgroundColor = new Color(composite.getDisplay(), 128, 128, 128);
      }
      Color bgCol = new Color(composite.getDisplay(), backgroundColor.getRGB());
      // TODO Vordergrundfarbe ungenutzt
      // Color fgCol = new Color(composite.getDisplay(), casData
      // .getForegroundColor(type).getRGB());
      RGB[] rgbs = { bgCol.getRGB() };
      ImageData imd = new ImageData(ICON_SIZE, ICON_SIZE, 8, new PaletteData(rgbs));
      Image image = new Image(composite.getDisplay(), imd);
      button.setImage(image);
    }

    public Button getButton() {
      return this.button;
    }

    public Type getType() {
      return this.type;
    }

    /**
     * Referenzen loesen und zum geloeschtwerden vorbereiten.
     */
    public void dispose() {
      button.dispose();
      composite.dispose();
      type = null;
    }

  }

  // Scrolled Composite
  private ScrolledComposite sc;

  // die ganze Komponente
  private Composite pane;

  // zugrundeliegende Daten
  private CEVDocument casDocument;

  // Typen-Liste
  private List<Type> types;

  // ToggleButton-Liste
  private List<AnnotationTypeCheckButtonPanel> toggleButtons = new ArrayList<AnnotationTypeCheckButtonPanel>();

  private int current;

  /**
   * Konstruktor
   * 
   * @param casView
   *          CASViewer
   * @param casData
   *          CASData
   */
  public CEVAnnotationMarkerPalettePage(CEVViewer casView, CEVDocument casDocument, int index) {
    super();
    current = index;
    // cas setzen
    this.casDocument = casDocument;
    // Als Listener fuer Changes an Annotationstypen anmelden
    this.getCurrentCEVData().addAnnotationListener(this);
    // Typen-Liste initialisieren:
    types = new ArrayList<Type>();
    updateTypes();
  }

  /**
   * Filters the selected / toggled Buttons.
   * 
   * @return selected / toggled Buttons.
   */
  public List<AnnotationTypeCheckButtonPanel> getToggledButtons() {
    if (toggleButtons == null) {
      return new ArrayList<AnnotationTypeCheckButtonPanel>();
    }
    List<AnnotationTypeCheckButtonPanel> toggledButtons = new ArrayList<AnnotationTypeCheckButtonPanel>();
    for (AnnotationTypeCheckButtonPanel buttonP : toggleButtons) {
      if (buttonP.getButton().getSelection()) {
        toggledButtons.add(buttonP);
      }
    }
    return toggledButtons;
  }

  /**
   * Erstellt die Button-Liste aus der types-Liste neu.<br>
   * In der Regel sollte vor Benutzung <i>updateTypes</i> aufgerufen werden.
   */
  private void updateControl(int maxTitleLength) {
    // toggleButton Liste leeren, vorsicht - richtig aushaengen
    for (AnnotationTypeCheckButtonPanel it : toggleButtons) {
      it.dispose();
    }
    toggleButtons.clear();
    // button-liste neu erstellen
    for (Iterator<Type> iterator = types.iterator(); iterator.hasNext();) {
      toggleButtons.add(new AnnotationTypeCheckButtonPanel(pane, iterator.next(), maxTitleLength));
    }
    pane.layout(true);
    sc.setContent(pane);
    sc.setMinSize(pane.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    // sc.layout(true);
  }

  /**
   * Erstellt die Typenliste neu.<br>
   * Danach sollte in der Regel <i>updateControl</i> aufgerufen werden.
   */
  private int updateTypes() {
    if (this.getCurrentCEVData() == null) {
      return 0;
    }
    // Iterator ueber types holen
    Iterator typeIterator = this.getCurrentCEVData().getCAS().getTypeSystem().getTypeIterator();
    // maximale Titellaenge
    int maxTitleLength = 0;
    // Liste leeren und mit aktuellen, relevanten types neu befuellen, sowie
    // laengsten titel finden:
    types.clear();

    while (typeIterator.hasNext()) {
      Type type = (Type) typeIterator.next();
      if (!(type.getName().startsWith("de.uniwue.tm.textmarker.kernel.type")
              || type.getName().startsWith("de.uniwue.tm.type")
              || type.getName().startsWith("uima.cas") || type.getName().startsWith("uima.tcas"))) {
        types.add(type);
        // maxTitelSuche:
        int typeShortName = type.getShortName().length();
        if (typeShortName > maxTitleLength) {
          maxTitleLength = typeShortName;
        }
      }
    }
    // Liste sortieren
    Collections.sort(types, new TypeComparator());
    return maxTitleLength;
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

    sc = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
    pane = new Composite(sc, SWT.NULL);
    pane.setLayout(new RowLayout(SWT.VERTICAL));
    // Properties for the ScrolledComposite

    sc.setExpandHorizontal(true);
    sc.setExpandVertical(true);
    sc.setContent(pane);
    sc.setMinSize(pane.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    // sorgt fuer aktuelle types-Liste und Buttons:
    casDataChanged();
  }

  private void annotateAllForSelection(int start, int end) {
    // fuer alle aktivierten Typen Annotationen setzen:
    List<AnnotationTypeCheckButtonPanel> toggledButtons = getToggledButtons();
    for (AnnotationTypeCheckButtonPanel buttonP : toggledButtons) {
      boolean update = toggledButtons.indexOf(buttonP) == toggledButtons.size() - 1;
      annotateSingleForSelection(start, end, buttonP.getType(), update);
    }
  }

  private void annotateSingleForSelection(int start, int end, Type type, boolean update) {
    if (type == null) {
      return;
    }
    getCurrentCEVData().addAnnotation(type, start, end, update);
  }

  /**
   * CAS hat sich geaendert.<br>
   * Daten und GUI updaten.
   * 
   * @param casData
   *          CAS
   */
  public void casDataChanged() {
    // casData updaten
    // types updaten und max.Titellaenge ermitteln
    final int maxTitleLength = updateTypes();
    // Button-Liste updaten
    updateControl(maxTitleLength);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    sc.dispose();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#getControl()
   */
  @Override
  public Control getControl() {
    return sc;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.part.Page#setFocus()
   */
  @Override
  public void setFocus() {
    sc.setFocus();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.ICEVAnnotationListener#annotationStateChanged(org
   * .apache.uima.cas.Type)
   */
  public void annotationStateChanged(Type type) {
    // this.casDataChanged(casData);
  }

  protected CEVData getCurrentCEVData() {
    return casDocument.getCASData(current);
  }

  public void viewChanged(int newIndex) {
    current = newIndex;
    casDataChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.ICEVAnnotationListener#colorChanged(org.apache. uima.cas.Type)
   */
  public void colorChanged(Type type) {
    for (Iterator<AnnotationTypeCheckButtonPanel> iterator = toggleButtons.iterator(); iterator
            .hasNext();) {
      AnnotationTypeCheckButtonPanel button = iterator.next();
      if (button.getType().equals(type)) {
        button.updateColor();
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.ICEVAnnotationListener#annotationAdded(org.apache
   * .uima.cas.text.AnnotationFS)
   */
  public void annotationsAdded(List<AnnotationFS> annots) {
    // Annotationen uninteressant
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.ICEVAnnotationListener#annotationRemoved(org.apache
   * .uima.cas.text.AnnotationFS)
   */
  public void annotationsRemoved(List<AnnotationFS> annots) {
    // Annotationen uninteressant
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.tm.cev.data.ICEVAnnotationListener#annotationStateChanged(org
   * .apache.uima.cas.text.AnnotationFS)
   */
  public void annotationStateChanged(AnnotationFS annot) {
    // Annotationen uninteressant
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
    }
    annotateAllForSelection(start, end);
  }

  public void casChanged(CEVDocument casDocument) {
    this.casDocument = casDocument;
  }
}
