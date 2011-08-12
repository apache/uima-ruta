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

package org.apache.uima.textmarker;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.textmarker.rule.RuleApply;


public class BlockApply extends ScriptApply {

  public BlockApply(TextMarkerStatement tme) {
    super(tme);
  }

  private List<ScriptApply> innerApplies = new ArrayList<ScriptApply>();

  private RuleApply ruleApply;

  public void add(ScriptApply apply) {
    innerApplies.add(apply);
  }

  public List<ScriptApply> getInnerApplies() {
    return innerApplies;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (ScriptApply each : getInnerApplies()) {
      result.append(each.toString());
      result.append("\n");
    }
    return result.toString() + " : " + hashCode();
  }

  public RuleApply getRuleApply() {
    return ruleApply;
  }

  public void setRuleApply(RuleApply ruleApply) {
    this.ruleApply = ruleApply;
  }

}
