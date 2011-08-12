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

import org.apache.commons.collections.ListUtils;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.list.ListExpression;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class MergeAction extends AbstractTextMarkerAction {

  private List<ListExpression> lists;

  private BooleanExpression unionExpr;

  private String target;

  public MergeAction(BooleanExpression union, String target, List<ListExpression> list) {
    super();
    this.unionExpr = union;
    this.target = target;
    this.lists = list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    boolean union = unionExpr.getBooleanValue(element.getParent());
    List<Object> list = new ArrayList<Object>();
    if (union) {
      for (ListExpression<Object> each : lists) {
        list.addAll(each.getList(element.getParent()));
      }
    } else {
      List<Object> lastList = null;
      for (int i = 1; i < lists.size(); i++) {
        if (lastList != null) {
          lastList = ListUtils.intersection(lastList, lists.get(i).getList(element.getParent()));
        } else {
          lastList = ListUtils.intersection(lists.get(i - 1).getList(element.getParent()), lists
                  .get(i).getList(element.getParent()));
        }
      }
      list = lastList;
    }
    if (list != null) {
      element.getParent().getEnvironment().setVariableValue(target, list);
    }
  }

  public BooleanExpression getUnion() {
    return unionExpr;
  }

  public List<ListExpression> getLists() {
    return lists;
  }

  public String getTarget() {
    return target;
  }

}
