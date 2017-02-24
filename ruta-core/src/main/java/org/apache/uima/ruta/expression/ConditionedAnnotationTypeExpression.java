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

package org.apache.uima.ruta.expression;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ConditionedAnnotationTypeExpression extends AnnotationTypeExpression {

  private AbstractRutaCondition condition;

  public ConditionedAnnotationTypeExpression(MatchReference reference,
          AbstractRutaCondition condition) {
    super(reference);
    this.condition = condition;
  }

  @Override
  public AnnotationFS getAnnotation(MatchContext context, RutaStream stream) {
   List<AnnotationFS> annotationList = getAnnotationList(context, stream);
   if(!annotationList.isEmpty()) {
     return annotationList.get(0);
   }
   return null;
  }

  @Override
  public List<AnnotationFS> getAnnotationList(MatchContext context, RutaStream stream) {
    List<AnnotationFS> annotationList = super.getAnnotationList(context, stream);
    List<AnnotationFS> result = new ArrayList<>();
    for (AnnotationFS annotation : annotationList) {
      if(evalulate(context, stream, annotation)) {
        result.add(annotation);
      }
    }
    return result;
  }

  private boolean evalulate(MatchContext context, RutaStream stream, AnnotationFS annotation) {
    MatchContext localContext = new MatchContext(annotation, context.getElement(), context.getRuleMatch(), context.getDirection());
    EvaluatedCondition eval = condition.eval(localContext, stream, InferenceCrowd.emptyCrowd);
    return eval.isValue();
  }
  
}
