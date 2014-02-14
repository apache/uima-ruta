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

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaColoring;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ColorAction extends AbstractRutaAction {

  private IStringExpression bgcolor;

  private IStringExpression fgcolor;

  private IBooleanExpression selected;

  private TypeExpression type;

  public ColorAction(TypeExpression type, IStringExpression bgcolor, IStringExpression fgcolor,
          IBooleanExpression selected) {
    super();
    this.type = type;
    this.bgcolor = bgcolor == null ? new SimpleStringExpression("red") : bgcolor;
    this.fgcolor = fgcolor == null ? new SimpleStringExpression("red") : fgcolor;
    this.selected = selected == null ? new SimpleBooleanExpression(false) : selected;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    Type casType = stream.getJCas().getCasType(RutaColoring.type);
    FeatureStructure newAnnotationFS = stream.getCas().createFS(casType);
    RutaColoring coloring = null;
    if (newAnnotationFS instanceof RutaColoring) {
      coloring = (RutaColoring) newAnnotationFS;
      RutaBlock parent = element.getParent();
      coloring.setBgColor(bgcolor.getStringValue(parent, match, element, stream));
      coloring.setFgColor(fgcolor.getStringValue(parent, match, element, stream));
      coloring.setSelected(selected.getBooleanValue(parent, match, element, stream));
      coloring.setTargetType(type.getType(parent).getName());
      coloring.addToIndexes();
    }
  }

  public IStringExpression getFgColor() {
    return fgcolor;
  }

  public IStringExpression getBgColor() {
    return bgcolor;
  }

  public IBooleanExpression getSelected() {
    return selected;
  }

  public TypeExpression getType() {
    return type;
  }

}
