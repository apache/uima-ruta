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
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.string.AbstractStringListExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.resource.RutaWordList;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class InListCondition extends TerminalRutaCondition {

  private WordListExpression listExpr;

  private AbstractStringListExpression stringList;

  private IStringExpression arg;

  public InListCondition(WordListExpression listExpr, IStringExpression arg) {
    super();
    this.listExpr = listExpr;
    this.arg = arg;
  }

  public InListCondition(AbstractStringListExpression list, IStringExpression arg) {
    super();
    this.stringList = list;
    this.arg = arg;
  }

  @Override
  public EvaluatedCondition eval(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    AnnotationFS annotation = context.getAnnotation();
    String text = annotation.getCoveredText();
    if (arg != null) {
      text = arg.getStringValue(context, stream);
    }
    if (text == null) {
      return new EvaluatedCondition(this, false);
    }
    if (stringList == null) {
      RutaWordList wordList = listExpr.getList(context, stream);
      boolean contains = false;
      if(wordList != null) {
        contains = wordList.contains(text, false, 0, null, 0, true);
      }
      return new EvaluatedCondition(this, contains);
    }
    List<String> sList = stringList.getList(context, stream);
    boolean contains = sList.contains(text);
    return new EvaluatedCondition(this, contains);
  }

  public WordListExpression getListExpression() {
    return listExpr;
  }

  public AbstractStringListExpression getStringList() {
    return stringList;
  }

  public IStringExpression getArg() {
    return arg;
  }

}
