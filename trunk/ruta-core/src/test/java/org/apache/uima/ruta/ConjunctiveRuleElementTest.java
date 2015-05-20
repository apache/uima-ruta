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

package org.apache.uima.ruta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class ConjunctiveRuleElementTest {

  @Test
  public void test() {
    String document = "Peter Kluegl, Joern Kottmann, Marshall Schor.";
    String script = "DECLARE T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T14, T15, T16, T17, T18;\n";
    script += "(W & SW & ANY){->MARK(T1)};\n";
    script += "((CW ANY{REGEXP(\"K.*\")}) & ANY{REGEXP(\"P.*\")}){->MARK(T2)};\n";
    script += "(ALL & ANY & W & CW{REGEXP(\"Schor\")}){->T3};\n";
    script += "((ALL ALL) & ANY & (W PERIOD) & CW{REGEXP(\"Schor\")}){->T4};\n";
    script += "((ALL ALL) & ANY & (W W) & (ANY CW{REGEXP(\"Schor\")})){->T5};\n";
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Peter Kluegl");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Schor");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "Schor.");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "Marshall Schor");

    cas.release();
  }

  @Test
  public void testWithFeatureMatch() {

    String document = "Peter did something.";
    String script = "";
    script += "CW{-> CREATE(Token, \"posTag\" = \"noun\")} SW{-> CREATE(Token, \"posTag\" = \"verb\", \"mood\" = \"p\", \"tense\" = \"p\")} SW;\n";
    script += "(Token.posTag == \"verb\" & Token.mood==\"p\" & Token.tense==\"p\" ){-> T1};\n";
    // script +=
    script += "(Token.posTag == \"noun\" ( Token.posTag == \"verb\" & Token.mood==\"p\" & Token.tense==\"p\" )){-> T3};\n";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "Token";
    typeMap.put(typeName1, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    String fn1 = "posTag";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    String fn2 = "mood";
    list.add(new TestFeature(fn2, "", "uima.cas.String"));
    String fn3 = "tense";
    list.add(new TestFeature(fn3, "", "uima.cas.String"));

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "did");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Peter did");

    cas.release();
  }

  @Test
  public void testWithStartAnchor() {

    String document = "Peter did something.";
    String script = "";
    script += "CW{-> CREATE(Token, \"posTag\" = \"noun\")} SW{-> CREATE(Token, \"posTag\" = \"verb\", \"mood\" = \"p\", \"tense\" = \"p\")} SW;\n";
    script += "(Token.posTag == \"noun\" @( Token.posTag == \"verb\" & Token.mood==\"p\" & Token.tense==\"p\" )){-> T1};\n";
    script += "(Token.posTag == \"noun\" @( Token.posTag == \"verb\" & Token.mood==\"p\" & Token.tense==\"p\" )){-> T2} ANY;\n";
    script += "Token.posTag == \"noun\" @(Token.posTag == \"verb\" ANY){-> T3};\n";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "Token";
    typeMap.put(typeName1, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    String fn1 = "posTag";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    String fn2 = "mood";
    list.add(new TestFeature(fn2, "", "uima.cas.String"));
    String fn3 = "tense";
    list.add(new TestFeature(fn3, "", "uima.cas.String"));

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Peter did");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Peter did");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "did something");

    cas.release();
  }
}
