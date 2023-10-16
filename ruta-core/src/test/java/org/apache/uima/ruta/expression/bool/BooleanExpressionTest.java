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
package org.apache.uima.ruta.expression.bool;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.jupiter.api.Test;

public class BooleanExpressionTest {

  @Test
  public void testBooleanAssignment() throws Exception {

    Map<String, String> typeMap = new TreeMap<>();
    typeMap.put("Struct", "uima.tcas.Annotation");
    typeMap.put("Temp", "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<>();
    featureMap.put("Struct", Arrays.asList(new TestFeature("bf", "", "uima.cas.Boolean")));
    featureMap.put("Temp", Arrays.asList(new TestFeature("sf", "", "uima.cas.String")));

    String document = "This is a test.";

    String script = "";
    script += "STRING vsTrue = \"true\";\n";
    script += "STRING vsFalse = \"FALSE\";\n";
    script += "Document{->Struct, t:Temp, t.sf=\"true\"};\n";

    script += "s:Struct{-> s.bf = false};\n";
    script += "s:Struct{-> s.bf = true};\n";
    script += "s:Struct{s.bf-> T1};\n";

    script += "s:Struct{-> s.bf = false};\n";
    script += "s:Struct{-> s.bf = vsTrue};\n";
    script += "s:Struct{s.bf-> T2};\n";

    script += "s:Struct{-> s.bf = false};\n";
    script += "s:Struct{-> s.bf = Temp.sf};\n";
    script += "s:Struct{s.bf-> T3};\n";

    script += "s:Struct{-> s.bf = false};\n";
    script += "s:Struct{-> s.bf = \"tr\" + \"ue\"};\n";
    script += "s:Struct{s.bf-> T4};\n";

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "This is a test.");
  }

}
