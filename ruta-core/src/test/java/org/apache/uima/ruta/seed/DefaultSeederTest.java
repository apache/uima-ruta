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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;

public class DefaultSeederTest {

  @Test
  public void test() throws Exception {

    String text = "Different kinds of tokens like ApacheUIMA % &amp; <nomarkup !?.;:,"
            + " and also <FONT CLASS=\"western\" ALIGN=JUSTIFY "
            + "STYLE=\"margin-bottom: 0cm\">html <b>markup</b></FONT>" + "<br/>\n";
    CAS cas = RutaTestUtils.getCAS(text);

    DefaultSeeder seeder = new DefaultSeeder();
    Type type = seeder.seed(text, cas);
    assertThat(type.getName()).isEqualTo("org.apache.uima.ruta.type.TokenSeed");
    AnnotationIndex<AnnotationFS> annotationIndex = cas.getAnnotationIndex(type);
    assertThat(annotationIndex.size()).isEqualTo(40);
    FSIterator<AnnotationFS> iterator = annotationIndex.iterator();

    assertThat(iterator.next().getType().getShortName()).isEqualTo("CW");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SW");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SW");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SW");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SW");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("CW");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("CAP");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPECIAL");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("AMP");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPECIAL");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SW");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("EXCLAMATION");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("QUESTION");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("PERIOD");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SEMICOLON");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("COLON");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("COMMA");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SW");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SW");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("MARKUP");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SW");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SPACE");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("MARKUP");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("SW");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("MARKUP");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("MARKUP");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("MARKUP");
    assertThat(iterator.next().getType().getShortName()).isEqualTo("BREAK");

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
    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_SEEDERS, new String[] { DefaultSeeder.class.getName() });
    Ruta.apply(cas, script, params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 8,
            "<xref ref-type=\"bibr\" rid=\"b35-ehp0113-000220\">", "<sec sec-type=\"methods\">",
            "<sec sectype=\"methods\">", "<sec sec-type=\"methods\">", "<sec sectype=\"methods\">",
            "<sec sectype='methods'>", "<tag-with-dash value=\"1\">", "<a_real_tag value=\"1\">");

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
    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_SEEDERS, new String[] { DefaultSeeder.class.getName() });
    Ruta.apply(cas, script, params);

    Type t1 = RutaTestUtils.getTestType(cas, 1);
    AnnotationIndex<AnnotationFS> ai = cas.getAnnotationIndex(t1);
    assertThat(ai).hasSize(2000);

    cas.release();

  }

  @Test
  public void testMultiLineXmlComment() throws Exception {

    String document = "Text text <!-- some \n\r more text --> text text.";
    String script = "ALL{-> T1};\n";
    script += "ADDRETAINTYPE(MARKUP);\n";
    script += "ALL{-> T2};\n";
    script += "MARKUP{-> T3};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_SEEDERS, new String[] { DefaultSeeder.class.getName() });
    Ruta.apply(cas, script, params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 5, "Text", "text", "text", "text", ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 6, "Text", "text", "<!-- some \n\r more text -->",
            "text", "text", ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "<!-- some \n\r more text -->");
  }

}
