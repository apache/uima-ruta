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
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;

public class LazyFeature implements Feature {

  private Feature delegate;
  
  private String featureName;
  
  public LazyFeature(String featureName) {
    super();
    this.featureName = featureName;
  }
  
  public Feature initialize(Type type) {
   delegate = type.getFeatureByBaseName(featureName);
   if(delegate == null) {
     return this;
   }
   return delegate;
  }
  
  public Feature initialize(FeatureStructure featureStructure) {
    return initialize(featureStructure.getType());
   }
  
  @Override
  public int compareTo(Feature o) {
    checkDelegate();
    return delegate.compareTo(o);
  }

  @Override
  public Type getDomain() {
    checkDelegate();
    return delegate.getDomain();
  }

  @Override
  public Type getRange() {
    checkDelegate();
    return delegate.getRange();
  }

  @Override
  public String getName() {
    checkDelegate();
    return delegate.getName();
  }

  @Override
  public String getShortName() {
    checkDelegate();
    return delegate.getShortName();
  }

  @Override
  public boolean isMultipleReferencesAllowed() {
    checkDelegate();
    return delegate.isMultipleReferencesAllowed();
  }

  public String getFeatureName() {
    return featureName;
  }

  private void checkDelegate() {
    if(delegate == null) {
      throw new RuntimeException("Feature with name '"+ featureName +"' has not yet been resolved. Most likely, it is not defined for the given type.");
    }
    
  }

}
