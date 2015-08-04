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

package org.apache.uima.ruta.condition;

import java.text.NumberFormat;
import java.util.Locale;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ParseCondition extends AbstractRutaCondition {

  private final String var;

  private IStringExpression localeExpr;
  
  public ParseCondition(String var) {
    super();
    this.var = var;
  }
  
  public ParseCondition(String var, IStringExpression localeExpr) {
    super();
    this.var = var;
    this.localeExpr = localeExpr;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    String text = annotation.getCoveredText();
    RutaEnvironment env = element.getParent().getEnvironment();
    Class<?> type = env.getVariableType(var);
    NumberFormat nf = null;
    String locale = annotation.getCAS().getDocumentLanguage();
    if(localeExpr != null) {
      locale = localeExpr.getStringValue(element.getParent(), annotation, stream);
    }
    if(locale == null) {
      locale = "x-unspecified";
    }
    nf = NumberFormat.getInstance(Locale.forLanguageTag(locale));     
    try {
      if (Integer.class.equals(type)) {
        Number parse = nf.parse(text);
        env.setVariableValue(var, parse.intValue());
        return new EvaluatedCondition(this, true);
      } else if (Double.class.equals(type)) {
        Number parse = nf.parse(text);
        env.setVariableValue(var, parse.doubleValue());
        return new EvaluatedCondition(this, true);
      } else if (Float.class.equals(type)) {
        Number parse = nf.parse(text);
        env.setVariableValue(var, parse.floatValue());
        return new EvaluatedCondition(this, true);
      } else if (String.class.equals(type)) {
        env.setVariableValue(var, text);
        return new EvaluatedCondition(this, true);
      } else if (Boolean.class.equals(type)) {
        env.setVariableValue(var, Boolean.valueOf(text));
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

  public String getVar() {
    return var;
  }

  public IStringExpression getLocaleExpr() {
    return localeExpr;
  }

  public void setLocaleExpr(IStringExpression localeExpr) {
    this.localeExpr = localeExpr;
  }

}
