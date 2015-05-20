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

import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

public class HtmlConverterXmlTest {

  @Test
  public void test() throws Exception {
    String html = "<Parent>\n";
    html += "<Child1>Some content</Child1>\n";
    html += "<Child2 attribute=“someValue” />\n";
    html += "<Child3>More content.</Child3>\n";
    html += "</Parent>\n";

    URL urlA = HtmlAnnotator.class.getClassLoader().getResource("HtmlAnnotator.xml");
    if (urlA == null) {
      urlA = HtmlAnnotator.class.getClassLoader().getResource(
              "org/apache/uima/ruta/engine/HtmlAnnotator.xml");
    }

    URL urlC = HtmlAnnotator.class.getClassLoader().getResource("HtmlConverter.xml");
    if (urlC == null) {
      urlC = HtmlAnnotator.class.getClassLoader().getResource(
              "org/apache/uima/ruta/engine/HtmlConverter.xml");
    }

    XMLInputSource inA = new XMLInputSource(urlA);
    ResourceSpecifier specifierA = UIMAFramework.getXMLParser().parseResourceSpecifier(inA);
    AnalysisEngine aeA = UIMAFramework.produceAnalysisEngine(specifierA);
    aeA.setConfigParameterValue(HtmlAnnotator.PARAM_ONLY_CONTENT, false);
    aeA.reconfigure();

    XMLInputSource inC = new XMLInputSource(urlC);
    ResourceSpecifier specifierC = UIMAFramework.getXMLParser().parseResourceSpecifier(inC);
    AnalysisEngine aeC = UIMAFramework.produceAnalysisEngine(specifierC);
    aeC.setConfigParameterValue(HtmlConverter.PARAM_SKIP_WHITESPACES, false);
    aeC.setConfigParameterValue(HtmlConverter.PARAM_PROCESS_ALL, true);
    aeC.setConfigParameterValue(HtmlConverter.PARAM_GAP_INDUCING_TAGS, new String[] { "child1",
        "child2", "child3" });
    aeC.setConfigParameterValue(HtmlConverter.PARAM_GAP_TEXT, "$");
    aeC.reconfigure();

    CAS cas = aeA.newCAS();
    Type tagType = cas.getTypeSystem().getType(HtmlAnnotator.NAMESPACE + "TAG");
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    cas.setDocumentText(html);
    aeA.process(cas);
    aeC.process(cas);

    CAS plainTextCas = cas.getView(HtmlConverter.DEFAULT_MODIFIED_VIEW);

    assertEquals("$Some content$$More content.", plainTextCas.getDocumentText());

    ai = plainTextCas.getAnnotationIndex(tagType);
    iterator = ai.iterator();
    assertEquals(4, ai.size());
    assertEquals("$Some content$$More content.", iterator.next().getCoveredText());
    assertEquals("$Some content", iterator.next().getCoveredText());
    assertEquals("$", iterator.next().getCoveredText());
    assertEquals("$More content.", iterator.next().getCoveredText());

    cas.release();
  }

  @Test
  public void testExpandOffsets() throws Exception {
    String html = "<Parent>\n";
    html += "<Child1>Some content</Child1>\n";
    html += "<Child2 attribute=“someValue” />\n";
    html += "<Child3>More content.</Child3>\n";
    html += "</Parent>\n";

    URL urlA = HtmlAnnotator.class.getClassLoader().getResource("HtmlAnnotator.xml");
    if (urlA == null) {
      urlA = HtmlAnnotator.class.getClassLoader().getResource(
              "org/apache/uima/ruta/engine/HtmlAnnotator.xml");
    }

    URL urlC = HtmlAnnotator.class.getClassLoader().getResource("HtmlConverter.xml");
    if (urlC == null) {
      urlC = HtmlAnnotator.class.getClassLoader().getResource(
              "org/apache/uima/ruta/engine/HtmlConverter.xml");
    }

    XMLInputSource inA = new XMLInputSource(urlA);
    ResourceSpecifier specifierA = UIMAFramework.getXMLParser().parseResourceSpecifier(inA);
    AnalysisEngine aeA = UIMAFramework.produceAnalysisEngine(specifierA);
    aeA.setConfigParameterValue(HtmlAnnotator.PARAM_ONLY_CONTENT, false);
    aeA.reconfigure();

    XMLInputSource inC = new XMLInputSource(urlC);
    ResourceSpecifier specifierC = UIMAFramework.getXMLParser().parseResourceSpecifier(inC);
    AnalysisEngine aeC = UIMAFramework.produceAnalysisEngine(specifierC);
    aeC.setConfigParameterValue(HtmlConverter.PARAM_SKIP_WHITESPACES, false);
    aeC.setConfigParameterValue(HtmlConverter.PARAM_PROCESS_ALL, true);
    aeC.setConfigParameterValue(HtmlConverter.PARAM_EXPAND_OFFSETS, true);
    aeC.reconfigure();

    CAS cas = aeA.newCAS();
    Type tagType = cas.getTypeSystem().getType(HtmlAnnotator.NAMESPACE + "TAG");
    Feature expandedFeature = tagType.getFeatureByBaseName("expandedOffsets");
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    cas.setDocumentText(html);
    aeA.process(cas);
    aeC.process(cas);

    CAS plainTextCas = cas.getView(HtmlConverter.DEFAULT_MODIFIED_VIEW);

    assertEquals("Some contentMore content.", plainTextCas.getDocumentText());

    ai = plainTextCas.getAnnotationIndex(tagType);
    iterator = ai.iterator();
    assertEquals(4, ai.size());
    AnnotationFS next = null;
    next = iterator.next();
    assertEquals(false, next.getBooleanValue(expandedFeature));
    assertEquals("Some contentMore content.", next.getCoveredText());
    next = iterator.next();
    assertEquals(false, next.getBooleanValue(expandedFeature));
    assertEquals("Some content", next.getCoveredText());
    next = iterator.next();
    boolean b1 = next.getBooleanValue(expandedFeature);
    assertEquals("More content.", next.getCoveredText());
    next = iterator.next();
    boolean b2 = next.getBooleanValue(expandedFeature);
    assertEquals("More content.", next.getCoveredText());
    // for one of these two annotation (with same offsets) the feature must be set to true 
    assertEquals(true, b1 || b2);

    cas.release();
  }
}
