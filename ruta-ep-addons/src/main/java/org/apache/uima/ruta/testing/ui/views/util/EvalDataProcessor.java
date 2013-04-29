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

package org.apache.uima.textmarker.testing.ui.views.util;

import java.util.Collection;
import java.util.HashMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.textmarker.testing.evaluator.ICasEvaluator;
import org.apache.uima.textmarker.testing.ui.views.TestCasData;
import org.apache.uima.textmarker.testing.ui.views.evalDataTable.TypeEvalData;


public class EvalDataProcessor {

  public static void calculateEvaluatData(TestCasData data, CAS resultCas) {
    data.setEvaluationStatus(true);
    TypeSystem ts = resultCas.getTypeSystem();
    Type falsePositiveType = ts.getType(ICasEvaluator.FALSE_POSITIVE);
    Type falseNegativeType = ts.getType(ICasEvaluator.FALSE_NEGATIVE);
    Type truePositiveType = ts.getType(ICasEvaluator.TRUE_POSITIVE);

    Feature originalFeature = truePositiveType.getFeatureByBaseName("original");

    int falsePositiveCount = resultCas.getAnnotationIndex(falsePositiveType).size();
    int falseNegativeCount = resultCas.getAnnotationIndex(falseNegativeType).size();
    int truePositiveCount = resultCas.getAnnotationIndex(truePositiveType).size();

    data.setTruePositiveCount(truePositiveCount);
    data.setFalsePositiveCount(falsePositiveCount);
    data.setFalseNegativeCount(falseNegativeCount);

    HashMap<String, TypeEvalData> map = new HashMap<String, TypeEvalData>();

    map.put(" Total", new TypeEvalData("Total", truePositiveCount, falsePositiveCount,
            falseNegativeCount));

    AnnotationIndex<AnnotationFS> index = resultCas.getAnnotationIndex(truePositiveType);

    FSIterator iter = index.iterator();

    if (originalFeature != null) {

      while (iter.isValid()) {
        AnnotationFS a = (AnnotationFS) iter.next();

        FeatureStructure fs = a.getFeatureValue(originalFeature);
        String typeName = fs.getType().getName();

        if (map.containsKey(typeName)) {
          TypeEvalData element = map.get(typeName);
          int oldCount = element.getTruePositives();
          element.setTruePositives(oldCount + 1);
        } else {
          TypeEvalData newData = new TypeEvalData(typeName, 1, 0, 0);
          map.put(typeName, newData);
        }
      }

      index = resultCas.getAnnotationIndex(falsePositiveType);
      iter = index.iterator();

      while (iter.isValid()) {
        AnnotationFS a = (AnnotationFS) iter.next();

        FeatureStructure fs = a.getFeatureValue(originalFeature);
        String typeName = fs.getType().getName();

        if (map.containsKey(typeName)) {
          TypeEvalData element = map.get(typeName);
          int oldCount = element.getFalsePositives();
          element.setFalsePositives(oldCount + 1);
        } else {
          TypeEvalData newData = new TypeEvalData(typeName, 0, 1, 0);
          map.put(typeName, newData);
        }
      }

      index = resultCas.getAnnotationIndex(falseNegativeType);
      iter = index.iterator();

      while (iter.isValid()) {
        AnnotationFS a = (AnnotationFS) iter.next();

        FeatureStructure fs = a.getFeatureValue(originalFeature);
        String typeName = fs.getType().getName();

        if (map.containsKey(typeName)) {
          TypeEvalData element = map.get(typeName);
          int oldCount = element.getFalseNegatives();
          element.setFalseNegatives(oldCount + 1);
        } else {
          TypeEvalData newData = new TypeEvalData(typeName, 0, 0, 1);
          map.put(typeName, newData);
        }
      }

      data.setTypeEvalData(map);

      Collection<TypeEvalData> col = map.values();
      for (TypeEvalData typeEvalData : col) {
        typeEvalData.calcFOne();
      }

      return;
    }
  }
}
