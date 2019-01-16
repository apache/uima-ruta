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

package org.apache.uima.ruta.seed;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.Assert;
import org.junit.Test;

public class DefaultSeederTest {

  @Test
  public void test() throws Exception {
    URL url = RutaEngine.class.getClassLoader().getResource("BasicEngine.xml");
    if (url == null) {
      url = RutaTestUtils.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/BasicEngine.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();

    String text = "Different kinds of tokens like ApacheUIMA % &amp; <nomarkup !?.;:,"
            + " and also <FONT CLASS=\"western\" ALIGN=JUSTIFY "
            + "STYLE=\"margin-bottom: 0cm\">html <b>markup</b></FONT>" + "<br/>\n";
    cas.setDocumentText(text);

    DefaultSeeder seeder = new DefaultSeeder();
    Type type = seeder.seed(text, cas);
    assertEquals("org.apache.uima.ruta.type.TokenSeed", type.getName());
    AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex(type);
    assertEquals(40, annotationIndex.size());
    FSIterator<AnnotationFS> iterator = annotationIndex.iterator();

    assertEquals("CW", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("SW", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("SW", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("SW", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("SW", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("CW", iterator.next().getType().getShortName());
    assertEquals("CAP", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("SPECIAL", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("AMP", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("SPECIAL", iterator.next().getType().getShortName());
    assertEquals("SW", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("EXCLAMATION", iterator.next().getType().getShortName());
    assertEquals("QUESTION", iterator.next().getType().getShortName());
    assertEquals("PERIOD", iterator.next().getType().getShortName());
    assertEquals("SEMICOLON", iterator.next().getType().getShortName());
    assertEquals("COLON", iterator.next().getType().getShortName());
    assertEquals("COMMA", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("SW", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("SW", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("MARKUP", iterator.next().getType().getShortName());
    assertEquals("SW", iterator.next().getType().getShortName());
    assertEquals("SPACE", iterator.next().getType().getShortName());
    assertEquals("MARKUP", iterator.next().getType().getShortName());
    assertEquals("SW", iterator.next().getType().getShortName());
    assertEquals("MARKUP", iterator.next().getType().getShortName());
    assertEquals("MARKUP", iterator.next().getType().getShortName());
    assertEquals("MARKUP", iterator.next().getType().getShortName());
    assertEquals("BREAK", iterator.next().getType().getShortName());

    cas.release();
  }

  @Test
  public void testMarkup() throws Exception {
    String document = "<xref ref-type=\"bibr\" rid=\"b35-ehp0113-000220\">"
            + "<sec sec-type=\"methods\">" + "<sec sectype=\"methods\">"
            + "<sec sec-type=\"methods\">" + "<sec sectype=\"methods\">" + "<sec sectype='methods'>"
            + "<tag-with-dash value=\"1\">" + "<-not-a-real-tag value=\"1\">"
            + "<a_real_tag value=\"1\">";
    String script = "RETAINTYPE(MARKUP);MARKUP{-> T1};";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 8,
            "<xref ref-type=\"bibr\" rid=\"b35-ehp0113-000220\">", "<sec sec-type=\"methods\">",
            "<sec sectype=\"methods\">", "<sec sec-type=\"methods\">", "<sec sectype=\"methods\">",
            "<sec sectype='methods'>", "<tag-with-dash value=\"1\">", "<a_real_tag value=\"1\">");

    cas.release();
  }

  @Test
  public void testStackedMarkup() throws Exception {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
      sb.append("<b>");
    }
    sb.append("content");
    for (int i = 0; i < 1000; i++) {
      sb.append("</b>");
    }

    String document = sb.toString();
    String script = "RETAINTYPE(MARKUP);MARKUP{-> T1};";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    Type t1 = RutaTestUtils.getTestType(cas, 1);
    AnnotationIndex<AnnotationFS> ai = cas.getAnnotationIndex(t1);
    Assert.assertEquals(2000, ai.size());

    cas.release();

  }

  @Test
  public void testVerticalTab() throws Exception {

    String document = "Some \u000b text.";
    String script = "RETAINTYPE(WS);\nBREAK{->T1};";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "\u000b");
  }

}
