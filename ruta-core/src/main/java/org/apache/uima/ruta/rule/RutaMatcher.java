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

package org.apache.uima.ruta.rule;

import java.util.Collection;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.expression.IRutaExpression;

public interface RutaMatcher {

  /**
   * @return a collection containing the matching annotations. Note that there is no
   *         guarantee that the returned collection is modifiable.
   */
  Collection<? extends AnnotationFS> getMatchingAnnotations(RutaBlock parent, RutaStream stream);

  Type getType(RutaBlock parent, RutaStream stream);

  IRutaExpression getExpression();

  long estimateAnchors(RutaBlock parent, RutaStream stream);

  /**
   * @return a collection containing the matching annotations. Note that there is no
   *         guarantee that the returned collection is modifiable.
   */
  Collection<? extends AnnotationFS> getAnnotationsAfter(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaBlock parent, RutaStream stream);

  /**
   * @return a collection containing the matching annotations. Note that there is no
   *         guarantee that the returned collection is modifiable.
   */
  Collection<? extends AnnotationFS> getAnnotationsBefore(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaBlock parent, RutaStream stream);

}
