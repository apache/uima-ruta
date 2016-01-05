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
package org.apache.uima.ruta.expression.feature;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class LazyFeature implements Feature {

  private Feature delegate;
  
  private String featureName;
  
  public LazyFeature(String featureName) {
    super();
    this.featureName = featureName;
  }
  
  public Feature initialize(Type type) {
   delegate = type.getFeatureByBaseName(featureName);
   return delegate;
  }
  
  public Feature initialize(AnnotationFS annotation) {
    if(annotation!= null) {
      delegate = annotation.getType().getFeatureByBaseName(featureName);
    } else {
      delegate = null;
    }
    return delegate;
   }
  
  @Override
  public int compareTo(Feature o) {
    return delegate.compareTo(o);
  }

  @Override
  public Type getDomain() {
    return delegate.getDomain();
  }

  @Override
  public Type getRange() {
    return delegate.getRange();
  }

  @Override
  public String getName() {
    return delegate.getName();
  }

  @Override
  public String getShortName() {
    return delegate.getShortName();
  }

  @Override
  public boolean isMultipleReferencesAllowed() {
    return delegate.isMultipleReferencesAllowed();
  }

}
