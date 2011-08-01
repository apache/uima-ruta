package org.apache.uima.tm.cev.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.CEVPlugin;
import org.apache.uima.tm.cev.data.CEVAnnotationRanges.StyleRangeContainer;
import org.apache.uima.tm.cev.data.tree.CEVAnnotationTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVTypeOrderedRootTreeNode;
import org.apache.uima.tm.cev.data.tree.CEVTypeTreeNode;
import org.apache.uima.tm.cev.data.tree.ICEVAnnotationNode;
import org.apache.uima.tm.cev.data.tree.ICEVRootTreeNode;
import org.apache.uima.tm.cev.preferences.CEVPreferenceConstants;
import org.apache.uima.tools.stylemap.StyleMapEntry;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;


/**
 * Wrapper-Klasse fuer einzelne CAS-Views
 * 
 * @author Marco Nehmeier
 * 
 */
public class CEVData implements ICheckStateListener/* , ProgressListener */{

  /**
   * HTML-Parser zum Analysieren des HTML-Dokuments um Positionen von Tags im Code zu bstimmen und
   * mit eindeutigen HTML-ID zu versehen
   * 
   * @author Marco Nehmeier
   * 
   */

  // Farben fuer Annotationen wenn nicht in Stylemap
  private static final int[] COLOR_NR = new int[] { SWT.COLOR_BLUE, SWT.COLOR_CYAN, SWT.COLOR_GRAY,
      SWT.COLOR_GREEN, SWT.COLOR_MAGENTA, SWT.COLOR_RED, SWT.COLOR_YELLOW, SWT.COLOR_DARK_BLUE,
      SWT.COLOR_DARK_CYAN, SWT.COLOR_DARK_GRAY, SWT.COLOR_DARK_GREEN, SWT.COLOR_DARK_MAGENTA,
      SWT.COLOR_DARK_RED, SWT.COLOR_DARK_YELLOW };

  private CAS cas;

  private HashSet<ICEVAnnotationListener> annotListeners;

  private ArrayList<Type> indexedTypes;

  private ArrayList<AnnotationFS> annotations;

  private HashMap<Type, Color> fgColors;

  private HashMap<Type, Color> bgColors;

  private HashMap<Type, Image> icons;

  // Hashmap der Typen mit Hashmap der Annotatinen mit den Status
  // (Anzeingen/Nichtanzeigen)
  private HashMap<Type, HashMap<AnnotationFS, Boolean>> annotationState;

  // Zaehler der Angezeigten Annotationen eines Typs
  private HashMap<Type, Integer> annotationStateCount;

  public String htmlText;

  // Map mit den Positionen der HTML-Elemente
  public LinkedHashMap<String, int[]> htmlElementPos;

  // ID-Prefix
  public String idName;

  // ID-Zaehler
  public int idCount;

  // Annotationsbereiche
  public CEVAnnotationRanges annotationRanges;

  private Map<String, ICEVDataExtension> extensions;

  /**
   * Konstruktor
   * 
   * @param cas
   *          CAS-View
   * @param style
   *          Map mit den Stylemap-Werten fuer die einzelnen Typen
   */
  public CEVData(CAS cas, Map<String, StyleMapEntry> style) {
    this.cas = cas;

    extensions = new HashMap<String, ICEVDataExtension>();

    // Initialisieren
    fgColors = new HashMap<Type, Color>();
    bgColors = new HashMap<Type, Color>();

    icons = new HashMap<Type, Image>();

    annotationState = new HashMap<Type, HashMap<AnnotationFS, Boolean>>();
    annotationStateCount = new HashMap<Type, Integer>();
    indexedTypes = new ArrayList<Type>();
    annotations = new ArrayList<AnnotationFS>();
    annotListeners = new HashSet<ICEVAnnotationListener>();

    // Cas nach Annotationen analysieren
    initializeAnnotations(style);

    // Annotationsbereiche erstellen
    annotationRanges = new CEVAnnotationRanges(this);
  }

  public void addExtension(String name, ICEVDataExtension extension) {
    extensions.put(name, extension);
  }

