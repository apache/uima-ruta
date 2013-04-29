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

package org.apache.uima.ruta.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlAnnotator extends JCasAnnotator_ImplBase {
  public static final String NAMESPACE = "org.apache.uima.ruta.type.html.";

  public static final String ONLY_CONTENT = "onlyContent";

  private Boolean onlyContent;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    onlyContent = (Boolean) aContext.getConfigParameterValue(ONLY_CONTENT);
    onlyContent = onlyContent == null ? true : onlyContent;
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    String documentText = jcas.getDocumentText();
    List<AnnotationFS> annotations = new ArrayList<AnnotationFS>();
    List<AnnotationFS> annotationStack = new ArrayList<AnnotationFS>();
    try {
      Parser parser = new Parser(documentText);
      NodeList list = parser.parse(null);
      HtmlVisitor visitor = new HtmlVisitor(jcas, onlyContent);
      list.visitAllNodesWith(visitor);
      annotations = visitor.getAnnotations();
      annotationStack = visitor.getAnnotationStack();
    } catch (ParserException e) {
      throw new AnalysisEngineProcessException(e);
    }
    for (AnnotationFS each : annotations) {
      if (each.getBegin() < each.getEnd()) {
        jcas.addFsToIndexes(each);
      }
    }
    for (AnnotationFS each : annotationStack) {
      if (each.getBegin() < each.getEnd()) {
        jcas.addFsToIndexes(each);
      }
    }
  }

}
