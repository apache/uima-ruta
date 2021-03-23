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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Assert;
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
  public void testOptional2() throws Exception {
    String document = "Cw 1 2 3 test";
    String script = "(CW # COLON?){-> T1} SW;";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Cw 1 2 3");
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

  @Test
  public void testMultipleAndQuantifier() throws Exception {
    String document = "1 A ...... 1 X 2 B ...... 2";
    String script = "";
    script += "PM{->T1};\n";
    // optional elements are not optional after wildcard UIMA-5484
    script += "NUM #{-> T2} T1* NUM;\n";
    script += "PM{->T1};\n";
    script += "NUM #{-> T3} T1* NUM;\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "A", "X 2 B", "B");
    Assert.assertEquals(192,
            cas.getAnnotationIndex(cas.getTypeSystem().getType(RutaTestUtils.TYPE + "3")).size());
    cas.release();
  }

  @Test
  public void testCheckInIterator() throws Exception {
    String document = "1 a b c 2 d e f 3";
    String script = "";
    script += "NUM #{-> T1} NUM;\n";
    script += "SW{-> Struct};\n";
    script += "T1->{# s2:Struct.a==null{-> s2.a=SW};};\n";
    script += "T1->{s1:Struct.a!=null # s2:@Struct.a==null{-> s2.a=s1.a};};\n";
    script += "s:Struct{REGEXP(s.a.ct, \"[ad]\") -> T2};\n";

    Map<String, String> complexType = new HashMap<>();
    complexType.put("Struct", CAS.TYPE_NAME_ANNOTATION);
    Map<String, List<TestFeature>> featureMap = new HashMap<>();
    List<TestFeature> list = new ArrayList<>();
    list.add(new TestFeature("a", "", CAS.TYPE_NAME_ANNOTATION));
    featureMap.put("Struct", list);
    CAS cas = RutaTestUtils.getCAS(document, complexType, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "a b c", "d e f");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 6, "a", "b", "c", "d", "e", "f");
    cas.release();
  }

  @Test
  public void testWildCardBetweenSameTypeWithAction() throws Exception {
    String document = "1 a b c 2 d e f 3";
    String script = "NUM{->T1,T1};";
    script += "T1 # t:T1{-> UNMARK(t)};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "1", "1");
  }

  @Test
  public void testConditionAtComposedWithWildcard() throws Exception {
    String document = "1 A a , 2 D d . 3";
    String script = "(NUM #){CONTAINS(CAP)->T1} NUM;";
    script += "((NUM #){CONTAINS(COMMA)}){CONTAINS(PERIOD)-> T2} NUM;";
    script += "((NUM #){CONTAINS(SW)}){CONTAINS(PERIOD)-> T3} NUM;";
    script += "(NUM #){CONTAINS(CAP)->T4} (NUM);";
    script += "((NUM #){CONTAINS(CAP)->T5}) ((NUM));";
    script += "((NUM #){CONTAINS(CAP)->T6}) \"2\";";
    script += "((NUM #){CONTAINS(CAP)->T7}) \"3\";";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "2 D d .");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 0);
  }

  @Test
  public void testDuplicateAnnotation() throws Exception {
    String document = "x x x 1 a b c 2 d e f 3";
    String script = "NUM{->T1, T1};";
    script += "# t:T1{-> T2};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "1", "1");
  }

  @Test
  public void testInlinedRulesAtWildcard() throws Exception {
    String document = "1 a a a 1";
    String script = "NUM #{->T1}<-{PERIOD;} NUM;\n";
    script += "NUM #{->T2}<-{SW;} NUM;\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "a a a");
  }

  @Test
  public void testInlinedRulesAtWildcardWithOptional() throws Exception {
    String document = "1 a a b / A 1";
    String script = "NUM #{->T1} NUM;\n";
    script += "T1{->T2}<-{# COLON? CW;} NUM;\n";
    script += "T2 -> {(#<-{SW # NUM?;} COLON? SPECIAL){-> T3} CW;};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "a a b / A");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "a a b /");
  }

  @Test
  public void testWithFailingNextRuleElement() throws Exception {
    String document = "a b c 1 d e f 2 g h i 3 / 4 m n o";
    String script = "\"a\" -> T1;\n";
    script += "INT a;\n";
    script += "INT t;\n";
    script += "(T1 # NUM{PARSE(a), a<100} ANY{REGEXP(\"[/]\")} NUM{PARSE(t),t<200}){ -> T2};\n";

    CAS cas = RutaTestUtils.getCAS(document, null, null, false);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "a");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
  }

  @Test
  public void testLabelForFailedLookahead() throws Exception {
    String document = "A x B x C x D";
    String script = "(w1:CW{REGEXP(\"A\")} # w2:CW{REGEXP(\"C\")})->{w1{->T1};};";

    CAS cas = RutaTestUtils.getCAS(document, null, null, false);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "A");
  }

}
