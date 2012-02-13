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

package org.apache.uima.textmarker.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.list.ListExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class GetAction extends AbstractTextMarkerAction {

  private ListExpression<TextMarkerExpression> listExpr;

  private String var;

  private StringExpression opExpr;

  public GetAction(ListExpression<TextMarkerExpression> f, String string, StringExpression op) {
    super();
    this.listExpr = f;
    this.var = string;
    this.opExpr = op;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    String op = opExpr.getStringValue(element.getParent());
    List<TextMarkerExpression> list = listExpr.getList(element.getParent());
    if ("dominant".equals(op)) {
      element.getParent().getEnvironment()
              .setVariableValue(var, getDominant(list, element.getParent()));
    }
  }

  private Object getDominant(List<TextMarkerExpression> list, TextMarkerBlock parent) {
    List<Object> objs = new ArrayList<Object>();
    List<Integer> counts = new ArrayList<Integer>();
    for (Object each : list) {
      Object value = each;// getValue(each, parent);
      if (objs.contains(value)) {
        int indexOf = objs.indexOf(value);
        Integer i = counts.get(indexOf);
        counts.set(indexOf, ++i);
      } else {
        counts.add(1);
        objs.add(each);
      }
    }
    Object dominant = null;
    int dominantCount = -1;
    int i = 0;
    for (Object each : objs) {
      int count = counts.get(i++);
      if (count > dominantCount) {
        dominantCount = count;
        dominant = each;
      }
    }
    return dominant;
  }

  public ListExpression<TextMarkerExpression> getListExpr() {
    return listExpr;
  }

  public String getVar() {
    return var;
  }

  public StringExpression getOpExpr() {
    return opExpr;
  }

}
