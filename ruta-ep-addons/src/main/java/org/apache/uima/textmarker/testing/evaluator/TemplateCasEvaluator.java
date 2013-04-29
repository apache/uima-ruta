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

package org.apache.uima.textmarker.testing.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;

public class TemplateCasEvaluator implements ICasEvaluator {

  public CAS evaluate(CAS test, CAS run, Collection<String> excludedTypes)
          throws CASRuntimeException, CASException {
    Type falsePositiveType = run.getTypeSystem().getType(ICasEvaluator.FALSE_POSITIVE);
    Type falseNegativeType = run.getTypeSystem().getType(ICasEvaluator.FALSE_NEGATIVE);
    Type truePositveType = run.getTypeSystem().getType(ICasEvaluator.TRUE_POSITIVE);
    Feature feature = falsePositiveType.getFeatureByBaseName(ICasEvaluator.ORIGINAL);
    List<Type> allTypes = test.getTypeSystem().getProperlySubsumedTypes(
            test.getTypeSystem().getTopType());
    List<Type> types = new ArrayList<Type>();

    TypeSystem typeSystem = test.getTypeSystem();
    Type annotationType = test.getAnnotationType();
    for (Type eachType : allTypes) {
      if (!excludedTypes.contains(eachType.getName())) {
        List<Feature> features = eachType.getFeatures();
        for (Feature f : features) {
          Type range = f.getRange();
          if (typeSystem.subsumes(annotationType, range)) {
            if (!eachType.getName().startsWith("org.apache.uima.textmarker.type")) {
              types.add(eachType);
              break;
            }
          }
        }
      }
    }

    Collection<FeatureStructure> testFSs = getFeatureStructures(types, test);
    Collection<FeatureStructure> runFSs = getFeatureStructures(types, run);

    Collection<FeatureStructure> matched = new HashSet<FeatureStructure>();
    List<FeatureStructure> fp = new ArrayList<FeatureStructure>();
    List<FeatureStructure> fn = new ArrayList<FeatureStructure>();
    List<FeatureStructure> tp = new ArrayList<FeatureStructure>();

    for (FeatureStructure eachTest : testFSs) {
      boolean found = false;
      for (FeatureStructure eachRun : runFSs) {
        if (match(eachTest, eachRun)) {
          matched.add(eachRun);
          found = true;
          break;
        }
      }
      if (!found) {
        FeatureStructure createFS = run.createFS(falseNegativeType);
        fillFS(eachTest, createFS, false);
        Type type = run.getTypeSystem().getType(eachTest.getType().getName());
        FeatureStructure original = run.createFS(type);
        fillFS(eachTest, original, true);
        createFS.setFeatureValue(feature, original);
        fn.add(createFS);
      } else {
        FeatureStructure createFS = run.createFS(truePositveType);
        fillFS(eachTest, createFS, false);
        Type type = run.getTypeSystem().getType(eachTest.getType().getName());
        FeatureStructure original = run.createFS(type);
        fillFS(eachTest, original, true);
        createFS.setFeatureValue(feature, original);
        tp.add(createFS);
      }
    }

    for (FeatureStructure each : runFSs) {
      if (!matched.contains(each)) {
        FeatureStructure createFS = run.createFS(falsePositiveType);
        fillFS(each, createFS, false);
        Type type = run.getTypeSystem().getType(each.getType().getName());
        FeatureStructure original = run.createFS(type);
        fillFS(each, original, true);
        createFS.setFeatureValue(feature, original);
        fp.add(createFS);
      }
    }

    for (FeatureStructure fs : fn) {
      run.addFsToIndexes(fs);
    }
    for (FeatureStructure fs : fp) {
      run.addFsToIndexes(fs);
    }
    for (FeatureStructure fs : tp) {
      run.addFsToIndexes(fs);
    }
    return run;
  }

