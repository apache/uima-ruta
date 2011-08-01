package org.apache.uima.tm.cev.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.eclipse.swt.custom.StyleRange;

/**
 * Annotationsbereiche im Dokument
 * 
 * @author Marco Nehmeier
 * 
 */
public class CEVAnnotationRanges {

  /**
   * Bereichsinfo
   */
  private class RangeInfo {
    // Start
    private int start;

    // Ende
    private int end;

    // View
    private CEVData casData;

    // Annotation
    private LinkedList<AnnotationFS> annotations;

    // verwendete HTML-IDS
    private LinkedList<String> htmlIDs;

    /**
     * Konstruktor
     * 
     * @param start
     *          Start
     * @param end
     *          Ende
     * @param casData
     *          View
     */
    public RangeInfo(int start, int end, CEVData casData) {
      this.start = start;
      this.end = end;

      this.casData = casData;

      htmlIDs = new LinkedList<String>();

      annotations = new LinkedList<AnnotationFS>();
    }

    /**
     * Start
     * 
     * @return Start
     */
    public int getStart() {
      return start;
    }

    /**
     * Ende
     * 
     * @return Ende
     */
    public int getEnd() {
      return end;
    }

    /**
     * Fuegt eine ID ein
     * 
     * @param id
     *          ID
     */
    public void addHtmlID(String id) {
      htmlIDs.add(id);
    }

    /**
     * Gibt die IDs zurueck
     * 
     * @return Liste mit IDs
     */
    public LinkedList<String> getHtmlID() {
      return htmlIDs;
    }

    /**
     * Fuegt eine Annotation ein
     * 
     * @param annot
     *          Annotation
     */
    public void addAnnotation(AnnotationFS annot) {
      annotations.addFirst(annot);
    }

    public void removeAnnotation(AnnotationFS annot) {
      annotations.remove(annot);
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
     * Gibt akive Annotationen zurueck
     * 
     * @return Annotationen
     */
    public AnnotationFS getActiveAnnotation() {
      for (AnnotationFS a : annotations)
        if (casData.isChecked(a))
          return a;

      return null;
    }
  }

  /**
   * Container fuer StyleRanges
   */
  public class StyleRangeContainer {
    // Start
    private int start;

    // Ende
    private int end;

    // Indices
    private LinkedList<Integer> indices;

    // Ranges
    private LinkedList<StyleRange> ranges;

    // Map fuer Typ auf Stylerange
    private HashMap<Type, StyleRange> typeStyleRangeMap;

    /**
     * Konstruktor
     */
    protected StyleRangeContainer() {
      start = Integer.MAX_VALUE;
      end = Integer.MIN_VALUE;

      indices = new LinkedList<Integer>();
      ranges = new LinkedList<StyleRange>();

      typeStyleRangeMap = new HashMap<Type, StyleRange>();
    }

    /**
     * Fuegt fuer eine Annotation einen Bereich ein
     * 
     * @param start
     *          Start
     * @param end
     *          Ende
     * @param annot
     *          Annotation
     */
    protected void addRange(int start, int end, AnnotationFS annot) {
      StyleRange styleRange = null;

      if (typeStyleRangeMap.containsKey(annot.getType()))
        styleRange = typeStyleRangeMap.get(annot.getType());
      else {
        styleRange = new StyleRange();
        styleRange.foreground = casData.getForegroundColor(annot.getType());
        styleRange.background = casData.getBackgroundColor(annot.getType());
        typeStyleRangeMap.put(annot.getType(), styleRange);
      }

      indices.add(start);
      indices.add(end - start);

      ranges.add(styleRange);
    }

    /**
     * Start setzen
     * 
     * @param start
     *          Start
     */
    protected void setStart(int start) {
      this.start = start;
    }

    /**
     * Ende setzen
     * 
     * @param end
     *          Ende
     */
    protected void setEnd(int end) {
      this.end = end;
    }

    /**
     * Gibt die Indices zurueck
     * 
     * @return Indieces
     */
    public int[] getIndices() {
      int[] i = new int[indices.size()];
      int c = 0;

      Iterator<Integer> iter = indices.listIterator();

      while (iter.hasNext())
        i[c++] = iter.next();

      return i;
    }

    /**
     * Gibt die Ranges zurueck
     * 
     * @return Ranges
     */
    public StyleRange[] getRanges() {
      StyleRange[] r = new StyleRange[ranges.size()];
      int c = 0;

      Iterator<StyleRange> iter = ranges.listIterator();

      while (iter.hasNext())
        r[c++] = iter.next();

      return r;
    }

    /**
     * Gibt Start zurueck
     * 
     * @return Start
     */
    public int getStart() {
      return start;
    }

    /**
     * Gibt Ende zurueck
     * 
     * @return Ende
     */
    public int getEnd() {
      return end;
    }

