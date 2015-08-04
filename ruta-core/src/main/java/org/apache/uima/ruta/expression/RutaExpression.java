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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.RutaStream;

public class RutaExpression extends RutaElement implements IRutaExpression {

  protected List<AnnotationFS> getTargetAnnotation(AnnotationFS annotation, Type type,
          RutaStream stream) {
    if (annotation == null) {
      return Collections.emptyList();
    }
    
    // "autocast" to document annotation when mentioning document.
    // This is either the actual document annotation or the current one in a block or inlined rule
    AnnotationFS documentAnnotation = stream.getCas().getDocumentAnnotation();
    Type docType = documentAnnotation.getType();
    if(docType.equals(type)) {
        List<AnnotationFS> result = new ArrayList<AnnotationFS>(1);
        AnnotationFS windowAnnotation = stream.getFilter().getWindowAnnotation();
        if(windowAnnotation == null) {
          result.add(documentAnnotation);
          return result;
        } else {
          result.add(windowAnnotation);
          return result;
        }
    }
    
    if (annotation.getType().equals(type)
            || stream.getCas().getTypeSystem().subsumes(type, annotation.getType())) {
      List<AnnotationFS> result = new ArrayList<AnnotationFS>(1);
      result.add(annotation);
      return result;
    } else {
      Collection<AnnotationFS> beginAnchors = stream.getBeginAnchor(annotation.getBegin())
              .getBeginAnchors(type);
      Collection<AnnotationFS> endAnchors = stream.getEndAnchor(annotation.getEnd()).getEndAnchors(type);
      @SuppressWarnings("unchecked")
      Collection<AnnotationFS> intersection = CollectionUtils
              .intersection(beginAnchors, endAnchors);
      return new ArrayList<AnnotationFS>(intersection);
    }
  }

}
