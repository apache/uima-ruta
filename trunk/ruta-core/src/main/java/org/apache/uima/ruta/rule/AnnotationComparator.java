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

package org.apache.uima.ruta.rule;

import java.util.Comparator;

import org.apache.uima.cas.text.AnnotationFS;

/**
 * Comparator for annotations following mostly the default order.
 * 
 */
public class AnnotationComparator implements Comparator<AnnotationFS> {

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  public int compare(AnnotationFS o1, AnnotationFS o2) {
    if (o1.getBegin() < o2.getBegin()) {
      return -1;
    } else if (o1.getBegin() > o2.getBegin()) {
      return 1;
    } else if (o1.getEnd() > o2.getEnd()) {
      return -1;
    } else if (o1.getEnd() < o2.getEnd()) {
      return 1;
    } else {
      boolean equals = o1.equals(o2);
      if (equals) {
        return 0;
      } else {
        int compareTo = o1.getType().getName().compareTo(o2.getType().getName());
        if (compareTo == 0) {
          return 1;
        } else {
          return compareTo;
        }
      }
    }

  }
}
