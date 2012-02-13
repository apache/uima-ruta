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
import java.io.FileOutputStream;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.XMLSerializer;

public class XMIWriter extends JCasAnnotator_ImplBase {

  private static final String OUTPUT = "Output";

  private UimaContext context;

  private String output;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    if (aContext == null && context != null) {
      aContext = context;
    }
    output = (String) aContext.getConfigParameterValue(OUTPUT);
  }

  private static void writeXmi(CAS aCas, File name) throws Exception {
    FileOutputStream out = null;

    try {
      // write XMI
      out = new FileOutputStream(name);
      XmiCasSerializer ser = new XmiCasSerializer(aCas.getTypeSystem());
      XMLSerializer xmlSer = new XMLSerializer(out, false);
      ser.serialize(aCas, xmlSer.getContentHandler());
    } catch (Exception e) {
      throw e;
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    CAS cas = jcas.getCas();

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
        if (!name.endsWith(".xmi")) {
          name = name + ".xmi";
        }
        String parent = f.getParent().endsWith("/") ? f.getParent() : f.getParent() + "/";
        file = new File(parent + output, name);
      }

    }
    try {
      writeXmi(cas, file);
    } catch (Exception e) {
      throw new AnalysisEngineProcessException(e);
    }
  }
}
