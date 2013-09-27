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

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;

public class TrabalRuleItem {
  private TrabalAnnotation annotation;

  public TrabalRuleItem(TrabalAnnotation annotation) {
    this.annotation = annotation;
  }

  public TrabalRuleItem(int begin, int end, CAS cas, boolean enableFeature) {
    Type type = cas.getTypeSystem().getType(TrabalLearner.ANNOTATION_TYPE_ANY);
    this.annotation = new TrabalAnnotation(cas.createAnnotation(type, begin, end), enableFeature);
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass().isInstance(this)) {
      TrabalRuleItem obj = (TrabalRuleItem) o;
      return annotation.equals(obj.getAnnotation());
    }
    return false;
  }

  public TrabalAnnotation getAnnotation() {
    return annotation;
  }

  public String getName() {
    return annotation.getType().getShortName();
  }

  public String toString() {
    return annotation.getType().getShortName();
  }

  public int hashCode() {
    return getAnnotation().getType().toString().hashCode()
            + getAnnotation().getCoveredText().hashCode();
  }
}