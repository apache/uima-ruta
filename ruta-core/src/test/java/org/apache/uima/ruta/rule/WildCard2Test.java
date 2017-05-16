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

package org.apache.uima.ruta.rule;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class WildCard2Test {

  @Test
  public void test() throws Exception {
    String document = "Ogren, P.V., Wetzler, P.G., Bethard, S.: ClearTK: A UIMA Toolkit for Statistical Natural Language Processing. In: UIMA for NLP workshop at LREC 08. (2008)";
    document += "\n";
    document += "Stephen Soderland, Claire Cardie, and Raymond Mooney. Learning Information Extraction Rules for Semi-Structured and Free Text. In Machine Learning, volume 34, pages 233–272, 1999.";
    String script = "";
    script += "RETAINTYPE(BREAK, SPACE);\n";
    script += "#{-> T6} BREAK #{-> T6};\n";
    script += "T6{-> TRIM(BREAK, SPACE)};\n";
    script += "CW{REGEXP(\".\")} PERIOD{->T7};\n";
    script += "RETAINTYPE;\n";
    script += "BLOCK(forEach) T6 {}{\n";
    script += "(# COLON){-> T1} (# PERIOD){-> T2} # \"(\" NUM{REGEXP(\"....\")-> T3} \")\";\n";
    script += "(#{-CONTAINS(COLON)} PERIOD{-PARTOF(T7)}){-> T1} (# PERIOD){-> T2} # NUM{REGEXP(\"....\")-> T3};\n";
    script += "}\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "Ogren, P.V., Wetzler, P.G., Bethard, S.:",
            "Stephen Soderland, Claire Cardie, and Raymond Mooney.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2,
            "ClearTK: A UIMA Toolkit for Statistical Natural Language Processing.",
            "Learning Information Extraction Rules for Semi-Structured and Free Text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "2008", "1999");

    cas.release();
  }

  @Test
  public void testOptional() throws Exception {
    String document = "Cw 1 2 3";
    String script = "(CW #){-> T1} SW?;";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Cw 1 2 3");

    cas.release();
  }

  @Test
  public void testLookaheadInGreedy() throws Exception {
    String document = "Some test. Some test. Some test.";
    String script = "((CW #){-> T1})+;";
    script += "((# PERIOD){-> T2})+;";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    // UIMA-4715: should be
    // RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "Some test.", "Some test.", "Some test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "Some test. Some test. Some test.",
            "Some test. Some test.", "Some test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "Some test.", "Some test.", "Some test.");

    cas.release();
  }

  @Test
  public void testMatchAtDocumentBegin() throws Exception {
    String document = "First test. Some test. Last test.";
    String script = "# CW{-> T1};";
    script += "CW{-> T2};";
    script += "T1 # (T2 #){->T3};";
    script += "T3->{# CW{->T4};};";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "First");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "First", "Some", "Last");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some test. Last test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "Some");
    cas.release();
  }

  @Test
  public void testInReverseForEachWithAnalysisEngineAndFiltering() throws Exception {
    String document = "a b (A) a . c (1 C) a . d (a C) .";
    String script = "UIMAFIT org.apache.uima.ruta.engine.UimaFitAnalysisEngineWithManditoryParameter (type, "
            + RutaTestUtils.TYPE + "10);";
    script += "EXEC(UimaFitAnalysisEngineWithManditoryParameter);";
    script += "ADDFILTERTYPE(PERIOD);";
    script += "FOREACH(cw,false) CW {}{\n";
    script += "(SPECIAL==\"(\" # cw SPECIAL.ct==\"]\"){->T1};";
    script += "(SPECIAL==\"(\" # cw SPECIAL.ct==\")\"){->T2};";
    script += "}";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "(A)", "(1 C)", "(a C)");
    cas.release();
  }

}
