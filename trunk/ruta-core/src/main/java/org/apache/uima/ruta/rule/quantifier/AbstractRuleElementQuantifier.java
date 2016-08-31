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

package org.apache.uima.ruta.rule.quantifier;

import java.util.List;

import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleElementMatch;
import org.apache.uima.ruta.rule.RuleMatch;

public abstract class AbstractRuleElementQuantifier implements RuleElementQuantifier {

  protected boolean nextElementMatched(RuleElement nextElement, List<RuleMatch> matches) {
    if (matches == null) {
      return false;
    }
    for (RuleMatch eachRuleMatch : matches) {
      if (eachRuleMatch != null) {
        List<List<RuleElementMatch>> matchInfo = eachRuleMatch.getMatchInfo(nextElement);
        for (List<RuleElementMatch> list : matchInfo) {
          if (list != null) {
            for (RuleElementMatch ruleElementMatch : list) {
              if (ruleElementMatch != null && ruleElementMatch.matched()) {
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }

}
