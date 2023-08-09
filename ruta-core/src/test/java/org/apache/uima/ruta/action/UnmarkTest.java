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

package org.apache.uima.ruta.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.jupiter.api.Test;

public class UnmarkTest {

  @Test
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);

    cas.release();
  }

  @Test
  public void testAnnotationExpression() throws Exception {
    Map<String, String> typeMap = new LinkedHashMap<String, String>();
    typeMap.put("Complex", "uima.tcas.Annotation");
    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<>();
    featureMap.put("Complex", list);
    list.add(new TestFeature("inner", "", "uima.tcas.Annotation"));

    CAS cas = RutaTestUtils.getCAS("This is a test.", typeMap, featureMap);
    String script = "";
    script += "CW{->T1};t:T1 SW SW{-> UNMARK(t)};";
    script += "CW{->T2};\n t:T2 # PERIOD{-> Complex, Complex.inner=t};\n Complex{-> UNMARK(Complex.inner)};\n";
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);

  }

  @Test
  public void testAnnotationListExpression() throws Exception {
    Map<String, String> typeMap = new LinkedHashMap<String, String>();
    typeMap.put("Complex", "uima.tcas.Annotation");
    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<>();
    featureMap.put("Complex", list);
    list.add(new TestFeature("inner", "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS("This is a test.", typeMap, featureMap);
    String script = "";
    script += "W{->T1}; Document{-> Complex, Complex.inner = T1};";
    script += "Complex{-> UNMARK(Complex.inner)};\n";
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
  }

  @Test
  public void testUnmarkWithFeatureMatchInBlock() throws Exception {

    Map<String, String> typeMap = new LinkedHashMap<String, String>();
    typeMap.put("Struct", "uima.tcas.Annotation");
    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<>();
    featureMap.put("Struct", list);
    list.add(new TestFeature("s", "", CAS.TYPE_NAME_STRING));

    CAS cas = RutaTestUtils.getCAS("This is a test.", typeMap, featureMap);
    String script = "\"a\"{->s:Struct,Struct.s=\"foo\"};";
    script += "BLOCK(SoftRemove) Struct.s==\"foo\"{} {\r\n"
            + "    t:Struct.s==\"foo\"{-> UNMARK(t)};\r\n" //
            + "    t:Struct.s==\"foo\"{-> T1}; \r\n" + "}";

    Ruta.apply(cas, script, RutaTestUtils.getDebugParams());

    if (RutaTestUtils.DEBUG_MODE) {
      RutaTestUtils.storeTypeSystem(typeMap, featureMap);
      RutaTestUtils.storeCas(cas, "testUnmarkWithFeatureMatchInBlock");
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
  }

}
