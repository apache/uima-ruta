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

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleElementMatch;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class ExpandAction extends MarkAction {

  public ExpandAction(TypeExpression type, List<NumberExpression> list) {
    super(type, null, list);
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<Integer> indexList = getIndexList(match, element);
    AnnotationFS matchedAnnotation = match.getMatchedAnnotation(stream, indexList);

    List<RuleElementMatch> matchInfo = match.getMatchInfo(element);
    if (matchInfo == null || matchInfo.isEmpty()) {
      return;
    }
    RuleElementMatch ruleElementMatch = matchInfo.get(0);
    List<AnnotationFS> textsMatched = ruleElementMatch.getTextsMatched();
    AnnotationFS first = textsMatched.get(0);
    TextMarkerBasic anchor = stream.getFirstBasicInWindow(first);
    Type t = type.getType(element.getParent());
    stream.removeAnnotation(anchor, t);
    createAnnotation(anchor, element, stream, matchedAnnotation);
  }

}
