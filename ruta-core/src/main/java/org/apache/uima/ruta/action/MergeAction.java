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

package org.apache.uima.ruta.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class MergeAction extends AbstractRutaAction {

  @SuppressWarnings("rawtypes")
  private List<ListExpression> lists;

  private IBooleanExpression unionExpr;

  private String target;

  @SuppressWarnings("rawtypes")
  public MergeAction(IBooleanExpression union, String target, List<ListExpression> list) {
    super();
    this.unionExpr = union;
    this.target = target;
    this.lists = list;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    boolean union = unionExpr.getBooleanValue(element.getParent(), match, element, stream);
    List<Object> list = new ArrayList<Object>();
    if (union) {
      for (ListExpression<Object> each : lists) {
        list.addAll(each.getList(element.getParent(), stream));
      }
    } else {
      List<Object> lastList = null;
      for (int i = 1; i < lists.size(); i++) {
        List l2 = lists.get(i).getList(element.getParent(), stream);
        if (lastList != null) {
          lastList = ListUtils.intersection(lastList, l2);
        } else {
          List l1 = lists.get(i - 1).getList(element.getParent(), stream);
          lastList = ListUtils.intersection(l1, l2);
        }
      }
      list = lastList;
    }
    if (list != null) {
      element.getParent().getEnvironment().setVariableValue(target, list);
    }
  }

  public IBooleanExpression getUnion() {
    return unionExpr;
  }

  @SuppressWarnings("rawtypes")
  public List<ListExpression> getLists() {
    return lists;
  }

  public String getTarget() {
    return target;
  }

}
