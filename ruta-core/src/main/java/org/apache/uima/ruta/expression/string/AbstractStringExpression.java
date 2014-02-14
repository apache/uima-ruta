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

package org.apache.uima.ruta.expression.string;

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;

public abstract class AbstractStringExpression extends RutaExpression implements IStringExpression {

  public String getStringValue(RutaBlock parent, RuleMatch match, RuleElement element,
          RutaStream stream) {
    List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOf(element);
    // TODO: do we need to select the correct annotation?
    AnnotationFS annotation = null;
    if (!matchedAnnotationsOf.isEmpty()) {
      annotation = matchedAnnotationsOf.get(0);
    }
    return getStringValue(parent, annotation, stream);
  }

  public abstract String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream);

}