  /**
   * CAS nach Annotationen analysieren
   * 
   * @param style
   *          StyleMap
   */
  private void initializeAnnotations(Map<String, StyleMapEntry> style) {
    // Preferences nach Annotation_Filter auslesen
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();
    String filter = store.getString(CEVPreferenceConstants.P_ANNOTATION_FILTER);

    int i = 0;

    // Ueber Typen interieren um Farben zu setzen
    TypeSystem typeSystem = cas.getTypeSystem();
    Iterator<Type> tIter = typeSystem.getTypeIterator();
    while (tIter.hasNext()) {
      Type t = tIter.next();

      if (!t.getName().matches(filter) && typeSystem.subsumes(cas.getAnnotationType(), t)) {
        // Typ indizieren
        indexedTypes.add(t);

        // in Stylemap
        if (style != null && style.containsKey(t.getName())) {
          StyleMapEntry se = style.get(t.getName());
          bgColors.put(t, new Color(Display.getCurrent(), se.getBackground().getRed(), se
                  .getBackground().getGreen(), se.getBackground().getBlue()));
          fgColors.put(t, new Color(Display.getCurrent(), se.getForeground().getRed(), se
                  .getForeground().getGreen(), se.getForeground().getBlue()));
        } else {
          RGB rgb = Display.getCurrent().getSystemColor(COLOR_NR[i++ % COLOR_NR.length]).getRGB();
          int sum = rgb.blue + rgb.green + rgb.red;
          int max = Math.max(Math.max(rgb.blue, rgb.green), rgb.red);
          bgColors.put(t, new Color(Display.getCurrent(), rgb));
          if (sum < 255 || max < 129) {
            fgColors.put(t, new Color(Display.getCurrent(), Display.getCurrent().getSystemColor(
                    SWT.COLOR_WHITE).getRGB()));
          } else {
            fgColors.put(t, new Color(Display.getCurrent(), Display.getCurrent().getSystemColor(
                    SWT.COLOR_BLACK).getRGB()));
          }
        }

        annotationState.put(t, new HashMap<AnnotationFS, Boolean>());
        annotationStateCount.put(t, 0);
      }
    }

    // Typenindex alpahbetisch
    Collections.sort(indexedTypes, new Comparator<Type>() {
      public int compare(Type o1, Type o2) {
        return o1.getName().compareTo(o2.getName());
      }
    });

    FSIndex anIndex = cas.getAnnotationIndex();
    FSIterator anIter = anIndex.iterator();

    // Annotationen auslesen
    while (anIter.isValid()) {
      AnnotationFS annot = (AnnotationFS) anIter.get();
      // if (!annot.getType().getName().matches(filter)) {
      if (annotationState.containsKey(annot.getType())) {
        boolean show = false;
        if (style != null) {
          // TODO what about the parents?
          StyleMapEntry se = style.get(annot.getType().getName());
          if (se != null) {
            show = se.getChecked();
          }
        }
        annotations.add(annot);
        annotationState.get(annot.getType()).put(annot, show);
        if (show) {
          annotationStateCount.put(annot.getType(), annotationStateCount.get(annot.getType()) + 1);
        }

      }

      anIter.moveToNext();
    }
  }

  /**
   * Gibt das CAS zurueck
   * 
   * @return CAS
   */
  public CAS getCAS() {
    return cas;
  }

  /**
   * Gibt den DocumentText zurueck
   * 
   * @return DocumentText
   */
  public String getDocumentText() {
    return cas.getDocumentText();
  }

  /**
   * Gibt den Namen zurueck
   * 
   * @return Name
   */
  public String getViewName() {
    return cas.getViewName();
  }

  /**
   * Gibt die Anzahl der Annotationen zurueck
   * 
   * @return Anzahl
   */
  public int getAnnotationsCount() {
    return annotations.size();
  }

  /**
   * Gibt die Annotationen zurueck
   * 
   * @return Annotationen
   */
  public AnnotationFS[] getAnnotations() {
    return annotations.toArray(new AnnotationFS[annotations.size()]);
  }

  /**
   * Gibt einen Iterator ueber die Annotationen zurueck
   * 
   * @return Iterator
   */
  public Iterator<AnnotationFS> getAnnotationIterator() {
    return annotations.iterator();
  }

  /**
   * Gibt den HTML-Code zurueck
   * 
   * @return HTML-Code
   */
  public String getHTMLSource() {
    return htmlText;
  }

  /**
   * Vordergrundfarbe eines Typs
   * 
   * @param type
   *          Typ
   * @return Farbe
   */
  public Color getForegroundColor(Type type) {
    return fgColors.get(type);
  }

