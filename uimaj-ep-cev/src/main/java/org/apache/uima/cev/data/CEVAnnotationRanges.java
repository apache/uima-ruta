/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.cev.data;

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

public class CEVAnnotationRanges {

  private class RangeInfo {
    private int start;

    private int end;

    private CEVData casData;

    private LinkedList<AnnotationFS> annotations;

    private LinkedList<String> htmlIDs;

    public RangeInfo(int start, int end, CEVData casData) {
      this.start = start;
      this.end = end;

      this.casData = casData;

      htmlIDs = new LinkedList<String>();

      annotations = new LinkedList<AnnotationFS>();
    }

    public int getStart() {
      return start;
    }

    public int getEnd() {
      return end;
    }

    public void addHtmlID(String id) {
      htmlIDs.add(id);
    }

    public LinkedList<String> getHtmlID() {
      return htmlIDs;
    }

    public void addAnnotation(AnnotationFS annot) {
      annotations.addFirst(annot);
    }

    public void removeAnnotation(AnnotationFS annot) {
      annotations.remove(annot);
    }

    public AnnotationFS[] getAnnotations() {
      return annotations.toArray(new AnnotationFS[annotations.size()]);
    }

    public AnnotationFS getActiveAnnotation() {
      for (AnnotationFS a : annotations)
        if (casData.isChecked(a))
          return a;

      return null;
    }
  }

  public class StyleRangeContainer {
    private int start;

    private int end;

    private LinkedList<Integer> indices;

    private LinkedList<StyleRange> ranges;

    private HashMap<Type, StyleRange> typeStyleRangeMap;

    protected StyleRangeContainer() {
      start = Integer.MAX_VALUE;
      end = Integer.MIN_VALUE;

      indices = new LinkedList<Integer>();
      ranges = new LinkedList<StyleRange>();

      typeStyleRangeMap = new HashMap<Type, StyleRange>();
    }

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

    protected void setStart(int start) {
      this.start = start;
    }

    protected void setEnd(int end) {
      this.end = end;
    }

    public int[] getIndices() {
      int[] i = new int[indices.size()];
      int c = 0;

      Iterator<Integer> iter = indices.listIterator();

      while (iter.hasNext())
        i[c++] = iter.next();

      return i;
    }

    public StyleRange[] getRanges() {
      StyleRange[] r = new StyleRange[ranges.size()];
      int c = 0;

      Iterator<StyleRange> iter = ranges.listIterator();

      while (iter.hasNext())
        r[c++] = iter.next();

      return r;
    }

    public int getStart() {
      return start;
    }

    public int getEnd() {
      return end;
    }

    public int getLength() {
      return end - start;
    }
  }

  public class HtmlIdInfo {
    private int start;

    private int end;

    private String id;

    public HtmlIdInfo(int start, int end, String id) {
      this.start = start;
      this.end = end;
      this.id = id;
    }

    public int getStart() {
      return start;
    }

    public int getEnd() {
      return end;
    }

    public String getId() {
      return id;
    }
  }

  private CEVData casData;

  private ArrayList<RangeInfo> ranges;

  private HashMap<AnnotationFS, LinkedList<RangeInfo>> annotationRangeMap;

  public CEVAnnotationRanges(CEVData casData) {
    this.casData = casData;
    ranges = new ArrayList<RangeInfo>();
    annotationRangeMap = new HashMap<AnnotationFS, LinkedList<RangeInfo>>();

    createAnnotationRanges();
  }

  private void createAnnotationRanges() {
    // TODO refactor this method: it's too slow

    Iterator<AnnotationFS> iter = casData.getAnnotationIterator();
    LinkedList<AnnotationFS> annotations = new LinkedList<AnnotationFS>();

    int[] pos = new int[casData.getAnnotationsCount() * 2];

    int i = 0;

    while (iter.hasNext()) {
      AnnotationFS annot = iter.next();
      annotations.add(annot);

      pos[i++] = annot.getBegin();
      pos[i++] = annot.getEnd();
    }

    Arrays.sort(pos);

    int s = 0;
    int e = 1;

    while (e < pos.length) {
      if (pos[s] == pos[e]) {
        e++;
        continue;
      }

      RangeInfo ri = new RangeInfo(pos[s], pos[e], casData);
      int count = 0;

      ListIterator<AnnotationFS> listIter = annotations.listIterator();

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

  public StyleRangeContainer getAllStyleRanges() {
    StyleRangeContainer sc = new StyleRangeContainer();

    int start = Integer.MAX_VALUE;
    int end = Integer.MIN_VALUE;

    boolean ok = false;

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

  public StyleRangeContainer getStyleRangesForAAnnotation(AnnotationFS annot) {
    StyleRangeContainer sc = new StyleRangeContainer();
    int start = Integer.MAX_VALUE;
    int end = Integer.MIN_VALUE;

    boolean ok = false;

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
