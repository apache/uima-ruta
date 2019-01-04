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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public abstract class AbstractRutaAction extends RutaElement {

  private String label;

  public AbstractRutaAction() {
    super();
  }

  public abstract void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd);

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }

  protected List<Integer> getIndexList(List<INumberExpression> indexes, MatchContext context,
          RutaStream stream) {
    RuleElement element = context.getElement();
    List<Integer> indexList = new ArrayList<Integer>();
    if (indexes == null || indexes.isEmpty()) {
      int self = element.getContainer().getRuleElements().indexOf(element) + 1;
      indexList.add(self);
      return indexList;
    }
    int last = Integer.MAX_VALUE - 1;
    for (INumberExpression each : indexes) {
      // no feature matches allowed
      int value = each.getIntegerValue(context, stream);
      for (int i = Math.min(value, last + 1); i < value; i++) {
        indexList.add(i);
      }
      indexList.add(value);
    }
    return indexList;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  protected void addAnnotationToLabel(AnnotationFS annotation, MatchContext context) {
    if (StringUtils.isBlank(label)) {
      return;
    }
    RutaEnvironment environment = context.getParent().getEnvironment();

    Class<?> variableType = environment.getVariableType(label);
    if (List.class.equals(variableType)
            && AnnotationFS.class.equals(environment.getVariableGenericType(label))) {
      environment.setVariableValue(label, Arrays.asList(annotation));
    } else if (AnnotationFS.class.equals(variableType)) {
      environment.setVariableValue(label, annotation);
    }
  }

}
