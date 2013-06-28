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

import java.util.Comparator;

public class AnnotationCheckTreeNodeComparator implements Comparator<AnnotationCheckTreeNode>{

  public int compare(AnnotationCheckTreeNode o1, AnnotationCheckTreeNode o2) {
    if(o1.getElement() instanceof CheckAnnotation && o1.getElement() instanceof CheckAnnotation) {
      CheckAnnotation ca1 = (CheckAnnotation) o1.getElement();
      CheckAnnotation ca2 = (CheckAnnotation) o2.getElement();
      if (ca1.begin < ca2.begin) {
        return -1;
      } else if (ca1.begin > ca2.begin) {
        return 1;
      } else if (ca1.end > ca2.end) {
        return -1;
      } else if (ca1.end < ca2.end) {
        return 1;
      } else {
        boolean equals = o1.equals(o2);
        if (equals) {
          return 0;
        } else {
          int compareTo = ca1.type.compareTo(ca2.type);
          if (compareTo == 0) {
            return 1;
          } else {
            return compareTo;
          }
        }
      }
    }
    return 0;
  }

}
