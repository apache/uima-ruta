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

package org.apache.uima.ruta.action;

import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class GreedyAnchoringAction extends AbstractRutaAction {

  private final IBooleanExpression greedyRuleElement;

  private final IBooleanExpression greedyRule;
  
  public GreedyAnchoringAction(IBooleanExpression active, IBooleanExpression active2) {
    super();
    this.greedyRuleElement = active;
    this.greedyRule = active2 == null ? new SimpleBooleanExpression(false) : active2;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    RutaBlock parent = element.getParent();
    boolean greedy1 = greedyRuleElement.getBooleanValue(parent, match, element, stream);
    boolean greedy2 = greedyRule.getBooleanValue(parent, match, element, stream);
    stream.setGreedyRuleElement(greedy1);
    stream.setGreedyRule(greedy2);
  }

  public IBooleanExpression getGreedyRuleElement() {
    return greedyRuleElement;
  }

  public IBooleanExpression getGreedyRule() {
    return greedyRule;
  }

  

}
