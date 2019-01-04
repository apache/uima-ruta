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

public class CoveredTextFeature implements Feature {

  public CoveredTextFeature() {
    super();
  }
  
  @Override
  public int compareTo(Feature o) {
    return 0;
  }

  @Override
  public Type getDomain() {
    return null;
  }

  @Override
  public Type getRange() {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String getShortName() {
    return null;
  }

  @Override
  public boolean isMultipleReferencesAllowed() {
    return false;
  }

}
