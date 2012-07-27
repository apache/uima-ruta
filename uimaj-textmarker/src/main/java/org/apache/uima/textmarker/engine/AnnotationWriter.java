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

import java.io.File;
import java.io.IOException;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;

public class AnnotationWriter extends JCasAnnotator_ImplBase {

  private static final String OUTPUT = "Output";

  private static final String ENCODING = "Encoding";

  private static final String TYPE = "Type";

  private UimaContext context;

  private String output;

  private String type;

  private String encoding;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    if (aContext == null && context != null) {
      aContext = context;
    }
    output = (String) aContext.getConfigParameterValue(OUTPUT);
    type = (String) aContext.getConfigParameterValue(TYPE);
    encoding = (String) aContext.getConfigParameterValue(ENCODING);
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    CAS cas = jcas.getCas();
    Type targetType = cas.getTypeSystem().getType(type);
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(targetType).iterator();

    StringBuilder newDocument = new StringBuilder();
    while (iterator.isValid()) {
      AnnotationFS fs = iterator.get();
      newDocument.append(fs.getCoveredText());
      newDocument.append("\n");
      iterator.moveToNext();
    }


    Type sdiType = cas.getTypeSystem().getType(TextMarkerEngine.SOURCE_DOCUMENT_INFORMATION);

    String filename = "output.txt";
    File file = new File(output, filename);
    if (sdiType != null) {
      FSIterator<AnnotationFS> sdiit = cas.getAnnotationIndex(sdiType).iterator();
      if (sdiit.isValid()) {
        AnnotationFS annotationFS = sdiit.get();
        Feature uriFeature = sdiType.getFeatureByBaseName("uri");
        String stringValue = annotationFS.getStringValue(uriFeature);
        File f = new File(stringValue);
        String name = f.getName();
        if (!name.endsWith(".txt")) {
          name = name + ".txt";
        }
        file = new File(f.getParent() + output, name);
      }

    }
    try {
      FileUtils.saveString2File(newDocument.toString(), file, encoding);
    } catch (IOException e) {
      throw new AnalysisEngineProcessException(e);
    }
  }
}
