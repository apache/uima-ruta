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
import java.util.StringTokenizer;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class CoreMatchCasEvaluator extends AbstractCasEvaluator {

  public CAS evaluate(CAS test, CAS run, Collection<String> excludedTypes, boolean includeSubtypes, boolean useAllTypes)
          throws CASRuntimeException, CASException {
    Type annotationType = test.getAnnotationType();
    Type falsePositiveType = run.getTypeSystem().getType(ICasEvaluator.FALSE_POSITIVE);
    Type falseNegativeType = run.getTypeSystem().getType(ICasEvaluator.FALSE_NEGATIVE);
    Type truePositveType = run.getTypeSystem().getType(ICasEvaluator.TRUE_POSITIVE);

    Feature feature = falsePositiveType.getFeatureByBaseName(ICasEvaluator.ORIGINAL);
    List<Type> types = getTypes(test, excludedTypes, annotationType, useAllTypes);

    List<AnnotationFS> testAnnotations = getAnnotations(types, test, includeSubtypes);
    List<AnnotationFS> runAnnotations = getAnnotations(types, run, includeSubtypes);

    Collection<AnnotationFS> matched = new HashSet<AnnotationFS>();
    List<AnnotationFS> fp = new ArrayList<AnnotationFS>();
    List<AnnotationFS> fn = new ArrayList<AnnotationFS>();
    List<AnnotationFS> tp = new ArrayList<AnnotationFS>();

    for (AnnotationFS eachTest : testAnnotations) {
      boolean found = false;
      for (AnnotationFS eachRun : runAnnotations) {
        if (matchCore(eachTest, eachRun)) {
          matched.add(eachRun);
          found = true;
          break;
        }
      }
      if (!found) {
        AnnotationFS createAnnotation = run.createAnnotation(falseNegativeType,
                eachTest.getBegin(), eachTest.getEnd());
        Type type = run.getTypeSystem().getType(eachTest.getType().getName());
        AnnotationFS original = run.createAnnotation(type, eachTest.getBegin(), eachTest.getEnd());
        createAnnotation.setFeatureValue(feature, original);
        fn.add(createAnnotation);
      } else {
        AnnotationFS createAnnotation = run.createAnnotation(truePositveType, eachTest.getBegin(),
                eachTest.getEnd());
        Type type = run.getTypeSystem().getType(eachTest.getType().getName());
        AnnotationFS original = run.createAnnotation(type, eachTest.getBegin(), eachTest.getEnd());
        createAnnotation.setFeatureValue(feature, original);
        tp.add(createAnnotation);
      }

    }

    for (AnnotationFS each : runAnnotations) {
      if (!matched.contains(each)) {
        AnnotationFS createAnnotation = run.createAnnotation(falsePositiveType, each.getBegin(),
                each.getEnd());
        Type type = run.getTypeSystem().getType(each.getType().getName());
        AnnotationFS original = run.createAnnotation(type, each.getBegin(), each.getEnd());
        createAnnotation.setFeatureValue(feature, original);
        fp.add(createAnnotation);
      }
    }

    for (AnnotationFS annotationFS : fn) {
      run.addFsToIndexes(annotationFS);
    }
    for (AnnotationFS annotationFS : fp) {
      run.addFsToIndexes(annotationFS);
    }
    for (AnnotationFS annotationFS : tp) {
      run.addFsToIndexes(annotationFS);
    }
    return run;
  }

  private boolean matchCore(AnnotationFS a1, AnnotationFS a2) {

    String coreTerm = null;
    StringTokenizer tokenizer = new StringTokenizer(a1.getCoveredText());
    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();
      if (Character.isUpperCase(token.charAt(0)) && token.length() > 3) {
        coreTerm = token;
      }
    }

    // check, if coreTerm is contained in a2
    if (coreTerm != null && a2.getCoveredText().indexOf(coreTerm) != -1)
      return true;

    return false;
  }

}
