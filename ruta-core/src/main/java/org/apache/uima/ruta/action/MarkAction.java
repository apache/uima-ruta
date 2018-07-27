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

import java.util.List;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaAnnotation;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class MarkAction extends AbstractMarkAction {

  protected final INumberExpression score;

  protected final List<INumberExpression> list;

  public MarkAction(ITypeExpression type, INumberExpression scoreValue, List<INumberExpression> list) {
    super(type);
    this.score = scoreValue;
    this.list = list;
  }

  @Override
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    RuleMatch match = context.getRuleMatch();
    RuleElement element = context.getElement();
    List<Integer> indexList = getIndexList(context, list, stream);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(indexList,
            element.getContainer());
    for (AnnotationFS matchedAnnotation : matchedAnnotations) {
      if (matchedAnnotation == null) {
        return;
      }
      if (score == null) {
        createAnnotation(matchedAnnotation, context, stream);
      } else {
        double deltaScore = score.getDoubleValue(context, stream);
        updateHeuristicAnnotation(context, stream, matchedAnnotation, deltaScore);
      }
    }

  }

  protected void updateHeuristicAnnotation(MatchContext context, RutaStream stream,
          AnnotationFS matchedAnnotation, double deltaScore) {
    
    Annotation targetAnnotation = null;

    List<AnnotationFS> annotationsInSpan = CasUtil.selectAt(stream.getCas(),
            this.type.getType(context, stream), matchedAnnotation.getBegin(),
            matchedAnnotation.getEnd());
    if (annotationsInSpan.isEmpty()) {
      targetAnnotation = this.createAnnotation(matchedAnnotation, context, stream);
    } else {
      targetAnnotation = (Annotation) annotationsInSpan.get(0);
      
    }

    if (targetAnnotation == null) {
      return;
    }

    RutaAnnotation rutaAnnotation = stream.getRutaAnnotationFor(targetAnnotation, true, stream);
    stream.removeAnnotation(rutaAnnotation);
    double newScore = rutaAnnotation.getScore() + deltaScore;
    rutaAnnotation.setScore(newScore);
    rutaAnnotation.addToIndexes();
    stream.addAnnotation(rutaAnnotation, context.getRuleMatch());
  }

  public INumberExpression getScore() {
    return score;
  }

  public List<INumberExpression> getList() {
    return list;
  }
}
