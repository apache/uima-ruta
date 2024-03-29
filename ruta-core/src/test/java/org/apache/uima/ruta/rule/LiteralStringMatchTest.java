/*
 * Licensed to the Apache Software FoSWation (ASF) SWer one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you SWer the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed SWer the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * SWer the License.
 */

package org.apache.uima.ruta.rule;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.seed.DefaultSeeder;
import org.junit.jupiter.api.Test;

public class LiteralStringMatchTest {

  @Test
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 4, "CW", "CW", "CW", "CW");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "SW");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 2, "CW COMMA CW COMMA", "CW COMMA");
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 5, "CW", "CW", "CW", "SW", "CW");
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 5, "CW", "CW", "CW SW CW", "SW CW", "CW");
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 2, "CW COMMA CW COMMA CW", "CW COMMA CW");
    RutaTestUtils.assertAnnotationsEquals(cas, 10, 3, "CW COMMA CW COMMA CW SW", "CW COMMA CW SW",
            "CW SW");
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 3, "CW COMMA CW COMMA CW SW CW PERIOD",
            "CW COMMA CW SW CW PERIOD", "CW SW CW PERIOD");
    RutaTestUtils.assertAnnotationsEquals(cas, 13, 6, "CW COMMA", "CW COMMA", "CW COMMA", "CW SW",
            "CW SW", "CW SW");
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 3, "CW COMMA CW COMMA CW SW", "CW COMMA CW SW",
            "CW SW");

    cas.release();
  }

  @Test
  public void testLiteralMatchingInInvisible() throws Exception {

    String document = "<W a >";
    String script = "";
    script += "\"W\"{-> T1};";

    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_SEEDERS, new String[] { DefaultSeeder.class.getName() });
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script, params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
  }

  @Test
  public void testExamples() throws Exception {

    String document = "breast and ovarian cancer.\r\n"
            + "colorectal, endometrial, and ovarian cancers.\r\n"
            + "colorectal, endometrial and ovarian cancers.\r\n"
            + "vasculopathy of the heart and brain.\r\n"
            + "abnormalities of eyes, nervous system, and kidneys.\r\n"
            + "Prader-Willi and Angelman syndromes\r\n" + "breast or ovarian cancer.\r\n"
            + "breast and/or ovarian cancer.";
    String script = "";
    script += "\"and\"{-> T1};";
    script += "\"or\"{-> T2};";
    script += "\"endometrial, and ovarian\"{-> T3};";
    script += "(\"colorectal\" # \"cancers.\"){-> T4};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 7, "and", "and", "and", "and", "and");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "or", "or");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "endometrial, and ovarian");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2,
            "colorectal, endometrial, and ovarian cancers.",
            "colorectal, endometrial and ovarian cancers.");
  }

  @Test
  public void testMatcherCombination() throws Exception {
    // see UIMA-6195
    String document = "PI_____I How to test it?\nP I_____I How to test it?\n";
    String script = "";
    script += "\"____+\" -> T1;";
    script += "\"I\" T1 \"I\"? { -> MARKONCE(T2,1,2)};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "I_____");
  }

  @Test
  public void testInRutaBasicMatch() throws Exception {

    String document = "1 abcd 2";
    String script = "";
    script += "\"ab\" {-> T1};";
    script += "\"cd\" {-> T2};";
    script += "\"1 ab\" {-> T3};";
    script += "\"cd 2\" {-> T4};";
    script += "NUM \"ab\" {-> T5};";
    script += "\"cd\" {-> T6} @NUM;";

    script += "\"ac\" {-> T7};";
    script += "\"bd\" {-> T8};";
    script += "\"1 ac\" {-> T9};";
    script += "\"bd 2\" {-> T10};";
    script += "NUM \"ac\" {-> T11};";
    script += "\"bd\" {-> T6} @NUM;";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 0);

    RutaTestUtils.assertAnnotationsEquals(cas, 7, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 10, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 12, 0);
  }
}
