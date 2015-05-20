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
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * This Analysis Engine provides support for HTML files by adding annotations for the HTML elements.
 * Using the default values, the HTML Annotator creates annotations for each HTML element spanning
 * the content of the element, whereas the most common elements are represented by own types. The
 * document <code>This text is <b>bold</b>.</code>, for example, would be annotated
 * with an annotation of the type <code>org.apache.uima.ruta.type.html.B</code> for the word
 * <code>bold</code>. The HTML annotator can be configured in order to include the start and end
 * elements in the created annotations. A descriptor file for this Analysis Engine is located in the
 * folder <code>descriptor/utils</code> of a UIMA Ruta project.
 * 
 */
public class HtmlAnnotator extends JCasAnnotator_ImplBase {
  public static final String NAMESPACE = "org.apache.uima.ruta.type.html.";

  /**
   * This parameter specifies whether created annotations should cover only the content of the HTML
   * elements or also their start and end elements. The default value is <code>true</code>.
   */
  public static final String PARAM_ONLY_CONTENT = "onlyContent";

  @ConfigurationParameter(name = PARAM_ONLY_CONTENT, mandatory = false, defaultValue = "true")
  private Boolean onlyContent;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    onlyContent = (Boolean) aContext.getConfigParameterValue(PARAM_ONLY_CONTENT);
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
