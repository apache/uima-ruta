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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.text.AnnotationFS;

public class RegExpRuleMatch extends AbstractRuleMatch<RegExpRule> {

  private Map<Integer, List<AnnotationFS>> map = new TreeMap<Integer, List<AnnotationFS>>();

  public RegExpRuleMatch(RegExpRule rule) {
    super(rule);
  }

  @Override
  public List<AnnotationFS> getMatchedAnnotationsOfRoot() {
    return map.get(0);
  }

  public void addMatched(int group, AnnotationFS afs) {
    List<AnnotationFS> list = map.get(group);
    if (list == null) {
      list = new LinkedList<AnnotationFS>();
      map.put(group, list);
    }
    list.add(afs);
  }

  public Map<Integer, List<AnnotationFS>> getMap() {
    return map;
  }

  public void setMap(Map<Integer, List<AnnotationFS>> map) {
    this.map = map;
  }

}
