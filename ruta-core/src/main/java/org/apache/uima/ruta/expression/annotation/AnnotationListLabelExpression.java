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

package org.apache.uima.ruta.expression.annotation;

import java.util.List;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.rule.MatchContext;

/**
 * An expression referring to an annotation matched by a rule element identified by a label.
 *
 */
public class AnnotationListLabelExpression extends AbstractAnnotationListExpression {

  private String label;

  public AnnotationListLabelExpression(String label) {
    super();
    this.label = label;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<AnnotationFS> getList(MatchContext context, RutaStream stream) {
    return context.getParent().getEnvironment().getVariableValue(label, List.class, stream);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<FeatureStructure> getFeatureStructureList(MatchContext context, RutaStream stream) {
    return context.getParent().getEnvironment().getVariableValue(label, List.class, stream);
  }

  public String getLabel() {
    return label;
  }

}
