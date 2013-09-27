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

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.rule.AbstractRule;
import org.apache.uima.ruta.rule.AbstractRuleMatch;

public interface RutaInferenceVisitor {

  void beginVisit(RutaElement element, ScriptApply result);

  void endVisit(RutaElement element, ScriptApply result);

  void finished(RutaStream stream, List<RutaInferenceVisitor> visitors);

  void annotationAdded(AnnotationFS annotation, AbstractRuleMatch<? extends AbstractRule> creator);

}
