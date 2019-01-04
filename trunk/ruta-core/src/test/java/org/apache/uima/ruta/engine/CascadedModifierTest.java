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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;
import org.xml.sax.SAXException;

public class CascadedModifierTest {

  @Test
  public void test() throws IOException, URISyntaxException, InvalidXMLException,
          ResourceInitializationException, ResourceConfigurationException,
          AnalysisEngineProcessException, SAXException {

    URL url = RutaEngine.class.getClassLoader().getResource("AAE.xml");
    if (url == null) {
      url = RutaTestUtils.class.getClassLoader().getResource("org/apache/uima/ruta/engine/AAE.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();
    CAS mCas = cas.createView("global1");
    mCas.setDocumentText("Peter is tired.");
    ae.process(mCas);

    Type cwswType = cas.getTypeSystem().getType("Simple.CwSw");
    CAS lastCas = cas.getView("global3");
    AnnotationIndex<AnnotationFS> ai = lastCas.getAnnotationIndex(cwswType);
    assertEquals(1, ai.size());
    FSIterator<AnnotationFS> iterator = ai.iterator();
    AnnotationFS a = iterator.next();
    assertEquals("CW SW", a.getCoveredText());

    cas.release();
    ae.destroy();
  }

}
