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

package org.apache.uima.ruta.textruler.learner.trabal;


/**
 * Conditions can be attached to TraBaL rules to reduce the chance of overfitting.
 * 
 */
public class Condition {

  private ConditionType type;

  private TrabalRuleItem item;

  private boolean isNegative = false;

  private boolean isExpansionCondition = false;

  public Condition(ConditionType type, TrabalRuleItem item) {
    this.type = type;
    this.item = item;
  }

  public Condition(ConditionType type, TrabalRuleItem item, boolean isNegative,
          boolean isExpansionCondition) {
    this.type = type;
    this.item = item;
    this.isNegative = isNegative;
    this.isExpansionCondition = isExpansionCondition;
  }

  public ConditionType getType() {
    return type;
  }

  public TrabalRuleItem getItem() {
    return item;
  }

  public boolean isNegative() {
    return isNegative;
  }

  public void setNegative() {
    isNegative = true;
  }

  public void setPositive() {
    isNegative = false;
  }
  
  public void changePosNegValue() {
    isNegative = !isNegative;
  }

  public boolean isExpansionCondition() {
    return isExpansionCondition;
  }

  public void setExpansionCondition() {
    isExpansionCondition = true;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Condition other = (Condition) obj;
    if (type != other.getType())
      return false;
    if (!item.getName().equals(other.getItem().getName()))
      return false;
    if (isNegative != other.isNegative)
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public String toString() {
    if (isNegative)
      return "-" + type.getConditionString(item.toString());
    return type.getConditionString(item.toString());
  }

  @Override
  public Condition clone() {
    Condition result = new Condition(type, item, isNegative, isExpansionCondition);
    return result;
  }
}
