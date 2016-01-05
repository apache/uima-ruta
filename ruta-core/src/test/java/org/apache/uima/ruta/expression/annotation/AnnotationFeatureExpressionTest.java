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

package org.apache.uima.ruta.expression.annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class AnnotationFeatureExpressionTest {

  @Test
  public void testList() {
    String document = "Some text.";
    String script = "";
    script += "W{-> CREATE(Inner, \"a\"=W)};";
    script += "Document{-> CREATE(Struct, \"as\"=Inner)};";
    script += "Struct.as{->T1};";
    script += "Struct.as.a{->T2};";
    script += "Struct{-> TRIM(PERIOD)};";
    script += "Struct.as{->T3} @PERIOD;";
    script += "Struct.as.a{->T4} @PERIOD;";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "Struct";
    typeMap.put(typeName1, "uima.tcas.Annotation");
    String typeName2 = "Inner";
    typeMap.put(typeName2, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    String fn1 = "as";
    list.add(new TestFeature(fn1, "", "uima.cas.FSArray"));
    list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName2, list);
    String fn2 = "a";
    list.add(new TestFeature(fn2, "", "uima.cas.FSArray"));

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "Some", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "Some", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "Some", "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "Some", "text");
  }
  
  @Test
  public void testIndex() {
    String document = "Some text.";
    String script = "";
    script += "W{-> CREATE(Inner, \"a\"=W)};";
    script += "Document{-> CREATE(Struct, \"as\"=Inner)};";
    script += "Struct.as[0]{->T1};";
    script += "Struct.as[1]{->T2};";
    script += "Struct.as[0].a{->T3};";
    script += "Struct.as[1].a{->T4};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "Struct";
    typeMap.put(typeName1, "uima.tcas.Annotation");
    String typeName2 = "Inner";
    typeMap.put(typeName2, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    String fn1 = "as";
    list.add(new TestFeature(fn1, "", "uima.cas.FSArray"));
    list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName2, list);
    String fn2 = "a";
    list.add(new TestFeature(fn2, "", "uima.cas.FSArray"));

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "text");
  }
  
}
