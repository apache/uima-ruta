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

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.bool.SimpleBooleanExpression;
import org.apache.uima.textmarker.expression.string.SimpleStringExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.type.TextMarkerColoring;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class ColorAction extends AbstractTextMarkerAction {

  private StringExpression bgcolor;

  private StringExpression fgcolor;

  private BooleanExpression selected;

  private TypeExpression type;

  public ColorAction(TypeExpression type, StringExpression bgcolor, StringExpression fgcolor,
          BooleanExpression selected) {
    super();
    this.type = type;
    this.bgcolor = bgcolor == null ? new SimpleStringExpression("red") : bgcolor;
    this.fgcolor = fgcolor == null ? new SimpleStringExpression("red") : fgcolor;
    this.selected = selected == null ? new SimpleBooleanExpression(false) : selected;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    Type casType = stream.getJCas().getCasType(TextMarkerColoring.type);
    FeatureStructure newAnnotationFS = stream.getCas().createFS(casType);
    TextMarkerColoring coloring = null;
    if (newAnnotationFS instanceof TextMarkerColoring) {
      coloring = (TextMarkerColoring) newAnnotationFS;
      coloring.setBgColor(bgcolor.getStringValue(element.getParent()));
      coloring.setFgColor(fgcolor.getStringValue(element.getParent()));
      coloring.setSelected(selected.getBooleanValue(element.getParent()));
      coloring.setTargetType(type.getType(element.getParent()).getName());
      coloring.addToIndexes();
    }
  }

  public StringExpression getFgColor() {
    return fgcolor;
  }

  public StringExpression getBgColor() {
    return bgcolor;
  }

  public BooleanExpression getSelected() {
    return selected;
  }

  public TypeExpression getType() {
    return type;
  }

}
