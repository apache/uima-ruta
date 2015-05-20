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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;
import org.apache.uima.util.XMLInputSource;

public class ExecAction extends CallAction {

  private TypeListExpression typeList;

  private IStringExpression view;

  public ExecAction(String namespace) {
    super(namespace);
  }

  public ExecAction(String ns, TypeListExpression tl, IStringExpression view) {
    this(ns);
    this.typeList = tl;
    this.view = view;
  }

  @Override
  protected void callScript(RutaBlock block, RuleMatch match, RuleElement element,
          RutaStream stream, InferenceCrowd crowd) {
    ScriptApply apply = block.apply(stream, crowd);
    match.addDelegateApply(this, apply);
  }

  @Override
  protected void callEngine(RuleMatch match, InferenceCrowd crowd, AnalysisEngine targetEngine,
          RuleElement element, RutaStream stream) throws ResourceInitializationException,
          AnalysisEngineProcessException {
    CAS cas = stream.getCas();
    if (view != null) {
      String viewName = view.getStringValue(element.getParent(), match, element, stream);
      if (!viewName.equals(CAS.NAME_DEFAULT_SOFA)) {
        cas = cas.getView(viewName);
        AnalysisEngineMetaData metaData = targetEngine.getAnalysisEngineMetaData();
        try {
          String sourceUrlString = metaData.getSourceUrlString();
          if (sourceUrlString != null) {
            AnalysisEngineDescription aed = (AnalysisEngineDescription) UIMAFramework
                    .getXMLParser().parseResourceSpecifier(new XMLInputSource(sourceUrlString));
            AnalysisEngine createEngine = AnalysisEngineFactory.createEngine(aed, viewName);
            targetEngine = createEngine;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    targetEngine.process(cas);

    if (typeList != null && view == null) {
      List<Type> list = typeList.getList(element.getParent(), stream);
      for (Type type : list) {
        AnnotationIndex<AnnotationFS> ai = cas.getAnnotationIndex(type);
        Collection<AnnotationFS> toUpdate = new LinkedList<AnnotationFS>();
        for (AnnotationFS annotationFS : ai) {
          toUpdate.add(annotationFS);
        }
        for (AnnotationFS each : toUpdate) {
          stream.removeAnnotation(each);
        }
        for (AnnotationFS each : toUpdate) {
          stream.addAnnotation(each, true, match);
        }
      }
    }
    
    
  }

  public TypeListExpression getTypeList() {
    return typeList;
  }

  public IStringExpression getView() {
    return view;
  }

  public void setView(IStringExpression view) {
    this.view = view;
  }

}