    /**
     * Laenge (Ende - Start)
     * 
     * @return Laenge
     */
    public int getLength() {
      return end - start;
    }
  }

  /**
   * HTML ID Info
   */
  public class HtmlIdInfo {
    // Start
    private int start;

    // Ende
    private int end;

    // HTML ID
    private String id;

    /**
     * Konstuktor
     * 
     * @param start
     *          Start
     * @param end
     *          Ende
     * @param id
     *          HTML ID
     */
    public HtmlIdInfo(int start, int end, String id) {
      this.start = start;
      this.end = end;
      this.id = id;
    }

    /**
     * Start
     * 
     * @return Start
     */
    public int getStart() {
      return start;
    }

    /**
     * End
     * 
     * @return End
     */
    public int getEnd() {
      return end;
    }

    /**
     * HTML ID
     * 
     * @return HTML ID
     */
    public String getId() {
      return id;
    }
  }

  // View
  private CEVData casData;

  // RangeInfos
  private ArrayList<RangeInfo> ranges;

  // Map Annotation => RangeInfo
  private HashMap<AnnotationFS, LinkedList<RangeInfo>> annotationRangeMap;

  /**
   * Konstuktor
   * 
   * @param casData
   *          View
   */
  public CEVAnnotationRanges(CEVData casData) {
    this.casData = casData;
    ranges = new ArrayList<RangeInfo>();
    annotationRangeMap = new HashMap<AnnotationFS, LinkedList<RangeInfo>>();

    // Ranges Erzeugen
    createAnnotationRanges();
  }

  /**
   * Ranges erzeugen
   */
  private void createAnnotationRanges() {
    // TODO refactor this method: it's too slow

    Iterator<AnnotationFS> iter = casData.getAnnotationIterator();
    LinkedList<AnnotationFS> annotations = new LinkedList<AnnotationFS>();

    int[] pos = new int[casData.getAnnotationsCount() * 2];

    int i = 0;

    // Annotionen und Positionen ermitteln
    while (iter.hasNext()) {
      AnnotationFS annot = iter.next();
      annotations.add(annot);

      pos[i++] = annot.getBegin();
      pos[i++] = annot.getEnd();
    }

    Arrays.sort(pos);

    int s = 0;
    int e = 1;

    // Annotationen abarbeiten
    while (e < pos.length) {
      if (pos[s] == pos[e]) {
        e++;
        continue;
      }

      // neuer RangeInfo
      RangeInfo ri = new RangeInfo(pos[s], pos[e], casData);
      int count = 0;

      ListIterator<AnnotationFS> listIter = annotations.listIterator();

      // Ende suchen
      while (listIter.hasNext()) {
        AnnotationFS annot = listIter.next();

        if (annot.getBegin() <= pos[s] && annot.getEnd() >= pos[e]) {
          ri.addAnnotation(annot);
          count++;

          if (!annotationRangeMap.containsKey(annot))
            annotationRangeMap.put(annot, new LinkedList<RangeInfo>());

          annotationRangeMap.get(annot).add(ri);
        }

        if (annot.getEnd() <= pos[e])
          listIter.remove();
      }

      s = e;
      e += 1;

      // RangeInfo hinzufuegen
      if (count > 0)
        ranges.add(ri);
    }
  }

  public void addAnnotation(AnnotationFS annot) {
    List<RangeInfo> ris = getAnnotationRanges(annot, true);
    for (RangeInfo each : ris) {
      each.addAnnotation(annot);
    }
  }

  private List<RangeInfo> getAnnotationRanges(AnnotationFS annot, boolean add) {
    List<RangeInfo> result = new ArrayList<RangeInfo>();
    boolean beginFound = false;
    boolean endFound = false;
    for (RangeInfo each : ranges) {

      if (each.getStart() == annot.getBegin()) {
        beginFound = true;
      }
      if (each.getEnd() == annot.getEnd()) {
        endFound = true;
      }
      if (each.getStart() > annot.getEnd()) {
        break;
      }
    }

    if (!beginFound) {
      for (RangeInfo each : new ArrayList<RangeInfo>(ranges)) {
        if (each.getStart() < annot.getBegin() && each.getEnd() > annot.getBegin()) {
          RangeInfo newLeft = new RangeInfo(each.getStart(), annot.getBegin(), casData);
          RangeInfo newRight = new RangeInfo(annot.getBegin(), each.getEnd(), casData);
          int indexOf = ranges.indexOf(each);
          ranges.remove(indexOf);
          ranges.add(indexOf, newRight);
          ranges.add(indexOf, newLeft);
        }
      }
    }

    if (!endFound) {
      for (RangeInfo each : new ArrayList<RangeInfo>(ranges)) {
        if (each.getStart() < annot.getEnd() && each.getEnd() > annot.getEnd()) {
          RangeInfo newLeft = new RangeInfo(each.getStart(), annot.getEnd(), casData);
          RangeInfo newRight = new RangeInfo(annot.getEnd(), each.getEnd(), casData);
          int indexOf = ranges.indexOf(each);
          ranges.remove(indexOf);
          ranges.add(indexOf, newRight);
          ranges.add(indexOf, newLeft);
        }
      }
    }

    for (RangeInfo each : ranges) {
      if (each.getStart() >= annot.getBegin() && each.getEnd() <= annot.getEnd()) {
        result.add(each);
      }
      if (each.getStart() > annot.getEnd()) {
        break;
      }
    }

    return result;
  }

