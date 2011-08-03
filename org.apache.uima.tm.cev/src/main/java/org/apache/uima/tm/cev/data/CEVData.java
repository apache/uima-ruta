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

public class CEVData implements ICheckStateListener/* , ProgressListener */{

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

  private HashMap<Type, HashMap<AnnotationFS, Boolean>> annotationState;

  private HashMap<Type, Integer> annotationStateCount;

  public String htmlText;

  public LinkedHashMap<String, int[]> htmlElementPos;

  public String idName;

  public int idCount;

  public CEVAnnotationRanges annotationRanges;

  private Map<String, ICEVDataExtension> extensions;

  public CEVData(CAS cas, Map<String, StyleMapEntry> style) {
    this.cas = cas;

    extensions = new HashMap<String, ICEVDataExtension>();

    fgColors = new HashMap<Type, Color>();
    bgColors = new HashMap<Type, Color>();

    icons = new HashMap<Type, Image>();

    annotationState = new HashMap<Type, HashMap<AnnotationFS, Boolean>>();
    annotationStateCount = new HashMap<Type, Integer>();
    indexedTypes = new ArrayList<Type>();
    annotations = new ArrayList<AnnotationFS>();
    annotListeners = new HashSet<ICEVAnnotationListener>();

    initializeAnnotations(style);

    annotationRanges = new CEVAnnotationRanges(this);
  }

  public void addExtension(String name, ICEVDataExtension extension) {
    extensions.put(name, extension);
  }