  /**
   * Vordergrundfarbe eines Typs als HTML-Farbcode
   * 
   * @param type
   *          Typ
   * @return Farbe
   */
  public String getHtmlForegroundColor(Type type) {
    Color c = fgColors.get(type);

    String rH = Integer.toHexString(c.getRed());
    if (rH.length() == 1)
      rH = 0 + rH;

    String gH = Integer.toHexString(c.getGreen());
    if (gH.length() == 1)
      gH = 0 + gH;

    String bH = Integer.toHexString(c.getBlue());
    if (bH.length() == 1)
      bH = 0 + bH;

    return "#" + rH + gH + bH;
  }

  /**
   * Setzt die Vordergrundfarbe eines Typs
   * 
   * @param type
   *          Typ
   * @param color
   *          Farbe
   */
  public void setForegroundColor(Type type, Color color) {
    Color oldColor = fgColors.get(type);
    Image oldIcon = icons.get(type);

    fgColors.put(type, color);
    icons.put(type, null);

    annotationColorChanged(type);

    if (oldColor != null)
      oldColor.dispose();

    if (oldIcon != null)
      oldIcon.dispose();
  }

  /**
   * Hintergrundfarbe eines Typs
   * 
   * @param type
   *          Typ
   * @return Farbe
   */
  public Color getBackgroundColor(Type type) {
    return bgColors.get(type);
  }

  /**
   * Hintergrundfrabe eines Typs als HTML-Code
   * 
   * @param type
   *          Typ
   * @return Farbe
   */
  public String getHtmlBackgroundColor(Type type) {
    Color c = bgColors.get(type);

    String rH = Integer.toHexString(c.getRed());
    if (rH.length() == 1)
      rH = 0 + rH;

    String gH = Integer.toHexString(c.getGreen());
    if (gH.length() == 1)
      gH = 0 + gH;

    String bH = Integer.toHexString(c.getBlue());
    if (bH.length() == 1)
      bH = 0 + bH;

    return "#" + rH + gH + bH;
  }

  /**
   * Setzt die Hintergrundfrabe
   * 
   * @param type
   *          Typ
   * @param color
   *          Farbe
   */
  public void setBackgroundColor(Type type, Color color) {
    Color oldColor = bgColors.get(type);
    Image oldIcon = icons.get(type);

    bgColors.put(type, color);
    icons.put(type, null);

    annotationColorChanged(type);

    if (oldColor != null)
      oldColor.dispose();

    if (oldIcon != null)
      oldIcon.dispose();
  }

  /**
   * Gibt das Icon eines Typs zurueck
   * 
   * @param type
   *          Typ
   * @return Icon
   */
  public Image getIcon(Type type) {

    // Wenn Icon fuer diesen Typ in der Farbe noch nicht existiert =>
    // erstellen
    if (!icons.containsKey(type) || icons.get(type) == null) {
      Color fg = getForegroundColor(type);
      Color bg = getBackgroundColor(type);

      if (fg == null || bg == null) {
        return null;
      }

      PaletteData paletteData = new PaletteData(new RGB[] { bg.getRGB(), fg.getRGB() });
      ImageData imageData = new ImageData(40, 40, 1, paletteData);

      Image image = new Image(Display.getCurrent(), imageData);
      GC gc = new GC(image);
      Point p = gc.stringExtent("A");

      gc.dispose();
      image.dispose();

      imageData = new ImageData(p.x + 4, p.y, 1, paletteData);
      image = new Image(Display.getCurrent(), imageData);
      gc = new GC(image);

      gc.setBackground(bg);
      gc.setForeground(fg);

      gc.setTextAntialias(SWT.ON);
      gc.drawString("A", 2, 0);

      gc.dispose();

      icons.put(type, image);

      return image;
    }

    // Icon zurueckgeben
    return icons.get(type);
  }

  /**
   * Gibt an ob eine Annotation zur Anzeige ausgewaehlt ist
   * 
   * @param annotation
   *          Annotation
   * @return Status
   */
  public boolean isChecked(AnnotationFS annotation) {
    if (annotationState.containsKey(annotation.getType())) {
      HashMap<AnnotationFS, Boolean> map = annotationState.get(annotation.getType());
      if (map.containsKey(annotation))
        return map.get(annotation);
    }

    return false;
  }

