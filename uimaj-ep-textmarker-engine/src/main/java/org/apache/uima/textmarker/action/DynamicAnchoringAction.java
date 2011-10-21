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

package org.apache.uima.textmarker.action;

import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class DynamicAnchoringAction extends AbstractTextMarkerAction {

  private final BooleanExpression active;

  private final NumberExpression panelty;

  private final NumberExpression factor;

  public DynamicAnchoringAction(BooleanExpression active, NumberExpression panelty,
          NumberExpression factor) {
    super();
    this.active = active;
    this.panelty = panelty;
    this.factor = factor;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    TextMarkerBlock parent = element.getParent();
    boolean activated = active.getBooleanValue(parent);
    stream.setDynamicAnchoring(activated);
    if (panelty != null) {
      double p = panelty.getDoubleValue(parent);
      stream.setIndexPenalty(p);
    }
    if (factor != null) {
      double f = factor.getDoubleValue(parent);
      stream.setAnchoringFactor(f);
    }
  }

  public BooleanExpression getActive() {
    return active;
  }

  public NumberExpression getPanelty() {
    return panelty;
  }

  public NumberExpression getFactor() {
    return factor;
  }

}
