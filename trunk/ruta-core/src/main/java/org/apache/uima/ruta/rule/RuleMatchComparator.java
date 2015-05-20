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
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;

/**
 * Comparator for rule matches following mostly the default order.
 * 
 */
public class RuleMatchComparator implements Comparator<RuleMatch> {

  public RuleMatchComparator() {
    super();
  }

  public int compare(RuleMatch m1, RuleMatch m2) {
    List<AnnotationFS> tm1 = m1.getRootMatch().getTextsMatched();
    List<AnnotationFS> tm2 = m2.getRootMatch().getTextsMatched();
    int b1 = 0;
    int b2 = 0;
    int e1 = 0;
    int e2 = 0;
    if (tm1 != null && !tm1.isEmpty()) {
      b1 = tm1.get(0).getBegin();
      e1 = tm1.get(tm1.size() - 1).getEnd();
    }
    if (tm2 != null && !tm2.isEmpty()) {
      b2 = tm2.get(0).getBegin();
      e2 = tm2.get(tm2.size() - 1).getEnd();
    }
    if (b1 < b2) {
      return -1;
    } else if (b1 > b2) {
      return 1;
    } else if (e1 > e2) {
      return -1;
    } else if (e1 < e2) {
      return 1;
    } else {
      return 0;
    }
  }
}
