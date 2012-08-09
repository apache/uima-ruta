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

package org.apache.uima.textmarker.engine.html;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.resource.ResourceInitializationException;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlAnnotator extends JCasAnnotator_ImplBase {
  public static final String NAMESPACE = "org.apache.uima.textmarker.type.html.";

  public static final String PLAIN_TEXT_OUTPUT = "plainTextOutput";

  public static final String OUTPUT_VIEW_NAME = "outputViewName";

  public static final String ONLY_CONTENT = "onlyContent";

  private Boolean plainTextOutput;

  private String outputViewName;

  private Boolean onlyContent;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    plainTextOutput = (Boolean) aContext.getConfigParameterValue(PLAIN_TEXT_OUTPUT);
    outputViewName = (String) aContext.getConfigParameterValue(OUTPUT_VIEW_NAME);
    onlyContent = (Boolean) aContext.getConfigParameterValue(ONLY_CONTENT);
    plainTextOutput = plainTextOutput == null ? false : plainTextOutput;
    onlyContent = onlyContent == null ? true : onlyContent;
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    CAS cas = jcas.getCas();
    JCas outJCas = jcas;
    CAS outCas = cas;

    String documentText = cas.getDocumentText();
    String plainTextDocument = documentText;
    List<AnnotationDelta> annotationList = new ArrayList<AnnotationDelta>();
    List<Remarks> remarks = new ArrayList<Remarks>();

    try {
      Parser parser = new Parser(documentText);
      NodeList list = parser.parse(null);
      HtmlVisitor visitor = new HtmlVisitor(jcas, plainTextOutput, onlyContent);
      list.visitAllNodesWith(visitor);
      plainTextDocument = visitor.getPlainTextDocument();
      remarks = visitor.getRemarks();
      annotationList = visitor.getAnnotationList();
    } catch (ParserException e) {
      throw new AnalysisEngineProcessException(e);
    }

    if (plainTextOutput) {
      outCas = cas.createView(outputViewName);
      try {
        outJCas = outCas.getJCas();
      } catch (CASException e) {
        throw new AnalysisEngineProcessException(e);
      }
      outCas.setDocumentText(plainTextDocument);
    }

    for (int k = 0; k < remarks.size(); k++) {
      Remarks remark = remarks.get(k);
      AnnotationFS annotation;
      Type type = outCas.getTypeSystem().getType(NAMESPACE + "REMARK");
      annotation = outCas.createAnnotation(type, remark.getPosition(), remark.getPosition());
      annotation.setFeatureValueFromString(annotation.getType().getFeatureByBaseName("comment"),
              remark.getComment());
      outCas.addFsToIndexes(annotation);
    }
    for (int i = 0; i < annotationList.size(); i++) {
      AnnotationFS annotation;
      AnnotationDelta annoDelta = annotationList.get(i);
      int begin = annoDelta.getAnnotation().getBegin();
      int end = annoDelta.getAnnotation().getEnd() - annoDelta.getDelta();

      StringArray attributeName = new StringArray(outJCas, annoDelta.getAttributeName().size());
      for (int j = 0; j < attributeName.size(); j++) {
        attributeName.set(j, annoDelta.getAttributeName().get(j));
      }
      StringArray attributeValue = new StringArray(outJCas, annoDelta.getAttributeValue().size());
      for (int k = 0; k < attributeName.size(); k++) {
        attributeValue.set(k, annoDelta.getAttributeValue().get(k));
      }
      if(begin == 0 && end == 0) {
        begin = 0;
        end = outCas.getDocumentAnnotation().getEnd();
      }
      annotation = outCas.createAnnotation(annoDelta.getAnnotation().getType(), begin, end);

      annotation.setFeatureValue(annotation.getType().getFeatureByBaseName("attributeName"),
              attributeName);
      annotation.setFeatureValue(annotation.getType().getFeatureByBaseName("attributeValue"),
              attributeValue);

      annotation.setFeatureValueFromString(annotation.getType().getFeatureByBaseName("name"),
              annoDelta.getName());
      outCas.addFsToIndexes(annotation);
    }
  }

}
