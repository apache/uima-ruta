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

package org.apache.uima.ruta.testing.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.engine.RutaEngine;

public class StringFeatureCasEvaluator extends AbstractCasEvaluator {
  
  public CAS evaluate(CAS test, CAS run, Collection<String> excludedTypes, boolean includeSubtypes, boolean useAllTypes)
          throws CASRuntimeException, CASException {
    Type falsePositiveType = run.getTypeSystem().getType(ICasEvaluator.FALSE_POSITIVE);
    Type falseNegativeType = run.getTypeSystem().getType(ICasEvaluator.FALSE_NEGATIVE);
    Type truePositveType = run.getTypeSystem().getType(ICasEvaluator.TRUE_POSITIVE);
    Feature feature = falsePositiveType.getFeatureByBaseName(ICasEvaluator.ORIGINAL);
    List<Type> allTypes = test.getTypeSystem().getProperlySubsumedTypes(
            test.getTypeSystem().getTopType());
    List<Type> types = new ArrayList<Type>();
    Type stringType = run.getTypeSystem().getType(CAS.TYPE_NAME_STRING);
    Type basicType = run.getTypeSystem().getType(RutaEngine.BASIC_TYPE);

    TypeSystem typeSystem = test.getTypeSystem();
    Type annotationType = test.getAnnotationType();
    for (Type eachType : allTypes) {
      if (!excludedTypes.contains(eachType.getName())
              && !eachType.getName().equals(test.getDocumentAnnotation().getType().getName())) {
        List<Feature> features = eachType.getFeatures();
        for (Feature f : features) {
          Type range = f.getRange();
          if (typeSystem.subsumes(annotationType, range) || typeSystem.subsumes(stringType, range)) {
            if (!eachType.getName().startsWith("org.apache.uima.ruta.type")
                    && !typeSystem.subsumes(basicType, eachType)) {
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
//      CAS runCas = newFS.getCAS();
      TypeSystem testTS = testCas.getTypeSystem();
//      TypeSystem runTS = runCas.getTypeSystem();
      Type stringType = testTS.getType(CAS.TYPE_NAME_STRING);
      List<Feature> features = fs.getType().getFeatures();
      for (Feature feature : features) {
        Type range = feature.getRange();
        if (testTS.subsumes(stringType, range)) {
          String valueTest = fs.getStringValue(feature);
          Feature feature2 = newFS.getType().getFeatureByBaseName(feature.getShortName());
          newFS.setStringValue(feature2, valueTest);
        }
      }
    }
  }

  private Collection<FeatureStructure> getFeatureStructures(List<Type> types, CAS cas) {
    TypeSystem typeSystem = cas.getTypeSystem();
    Type stringType = typeSystem.getType(CAS.TYPE_NAME_STRING);
    Collection<FeatureStructure> result = new HashSet<FeatureStructure>();
    AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex();
    for (AnnotationFS each : annotationIndex) {
      Type type = each.getType();
      for (Type eachType : types) {
        if(typeSystem.subsumes(eachType, type)) {
          List<Feature> features = each.getType().getFeatures();
          for (Feature feature : features) {
            Type range = feature.getRange();
            if (typeSystem.subsumes(stringType, range)) {
              result.add(each);
              break;
            }
          }
        }
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
    Type stringType = typeSystem.getType(CAS.TYPE_NAME_STRING);
    List<Feature> features1 = type1.getFeatures();
    boolean result = true;
    boolean allEmpty1 = true;
    boolean allEmpty2 = true;
    for (Feature eachFeature1 : features1) {
      Type range = eachFeature1.getRange();
      if (typeSystem.subsumes(stringType, range)) {
        String name = eachFeature1.getShortName();
        Feature eachFeature2 = type2.getFeatureByBaseName(name);
        String featureValue1 = a1.getStringValue(eachFeature1);
        String featureValue2 = a2.getStringValue(eachFeature2);
        allEmpty1 &= featureValue1 == null;
        allEmpty2 &= featureValue2 == null;
        if (featureValue1 != null) {
          featureValue1 = featureValue1.replaceAll("\\d", "").replaceAll("\\W", "");
        }
        if (featureValue2 != null) {
          featureValue2 = featureValue2.replaceAll("\\d", "").replaceAll("\\W", "");
        }
        result &= (featureValue1 == null && featureValue2 == null)
                || featureValue1.equals(featureValue2);

      }
    }
    return result && (allEmpty1 == allEmpty2);
  }

}
