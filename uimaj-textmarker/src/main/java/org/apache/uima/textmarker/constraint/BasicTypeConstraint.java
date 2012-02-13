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

package org.apache.uima.textmarker.constraint;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.textmarker.type.TextMarkerBasic;

public class BasicTypeConstraint implements FSTypeConstraint {
  private static final long serialVersionUID = 1115953538613617791L;

  private final FSTypeConstraint constraint;

  private final Collection<Type> types;

  public BasicTypeConstraint(FSTypeConstraint constraint, Collection<Type> types) {
    super();
    this.constraint = constraint;
    this.types = types;
  }

  public BasicTypeConstraint(FSTypeConstraint constraint, Type type) {
    super();
    this.constraint = constraint;
    if (type != null) {
      this.types = new ArrayList<Type>();
      this.types.add(type);
    } else {
      this.types = null;
    }
  }

  public void add(Type type) {
    constraint.add(type);
  }

  public boolean match(FeatureStructure fs) {
    boolean matched = constraint.match(fs);
    if (matched)
      return true;
    boolean result = false;
    if (fs instanceof TextMarkerBasic) {
      TextMarkerBasic tmb = (TextMarkerBasic) fs;
      if (types != null) {
        for (Type each : types) {
          result |= tmb.isPartOf(each)|| tmb.beginsWith(each) || tmb.endsWith(each);
          if (result)
            break;
        }
      }
    }
    return result;
  }

  @Override
  public String toString() {
    return "(BASIC " + constraint.toString() + " with " + types + ")";
  }

  public void add(String type) {
    constraint.add(type);
  }
}