  private void fillFS(FeatureStructure fs, FeatureStructure newFS, boolean withFeatures) {
    if (fs instanceof AnnotationFS) {
      Annotation a = (Annotation) newFS;
      a.setBegin(((AnnotationFS) fs).getBegin());
      a.setEnd(((AnnotationFS) fs).getEnd());
    }
    if (withFeatures) {
      CAS testCas = fs.getCAS();
      CAS runCas = newFS.getCAS();
      TypeSystem testTS = testCas.getTypeSystem();
      TypeSystem runTS = runCas.getTypeSystem();
      Type annotationType = testCas.getAnnotationType();
      List<Feature> features = fs.getType().getFeatures();
      for (Feature feature : features) {
        Type range = feature.getRange();
        if (testTS.subsumes(annotationType, range)) {
          FeatureStructure valueTest = fs.getFeatureValue(feature);
          if (valueTest instanceof AnnotationFS) {
            AnnotationFS a1 = (AnnotationFS) valueTest;
            Feature feature2 = newFS.getType().getFeatureByBaseName(feature.getShortName());
            if (feature != null) {
              Type range2 = runTS.getType(range.getName());
              AnnotationFS createAnnotation = runCas.createAnnotation(range2, a1.getBegin(),
                      a1.getEnd());
              newFS.setFeatureValue(feature2, createAnnotation);
            }
          }
        }
      }
    }
  }

  private Collection<FeatureStructure> getFeatureStructures(List<Type> types, CAS cas) {
    TypeSystem typeSystem = cas.getTypeSystem();
    Type annotationType = cas.getAnnotationType();
    Collection<FeatureStructure> result = new HashSet<FeatureStructure>();
    for (Type type : types) {
      FSIterator<FeatureStructure> iterator = cas.getIndexRepository().getAllIndexedFS(type);
      while (iterator.isValid()) {
        FeatureStructure fs = iterator.get();
        List<Feature> features = fs.getType().getFeatures();
        for (Feature feature : features) {
          Type range = feature.getRange();
          if (typeSystem.subsumes(annotationType, range)) {
            result.add(fs);
            break;
          }
        }
        iterator.moveToNext();
      }
    }
    return result;
  }

  private boolean match(FeatureStructure a1, FeatureStructure a2) {
    Type type1 = a1.getType();
    Type type2 = a2.getType();
    if (!type1.getName().equals(type2.getName())) {
      return false;
    }

    if (a1 instanceof AnnotationFS && a2 instanceof AnnotationFS) {
      AnnotationFS a11 = (AnnotationFS) a1;
      AnnotationFS a22 = (AnnotationFS) a2;
      if (!(a11.getBegin() == a22.getBegin() && a11.getEnd() == a22.getEnd())) {
        return false;
      }
    }
    CAS cas = a1.getCAS();
    TypeSystem typeSystem = cas.getTypeSystem();
    Type annotationType = cas.getAnnotationType();
    List<Feature> features1 = type1.getFeatures();
    boolean result = true;
    boolean allEmpty1 = true;
    boolean allEmpty2 = true;
    for (Feature eachFeature1 : features1) {
      Type range = eachFeature1.getRange();
      if (typeSystem.subsumes(annotationType, range)) {
        String name = eachFeature1.getShortName();
        Feature eachFeature2 = type2.getFeatureByBaseName(name);
        FeatureStructure featureValue1 = a1.getFeatureValue(eachFeature1);
        FeatureStructure featureValue2 = a2.getFeatureValue(eachFeature2);
        allEmpty1 &= featureValue1 == null;
        allEmpty2 &= featureValue2 == null;
        if (featureValue1 instanceof AnnotationFS && featureValue2 instanceof AnnotationFS) {
          result &= matchAnnotations((AnnotationFS) featureValue1, (AnnotationFS) featureValue2);
        } else if (featureValue1 == null) {
          // nothing to do
        } else if (featureValue1 != null || featureValue2 != null) {
          return false;
        }
      }
    }
    return result && (allEmpty1 == allEmpty2);
  }

  private boolean matchAnnotations(AnnotationFS a1, AnnotationFS a2) {
    if (a1 != null && a2 != null) {
      if (a1.getBegin() == a2.getBegin() && a1.getEnd() == a2.getEnd()
              && a1.getType().getName().equals(a2.getType().getName())) {
        return true;
      }
    }
    return false;
  }

}
