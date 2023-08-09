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
public class AnnotationTypeExpressionTest {

  @Test
  public void testEmptyAnnotationList() throws Exception {
    String document = "This is a test.";
    String script = "";
    script += "Document{-> CREATE(Struct1, \"as\" = COMMA)};\n";
    script += "Struct1.as{-> T1};\n";

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

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
  }

  @Test
  public void testTypeList() throws Exception {
    String document = "This is a test.";
    String script = "TYPELIST tl = {CW,PERIOD};\n";
    script += "tl{-> T1};\n";
    script += "SW tl{-> T2};\n";
    script += "tl{-> T3} @SW;\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "This", ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "This");
  }

}