  /**
   * Gibt an ob ein Typ zu Anzeige ausgewehlt ist
   * 
   * @param type
   *          Type
   * @return Status
   */
  public boolean isChecked(Type type) {
    return (annotationState.containsKey(type) && (annotationStateCount.get(type) == annotationState
            .get(type).size()));
  }

  /**
   * Gibt an ob nicht alle Annotationen eines Typs ausgewaehlt sind
   * 
   * @param type
   *          Type
   * @return Status
   */
  public boolean isGrayed(Type type) {
    return (annotationState.containsKey(type) && (annotationStateCount.get(type) > 0) && (annotationStateCount
            .get(type) < annotationState.get(type).size()));
  }

  /**
   * Gibt die Typnamen in alphabetischer Reihenfolge zurueck
   * 
   * @return Typen
   */
  public String[] getTypeNames() {
    String[] names = new String[indexedTypes.size()];

    for (int i = 0; i < names.length; i++) {
      names[i] = indexedTypes.get(i).getName();
    }

    return names;
  }

  /**
   * Gibt den Typ fuer den Index zurueck
   * 
   * @param index
   *          Index
   * @return Typ
   */
  public Type getTypeByIndex(int index) {
    if (index >= 0 && index < indexedTypes.size())
      return indexedTypes.get(index);

    return null;
  }

  /**
   * Entfernt eine Annotation
   * 
   * @param annot
   *          Annotation
   */
  public void removeAnnotation(AnnotationFS annot) {
    // Existiert Annot?
    if (annotationState.containsKey(annot.getType())
            && annotationState.get(annot.getType()).containsKey(annot)) {

      annotationRanges.removeAnnotation(annot);
      // Annotationszustaende aktualisieren
      if (annotationState.get(annot.getType()).get(annot)) {
        annotationState.get(annot.getType()).put(annot, false);
        annotationStateCount.put(annot.getType(), annotationStateCount.get(annot.getType()) - 1);

      }

      // aus CAS loeschen
      cas.removeFsFromIndexes(annot);

      // Aus der Zustandasliste loeschen
      annotationState.get(annot.getType()).remove(annot);

      // Aus der Annotationliste loeschen
      annotations.remove(annot);

      annotationStateChanged(annot);

      // Listener benachrichtigen
      List<AnnotationFS> annots = new ArrayList<AnnotationFS>();
      annots.add(annot);
      for (ICEVAnnotationListener l : annotListeners)
        l.annotationsRemoved(annots);
    }
  }

  /**
   * Entfernt mehrere Annotationen
   * 
   * @param annots
   *          Array mit Annotationen
   */
  public void removeAnnotations(AnnotationFS[] annots) {
    for (AnnotationFS annot : annots) {

      // Existiert Annot?
      if (annotationState.containsKey(annot.getType())
              && annotationState.get(annot.getType()).containsKey(annot)) {

        // Annotationszustaende aktualisieren
        if (annotationState.get(annot.getType()).get(annot)) {
          annotationState.get(annot.getType()).put(annot, false);
          annotationStateCount.put(annot.getType(), annotationStateCount.get(annot.getType()) - 1);
          annotationStateChanged(annot);
        }

        // aus CAS loeschen
        cas.removeFsFromIndexes(annot);

        // Aus der Zustandasliste loeschen
        annotationState.get(annot.getType()).remove(annot);

        // Aus der Annotationliste loeschen
        annotations.remove(annot);
        annotationRanges.removeAnnotation(annot);
        // Listener benachrichtigen
      }
    }
    for (ICEVAnnotationListener l : annotListeners) {
      l.annotationsRemoved(Arrays.asList(annots));
    }
  }

  /**
   * Fuegt eine Annotation hinzu
   * 
   * @param type
   *          Typ
   * @param start
   *          Startpos
   * @param end
   *          Endpos
   */
  public void addAnnotation(Type type, int start, int end, boolean update) {
    AnnotationFS annot = cas.createAnnotation(type, start, end);
    addAnnotation(annot, update);
  }

  public void addAnnotation(AnnotationFS annotation, boolean update) {
    cas.addFsToIndexes(annotation);
    annotationRanges.addAnnotation(annotation);
    boolean show = isChecked(annotation.getType());
    annotationState.get(annotation.getType()).put(annotation, show);
    annotations.add(annotation);
    if (show) {
      annotationStateCount.put(annotation.getType(),
              annotationStateCount.get(annotation.getType()) + 1);
      // annotationRanges.addAnnotation(annotation);
      annotationStateChanged(annotation);
    }
    if (update) {
      for (ICEVDataExtension each : extensions.values()) {
        each.initialize();
      }
    }
    List<AnnotationFS> annots = new ArrayList<AnnotationFS>();
    annots.add(annotation);
    for (ICEVAnnotationListener l : annotListeners) {
      l.annotationsAdded(annots);
    }
  }

