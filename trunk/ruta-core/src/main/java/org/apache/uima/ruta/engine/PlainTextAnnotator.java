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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;

public class PlainTextAnnotator extends JCasAnnotator_ImplBase {

  public static final String TYPE_LINE = "org.apache.uima.ruta.type.Line";

  public static final String TYPE_WSLINE = "org.apache.uima.ruta.type.WSLine";
  
  public static final String TYPE_EMPTYLINE = "org.apache.uima.ruta.type.EmptyLine";

  public static final String TYPE_PARAGRAPH = "org.apache.uima.ruta.type.Paragraph";

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    CAS cas = jcas.getCas();
    String documentText = cas.getDocumentText();
    BufferedReader br = new BufferedReader(new StringReader(documentText));
    Type lineType = cas.getTypeSystem().getType(TYPE_LINE);
    Type wsLineType = cas.getTypeSystem().getType(TYPE_WSLINE);
    Type emptyLineType = cas.getTypeSystem().getType(TYPE_EMPTYLINE);
    Type paragraphType = cas.getTypeSystem().getType(TYPE_PARAGRAPH);

    int offsetTillNow = 0;
    int paragraphBegin = -1;
    int lastLineEnd = 0;

    boolean lastWasEmpty = true;
    String eachLine = null;
    try {
      while ((eachLine = br.readLine()) != null) {
        boolean wsLine = StringUtils.isBlank(eachLine);
        if(!wsLine && StringUtils.isBlank(eachLine.trim().replaceAll("\u00A0|\u202F|\uFEFF|\u2007|\u180E", ""))) {
          // HOTFIX for NBSPs
          wsLine = true;
        }
        boolean emptyLine = StringUtils.isEmpty(eachLine);
        int offsetAfterLine = offsetTillNow + eachLine.length();
        int nlLength = 1;
        if (documentText.length() >= offsetAfterLine + 2) {
          String substring = documentText.substring(offsetAfterLine, offsetAfterLine + 2);
          if (substring.equals("\r\n")) {
            nlLength = 2;
          }
        }
        if (lastWasEmpty && !wsLine) {
          paragraphBegin = offsetTillNow;
        }

        if (wsLine && emptyLine) {
          // do not create annotation with length 0
          // instead append the line break to the annotation
          AnnotationFS newEmptyLineFS = cas.createAnnotation(emptyLineType, offsetTillNow, offsetTillNow
                  + nlLength);
          cas.addFsToIndexes(newEmptyLineFS);
        } else if (wsLine && !emptyLine) {
          AnnotationFS newWSLineFS = cas.createAnnotation(wsLineType, offsetTillNow, offsetTillNow
                  + eachLine.length());
          cas.addFsToIndexes(newWSLineFS);
        } else if (!emptyLine) {
          AnnotationFS newLineFS = cas.createAnnotation(lineType, offsetTillNow, offsetTillNow
                  + eachLine.length());
          cas.addFsToIndexes(newLineFS);

          lastWasEmpty = false;
          lastLineEnd = offsetTillNow + eachLine.length();
        }
        if (wsLine && !lastWasEmpty && lastLineEnd != 0) {
          AnnotationFS newParaFS = cas.createAnnotation(paragraphType, paragraphBegin, lastLineEnd);
          cas.addFsToIndexes(newParaFS);
        } else if (offsetAfterLine + nlLength == documentText.length()) {
          AnnotationFS newParaFS = cas.createAnnotation(paragraphType, paragraphBegin,
                  offsetAfterLine);
          cas.addFsToIndexes(newParaFS);
        } else if (offsetAfterLine == documentText.length()) {
          AnnotationFS newParaFS = cas.createAnnotation(paragraphType, paragraphBegin,
                  offsetAfterLine);
          cas.addFsToIndexes(newParaFS);
        }
        if (wsLine) {
          lastWasEmpty = true;
        }
        offsetTillNow = offsetTillNow + eachLine.length() + nlLength;
      }
    } catch (IOException e) {
      throw new AnalysisEngineProcessException(e);
    }

  }

}
