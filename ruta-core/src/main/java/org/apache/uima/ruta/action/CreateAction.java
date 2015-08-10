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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class CreateAction extends AbstractStructureAction {

  private TypeExpression structureType;

  private Map<IStringExpression, IRutaExpression> features;

  private List<INumberExpression> indexes;

  public CreateAction(TypeExpression structureType, Map<IStringExpression, IRutaExpression> features,
          List<INumberExpression> indexes) {
    super();
    this.structureType = structureType;
    this.features = features == null ? new HashMap<IStringExpression, IRutaExpression>() : features;
    this.indexes = (indexes == null || indexes.isEmpty()) ? null : indexes;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, RutaStream stream, InferenceCrowd crowd) {
    List<Integer> indexList = getIndexList(match, element, stream);
    List<AnnotationFS> matchedAnnotations = match.getMatchedAnnotations(indexList,
            element.getContainer());
    for (AnnotationFS matchedAnnotation : matchedAnnotations) {

      if (matchedAnnotation == null) {
        return;
      }
      Type type = structureType.getType(element.getParent());
      FeatureStructure newFS = stream.getCas().createFS(type);
      if (newFS instanceof Annotation) {
        Annotation a = (Annotation) newFS;
        a.setBegin(matchedAnnotation.getBegin());
        a.setEnd(matchedAnnotation.getEnd());
        stream.addAnnotation(a, match);
      }
      TOP newStructure = null;
      if (newFS instanceof TOP) {
        newStructure = (TOP) newFS;
        fillFeatures(newStructure, features, matchedAnnotation, element, stream);
        newStructure.addToIndexes();
      }
    }
  }

  // TODO refactor duplicate methods -> MarkAction
  protected List<Integer> getIndexList(RuleMatch match, RuleElement element, RutaStream stream) {
    List<Integer> indexList = new ArrayList<Integer>();
    if (indexes == null || indexes.isEmpty()) {
      int self = element.getContainer().getRuleElements().indexOf(element) + 1;
      indexList.add(self);
      return indexList;
    }
    int last = Integer.MAX_VALUE - 1;
    for (INumberExpression each : indexes) {
      // no feature matches allowed
      int value = each.getIntegerValue(element.getParent(), null, stream);
      for (int i = Math.min(value, last + 1); i < value; i++) {
        indexList.add(i);
      }
      indexList.add(value);
    }
    return indexList;
  }

  public TypeExpression getStructureType() {
    return structureType;
  }

  public Map<IStringExpression, IRutaExpression> getFeatures() {
    return features;
  }

  public List<INumberExpression> getIndexes() {
    return indexes;
  }
}
