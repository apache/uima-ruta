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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.rule.RutaRuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class GetFeatureAction extends AbstractRutaAction {

  private IStringExpression featureStringExpression;

  private String variable;

  public GetFeatureAction(IStringExpression f, String variable) {
    super();
    this.featureStringExpression = f;
    this.variable = variable;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    List<Type> types = new ArrayList<Type>();
    RutaBlock parent = element.getParent();
    if (element instanceof RutaRuleElement) {
      types = ((RutaRuleElement) element).getMatcher().getTypes(parent, stream);
    }
    if (types == null)
      return;

    for (Type type : types) {
      String stringValue = featureStringExpression.getStringValue(parent, match, element, stream);
      Feature featureByBaseName = type.getFeatureByBaseName(stringValue);
      RutaEnvironment environment = parent.getEnvironment();
      List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotationsOf(element);
      for (AnnotationFS annotationFS : matchedAnnotations) {
        if (annotationFS.getType().getFeatureByBaseName(stringValue) == null) {
          System.out.println("Can't access feature " + stringValue
                  + ", because it's not defined in the matched type: " + annotationFS.getType());
          return;
        }

        String featName = featureByBaseName.getRange().getName();
        if (environment.getVariableType(variable).equals(String.class)
                && featName.equals(UIMAConstants.TYPE_STRING)) {
          Object value = annotationFS.getStringValue(featureByBaseName);
          environment.setVariableValue(variable, value);
        } else if (Number.class.isAssignableFrom(environment.getVariableType(variable))) {
          Number value = 0;
          if (featName.equals(UIMAConstants.TYPE_INTEGER)) {
            value = annotationFS.getIntValue(featureByBaseName);
          } else if (featName.equals(UIMAConstants.TYPE_DOUBLE)) {
            value = annotationFS.getDoubleValue(featureByBaseName);
          } else if (featName.equals(UIMAConstants.TYPE_FLOAT)) {
            value = annotationFS.getFloatValue(featureByBaseName);
          } else if (featName.equals(UIMAConstants.TYPE_BYTE)) {
            value = annotationFS.getByteValue(featureByBaseName);
          } else if (featName.equals(UIMAConstants.TYPE_SHORT)) {
            value = annotationFS.getShortValue(featureByBaseName);
          } else if (featName.equals(UIMAConstants.TYPE_LONG)) {
            value = annotationFS.getLongValue(featureByBaseName);
          }
          environment.setVariableValue(variable, value);
        } else if (environment.getVariableType(variable).equals(Boolean.class)
                && featName.equals(UIMAConstants.TYPE_BOOLEAN)) {
          Object value = annotationFS.getBooleanValue(featureByBaseName);
          environment.setVariableValue(variable, value);
        } else if (environment.getVariableType(variable).equals(Type.class)
                && featName.equals(UIMAConstants.TYPE_STRING)) {
          Object value = annotationFS.getStringValue(featureByBaseName);
          Type t = stream.getCas().getTypeSystem().getType((String) value);
          if (t != null) {
            environment.setVariableValue(variable, t);
          }
        }
      }
    }

  }

  public IStringExpression getFeatureStringExpression() {
    return featureStringExpression;
  }

  public String getVariable() {
    return variable;
  }
}