  /**
   * AnnotationListener hinzufuegen
   * 
   * @param listener
   *          Listener
   */
  public void addAnnotationListener(ICEVAnnotationListener listener) {
    annotListeners.add(listener);
  }

  /**
   * AnnotationListener entfernen
   * 
   * @param listener
   *          Listener
   */
  public void removeAnnotationListener(ICEVAnnotationListener listener) {
    annotListeners.remove(listener);
  }

  /**
   * Annotationszustand hat sich geaendert => Listener Benachtichtigen
   * 
   * @param type
   *          Typ
   */
  public void annotationStateChanged(Type type) {
    for (ICEVAnnotationListener l : annotListeners)
      l.annotationStateChanged(type);
  }

  /**
   * Annotationszustand hat sich geaendert => Listener Benachtichtigen
   * 
   * @param annot
   *          Annotation
   */
  public void annotationStateChanged(AnnotationFS annot) {
    for (ICEVAnnotationListener l : annotListeners)
      l.annotationStateChanged(annot);
  }

  /**
   * Farbe einer Annotation hat sich geaendert => Listener benachrichtigen
   * 
   * @param type
   *          Typ
   */
  public void annotationColorChanged(Type type) {
    for (ICEVAnnotationListener l : annotListeners)
      l.colorChanged(type);
  }

  /**
   * Baum nach Typen sortiert
   * 
   * @return Baum
   */
  public ICEVRootTreeNode getTypeOrderedTree(String manualFilter) {
    CEVTypeOrderedRootTreeNode root = new CEVTypeOrderedRootTreeNode();
    Iterator<AnnotationFS> iter = getAnnotationIterator();
    while (iter.hasNext()) {
      AnnotationFS next = iter.next();
      if (manualFilter == null || "".equals(manualFilter)
              || next.getType().getName().indexOf(manualFilter) != -1) {
        root.insertFS(next);
      }
    }
    root.sort();
    return root;
  }

  /**
   * Baum nach Annotationen Sortiert
   * 
   * @return Baum
   */
  public ICEVRootTreeNode getAnnotationOrderedTree(String manualFilter) {
    // TODO Anpassen wegen Performance
    // CEVAnnotationOrderedRootTreeNode root = new
    // CEVAnnotationOrderedRootTreeNode();

    // Wurzel
    CEVTypeOrderedRootTreeNode root = new CEVTypeOrderedRootTreeNode();

    Iterator<AnnotationFS> iter = getAnnotationIterator();

    // Annotationen einfuegen
    while (iter.hasNext()) {
      AnnotationFS next = iter.next();
      if (manualFilter == null || "".equals(manualFilter)
              || next.getType().getName().indexOf(manualFilter) != -1) {
        root.insertFS(next);
      }
    }
    // sortieren
    root.sort();

    return root;
  }

  /**
   * Baum der Annotationen an einer bestimmten Position im Text
   * 
   * @param pos
   *          Position
   * @return Baum
   */
  public ICEVRootTreeNode getAnnotationOrderedTree(int pos, String manualFilter) {
    // TODO Anpassen wegen Performance
    // CEVAnnotationOrderedRootTreeNode root = new
    // CEVAnnotationOrderedRootTreeNode();

    // Wurzel
    CEVTypeOrderedRootTreeNode root = new CEVTypeOrderedRootTreeNode();

    Iterator<AnnotationFS> iter = getAnnotationIterator();

    // Annotationen einfuegen wenn Position passt
    while (iter.hasNext()) {
      AnnotationFS next = iter.next();
      if (manualFilter == null || "".equals(manualFilter)
              || next.getType().getName().indexOf(manualFilter) != -1) {
        if (next.getBegin() <= pos && next.getEnd() >= pos) {
          root.insertFS(next);
        }
      }
    }

    // sortieren
    root.sort();

    return root;
  }

  public List<AnnotationFS> getAnnotationsAt(int pos) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    Iterator<AnnotationFS> iter = getAnnotationIterator();

