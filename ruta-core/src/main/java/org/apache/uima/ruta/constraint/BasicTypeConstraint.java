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

package org.apache.uima.ruta.constraint;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.NotImplementedException;
import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.ruta.type.RutaBasic;

public class BasicTypeConstraint implements FSTypeConstraint {
  private static final long serialVersionUID = 1115953538613617791L;


  private final Collection<Type> types;

  public BasicTypeConstraint(Collection<Type> types) {
    super();
    this.types = types;
  }

  public BasicTypeConstraint(Type type) {
    super();
    if (type != null) {
      this.types = new ArrayList<Type>();
      this.types.add(type);
    } else {
      this.types = null;
    }
  }

  public void add(Type type) {
    types.add(type);
  }

  public void add(String typeString) {
    throw new NotImplementedException();
  }
  

  public boolean match(FeatureStructure fs) {
    boolean result = false;
    if (fs instanceof RutaBasic) {
      RutaBasic tmb = (RutaBasic) fs;
      if(tmb.isEmpty()) {
        return true;
      }
      if (types != null) {
        for (Type each : types) {
          result |= tmb.isPartOf(each);
          if (result) {
            return true;
          }
        }
      }
    } 
    return result;
  }

  @Override
  public String toString() {
    return "(BASIC " +  " with " + types + ")";
  }



}
