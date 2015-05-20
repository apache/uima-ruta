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

public class CoveredTextTest {

  @Test
  public void testWithCTFeature() {
    String document = "This is the document.";
    String script = "";
    script += "DECLARE Annotation Type(STRING ct);\n";
    script += "CW{-> CREATE(Type, \"ct\" = \"cw\")};";
    script += "SW{-> CREATE(Type, \"ct\" = \"sw\")};";
    script += "Type.ct == \"cw\" {-> T1};";
    script += "Type.ct == \"sw\" {-> T2};";
    script += "Type.ct == \"This\" {-> T3};";
    script += "Type.ct == \"is\" {-> T4};";
    script += "Type.ct == Type.coveredText {-> T5};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "Type";
    typeMap.put(typeName1, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    String fn1 = "ct";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "is", "the", "document");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 0);

    cas.release();
  }

  @Test
  public void testCoveredText() {
    String document = "This is the document.";
    String script = "STRING var = \"This\";";
    script += "W.ct == \"This\"{-> T1};";
    script += "W.ct == \"is\"{-> T2};";
    script += "W.coveredText == \"This\"{-> T3};";
    script += "W.coveredText == \"is\"{-> T4};";
    script += "W{W.ct == var -> T5};";
    script += "W{W.coveredText == var -> T6};";
    // script += "W{-> W.coveredText = \"NEW\"};";
    // script += "W{-> SETFEATURE(\"coveredText\", \"NEW\")};";

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "is");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "is");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, "This");

    cas.release();
  }
}
