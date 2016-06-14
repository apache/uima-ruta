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
import org.apache.uima.ruta.expression.string.AbstractStringExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ExampleCondition extends AbstractRutaCondition {

  private final AbstractStringExpression dateExpr;

  private final AbstractStringExpression formatExpr;

  public ExampleCondition(AbstractStringExpression expr, AbstractStringExpression format) {
    super();
    this.dateExpr = expr;
    this.formatExpr = format;
  }

  @Override
  public EvaluatedCondition eval(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    AnnotationFS annotation = context.getAnnotation();
    String coveredText = annotation.getCoveredText();
    String dateValue = dateExpr.getStringValue(context, stream);
    String formatValue = formatExpr.getStringValue(context, stream);
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

  public AbstractStringExpression getExpr() {
    return dateExpr;
  }

  public AbstractStringExpression getFormatExpr() {
    return formatExpr;
  }

}
