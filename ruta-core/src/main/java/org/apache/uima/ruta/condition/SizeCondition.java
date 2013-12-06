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

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class SizeCondition extends AbstractRutaCondition {

  private ListExpression<?> listExpr;

  private INumberExpression minExpr;

  private INumberExpression maxExpr;

  private String varExpr;

  public SizeCondition(ListExpression<?> list, INumberExpression min, INumberExpression max,
          String string) {
    super();
    this.listExpr = list;
    this.minExpr = min == null ? new SimpleNumberExpression(Integer.MIN_VALUE) : min;
    this.maxExpr = max == null ? new SimpleNumberExpression(Integer.MAX_VALUE) : max;
    this.varExpr = string;
  }

  @Override
  public EvaluatedCondition eval(AnnotationFS annotation, RuleElement element, RutaStream stream,
          InferenceCrowd crowd) {
    int count = listExpr.getList(element.getParent(), stream).size();
    boolean value = count >= minExpr.getIntegerValue(element.getParent(), annotation, stream)
            && count <= maxExpr.getIntegerValue(element.getParent(), annotation, stream);
    if (varExpr != null) {
      element.getParent().getEnvironment().setVariableValue(varExpr, count);
    }
    return new EvaluatedCondition(this, value);
  }

  public ListExpression<?> getListExpr() {
    return listExpr;
  }

  public INumberExpression getMinExpr() {
    return minExpr;
  }

  public INumberExpression getMaxExpr() {
    return maxExpr;
  }

  public String getVarExpr() {
    return varExpr;
  }
}
