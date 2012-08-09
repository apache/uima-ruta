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

package org.apache.uima.textmarker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FSIteratorImplBase;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.textmarker.rule.RuleElementMatch;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.type.TextMarkerAnnotation;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class TextMarkerStream extends FSIteratorImplBase<AnnotationFS> {

  private final CAS cas;

  private FSIterator<AnnotationFS> basicIt;

  private FSIterator<AnnotationFS> currentIt;

  private AnnotationFS documentAnnotation;

  private Type documentAnnotationType;

  private Type basicType;

  private final TreeSet<TextMarkerBasic> basics;

  private TreeMap<Integer, TextMarkerBasic> beginAnchors;

  private TreeMap<Integer, TextMarkerBasic> endAnchors;

  private FilterManager filter;

  private Map<Integer, TextMarkerBasic> pointerMap = new HashMap<Integer, TextMarkerBasic>();

  private boolean dynamicAnchoring;

  private double indexPenalty = 5;

  private double anchoringFactor;

  private boolean lowMemoryProfile;

  private InferenceCrowd crowd;

  protected TextMarkerStream(CAS cas, FSIterator<AnnotationFS> current, Type basicType,
          FilterManager filter, boolean lowMemoryProfile, InferenceCrowd crowd) {
    super();
    this.cas = cas;
    this.filter = filter;
    this.basicType = basicType;
    this.lowMemoryProfile = lowMemoryProfile;
    this.crowd = crowd;
    AnnotationFS additionalWindow = filter.getWindowAnnotation();
    updateIterators(cas, basicType, filter, additionalWindow);
    // really an if? sub it of basic should fix this
    if (additionalWindow == null) {
      documentAnnotation = (DocumentAnnotation) getJCas().getDocumentAnnotationFs();
      documentAnnotationType = getCas().getDocumentAnnotation().getType();
      basicIt.moveToFirst();
    } else {
      documentAnnotation = additionalWindow;
      documentAnnotationType = filter.getWindowType();
    }
    // // really faster???
    // TODO this needs to be changed!! use collection of prior stream
    org.apache.uima.textmarker.rule.AnnotationComparator comparator = new org.apache.uima.textmarker.rule.AnnotationComparator();
    basics = new TreeSet<TextMarkerBasic>(comparator);
    beginAnchors = new TreeMap<Integer, TextMarkerBasic>();
    endAnchors = new TreeMap<Integer, TextMarkerBasic>();
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(basicType).subiterator(
            documentAnnotation);
    while (iterator.isValid()) {
      TextMarkerBasic e = (TextMarkerBasic) iterator.get();
      beginAnchors.put(e.getBegin(), e);
      endAnchors.put(e.getEnd(), e);
      basics.add(e);
      iterator.moveToNext();
    }

  }

  private void updateIterators(AnnotationFS additionalWindow) {
    updateIterators(cas, basicType, filter, additionalWindow);
  }

  private void updateIterators(CAS cas, Type basicType, FilterManager filter,
          AnnotationFS additionalWindow) {
    if (additionalWindow != null) {
      this.basicIt = cas.getAnnotationIndex(basicType).subiterator(additionalWindow);
    } else {
      this.basicIt = cas.getAnnotationIndex(basicType).iterator();
    }
    currentIt = filter.createFilteredIterator(cas, basicType);
  }

  public TextMarkerStream(CAS cas, Type basicType, FilterManager filter, boolean lowMemoryProfile, InferenceCrowd crowd) {
    this(cas, null, basicType, filter, lowMemoryProfile, crowd);
  }

  public void initalizeBasics() {
    AnnotationIndex<AnnotationFS> basicIndex = cas.getAnnotationIndex(basicType);
    AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex();
    if (basicIndex.size() == 0) {
      TreeSet<Integer> anchors = new TreeSet<Integer>();
      for (AnnotationFS a : annotationIndex) {
        anchors.add(a.getBegin());
        anchors.add(a.getEnd());
      }
      while (anchors.size() >= 2) {
        Integer first = anchors.pollFirst();
        Integer second = anchors.first();
        TextMarkerBasic newTMB = new TextMarkerBasic(getJCas(), first, second);
        newTMB.setLowMemoryProfile(lowMemoryProfile);
        beginAnchors.put(first, newTMB);
        endAnchors.put(second, newTMB);
        basics.add(newTMB);
        cas.addFsToIndexes(newTMB);
      }
    }
    for (AnnotationFS a : annotationIndex) {
      if (!a.getType().equals(basicType)) {
        addAnnotation(a, false, null);
      }
    }
    updateIterators(documentAnnotation);
  }

  public void addAnnotation(AnnotationFS annotation, RuleMatch creator) {
    addAnnotation(annotation, false, creator);
  }

  public void addAnnotation(AnnotationFS annotation, boolean update, RuleMatch creator) {
    Type type = annotation.getType();
    Type parent = type;
    boolean modified = checkSpan(annotation);
    if (modified) {
      updateIterators(filter.getWindowAnnotation());
    }
    TextMarkerBasic beginAnchor = getBeginAnchor(annotation.getBegin());
    TextMarkerBasic endAnchor = getEndAnchor(annotation.getEnd());
    beginAnchor.addBegin(annotation, parent);
    if(endAnchor!= null) {
      endAnchor.addEnd(annotation, parent);
    }
    Collection<TextMarkerBasic> basicAnnotationsInWindow = getAllBasicsInWindow(annotation);
    for (TextMarkerBasic basic : basicAnnotationsInWindow) {
      basic.addPartOf(type);
    }
    crowd.annotationAdded(annotation, creator);
  }

  private boolean checkSpan(AnnotationFS annotation) {
    boolean result = false;
    int begin = annotation.getBegin();
    int end = annotation.getEnd();
    TextMarkerBasic beginAnchor = getBeginAnchor(begin);
    TextMarkerBasic endAnchor = getEndAnchor(end);
    if (beginAnchor != null && endAnchor != null) {
      result = false;
    } else {
      if (beginAnchor == null) {
        result |= checkAnchor(begin);
      }
      if (endAnchor == null) {
        result |= checkAnchor(end);
      }
    }
    return result;
  }

  private boolean checkAnchor(int anchor) {
    Entry<Integer, TextMarkerBasic> floorEntry = endAnchors.floorEntry(anchor);
    Entry<Integer, TextMarkerBasic> ceilingEntry = endAnchors.ceilingEntry(anchor);
    if (floorEntry != null && ceilingEntry != null) {
      TextMarkerBasic floor = floorEntry.getValue();
      TextMarkerBasic ceiling = ceilingEntry.getValue();
      cas.removeFsFromIndexes(floor);
      floor.setEnd(anchor);
      AnnotationFS createAnnotation = cas.createAnnotation(basicType, anchor, ceiling.getBegin());
      cas.addFsToIndexes(floor);
      cas.addFsToIndexes(createAnnotation);
      return true;
    } else {
      // TODO this should never happen! test it and remove the assert!
      assert (false);
    }
    return false;
  }

  public void removeAnnotation(AnnotationFS annotationFS) {
    removeAnnotation(annotationFS, annotationFS.getType());
  }

  public void removeAnnotation(AnnotationFS annotation, Type type) {
    Collection<TextMarkerBasic> basicAnnotationsInWindow = getAllBasicsInWindow(annotation);
    for (TextMarkerBasic basic : basicAnnotationsInWindow) {
      basic.removePartOf(type);
    }
    Type parent = type;
    TextMarkerBasic beginAnchor = getBeginAnchor(annotation.getBegin());
    TextMarkerBasic endAnchor = getEndAnchor(annotation.getEnd());
    beginAnchor.removeBegin(annotation, parent);
    endAnchor.removeEnd(annotation, parent);
    if (!(annotation instanceof TextMarkerBasic)) {
      cas.removeFsFromIndexes(annotation);
    }

  }

  public FSIterator<AnnotationFS> getFilteredBasicIterator(FSMatchConstraint constraint) {
    ConstraintFactory cf = cas.getConstraintFactory();
    FSMatchConstraint matchConstraint = cf.and(constraint, filter.getDefaultConstraint());
    return cas.createFilteredIterator(basicIt, matchConstraint);
  }

  public TextMarkerStream getWindowStream(AnnotationFS windowAnnotation, Type windowType) {
    if (windowAnnotation.getBegin() == documentAnnotation.getBegin()
            && windowAnnotation.getEnd() == documentAnnotation.getEnd()) {
      return this;
    }

    FilterManager filterManager = new FilterManager(filter.getDefaultFilterTypes(),
            filter.getCurrentFilterTypes(), filter.getCurrentRetainTypes(), windowAnnotation,
            windowType, cas);
    TextMarkerStream stream = new TextMarkerStream(cas, basicIt, basicType, filterManager, lowMemoryProfile, crowd);
    return stream;
  }

  public FSIterator<AnnotationFS> copy() {
    return new TextMarkerStream(cas, currentIt.copy(), basicType, filter, lowMemoryProfile, crowd);
  }

  public AnnotationFS get() throws NoSuchElementException {
    return currentIt.get();
  }

  public boolean isValid() {
    return currentIt.isValid();
  }

  public void moveTo(FeatureStructure fs) {
    try {
      currentIt.moveTo(fs);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void moveToFirst() {
    currentIt.moveToFirst();
  }

  public void moveToLast() {
    currentIt.moveToLast();
  }

  public void moveToNext() {
    currentIt.moveToNext();
  }

  public void moveToPrevious() {
    currentIt.moveToPrevious();
  }

  public List<AnnotationFS> getOverappingAnnotations(AnnotationFS window, Type type) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    AnnotationFS newWindow = cas.createAnnotation(type, window.getBegin(), window.getEnd() - 1);
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(type).iterator(newWindow);
    if (!iterator.isValid()) {
      iterator.moveToLast();
    }
    while (iterator.isValid()) {
      FeatureStructure fs = iterator.get();
      if (fs instanceof AnnotationFS) {
        AnnotationFS a = (AnnotationFS) fs;
        if (a.getEnd() >= window.getEnd() && a.getBegin() <= window.getBegin()) {
          result.add(a);
        }
      }
      iterator.moveToPrevious();
    }
    return result;
  }

  public List<Annotation> getAnnotationsFollowing(Annotation annotation) {
    List<Annotation> result = new ArrayList<Annotation>();
    moveTo(annotation);
    while (currentIt.isValid()) {
      currentIt.moveToNext();
      if (currentIt.isValid()) {
        Annotation nextAnnotation = (Annotation) currentIt.get();
        if (nextAnnotation.getBegin() == annotation.getEnd()) {
          result.add(nextAnnotation);
        } else if (nextAnnotation.getBegin() >= annotation.getEnd()) {
          break;
        }
      }
    }
    return result;
  }

  public CAS getCas() {
    return cas;
  }

  public JCas getJCas() {
    try {
      return cas.getJCas();
    } catch (CASException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<AnnotationFS> getAllofType(Type type) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(type).iterator();
    while (iterator.isValid()) {
      FeatureStructure featureStructure = iterator.get();
      result.add((AnnotationFS) featureStructure);
      iterator.moveToNext();
    }
    return result;
  }

  public List<AnnotationFS> getAnnotationsInWindow2(AnnotationFS windowAnnotation, Type type) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    // if (windowAnnotation instanceof TextMarkerBasic) {
    // TextMarkerBasic basic = (TextMarkerBasic) windowAnnotation;
    // AnnotationFS a = basic.getType(type.getName());
    // if (a != null) {
    // result.add(a);
    // }
    // return result;
    // }
    windowAnnotation = cas.createAnnotation(type, windowAnnotation.getBegin(),
            windowAnnotation.getEnd() + 1);
    FSIterator<AnnotationFS> completeIt = getCas().getAnnotationIndex(type).iterator();
    if (getDocumentAnnotation().getEnd() < windowAnnotation.getEnd()) {
      completeIt.moveToLast();
    } else {
      completeIt.moveTo(windowAnnotation);
    }
    while (completeIt.isValid()
            && ((Annotation) completeIt.get()).getBegin() >= windowAnnotation.getBegin()) {
      completeIt.moveToPrevious();
    }

    if (completeIt.isValid()) {
      completeIt.moveToNext();
    } else {
      completeIt.moveToFirst();
    }

    while (completeIt.isValid()
            && ((Annotation) completeIt.get()).getBegin() < windowAnnotation.getBegin()) {
      completeIt.moveToNext();
    }

    while (completeIt.isValid()
            && ((Annotation) completeIt.get()).getBegin() >= windowAnnotation.getBegin()) {
      Annotation annotation = (Annotation) completeIt.get();
      if (getCas().getTypeSystem().subsumes(type, annotation.getType())
              && annotation.getEnd() <= windowAnnotation.getEnd()) {
        result.add(annotation);
      }
      completeIt.moveToNext();
    }
    return result;
  }

  public List<AnnotationFS> getAnnotationsInWindow(AnnotationFS windowAnnotation, Type type) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    List<AnnotationFS> inWindow = getAnnotationsInWindow2(windowAnnotation, type);
    result = inWindow;
    return result;
  }

  public Collection<TextMarkerBasic> getAllBasicsInWindow(AnnotationFS windowAnnotation) {
    if(windowAnnotation.getBegin() >= windowAnnotation.getEnd()) {
      return Collections.emptySet();
    }
    TextMarkerBasic beginAnchor = getBeginAnchor(windowAnnotation.getBegin());
    if (beginAnchor.getEnd() == windowAnnotation.getEnd()) {
      Collection<TextMarkerBasic> result = new ArrayList<TextMarkerBasic>(1);
      result.add(beginAnchor);
      return result;
    }
    TextMarkerBasic endAnchor = getEndAnchor(windowAnnotation.getEnd());
    NavigableSet<TextMarkerBasic> subSet = basics.subSet(beginAnchor, true, endAnchor, true);
    return subSet;
    // List<TextMarkerBasic> result = new ArrayList<TextMarkerBasic>();
    // if (windowAnnotation instanceof TextMarkerBasic) {
    // result.add((TextMarkerBasic) windowAnnotation);
    // return result;
    // } else if (windowAnnotation.getBegin() <=
    // documentAnnotation.getBegin()
    // && windowAnnotation.getEnd() >= documentAnnotation.getEnd()) {
    // return basics;
    // }
    // TextMarkerFrame frame = new TextMarkerFrame(getJCas(),
    // windowAnnotation.getBegin(),
    // windowAnnotation.getEnd());
    // FSIterator<AnnotationFS> iterator =
    // cas.getAnnotationIndex(basicType).subiterator(frame);
    // while (iterator.isValid()) {
    // result.add((TextMarkerBasic) iterator.get());
    // iterator.moveToNext();
    // }
    // return result;
  }

  public TextMarkerBasic getBasicNextTo(boolean before, AnnotationFS annotation) {
    if(before) {
      TextMarkerBasic pointer = beginAnchors.get(annotation.getBegin());
      moveTo(pointer);
      moveToPrevious();
      if(isValid()) {
        return (TextMarkerBasic)get();
      }
    } else {
      TextMarkerBasic pointer = endAnchors.get(annotation.getEnd());
      moveTo(pointer);
      moveToNext();
      if(isValid()) {
        return (TextMarkerBasic)get();
      }
    }
    return null;
  }
  
  
  public List<TextMarkerBasic> getBasicsInWindow(AnnotationFS windowAnnotation) {
    List<TextMarkerBasic> result = new ArrayList<TextMarkerBasic>();
    if (windowAnnotation instanceof TextMarkerBasic) {
      result.add((TextMarkerBasic) windowAnnotation);
      return result;
    }
    FSMatchConstraint defaultConstraint = filter.getDefaultConstraint();
    FSIterator<AnnotationFS> iterator = cas.createFilteredIterator(cas
            .getAnnotationIndex(basicType).subiterator(windowAnnotation), defaultConstraint);

    while (iterator.isValid()) {
      result.add((TextMarkerBasic) iterator.get());
      iterator.moveToNext();
    }
    return result;
  }

  public TextMarkerBasic getFirstBasicInWindow(AnnotationFS windowAnnotation) {
    return getFirstBasicInWindow(windowAnnotation, currentIt);
  }

  public TextMarkerBasic getFirstBasicInWindow(AnnotationFS windowAnnotation,
          FSIterator<AnnotationFS> it) {
    if (windowAnnotation instanceof TextMarkerBasic) {
      return (TextMarkerBasic) windowAnnotation;
    }
    it.moveTo(windowAnnotation);
    if (it.isValid()) {
      return (TextMarkerBasic) it.get();
    }
    return null;
  }

  public List<TextMarkerBasic> getAnnotationsOverlappingWindow(AnnotationFS annotation) {
    if (annotation != null) {
      return getBasicsInWindow(annotation);
    } else {
      return new ArrayList<TextMarkerBasic>();
    }
  }

  public FSIterator<AnnotationFS> getUnfilteredBasicIterator() {
    return basicIt;
  }

  public AnnotationFS getDocumentAnnotation() {
    return documentAnnotation;
  }

  public TextMarkerAnnotation getCorrectTMA(List<AnnotationFS> annotationsInWindow,
          TextMarkerAnnotation heuristicAnnotation) {
    for (AnnotationFS annotation : annotationsInWindow) {
      if (annotation instanceof TextMarkerAnnotation) {
        TextMarkerAnnotation tma = (TextMarkerAnnotation) annotation;
        if (tma.getBegin() == heuristicAnnotation.getBegin()
                && tma.getEnd() == heuristicAnnotation.getEnd()
                && tma.getAnnotation().getType()
                        .equals(heuristicAnnotation.getAnnotation().getType())) {
          return tma;
        }
      }
    }
    return null;
  }

  public void retainTypes(List<Type> list) {
    filter.retainTypes(list);
    FSMatchConstraint defaultConstraint = filter.getDefaultConstraint();
    currentIt = cas.createFilteredIterator(basicIt, defaultConstraint);
  }

  public void filterTypes(List<Type> list) {
    filter.filterTypes(list);
    FSMatchConstraint defaultConstraint = filter.getDefaultConstraint();
    currentIt = cas.createFilteredIterator(basicIt, defaultConstraint);
  }

  public FilterManager getFilter() {
    return filter;
  }

  public TextMarkerBasic getFirstBasicOfAll() {
    return basics.first();
  }

  public Type getDocumentAnnotationType() {
    return documentAnnotationType;
  }

  public TextMarkerBasic nextAnnotation(RuleElementMatch match) {
    if (!match.matched()) {
      return null;
    }
    List<AnnotationFS> textsMatched = match.getTextsMatched();
    AnnotationFS lastAnnotation = null;
    if (textsMatched != null && !textsMatched.isEmpty()) {
      lastAnnotation = textsMatched.get(textsMatched.size() - 1);
    }
    if (lastAnnotation == null || lastAnnotation.getEnd() >= getDocumentAnnotation().getEnd()) {
      return null;
    }
    // if (getNextBasic(lastAnnotation) == null) {
    // System.out.println();
    // }
    // if (getNextBasic(lastAnnotation) != null
    // && getNextBasic(lastAnnotation).getBegin() ==
    // lastAnnotation.getBegin()) {
    // System.out.println();
    // }
    TextMarkerBasic nextBasic = getNextBasic(lastAnnotation);
    // TextMarkerBasic nextBasic2 = getNextBasic2(lastAnnotation);
    // if (nextBasic != nextBasic2) {
    // String string = nextBasic == null ? "null" :
    // nextBasic.getCoveredText();
    // String string2 = nextBasic == null ? "null" : (nextBasic.getBegin() +
    // "");
    // System.out.println("nextBasic.getBegin() != nextBasic2.getBegin() " +
    // string + " "
    // + nextBasic2.getCoveredText());
    // System.out.println(lastAnnotation.getBegin() + "=" + string2 + "=" +
    // nextBasic2.getBegin());
    // }
    return nextBasic;
  }

  public TextMarkerBasic getNextBasic(AnnotationFS previous) {
    TextMarkerBasic pointer = pointerMap.get(previous.getEnd());
    if (pointer == null) {
      // FIXME: hotfix for ML stuff
      // pointer = new TextMarkerFrame(getJCas(), previous.getEnd()-1,
      // previous.getEnd());
      pointer = (TextMarkerBasic) cas.createAnnotation(basicType, previous.getEnd() - 1,
              previous.getEnd());
      pointerMap.put(previous.getEnd(), pointer);
    }
    currentIt.moveTo(pointer);
    if (currentIt.isValid()) {
      TextMarkerBasic basic = (TextMarkerBasic) currentIt.get();
      if (basic.getBegin() == previous.getBegin()) {
        // if (basic.getBegin() >= previous.getBegin() || basic.getEnd()
        // <= previous.getEnd()) {
        currentIt.moveToNext();
        if (currentIt.isValid()) {
          return (TextMarkerBasic) currentIt.get();
        } else {
          return null;
        }
      } else {
        return basic;
      }
    }
    return null;
  }

  public TextMarkerBasic getNextBasic2(AnnotationFS previous) {
    AnnotationFS pointer = cas
            .createAnnotation(basicType, previous.getEnd() - 1, previous.getEnd());
    currentIt.moveTo(pointer);
    if (currentIt.isValid()) {
      TextMarkerBasic basic = (TextMarkerBasic) currentIt.get();
      return basic;
    }
    return null;
  }

  public TextMarkerStream getCompleteStream() {
    FilterManager defaultFilter = new FilterManager(filter.getDefaultFilterTypes(), getCas());
    return new TextMarkerStream(getCas(), basicIt, basicType, defaultFilter, lowMemoryProfile, crowd);
  }

  public int getHistogram(Type type) {
    return cas.getAnnotationIndex(type).size();
  }

  public double getIndexPenalty() {
    return indexPenalty;
  }

  public TextMarkerBasic getEndAnchor(int end) {
    return endAnchors.get(end);
  }

  public TextMarkerBasic getBeginAnchor(int begin) {
    return beginAnchors.get(begin);
  }

  public boolean isDynamicAnchoring() {
    return dynamicAnchoring;
  }

  public void setDynamicAnchoring(boolean dynamicAnchoring) {
    this.dynamicAnchoring = dynamicAnchoring;
  }

  public void setIndexPenalty(double indexPenalty) {
    this.indexPenalty = indexPenalty;
  }

  public double getAnchoringFactor() {
    return anchoringFactor;
  }

  public void setAnchoringFactor(double anchoringFactor) {
    this.anchoringFactor = anchoringFactor;
  }

}
