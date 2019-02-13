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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class StringFeatureTest {

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

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "Struct";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
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

}
