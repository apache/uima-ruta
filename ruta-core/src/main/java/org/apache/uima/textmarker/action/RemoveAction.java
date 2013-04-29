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

import org.apache.uima.textmarker.TextMarkerStatement;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.list.ListExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class RemoveAction extends AbstractTextMarkerAction {

  private String var;

  private List<TextMarkerExpression> elements;

  public RemoveAction(String var, List<TextMarkerExpression> list) {
    super();
    this.var = var;
    this.elements = list;
  }

  public String getListExpr() {
    return var;
  }

  public List<TextMarkerExpression> getElements() {
    return elements;
  }

  @SuppressWarnings({ "rawtypes" })
  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    TextMarkerStatement parent = element.getParent();
    List list = parent.getEnvironment().getVariableValue(var, List.class);
    List<Object> toRemove = new ArrayList<Object>();
    for (Object entry : list) {
      Object value1 = getValue(entry, parent);
      for (TextMarkerExpression arg : elements) {
        if(arg instanceof ListExpression) {
          ListExpression l = (ListExpression) arg;
          List list2 = l.getList(parent);
          for (Object object : list2) {
            Object value2 = getValue(object, parent);
            if(value1.equals(value2)) {
              toRemove.add(entry);
            }
          }
        } else {
          Object value2 = getValue(arg, parent);
          if(value1.equals(value2)) {
            toRemove.add(entry);
          }
        }
      }
    }
    for (Object object : toRemove) {
      list.remove(object);
    }
    parent.getEnvironment().setVariableValue(var, list);
  }

  private Object getValue(Object obj, TextMarkerStatement parent) {
    if(obj instanceof NumberExpression) {
      return ((NumberExpression)obj).getDoubleValue(parent);
    } else if(obj instanceof BooleanExpression) {
      return ((BooleanExpression)obj).getBooleanValue(parent);
    } else if(obj instanceof TypeExpression) {
      return ((TypeExpression)obj).getType(parent);
    } else if(obj instanceof StringExpression) {
      return ((StringExpression)obj).getStringValue(parent);
    }
    return null;
  }
  
  
}
