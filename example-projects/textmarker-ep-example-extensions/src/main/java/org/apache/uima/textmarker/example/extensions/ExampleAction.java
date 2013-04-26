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

package org.apache.uima.textmarker.example.extensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class ExampleAction extends AbstractTextMarkerAction {

  private List<NumberExpression> indexExprList;

  public ExampleAction(List<NumberExpression> indexExprList) {
    super();
    this.indexExprList = indexExprList;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<Integer> indexes = new ArrayList<Integer>();
    for (NumberExpression each : indexExprList) {
      int integerValue = each.getIntegerValue(element.getParent());
      indexes.add(integerValue);
    }
    List<RuleElement> ruleElements = element.getContainer().getRuleElements();
    for (Integer each : indexes) {
      if (each > 0 && each <= ruleElements.size()) {
        Type type = getRandomType(stream);
        RuleElement ruleElement = ruleElements.get(each-1);
        List<AnnotationFS> matchedAnnotationsOf = match
                .getMatchedAnnotationsOf(ruleElement, stream);
        for (AnnotationFS eachMatched : matchedAnnotationsOf) {
          AnnotationFS newAFS = stream.getCas().createAnnotation(type, eachMatched.getBegin(),
                  eachMatched.getEnd());
          stream.addAnnotation(newAFS, true, match);
        }
      }
    }
  }

  private Type getRandomType(TextMarkerStream stream) {
    Type annotationType = stream.getCas().getAnnotationType();
    TypeSystem typeSystem = stream.getCas().getTypeSystem();
    List<Type> subsumedTypes = typeSystem.getProperlySubsumedTypes(annotationType);
    Random r = new Random();
    int nextInt = r.nextInt(subsumedTypes.size());
    return subsumedTypes.get(nextInt);
  }

  public List<NumberExpression> getIndexExprList() {
    return indexExprList;
  }

  public void setIndexExprList(List<NumberExpression> indexExprList) {
    this.indexExprList = indexExprList;
  }

}
