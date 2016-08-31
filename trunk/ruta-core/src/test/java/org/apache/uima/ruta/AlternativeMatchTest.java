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

public class AlternativeMatchTest {

  @Test
  public void test() {
    String document = "1 this is a Test.";
    String script = "";
    script += "CW{-> CREATE(T0, \"s\"=\"a\"),CREATE(T0, \"s\"=\"b\")};";
    script += "NUM W W W T0.s==\"b\"{-> T1};";
    script += "NUM W W W T0.s==\"a\"{-> T2};";
    script += "NUM W*? T0.s==\"b\"{-> T3};";
    script += "NUM W*? T0.s==\"a\"{-> T4};";
    script += "NUM W+? T0.s==\"b\"{-> T5};";
    script += "NUM W+? T0.s==\"a\"{-> T6};";
    script += "NUM W[1,8]? T0.s==\"b\"{-> T7};";
    script += "NUM W[1,8]? T0.s==\"a\"{-> T8};";
    script += "NUM W W W W?? T0.s==\"b\"{-> T9};";
    script += "NUM W W W W?? T0.s==\"a\"{-> T10};";
    
    
    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "T0";
    typeMap.put(typeName1, "uima.tcas.Annotation");
    
    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    String fn1 = "s";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 10, 1, "Test");
    
    if (cas != null) {
      cas.release();
    }

  }
}
