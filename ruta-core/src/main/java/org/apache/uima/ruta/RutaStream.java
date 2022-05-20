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

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.BooleanArrayFS;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.DoubleArrayFS;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.FloatArrayFS;
import org.apache.uima.cas.IntArrayFS;
import org.apache.uima.cas.StringArrayFS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.fit.util.FSCollectionFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.expression.AnnotationTypeExpression;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationListExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.IBooleanListExpression;
import org.apache.uima.ruta.expression.feature.CoveredTextFeature;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.feature.GenericFeatureExpression;
import org.apache.uima.ruta.expression.feature.LazyFeature;
import org.apache.uima.ruta.expression.feature.SimpleFeatureExpression;
import org.apache.uima.ruta.expression.feature.TypeFeature;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.INumberListExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.IStringListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.ITypeListExpression;
import org.apache.uima.ruta.rule.AbstractRule;
import org.apache.uima.ruta.rule.AbstractRuleMatch;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.type.RutaAnnotation;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.type.RutaOptional;
import org.apache.uima.ruta.utils.RutaListUtils;
import org.apache.uima.ruta.utils.UIMAUtils;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RutaStream {

  private final CAS cas;

  private FSIterator<AnnotationFS> basicIt;

  private FSIterator<AnnotationFS> currentIt;

  private AnnotationFS documentAnnotation;

  private Type documentAnnotationType;

  private Type basicType;

  private NavigableMap<Integer, RutaBasic> beginAnchors = new TreeMap<>();

  private NavigableMap<Integer, RutaBasic> endAnchors = new TreeMap<>();

  private FilterManager filter;

  private boolean dynamicAnchoring;

  private double indexPenalty = 0;

  private double anchoringFactor;

  private boolean lowMemoryProfile;

  private boolean simpleGreedyForComposed;

  private InferenceCrowd crowd;

  private TypeUsageInformation typeUsage;

  private Boolean greedyRuleElement;

  private Boolean greedyRule;

  private boolean onlyOnce = false;

  private Annotation documentBeginAnchor;

  private Annotation documentEndAnchor;

  private boolean emptyIsInvisible;

  private long maxRuleMatches;

  private long maxRuleElementMatches;

  public RutaStream(CAS cas, Type basicType, FilterManager filter, boolean lowMemoryProfile,
          boolean simpleGreedyForComposed, boolean emptyIsInvisible, TypeUsageInformation typeUsage,
          InferenceCrowd crowd) {
    super();
    this.cas = cas;
    this.filter = filter;
    this.basicType = basicType;
    this.lowMemoryProfile = lowMemoryProfile;
    this.simpleGreedyForComposed = simpleGreedyForComposed;
    this.emptyIsInvisible = emptyIsInvisible;
    this.typeUsage = typeUsage;
    this.crowd = crowd;
    AnnotationFS additionalWindow = filter.getWindowAnnotation();
    updateIterators(cas, basicType, filter, additionalWindow);
    // really an if? sub it of basic should fix this
    if (additionalWindow == null) {
      documentAnnotation = cas.getDocumentAnnotation();
      documentAnnotationType = getCas().getDocumentAnnotation().getType();
      basicIt.moveToFirst();
      documentBeginAnchor = new RutaOptional(getJCas(), 0, 0);
      documentEndAnchor = new RutaOptional(getJCas(), documentAnnotation.getEnd(),
              documentAnnotation.getEnd());
    } else {
      documentAnnotation = additionalWindow;
      documentAnnotationType = filter.getWindowType();
    }
  }

  protected RutaStream(CAS cas, Type basicType, NavigableMap<Integer, RutaBasic> beginAnchors,
          NavigableMap<Integer, RutaBasic> endAnchors, FilterManager filter,
          boolean lowMemoryProfile, boolean simpleGreedyForComposed, boolean emptyIsInvisible,
          TypeUsageInformation typeUsage, InferenceCrowd crowd) {
    super();
    this.cas = cas;
    this.beginAnchors = beginAnchors;
    this.endAnchors = endAnchors;
    this.filter = filter;
    this.basicType = basicType;
    this.lowMemoryProfile = lowMemoryProfile;
    this.simpleGreedyForComposed = simpleGreedyForComposed;
    this.emptyIsInvisible = emptyIsInvisible;
    this.typeUsage = typeUsage;
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
  }

  private void updateIterators(AnnotationFS additionalWindow) {
    updateIterators(cas, basicType, filter, additionalWindow);
  }

  private void updateIterators(CAS cas, Type basicType, FilterManager filter,
          AnnotationFS additionalWindow) {
    if (additionalWindow != null) {
      // TODO UIMA-6281 replace select
      this.basicIt = cas.getAnnotationIndex(basicType).select().coveredBy(additionalWindow)
              .fsIterator();
      // was: this.basicIt = cas.getAnnotationIndex(basicType).subiterator(additionalWindow);
    } else {
      this.basicIt = cas.getAnnotationIndex(basicType).iterator();
    }
    currentIt = filter.createFilteredIterator(cas, basicType);
  }

  @Deprecated
  public void initalizeBasics(String[] reindexOnly, boolean reindexOnlyMentionedTypes) {
    RutaIndexingConfiguration config = new RutaIndexingConfiguration();
    config.setReindexOnly(reindexOnly);
    config.setReindexOnlyMentionedTypes(reindexOnlyMentionedTypes);
    config.setReindexUpdateMode(ReindexUpdateMode.ADDITIVE);
    initalizeBasics(config);
  }

  public void initalizeBasics(RutaIndexingConfiguration config) {

    AnnotationIndex<AnnotationFS> basicIndex = cas.getAnnotationIndex(basicType);
    boolean basicsAvailable = basicIndex.size() != 0;

    if (config.getReindexUpdateMode() == ReindexUpdateMode.NONE && basicsAvailable) {

      // there are already some ruta basics and we do not want to update anything, since we know we
      // do not need to. Only set internal maps
      initializeInternalAnchorMaps(basicIndex);
      return;
    }

    if (!basicsAvailable) {
      // indexing
      createBasics(config);
    } else {
      // reindexing
      updateBasics(basicIndex, config);
    }
  }

  private void createBasics(RutaIndexingConfiguration config) {
    TypeSystem typeSystem = cas.getTypeSystem();
    Collection<Type> indexTypes;
    if (config.isIndexOnlyMentionedTypes()) {
      indexTypes = convertNamesToTypes(typeUsage.getUsedTypes().toArray(new String[0]), typeSystem);
    } else {
      indexTypes = convertNamesToTypes(config.getIndexOnly(), typeSystem);
    }
    Collection<Type> indexSkipTypes = convertNamesToTypes(config.getIndexSkipTypes(), typeSystem);
    Collection<Type> indexParentTypes = removeSubsumedTypes(indexTypes, typeSystem);
    Collection<Type> allIndexTypes = expandToAllSubtypes(indexTypes, indexSkipTypes, typeSystem);

    List<FSIndex<AnnotationFS>> relevantIndexes = getRelevantIndexes(typeSystem, indexParentTypes,
            indexSkipTypes);
    createBasics(relevantIndexes, allIndexTypes);
  }

  private void updateBasics(AnnotationIndex<AnnotationFS> basicIndex,
          RutaIndexingConfiguration config) {
    TypeSystem typeSystem = cas.getTypeSystem();
    Collection<Type> reindexTypes;
    if (config.isReindexOnlyMentionedTypes()) {
      reindexTypes = convertNamesToTypes(typeUsage.getUsedTypes().toArray(new String[0]),
              typeSystem);
    } else {
      reindexTypes = convertNamesToTypes(config.getReindexOnly(), typeSystem);
    }
    Collection<Type> reindexSkipTypes = convertNamesToTypes(config.getReindexSkipTypes(),
            typeSystem);
    Collection<Type> reindexParentTypes = removeSubsumedTypes(reindexTypes, typeSystem);
    Collection<Type> allReindexTypes = expandToAllSubtypes(reindexTypes, reindexSkipTypes,
            typeSystem);
    List<FSIndex<AnnotationFS>> relevantIndexes = getRelevantIndexes(typeSystem, reindexParentTypes,
            reindexSkipTypes);
    updateBasics(basicIndex, relevantIndexes, allReindexTypes, config.getReindexUpdateMode());

  }

  private List<FSIndex<AnnotationFS>> getRelevantIndexes(TypeSystem typeSystem,
          Collection<Type> rootReindexTypeList, Collection<Type> skipTypeList) {
    List<FSIndex<AnnotationFS>> relevantIndexes = new ArrayList<>();

    for (Type type : rootReindexTypeList) {

      if (skipTypeForIndexing(type, skipTypeList, typeSystem)) {
        continue;
      }

      if (StringUtils.equals(type.getName(), CAS.TYPE_NAME_ANNOTATION)) {
        relevantIndexes.add(cas.getAnnotationIndex().withSnapshotIterators());
      } else {
        relevantIndexes.add(cas.getAnnotationIndex(type).withSnapshotIterators());
      }
    }
    return relevantIndexes;
  }

  private boolean skipTypeForIndexing(Type type, Collection<Type> skipTypeList,
          TypeSystem typeSystem) {
    // collect no ruta basics
    if (typeSystem.subsumes(basicType, type)) {
      return true;
    }
    if (skipTypeList != null) {
      if (skipTypeList.contains(type)) {
        return true;
      } else {
        for (Type skipType : skipTypeList) {
          if (typeSystem.subsumes(skipType, type)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private void createBasics(List<FSIndex<AnnotationFS>> relevantIndexes,
          Collection<Type> allIndexTypes) {

    List<Integer> anchors = getSortedUniqueAnchors(relevantIndexes);
    createBasicsForAnchors(anchors);

    // add all annotations
    for (FSIndex<AnnotationFS> index : relevantIndexes) {
      for (AnnotationFS a : index) {
        // consider skipped types
        if (allIndexTypes == null || allIndexTypes.contains(a.getType())) {
          addAnnotation(a, false, false, null);
        }
      }
    }

    updateIterators(documentAnnotation);
  }

  private void createBasicsForAnchors(List<Integer> anchors) {
    if (anchors.size() == 0) {
      // empty document
      createRutaBasic(0, 0);
    } else if (anchors.size() == 1) {
      Integer first = anchors.get(0);
      if (first >= 0 && first <= cas.getDocumentText().length())
        createRutaBasic(first, first);
    } else {
      for (int i = 0; i < anchors.size() - 1; i++) {
        Integer first = anchors.get(i);
        Integer second = anchors.get(i + 1);
        if (first < second && first >= 0 && second <= cas.getDocumentText().length()) {
          createRutaBasic(first, second);
        }
      }
    }
  }

  private List<Integer> getSortedUniqueAnchors(List<FSIndex<AnnotationFS>> relevantIndexes) {
    Set<Integer> anchorSet = new HashSet<>();
    for (FSIndex<AnnotationFS> annotationIndex : relevantIndexes) {
      for (AnnotationFS a : annotationIndex) {
        anchorSet.add(a.getBegin());
        anchorSet.add(a.getEnd());
      }
    }
    List<Integer> anchors = new ArrayList<>(anchorSet);
    Collections.sort(anchors);
    return anchors;
  }

  private void updateBasics(AnnotationIndex<AnnotationFS> basicIndex,
          List<FSIndex<AnnotationFS>> relevantIndexes, Collection<Type> allReindexTypes,
          ReindexUpdateMode indexUpdateMode) {

    initializeInternalAnchorMaps(basicIndex);

    updateRutaBasicMemoryProfile(basicIndex);

    switch (indexUpdateMode) {
      case COMPLETE:
        updateBasicsComplete(basicIndex, relevantIndexes, allReindexTypes);
        break;
      case ADDITIVE:
        updateBasicsAdditive(basicIndex, relevantIndexes);
        break;
      case SAFE_ADDITIVE:
        updateBasicsSafeAdditive(basicIndex, relevantIndexes, allReindexTypes);
        break;
      case NONE:
        // do nothing
        break;

      default:
        throw new IllegalArgumentException(
                "The given IndexUpdateMode is not supported: " + indexUpdateMode);
    }

  }

  private void updateBasicsComplete(AnnotationIndex<AnnotationFS> basicIndex,
          List<FSIndex<AnnotationFS>> relevantIndexes, Collection<Type> completeReindexTypeList) {

    // cleanup index info for given types
    for (AnnotationFS each : basicIndex) {
      RutaBasic rutaBasic = (RutaBasic) each;
      for (Type type : completeReindexTypeList) {
        int code = ((TypeImpl) type).getCode();
        rutaBasic.getPartOf()[code] = 0;
        rutaBasic.getBeginMap()[code] = null;
        rutaBasic.getEndMap()[code] = null;
      }
    }

    // add all annotations
    for (FSIndex<AnnotationFS> index : relevantIndexes) {
      for (AnnotationFS a : index) {
        // consider skipped types
        if (completeReindexTypeList.contains(a.getType())) {
          addAnnotation(a, false, false, null);
        }
      }
    }
  }

  private void updateBasicsAdditive(AnnotationIndex<AnnotationFS> basicIndex,
          List<FSIndex<AnnotationFS>> relevantIndexes) {

    // adds annotation only if not already known and included
    for (FSIndex<AnnotationFS> annotationIndex : relevantIndexes) {
      for (AnnotationFS a : annotationIndex) {
        Type type = a.getType();
        RutaBasic beginAnchor = getBeginAnchor(a.getBegin());
        RutaBasic endAnchor = getEndAnchor(a.getEnd());
        boolean shouldBeAdded = false;
        if (beginAnchor == null || endAnchor == null) {
          shouldBeAdded = true;
        } else {
          Collection<AnnotationFS> set = beginAnchor.getBeginAnchors(type);
          if (!set.contains(a)) {
            shouldBeAdded = true;
          }
        }
        if (shouldBeAdded) {
          addAnnotation(a, false, false, null);
        }
      }
    }
  }

  private void updateBasicsSafeAdditive(AnnotationIndex<AnnotationFS> basicIndex,
          List<FSIndex<AnnotationFS>> relevantIndexes, Collection<Type> completeReindexTypeList) {

    // search for removed annotations, and remove them
    for (AnnotationFS each : basicIndex) {
      RutaBasic rutaBasic = (RutaBasic) each;
      for (Type type : completeReindexTypeList) {
        // it's sufficient to check begin anchors, end should be consistent
        Collection<AnnotationFS> beginAnchors = rutaBasic.getBeginAnchors(type);
        Collection<AnnotationFS> toRemove = new ArrayList<>();
        for (AnnotationFS annotationAtAnchor : beginAnchors) {
          if (!annotationAtAnchor.getCAS().getAnnotationIndex().contains(annotationAtAnchor)) {
            // not in index? -> was removed
            toRemove.add(annotationAtAnchor);
          }
        }
        toRemove.forEach(a -> removeAnnotation(a));
      }
    }

    updateBasicsAdditive(basicIndex, relevantIndexes);
  }

  private void initializeInternalAnchorMaps(AnnotationIndex<AnnotationFS> basicIndex) {
    for (AnnotationFS e : basicIndex) {
      beginAnchors.put(e.getBegin(), (RutaBasic) e);
      endAnchors.put(e.getEnd(), (RutaBasic) e);
    }
  }

  private void updateRutaBasicMemoryProfile(AnnotationIndex<AnnotationFS> basicIndex) {
    RutaBasic firstBasic = (RutaBasic) basicIndex.iterator().get();
    if (firstBasic.isLowMemoryProfile() != lowMemoryProfile) {
      for (AnnotationFS each : basicIndex) {
        RutaBasic eachBasic = (RutaBasic) each;
        eachBasic.setLowMemoryProfile(lowMemoryProfile);
      }
    }
  }

  private RutaBasic createRutaBasic(int begin, int end) {
    RutaBasic newTMB = new RutaBasic(getJCas(), begin, end);
    newTMB.setLowMemoryProfile(lowMemoryProfile);
    beginAnchors.put(begin, newTMB);
    endAnchors.put(end, newTMB);
    cas.addFsToIndexes(newTMB);
    return newTMB;
  }

  public void addAnnotation(AnnotationFS annotation, boolean addToIndex,
          AbstractRuleMatch<? extends AbstractRule> creator) {
    addAnnotation(annotation, addToIndex, true, creator);
  }

  public void addAnnotation(AnnotationFS annotation,
          AbstractRuleMatch<? extends AbstractRule> creator) {
    addAnnotation(annotation, false, true, creator);
  }

  public void addAnnotation(AnnotationFS annotation, boolean addToIndex, boolean updateInternal,
          AbstractRuleMatch<? extends AbstractRule> creator) {
    Type type = annotation.getType();
    // no internal indexing for basics themselves or for zero-length annotations, exception for
    // DocumentAnnotation
    if (type.equals(basicType) || (annotation.getBegin() >= annotation.getEnd()
            && !annotation.equals(cas.getDocumentAnnotation()))) {
      return;
    }
    if (indexType(annotation.getType())) {
      boolean modified = checkSpan(annotation);
      if (modified && updateInternal) {
        updateIterators(filter.getWindowAnnotation());
      }
      RutaBasic beginAnchor = getBeginAnchor(annotation.getBegin());
      RutaBasic endAnchor = getEndAnchor(annotation.getEnd());
      if (beginAnchor != null) {
        beginAnchor.addBegin(annotation, type);
      }
      if (endAnchor != null) {
        endAnchor.addEnd(annotation, type);
      }
      Collection<RutaBasic> basicAnnotationsInWindow = getAllBasicsInWindow(annotation);
      for (RutaBasic basic : basicAnnotationsInWindow) {
        basic.addPartOf(type);
      }
    }
    if (addToIndex) {
      cas.addFsToIndexes(annotation);
    }
    crowd.annotationAdded(annotation, creator);
  }

  private boolean indexType(Type type) {
    if (typeUsage != null) {
      boolean contains = typeUsage.getUsedTypesWithSubTypes().contains(type.getName());
      return contains;
    }
    return true;
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
    Entry<Integer, RutaBasic> floorEntry = endAnchors.floorEntry(anchor);
    if (floorEntry == null) {
      floorEntry = beginAnchors.floorEntry(anchor);
    }
    Entry<Integer, RutaBasic> ceilingEntry = endAnchors.ceilingEntry(anchor);
    if (floorEntry != null && ceilingEntry != null) {
      RutaBasic floor = floorEntry.getValue();
      RutaBasic ceiling = ceilingEntry.getValue();
      RutaBasic toSplit = null;
      if (floor.getEnd() > anchor) {
        toSplit = floor;
      } else {
        toSplit = ceiling;
      }
      int newEnd = toSplit.getEnd();
      if (newEnd == anchor) {
        return false;
      }
      cas.removeFsFromIndexes(toSplit);
      toSplit.setEnd(anchor);
      RutaBasic newRB = new RutaBasic(getJCas(), anchor, newEnd);
      newRB.setLowMemoryProfile(lowMemoryProfile);
      newRB.setEndMap(toSplit.getEndMap().clone());
      newRB.setPartOf(toSplit.getPartOf().clone());
      toSplit.clearEndMap();
      cas.addFsToIndexes(toSplit);
      cas.addFsToIndexes(newRB);
      beginAnchors.put(floor.getBegin(), floor);
      beginAnchors.put(newRB.getBegin(), newRB);
      beginAnchors.put(ceiling.getBegin(), ceiling);
      endAnchors.put(floor.getEnd(), floor);
      endAnchors.put(newRB.getEnd(), newRB);
      endAnchors.put(ceiling.getEnd(), ceiling);
      return true;
    } else {
      // TODO this should never happen! test it!
    }
    return false;
  }

  public void removeAnnotation(AnnotationFS annotationFS) {
    removeAnnotation(annotationFS, annotationFS.getType());
  }

  public void removeAnnotation(AnnotationFS annotation, Type type) {
    if (type.getName().equals(CAS.TYPE_NAME_DOCUMENT_ANNOTATION)) {
      // do not remove DocumentAnnotation
      return;
    }
    Collection<RutaBasic> basicAnnotationsInWindow = getAllBasicsInWindow(annotation);
    for (RutaBasic basic : basicAnnotationsInWindow) {
      basic.removePartOf(type);
    }
    Type parent = type;
    RutaBasic beginAnchor = getBeginAnchor(annotation.getBegin());
    RutaBasic endAnchor = getEndAnchor(annotation.getEnd());
    if (beginAnchor != null) {
      beginAnchor.removeBegin(annotation, parent);
    }
    if (endAnchor != null) {
      endAnchor.removeEnd(annotation, parent);
    }
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
            windowType, emptyIsInvisible, cas);

    // NavigableMap<Integer, RutaBasic> newBeginAnchors = beginAnchors.subMap(
    // windowAnnotation.getBegin(), true, windowAnnotation.getEnd(), false);
    // NavigableMap<Integer, RutaBasic> newEndAnchors =
    // endAnchors.subMap(windowAnnotation.getBegin(),
    // false, windowAnnotation.getEnd(), true);

    RutaStream stream = new RutaStream(cas, basicType, beginAnchors, endAnchors, filterManager,
            lowMemoryProfile, simpleGreedyForComposed, emptyIsInvisible, typeUsage, crowd);
    stream.setDynamicAnchoring(dynamicAnchoring);
    stream.setGreedyRuleElement(greedyRuleElement);
    stream.setGreedyRule(greedyRule);
    stream.setMaxRuleMatches(maxRuleMatches);
    stream.setMaxRuleElementMatches(maxRuleElementMatches);
    return stream;
  }

  public RutaStream copy() {
    RutaStream stream = new RutaStream(cas, basicType, beginAnchors, endAnchors, filter,
            lowMemoryProfile, simpleGreedyForComposed, emptyIsInvisible, typeUsage, crowd);
    stream.setDynamicAnchoring(dynamicAnchoring);
    stream.setGreedyRuleElement(greedyRuleElement);
    stream.setGreedyRule(greedyRule);
    stream.setMaxRuleMatches(maxRuleMatches);
    stream.setMaxRuleElementMatches(maxRuleElementMatches);
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
      // e.printStackTrace();
    }
  }

  public boolean hasNext() {
    return currentIt.hasNext();
  }

  public AnnotationFS next() {
    return currentIt.next();
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
    List<AnnotationFS> result = new ArrayList<>();
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
    List<Annotation> result = new ArrayList<>();
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
      // e.printStackTrace();
    }
    return null;
  }

  public List<AnnotationFS> getAllofType(Type type) {
    List<AnnotationFS> result = new ArrayList<>();
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(type).iterator();
    while (iterator.isValid()) {
      FeatureStructure featureStructure = iterator.get();
      result.add((AnnotationFS) featureStructure);
      iterator.moveToNext();
    }
    return result;
  }

  public List<AnnotationFS> getAnnotationsInWindow(AnnotationFS windowAnnotation, Type type) {

    return getAnnotationsInWindow(type, windowAnnotation, false);
  }

  public Collection<RutaBasic> getAllBasicsInWindow(AnnotationFS windowAnnotation) {
    if (windowAnnotation.getBegin() >= windowAnnotation.getEnd()) {
      return emptySet();
    }

    RutaBasic beginAnchor = getBeginAnchor(windowAnnotation.getBegin());
    if (beginAnchor != null && beginAnchor.getEnd() == windowAnnotation.getEnd()) {
      return asList(beginAnchor);
    }

    if (windowAnnotation.getEnd() == cas.getDocumentAnnotation().getEnd()
            && windowAnnotation.getBegin() == 0) {
      return beginAnchors.values();
    }

    return beginAnchors.subMap(windowAnnotation.getBegin(), true, windowAnnotation.getEnd(), false)
            .values();
  }

  public RutaBasic getBasicNextTo(boolean before, AnnotationFS annotation) {

    if (annotation == null) {
      return null;
    }

    if (before) {

      RutaBasic pointer = endAnchors.get(annotation.getBegin());
      while (pointer != null && pointer.getBegin() >= documentAnnotation.getBegin()) {

        if (isVisible(pointer)) {
          return pointer;
        }

        Entry<Integer, RutaBasic> lowerEntry = endAnchors.lowerEntry(pointer.getEnd());
        if (lowerEntry != null) {
          pointer = lowerEntry.getValue();
        } else {
          pointer = null;
        }
      }

    } else {

      RutaBasic pointer = beginAnchors.get(annotation.getEnd());
      while (pointer != null && pointer.getEnd() <= documentAnnotation.getEnd()) {

        if (isVisible(pointer)) {
          return pointer;
        }

        Entry<Integer, RutaBasic> higherEntry = beginAnchors.higherEntry(pointer.getBegin());
        if (higherEntry != null) {
          pointer = higherEntry.getValue();
        } else {
          pointer = null;
        }
      }
    }
    return null;
  }

  public List<RutaBasic> getBasicsInWindow(AnnotationFS windowAnnotation) {
    List<RutaBasic> result = new ArrayList<>();
    if (windowAnnotation instanceof RutaBasic) {
      result.add((RutaBasic) windowAnnotation);
      return result;
    }
    FSMatchConstraint defaultConstraint = filter.getDefaultConstraint();
    // TODO UIMA-6281 replace select
    FSIterator<AnnotationFS> iterator = cas.createFilteredIterator(
            cas.getAnnotationIndex(basicType).select().coveredBy(windowAnnotation).fsIterator(),
            defaultConstraint);
//    FSIterator<AnnotationFS> iterator = cas.createFilteredIterator(
//            cas.getAnnotationIndex(basicType).subiterator(windowAnnotation), defaultConstraint);

    while (iterator.isValid()) {
      result.add((RutaBasic) iterator.get());
      iterator.moveToNext();
    }
    return result;
  }

  public RutaBasic getFirstBasicInWindow(AnnotationFS windowAnnotation) {
    return getFirstBasicInWindow(windowAnnotation, currentIt);
  }

  public RutaBasic getFirstBasicInWindow(AnnotationFS windowAnnotation,
          FSIterator<AnnotationFS> it) {
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
      return new ArrayList<>();
    }
  }

  public FSIterator<AnnotationFS> getUnfilteredBasicIterator() {
    return basicIt;
  }

  public FSIterator<AnnotationFS> getCurrentIterator() {
    return currentIt;
  }

  public AnnotationFS getDocumentAnnotation() {
    return documentAnnotation;
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
    if (beginAnchors.isEmpty()) {
      return null;
    }
    return beginAnchors.firstEntry().getValue();
  }

  public RutaBasic getLastBasicOfAll() {
    if (endAnchors.isEmpty()) {
      return null;
    }
    return endAnchors.lastEntry().getValue();
  }

  public Type getDocumentAnnotationType() {
    return documentAnnotationType;
  }

  public RutaBasic getNextBasic2(AnnotationFS previous) {
    AnnotationFS pointer = cas.createAnnotation(basicType, previous.getEnd() - 1,
            previous.getEnd());
    currentIt.moveTo(pointer);
    if (currentIt.isValid()) {
      RutaBasic basic = (RutaBasic) currentIt.get();
      return basic;
    }
    return null;
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
    greedyRuleElement = greedyAnchoring;
  }

  public boolean isGreedyRule() {
    return greedyRule;
  }

  public void setGreedyRule(Boolean greedyAnchoring) {
    greedyRule = greedyAnchoring;
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

  public boolean isGreedyAnchoring() {
    return greedyRule || greedyRuleElement;
  }

  public boolean isOnlyOnce() {
    return onlyOnce;
  }

  public void setOnlyOnce(Boolean onlyOnce) {
    this.onlyOnce = onlyOnce;
  }

  public boolean isVisible(AnnotationFS annotationFS) {
    return isVisible(annotationFS, false);
  }

  public boolean isVisible(AnnotationFS annotationFS, boolean ignoreWindow) {
    if (annotationFS == null) {
      return false;
    }
    if (annotationFS.getBegin() >= annotationFS.getEnd()) {
      return false;
    }

    AnnotationFS windowAnnotation = filter.getWindowAnnotation();
    if (!ignoreWindow && windowAnnotation != null
            && (annotationFS.getBegin() < windowAnnotation.getBegin()
                    || annotationFS.getEnd() > windowAnnotation.getEnd())) {
      return false;
    }
    int begin = annotationFS.getBegin();
    int end = annotationFS.getEnd();
    Set<Type> currentHiddenTypes = filter.getCurrentHiddenTypes();
    RutaBasic beginAnchor = getBeginAnchor(begin);
    if (beginAnchor != null) {
      if (beginAnchor.isEmpty() && emptyIsInvisible) {
        return false;
      }
      for (Type type : currentHiddenTypes) {
        boolean partOf = beginAnchor.isPartOf(type);
        if (partOf) {
          return false;
        }
      }
    }
    RutaBasic endAnchor = getEndAnchor(end);
    if (endAnchor != null) {
      if (endAnchor.isEmpty() && emptyIsInvisible) {
        return false;
      }
      for (Type type : currentHiddenTypes) {
        boolean partOf = endAnchor.isPartOf(type);
        if (partOf) {
          return false;
        }
      }
    }
    if (beginAnchor == null && endAnchor == null) {
      // missing segmentation in RutaBasics maybe because of temp annotation?
      Entry<Integer, RutaBasic> floorEntry = beginAnchors.floorEntry(Integer.valueOf(begin));
      Entry<Integer, RutaBasic> ceilingEntry = endAnchors.ceilingEntry(Integer.valueOf(end));
      if (floorEntry != null && ceilingEntry != null) {
        for (Type type : currentHiddenTypes) {
          if (floorEntry.getValue().isPartOf(type) || ceilingEntry.getValue().isPartOf(type)) {
            return false;
          }
        }
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

  public Collection<AnnotationFS> getAnnotations(Type type) {

    return getAnnotations(type, filter.getWindowAnnotation());
  }

  public Collection<AnnotationFS> getAnnotations(Type type, AnnotationFS windowAnnotation) {
    Collection<AnnotationFS> result = new LinkedList<>();
    if (windowAnnotation != null
            && (windowAnnotation.getBegin() != cas.getDocumentAnnotation().getBegin()
                    || windowAnnotation.getEnd() != cas.getDocumentAnnotation().getEnd())) {

      return getAnnotationsInWindow(type, windowAnnotation, true);
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

  public List<AnnotationFS> getAnnotationsInWindow(Type type, AnnotationFS windowAnnotation,
          boolean sensitiveToVisibility) {

    if (type == null || windowAnnotation == null) {
      return Collections.emptyList();
    }

    List<AnnotationFS> result = new LinkedList<>();

    if (cas.getTypeSystem().subsumes(type, windowAnnotation.getType())) {
      if (!sensitiveToVisibility || isVisible(windowAnnotation)) {
        // the window defined by a BLOCK could actually have already been removed, thus we do not
        // want to return it
        if (cas.getAnnotationIndex(windowAnnotation.getType()).contains(windowAnnotation)) {
          result.add(windowAnnotation);
        }
      }
    }

    List<AnnotationFS> selectCovered = CasUtil.selectCovered(cas, type, windowAnnotation);
    for (AnnotationFS each : selectCovered) {
      if (!sensitiveToVisibility || isVisible(each)) {
        result.add(each);
      }
    }
    return result;
  }

  public String getVisibleCoveredText(AnnotationFS annotationFS) {
    StringBuilder result = new StringBuilder();
    List<RutaBasic> basicsInWindow = getBasicsInWindow(annotationFS);
    for (RutaBasic each : basicsInWindow) {
      if (isVisible(each)) {
        result.append(each.getCoveredText());
      }
    }
    return result.toString();
  }

  public void assignFeatureValues(FeatureStructure annotation,
          Map<IStringExpression, IRutaExpression> map, MatchContext context) {
    Type type = annotation.getType();
    Set<Entry<IStringExpression, IRutaExpression>> entrySet = map.entrySet();
    for (Entry<IStringExpression, IRutaExpression> entry : entrySet) {
      IStringExpression key = entry.getKey();
      IRutaExpression value = entry.getValue();
      String featureName = key.getStringValue(context, this);
      Feature feature = type.getFeatureByBaseName(featureName);
      if (feature == null) {
        throw new IllegalArgumentException("Not able to assign feature value for feature '"
                + featureName + "'. Feature is not defined for type '" + type.getName() + "'"
                + " in script " + context.getParent().getName());
      }
      assignFeatureValue(annotation, feature, value, context);
    }
  }

  public void assignFeatureValue(FeatureStructure annotation, Feature feature,
          IRutaExpression value, MatchContext context) {
    if (feature == null || feature instanceof CoveredTextFeature
            || feature instanceof TypeFeature) {
      throw new IllegalArgumentException(
              "Not able to assign feature value (e.g., coveredText, type) in script "
                      + context.getParent().getName());
    }
    if (feature instanceof LazyFeature) {
      LazyFeature lazyFeature = (LazyFeature) feature;
      feature = lazyFeature.initialize(annotation);
    }

    CAS cas = annotation.getCAS();
    TypeSystem typeSystem = cas.getTypeSystem();
    Type range = feature.getRange();
    String rangeName = range.getName();

    if (typeSystem.subsumes(typeSystem.getType(CAS.TYPE_NAME_STRING), range)) {
      if (value instanceof IStringExpression) {
        IStringExpression stringExpr = (IStringExpression) value;
        String string = stringExpr.getStringValue(context, this);
        annotation.setStringValue(feature, string);
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_STRING_ARRAY)) {
      if (value instanceof IStringListExpression) {
        IStringListExpression stringListExpr = (IStringListExpression) value;
        List<String> stringList = stringListExpr.getStringList(context, this);
        StringArrayFS stringArray = FSCollectionFactory.createStringArrayFS(cas, stringList);
        annotation.setFeatureValue(feature, stringArray);
      } else if (value instanceof IStringExpression) {
        IStringExpression stringExpr = (IStringExpression) value;
        String string = stringExpr.getStringValue(context, this);
        StringArrayFS array = FSCollectionFactory.createStringArrayFS(cas, new String[] { string });
        annotation.setFeatureValue(feature, array);
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_INTEGER) || rangeName.equals(CAS.TYPE_NAME_LONG)
            || rangeName.equals(CAS.TYPE_NAME_SHORT) || rangeName.equals(CAS.TYPE_NAME_BYTE)) {
      if (value instanceof INumberExpression) {
        INumberExpression numberExpr = (INumberExpression) value;
        int v = numberExpr.getIntegerValue(context, this);
        if (annotation instanceof AnnotationFS
                && StringUtils.equals(feature.getShortName(), CAS.FEATURE_BASE_NAME_BEGIN)) {
          changeBegin((AnnotationFS) annotation, v, context.getRuleMatch());
        } else if (annotation instanceof AnnotationFS
                && StringUtils.equals(feature.getShortName(), CAS.FEATURE_BASE_NAME_END)) {
          changeEnd((AnnotationFS) annotation, v, context.getRuleMatch());
        } else {
          annotation.setIntValue(feature, v);
        }
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_INTEGER_ARRAY)) {
      if (value instanceof INumberExpression) {
        INumberExpression numberExpr = (INumberExpression) value;
        int v = numberExpr.getIntegerValue(context, this);
        IntArrayFS array = FSCollectionFactory.createIntArrayFS(cas, new int[] { v });
        annotation.setFeatureValue(feature, array);
      } else if (value instanceof INumberListExpression) {
        INumberListExpression expr = (INumberListExpression) value;
        List<Number> list = expr.getNumberList(context, this);
        IntArrayFS array = FSCollectionFactory.createIntArrayFS(cas,
                RutaListUtils.toIntArray(list));
        annotation.setFeatureValue(feature, array);
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_DOUBLE)) {
      if (value instanceof INumberExpression) {
        INumberExpression numberExpr = (INumberExpression) value;
        double v = numberExpr.getDoubleValue(context, this);
        annotation.setDoubleValue(feature, v);
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_DOUBLE_ARRAY)) {
      if (value instanceof INumberExpression) {
        INumberExpression numberExpr = (INumberExpression) value;
        double v = numberExpr.getDoubleValue(context, this);
        DoubleArrayFS array = FSCollectionFactory.createDoubleArrayFS(cas, new double[] { v });
        annotation.setFeatureValue(feature, array);
      } else if (value instanceof INumberListExpression) {
        INumberListExpression expr = (INumberListExpression) value;
        List<Number> list = expr.getNumberList(context, this);
        DoubleArrayFS array = FSCollectionFactory.createDoubleArrayFS(cas,
                RutaListUtils.toDoubleArray(list));
        annotation.setFeatureValue(feature, array);
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_FLOAT)) {
      if (value instanceof INumberExpression) {
        INumberExpression numberExpr = (INumberExpression) value;
        float v = numberExpr.getFloatValue(context, this);
        annotation.setFloatValue(feature, v);
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_FLOAT_ARRAY)) {
      if (value instanceof INumberExpression) {
        INumberExpression numberExpr = (INumberExpression) value;
        float v = numberExpr.getFloatValue(context, this);
        FloatArrayFS array = FSCollectionFactory.createFloatArrayFS(cas, new float[] { v });
        annotation.setFeatureValue(feature, array);
      } else if (value instanceof INumberListExpression) {
        INumberListExpression expr = (INumberListExpression) value;
        List<Number> list = expr.getNumberList(context, this);
        FloatArrayFS array = FSCollectionFactory.createFloatArrayFS(cas,
                RutaListUtils.toFloatArray(list));
        annotation.setFeatureValue(feature, array);
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_BOOLEAN)) {
      if (value instanceof IBooleanExpression) {
        IBooleanExpression expr = (IBooleanExpression) value;
        Boolean v = expr.getBooleanValue(context, this);
        annotation.setBooleanValue(feature, v);
      }
    } else if (rangeName.equals(CAS.TYPE_NAME_BOOLEAN_ARRAY)) {
      if (value instanceof IBooleanListExpression) {
        IBooleanListExpression expr = (IBooleanListExpression) value;
        List<Boolean> list = expr.getBooleanList(context, this);
        BooleanArrayFS array = FSCollectionFactory.createBooleanArrayFS(cas, list);
        annotation.setFeatureValue(feature, array);
      } else if (value instanceof IBooleanExpression) {
        IBooleanExpression expr = (IBooleanExpression) value;
        Boolean v = expr.getBooleanValue(context, this);
        BooleanArrayFS array = FSCollectionFactory.createBooleanArrayFS(cas, new boolean[] { v });
        annotation.setFeatureValue(feature, array);
      }
    } else if (value instanceof AnnotationTypeExpression && !range.isPrimitive()) {
      AnnotationTypeExpression ate = (AnnotationTypeExpression) value;
      if (range.isArray()) {
        List<AnnotationFS> annotations = ate.getAnnotationList(context, this);
        annotation.setFeatureValue(feature, UIMAUtils.toFSArray(getJCas(), annotations));
      } else {
        AnnotationFS a = ate.getAnnotation(context, this);
        annotation.setFeatureValue(feature, a);
      }
    } else if (value instanceof IAnnotationExpression && !range.isPrimitive()) {
      IAnnotationExpression ae = (IAnnotationExpression) value;
      boolean rangeSubsumesAnnotation = cas.getTypeSystem().subsumes(cas.getAnnotationType(),
              range);

      FeatureStructure a = null;
      if (rangeSubsumesAnnotation) {
        a = ae.getAnnotation(context, this);
      } else {
        a = ae.getFeatureStructure(context, this);
      }
      if (range.isArray()) {
        List<FeatureStructure> c = new ArrayList<>();
        c.add(a);
        annotation.setFeatureValue(feature, UIMAUtils.toFSArray(getJCas(), c));
      } else {
        annotation.setFeatureValue(feature, a);
      }
    } else if (value instanceof IAnnotationListExpression && !range.isPrimitive()) {
      IAnnotationListExpression ale = (IAnnotationListExpression) value;
      List<AnnotationFS> annotations = ale.getAnnotationList(context, this);
      if (annotations != null) {
        if (range.isArray()) {
          annotation.setFeatureValue(feature, UIMAUtils.toFSArray(getJCas(), annotations));
        } else {
          if (annotations.isEmpty()) {
            annotation.setFeatureValue(feature, null);
          } else {
            annotation.setFeatureValue(feature, annotations.get(0));
          }
        }
      } else {
        annotation.setFeatureValue(feature, null);
      }
    } else if (value instanceof ITypeExpression && !range.isPrimitive()) {
      ITypeExpression typeExpr = (ITypeExpression) value;
      Type t = typeExpr.getType(context, this);
      assignAnnotationByTypeInWindow(annotation, feature, context, t);
    } else if (value instanceof GenericFeatureExpression && !range.isPrimitive()) {
      FeatureExpression fe = ((GenericFeatureExpression) value).getFeatureExpression();
      Type t = fe.getInitialType(context, this);
      List<AnnotationFS> inWindow = getAnnotationsInWindow(context.getAnnotation(), t);
      if (fe instanceof SimpleFeatureExpression) {
        SimpleFeatureExpression sfe = (SimpleFeatureExpression) fe;
        List<? extends FeatureStructure> featureAnnotations = null;
        if (fe.getFeatures(context, this) != null) {
          featureAnnotations = new ArrayList<>(
                  sfe.getFeatureStructures(inWindow, false, context, this));
        } else {
          featureAnnotations = inWindow;
        }
        if (range.isArray()) {
          annotation.setFeatureValue(feature, UIMAUtils.toFSArray(getJCas(), featureAnnotations));
        } else if (!featureAnnotations.isEmpty()) {
          FeatureStructure a = featureAnnotations.get(0);
          annotation.setFeatureValue(feature, a);
        }
      } else {
        if (range.isArray()) {
          annotation.setFeatureValue(feature, UIMAUtils.toFSArray(getJCas(), inWindow));
        } else {
          AnnotationFS a = inWindow.get(0);
          annotation.setFeatureValue(feature, a);
        }
      }
    }
  }

  private void assignAnnotationByTypeInWindow(FeatureStructure annotation, Feature feature,
          MatchContext context, Type type) {

    List<AnnotationFS> inWindow = getAnnotationsInWindow(context.getAnnotation(), type);
    if (feature.getRange().isArray()) {
      annotation.setFeatureValue(feature, UIMAUtils.toFSArray(getJCas(), inWindow));
    } else {
      if (inWindow != null && !inWindow.isEmpty()) {
        AnnotationFS a = inWindow.get(0);
        annotation.setFeatureValue(feature, a);
      } else {
        annotation.setFeatureValue(feature, null);
      }
    }
  }

  public void assignVariable(String var, IRutaExpression expression, MatchContext context) {
    RutaBlock parent = context.getParent();
    RutaEnvironment environment = parent.getEnvironment();
    Class<?> clazz = environment.getVariableType(var);
    if (clazz.equals(Double.class) && expression instanceof INumberExpression) {
      double v = ((INumberExpression) expression).getDoubleValue(context, this);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Float.class) && expression instanceof INumberExpression) {
      float v = (float) ((INumberExpression) expression).getDoubleValue(context, this);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Integer.class) && expression instanceof INumberExpression) {
      int v = ((INumberExpression) expression).getIntegerValue(context, this);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Type.class) && expression instanceof ITypeExpression) {
      Type v = ((ITypeExpression) expression).getType(context, this);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Boolean.class) && expression instanceof IBooleanExpression) {
      boolean v = ((IBooleanExpression) expression).getBooleanValue(context, this);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(String.class) && expression instanceof IStringExpression) {
      String v = ((IStringExpression) expression).getStringValue(context, this);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(AnnotationFS.class) && expression instanceof IAnnotationExpression) {
      AnnotationFS v = ((IAnnotationExpression) expression).getAnnotation(context, this);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(List.class)) {
      Class<?> variableGenericType = environment.getVariableGenericType(var);
      if (variableGenericType.equals(AnnotationFS.class)
              && expression instanceof IAnnotationListExpression) {
        List<AnnotationFS> v = ((IAnnotationListExpression) expression).getAnnotationList(context,
                this);
        environment.setVariableValue(var, v);
      } else if (variableGenericType.equals(Boolean.class)
              && expression instanceof IBooleanListExpression) {
        List<Boolean> v = ((IBooleanListExpression) expression).getBooleanList(context, this);
        environment.setVariableValue(var, v);
      } else if (Number.class.isAssignableFrom(variableGenericType)
              && expression instanceof INumberListExpression) {
        List<Number> v = ((INumberListExpression) expression).getNumberList(context, this);
        environment.setVariableValue(var, v);
      } else if (variableGenericType.equals(String.class)
              && expression instanceof IStringListExpression) {
        List<String> v = ((IStringListExpression) expression).getStringList(context, this);
        environment.setVariableValue(var, v);
      } else if (variableGenericType.equals(Type.class)
              && expression instanceof ITypeListExpression) {
        List<Type> v = ((ITypeListExpression) expression).getTypeList(context, this);
        environment.setVariableValue(var, v);
      }
    } else if (clazz.equals(Boolean.class) && expression instanceof AnnotationTypeExpression) {
      // special not yet supported use case: b = a1==a2
      // TODO: this should be solved by having a boolean expression and an atomic feature
      // expression?
      AnnotationTypeExpression ate = (AnnotationTypeExpression) expression;
      AnnotationFS annotation = ate.getAnnotation(context, this);
      FeatureExpression featureExpression = ate.getFeatureExpression();
      if (featureExpression instanceof FeatureMatchExpression) {
        FeatureMatchExpression fme = (FeatureMatchExpression) featureExpression;
        IRutaExpression arg = fme.getArg();
        if (arg instanceof IAnnotationExpression) {
          AnnotationFS argAnnotation = ((IAnnotationExpression) arg).getAnnotation(context, this);
          if (StringUtils.equals(fme.getOp(), "==")) {
            environment.setVariableValue(var, annotation == argAnnotation);
          } else if (StringUtils.equals(fme.getOp(), "!=")) {
            environment.setVariableValue(var, annotation != argAnnotation);
          }
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  public <T extends Object> T getExpressionValue(Class<T> clazz, IRutaExpression expression,
          MatchContext context) {
    if (clazz.equals(Double.class) && expression instanceof INumberExpression) {
      double v = ((INumberExpression) expression).getDoubleValue(context, this);
      return (T) Double.valueOf(v);
    } else if (clazz.equals(Float.class) && expression instanceof INumberExpression) {
      float v = (float) ((INumberExpression) expression).getDoubleValue(context, this);
      return (T) Float.valueOf(v);
    } else if (clazz.equals(Integer.class) && expression instanceof INumberExpression) {
      int v = ((INumberExpression) expression).getIntegerValue(context, this);
      return (T) Integer.valueOf(v);
    } else if (clazz.equals(Type.class) && expression instanceof ITypeExpression) {
      Type v = ((ITypeExpression) expression).getType(context, this);
      return (T) v;
    } else if (clazz.equals(Boolean.class) && expression instanceof IBooleanExpression) {
      boolean v = ((IBooleanExpression) expression).getBooleanValue(context, this);
      return (T) Boolean.valueOf(v);
    } else if (clazz.equals(String.class) && expression instanceof IStringExpression) {
      String v = ((IStringExpression) expression).getStringValue(context, this);
      return (T) v;
    } else if (clazz.equals(AnnotationFS.class) && expression instanceof IAnnotationExpression) {
      AnnotationFS v = ((IAnnotationExpression) expression).getAnnotation(context, this);
      return (T) v;
    }
    return null;
  }

  public AnnotationFS getSingleAnnotationByTypeInContext(Type type, MatchContext context) {
    List<AnnotationFS> inWindow = getAnnotationsInWindow(context.getAnnotation(), type);
    if (inWindow != null && !inWindow.isEmpty()) {
      return inWindow.get(0);
    }
    return null;
  }

  public List<AnnotationFS> getAnnotationsByTypeInContext(Type type, MatchContext context) {
    List<AnnotationFS> inWindow = getAnnotationsInWindow(context.getAnnotation(), type);
    return inWindow;
  }

  public List<AnnotationFS> getBestGuessedAnnotationsAt(AnnotationFS window, Type type) {
    List<AnnotationFS> result = new ArrayList<>();
    if (window == null || type == null) {
      return result;
    }
    TypeSystem typeSystem = getCas().getTypeSystem();
    if (typeSystem.subsumes(type, window.getType())) {
      result.add(window);
    } else {
      Collection<AnnotationFS> beginAnchors = getBeginAnchor(window.getBegin())
              .getBeginAnchors(type);
      Collection<AnnotationFS> endAnchors = getEndAnchor(window.getEnd()).getEndAnchors(type);
      @SuppressWarnings("unchecked")
      Collection<AnnotationFS> intersection = CollectionUtils.intersection(beginAnchors,
              endAnchors);
      result.addAll(intersection);
    }
    return result;
  }

  public void changeOffsets(AnnotationFS annotation, int begin, int end,
          AbstractRuleMatch<? extends AbstractRule> modifikator) {
    if (!(annotation instanceof Annotation)) {
      return;
    }
    Annotation a = (Annotation) annotation;
    if (annotation.getBegin() == begin && annotation.getEnd() == end) {
      return;
    }
    // TODO implement incremental reindexing
    removeAnnotation(a);
    a.setBegin(begin);
    a.setEnd(end);
    addAnnotation(a, true, modifikator);
  }

  public void changeBegin(AnnotationFS annotation, int begin,
          AbstractRuleMatch<? extends AbstractRule> modifikator) {
    changeOffsets(annotation, begin, annotation.getEnd(), modifikator);
  }

  public void changeEnd(AnnotationFS annotation, int end,
          AbstractRuleMatch<? extends AbstractRule> modifikator) {
    changeOffsets(annotation, annotation.getBegin(), end, modifikator);
  }

  public AnnotationFS getVeryFirstBeforeWindow(boolean direction) {
    if (direction) {
      RutaBasic firstBasicOfAll = getFirstBasicOfAll();
      int begin = firstBasicOfAll.getBegin();
      if (begin == 0) {
        return documentBeginAnchor;
      } else {
        return getEndAnchor(begin);
      }
    } else {
      RutaBasic lastBasicOfAll = getLastBasicOfAll();
      int end = lastBasicOfAll.getEnd();
      if (end == cas.getDocumentAnnotation().getEnd()) {
        return documentEndAnchor;
      } else {
        return getBeginAnchor(end);
      }
    }
  }

  public Type getSharedParentType(List<Type> types) {
    if (types == null || types.isEmpty()) {
      return cas.getAnnotationType();
    }
    if (types.size() == 1) {
      return types.get(0);
    }
    TypeSystem typeSystem = cas.getTypeSystem();
    Type parentType = types.get(0);
    for (Type type : types) {
      parentType = getSharedParentType(parentType, type, typeSystem);
    }
    return parentType;
  }

  private Type getSharedParentType(Type type1, Type type2, TypeSystem typeSystem) {
    if (cas.getAnnotationType().equals(type1) || cas.getAnnotationType().equals(type2)) {
      return cas.getAnnotationType();
    }
    if (type1.equals(type2)) {
      return type1;
    }
    if (typeSystem.subsumes(type1, type2)) {
      return type1;
    }
    if (typeSystem.subsumes(type2, type1)) {
      return type2;
    }
    Type parentType = typeSystem.getParent(type1);
    while (parentType != null && !cas.getAnnotationType().equals(parentType)) {
      if (typeSystem.subsumes(parentType, type2)) {
        return parentType;
      }
    }

    return cas.getAnnotationType();
  }

  public RutaAnnotation getRutaAnnotationFor(AnnotationFS annotation, boolean create,
          RutaStream stream) {
    Type heuristicType = cas.getTypeSystem().getType(RutaAnnotation.class.getName());
    List<AnnotationFS> ras = CasUtil.selectAt(cas, heuristicType, annotation.getBegin(),
            annotation.getEnd());
    for (AnnotationFS each : ras) {
      if (((RutaAnnotation) each).getAnnotation() == annotation) {
        return (RutaAnnotation) each;
      }
    }
    if (create) {
      JCas jCas = stream.getJCas();
      RutaAnnotation result = new RutaAnnotation(jCas, annotation.getBegin(), annotation.getEnd());
      result.setAnnotation((Annotation) annotation);
      result.addToIndexes();
      return result;
    }
    return null;
  }

  private Collection<Type> removeSubsumedTypes(Collection<Type> types, TypeSystem typeSystem) {
    List<Type> rootTypes = new ArrayList<>(types);
    for (Type type1 : types) {
      for (Type type2 : types) {
        if (type1 != type2 && typeSystem.subsumes(type1, type2)) {
          rootTypes.remove(type2);
        }
      }
    }
    return rootTypes;
  }

  private Collection<Type> expandToAllSubtypes(Collection<Type> reindexTypeList,
          Collection<Type> reindexSkipTypes, TypeSystem typeSystem) {
    if (reindexTypeList.isEmpty()) {
      return Collections.emptyList();
    }

    Collection<Type> result = new LinkedHashSet<>();
    for (Type type : reindexTypeList) {

      if (skipTypeForIndexing(type, reindexSkipTypes, typeSystem)) {
        continue;
      }

      result.add(type);
      List<Type> properlySubsumedTypes = typeSystem.getProperlySubsumedTypes(type);
      for (Type subType : properlySubsumedTypes) {
        if (skipTypeForIndexing(subType, reindexSkipTypes, typeSystem)) {
          continue;
        }
        result.add(subType);
      }

      // if we started with uima.tcas.Annotation, we already collected all
      if (type.getName().equals(CAS.TYPE_NAME_ANNOTATION)) {
        return result;
      }
    }

    return result;
  }

  private Collection<Type> convertNamesToTypes(String[] typeNames, TypeSystem typeSystem) {
    if (typeNames == null) {
      return Collections.emptyList();
    }

    Collection<Type> result = new ArrayList<>(typeNames.length);
    for (String each : typeNames) {
      Type type = typeSystem.getType(each);
      if (type != null) {
        result.add(type);
      }
    }
    return result;
  }

  public void setMaxRuleMatches(long maxRuleMatches) {
    this.maxRuleMatches = maxRuleMatches;
  }

  public void setMaxRuleElementMatches(long maxRuleElementMatches) {
    this.maxRuleElementMatches = maxRuleElementMatches;
  }

  public long getMaxRuleMatches() {
    return maxRuleMatches;
  }

  public long getMaxRuleElementMatches() {
    return maxRuleElementMatches;
  }

}
