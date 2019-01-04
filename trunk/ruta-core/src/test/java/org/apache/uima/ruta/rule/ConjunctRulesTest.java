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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class ConjunctRulesTest {

  @Test
  public void test() throws Exception {
    String document = "Peter Kluegl, Joern Kottmann, Marshall Schor.";
    String script = "DECLARE T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T14, T15, T16, T17, T18;\n";
    script += "W{REGEXP(\"Peter\")} ANY{-> T1} % ANY{REGEXP(\"J.*\")} CW PM{->MARK(T2)} % (COMMA # PM){->MARK(T3)};";
    script += "W{REGEXP(\"Peter\")} ANY{-> T4} % ANY{REGEXP(\"J.*\")} CW PM{->MARK(T5)} % (COMMA # QUESTION){->MARK(T6)};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Kluegl");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, ",");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, ", Joern Kottmann,", ", Marshall Schor.");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 0);

    cas.release();
  }

  @Test
  public void testInlinedRules() throws Exception {

    String document = "This is a test.";
    String script = "(CW # PERIOD) {-> T1};";
    script += "T1{->T2}<-{SW % CW;};";
    script += "T1{->T3}<-{COMMA % CW;};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, document);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
  }

  @Test
  public void testInlinedRulesWithLabels() throws Exception {

    String document = "This is a test.";
    String script = "(CW # PERIOD) {-> T1};";
    script += "T1{->T2}<-{s:W{s.ct==\"is\"} % c:W{c.ct==\"This\"};};";
    script += "T1{->T3}<-{s:W{s.ct==\"missing\"} % c:W{c.ct==\"This\"};};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, document);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
  }

  @Test
  public void testInlinedRulesWithSameMatchingCondition() throws Exception {

    String document = "A B B A X";
    String script = "ANNOTATIONLIST list;\n";
    script += "Document{ -> CREATE(Struct, \"as\" = list)} <- {\n";
    script += "w1:CW{w1.ct==\"A\" -> ADD(list, CW)}\n";
    script += "%\n";
    script += "w2:CW{w2.ct==\"B\" -> ADD(list, CW)}\n";
    script += "%\n";
    script += "w3:CW{w3.ct==\"B\" -> ADD(list, CW)}\n";
    script += "%\n";
    script += "w4:CW{w4.ct==\"A\" -> ADD(list, CW)};\n";
    script += "};\n";
    script += "Struct.as{REGEXP(\"X\")-> T1};\n";
    script += "Struct.as{-> T2};\n";

    Map<String, String> complexTypes = new TreeMap<String, String>();
    String typeName = "Struct";
    complexTypes.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    String fn = "as";
    list.add(new TestFeature(fn, "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS(document, complexTypes, features);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 8, "A", "A", "B", "B", "B", "B", "A", "A");
  }

}
