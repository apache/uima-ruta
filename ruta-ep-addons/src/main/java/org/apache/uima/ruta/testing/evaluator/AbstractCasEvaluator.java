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
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;

public abstract class AbstractCasEvaluator implements ICasEvaluator {

  protected List<AnnotationFS> getAnnotations(List<Type> types, CAS cas) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    TypeSystem typeSystem = cas.getTypeSystem();
    AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex();
    for (AnnotationFS each : annotationIndex) {
      Type type = each.getType();
      for (Type eachType : types) {
        if(typeSystem.subsumes(eachType, type)) {
          result.add(each);
          break;
        }
      }
    }
    return result;
  }

  protected boolean match(AnnotationFS a1, AnnotationFS a2) {
    if (a1 != null && a2 != null) {
      if (a1.getBegin() == a2.getBegin() && a1.getEnd() == a2.getEnd()
              && a1.getType().getName().equals(a2.getType().getName()))
        return true;
    }
    return false;
  }

}
