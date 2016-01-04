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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class CreateAction extends AbstractStructureAction {

  private ITypeExpression structureType;

  private Map<IStringExpression, IRutaExpression> features;

  private List<INumberExpression> indexes;

  public CreateAction(ITypeExpression structureType,
          Map<IStringExpression, IRutaExpression> features, List<INumberExpression> indexes) {
    super();
    this.structureType = structureType;
    this.features = features == null ? new HashMap<IStringExpression, IRutaExpression>() : features;
    this.indexes = (indexes == null || indexes.isEmpty()) ? null : indexes;
  }

  @Override
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    RuleMatch match = context.getRuleMatch();
    RuleElement element = context.getElement();
    List<Integer> indexList = getIndexList(indexes, context, stream);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(indexList,
            element.getContainer());
    for (AnnotationFS matchedAnnotation : matchedAnnotations) {

      if (matchedAnnotation == null) {
        return;
      }
      Type type = structureType.getType(context, stream);
      AnnotationFS annotation = stream.getCas().createAnnotation(type, 0, 0);
      if (annotation instanceof Annotation) {
        Annotation a = (Annotation) annotation;
        a.setBegin(matchedAnnotation.getBegin());
        a.setEnd(matchedAnnotation.getEnd());
        context.setAnnotation(matchedAnnotation);
        stream.assignFeatureValues(annotation, features, context);
        stream.addAnnotation(a, true, match);
      }
    }
  }

  public ITypeExpression getStructureType() {
    return structureType;
  }

  public Map<IStringExpression, IRutaExpression> getFeatures() {
    return features;
  }

  public List<INumberExpression> getIndexes() {
    return indexes;
  }
}
