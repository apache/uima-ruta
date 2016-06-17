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
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationListExpression;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.SimpleFeatureExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class RutaExpression extends RutaElement implements IRutaExpression {

  protected List<AnnotationFS> getTargetAnnotation(AnnotationFS matchedAnnotation,
          FeatureExpression fe, MatchContext context, RutaStream stream) {

    if (fe instanceof SimpleFeatureExpression) {
      SimpleFeatureExpression sfe = (SimpleFeatureExpression) fe;
      IAnnotationExpression annotationExpression = sfe.getMatchReference()
              .getAnnotationExpression(context, stream);
      IAnnotationListExpression annotationListExpression = sfe.getMatchReference()
              .getAnnotationListExpression(context, stream);
      if (annotationExpression != null) {
        List<AnnotationFS> as = new ArrayList<>(1);
        as.add(annotationExpression.getAnnotation(context, stream));
        return as;
      } else if (annotationListExpression != null) {
        return annotationListExpression.getAnnotationList(context, stream);
      }
    }

    if (matchedAnnotation == null) {
      return Collections.emptyList();
    }
    // TODO refactor

    Type type = fe.getInitialType(context, stream);

    // "autocast" to document annotation when mentioning document.
    // This is either the actual document annotation or the current one in a block or inlined rule
    AnnotationFS documentAnnotation = stream.getCas().getDocumentAnnotation();
    Type docType = documentAnnotation.getType();
    if (docType.equals(type)) {
      List<AnnotationFS> result = new ArrayList<AnnotationFS>(1);
      AnnotationFS windowAnnotation = stream.getFilter().getWindowAnnotation();
      if (windowAnnotation == null) {
        result.add(documentAnnotation);
        return result;
      } else {
        result.add(windowAnnotation);
        return result;
      }
    }
    
    return stream.getBestGuessedAnnotationsAt(matchedAnnotation, type);
    
  }

}
