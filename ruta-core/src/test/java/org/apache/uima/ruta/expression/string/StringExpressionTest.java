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
package org.apache.uima.ruta.expression.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.jupiter.api.Test;

public class StringExpressionTest {

  @Test
  public void testValidValue() throws Exception {

    String document = "Any - 0 test.";

    String script = "DOUBLE d = 5; BOOLEAN b = true; TYPE t = org.apache.uima.ruta.type.TruePositive;INT i = 4;\n";
    script += "CW{-> CREATE(Struct, \"s\" = d)};";
    script += "SPECIAL{-> CREATE(Struct, \"s\" = b)};";
    script += "NUM{-> CREATE(Struct, \"s\" = t)};";
    script += "SW{-> Struct};";
    script += "s:Struct{PARTOF(SW)-> s.s = i};";

    script += "Struct.s==\"5.0\"{-> T1};";
    script += "Struct.s==\"true\"{-> T2};";
    script += "Struct.s==\"org.apache.uima.ruta.type.TruePositive\"{-> T3};";
    script += "Struct.s==\"4\"{-> T4};";

    Map<String, String> typeMap = new TreeMap<>();
    String typeName = "Struct";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<>();
    List<TestFeature> list = new ArrayList<>();
    featureMap.put(typeName, list);
    String fn = "s";
    list.add(new TestFeature(fn, "", "uima.cas.String"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Any");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "-");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "0");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "test");

  }

  @Test
  public void testStringFeatureAssignment() throws Exception {

    Map<String, String> typeMap = new TreeMap<>();
    typeMap.put("Struct", "uima.tcas.Annotation");
    typeMap.put("Temp", "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<>();
    featureMap.put("Struct", Arrays.asList(new TestFeature("sf", "", "uima.cas.String")));
    featureMap.put("Temp", Arrays.asList(new TestFeature("if", "", "uima.cas.Integer")));

    String document = "This is a test.";

    String script = "";
    script += "INT vi = 2;\n";
    script += "STRING vs = \"T\";\n";
    script += "Document{->Struct, Temp};\n";

//    script += "s:Struct{-> s.sf = \"T\" + 1};\n";
//    script += "s:Struct{s.sf == \"T1\"-> T1};\n";
//
//    script += "s:Struct{-> s.sf = \"T\" + vi};\n";
//    script += "s:Struct{s.sf == \"T2\"-> T2};\n";
//
//    script += "s:Struct{->s.sf = \"T\", s.sf = s.sf + 3};\n";
//    script += "s:Struct{s.sf == \"T3\"-> T3};\n";

    script += "t:Temp{-> t.if = 4};\n";
    script += "s:Struct{->s.sf = \"T\", s.sf = \"T\" + Temp.if};\n";
    script += "s:Struct{s.sf == \"T4\"-> T4};\n";

    script += "t:Temp{-> t.if = 5};\n";
    script += "s:Struct{->s.sf = \"T\" + t.if}<-{t:Temp;};\n";
    script += "s:Struct{s.sf == \"T5\"-> T5};\n";

    script += "t:Temp{-> t.if = 6};\n";
    script += "s:Struct{->s.sf = vs + t.if}<-{t:Temp;};\n";
    script += "s:Struct{s.sf == \"T6\"-> T6};\n";

    script += "s:Struct{->s.sf = vi};\n";
    script += "s:Struct{s.sf == \"2\"-> T7};\n";

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

//    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This is a test.");
//    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "This is a test.");
//    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 1, "This is a test.");
  }

  @Test
  public void testStringVariableAssignment() throws Exception {

    Map<String, String> typeMap = new TreeMap<>();
    typeMap.put("Struct", "uima.tcas.Annotation");
    typeMap.put("Temp", "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<>();
    featureMap.put("Struct", Arrays.asList(new TestFeature("sf", "", "uima.cas.String")));
    featureMap.put("Temp", Arrays.asList(new TestFeature("if", "", "uima.cas.Integer")));

    String document = "This is a test.";

    String script = "";
    script += "INT vi = 2;\n";
    script += "STRING result = \"\";\n";
    script += "STRING st = \"T\";\n";
    script += "Document{->s:Struct, t:Temp, s.sf=\"a\", t.if=9};\n";

    script += "Document{-> result = \"T\" + 1};\n";
    script += "Document{result == \"T1\"-> T1};\n";

    script += "Document{-> result = \"T\" + vi};\n";
    script += "Document{result == \"T2\"-> T2};\n";

    script += "Document{-> result = st +3};\n";
    script += "Document{result == \"T3\"-> T3};\n";

    script += "Document{-> result = 4};\n";
    script += "Document{result == \"4\"-> T4};\n";

    script += "Document{-> result = false};\n";
    script += "Document{result == \"false\"-> T5};\n";

    script += "Document{-> result = Struct.sf + Temp.if};\n";
    script += "Document{result == \"a9\"-> T6};\n";

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, "This is a test.");
  }

}
