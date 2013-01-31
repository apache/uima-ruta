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
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIntConstraint;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FeaturePath;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.constraint.BasicTypeConstraint;
import org.apache.uima.textmarker.constraint.NotConstraint;

public class FilterManager {

  private final Collection<Type> defaultFilterTypes;

  private final FSMatchConstraint additionalWindow;

  private final AnnotationFS windowAnnotation;

  private final Type windowType;

  private Collection<Type> currentFilterTypes;

  private Collection<Type> currentRetainTypes;

  private ConstraintFactory cf;

  public FilterManager(Collection<Type> filterTypes, CAS cas) {
    super();
    this.defaultFilterTypes = filterTypes;

    currentFilterTypes = new ArrayList<Type>();
    currentRetainTypes = new ArrayList<Type>();

    cf = cas.getConstraintFactory();

    this.windowAnnotation = null;
    this.windowType = null;
    this.additionalWindow = null;
  }

  public FilterManager(Collection<Type> defaultFilterTypes, Collection<Type> filterTypes,
          Collection<Type> retainTypes, AnnotationFS windowAnnotation, Type windowType, CAS cas) {
    super();
    this.defaultFilterTypes = defaultFilterTypes;

    currentFilterTypes = new ArrayList<Type>(filterTypes);
    currentRetainTypes = new ArrayList<Type>(retainTypes);

    cf = cas.getConstraintFactory();

    this.windowAnnotation = windowAnnotation;
    this.windowType = windowType;
    this.additionalWindow = createWindowConstraint(windowAnnotation, cas);
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

    FSMatchConstraint typeConstraint = createTypeConstraint(filterTypes);

    FSMatchConstraint constraint = new NotConstraint(typeConstraint);
    if (additionalWindow != null && windowConstraint) {
      constraint = cf.and(additionalWindow, constraint);
    }
    return constraint;
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

  public Collection<Type> getDefaultFilterTypes() {
    return defaultFilterTypes;
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
      return iterator;
    } else {
      FSIterator<AnnotationFS> iterator = cas.createFilteredIterator(
              cas.getAnnotationIndex(basicType).iterator(), createCurrentConstraint(false));
      return iterator;
    }
  }

}
