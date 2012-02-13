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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.Type;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.ConfigurationParameterDeclarations;
import org.apache.uima.textmarker.TextMarkerModule;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.list.BooleanListExpression;
import org.apache.uima.textmarker.expression.list.NumberListExpression;
import org.apache.uima.textmarker.expression.list.StringListExpression;
import org.apache.uima.textmarker.expression.list.TypeListExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class ConfigureAction extends AbstractTextMarkerAction {

  private String namespace;

  private Map<StringExpression, TextMarkerExpression> parameterMap;

  public ConfigureAction(String ns, Map<StringExpression, TextMarkerExpression> map) {
    super();
    this.namespace = ns;
    this.parameterMap = map;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    TextMarkerModule thisScript = element.getParent().getScript();
    AnalysisEngine targetEngine = thisScript.getEngine(namespace);
    ConfigurationParameterDeclarations configurationParameterDeclarations = targetEngine
            .getAnalysisEngineMetaData().getConfigurationParameterDeclarations();

    Set<Entry<StringExpression, TextMarkerExpression>> entrySet = parameterMap.entrySet();
    for (Entry<StringExpression, TextMarkerExpression> entry : entrySet) {
      StringExpression key = entry.getKey();
      String stringValue = key.getStringValue(element.getParent());
      ConfigurationParameter configurationParameter = configurationParameterDeclarations
              .getConfigurationParameter(null, stringValue);
      if (configurationParameter != null) {
        TextMarkerExpression value = entry.getValue();
        String type = configurationParameter.getType();
        if (type.equals("String")) {
          if (configurationParameter.isMultiValued()) {
            if (value instanceof StringListExpression) {
              StringListExpression sle = (StringListExpression) value;
              List<String> list = sle.getList(element.getParent());
              targetEngine.setConfigParameterValue(stringValue, list.toArray());
            } else if (value instanceof TypeListExpression) {
              TypeListExpression tle = (TypeListExpression) value;
              List<Type> list = tle.getList(element.getParent());
              List<String> stringList = new ArrayList<String>();
              for (Type each : list) {
                stringList.add(each.getName());
              }
              targetEngine.setConfigParameterValue(stringValue, stringList.toArray());
            }
          } else {
            if (value instanceof StringExpression) {
              StringExpression se = (StringExpression) value;
              String string = se.getStringValue(element.getParent());
              targetEngine.setConfigParameterValue(stringValue, string);
            } else if (value instanceof TypeExpression) {
              TypeExpression te = (TypeExpression) value;
              Type t = te.getType(element.getParent());
              targetEngine.setConfigParameterValue(stringValue, t.getName());
            }
          }
        } else if (type.equals("Float")) {
          if (value instanceof NumberListExpression) {
            NumberListExpression nle = (NumberListExpression) value;
            List<Number> list = nle.getList(element.getParent());
            List<Float> numbers = new ArrayList<Float>();
            for (Number number : list) {
              numbers.add(number.floatValue());
            }
            targetEngine.setConfigParameterValue(stringValue, numbers.toArray());
          } else {
            if (value instanceof NumberExpression) {
              NumberExpression ne = (NumberExpression) value;
              Double d = ne.getDoubleValue(element.getParent());
              targetEngine.setConfigParameterValue(stringValue, d.floatValue());
            }
          }
        } else if (type.equals("Integer")) {
          if (value instanceof NumberListExpression) {
            NumberListExpression nle = (NumberListExpression) value;
            List<Number> list = nle.getList(element.getParent());
            List<Integer> numbers = new ArrayList<Integer>();
            for (Number number : list) {
              numbers.add(number.intValue());
            }
            targetEngine.setConfigParameterValue(stringValue, numbers.toArray());
          } else {
            if (value instanceof NumberExpression) {
              NumberExpression ne = (NumberExpression) value;
              Integer i = ne.getIntegerValue(element.getParent());
              targetEngine.setConfigParameterValue(stringValue, i);
            }
          }
        } else if (type.equals("Boolean")) {
          if (value instanceof BooleanListExpression) {
            BooleanListExpression ble = (BooleanListExpression) value;
            List<Boolean> list = ble.getList(element.getParent());
            targetEngine.setConfigParameterValue(stringValue, list.toArray());
          } else {
            if (value instanceof BooleanExpression) {
              BooleanExpression be = (BooleanExpression) value;
              Boolean b = be.getBooleanValue(element.getParent());
              targetEngine.setConfigParameterValue(stringValue, b);
            }
          }
        }
      }
    }

    try {
      targetEngine.reconfigure();
    } catch (ResourceConfigurationException e) {
      e.printStackTrace();
    }
  }

}
