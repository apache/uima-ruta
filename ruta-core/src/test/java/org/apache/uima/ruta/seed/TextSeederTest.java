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
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.type.MARKUP;
import org.junit.jupiter.api.Test;

public class TextSeederTest {

  @Test
  public void testNoMarkupForXmlComment() throws Exception {

    String document = "Text text <!-- some more text --> text text.";
    String script = "ALL{-> T1};\n";
    script += "ADDRETAINTYPE(MARKUP);\n";
    script += "ALL{-> T2};\n";
    script += "MARKUP{-> T3};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_SEEDERS, new String[] { TextSeeder.class.getName() });
    Ruta.apply(cas, script, params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 15, "Text", "text", "<", "!", "-", "-", "some",
            "more", "text", "-", "-", ">", "text", "text", ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 15, "Text", "text", "<", "!", "-", "-", "some",
            "more", "text", "-", "-", ">", "text", "text", ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);

    assertThat(JCasUtil.select(cas.getJCas(), MARKUP.class).isEmpty()).isTrue();
  }

  @Test
  public void testVerticalTab() throws Exception {

    String document = "Some \u000b text.";
    String script = "RETAINTYPE(WS);\nBREAK{->T1};";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "\u000b");
  }

  @Test
  public void testSpecialChars() throws Exception {

    String document = "Some text  Dr.";
    String script = "RETAINTYPE(WS);\nBREAK{-> T1};";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, " ");
  }
}
