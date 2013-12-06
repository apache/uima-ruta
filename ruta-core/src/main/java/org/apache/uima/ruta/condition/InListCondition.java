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

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.list.StringListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.resource.RutaWordList;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class InListCondition extends TerminalRutaCondition {

  private IBooleanExpression relative;

  private final INumberExpression distance;

  private WordListExpression listExpr;

  private StringListExpression stringList;

  public InListCondition(WordListExpression listExpr, INumberExpression distance,
          IBooleanExpression relative) {
    super();
    this.listExpr = listExpr;
    this.distance = distance;
    this.relative = relative;
  }

  public InListCondition(StringListExpression list, INumberExpression distance,
          IBooleanExpression relative) {
    super();
    this.distance = distance;
    this.relative = relative;
    this.stringList = list;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    String coveredText = annotation.getCoveredText();
    if (stringList == null) {
      RutaWordList wordList = listExpr.getList(element.getParent());
      return new EvaluatedCondition(this, wordList.contains(coveredText, false, 0, null, 0, true));
    }
    List<String> sList = stringList.getList(element.getParent(), stream);
    boolean contains = sList.contains(coveredText);
    return new EvaluatedCondition(this, contains);
  }

  public IBooleanExpression getRelative() {
    return relative;
  }

  public INumberExpression getDistance() {
    return distance;
  }

  public WordListExpression getListExpression() {
    return listExpr;
  }

  public StringListExpression getStringList() {
    return stringList;
  }

}
