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

package org.apache.uima.ruta.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.jupiter.api.Test;
public class ConditionedAnnotationTypeExpressionTest {

  @Test
  public void test() throws Exception {
    String document = "This is a test.";
    String script = "";
    script += "Document{-> CREATE(Struct1, \"a\" = SW{SW.begin==8})};\n";
    script += "Document{-> CREATE(Struct2, \"a\" = SW.begin==8{REGEXP(\"a\")})};\n";
    script += "Struct1.a{-> T1};\n";
    script += "Struct2.a{-> T2};\n";
    
    Map<String, String> complexTypes = new TreeMap<String, String>();
    complexTypes.put("Struct1", "uima.tcas.Annotation");
    complexTypes.put("Struct2", "uima.tcas.Annotation");
    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put("Struct1", list);
    features.put("Struct2", list);
    list.add(new TestFeature("a", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("as", "", "uima.cas.FSArray"));
    
    CAS cas = RutaTestUtils.getCAS(document, complexTypes, features);
    Ruta.apply(cas, script);    
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "a");
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "a");
  }

}
