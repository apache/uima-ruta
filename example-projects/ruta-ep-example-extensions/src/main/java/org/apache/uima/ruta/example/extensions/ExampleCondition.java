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

package org.apache.uima.ruta.example.extensions;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.expression.string.StringExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ExampleCondition extends AbstractRutaCondition {

  private final StringExpression dateExpr;

  private final StringExpression formatExpr;

  public ExampleCondition(StringExpression expr, StringExpression format) {
    super();
    this.dateExpr = expr;
    this.formatExpr = format;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    String coveredText = annotation.getCoveredText();
    String dateValue = dateExpr.getStringValue(element.getParent(), annotation, stream);
    String formatValue = formatExpr.getStringValue(element.getParent(), annotation, stream);
    SimpleDateFormat dateFormat = new SimpleDateFormat(formatValue);
    boolean result = false;
    try {
      Date matchedDate = dateFormat.parse(coveredText);
      Date givenDate = dateFormat.parse(dateValue);
      int compareTo = matchedDate.compareTo(givenDate);
      result = compareTo < 0;
    } catch (Exception e) {
    }
    return new EvaluatedCondition(this, result);
  }

  public StringExpression getExpr() {
    return dateExpr;
  }

  public StringExpression getFormatExpr() {
    return formatExpr;
  }

}
