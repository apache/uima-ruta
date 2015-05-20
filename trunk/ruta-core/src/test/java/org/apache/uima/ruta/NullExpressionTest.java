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

public class NullExpressionTest {

  @Test
  public void test() {
    String document = "Some text.";
    String script = "";
    script += "CREATE(A, \"a\" = \"test\", \"b\" = SW), CREATE(B, \"a\" = \"test\", \"b\" = SW);\n";
    script += "A.a != null{-> T5};";
    script += "A.b != null{-> T6};";
    script += "A{-> A.a = null, A.b = null};";
    script += "B{-> SETFEATURE(\"a\", null), SETFEATURE(\"b\", null)};";
    script += "A.a == null{-> T1};";
    script += "A.b == null{-> T2};";
    script += "B{FEATURE(\"a\", null)-> T3};";
    script += "B{FEATURE(\"b\", null)-> T4};";

    
    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "A";
    typeMap.put(typeName1, "uima.tcas.Annotation");
    String typeName2 = "B";
    typeMap.put(typeName2, "uima.tcas.Annotation");
    
    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    featureMap.put(typeName2, list);
    String fn1 = "a";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    String fn2 = "b";
    list.add(new TestFeature(fn2, "", "uima.tcas.Annotation"));
    
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, "Some text.");

    cas.release();
  }
}
