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
package org.apache.uima.ruta.rule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;
import org.xml.sax.SAXException;

public class RutaAnnotationTypeMatcherTest {

  @Test
  public void test() throws Exception {
    String document = "This is a test.";
    String script = "";
    script += "CW{-> T1};\n";
    script += "COMMA{-> T2};\n";
    script += "CW.begin == 0 {-> T3};\n";
    script += "CW.begin == 1 {-> T4};\n";
    script += "ANNOTATION a, b;\n";
    script += "Document{-> a = CW};\n";
    script += "a{-> T5};\n";
    script += "b{-> T6};\n";
    script += "a.begin == 0 {-> T7};\n";
    script += "a.begin == 1 {-> T8};\n";
    script += "b.begin == 0 {-> T9};\n";
    script += "ANNOTATIONLIST as;\n";
    script += "ANNOTATIONLIST bs;\n";
    script += "Document{-> as = SW};\n";
    script += "as{-> T10};\n";
    script += "bs{-> T11};\n";
    script += "as.begin == 5 {-> T12};\n";
    script += "as.begin == 0 {-> T13};\n";
    script += "bs.begin == 0 {-> T14};\n";
    script += "TYPE t = CW;\n";
    script += "TYPE u = TruePositive;\n";
    script += "t{-> T15};\n";
    script += "u{-> T16};\n";
    script += "t.begin == 0 {-> T17};\n";
    script += "t.begin == 1 {-> T18};\n";
    script += "u.begin == 0 {-> T19};\n";
    script += "Document{-> Struct1, Struct1.a = CW, Struct1.as = SW};\n";
    script += "Struct1.a{-> T20};\n";
    script += "Struct1.a.begin==0{-> T21};\n";
    script += "Struct1.a.begin==1{-> T22};\n";
    script += "Struct1.as{-> T23};\n";
    script += "Struct1.as.begin==5{-> T24};\n";
    script += "Struct1.as.begin==0{-> T25};\n";
    script += "c:CW{->T26}<-{c.begin==0;};\n";
    script += "s:Struct1->{s.as{-> T27};};\n";
    script += "s:Struct1->{s.as.begin==5{-> T28};};\n";

    CAS cas = apply(document, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 10, 3, "is", "a", "test");
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 12, 1, "is");
    RutaTestUtils.assertAnnotationsEquals(cas, 13, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 15, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 16, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 17, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 18, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 19, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 20, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 21, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 22, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 23, 3, "is", "a", "test");
    RutaTestUtils.assertAnnotationsEquals(cas, 24, 1, "is");
    RutaTestUtils.assertAnnotationsEquals(cas, 25, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 26, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 27, 3, "is", "a", "test");
    RutaTestUtils.assertAnnotationsEquals(cas, 28, 1, "is");

  }

  private CAS apply(String document, String script) throws ResourceInitializationException,
          IOException, InvalidXMLException, ResourceConfigurationException,
          AnalysisEngineProcessException, URISyntaxException, SAXException {
    Map<String, String> complexTypes = new TreeMap<String, String>();
    complexTypes.put("Struct1", "uima.tcas.Annotation");
    complexTypes.put("Struct2", "uima.tcas.Annotation");
    complexTypes.put("Struct3", "uima.tcas.Annotation");
    complexTypes.put("Struct4", "uima.tcas.Annotation");

    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put("Struct1", list);
    features.put("Struct2", list);
    features.put("Struct3", list);
    features.put("Struct4", list);
    list.add(new TestFeature("a", "", "uima.tcas.Annotation"));
    list.add(new TestFeature("as", "", "uima.cas.FSArray"));

    CAS cas = RutaTestUtils.getCAS(document, complexTypes, features);
    Ruta.apply(cas, script);
    return cas;
  }

}
