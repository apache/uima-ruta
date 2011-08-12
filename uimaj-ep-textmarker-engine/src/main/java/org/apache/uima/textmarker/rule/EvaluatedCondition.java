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

package org.apache.uima.textmarker.rule;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.condition.NotCondition;


public class EvaluatedCondition {

  private final AbstractTextMarkerCondition condition;

  private final boolean value;

  private final List<EvaluatedCondition> conditions;

  private final List<EvaluatedCondition> noConditions = new ArrayList<EvaluatedCondition>(0);

  public EvaluatedCondition(AbstractTextMarkerCondition condition, boolean value,
          List<EvaluatedCondition> conditions) {
    super();
    this.condition = condition;
    this.value = value;
    this.conditions = conditions;
  }

  public EvaluatedCondition(AbstractTextMarkerCondition condition, boolean value) {
    super();
    this.condition = condition;
    this.value = value;
    this.conditions = noConditions;
  }

  public EvaluatedCondition(NotCondition condition, boolean value, EvaluatedCondition eval) {
    super();
    this.condition = condition;
    this.value = value;
    this.conditions = new ArrayList<EvaluatedCondition>();
    conditions.add(eval);
  }

  public AbstractTextMarkerCondition getCondition() {
    return condition;
  }

  public boolean isValue() {
    return value;
  }

  public List<EvaluatedCondition> getConditions() {
    return conditions;
  }

}
