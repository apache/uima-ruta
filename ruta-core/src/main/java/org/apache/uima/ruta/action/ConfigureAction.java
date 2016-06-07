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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.Type;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.ConfigurationParameterDeclarations;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaModule;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.AbstractBooleanListExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.AbstractNumberListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.AbstractStringListExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.AbstractTypeListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ConfigureAction extends AbstractRutaAction {

  private final String namespace;

  private final Map<IStringExpression, IRutaExpression> parameterMap;

  public ConfigureAction(String ns, Map<IStringExpression, IRutaExpression> map) {
    super();
    this.namespace = ns;
    this.parameterMap = map;
  }

  @Override
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    RuleElement element = context.getElement();
    RutaBlock parent = element.getParent();
    RutaModule thisScript = parent.getScript();
    AnalysisEngine targetEngine = thisScript.getEngine(namespace);
    if(targetEngine == null) {
      throw new IllegalArgumentException("Analysis Engine with name '"+namespace+"' is unknown.");
    }
    ConfigurationParameterDeclarations configurationParameterDeclarations = targetEngine
            .getAnalysisEngineMetaData().getConfigurationParameterDeclarations();

    Set<Entry<IStringExpression, IRutaExpression>> entrySet = parameterMap.entrySet();
    for (Entry<IStringExpression, IRutaExpression> entry : entrySet) {
      IStringExpression key = entry.getKey();
      String stringValue = key.getStringValue(context, stream);
      ConfigurationParameter configurationParameter = configurationParameterDeclarations
              .getConfigurationParameter(null, stringValue);
      if (configurationParameter != null) {
        IRutaExpression value = entry.getValue();
        String type = configurationParameter.getType();
        if (type.equals("String")) {
          if (configurationParameter.isMultiValued()) {
            if (value instanceof AbstractStringListExpression) {
              AbstractStringListExpression sle = (AbstractStringListExpression) value;
              List<String> list = sle.getList(context, stream);
              targetEngine.setConfigParameterValue(stringValue, list.toArray(new String[0]));
            } else if (value instanceof AbstractTypeListExpression) {
              AbstractTypeListExpression tle = (AbstractTypeListExpression) value;
              List<Type> list = tle.getList(context, stream);
              List<String> stringList = new ArrayList<String>();
              for (Type each : list) {
                stringList.add(each.getName());
              }
              targetEngine.setConfigParameterValue(stringValue, stringList.toArray(new String[0]));
            }
          } else {
            if (value instanceof IStringExpression) {
              IStringExpression se = (IStringExpression) value;
              String string = se.getStringValue(context, stream);
              targetEngine.setConfigParameterValue(stringValue, string);
            } else if (value instanceof ITypeExpression) {
              ITypeExpression te = (ITypeExpression) value;
              Type t = te.getType(context, stream);
              targetEngine.setConfigParameterValue(stringValue, t.getName());
            }
          }
        } else if (type.equals("Float")) {
          if (value instanceof AbstractNumberListExpression) {
            AbstractNumberListExpression nle = (AbstractNumberListExpression) value;
            List<Number> list = nle.getList(context, stream);
            List<Float> numbers = new ArrayList<Float>();
            for (Number number : list) {
              numbers.add(number.floatValue());
            }
            targetEngine.setConfigParameterValue(stringValue, numbers.toArray());
          } else {
            if (value instanceof INumberExpression) {
              INumberExpression ne = (INumberExpression) value;
              Double d = ne.getDoubleValue(context, stream);
              targetEngine.setConfigParameterValue(stringValue, d.floatValue());
            }
          }
        } else if (type.equals("Integer")) {
          if (value instanceof AbstractNumberListExpression) {
            AbstractNumberListExpression nle = (AbstractNumberListExpression) value;
            List<Number> list = nle.getList(context, stream);
            List<Integer> numbers = new ArrayList<Integer>();
            for (Number number : list) {
              numbers.add(number.intValue());
            }
            targetEngine.setConfigParameterValue(stringValue, numbers.toArray());
          } else {
            if (value instanceof INumberExpression) {
              INumberExpression ne = (INumberExpression) value;
              Integer i = ne.getIntegerValue(context, stream);
              targetEngine.setConfigParameterValue(stringValue, i);
            }
          }
        } else if (type.equals("Boolean")) {
          if (value instanceof AbstractBooleanListExpression) {
            AbstractBooleanListExpression ble = (AbstractBooleanListExpression) value;
            List<Boolean> list = ble.getList(context, stream);
            targetEngine.setConfigParameterValue(stringValue, list.toArray());
          } else {
            if (value instanceof IBooleanExpression) {
              IBooleanExpression be = (IBooleanExpression) value;
              Boolean b = be.getBooleanValue(context, stream);
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

  public String getNamespace() {
    return namespace;
  }

  public Map<IStringExpression, IRutaExpression> getParameterMap() {
    return parameterMap;
  }

}
