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

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class MarkOnceAction extends MarkAction {

  public MarkOnceAction(TypeExpression type, NumberExpression scoreValue,
          List<NumberExpression> list) {
    super(type, scoreValue, list);
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    List<Integer> indexList = getIndexList(element, list, stream);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(indexList,
            element.getContainer());
    Type targetType = type.getType(element.getParent());
    for (AnnotationFS matchedAnnotation : matchedAnnotations) {
      boolean partof = false;
      List<RutaBasic> basicsInWindow = stream.getBasicsInWindow(matchedAnnotation);
      for (RutaBasic rutaBasic : basicsInWindow) {
        if (rutaBasic.isPartOf(targetType)) {
          partof = true;
          break;
        }
      }
      if (!partof) {
        createAnnotation(matchedAnnotation, element, stream, match);
      }
    }
  }

}
