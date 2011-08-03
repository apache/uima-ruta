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

package org.apache.uima.tm.textmarker.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.tm.textmarker.kernel.ScriptApply;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerModule;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.list.TypeListExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class ExecAction extends CallAction {

  private TypeListExpression typeList;

  public ExecAction(String namespace) {
    super(namespace);
  }

  public ExecAction(String ns, TypeListExpression tl) {
    this(ns);
    this.typeList = tl;
  }

  @Override
  protected void callScript(String blockName, RuleMatch match, TextMarkerRuleElement element,
          TextMarkerStream stream, InferenceCrowd crowd, TextMarkerModule targetScript) {
    TextMarkerBlock block = targetScript.getBlock(blockName);
    if (block == null) {
      return;
    }
    TextMarkerStream completeStream = stream.getCompleteStream();
    ScriptApply apply = block.apply(completeStream, crowd);
    match.addDelegateApply(this, apply);
  }

  @Override
  protected void callEngine(RuleMatch match, InferenceCrowd crowd, AnalysisEngine targetEngine,
          TextMarkerRuleElement element, TextMarkerStream stream)
          throws ResourceInitializationException, AnalysisEngineProcessException {
    CAS cas = stream.getCas();
    targetEngine.process(cas);

    if (typeList != null) {
      List<Type> list = typeList.getList(element.getParent());
      for (Type type : list) {
        Map<TextMarkerBasic, Collection<AnnotationFS>> map = new HashMap<TextMarkerBasic, Collection<AnnotationFS>>();
        AnnotationIndex<AnnotationFS> ai = cas.getAnnotationIndex(type);
        for (AnnotationFS fs : ai) {
          TextMarkerBasic basic = stream.getFirstBasicInWindow(fs);
          if (basic != null) {
            Collection<AnnotationFS> collection = map.get(basic);
            if (collection == null) {
              collection = new HashSet<AnnotationFS>();
              map.put(basic, collection);
            }
            collection.add(fs);
          }
        }
        Set<Entry<TextMarkerBasic, Collection<AnnotationFS>>> entrySet = map.entrySet();
        for (Entry<TextMarkerBasic, Collection<AnnotationFS>> entry : entrySet) {
          for (AnnotationFS each : entry.getValue()) {
            cas.removeFsFromIndexes(each);
            stream.removeAnnotation(entry.getKey(), each.getType());
            cas.addFsToIndexes(each);
            stream.addAnnotation(entry.getKey(), each);
          }
        }
      }
    }
    System.out.println();
  }
}
