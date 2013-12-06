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
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RegExpCondition extends TerminalRutaCondition {
  private final IStringExpression pattern;

  private final IBooleanExpression ignoreCase;

  private IStringExpression variable;

  public RegExpCondition(IStringExpression pattern, IBooleanExpression ignoreCase) {
    super();
    this.pattern = pattern;
    this.ignoreCase = ignoreCase == null ? new SimpleBooleanExpression(false) : ignoreCase;
  }

  public RegExpCondition(IStringExpression v, IStringExpression pattern, IBooleanExpression ignoreCase) {
    this(pattern, ignoreCase);
    this.variable = v;
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
        regularExpPattern = Pattern.compile(stringValue, Pattern.MULTILINE + Pattern.DOTALL + Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
      } else {
        regularExpPattern = Pattern.compile(stringValue, Pattern.MULTILINE + Pattern.DOTALL);
      }
      matcher = regularExpPattern.matcher(coveredText);
    } else {
      String variableValue = variable.getStringValue(element.getParent(), annotation, stream);
      Pattern regularExpPattern = null;
      if (ignore) {
        regularExpPattern = Pattern.compile(stringValue, Pattern.MULTILINE + Pattern.DOTALL + Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
      } else {
        regularExpPattern = Pattern.compile(stringValue, Pattern.MULTILINE + Pattern.DOTALL);
      }
      matcher = regularExpPattern.matcher(variableValue);
    }
    boolean matches = matcher.matches();
    return new EvaluatedCondition(this, matches);
  }

  public IStringExpression getPattern() {
    return pattern;
  }

  public IStringExpression getVariable() {
    return variable;
  }

  public IBooleanExpression getIgnoreCase() {
    return ignoreCase;
  }

}
