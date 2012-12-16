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

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.textmarker.TextMarkerTestUtils;
import org.apache.uima.textmarker.engine.html.HtmlAnnotator;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

public class HtmlAnnotatorTest {

  @Test
  public void test() throws Exception {
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    String name = namespace + "/" + "test.html";
    URL textURL = HtmlAnnotatorTest.class.getClassLoader().getResource(name);
    File textFile = new File(textURL.toURI());
    String html = FileUtils.file2String(textFile, "UTF-8");
    URL url = HtmlAnnotator.class.getClassLoader().getResource("HtmlAnnotator.xml");
    if (url == null) {
      url = HtmlAnnotator.class.getClassLoader().getResource(
              "org/apache/uima/textmarker/engine/HtmlAnnotator.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();
    Type tagType = cas.getTypeSystem().getType(HtmlAnnotator.NAMESPACE + "TAG");
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    ae.setConfigParameterValue("plainTextOutput", false);
    ae.setConfigParameterValue("outputViewName", "");
    ae.setConfigParameterValue("onlyContent", false);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(html);
    ae.process(cas);
    ai = cas.getAnnotationIndex(tagType);
    iterator = ai.iterator();
    assertEquals(7, ai.size());
    AnnotationFS a = iterator.next();
    assertEquals("HTML", a.getType().getShortName());
    assertEquals(0, a.getBegin());
    assertEquals(127, a.getEnd());
    a = iterator.next();
    assertEquals("HEAD", a.getType().getShortName());
    assertEquals(8, a.getBegin());
    assertEquals(39, a.getEnd());
    a = iterator.next();
    assertEquals("REMARK", a.getType().getShortName());
    assertEquals(16, a.getBegin());
    assertEquals(30, a.getEnd());
    a = iterator.next();
    assertEquals("BODY", a.getType().getShortName());
    assertEquals(41, a.getBegin());
    assertEquals(118, a.getEnd());
    a = iterator.next();
    assertEquals("BR", a.getType().getShortName());
    assertEquals(64, a.getBegin());
    assertEquals(69, a.getEnd());
    a = iterator.next();
    assertEquals("B", a.getType().getShortName());
    assertEquals(78, a.getBegin());
    assertEquals(89, a.getEnd());
    a = iterator.next();
    assertEquals("BR", a.getType().getShortName());
    assertEquals(91, a.getBegin());
    assertEquals(96, a.getEnd());

    ae.setConfigParameterValue("plainTextOutput", false);
    ae.setConfigParameterValue("outputViewName", "");
    ae.setConfigParameterValue("onlyContent", true);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(html);
    ae.process(cas);
    ai = cas.getAnnotationIndex(tagType);
    iterator = ai.iterator();
    assertEquals(7, ai.size());
    a = iterator.next();
    

    ae.setConfigParameterValue("plainTextOutput", true);
    ae.setConfigParameterValue("outputViewName", "plain");
    ae.setConfigParameterValue("onlyContent", false);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(html);
    ae.process(cas);

    ae.setConfigParameterValue("plainTextOutput", false);
    ae.setConfigParameterValue("outputViewName", "plain");
    ae.setConfigParameterValue("onlyContent", true);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(html);
    ae.process(cas);

  }
}
