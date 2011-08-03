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

package org.apache.uima.tm.textmarker.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class FilterMarkupAction extends AbstractTextMarkerAction {

  private List<StringExpression> markup;

  public FilterMarkupAction(List<StringExpression> markup) {
    super();
    this.markup = markup;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<String> list = new ArrayList<String>();
    for (StringExpression each : markup) {
      list.add(each.getStringValue(element.getParent()));
    }
    stream.filterTags(list);
  }

  public List<StringExpression> getMarkup() {
    return markup;
  }

}
