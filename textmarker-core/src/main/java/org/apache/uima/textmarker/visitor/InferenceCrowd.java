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

package org.apache.uima.textmarker.visitor;

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.ScriptApply;
import org.apache.uima.textmarker.TextMarkerElement;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.rule.RuleMatch;


public class InferenceCrowd implements TextMarkerInferenceVisitor {

  private final List<TextMarkerInferenceVisitor> visitors;

  public InferenceCrowd(List<TextMarkerInferenceVisitor> visitors) {
    super();
    this.visitors = visitors;
  }

  public void beginVisit(TextMarkerElement element, ScriptApply result) {
    for (TextMarkerInferenceVisitor each : visitors) {
      each.beginVisit(element, result);
    }
  }

  public void endVisit(TextMarkerElement element, ScriptApply result) {
    for (TextMarkerInferenceVisitor each : visitors) {
      each.endVisit(element, result);
    }
  }

  public void finished(TextMarkerStream stream) {
    for (TextMarkerInferenceVisitor each : visitors) {
      each.finished(stream, visitors);
    }
  }

  public void finished(TextMarkerStream stream, List<TextMarkerInferenceVisitor> visitors) {
    finished(stream);
  }

  public void annotationAdded(AnnotationFS annotation, RuleMatch creator) {
    for (TextMarkerInferenceVisitor each : visitors) {
      each.annotationAdded(annotation, creator);
    }
    
  }

}
