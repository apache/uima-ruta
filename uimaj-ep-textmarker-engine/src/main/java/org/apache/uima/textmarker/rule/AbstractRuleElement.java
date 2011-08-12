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

import java.util.Collections;
import java.util.List;

import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerElement;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;


public abstract class AbstractRuleElement extends TextMarkerElement implements RuleElement {

  protected RuleElementQuantifier quantifier;

  public AbstractRuleElement(RuleElementQuantifier quantifier) {
    super();
    this.quantifier = quantifier;
  }

  @SuppressWarnings("unchecked")
  protected final InferenceCrowd emptyCrowd = new InferenceCrowd(Collections.EMPTY_LIST);

  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches,
          TextMarkerBlock parent) {
    return quantifier.evaluateMatches(matches, parent, emptyCrowd);
  }

  public boolean continueMatch(int index, List<RuleElement> elements, TextMarkerBasic next,
          RuleElementMatch match, List<RuleElementMatch> matches, TextMarkerStream stream) {
    return quantifier.continueMatch(index, elements, next, match, matches, stream, emptyCrowd);
  }

  public RuleElementQuantifier getQuantifier() {
    return quantifier;
  }

}
