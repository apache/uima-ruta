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
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class DynamicAnchoringAction extends AbstractRutaAction {

  private final IBooleanExpression active;

  private final INumberExpression panelty;

  private final INumberExpression factor;

  public DynamicAnchoringAction(IBooleanExpression active, INumberExpression panelty,
          INumberExpression factor) {
    super();
    this.active = active;
    this.panelty = panelty;
    this.factor = factor;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    RutaBlock parent = element.getParent();
    boolean activated = active.getBooleanValue(parent, match, element, stream);
    stream.setDynamicAnchoring(activated);
    if (panelty != null) {
      double p = panelty.getDoubleValue(parent, match, element, stream);
      stream.setIndexPenalty(p);
    }
    if (factor != null) {
      double f = factor.getDoubleValue(parent, match, element, stream);
      stream.setAnchoringFactor(f);
    }
  }

  public IBooleanExpression getActive() {
    return active;
  }

  public INumberExpression getPanelty() {
    return panelty;
  }

  public INumberExpression getFactor() {
    return factor;
  }

}
