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

import org.apache.uima.UimaContextHolder;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleElementMatch;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class TransferAction extends TypeSensitiveAction {

  public TransferAction(ITypeExpression type) {
    super(type);
  }

  @Override
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    RuleMatch match = context.getRuleMatch();
    RuleElement element = context.getElement();
    List<List<RuleElementMatch>> list = match.getMatchInfo(element);
    CAS cas = stream.getCas();
    Type t = type.getType(context, stream);

    if (t == null) {
      return;
    }

    for (List<RuleElementMatch> eachList : list) {
      for (RuleElementMatch each : eachList) {
        List<AnnotationFS> matched = each.getTextsMatched();
        for (AnnotationFS annotationFS : matched) {
          FeatureStructure createFS = cas.createFS(t);
          copyFeatures(annotationFS, createFS, cas);
          if (createFS instanceof AnnotationFS) {
            stream.addAnnotation((AnnotationFS) createFS, match);
            addAnnotationToLabel((AnnotationFS) createFS, context);
          }
          cas.addFsToIndexes(createFS);
        }
      }
    }

  }

  private void copyFeatures(AnnotationFS oldFS, FeatureStructure newFS, CAS cas) {
    List<?> features = oldFS.getType().getFeatures();
    Type newType = newFS.getType();
    for (Object object : features) {
      Feature feature = (Feature) object;
      String shortName = feature.getShortName();
      Feature newFeature = newType.getFeatureByBaseName(shortName);
      if (newFeature != null) {
        if (feature.getRange().isPrimitive()) {
          String value = oldFS.getFeatureValueAsString(feature);
          try {
            newFS.setFeatureValueFromString(newFeature, value);
          } catch (Exception e) {
            UimaContextHolder.getContext().getLogger().debug("Unable to transfer feature {}: {}",
                    shortName, e.getMessage());
          }
        } else {
          FeatureStructure value = oldFS.getFeatureValue(feature);
          try {
            newFS.setFeatureValue(newFeature, value);
          } catch (Exception e) {
            UimaContextHolder.getContext().getLogger().debug("Unable to transfer feature {}: {}",
                    shortName, e.getMessage());
          }
        }
      }
    }
  }

}
