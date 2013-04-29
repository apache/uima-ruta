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

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.textmarker.TextMarkerTestUtils;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

public class TextMarkerModifierTest {

  @Test
  public void test() throws Exception {
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    URL url = HtmlAnnotator.class.getClassLoader().getResource("Modifier.xml");
    if (url == null) {
      url = HtmlAnnotator.class.getClassLoader().getResource(
              "org/apache/uima/textmarker/engine/Modifier.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngineDescription aed = (AnalysisEngineDescription) specifier;
    
    TypeSystemDescription basicTypeSystem = aed.getAnalysisEngineMetaData().getTypeSystem();
    for (int i = 1; i <= 20; i++) {
      basicTypeSystem.addType(TextMarkerTestUtils.TYPE + i, "Type for Testing", "uima.tcas.Annotation");
    }
    Collection<TypeSystemDescription> tsds = new ArrayList<TypeSystemDescription>();
    tsds.add(basicTypeSystem);
    TypeSystemDescription mergeTypeSystems = CasCreationUtils.mergeTypeSystems(tsds);
    aed.getAnalysisEngineMetaData().setTypeSystem(mergeTypeSystems);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    ae.setConfigParameterValue(TextMarkerModifier.OUTPUT_LOCATION, "");
    String viewName = "modified_for_testing";
    ae.setConfigParameterValue(TextMarkerModifier.OUTPUT_VIEW, viewName);
    ae.reconfigure();
    
    String scriptName = this.getClass().getSimpleName();
    CAS cas = null;
    try {
      cas = TextMarkerTestUtils.process(namespace + "/" + scriptName + ".tm", namespace + "/test.html", 50);
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
    }
    ae.process(cas);
    
    CAS modifiedView = cas.getView(viewName);
    String text = modifiedView.getDocumentText();
    
    assertEquals("start of bodynormal BOLDend of body" , text);
    
    
    cas.release();
    ae.destroy();
  }

  
}