    while (iter.hasNext()) {
      AnnotationFS next = iter.next();
      if (next.getBegin() <= pos && next.getEnd() >= pos) {
        result.add(next);
      }
    }
    return result;
  }

  /**
   * Dispose
   */
  public void dispose() {
    for (Color c : fgColors.values()) {
      c.dispose();
    }
    for (Color c : bgColors.values()) {
      c.dispose();
    }
    for (Image i : icons.values()) {
      i.dispose();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse
   * .jface.viewers.CheckStateChangedEvent)
   */
  public void checkStateChanged(CheckStateChangedEvent event) {
    // Checkstate anpassen
    Object element = event.getElement();
    if (element instanceof CEVAnnotationTreeNode) {
      AnnotationFS annot = ((CEVAnnotationTreeNode) element).getAnnotation();

      if (annotationState.containsKey(annot.getType())) {
        HashMap<AnnotationFS, Boolean> map = annotationState.get(annot.getType());
        if (map.containsKey(annot)) {
          map.put(annot, event.getChecked());

          int count = annotationStateCount.get(annot.getType());

          if (event.getChecked())
            count++;
          else
            count--;

          annotationStateCount.put(annot.getType(), count);
          annotationStateChanged(annot);
        }
      }
    } else if (element instanceof CEVTypeTreeNode) {
      Type type = ((CEVTypeTreeNode) element).getType();

      if (annotationState.containsKey(type)) {
        HashMap<AnnotationFS, Boolean> map = annotationState.get(type);
        for (AnnotationFS a : map.keySet())
          map.put(a, event.getChecked());

        if (event.getChecked())
          annotationStateCount.put(type, map.size());
        else
          annotationStateCount.put(type, 0);

        if (!map.isEmpty())
          annotationStateChanged(type);
      }
    } else if (element instanceof ICEVAnnotationNode) {
      AnnotationFS annot = ((ICEVAnnotationNode) element).getAnnotation();

      if (annotationState.containsKey(annot.getType())) {
        HashMap<AnnotationFS, Boolean> map = annotationState.get(annot.getType());
        if (map.containsKey(annot)) {
          map.put(annot, event.getChecked());
          int count = annotationStateCount.get(annot.getType());
          if (event.getChecked())
            count++;
          else
            count--;
          annotationStateCount.put(annot.getType(), count);
          annotationStateChanged(annot);
        }
      }
    }
  }

  /**
   * Alle StyleRanges
   * 
   * @return StyleRanges
   */
  public StyleRangeContainer getAllStyleRanges() {
    return annotationRanges.getAllStyleRanges();
  }

  /**
   * Alle StyleRanges fuer eine Annotation
   * 
   * @param annot
   *          Annotation
   * @return StyleRanges
   */
  public StyleRangeContainer getStyleRangesForAAnnotation(AnnotationFS annot) {
    return annotationRanges.getStyleRangesForAAnnotation(annot);
  }

  /**
   * HTML-ID fuer aktive Typen
   * 
   * @return Map
   */
  public Map<String, Type> getAllHtmlIdsAndActiveTypes() {
    return annotationRanges.getAllHtmlIdsAndActiveTypes();
  }

  /**
   * HTML-ID und Typen fuer Annotatioen
   * 
   * @param annot
   *          Annotation
   * @return Map
   */
  public Map<String, Type> getHtmlIdsAndActiveTypesForAAnnotation(AnnotationFS annot) {
    return annotationRanges.getHtmlIdsAndActiveTypesForAAnnotation(annot);
  }

  /**
   * Neue HTML-ID erzeugen
   * 
   * @return HTM_ID
   */
  public String getNextHtmlID() {
    return idName + idCount++;
  }

  /**
   * Pruefen Ob HTML-ID existiert
   * 
   * @param id
   *          ID
   * @return ja/nein
   */
  public boolean containsHtmlId(String id) {
    return htmlElementPos.containsKey(id);
  }

  /**
   * Postionen fuer eine HTMLId
   * 
   * @param id
   *          ID
   * @return Positionen
   */
  public int[] getHtmlElementPos(String id) {
    if (htmlElementPos.containsKey(id))
      return htmlElementPos.get(id);

    return null;
  }

  public HashMap<Type, Integer> getAnnotationStateCount() {
    return annotationStateCount;
  }

  public HashMap<Type, HashMap<AnnotationFS, Boolean>> getAnnotationState() {
    return annotationState;
  }

}
