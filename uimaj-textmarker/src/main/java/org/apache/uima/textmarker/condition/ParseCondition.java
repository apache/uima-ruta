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

package org.apache.uima.textmarker.condition;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerEnvironment;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.rule.EvaluatedCondition;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class ParseCondition extends AbstractTextMarkerCondition {

  private final String var;

  public ParseCondition(String var) {
    super();
    this.var = var;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element,
          TextMarkerStream stream, InferenceCrowd crowd) {
    String text = annotation.getCoveredText();
    TextMarkerEnvironment env = element.getParent().getEnvironment();
    Class<?> type = env.getVariableType(var);
    try {
      if (Integer.class.equals(type)) {
        text = normalizeNumber(text);
        int value = Integer.valueOf(text);
        env.setVariableValue(var, value);
        return new EvaluatedCondition(this, true);
      } else if (Double.class.equals(type)) {
        text = normalizeNumber(text);
        double value = Double.valueOf(text);
        env.setVariableValue(var, value);
        return new EvaluatedCondition(this, true);
      } else if (String.class.equals(type)) {
        env.setVariableValue(var, text);
        return new EvaluatedCondition(this, true);
      } else if (Boolean.class.equals(type)) {
        env.setVariableValue(var, !"".equals(text));
        return new EvaluatedCondition(this, true);
      } else if (Type.class.equals(type)) {
        Type value = stream.getCas().getTypeSystem().getType(text);
        env.setVariableValue(var, value);
        return new EvaluatedCondition(this, true);
      } else {
        return new EvaluatedCondition(this, false);
      }
    } catch (Exception e) {
      return new EvaluatedCondition(this, false);
    }
  }

  private String normalizeNumber(String text) {
    String[] split = text.split("[,]");
    if (split.length == 2) {
      return text.replaceAll(",", ".");
    }
    return text;
  }

  public String getVar() {
    return var;
  }

}
