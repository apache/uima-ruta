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
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class FillAction extends AbstractStructureAction {

  private Map<StringExpression, TextMarkerExpression> features;

  private TypeExpression structureType;

  public FillAction(TypeExpression structureType,
          Map<StringExpression, TextMarkerExpression> features) {
    super();
    this.structureType = structureType;
    this.features = features;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(stream, null,
            element.getContainer());
    for (AnnotationFS matchedAnnotation : matchedAnnotations) {
      if (matchedAnnotation == null) {
        return;
      }
      Type type = getStructureType().getType(element.getParent());
      List<AnnotationFS> list = stream.getAnnotationsInWindow(matchedAnnotation, type);
      if (list.isEmpty()) {
        list = stream.getOverappingAnnotations(matchedAnnotation, type);
      }
      //
      // for (AnnotationFS each : list) {
      // fillFeatures((Annotation)each, features, matchedAnnotation, element, stream);
      // }
      if (!list.isEmpty()) {
        AnnotationFS annotationFS = list.get(0);
        stream.getCas().removeFsFromIndexes(annotationFS);
        fillFeatures((Annotation) annotationFS, features, matchedAnnotation, element, stream);
        stream.getCas().addFsToIndexes(annotationFS);
      }
    }

  }

  public Map<StringExpression, TextMarkerExpression> getFeatures() {
    return features;
  }

  public TypeExpression getStructureType() {
    return structureType;
  }

}