  public void removeAnnotation(AnnotationFS annot) {
    List<RangeInfo> ris = getAnnotationRanges(annot, false);
    for (RangeInfo each : ris) {
      each.removeAnnotation(annot);
    }
  }

  /**
   * Gibt alle StyleRanges zurueck
   * 
   * @return StyleRangeContainer
   */
  public StyleRangeContainer getAllStyleRanges() {
    StyleRangeContainer sc = new StyleRangeContainer();

    int start = Integer.MAX_VALUE;
    int end = Integer.MIN_VALUE;

    boolean ok = false;

    // Ranges interieren
    for (RangeInfo r : ranges) {
      AnnotationFS annot = r.getActiveAnnotation();

      if (annot != null)
        sc.addRange(r.getStart(), r.getEnd(), annot);

      if (r.getStart() < start)
        start = r.getStart();

      if (r.getEnd() > end)
        end = r.getEnd();

      ok = true;
    }

    if (!ok) {
      start = 0;
      end = 0;
    }

    sc.setStart(start);
    sc.setEnd(end);
    return sc;
  }

  /**
   * Gibt alle StyleRanges fuer eine Annotation zurueck
   * 
   * @param annot
   *          Annotation
   * @return StyleRangeContainer
   */
  public StyleRangeContainer getStyleRangesForAAnnotation(AnnotationFS annot) {
    StyleRangeContainer sc = new StyleRangeContainer();
    int start = Integer.MAX_VALUE;
    int end = Integer.MIN_VALUE;

    boolean ok = false;

    // ueber die Annotation iterieren
    if (annotationRangeMap.containsKey(annot))
      for (RangeInfo r : annotationRangeMap.get(annot)) {
        AnnotationFS a = r.getActiveAnnotation();

        if (a != null)
          sc.addRange(r.getStart(), r.getEnd(), a);

        if (r.getStart() < start)
          start = r.getStart();

        if (r.getEnd() > end)
          end = r.getEnd();

        ok = true;
      }

    if (!ok) {
      start = 0;
      end = 0;
    }

    sc.setStart(start);
    sc.setEnd(end);
    return sc;
  }

  /**
   * Alle HTMLIds fuer aktive Typen
   * 
   * @return
   */
  public Map<String, Type> getAllHtmlIdsAndActiveTypes() {
    TreeMap<String, Type> map = new TreeMap<String, Type>();

    for (RangeInfo r : ranges) {
      if (r.getHtmlID().size() > 0) {
        AnnotationFS a = r.getActiveAnnotation();
        Type t = null;
        if (a != null)
          t = a.getType();

        for (String id : r.getHtmlID())
          map.put(id, t);
      }
    }

    return map;
  }

  /**
   * Alle HTMLIds und und Typen fuer eine Annotation
   * 
   * @param annot
   * @return
   */
  public Map<String, Type> getHtmlIdsAndActiveTypesForAAnnotation(AnnotationFS annot) {
    TreeMap<String, Type> map = new TreeMap<String, Type>();

    if (annotationRangeMap.containsKey(annot))
      for (RangeInfo r : annotationRangeMap.get(annot)) {
        if (r.getHtmlID().size() > 0) {
          AnnotationFS a = r.getActiveAnnotation();
          Type t = null;
          if (a != null)
            t = a.getType();

          for (String id : r.getHtmlID())
            map.put(id, t);
        }
      }

    return map;
  }

  /**
   * Erzeugt HTML IDd
   * 
   * @param start
   *          Start
   * @param end
   *          End
   * @return Liste mit allen Ids
   */
  public LinkedList<HtmlIdInfo> createHtmlIDs(int start, int end) {
    LinkedList<HtmlIdInfo> list = new LinkedList<HtmlIdInfo>();

    for (RangeInfo ri : ranges) {
      if ((ri.getStart() >= start && ri.getEnd() <= end)
              || (ri.getStart() < start && ri.getEnd() > start)
              || (ri.getStart() < end && ri.getEnd() > end)) {
        String id = casData.getNextHtmlID();
        ri.addHtmlID(id);

        int s = start < ri.getStart() ? ri.getStart() : start;
        int e = end > ri.getEnd() ? ri.getEnd() : end;

        list.add(new HtmlIdInfo(s, e, id));
      }
    }

    return list;
  }
}
