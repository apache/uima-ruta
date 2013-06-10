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

package org.apache.uima.ruta.textruler.learner.kep;

import org.apache.uima.cas.Type;

public class KEPRuleItemCondition {

  public enum Condition {
    IS, PARTOF, CONTAINS, REGEXP
  }

  private Type type;

  private Condition condition;

  private boolean isNot;

  private String regExp;

  public KEPRuleItemCondition(Type type, Condition condition, boolean isNot) {
    this.type = type;
    this.condition = condition;
    this.isNot = isNot;
    this.regExp = "";
  }

  public KEPRuleItemCondition(String regExp) {
    this.regExp = regExp;
    this.condition = Condition.REGEXP;
    this.isNot = false;
  }

  public String toString() {
    return (isNot ? "-" : "") + this.condition.toString() + "("
            + (regExp + type == null ? "" : type.getShortName()) + ")";
  }

  public boolean equals(KEPRuleItemCondition other) {
    if (this.condition == Condition.REGEXP && other.condition == Condition.REGEXP
            && this.regExp.equals(other.regExp) && this.isNot == other.isNot)
      return true;
    if (this.type.toString().equals(other.type.toString()) && this.isNot == other.isNot
            && this.condition == other.condition)
      return true;
    return false;
  }

  public boolean equals(Type type) {
    if (this.type.toString().equals(type.toString()))
      return true;
    return false;
  }
}
