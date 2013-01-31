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

package org.apache.uima.textmarker.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;

public class PlainTextAnnotator extends JCasAnnotator_ImplBase {

  public static final String TYPE_LINE = "org.apache.uima.textmarker.type.Line";

  public static final String TYPE_WSLINE = "org.apache.uima.textmarker.type.WSLine";

  public static final String TYPE_PARAGRAPH = "org.apache.uima.textmarker.type.Paragraph";

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    CAS cas = jcas.getCas();
    String documentText = cas.getDocumentText();
    BufferedReader br = new BufferedReader(new StringReader(documentText));
    Type lineType = cas.getTypeSystem().getType(TYPE_LINE);
    Type wsLineType = cas.getTypeSystem().getType(TYPE_WSLINE);
    Type paragraphType = cas.getTypeSystem().getType(TYPE_PARAGRAPH);

    int offsetTillNow = 0;
    int paragraphBegin = -1;
    int lastLineEnd = 0;

    boolean lastWasEmpty = true;
    String eachLine = null;
    try {
      while ((eachLine = br.readLine()) != null) {
        boolean wsLine = "".equals(eachLine.trim());
        boolean emptyLine = "".equals(eachLine);
        int offsetAfterLine = offsetTillNow + eachLine.length();
        int nlLength = 1;
        String substring = documentText.substring(offsetAfterLine, offsetAfterLine + 2);
        if (substring.equals("\r\n")) {
          nlLength = 2;
        }

        if (lastWasEmpty && !wsLine) {
          paragraphBegin = offsetTillNow;
        }

        if (wsLine && !emptyLine) {
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
        }
        if(wsLine) {
          lastWasEmpty = true;
        }
        offsetTillNow = offsetTillNow + eachLine.length() + nlLength;
      }
    } catch (IOException e) {
      throw new AnalysisEngineProcessException(e);
    }

  }

  public static void main(String[] args) throws Exception {
    URL url = TextMarkerEngine.class.getClassLoader().getResource("PlainTextAnnotator.xml");
    if (url == null) {
      url = PlainTextAnnotator.class.getClassLoader().getResource(
              "org/apache/uima/textmarker/engine/PlainTextAnnotator.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();
    cas.setDocumentText(FileUtils.file2String(new File(
            "D:/work/workspace-textmarker/Test/input/list1.txt"), "UTF-8"));
    ae.process(cas);
    AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex();
    for (AnnotationFS annotationFS : annotationIndex) {
      System.out.println(annotationFS.getType().getShortName() + " :  "
              + annotationFS.getCoveredText());
    }
  }

}
