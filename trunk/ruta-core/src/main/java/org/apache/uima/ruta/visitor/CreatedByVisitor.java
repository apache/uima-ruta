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

package org.apache.uima.ruta.visitor;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.rule.AbstractRule;
import org.apache.uima.ruta.rule.AbstractRuleMatch;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;

public class CreatedByVisitor implements RutaInferenceVisitor {

  public static final String TYPE = "org.apache.uima.ruta.type.DebugCreatedBy";

  public static final String FEATURE_RULE = "rule";

  public static final String FEATURE_ANNOTATION = "annotation";

  public static final String FEATURE_SCRIPT = "script";

  public static final String FEATURE_ID = "id";

  private List<FeatureStructure> fsList = new ArrayList<FeatureStructure>();

  private RutaVerbalizer verbalizer;

  public CreatedByVisitor(RutaVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
  }

  public void beginVisit(RutaElement element, ScriptApply result) {
  }

  public void endVisit(RutaElement element, ScriptApply result) {
  }

  public void finished(RutaStream stream, List<RutaInferenceVisitor> visitors) {
    for (FeatureStructure each : fsList) {
      each.getCAS().addFsToIndexes(each);
    }
  }

  public void annotationAdded(AnnotationFS annotation,
          AbstractRuleMatch<? extends AbstractRule> creator) {
    CAS cas = annotation.getCAS();
    Type t = cas.getTypeSystem().getType(TYPE);
    Feature featureRule = t.getFeatureByBaseName(FEATURE_RULE);
    Feature featureAnnotation = t.getFeatureByBaseName(FEATURE_ANNOTATION);
    Feature featureScript = t.getFeatureByBaseName(FEATURE_SCRIPT);
    Feature featureId = t.getFeatureByBaseName(FEATURE_ID);

    String ruleString = "provided";
    String ruleScript = "";
    int ruleId = -1;
    if (creator != null) {
      ruleString = verbalizer.verbalize(creator.getRule());
      ruleId = creator.getRule().getId();
      ruleScript = creator.getRule().getParent().getScript().getRootBlock().getNamespace();
    }
    FeatureStructure fs = cas.createFS(t);
    fs.setStringValue(featureRule, ruleString);
    fs.setFeatureValue(featureAnnotation, annotation);
    fs.setIntValue(featureId, ruleId);
    fs.setStringValue(featureScript, ruleScript);
    fsList.add(fs);
  }

}
