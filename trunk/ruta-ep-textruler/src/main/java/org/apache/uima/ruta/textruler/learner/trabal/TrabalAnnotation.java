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

package org.apache.uima.ruta.textruler.learner.trabal;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.textruler.core.TextRulerAnnotation;
import org.apache.uima.ruta.textruler.core.TextRulerExampleDocument;

public class TrabalAnnotation extends TextRulerAnnotation {
  private Map<String, String> features;

  private boolean enableFeatures;

  public TrabalAnnotation(AnnotationFS afs, boolean enableFeatures) {
    super(afs);
    if (afs.getType().getFeatures() != null) {
      features = new HashMap<String, String>();
      this.enableFeatures = enableFeatures;
      for (Feature f : afs.getType().getFeatures()) {
        try {
          if (!TrabalLearner.FILTERED_FEATURES.contains(f.getShortName()))
            features.put(f.getShortName(), afs.getFeatureValueAsString(f));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public TrabalAnnotation(AnnotationFS afs, TextRulerExampleDocument document,
          boolean enableFeatures) {
    super(afs, document);
    if (afs.getType().getFeatures() != null) {
      features = new HashMap<String, String>();
      this.enableFeatures = enableFeatures;
      for (Feature f : afs.getType().getFeatures()) {
        try {
          if (!TrabalLearner.FILTERED_FEATURES.contains(f.getShortName()))
            features.put(f.getShortName(), afs.getFeatureValueAsString(f));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public Map<String, String> getFeatures() {
    return features;
  }

  @Override
  public String toString() {
    String result = getType().getShortName();
    result += "(" + getBegin() + ", " + getEnd() + ")";
    if (features != null) {
      result += "[";
      for (String key : features.keySet()) {
        result += key + ":" + features.get(key) + ", ";
      }
      result = result.substring(0, result.length() - 2);
      result += "]";
    }
    result += " \"" + getCoveredText() + "\"";
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass().isInstance(this)) {
      TrabalAnnotation obj = (TrabalAnnotation) o;
      return getBegin() == obj.getBegin() && getEnd() == obj.getEnd()
              && getType().getName().equals(obj.getType().getName()) && hasEqualFeatures(obj);
    }
    return false;
  }

  public boolean hasEqualFeatures(TrabalAnnotation obj) {
    if (enableFeatures) {
      if (features != null && obj.getFeatures() != null) {
        if (features.size() != obj.getFeatures().size())
          return false;
        for (String key : features.keySet()) {
          if (!obj.getFeatures().containsKey(key))
            return false;
          if (features.get(key) != null && obj.getFeatures().get(key) != null)
            if (!features.get(key).equals(obj.getFeatures().get(key)))
              return false;
        }
      } else {
        if (this.features != null || obj.getFeatures() != null)
          return false;
      }
    }
    return true;
  }
}
