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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.ScriptApply;

public class RuleApply extends ScriptApply {

  private List<AbstractRuleMatch<? extends AbstractRule>> list;

  protected int tried = 0;

  protected int applied = 0;

  private boolean acceptMatches;

  public RuleApply(RutaStatement tme, boolean remember) {
    super(tme);
    list = new ArrayList<AbstractRuleMatch<? extends AbstractRule>>();
    this.acceptMatches = remember;
  }

  public List<AbstractRuleMatch<? extends AbstractRule>> getList() {
    return list;
  }

  public void add(AbstractRuleMatch<? extends AbstractRule> match) {
    if (match.matchedCompletely()) {
      applied++;
    }
    tried++;
    if (acceptMatches) {
      list.add(match);
    }
  }

  public int getTried() {
    return tried;
  }

  public int getApplied() {
    return applied;
  }

  public boolean isAcceptMatches() {
    return acceptMatches;
  }

  public void setAcceptMatches(boolean acceptMatches) {
    this.acceptMatches = acceptMatches;
  }

  public void addAll(List<RuleMatch> matches) {
    for (RuleMatch ruleMatch : matches) {
      add(ruleMatch);
    }
  }

}
