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

package org.apache.uima.ruta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
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
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.rule.AbstractRule;
import org.apache.uima.ruta.rule.AbstractRuleMatch;
import org.apache.uima.ruta.type.RutaAnnotation;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RutaStream extends FSIteratorImplBase<AnnotationFS> {

  private final CAS cas;

  private FSIterator<AnnotationFS> basicIt;

  private FSIterator<AnnotationFS> currentIt;

  private AnnotationFS documentAnnotation;

  private Type documentAnnotationType;

  private Type basicType;

  private final TreeSet<RutaBasic> basics;

  private TreeMap<Integer, RutaBasic> beginAnchors;

  private TreeMap<Integer, RutaBasic> endAnchors;

  private FilterManager filter;

  private boolean dynamicAnchoring;

  private double indexPenalty = 2;

  private double anchoringFactor;

  private boolean lowMemoryProfile;

  private boolean simpleGreedyForComposed;

  private InferenceCrowd crowd;

  private Boolean greedyRuleElement;

  private Boolean greedyRule;

  protected RutaStream(CAS cas, FSIterator<AnnotationFS> current, Type basicType,
          FilterManager filter, boolean lowMemoryProfile, boolean simpleGreedyForComposed,
          InferenceCrowd crowd) {
    super();
    this.cas = cas;
    this.filter = filter;
    this.basicType = basicType;
    this.lowMemoryProfile = lowMemoryProfile;
    this.simpleGreedyForComposed = simpleGreedyForComposed;
    this.crowd = crowd;
    AnnotationFS additionalWindow = filter.getWindowAnnotation();
    updateIterators(cas, basicType, filter, additionalWindow);
    // really an if? sub it of basic should fix this
    if (additionalWindow == null) {
      documentAnnotation = cas.getDocumentAnnotation();
      documentAnnotationType = getCas().getDocumentAnnotation().getType();
      basicIt.moveToFirst();
    } else {
      documentAnnotation = additionalWindow;
      documentAnnotationType = filter.getWindowType();
    }
    // // really faster???
    // TODO this needs to be changed!! use collection of prior stream
    org.apache.uima.ruta.rule.AnnotationComparator comparator = new org.apache.uima.ruta.rule.AnnotationComparator();
    basics = new TreeSet<RutaBasic>(comparator);
    beginAnchors = new TreeMap<Integer, RutaBasic>();
    endAnchors = new TreeMap<Integer, RutaBasic>();
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(basicType).subiterator(
            documentAnnotation);
    while (iterator.isValid()) {
      RutaBasic e = (RutaBasic) iterator.get();
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

  public RutaStream(CAS cas, Type basicType, FilterManager filter, boolean lowMemoryProfile,
          boolean simpleGreedyForComposed, InferenceCrowd crowd) {
    this(cas, null, basicType, filter, lowMemoryProfile, simpleGreedyForComposed, crowd);
  }

  public void initalizeBasics() {
    AnnotationIndex<AnnotationFS> basicIndex = cas.getAnnotationIndex(basicType);
    AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex();
    final List<AnnotationFS> allAnnotations = new LinkedList<AnnotationFS>();
    for (AnnotationFS a : annotationIndex) {
      allAnnotations.add(a);
    }
    if (basicIndex.size() == 0) {
      TreeSet<Integer> anchors = new TreeSet<Integer>();
      for (AnnotationFS a : allAnnotations) {
        anchors.add(a.getBegin());
        anchors.add(a.getEnd());
      }
      if (anchors.size() == 1) {
        // for Java 6:
        // Integer first = anchors.pollFirst();
        Integer first = anchors.first();
        anchors.remove(first);
        RutaBasic newTMB = new RutaBasic(getJCas(), first, first);
        newTMB.setLowMemoryProfile(lowMemoryProfile);
        beginAnchors.put(first, newTMB);
        endAnchors.put(first, newTMB);
        basics.add(newTMB);
        cas.addFsToIndexes(newTMB);
      } else {
        while (anchors.size() >= 2) {
          // for Java 6:
          // Integer first = anchors.pollFirst();
          Integer first = anchors.first();
          anchors.remove(first);
          Integer second = anchors.first();
          RutaBasic newTMB = new RutaBasic(getJCas(), first, second);
          newTMB.setLowMemoryProfile(lowMemoryProfile);
          beginAnchors.put(first, newTMB);
          endAnchors.put(second, newTMB);
          basics.add(newTMB);
          cas.addFsToIndexes(newTMB);
        }
      }
    } else {
      RutaBasic firstBasic = (RutaBasic) basicIndex.iterator().get();
      if (firstBasic.isLowMemoryProfile() != lowMemoryProfile) {
        for (AnnotationFS each : basicIndex) {
          RutaBasic eachBasic = (RutaBasic) each;
          eachBasic.setLowMemoryProfile(lowMemoryProfile);
        }
      }
    }
    for (AnnotationFS a : allAnnotations) {
      if (!a.getType().equals(basicType)) {
        addAnnotation(a, false, false, null);
      }
    }
    updateIterators(documentAnnotation);
  }

  public void addAnnotation(AnnotationFS annotation, boolean addToIndex,
          AbstractRuleMatch<? extends AbstractRule> creator) {
    addAnnotation(annotation, true, true, creator);
  }

  public void addAnnotation(AnnotationFS annotation,
          AbstractRuleMatch<? extends AbstractRule> creator) {
    addAnnotation(annotation, false, true, creator);
  }

  public void addAnnotation(AnnotationFS annotation, boolean addToIndex, boolean updateInternal,
          AbstractRuleMatch<? extends AbstractRule> creator) {
    Type type = annotation.getType();
    boolean modified = checkSpan(annotation);
    if (modified && updateInternal) {
      updateIterators(filter.getWindowAnnotation());
    }
    RutaBasic beginAnchor = getBeginAnchor(annotation.getBegin());
    RutaBasic endAnchor = getEndAnchor(annotation.getEnd());
    beginAnchor.addBegin(annotation, type);
    if (endAnchor != null) {
      endAnchor.addEnd(annotation, type);
    }
    Collection<RutaBasic> basicAnnotationsInWindow = getAllBasicsInWindow(annotation);
    for (RutaBasic basic : basicAnnotationsInWindow) {
      basic.addPartOf(type);
    }
    if (addToIndex) {
      cas.addFsToIndexes(annotation);
    }
    crowd.annotationAdded(annotation, creator);
  }

  private boolean checkSpan(AnnotationFS annotation) {
    boolean result = false;
    int begin = annotation.getBegin();
    int end = annotation.getEnd();
    RutaBasic beginAnchor = getBeginAnchor(begin);
    RutaBasic endAnchor = getEndAnchor(end);
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
    // was for Java 6:
    // Entry<Integer, RutaBasic> floorEntry = endAnchors.floorEntry(anchor);
    // Entry<Integer, RutaBasic> ceilingEntry = endAnchors.ceilingEntry(anchor);
    // if (floorEntry != null && ceilingEntry != null) {
    // RutaBasic floor = floorEntry.getValue();
    // RutaBasic ceiling = ceilingEntry.getValue();
    RutaBasic floor = getFloor(endAnchors, anchor);
    if (floor == null) {
      floor = getFloor(beginAnchors, anchor);
    }
    RutaBasic ceiling = getCeiling(endAnchors, anchor);
    if (floor != null && ceiling != null) {
      RutaBasic toSplit = null;
      if (floor.getEnd() > anchor) {
        toSplit = floor;
      } else {
        toSplit = ceiling;
      }
      int newEnd = toSplit.getEnd();
      cas.removeFsFromIndexes(toSplit);
      toSplit.setEnd(anchor);
      RutaBasic newTMB = new RutaBasic(getJCas(), anchor, newEnd);
      newTMB.setLowMemoryProfile(lowMemoryProfile);
      cas.addFsToIndexes(toSplit);
      cas.addFsToIndexes(newTMB);
      beginAnchors.put(floor.getBegin(), floor);
      beginAnchors.put(newTMB.getBegin(), newTMB);
      beginAnchors.put(ceiling.getBegin(), ceiling);
      endAnchors.put(floor.getEnd(), floor);
      endAnchors.put(newTMB.getEnd(), newTMB);
      endAnchors.put(ceiling.getEnd(), ceiling);
      return true;
    } else {
      // TODO this should never happen! test it!
    }
    return false;
  }

  private RutaBasic getCeiling(TreeMap<Integer, RutaBasic> anchors, int anchor) {
    while (anchor <= anchors.lastKey()) {
      RutaBasic basic = anchors.get(anchor);
      if (basic != null) {
        return basic;
      }
      anchor++;
    }
    return null;
  }

  private RutaBasic getFloor(TreeMap<Integer, RutaBasic> anchors, int anchor) {
    while (anchor >= 0) {
      RutaBasic basic = anchors.get(anchor);
      if (basic != null) {
        return basic;
      }
      anchor--;
    }
    return null;
  }

  public void removeAnnotation(AnnotationFS annotationFS) {
    removeAnnotation(annotationFS, annotationFS.getType());
  }

  public void removeAnnotation(AnnotationFS annotation, Type type) {
    Collection<RutaBasic> basicAnnotationsInWindow = getAllBasicsInWindow(annotation);
    for (RutaBasic basic : basicAnnotationsInWindow) {
      basic.removePartOf(type);
    }
    Type parent = type;
    RutaBasic beginAnchor = getBeginAnchor(annotation.getBegin());
    RutaBasic endAnchor = getEndAnchor(annotation.getEnd());
    beginAnchor.removeBegin(annotation, parent);
    endAnchor.removeEnd(annotation, parent);
    if (!(annotation instanceof RutaBasic)) {
      cas.removeFsFromIndexes(annotation);
    }

  }

  public FSIterator<AnnotationFS> getFilteredBasicIterator(FSMatchConstraint constraint) {
    ConstraintFactory cf = cas.getConstraintFactory();
    FSMatchConstraint matchConstraint = cf.and(constraint, filter.getDefaultConstraint());
    return cas.createFilteredIterator(basicIt, matchConstraint);
  }

  public RutaStream getWindowStream(AnnotationFS windowAnnotation, Type windowType) {
    if (windowAnnotation.getBegin() == documentAnnotation.getBegin()
            && windowAnnotation.getEnd() == documentAnnotation.getEnd()) {
      return this;
    }

    FilterManager filterManager = new FilterManager(filter.getDefaultFilterTypes(),
            filter.getCurrentFilterTypes(), filter.getCurrentRetainTypes(), windowAnnotation,
            windowType, cas);
    RutaStream stream = new RutaStream(cas, basicIt, basicType, filterManager, lowMemoryProfile,
            simpleGreedyForComposed, crowd);
    stream.setDynamicAnchoring(dynamicAnchoring);
    stream.setGreedyRuleElement(greedyRuleElement);
    stream.setGreedyRule(greedyRule);
    return stream;
  }

  public FSIterator<AnnotationFS> copy() {
    RutaStream stream = new RutaStream(cas, currentIt.copy(), basicType, filter, lowMemoryProfile,
            simpleGreedyForComposed, crowd);
    stream.setDynamicAnchoring(dynamicAnchoring);
    stream.setGreedyRuleElement(greedyRuleElement);
    stream.setGreedyRule(greedyRule);
    return stream;
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

  public Collection<RutaBasic> getAllBasicsInWindow(AnnotationFS windowAnnotation) {
    if (windowAnnotation.getBegin() >= windowAnnotation.getEnd()) {
      return Collections.emptySet();
    }
    RutaBasic beginAnchor = getBeginAnchor(windowAnnotation.getBegin());
    if (beginAnchor.getEnd() == windowAnnotation.getEnd()) {
      Collection<RutaBasic> result = new ArrayList<RutaBasic>(1);
      result.add(beginAnchor);
      return result;
    }
    // was Java 6:
    // RutaBasic endAnchor = getEndAnchor(windowAnnotation.getEnd());
    // NavigableSet<RutaBasic> subSet = basics.subSet(beginAnchor, true, endAnchor, true);

    Collection<RutaBasic> subSet = null;
    if (windowAnnotation.getEnd() == cas.getDocumentAnnotation().getEnd()
            && windowAnnotation.getBegin() == 0) {
      subSet = basics;
    } else if (windowAnnotation.getEnd() == cas.getDocumentAnnotation().getEnd()) {
      subSet = basics.tailSet(beginAnchor);
    } else {
      RutaBasic endAnchor1 = getCeiling(endAnchors, windowAnnotation.getEnd() + 1);
      if (endAnchor1 != null) {
        subSet = basics.subSet(beginAnchor, endAnchor1);
      } else {
        // hotfix for limited window stream with a window on the complete document
        subSet = new LinkedList<RutaBasic>();
        RutaBasic floor = getFloor(endAnchors, windowAnnotation.getEnd());
        Collection<RutaBasic> subSetHead = basics.subSet(beginAnchor, floor);
        RutaBasic endAnchorTail = endAnchors.get(windowAnnotation.getEnd());
        subSet.addAll(subSetHead);
        subSet.add(endAnchorTail);
      }
    }
    return subSet;
  }

  public RutaBasic getBasicNextTo(boolean before, AnnotationFS annotation) {
    if (annotation == null) {
      return beginAnchors.get(0);
    }
    if (before) {
      RutaBasic pointer = beginAnchors.get(annotation.getBegin());
      moveTo(pointer);
      if (isVisible(pointer)) {
        moveToPrevious();
      }
      if (isValid()) {
        RutaBasic nextBasic = (RutaBasic) get();
        // TODO HOTFIX for annotation of length 0
        while (isValid() && nextBasic.getEnd() > annotation.getBegin()) {
          moveToPrevious();
          if (isValid()) {
            nextBasic = (RutaBasic) get();
          }
        }
        return nextBasic;
      }
    } else {
      RutaBasic pointer = endAnchors.get(annotation.getEnd());
      moveTo(pointer);
      if (isVisible(pointer)) {
        moveToNext();
      }
      if (isValid()) {
        RutaBasic nextBasic = (RutaBasic) get();
        // TODO HOTFIX for annotation of length 0
        while (isValid() && nextBasic.getBegin() < annotation.getEnd()) {
          moveToNext();
          if (isValid()) {
            nextBasic = (RutaBasic) get();
          }
        }
        return nextBasic;
      }
    }
    return null;
  }

  public List<RutaBasic> getBasicsInWindow(AnnotationFS windowAnnotation) {
    List<RutaBasic> result = new ArrayList<RutaBasic>();
    if (windowAnnotation instanceof RutaBasic) {
      result.add((RutaBasic) windowAnnotation);
      return result;
    }
    FSMatchConstraint defaultConstraint = filter.getDefaultConstraint();
    FSIterator<AnnotationFS> iterator = cas.createFilteredIterator(cas
            .getAnnotationIndex(basicType).subiterator(windowAnnotation), defaultConstraint);

    while (iterator.isValid()) {
      result.add((RutaBasic) iterator.get());
      iterator.moveToNext();
    }
    return result;
  }

  public RutaBasic getFirstBasicInWindow(AnnotationFS windowAnnotation) {
    return getFirstBasicInWindow(windowAnnotation, currentIt);
  }

  public RutaBasic getFirstBasicInWindow(AnnotationFS windowAnnotation, FSIterator<AnnotationFS> it) {
    if (windowAnnotation instanceof RutaBasic) {
      return (RutaBasic) windowAnnotation;
    }
    it.moveTo(windowAnnotation);
    if (it.isValid()) {
      return (RutaBasic) it.get();
    }
    return null;
  }

  public List<RutaBasic> getAnnotationsOverlappingWindow(AnnotationFS annotation) {
    if (annotation != null) {
      return getBasicsInWindow(annotation);
    } else {
      return new ArrayList<RutaBasic>();
    }
  }

  public FSIterator<AnnotationFS> getUnfilteredBasicIterator() {
    return basicIt;
  }

  public AnnotationFS getDocumentAnnotation() {
    return documentAnnotation;
  }

  public RutaAnnotation getCorrectTMA(List<AnnotationFS> annotationsInWindow,
          RutaAnnotation heuristicAnnotation) {
    for (AnnotationFS annotation : annotationsInWindow) {
      if (annotation instanceof RutaAnnotation) {
        RutaAnnotation tma = (RutaAnnotation) annotation;
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
    currentIt = filter.createFilteredIterator(cas, basicType);
  }

  public void filterTypes(List<Type> list) {
    filter.filterTypes(list);
    currentIt = filter.createFilteredIterator(cas, basicType);
  }

  public void addFilterTypes(List<Type> types) {
    filter.addFilterTypes(types);
    currentIt = filter.createFilteredIterator(cas, basicType);
  }

  public void addRetainTypes(List<Type> types) {
    filter.addRetainTypes(types);
    currentIt = filter.createFilteredIterator(cas, basicType);
  }

  public void removeFilterTypes(List<Type> types) {
    filter.removeFilterTypes(types);
    currentIt = filter.createFilteredIterator(cas, basicType);
  }

  public void removeRetainTypes(List<Type> types) {
    filter.removeRetainTypes(types);
    currentIt = filter.createFilteredIterator(cas, basicType);
  }

  public FilterManager getFilter() {
    return filter;
  }

  public RutaBasic getFirstBasicOfAll() {
    if (basics.isEmpty()) {
      return null;
    }
    return basics.first();
  }

  public Type getDocumentAnnotationType() {
    return documentAnnotationType;
  }

  public RutaBasic getNextBasic2(AnnotationFS previous) {
    AnnotationFS pointer = cas
            .createAnnotation(basicType, previous.getEnd() - 1, previous.getEnd());
    currentIt.moveTo(pointer);
    if (currentIt.isValid()) {
      RutaBasic basic = (RutaBasic) currentIt.get();
      return basic;
    }
    return null;
  }

  public RutaStream getCompleteStream() {
    FilterManager defaultFilter = new FilterManager(filter.getDefaultFilterTypes(), getCas());
    RutaStream stream = new RutaStream(getCas(), basicIt, basicType, defaultFilter,
            lowMemoryProfile, simpleGreedyForComposed, crowd);
    stream.setDynamicAnchoring(dynamicAnchoring);
    stream.setGreedyRuleElement(greedyRuleElement);
    stream.setGreedyRule(greedyRule);
    return stream;
  }

  public long getHistogram(Type type) {
    return cas.getAnnotationIndex(type).size();
  }

  public double getIndexPenalty() {
    return indexPenalty;
  }

  public RutaBasic getEndAnchor(int end) {
    return endAnchors.get(end);
  }

  public RutaBasic getBeginAnchor(int begin) {
    return beginAnchors.get(begin);
  }

  public boolean isDynamicAnchoring() {
    return dynamicAnchoring;
  }

  public void setDynamicAnchoring(boolean dynamicAnchoring) {
    this.dynamicAnchoring = dynamicAnchoring;
  }

  public boolean isGreedyRuleElement() {
    return greedyRuleElement;
  }

  public void setGreedyRuleElement(Boolean greedyAnchoring) {
    this.greedyRuleElement = greedyAnchoring;
  }

  public boolean isGreedyRule() {
    return greedyRule;
  }

  public void setGreedyRule(Boolean greedyAnchoring) {
    this.greedyRule = greedyAnchoring;
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

  public boolean isSimpleGreedyForComposed() {
    return simpleGreedyForComposed;
  }

  public void setSimpleGreedyForComposed(boolean simpleGreedyForComposed) {
    this.simpleGreedyForComposed = simpleGreedyForComposed;
  }

  public boolean isVisible(AnnotationFS annotationFS) {
    AnnotationFS windowAnnotation = filter.getWindowAnnotation();
    if (windowAnnotation != null
            && (annotationFS.getBegin() < windowAnnotation.getBegin() || annotationFS.getEnd() > windowAnnotation
                    .getEnd())) {
      return false;
    }
    int begin = annotationFS.getBegin();
    int end = annotationFS.getEnd();
    Set<Type> currentHiddenTypes = filter.getCurrentHiddenTypes();
    RutaBasic beginAnchor = getBeginAnchor(begin);
    for (Type type : currentHiddenTypes) {
      boolean partOf = beginAnchor.isPartOf(type);
      if(partOf) {
        return false;
      }
    }
    RutaBasic endAnchor = getEndAnchor(end);
    for (Type type : currentHiddenTypes) {
      boolean partOf = endAnchor.isPartOf(type);
      if(partOf) {
        return false;
      }
    }
    return true;
  }

  public RutaBasic getAnchor(boolean direction, int pointer) {
    if (direction) {
      return getBeginAnchor(pointer);
    } else {
      return getEndAnchor(pointer);
    }
  }

  public RutaBasic getAnchor(boolean direction, AnnotationFS annotation) {
    if (direction) {
      return getEndAnchor(annotation.getEnd());
    } else {
      return getBeginAnchor(annotation.getBegin());
    }
  }

  public boolean isGreedyAnchoring() {
    return greedyRule || greedyRuleElement;
  }

  public Collection<AnnotationFS> getAnnotations(Type type) {
    Collection<AnnotationFS> result = new LinkedList<AnnotationFS>();
    AnnotationFS windowAnnotation = filter.getWindowAnnotation();
    if (windowAnnotation != null
            && (windowAnnotation.getBegin() != cas.getDocumentAnnotation().getBegin() || windowAnnotation
                    .getEnd() != cas.getDocumentAnnotation().getEnd())) {
      AnnotationFS frame = cas.createAnnotation(cas.getTypeSystem().getType(RutaEngine.FRAME_TYPE),
              windowAnnotation.getBegin(), windowAnnotation.getEnd());
      FSIterator<AnnotationFS> subiterator = cas.getAnnotationIndex(type).subiterator(frame);
      while (subiterator.hasNext()) {
        AnnotationFS each = (AnnotationFS) subiterator.next();
        if (isVisible(each)) {
          result.add(each);
        }
      }
    } else {
      AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex(type);
      for (AnnotationFS each : annotationIndex) {
        if (isVisible(each)) {
          result.add(each);
        }
      }
    }
    return result;
  }

}
