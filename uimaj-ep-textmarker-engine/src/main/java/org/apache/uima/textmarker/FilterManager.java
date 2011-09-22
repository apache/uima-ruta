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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIntConstraint;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.FeaturePath;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.constraint.BasicTypeConstraint;
import org.apache.uima.textmarker.constraint.MarkupConstraint;
import org.apache.uima.textmarker.constraint.NotConstraint;
import org.apache.uima.textmarker.type.MARKUP;

public class FilterManager {

  private final Collection<Type> defaultFilterTypes;

  private final Collection<String> defaultRetainTags;

  private final FSMatchConstraint additionalWindow;

  private final AnnotationFS windowAnnotation;

  private final Type windowType;

  private Collection<Type> currentFilterTypes;

  private Collection<Type> currentRetainTypes;

  private Collection<String> currentFilterTags;

  private Collection<String> currentRetainTags;

  private ConstraintFactory cf;

  private Type markupType;

  public FilterManager(Collection<Type> filterTypes, Collection<String> filterTags, CAS cas) {
    super();
    this.defaultFilterTypes = filterTypes;
    this.defaultRetainTags = filterTags;

    currentFilterTypes = new ArrayList<Type>();
    currentRetainTypes = new ArrayList<Type>();
    currentFilterTags = new ArrayList<String>();
    currentRetainTags = new ArrayList<String>();

    cf = cas.getConstraintFactory();

    this.windowAnnotation = null;
    this.windowType = null;
    this.additionalWindow = null;
    try {
      markupType = cas.getJCas().getCasType(MARKUP.type);
    } catch (CASException e) {
      e.printStackTrace();
    }
  }

  public FilterManager(Collection<Type> defaultFilterTypes, Collection<String> defaultFilterTags,
          Collection<Type> filterTypes, Collection<Type> retainTypes,
          Collection<String> filterTags, Collection<String> retainTags,
          AnnotationFS windowAnnotation, Type windowType, CAS cas) {
    super();
    this.defaultFilterTypes = defaultFilterTypes;
    this.defaultRetainTags = defaultFilterTags;

    currentFilterTypes = new ArrayList<Type>(filterTypes);
    currentRetainTypes = new ArrayList<Type>(retainTypes);
    currentFilterTags = new ArrayList<String>(filterTags);
    currentRetainTags = new ArrayList<String>(retainTags);

    cf = cas.getConstraintFactory();

    this.windowAnnotation = windowAnnotation;
    this.windowType = windowType;
    this.additionalWindow = createWindowConstraint(windowAnnotation, cas);
    try {
      markupType = cas.getJCas().getCasType(MARKUP.type);
    } catch (CASException e) {
      e.printStackTrace();
    }
  }

  private FSMatchConstraint createWindowConstraint(AnnotationFS windowAnnotation, CAS cas) {
    if (windowAnnotation == null)
      return null;
    FeaturePath beginFP = cas.createFeaturePath();
    Type type = windowAnnotation.getType();
    beginFP.addFeature(type.getFeatureByBaseName("begin"));
    FSIntConstraint intConstraint = cf.createIntConstraint();
    intConstraint.geq(windowAnnotation.getBegin());
    FSMatchConstraint beginConstraint = cf.embedConstraint(beginFP, intConstraint);

    FeaturePath endFP = cas.createFeaturePath();
    endFP.addFeature(type.getFeatureByBaseName("end"));
    intConstraint = cf.createIntConstraint();
    intConstraint.leq(windowAnnotation.getEnd());
    FSMatchConstraint endConstraint = cf.embedConstraint(endFP, intConstraint);

    FSMatchConstraint windowConstraint = cf.and(beginConstraint, endConstraint);
    return windowConstraint;
  }

  public FSMatchConstraint getDefaultConstraint() {
    return createCurrentConstraint(true);
  }

  private FSMatchConstraint createCurrentConstraint(boolean windowConstraint) {
    Set<Type> filterTypes = new HashSet<Type>();
    filterTypes.addAll(defaultFilterTypes);
    filterTypes.addAll(currentFilterTypes);
    filterTypes.removeAll(currentRetainTypes);

    Set<String> retainTags = new HashSet<String>();
    retainTags.addAll(defaultRetainTags);
    retainTags.addAll(currentRetainTags);
    retainTags.removeAll(currentFilterTags);

    FSMatchConstraint typeConstraint = createTypeConstraint(filterTypes);
    FSMatchConstraint markupConstraint = createTagConstraint(retainTags);

    FSMatchConstraint constraint = cf.or(new NotConstraint(typeConstraint), markupConstraint);
    if (additionalWindow != null && windowConstraint) {
      constraint = cf.and(additionalWindow, constraint);
    }
    return constraint;
  }

  private FSMatchConstraint createTagConstraint(Collection<String> tags) {
    FSTypeConstraint constraint = cf.createTypeConstraint();
    constraint.add(markupType);
    MarkupConstraint result = new MarkupConstraint(constraint);
    for (String string : tags) {
      result.addTag(string);
    }
    return result;
  }

  private FSMatchConstraint createTypeConstraint(Collection<Type> types) {
    BasicTypeConstraint result = new BasicTypeConstraint(cf.createTypeConstraint(), types);
    for (Type each : types) {
      result.add(each);
    }
    return result;
  }

  public void retainTypes(List<Type> list) {
    currentRetainTypes = list;
  }

  public void filterTypes(List<Type> list) {
    currentFilterTypes = list;
  }

  public void retainTags(List<String> list) {
    currentRetainTags = list;
  }

  public void filterTags(List<String> list) {
    currentFilterTags = list;
  }

  public Collection<Type> getDefaultFilterTypes() {
    return defaultFilterTypes;
  }

  public Collection<String> getDefaultRetainTags() {
    return defaultRetainTags;
  }

  public FSMatchConstraint getAdditionalWindow() {
    return additionalWindow;
  }

  public Collection<Type> getCurrentFilterTypes() {
    return currentFilterTypes;
  }

  public Collection<Type> getCurrentRetainTypes() {
    return currentRetainTypes;
  }

  public Collection<String> getCurrentFilterTags() {
    return currentFilterTags;
  }

  public Collection<String> getCurrentRetainTags() {
    return currentRetainTags;
  }

  public AnnotationFS getWindowAnnotation() {
    return windowAnnotation;
  }

  public Type getWindowType() {
    return windowType;
  }

  public FSIterator<AnnotationFS> createFilteredIterator(CAS cas, Type basicType) {
    if (windowAnnotation != null) {
      FSIterator<AnnotationFS> windowIt = cas.getAnnotationIndex(basicType).subiterator(
              windowAnnotation);
      FSIterator<AnnotationFS> iterator = cas.createFilteredIterator(windowIt,
              createCurrentConstraint(false));
      // FSIterator<AnnotationFS> iterator = cas.createFilteredIterator(basic,
      // createCurrentConstraint(false));
      return iterator;
    } else {
      // FSIterator<AnnotationFS> iterator = cas.createFilteredIterator(basic,
      // createCurrentConstraint(false));
      FSIterator<AnnotationFS> iterator = cas.createFilteredIterator(
              cas.getAnnotationIndex(basicType).iterator(), createCurrentConstraint(false));
      return iterator;
    }
  }

}
