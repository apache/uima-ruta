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

package org.apache.uima.tm.textmarker.kernel.rule.quantifier;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElementMatch;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class StarReluctant implements RuleElementQuantifier {

  public boolean continueMatch(int index, List<RuleElement> elements, TextMarkerBasic next,
          RuleElementMatch match, List<RuleElementMatch> matches, TextMarkerStream stream,
          InferenceCrowd crowd) {
    if (index == elements.size() - 1) {
      // reluctant = minimal ... last element never needs to match.
      return false;
    }
    boolean nextMatched = false;
    if (index + 1 < elements.size()) {
      RuleElement element = elements.get(index + 1);
      RuleElementMatch nextMatch = element.match(next, stream, crowd);
      if (nextMatch.matched()) {
        nextMatched = true;
      }
    }
    return !nextMatched && next != null;
  }

  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches,
          TextMarkerStatement element, InferenceCrowd crowd) {
    return matches;
  }
}
