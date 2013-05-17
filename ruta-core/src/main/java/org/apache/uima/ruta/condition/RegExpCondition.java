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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.BooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.string.StringExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RegExpCondition extends TerminalRutaCondition {
  private final StringExpression pattern;

  private final BooleanExpression ignoreCase;

  private String variable;

  public RegExpCondition(StringExpression pattern, BooleanExpression ignoreCase) {
    super();
    this.pattern = pattern;
    this.ignoreCase = ignoreCase == null ? new SimpleBooleanExpression(false) : ignoreCase;
  }

  public RegExpCondition(String variable, StringExpression pattern, BooleanExpression ignoreCase) {
    this(pattern, ignoreCase);
    this.variable = variable;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    Matcher matcher = null;
    boolean ignore = ignoreCase == null ? false : ignoreCase.getBooleanValue(element.getParent(),
            annotation, stream);
    String stringValue = pattern.getStringValue(element.getParent(), annotation, stream);
    if (variable == null) {
      String coveredText = annotation.getCoveredText();
      Pattern regularExpPattern = null;
      if (ignore) {
        regularExpPattern = Pattern.compile(stringValue, Pattern.CASE_INSENSITIVE);
      } else {
        regularExpPattern = Pattern.compile(stringValue);
      }
      matcher = regularExpPattern.matcher(coveredText);
    } else {
      RutaEnvironment environment = element.getParent().getEnvironment();
      String variableValue = environment.getVariableValue(variable, String.class);
      Pattern regularExpPattern = null;
      if (ignore) {
        regularExpPattern = Pattern.compile(stringValue, Pattern.CASE_INSENSITIVE);
      } else {
        regularExpPattern = Pattern.compile(stringValue);
      }
      matcher = regularExpPattern.matcher(variableValue);
    }
    boolean matches = matcher.matches();
    return new EvaluatedCondition(this, matches);
  }

  public StringExpression getPattern() {
    return pattern;
  }

  public String getVariable() {
    return variable;
  }

  public BooleanExpression getIgnoreCase() {
    return ignoreCase;
  }

}
