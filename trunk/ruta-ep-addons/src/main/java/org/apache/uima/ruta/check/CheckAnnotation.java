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

package org.apache.uima.ruta.check;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class CheckAnnotation extends CheckElement {

  private int begin;

  private int end;

  private String typeName;

  private String coveredText;

  private String shortTypeName;

  private Map<Feature, String> features;

  public CheckAnnotation(AnnotationFS afs) {
    super();
    begin = afs.getBegin();
    end = afs.getEnd();
    typeName = afs.getType().getName();
    shortTypeName = afs.getType().getShortName();
    coveredText = afs.getCoveredText();
    features = new HashMap<Feature, String>();
    for (Feature feat : afs.getType().getFeatures()) {
      try {
        features.put(feat, afs.getFeatureValueAsString(feat));
      } catch (Exception e) {
        continue;
      }
    }
  }

  public AnnotationFS toAnnotationFS(CAS cas, Type type) {
    AnnotationFS afs = cas.createAnnotation(type, begin, end);
    for (Entry<Feature, String> entry : features.entrySet()) {
      afs.setFeatureValueFromString(entry.getKey(), entry.getValue());
    }
    return afs;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String type) {
    this.typeName = type;
  }

  public int getBegin() {
    return begin;
  }

  public void setBegin(int begin) {
    this.begin = begin;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  public String getCoveredText() {
    return coveredText;
  }

  public void setCoveredText(String coveredText) {
    this.coveredText = coveredText;
  }

  public String getShortType() {
    return shortTypeName;
  }

  public void setShortType(String shortType) {
    this.shortTypeName = shortType;
  }

  public Map<Feature, String> getFeatures() {
    return features;
  }

  public void setFeatures(Map<Feature, String> features) {
    this.features = features;
  }

}