  private void initializeAnnotations(Map<String, StyleMapEntry> style) {
    IPreferenceStore store = CEVPlugin.getDefault().getPreferenceStore();
    String filter = store.getString(CEVPreferenceConstants.P_ANNOTATION_FILTER);

    int i = 0;

    TypeSystem typeSystem = cas.getTypeSystem();
    Iterator<Type> tIter = typeSystem.getTypeIterator();
    while (tIter.hasNext()) {
      Type t = tIter.next();

      if (!t.getName().matches(filter) && typeSystem.subsumes(cas.getAnnotationType(), t)) {
        indexedTypes.add(t);

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
            fgColors.put(
                    t,
                    new Color(Display.getCurrent(), Display.getCurrent()
                            .getSystemColor(SWT.COLOR_WHITE).getRGB()));
          } else {
            fgColors.put(
                    t,
                    new Color(Display.getCurrent(), Display.getCurrent()
                            .getSystemColor(SWT.COLOR_BLACK).getRGB()));
          }
        }

        annotationState.put(t, new HashMap<AnnotationFS, Boolean>());
        annotationStateCount.put(t, 0);
      }
    }

    Collections.sort(indexedTypes, new Comparator<Type>() {
      public int compare(Type o1, Type o2) {
        return o1.getName().compareTo(o2.getName());
      }
    });

    FSIndex anIndex = cas.getAnnotationIndex();
    FSIterator anIter = anIndex.iterator();

    while (anIter.isValid()) {
      AnnotationFS annot = (AnnotationFS) anIter.get();
      if (annotationState.containsKey(annot.getType())) {
        boolean show = false;
        if (style != null) {
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

  public CAS getCAS() {
    return cas;
  }

  public String getDocumentText() {
    return cas.getDocumentText();
  }

  public String getViewName() {
    return cas.getViewName();
  }

  public int getAnnotationsCount() {
    return annotations.size();
  }

  public AnnotationFS[] getAnnotations() {
    return annotations.toArray(new AnnotationFS[annotations.size()]);
  }

  public Iterator<AnnotationFS> getAnnotationIterator() {
    return annotations.iterator();
  }

  public String getHTMLSource() {
    return htmlText;
  }

  public Color getForegroundColor(Type type) {
    return fgColors.get(type);
  }

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

  public Color getBackgroundColor(Type type) {
    return bgColors.get(type);
  }

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

  public Image getIcon(Type type) {

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

    return icons.get(type);
  }

  public boolean isChecked(AnnotationFS annotation) {
    if (annotationState.containsKey(annotation.getType())) {
      HashMap<AnnotationFS, Boolean> map = annotationState.get(annotation.getType());
      if (map.containsKey(annotation))
        return map.get(annotation);
    }

    return false;
  }

  public boolean isChecked(Type type) {
    return (annotationState.containsKey(type) && (annotationStateCount.get(type) == annotationState
            .get(type).size()));
  }

  public boolean isGrayed(Type type) {
    return (annotationState.containsKey(type) && (annotationStateCount.get(type) > 0) && (annotationStateCount
            .get(type) < annotationState.get(type).size()));
  }

  public String[] getTypeNames() {
    String[] names = new String[indexedTypes.size()];

    for (int i = 0; i < names.length; i++) {
      names[i] = indexedTypes.get(i).getName();
    }

    return names;
  }

  public Type getTypeByIndex(int index) {
    if (index >= 0 && index < indexedTypes.size())
      return indexedTypes.get(index);

    return null;
  }

  public void removeAnnotation(AnnotationFS annot) {
    if (annotationState.containsKey(annot.getType())
            && annotationState.get(annot.getType()).containsKey(annot)) {

      annotationRanges.removeAnnotation(annot);
      if (annotationState.get(annot.getType()).get(annot)) {
        annotationState.get(annot.getType()).put(annot, false);
        annotationStateCount.put(annot.getType(), annotationStateCount.get(annot.getType()) - 1);

      }

      cas.removeFsFromIndexes(annot);

      annotationState.get(annot.getType()).remove(annot);

      annotations.remove(annot);

      annotationStateChanged(annot);

      List<AnnotationFS> annots = new ArrayList<AnnotationFS>();
      annots.add(annot);
      for (ICEVAnnotationListener l : annotListeners)
        l.annotationsRemoved(annots);
    }
  }

  public void removeAnnotations(AnnotationFS[] annots) {
    for (AnnotationFS annot : annots) {

      if (annotationState.containsKey(annot.getType())
              && annotationState.get(annot.getType()).containsKey(annot)) {

        if (annotationState.get(annot.getType()).get(annot)) {
          annotationState.get(annot.getType()).put(annot, false);
          annotationStateCount.put(annot.getType(), annotationStateCount.get(annot.getType()) - 1);
          annotationStateChanged(annot);
        }

        cas.removeFsFromIndexes(annot);

        annotationState.get(annot.getType()).remove(annot);

        annotations.remove(annot);
        annotationRanges.removeAnnotation(annot);
      }
    }
    for (ICEVAnnotationListener l : annotListeners) {
      l.annotationsRemoved(Arrays.asList(annots));
    }
  }

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

  public void addAnnotationListener(ICEVAnnotationListener listener) {
    annotListeners.add(listener);
  }

  public void removeAnnotationListener(ICEVAnnotationListener listener) {
    annotListeners.remove(listener);
  }

  public void annotationStateChanged(Type type) {
    for (ICEVAnnotationListener l : annotListeners)
      l.annotationStateChanged(type);
  }

  public void annotationStateChanged(AnnotationFS annot) {
    for (ICEVAnnotationListener l : annotListeners)
      l.annotationStateChanged(annot);
  }

  public void annotationColorChanged(Type type) {
    for (ICEVAnnotationListener l : annotListeners)
      l.colorChanged(type);
  }

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

  public ICEVRootTreeNode getAnnotationOrderedTree(String manualFilter) {
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

  public ICEVRootTreeNode getAnnotationOrderedTree(int pos, String manualFilter) {
    CEVTypeOrderedRootTreeNode root = new CEVTypeOrderedRootTreeNode();

    Iterator<AnnotationFS> iter = getAnnotationIterator();

    while (iter.hasNext()) {
      AnnotationFS next = iter.next();
      if (manualFilter == null || "".equals(manualFilter)
              || next.getType().getName().indexOf(manualFilter) != -1) {
        if (next.getBegin() <= pos && next.getEnd() >= pos) {
          root.insertFS(next);
        }
      }
    }

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

  public StyleRangeContainer getAllStyleRanges() {
    return annotationRanges.getAllStyleRanges();
  }

  public StyleRangeContainer getStyleRangesForAAnnotation(AnnotationFS annot) {
    return annotationRanges.getStyleRangesForAAnnotation(annot);
  }

  public Map<String, Type> getAllHtmlIdsAndActiveTypes() {
    return annotationRanges.getAllHtmlIdsAndActiveTypes();
  }

  public Map<String, Type> getHtmlIdsAndActiveTypesForAAnnotation(AnnotationFS annot) {
    return annotationRanges.getHtmlIdsAndActiveTypesForAAnnotation(annot);
  }

  public String getNextHtmlID() {
    return idName + idCount++;
  }

  public boolean containsHtmlId(String id) {
    return htmlElementPos.containsKey(id);
  }

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
